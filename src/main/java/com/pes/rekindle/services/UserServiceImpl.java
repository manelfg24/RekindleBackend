
package com.pes.rekindle.services;

import java.sql.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import com.pes.rekindle.dto.DTOChat;
import com.pes.rekindle.dto.DTOUser;
import com.pes.rekindle.dto.DTOUser;
import com.pes.rekindle.entities.Chat;
import com.pes.rekindle.entities.Donation;
import com.pes.rekindle.entities.Education;
import com.pes.rekindle.entities.Job;
import com.pes.rekindle.entities.Lodge;
import com.pes.rekindle.entities.Refugee;
import com.pes.rekindle.entities.Volunteer;
import com.pes.rekindle.repositories.ChatRepository;
import com.pes.rekindle.repositories.DonationRepository;
import com.pes.rekindle.repositories.EducationRepository;
import com.pes.rekindle.repositories.JobRepository;
import com.pes.rekindle.repositories.LodgeRepository;
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

    public Volunteer createVolunteer(Volunteer volunteer) throws Exception {
        Volunteer createdVolunteer = new Volunteer();
        Optional<Volunteer> oVolunteer = volunteerRepository
                .findOptionalByMail(volunteer.getMail());
        Optional<Refugee> oRefugee = refugeeRepository.findOptionalByMail(volunteer.getMail());
        if (oVolunteer.isPresent() || oRefugee.isPresent()) {
            throw new Exception();
        } else {
            volunteerRepository.create(volunteer.getMail(), volunteer.getPassword(),
                    volunteer.getName(), volunteer.getSurname1(), volunteer.getSurname2());
            createdVolunteer = volunteerRepository.findByMail(volunteer.getMail());
        }
        return createdVolunteer;
    }

    @Override
    public DTOUser createRefugee(DTOUser DTOUser) throws Exception {
        Refugee refugee = new Refugee();
        DTOUser createdDTOUser;
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
            refugee = refugeeRepository.findByMail(DTOUser.getMail());
            createdDTOUser = new DTOUser(refugee);
        }
        return createdDTOUser;
    }

    public Object logIn(String mail, String password) {
        Object user = new Object();
        DTOUser refugee = logInRefugee(mail, password);
        if (refugee.getMail() == null) {
            Volunteer volunteer = logInVolunteer(mail, password);
            user = volunteer;
        } else
            user = refugee;
        return user;
    }

    // Mirar visibilidad
    public Volunteer logInVolunteer(String mail, String password) {
        Optional<Volunteer> oVolunteer = volunteerRepository.findOptionalByMailAndPassword(mail,
                password);
        Volunteer volunteer = new Volunteer();
        if (oVolunteer.isPresent()) {
            volunteer = oVolunteer.get();
        }
        return volunteer;
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
    public Volunteer infoVolunteer(String mail) {
        Volunteer volunteer = volunteerRepository.findByMail(mail);
        return volunteer;
    }

    @Override
    public DTOUser infoRefugee(String mail) {
        Refugee refugee = refugeeRepository.findByMail(mail);
        DTOUser DTOUser = new DTOUser(refugee);
        return DTOUser;
    }

    @Override
    public Pair<Integer, Object> exists(String mail, String password) {
        Optional<Refugee> oRefugee = refugeeRepository.findOptionalByMailAndPassword(mail,
                password);
        if (oRefugee.isPresent()) {
            return Pair.of(0, oRefugee.get());
        }
        Optional<Volunteer> oVolunteer = volunteerRepository.findOptionalByMailAndPassword(mail,
                password);
        if (oVolunteer.isPresent()) {
            return Pair.of(1, oVolunteer.get());
        }
        return Pair.of(-1, "Usuario pa' la pinga");
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
    public Set<Lodge> refugeeLodges(String mail) {
        Refugee refugee = refugeeRepository.findByMail(mail);
        Set<Lodge> lodges = refugee.getLodges();
        return lodges;
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
    public Map<Integer, Set<Object>> obtainOwnServices(String mail, Integer userType) {
        Map<Integer, Set<Object>> result = new HashMap<Integer, Set<Object>>();
        if (userType == 0) { // refugee
            result.put(0, lodgeRepository.findByInscriptions_Mail(mail));
            result.put(1, donationRepository.findByInscriptions_Mail(mail));
            result.put(2, educationRepository.findByInscriptions_Mail(mail));
            result.put(3, jobRepository.findByInscriptions_Mail(mail));
        } else { // volunteer
            result.put(0, lodgeRepository.findByVolunteer(mail));
            result.put(1, donationRepository.findByVolunteer(mail));
            result.put(2, educationRepository.findByVolunteer(mail));
            result.put(3, jobRepository.findByVolunteer(mail));
        }
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
				Optional<Refugee> oRefugee = refugeeRepository.findOptionalByMail(chat.getMailUser1());
				if (oRefugee.isPresent()) {
					DTOUser dtoUser = new DTOUser(oRefugee.get());
					dtoChat.setUser1(dtoUser);
				}
				else {
					Optional<Volunteer> oVolunteer = volunteerRepository.findOptionalByMail(chat.getMailUser1());
					DTOUser dtoUser = new DTOUser(oVolunteer.get());
					dtoChat.setUser1(dtoUser);
				}
				
				oRefugee = refugeeRepository.findOptionalByMail(chat.getMailUser2());
				if (oRefugee.isPresent()) {
					DTOUser dtoUser = new DTOUser(oRefugee.get());
					dtoChat.setUser2(dtoUser);
				}
				else {
					Optional<Volunteer> oVolunteer = volunteerRepository.findOptionalByMail(chat.getMailUser2());
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
}