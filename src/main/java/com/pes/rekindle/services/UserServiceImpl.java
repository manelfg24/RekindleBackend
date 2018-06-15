
package com.pes.rekindle.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.security.auth.login.LoginException;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pes.rekindle.dto.DTOChat;
import com.pes.rekindle.dto.DTOLink;
import com.pes.rekindle.dto.DTOLogInInfo;
import com.pes.rekindle.dto.DTOMessage;
import com.pes.rekindle.dto.DTOReport;
import com.pes.rekindle.dto.DTOService;
import com.pes.rekindle.dto.DTOUser;
import com.pes.rekindle.entities.Admin;
import com.pes.rekindle.entities.Chat;
import com.pes.rekindle.entities.Donation;
import com.pes.rekindle.entities.Education;
import com.pes.rekindle.entities.Job;
import com.pes.rekindle.entities.Link;
import com.pes.rekindle.entities.Lodge;
import com.pes.rekindle.entities.Message;
import com.pes.rekindle.entities.Refugee;
import com.pes.rekindle.entities.Report;
import com.pes.rekindle.entities.Volunteer;
import com.pes.rekindle.exceptions.ReportNotExistsException;
import com.pes.rekindle.exceptions.UserAlreadyExistsException;
import com.pes.rekindle.exceptions.UserNotExistsException;
import com.pes.rekindle.exceptions.UserStateAlreadyUpdatedException;
import com.pes.rekindle.repositories.AdminRepository;
import com.pes.rekindle.repositories.ChatRepository;
import com.pes.rekindle.repositories.DonationRepository;
import com.pes.rekindle.repositories.EducationRepository;
import com.pes.rekindle.repositories.JobRepository;
import com.pes.rekindle.repositories.LinkRepository;
import com.pes.rekindle.repositories.LodgeRepository;
import com.pes.rekindle.repositories.MessageRepository;
import com.pes.rekindle.repositories.RefugeeRepository;
import com.pes.rekindle.repositories.ReportRepository;
import com.pes.rekindle.repositories.VolunteerRepository;
import com.pusher.rest.Pusher;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    VolunteerRepository volunteerRepository;
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    RefugeeRepository refugeeRepository;
    @Autowired
    LodgeRepository lodgeRepository;
    @Autowired
    EducationRepository educationRepository;
    @Autowired
    DonationRepository donationRepository;
    @Autowired
    JobRepository jobRepository;

    @Autowired
    ServiceService serviceService;

    @Autowired
    ReportRepository reportRepository;

    @Autowired
    ChatRepository chatRepository;
    @Autowired
    MessageRepository messageRepository;

    @Autowired
    LinkRepository linkRepository;

    @Autowired
    DozerBeanMapper mapper;

    private boolean exists(DTOUser dtoUser) {
        Optional<Volunteer> oVolunteer = volunteerRepository.findOptionalByMail(dtoUser.getMail());
        if (oVolunteer.isPresent()) {
            return true;
        } else {
            Optional<Refugee> oRefugee = refugeeRepository.findOptionalByMail(dtoUser.getMail());
            if (oRefugee.isPresent()) {
                return true;
            }
        }
        return false;
    }

    private String md5(String mail) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(mail.getBytes());

        byte byteData[] = md.digest();

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();

    }

    @Override
    public boolean authenticate(String mail, String apiKey) {
        Optional<Refugee> oRefugee = refugeeRepository.findOptionalByMail(mail);
        if (oRefugee.isPresent()) {
            Refugee refugee = oRefugee.get();
            return (refugee.getApiKey().equals(apiKey));
        } else {
            Optional<Volunteer> oVolunteer = volunteerRepository.findOptionalByMail(mail);
            if (oVolunteer.isPresent()) {
                Volunteer volunteer = oVolunteer.get();
                return (volunteer.getApiKey().equals(apiKey));
            }
            Optional<Admin> oAdmin = adminRepository.findOptionalByMail(mail);
            if (oAdmin.isPresent()) {
                Admin admin = oAdmin.get();
                return (admin.getApiKey().equals(apiKey));
            }
            return false;
        }
    }

    @Override
    public DTOUser hideCredentials(DTOUser dtoUser) {
        dtoUser.setApiKey("");
        dtoUser.setPassword("");
        return dtoUser;
    }

    public void createVolunteer(DTOUser dtoVolunteer) throws UserAlreadyExistsException {
        if (exists(dtoVolunteer)) {
            throw new UserAlreadyExistsException();
        } else {
            Volunteer volunteer = mapper.map(dtoVolunteer, Volunteer.class);
            volunteer.setApiKey(md5(dtoVolunteer.getMail()));
            volunteerRepository.save(volunteer);
        }
    }

    @Override
    public void createRefugee(DTOUser dtoRefugee) throws UserAlreadyExistsException {
        if (exists(dtoRefugee)) {
            throw new UserAlreadyExistsException();
        } else {
            Refugee refugee = mapper.map(dtoRefugee, Refugee.class);
            refugee.setApiKey(md5(dtoRefugee.getMail()));
            refugeeRepository.save(refugee);
        }
    }

    public DTOUser logIn(String mail, String password) {
        DTOUser user = logInRefugee(mail, password);
        if (user == null) {
            user = logInVolunteer(mail, password);
            if (user == null) {
                user = logInAdmin(mail, password);
            }
        }
        return user;
    }

    public DTOUser logInVolunteer(String mail, String password) {
        Optional<Volunteer> oVolunteer = volunteerRepository.findOptionalByMailAndPassword(mail,
                password);
        Volunteer volunteer = new Volunteer();
        if (oVolunteer.isPresent()) {
            volunteer = oVolunteer.get();
        }
        return new DTOUser(volunteer);
    }

    public DTOUser logInAdmin(String mail, String password) {
        Optional<Admin> oAdmin = adminRepository.findOptionalByMailAndPassword(mail,
                password);
        Admin admin = new Admin();
        if (oAdmin.isPresent()) {
            admin = oAdmin.get();
        }
        return new DTOUser(admin);
    }

    public DTOUser logInRefugee(String mail, String password) {
        Optional<Refugee> oRefugee = refugeeRepository.findOptionalByMailAndPassword(mail,
                password);
        DTOUser DTOUser = null;
        Refugee refugee = new Refugee();
        if (oRefugee.isPresent()) {
            refugee = oRefugee.get();
            DTOUser = new DTOUser(refugee);
        }
        return DTOUser;
    }

    public Boolean changePasswordVolunteer(String mail, String password, String newPassword) {
        Volunteer volunteer = volunteerRepository.findByMail(mail);
        if (volunteer.getPassword().equals(password)) {
            volunteer.setPassword(newPassword);
            volunteerRepository.save(volunteer);
            return true;
        } else {
            return false;
        }
    }

    public Boolean changePasswordRefugee(String mail, String password, String newPassword) {
        Refugee refugee = refugeeRepository.findByMail(mail);
        if (refugee.getPassword().equals(password)) {
            refugee.setPassword(newPassword);
            refugeeRepository.save(refugee);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void modifyProfileVolunteer(DTOUser dtoUser) {
        Volunteer volunteer = volunteerRepository.findByMail(dtoUser.getMail());
        volunteer.setName(dtoUser.getName());
        volunteer.setSurname1(dtoUser.getSurname1());
        volunteer.setSurname2(dtoUser.getSurname2());
        volunteer.setPhoto(dtoUser.getPhoto());
        volunteerRepository.save(volunteer);
    }

    public void modifyProfileRefugee(DTOUser dtoUser) {
        Refugee refugee = refugeeRepository.findByMail(dtoUser.getMail());
        refugee.setName(dtoUser.getName());
        refugee.setSurname1(dtoUser.getSurname1());
        refugee.setSurname2(dtoUser.getSurname2());
        refugee.setPhoneNumber(dtoUser.getPhoneNumber());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date birthdate = formatter.parse(dtoUser.getBirthdate());
            refugee.setBirthdate(birthdate);
        } catch (Exception e) {
        }
        refugee.setSex(dtoUser.getSex());
        refugee.setCountry(dtoUser.getCountry());
        refugee.setTown(dtoUser.getTown());
        refugee.setEthnic(dtoUser.getEthnic());
        refugee.setBloodType(dtoUser.getBloodType());
        refugee.setEyeColor(dtoUser.getEyeColor());
        refugee.setBiography(dtoUser.getBiography());
        refugee.setPhoto(dtoUser.getPhoto());
        refugeeRepository.save(refugee);
    }

    @Override
    public DTOUser getVolunteer(String mail) throws UserNotExistsException {
        Optional<Volunteer> oVolunteer = volunteerRepository.findOptionalByMail(mail);
        if (oVolunteer.isPresent()) {
            return new DTOUser(oVolunteer.get());
        } else {
            throw new UserNotExistsException();
        }
    }

    @Override
    public DTOUser getRefugee(String mail) throws UserNotExistsException {
        Optional<Refugee> oRefugee = refugeeRepository.findOptionalByMail(mail);
        if (oRefugee.isPresent()) {
            return new DTOUser(oRefugee.get());
        } else {
            throw new UserNotExistsException();
        }
    }

    @Override
    public DTOUser getUser(DTOLogInInfo dtoLogInInfo) throws LoginException {
        Optional<Refugee> oRefugee = refugeeRepository.findOptionalByMailAndPassword(
                dtoLogInInfo.getMail(),
                dtoLogInInfo.getPassword());
        if (oRefugee.isPresent()) {
            DTOUser refugee = mapper.map(oRefugee.get(), DTOUser.class);
            refugee.setUserType("Refugee");
            return refugee;
        }

        Optional<Volunteer> oVolunteer = volunteerRepository.findOptionalByMailAndPassword(
                dtoLogInInfo.getMail(),
                dtoLogInInfo.getPassword());
        if (oVolunteer.isPresent()) {
            DTOUser volunteer = mapper.map(oVolunteer.get(), DTOUser.class);
            volunteer.setUserType("Volunteer");
            return volunteer;
        }

        Optional<Admin> oAdmin = adminRepository.findOptionalByMailAndPassword(
                dtoLogInInfo.getMail(),
                dtoLogInInfo.getPassword());
        if (oAdmin.isPresent()) {
            DTOUser admin = mapper.map(oAdmin.get(), DTOUser.class);
            admin.setUserType("Admin");
            return admin;
        } else {
            throw new LoginException();
        }
    }

    @Override
    public Set<DTOUser> findRefugee(String name, String surname1, String surname2, String birthdate,
            String sex, String country,
            String town, String ethnic, String blood, String eye, String mail) {
        Set<Refugee> refugees = refugeeRepository.findRefugeeByParams(name, surname1, surname2,
                birthdate, sex, country, town, ethnic, blood, eye);

        Set<DTOUser> dtosRefugee = new HashSet<DTOUser>();
        for (Refugee refugee : refugees) {
            if (!refugee.getMail().equals(mail)) {
                DTOUser dtoUser = mapper.map(refugee, DTOUser.class);
                dtoUser.setUserType("Refugee");
                dtoUser = hideCredentials(dtoUser);
                dtosRefugee.add(dtoUser);
            }
        }
        return dtosRefugee;
    }

    @Override
    public void changePassword(String mail, String passwordOld, String passwordNew)
            throws LoginException {
        DTOLogInInfo dtoLogInInfo = new DTOLogInInfo();
        dtoLogInInfo.setMail(mail);
        dtoLogInInfo.setPassword(passwordOld);
        try {
            DTOUser dtoUser = getUser(dtoLogInInfo);
            dtoUser.setPassword(passwordNew);
            switch (dtoUser.getUserType()) {
                case "Volunteer":
                    Volunteer volunteer = mapper.map(dtoUser, Volunteer.class);
                    volunteer.setPassword(passwordNew);
                    volunteerRepository.save(volunteer);
                    break;
                case "Refugee":
                    Refugee refugee = mapper.map(dtoUser, Refugee.class);
                    refugee.setPassword(passwordNew);
                    refugeeRepository.save(refugee);
                    break;
                case "Admin":
                    Admin admin = mapper.map(dtoUser, Admin.class);
                    admin.setPassword(passwordNew);
                    adminRepository.save(admin);
                    break;
            }
        } catch (Exception e) {
            throw new LoginException();
        }
    }

    @Override
    public void recoverPassword(String mail, String passwordNew) throws LoginException {
        Optional<Refugee> oRefugee = refugeeRepository.findOptionalByMail(mail);
        if (oRefugee.isPresent()) {
            Refugee refugee = oRefugee.get();
            refugee.setPassword(passwordNew);
            refugeeRepository.save(refugee);
        } else {
            Optional<Volunteer> oVolunteer = volunteerRepository.findOptionalByMail(mail);
            if (oVolunteer.isPresent()) {
                Volunteer volunteer = oVolunteer.get();
                volunteer.setPassword(passwordNew);
                volunteerRepository.save(volunteer);
            } else {
                throw new LoginException();
            }
        }
    }

    @Override
    public List<DTOService> obtainOwnServices(String mail, String userType, Boolean ended) {
        ArrayList<DTOService> result = new ArrayList<DTOService>();

        Set<Lodge> lodges;
        Set<Donation> donations;
        Set<Job> jobs;
        Set<Education> courses;
        if (userType.equals("Refugee")) { // refugee
            lodges = lodgeRepository.findByInscriptions_Mail(mail);
            donations = donationRepository.findByInscriptions_Mail(mail);
            courses = educationRepository.findByInscriptions_Mail(mail);
            jobs = jobRepository.findByInscriptions_Mail(mail);
        } else { // volunteer
            lodges = lodgeRepository.findByVolunteer(mail);
            donations = donationRepository.findByVolunteer(mail);
            courses = educationRepository.findByVolunteer(mail);
            jobs = jobRepository.findByVolunteer(mail);
        }
        for (Lodge lodge : lodges) {
            if (lodge.getEnded() == ended) {
                result.add(new DTOService(lodge));
            }
        }
        for (Education education : courses) {
            if (education.getEnded() == ended) {
                result.add(new DTOService(education));
            }
        }
        for (Donation donation : donations) {
            if (donation.getEnded() == ended) {
                result.add(new DTOService(donation));
            }
        }
        for (Job job : jobs) {
            if (job.getEnded() == ended) {
                result.add(new DTOService(job));
            }
        }
        return result;
    }

    @Override
    public void enrollUserToService(String mail, Long id, String serviceType) throws Exception {
        Pusher pusher = new Pusher("525518", "743a4fb4a1370f0ca9a4",
                "c78f3bfa72330a58ee1f");
        pusher.setCluster("eu");
        pusher.setEncrypted(true);
        switch (serviceType) {
            case "Lodge":
                enrollUserToLodge(mail, id);

                Lodge lodge = lodgeRepository.findById(id);

                pusher.trigger(mail, "enroll-service",
                        Collections.singletonMap("message", new DTOService(lodge)));
                break;
            case "Education":
                enrollUserToEducation(mail, id);

                Education education = educationRepository.findById(id);

                pusher.trigger(mail, "enroll-service",
                        Collections.singletonMap("message", new DTOService(education)));
                break;
            case "Donation":
                enrollUserToDonation(mail, id);

                Donation donation = donationRepository.findById(id);

                pusher.trigger(mail, "enroll-service",
                        Collections.singletonMap("message", new DTOService(donation)));
                break;
            case "Job":
                enrollUserToJob(mail, id);

                Job job = jobRepository.findById(id);

                pusher.trigger(mail, "enroll-service",
                        Collections.singletonMap("message", new DTOService(job)));
                break;
        }
    }

    private void enrollUserToLodge(String mail, Long id) throws Exception {
        Lodge lodge = serviceService.getLodge(id);
        java.util.Date today = Calendar.getInstance().getTime();
        int enrolledCount = lodge.getInscriptions().size() + 1;
        /*
         * System.out.println("---------------------------------------");
         * System.out.println("Numero de places total: " + lodge.getPlaces());
         * System.out.println("Numero de places ocupadas: " + lodge.getInscriptions().size()+1);
         * System.out.println("---------------------------------------");
         * System.out.println("Data del servicio: " + lodge.getDateLimit());
         * System.out.println("Data actual: " + Calendar.getInstance().getTime());
         * System.out.println("---------------------------------------");
         */

        if (enrolledCount > lodge.getPlaces() /* || today.after(lodge.getDateLimit()) */) {
            throw new Exception();
        } else {
            Refugee refugee = refugeeRepository.findByMail(mail);

            Set<Lodge> lodges = refugee.getLodges();
            Set<Refugee> refugees = lodge.getInscriptions();

            lodges.add(lodge);
            refugees.add(refugee);

            refugee.setLodges(lodges);
            lodge.setInscriptions(refugees);

            // sobra un save
            refugeeRepository.save(refugee);
            lodgeRepository.save(lodge);
        }
    }

    private void enrollUserToEducation(String mail, Long id) throws Exception {
        Education education = serviceService.getEducation(id);
        java.util.Date today = Calendar.getInstance().getTime();
        int enrolledCount = education.getInscriptions().size() + 1;

        if (enrolledCount > education.getPlaces()) {
            throw new Exception();
        } else {
            Refugee refugee = refugeeRepository.findByMail(mail);

            Set<Education> courses = refugee.getCourses();
            Set<Refugee> refugees = education.getInscriptions();

            courses.add(education);
            refugees.add(refugee);

            refugee.setCourses(courses);
            education.setInscriptions(refugees);

            refugeeRepository.save(refugee);
            educationRepository.save(education);
        }
    }

    private void enrollUserToDonation(String mail, Long id) throws Exception {
        Donation donation = serviceService.getDonation(id);
        java.util.Date today = Calendar.getInstance().getTime();
        int enrolledCount = donation.getInscriptions().size() + 1;

        if (enrolledCount > donation.getPlaces()) {
            throw new Exception();
        } else {
            Refugee refugee = refugeeRepository.findByMail(mail);

            Set<Donation> donations = refugee.getDonations();
            Set<Refugee> refugees = donation.getInscriptions();

            donations.add(donation);
            refugees.add(refugee);

            refugee.setDonations(donations);
            donation.setInscriptions(refugees);

            refugeeRepository.save(refugee);
            donationRepository.save(donation);
        }
    }

    private void enrollUserToJob(String mail, Long id) throws Exception {
        Job job = serviceService.getJob(id);
        Date today = Calendar.getInstance().getTime();
        int enrolledCount = job.getInscriptions().size() + 1;

        if (enrolledCount > job.getPlaces()) {
            throw new Exception();
        } else {
            Refugee refugee = refugeeRepository.findByMail(mail);

            Set<Job> jobs = refugee.getJobs();
            Set<Refugee> refugees = job.getInscriptions();

            jobs.add(job);
            refugees.add(refugee);

            refugee.setJobs(jobs);
            job.setInscriptions(refugees);

            refugeeRepository.save(refugee);
            jobRepository.save(job);
        }
    }

    @Override
    public void unenrollUserFromService(String mail, Long id, String serviceType) {
        Pusher pusher = new Pusher("525518", "743a4fb4a1370f0ca9a4",
                "c78f3bfa72330a58ee1f");
        pusher.setCluster("eu");
        pusher.setEncrypted(true);
        switch (serviceType) {
            case "Lodge":
                unenrollUserFromLodge(mail, id);

                Lodge lodge = lodgeRepository.findById(id);

                pusher.trigger(mail, "unenroll-service",
                        Collections.singletonMap("message", new DTOService(lodge)));
                break;
            case "Education":
                unenrollUserFromEducation(mail, id);

                Education education = educationRepository.findById(id);

                pusher.trigger(mail, "unenroll-service",
                        Collections.singletonMap("message", new DTOService(education)));
                break;
            case "Donation":
                unenrollUserFromDonation(mail, id);

                Donation donation = donationRepository.findById(id);

                pusher.trigger(mail, "unenroll-service",
                        Collections.singletonMap("message", new DTOService(donation)));
                break;
            case "Job":
                unenrollUserFromJob(mail, id);

                Job job = jobRepository.findById(id);

                pusher.trigger(mail, "unenroll-service",
                        Collections.singletonMap("message", new DTOService(job)));
                break;
        }
    }

    private void unenrollUserFromJob(String mail, Long id) {
        Refugee refugee = refugeeRepository.findByMail(mail);
        Job job = serviceService.getJob(id);

        Set<Job> jobs = refugee.getJobs();
        Set<Refugee> refugees = job.getInscriptions();

        jobs.remove(job);
        refugees.remove(refugee);

        refugee.setJobs(jobs);
        job.setInscriptions(refugees);

        refugeeRepository.save(refugee);
        jobRepository.save(job);
    }

    private void unenrollUserFromDonation(String mail, Long id) {
        Refugee refugee = refugeeRepository.findByMail(mail);
        Donation donation = serviceService.getDonation(id);

        Set<Donation> donations = refugee.getDonations();
        Set<Refugee> refugees = donation.getInscriptions();

        donations.remove(donation);
        refugees.remove(refugee);

        refugee.setDonations(donations);
        donation.setInscriptions(refugees);

        refugeeRepository.save(refugee);
        donationRepository.save(donation);

    }

    private void unenrollUserFromEducation(String mail, Long id) {
        Refugee refugee = refugeeRepository.findByMail(mail);
        Education education = serviceService.getEducation(id);

        Set<Education> courses = refugee.getCourses();
        Set<Refugee> refugees = education.getInscriptions();

        courses.remove(education);
        refugees.remove(refugee);

        refugee.setCourses(courses);
        education.setInscriptions(refugees);

        refugeeRepository.save(refugee);
        educationRepository.save(education);

    }

    private void unenrollUserFromLodge(String mail, Long id) {
        Refugee refugee = refugeeRepository.findByMail(mail);
        Lodge lodge = serviceService.getLodge(id);

        Set<Lodge> lodges = refugee.getLodges();
        Set<Refugee> refugees = lodge.getInscriptions();

        lodges.remove(lodge);
        refugees.remove(refugee);

        refugee.setLodges(lodges);
        lodge.setInscriptions(refugees);

        refugeeRepository.save(refugee);
        lodgeRepository.save(lodge);
    }

    @Override
    public Set<DTOChat> listUserChats(String mail) {
        Set<DTOChat> dtoChats = new HashSet<DTOChat>();
        if (chatRepository.existsByMailUser1(mail) || chatRepository.existsByMailUser2(mail)) {
            Set<Chat> chats = chatRepository.findByMailUser1(mail);
            chats.addAll(chatRepository.findByMailUser2(mail));

            for (Chat chat : chats) {
                DTOChat dtoChat = new DTOChat();
                dtoChat.setId(chat.getId());
                Optional<Refugee> oRefugee = refugeeRepository
                        .findOptionalByMail(chat.getMailUser1());
                if (oRefugee.isPresent()) {
                    DTOUser dtoUser = new DTOUser(oRefugee.get());
                    dtoUser = hideCredentials(dtoUser);
                    dtoChat.setUser1(dtoUser);
                } else {
                    Optional<Volunteer> oVolunteer = volunteerRepository
                            .findOptionalByMail(chat.getMailUser1());
                    DTOUser dtoUser = null;
                    if (oVolunteer.isPresent()) {
                        dtoUser = new DTOUser(oVolunteer.get());
                        dtoUser = hideCredentials(dtoUser);
                    }
                    dtoChat.setUser1(dtoUser);
                }

                oRefugee = refugeeRepository.findOptionalByMail(chat.getMailUser2());
                if (oRefugee.isPresent()) {
                    DTOUser dtoUser = new DTOUser(oRefugee.get());
                    dtoChat.setUser2(dtoUser);
                } else {
                    Optional<Volunteer> oVolunteer = volunteerRepository
                            .findOptionalByMail(chat.getMailUser2());
                    DTOUser dtoUser = null;
                    if (oVolunteer.isPresent()) {
                        dtoUser = new DTOUser(oVolunteer.get());
                    }

                    dtoChat.setUser2(dtoUser);
                }
                dtoChats.add(dtoChat);
            }
        }
        return dtoChats;
    }

    @Override
    public DTOChat createChat(DTOChat dtoChat) {
        Chat chat = new Chat();
        chat.setMailUser1(dtoChat.getUser1().getMail());
        chat.setMailUser2(dtoChat.getUser2().getMail());
        chatRepository.save(chat);
        Chat newChat = chatRepository.findByMailUser1AndMailUser2(dtoChat.getUser1().getMail(),
                dtoChat.getUser2().getMail());
        DTOChat newDtoChat = new DTOChat();
        newDtoChat.setId(newChat.getId());
        newDtoChat.setUser1(dtoChat.getUser1());
        newDtoChat.setUser2(dtoChat.getUser2());
        return newDtoChat;
    }

    @Override
    public List<DTOMessage> listMessagesChat(String mail, long idChat) {
        ArrayList<Message> messages = new ArrayList<Message>();
        messages.addAll(chatRepository.findById(idChat).getMessages());
        Collections.sort(messages, new Comparator<Message>() {
            public int compare(Message m1, Message m2) {
                return m1.getTimestamp().compareTo(m2.getTimestamp());
            }
        });
        List<DTOMessage> dtoMessages = new ArrayList<DTOMessage>();
        for (Message message : messages) {
            DTOMessage dtoMessage = new DTOMessage(message);
            Optional<Refugee> oRefugee = refugeeRepository
                    .findOptionalByMail(message.getMailSender());
            if (oRefugee.isPresent()) {
                DTOUser dtoUser = new DTOUser(oRefugee.get());
                dtoUser = hideCredentials(dtoUser);
                dtoMessage.setOwner(dtoUser);
            } else {
                Optional<Volunteer> oVolunteer = volunteerRepository
                        .findOptionalByMail(message.getMailSender());
                DTOUser dtoUser = null;
                if (oVolunteer.isPresent()) {
                    dtoUser = new DTOUser(oVolunteer.get());
                    dtoUser = hideCredentials(dtoUser);
                }
                dtoMessage.setOwner(dtoUser);
            }
            dtoMessages.add(dtoMessage);
        }
        return dtoMessages;
    }

    @Override
    public DTOChat getChat(String mail1, String mail2) {
        Optional<Chat> oChat = chatRepository.findOptionalByMailUser1AndMailUser2(mail1, mail2);
        Chat chat;
        if (oChat.isPresent()) {
            chat = oChat.get();
        } else {
            chat = chatRepository.findByMailUser1AndMailUser2(mail2, mail1);
        }

        if (chat == null) {
            return null;
        }

        DTOChat dtoChat = new DTOChat();

        dtoChat.setId(chat.getId());

        Optional<Refugee> oRefugee = refugeeRepository
                .findOptionalByMail(chat.getMailUser1());
        if (oRefugee.isPresent()) {
            DTOUser dtoUser = new DTOUser(oRefugee.get());
            dtoUser = hideCredentials(dtoUser);
            dtoChat.setUser1(dtoUser);
        } else {
            Optional<Volunteer> oVolunteer = volunteerRepository
                    .findOptionalByMail(chat.getMailUser1());
            DTOUser dtoUser = null;
            if (oVolunteer.isPresent()) {
                dtoUser = new DTOUser(oVolunteer.get());
                dtoUser = hideCredentials(dtoUser);
            }
            dtoChat.setUser1(dtoUser);
        }

        oRefugee = refugeeRepository.findOptionalByMail(chat.getMailUser2());
        if (oRefugee.isPresent()) {
            DTOUser dtoUser = new DTOUser(oRefugee.get());
            dtoChat.setUser2(dtoUser);
        } else {
            Optional<Volunteer> oVolunteer = volunteerRepository
                    .findOptionalByMail(chat.getMailUser2());
            DTOUser dtoUser = null;
            if (oVolunteer.isPresent()) {
                dtoUser = new DTOUser(oVolunteer.get());
            }
            dtoChat.setUser2(dtoUser);
        }
        return dtoChat;
    }

    @Override
    public void sendMessage(String mail, long idChat, DTOMessage dtoMessage) {
        Chat chat = chatRepository.findById(idChat);
        Message message = new Message();

        message.setChat(chat);
        message.setContent(dtoMessage.getContent());
        message.setMailSender(dtoMessage.getOwner().getMail());
        message.setTimestamp(dtoMessage.getTimestamp());

        messageRepository.save(message);
        /*
         * chat.addMessage(message); chatRepository.save(chat);
         */
    }

    @Override
    public void createReport(DTOReport dtoReport) {
        Report report = new Report(dtoReport);
        reportRepository.save(report);
    }

    @Override
    public Set<DTOReport> listReports() {
        Set<Report> reports = reportRepository.findAll();
        Set<DTOReport> dtoReports = new HashSet<DTOReport>();

        for (Report report : reports) {
            DTOReport dtoReport = new DTOReport();
            dtoReport.setIdReport(report.getId());

            Optional<Refugee> oRefugee = refugeeRepository
                    .findOptionalByMail(report.getMailInformer());
            if (oRefugee.isPresent()) {
                DTOUser dtoUser = new DTOUser(oRefugee.get());
                dtoReport.setInformerUser(dtoUser);
            }

            oRefugee = refugeeRepository.findOptionalByMail(report.getMailReported());
            if (oRefugee.isPresent()) {
                DTOUser dtoUser = new DTOUser(oRefugee.get());
                dtoReport.setReportedUser(dtoUser);
            }

            dtoReport.setMotive(report.getMotive());

            dtoReports.add(dtoReport);
        }

        return dtoReports;
    }

    @Override
    public DTOReport getReport(Long id) {
        Report report = reportRepository.findById(id);

        DTOReport dtoReport = new DTOReport();
        dtoReport.setIdReport(report.getId());

        Optional<Refugee> oRefugee = refugeeRepository
                .findOptionalByMail(report.getMailInformer());
        if (oRefugee.isPresent()) {
            DTOUser dtoUser = new DTOUser(oRefugee.get());
            dtoReport.setInformerUser(dtoUser);
        }

        oRefugee = refugeeRepository.findOptionalByMail(report.getMailReported());
        if (oRefugee.isPresent()) {
            DTOUser dtoUser = new DTOUser(oRefugee.get());
            dtoReport.setReportedUser(dtoUser);
        }

        dtoReport.setMotive(report.getMotive());

        return dtoReport;
    }

    @Override
    public void createLink(DTOLink dtoLink) {
        Link link = mapper.map(dtoLink, Link.class);
        linkRepository.save(link);
    }

    @Override
    public Set<DTOLink> listLinks() {
        Set<Link> links = linkRepository.findAll();
        Set<DTOLink> dtoLinks = new HashSet();
        for (Link link : links) {
            DTOLink auxiliarLink = mapper.map(link, DTOLink.class);
            dtoLinks.add(auxiliarLink);
        }
        return dtoLinks;
    }

    @Override
    public void modifyLink(DTOLink dtoLink) {
        Link link = linkRepository.findById(dtoLink.getId());
        link.updateLink(dtoLink);
        linkRepository.save(link);
    }

    @Override
    public void deleteLink(Long id) {
        linkRepository.deleteById(id);
    }

    // -------------------------------------------------------------------
    @Override
    public String test() {
        return "Hola";
    }

    @Override
    public Boolean userAlreadyEnrolledLodge(String mail, Long id) {
        return refugeeRepository.existsByMailAndLodges_Id(mail, id);
    }

    @Override
    public Boolean userAlreadyEnrolledEducation(String mail, Long id) {
        return refugeeRepository.existsByMailAndCourses_Id(mail, id);
    }

    @Override
    public Boolean userAlreadyEnrolledDonation(String mail, Long id) {
        return refugeeRepository.existsByMailAndDonations_Id(mail, id);
    }

    @Override
    public Boolean userAlreadyEnrolledJob(String mail, Long id) {
        return refugeeRepository.existsByMailAndJobs_Id(mail, id);
    }

    @Override
    public void valorateVolunteer(String volunteer, float newValoration, float oldValoration) {
        Volunteer modifiedVolunteer = volunteerRepository.findByMail(volunteer);
        if (oldValoration == -1) {
            modifiedVolunteer
                    .setAverageValoration(modifiedVolunteer.getAverageValoration() + newValoration);
            modifiedVolunteer
                    .setNumberOfValorations(modifiedVolunteer.getNumberOfValorations() + 1);
        } else {
            modifiedVolunteer.setAverageValoration(
                    modifiedVolunteer.getAverageValoration() + newValoration - oldValoration);
        }
        volunteerRepository.save(modifiedVolunteer);

    }

    @Override
    public Set<DTOUser> getAllUsers() {
        Set<Refugee> refugees = refugeeRepository.findAll();
        Set<Volunteer> volunteers = volunteerRepository.findAll();
        Set<DTOUser> dtoUsers = new HashSet<DTOUser>();

        for (Refugee refugee : refugees) {
            dtoUsers.add(new DTOUser(refugee));
        }

        for (Volunteer volunteer : volunteers) {
            dtoUsers.add(new DTOUser(volunteer));
        }

        return dtoUsers;
    }

    @Override
    public Integer isUserEnabled(String mail) throws UserNotExistsException {
        DTOUser dtoUser = getDTOUser(mail);
        return dtoUser.getEnabled();
    }

    private DTOUser getDTOUser(String mail) throws UserNotExistsException {
        Optional<Volunteer> oVolunteer = volunteerRepository.findOptionalByMail(mail);
        if (oVolunteer.isPresent()) {
            return new DTOUser(oVolunteer.get());
        } else {
            Optional<Refugee> oRefugee = refugeeRepository.findOptionalByMail(mail);
            if (oRefugee.isPresent()) {
                return new DTOUser(oRefugee.get());
            } else {
                throw new UserNotExistsException();
            }
        }
    }

    @Override
    public void modifyBannedStatus(String mail, int userFinalState)
            throws UserNotExistsException, UserStateAlreadyUpdatedException {
        changeBanStatus(mail, userFinalState);
    }

    private void changeBanStatus(String mail, int userFinalState)
            throws UserNotExistsException, UserStateAlreadyUpdatedException {
        Optional<Volunteer> oVolunteer = volunteerRepository.findOptionalByMail(mail);
        if (oVolunteer.isPresent()) {
            Volunteer volunteer = oVolunteer.get();
            if (userFinalState == volunteer.getEnabled()) { // El estado del usuario es el mismo que
                                                            // el que nos pasan
                throw new UserStateAlreadyUpdatedException();
            } else {
                volunteer.setEnabled(userFinalState);
                volunteerRepository.save(volunteer);
            }
        } else {
            Optional<Refugee> oRefugee = refugeeRepository.findOptionalByMail(mail);
            if (oRefugee.isPresent()) {
                Refugee refugee = oRefugee.get();
                if (userFinalState == refugee.getEnabled()) {
                    throw new UserStateAlreadyUpdatedException();
                } else {
                    refugee.setEnabled(userFinalState);
                    refugeeRepository.save(refugee);
                }
            } else {
                throw new UserNotExistsException();
            }
        }
    }

    @Override
    public void deleteReport(Long id) throws ReportNotExistsException {
        Optional<Report> oReport = reportRepository.findOptionalById(id);
        if (oReport.isPresent()) {
            // mirar apikey
            reportRepository.deleteById(id);
        } else {
            throw new ReportNotExistsException();
        }
    }
}
