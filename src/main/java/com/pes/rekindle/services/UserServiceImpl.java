
package com.pes.rekindle.services;

import java.sql.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pes.rekindle.dto.DTOChat;
import com.pes.rekindle.dto.DTOMessage;
import com.pes.rekindle.dto.DTOService;
import com.pes.rekindle.dto.DTOUser;
import com.pes.rekindle.entities.Chat;
import com.pes.rekindle.entities.Donation;
import com.pes.rekindle.entities.Education;
import com.pes.rekindle.entities.Job;
import com.pes.rekindle.entities.Lodge;
import com.pes.rekindle.entities.Message;
import com.pes.rekindle.entities.Refugee;
import com.pes.rekindle.entities.Volunteer;
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
            refugeeRepository.create(DTOUser.getMail(), DTOUser.getPassword(),
                    DTOUser.getName(), DTOUser.getSurname1(), DTOUser.getSurname2(),
                    DTOUser.getPhoneNumber(), DTOUser.getBirthdate(), DTOUser.getSex(),
                    DTOUser.getCountry(), DTOUser.getTown(), DTOUser.getEthnic(),
                    DTOUser.getBloodType(), DTOUser.getEyeColor(), DTOUser.getBiography());
        }
    }

    public DTOUser logIn(String mail, String password) {
        DTOUser user = logInRefugee(mail, password);
        if (user == null) {
            user = logInVolunteer(mail, password);
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
    public void modifyProfileVolunteer(String mail, String name, String surname1, String surname2) {
        Volunteer volunteer = volunteerRepository.findByMail(mail);
        volunteer.setName(name);
        volunteer.setSurname1(surname1);
        volunteer.setSurname2(surname2);
        volunteerRepository.flush();
    }

    public void modifyProfileRefugee(DTOUser DTOUser) {
        Refugee refugee = refugeeRepository.findByMail(DTOUser.getMail());
        refugee.setName(DTOUser.getName());
        refugee.setSurname1(DTOUser.getSurname1());
        refugee.setSurname2(DTOUser.getSurname2());
        refugee.setPhoneNumber(DTOUser.getPhoneNumber());
        refugee.setBirthdate(DTOUser.getBirthdate());
        refugee.setSex(DTOUser.getSex());
        refugee.setCountry(DTOUser.getCountry());
        refugee.setTown(DTOUser.getTown());
        refugee.setEthnic(DTOUser.getEthnic());
        refugee.setBloodType(DTOUser.getBloodType());
        refugee.setEyeColor(DTOUser.getEyeColor());
        refugee.setBiography(DTOUser.getBiography());
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
        DTOUser loginFail = new DTOUser();
        loginFail.setUserType("Error");
        return loginFail;
    }

    @Override
    public void enrollRefugeeLodge(String refugeeMail, long serviceId) {
        Refugee r = refugeeRepository.findByMail(refugeeMail);
        Lodge l = (Lodge) lodgeRepository.findById(serviceId);

        Set<Lodge> lodges = r.getLodges();
        Set<Refugee> refugees = l.getInscriptions();

        refugees.add(r);
        lodges.add(l);

        refugeeRepository.save(r);
        lodgeRepository.save(l);
    }

    @Override
    public void enrollRefugeeEducation(String refugeeMail, long serviceId) {
        Refugee r = refugeeRepository.findByMail(refugeeMail);
        Education e = (Education) educationRepository.findById(serviceId);

        Set<Education> courses = r.getCourses();
        Set<Refugee> refugees = e.getInscriptions();

        System.out.println("Courses size before: " + courses.size());
        System.out.println("Inscriptions size before: " + refugees.size());

        refugees.add(r);
        courses.add(e);

        System.out.println("Courses size after: " + courses.size());
        System.out.println("Inscriptions size after: " + refugees.size());

        refugeeRepository.save(r);
        educationRepository.save(e);
    }

    @Override
    public void enrollRefugeeJob(String refugeeMail, long serviceId) {
        Refugee r = refugeeRepository.findByMail(refugeeMail);
        Job j = (Job) jobRepository.findById(serviceId);

        Set<Job> jobs = r.getJobs();
        Set<Refugee> refugees = j.getInscriptions();

        refugees.add(r);
        jobs.add(j);

        refugeeRepository.save(r);
        jobRepository.save(j);
    }

    @Override
    public void enrollRefugeeDonation(String refugeeMail, long serviceId) {
        Refugee r = refugeeRepository.findByMail(refugeeMail);
        Donation d = (Donation) donationRepository.findById(serviceId);

        Set<Donation> donations = r.getDonations();
        Set<Refugee> refugees = d.getInscriptions();

        refugees.add(r);
        donations.add(d);

        refugeeRepository.save(r);
        donationRepository.save(d);
    }

    @Override
    public Set<DTOUser> findRefugee(String name, String surname1, String surname2,
            Date birthdate,
            String sex,
            String country, String town, String ethnic, String blood, String eye) {
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
        if (!birthdate.equals(Date.valueOf("1890-01-01"))) {
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
            DTOUser dtoUser = new DTOUser(refugee);
            dtoUser.setUserType("Refugee");
            dtosRefugee.add(dtoUser);
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

    public DTOUser newChat(String mailUser1, String mailUser2) {
        return null;
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
        newDtoChat.setUser1(dtoChat.getUser2());
        return newDtoChat;
    }

    @Override
    public Set<DTOMessage> listMessagesChat(String mail, long idChat) {
        // Set<Message> messages = chatRepository.findByMessages_IdChat(idChat);
        Set<Message> messages = chatRepository.findById(idChat).getMessages();
        Set<DTOMessage> dtoMessages = new HashSet<DTOMessage>();
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
		}
		else {
			chat = chatRepository.findByMailUser1AndMailUser2(mail2, mail1);
		}
		
		if (chat==null) {
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
		chat.addMessage(message);
		chatRepository.save(chat);
		*/
	}
    
	//-------------------------------------------------------------------
	@Override
	public String test() {
		return "Hola";
	}
}
