
package com.pes.rekindle.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pes.rekindle.dto.DTOChat;
import com.pes.rekindle.dto.DTOMessage;
import com.pes.rekindle.dto.DTOService;
import com.pes.rekindle.dto.DTOUser;
import com.pes.rekindle.entities.Admin;
import com.pes.rekindle.entities.Chat;
import com.pes.rekindle.entities.Donation;
import com.pes.rekindle.entities.Education;
import com.pes.rekindle.entities.Job;
import com.pes.rekindle.entities.Lodge;
import com.pes.rekindle.entities.Message;
import com.pes.rekindle.entities.Refugee;
import com.pes.rekindle.entities.Volunteer;
import com.pes.rekindle.repositories.AdminRepository;
import com.pes.rekindle.repositories.ChatRepository;
import com.pes.rekindle.repositories.DonationRepository;
import com.pes.rekindle.repositories.EducationRepository;
import com.pes.rekindle.repositories.JobRepository;
import com.pes.rekindle.repositories.LodgeRepository;
import com.pes.rekindle.repositories.MessageRepository;
import com.pes.rekindle.repositories.RefugeeRepository;
import com.pes.rekindle.repositories.VolunteerRepository;

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
    ChatRepository chatRepository;
    @Autowired
    MessageRepository messageRepository;

    public void createVolunteer(Volunteer volunteer) throws Exception {
        Optional<Volunteer> oVolunteer = volunteerRepository
                .findOptionalByMail(volunteer.getMail());
        Optional<Refugee> oRefugee = refugeeRepository.findOptionalByMail(volunteer.getMail());
        if (oVolunteer.isPresent() || oRefugee.isPresent()) {
            throw new Exception();
        } else {
            volunteerRepository.save(volunteer);
        }
    }

    @Override
    public void createRefugee(DTOUser DTOUser) throws Exception {
        Optional<Refugee> oRefugee = refugeeRepository.findOptionalByMail(DTOUser.getMail());
        Optional<Volunteer> oVolunteer = volunteerRepository
                .findOptionalByMail(DTOUser.getMail());
        if (oRefugee.isPresent() || oVolunteer.isPresent()) {
            throw new Exception();
        } else {
            refugeeRepository.save(new Refugee(DTOUser));
        }
    }

    public DTOUser logIn(String mail, String password) {
        DTOUser user = logInRefugee(mail, password);
        if (user == null) {
            user = logInVolunteer(mail, password);
            if(user == null) {
            	user = logInAdmin(mail, password);
            }
        }
        return user;
    }

    // Mirar visibilidad
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
        volunteerRepository.flush();
    }

    public void modifyProfileRefugee(DTOUser dtoUser) {
        Refugee refugee = refugeeRepository.findByMail(dtoUser.getMail());
        refugee.setName(dtoUser.getName());
        refugee.setSurname1(dtoUser.getSurname1());
        refugee.setSurname2(dtoUser.getSurname2());
        refugee.setPhoneNumber(dtoUser.getPhoneNumber());
        refugee.setBirthdate(dtoUser.getBirthdate());
        refugee.setSex(dtoUser.getSex());
        refugee.setCountry(dtoUser.getCountry());
        refugee.setTown(dtoUser.getTown());
        refugee.setEthnic(dtoUser.getEthnic());
        refugee.setBloodType(dtoUser.getBloodType());
        refugee.setEyeColor(dtoUser.getEyeColor());
        refugee.setBiography(dtoUser.getBiography());
        refugee.setPhoto(dtoUser.getPhoto());
        refugeeRepository.flush();
    }

    @Override
    public DTOUser infoVolunteer(String mail) {
        Optional<Volunteer> oVolunteer = volunteerRepository.findOptionalByMail(mail);
        if (oVolunteer.isPresent())
            return new DTOUser(oVolunteer.get());
        return null;
    }

    @Override
    public DTOUser infoRefugee(String mail) {
        Optional<Refugee> oRefugee = refugeeRepository.findOptionalByMail(mail);
        if (oRefugee.isPresent())
            return new DTOUser(oRefugee.get());
        return null;
    }

    @Override
    public DTOUser exists(String mail, String password) {
        Optional<Refugee> oRefugee = refugeeRepository.findOptionalByMailAndPassword(mail,
                password);
        if (oRefugee.isPresent()) {
            return new DTOUser(oRefugee.get());
        }
        Optional<Volunteer> oVolunteer = volunteerRepository.findOptionalByMailAndPassword(mail,
                password);
        if (oVolunteer.isPresent()) {
            return new DTOUser(oVolunteer.get());
        }
        Optional<Admin> oAdmin = adminRepository.findOptionalByMailAndPassword(mail,
                password);
        if (oAdmin.isPresent()) {
            return new DTOUser(oAdmin.get());
        }
        DTOUser loginFail = new DTOUser();
        loginFail.setUserType("Error");
        return loginFail;
    }

    @Override
    public Set<DTOUser> findRefugee(String name, String surname1, String surname2,
            Date birthdate,
            String sex,
            String country, String town, String ethnic, String blood, String eye, String mail) {
        Set<Refugee> result = new HashSet<Refugee>();
        result = refugeeRepository.findAll();
        if (!name.equals("")) {
            result.retainAll(refugeeRepository.findByName(name));
        }
        if (!surname1.equals("")) {
            result.retainAll(refugeeRepository.findBySurname1(surname1));
        }
        if (!surname2.equals("")) {
            result.retainAll(refugeeRepository.findBySurname2(surname2));
        }
        if (!birthdate.equals(Date.parse("1890-01-01"))) {
            result.retainAll(refugeeRepository.findByBirthdate(birthdate));
        }
        if (!sex.equals("-"))
            result.retainAll(refugeeRepository.findBySex(sex));

        if (!country.equals("")) {
            result.retainAll(refugeeRepository.findByCountry(country));
        }
        if (!town.equals("")) {
            result.retainAll(refugeeRepository.findByTown(town));
        }
        if (!ethnic.equals("")) {
            result.retainAll(refugeeRepository.findByEthnic(ethnic));
        }
        if (!blood.equals("-")) {
            result.retainAll(refugeeRepository.findByBloodType(blood));
        }
        if (!eye.equals("-")) {
            result.retainAll(refugeeRepository.findByEyeColor(eye));
        }
        Set<DTOUser> dtosRefugee = new HashSet<DTOUser>();
        for (Refugee refugee : result) {
            if (!refugee.getMail().equals(mail)) {
                DTOUser dtoUser = new DTOUser(refugee);
                dtoUser.setUserType("Refugee");
                dtosRefugee.add(dtoUser);
            }
        }
        return dtosRefugee;
    }

    @Override
    public boolean changePassword(String mail, String passwordOld, String passwordNew) {
        Optional<Refugee> oRefugee = refugeeRepository.findOptionalByMailAndPassword(mail,
                passwordOld);
        if (oRefugee.isPresent()) {
            Refugee refugee = oRefugee.get();
            if (refugee.getPassword().equals(passwordOld)) {
                refugee.setPassword(passwordNew);
                refugeeRepository.save(refugee);
                return true;
            }
        }
        Optional<Volunteer> oVolunteer = volunteerRepository.findOptionalByMailAndPassword(mail,
                passwordOld);
        if (oVolunteer.isPresent()) {
            Volunteer volunteer = oVolunteer.get();
            if (volunteer.getPassword().equals(passwordOld)) {
                volunteer.setPassword(passwordNew);
                volunteerRepository.save(volunteer);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean recoverPassword(String mail, String passwordNew) {
        Optional<Refugee> oRefugee = refugeeRepository.findOptionalByMail(mail);
        if (oRefugee.isPresent()) {
            Refugee refugee = oRefugee.get();
            refugee.setPassword(passwordNew);
            refugeeRepository.save(refugee);
            return true;
        }
        Optional<Volunteer> oVolunteer = volunteerRepository.findOptionalByMail(mail);
        if (oVolunteer.isPresent()) {
            Volunteer volunteer = oVolunteer.get();
            volunteer.setPassword(passwordNew);
            volunteerRepository.save(volunteer);
            return true;
        }
        return false;
    }

    @Override
    public Set<DTOService> obtainOwnServices(String mail, String userType) {
        Set<DTOService> result = new HashSet<DTOService>();
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
        for (Lodge lodge : lodges)
            result.add(new DTOService(lodge));
        for (Education education : courses)
            result.add(new DTOService(education));
        for (Donation donation : donations)
            result.add(new DTOService(donation));
        for (Job job : jobs)
            result.add(new DTOService(job));
        return result;
    }

    @Override
    public void enrollUserToService(String mail, Long id, String serviceType) throws Exception {
        switch (serviceType) {
            case "Lodge":
                enrollUserToLodge(mail, id);
                break;
            case "Education":
                enrollUserToEducation(mail, id);
                break;
            case "Donation":
                enrollUserToDonation(mail, id);
                break;
            case "Job":
                enrollUserToJob(mail, id);
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
        switch (serviceType) {
            case "Lodge":
                unenrollUserFromLodge(mail, id);
                break;
            case "Education":
                unenrollUserFromEducation(mail, id);
                break;
            case "Donation":
                unenrollUserFromDonation(mail, id);
                break;
            case "Job":
                unenrollUserFromJob(mail, id);
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
                    dtoChat.setUser1(dtoUser);
                } else {
                    Optional<Volunteer> oVolunteer = volunteerRepository
                            .findOptionalByMail(chat.getMailUser1());
                    DTOUser dtoUser = new DTOUser(oVolunteer.get());
                    dtoChat.setUser1(dtoUser);
                }

                oRefugee = refugeeRepository.findOptionalByMail(chat.getMailUser2());
                if (oRefugee.isPresent()) {
                    DTOUser dtoUser = new DTOUser(oRefugee.get());
                    dtoChat.setUser2(dtoUser);
                } else {
                    Optional<Volunteer> oVolunteer = volunteerRepository
                            .findOptionalByMail(chat.getMailUser2());
                    DTOUser dtoUser = new DTOUser(oVolunteer.get());
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
        // Set<Message> messages = chatRepository.findByMessages_IdChat(idChat);
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
                dtoMessage.setOwner(dtoUser);
            } else {
                Optional<Volunteer> oVolunteer = volunteerRepository
                        .findOptionalByMail(message.getMailSender());
                DTOUser dtoUser = new DTOUser(oVolunteer.get());
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
            dtoChat.setUser1(dtoUser);
        } else {
            Optional<Volunteer> oVolunteer = volunteerRepository
                    .findOptionalByMail(chat.getMailUser1());
            DTOUser dtoUser = new DTOUser(oVolunteer.get());
            dtoChat.setUser1(dtoUser);
        }

        oRefugee = refugeeRepository.findOptionalByMail(chat.getMailUser2());
        if (oRefugee.isPresent()) {
            DTOUser dtoUser = new DTOUser(oRefugee.get());
            dtoChat.setUser2(dtoUser);
        } else {
            Optional<Volunteer> oVolunteer = volunteerRepository
                    .findOptionalByMail(chat.getMailUser2());
            DTOUser dtoUser = new DTOUser(oVolunteer.get());
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
}
