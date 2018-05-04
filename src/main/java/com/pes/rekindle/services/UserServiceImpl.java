
package com.pes.rekindle.services;

import java.sql.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import com.pes.rekindle.entities.Donation;
import com.pes.rekindle.entities.Education;
import com.pes.rekindle.entities.Job;
import com.pes.rekindle.entities.Lodge;
import com.pes.rekindle.entities.Refugee;
import com.pes.rekindle.entities.Volunteer;
import com.pes.rekindle.repositories.DonationRepository;
import com.pes.rekindle.repositories.EducationRepository;
import com.pes.rekindle.repositories.JobRepository;
import com.pes.rekindle.repositories.LodgeRepository;
import com.pes.rekindle.repositories.RefugeeRepository;
import com.pes.rekindle.repositories.UserRepository;
import com.pes.rekindle.repositories.VolunteerRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
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

    public Volunteer createVolunteer(String mail, String password, String name, String surname1,
            String surname2) throws Exception {
        Volunteer volunteer = new Volunteer();
        Optional<Volunteer> oVolunteer = volunteerRepository.findOptionalByMail(mail);
        Optional<Refugee> oRefugee = refugeeRepository.findOptionalByMail(mail);
        if (oVolunteer.isPresent() || oRefugee.isPresent()) {
            throw new Exception();
        } else {
            volunteerRepository.create(mail, password, name, surname1, surname2);
            volunteer = volunteerRepository.findByMail(mail);
        }
        return volunteer;
    }

    @Override
    public Refugee createRefugee(String mail, String password, String name, String surname1,
            String surname2,
            Integer phoneNumber, Date birthdate, String sex, String country, String town,
            String ethnic,
            String bloodType, String eyeColor) throws Exception {
        Refugee refugee = new Refugee();
        Optional<Refugee> oRefugee = refugeeRepository.findOptionalByMail(mail);
        Optional<Volunteer> oVolunteer = volunteerRepository.findOptionalByMail(mail);
        if (oRefugee.isPresent() || oVolunteer.isPresent()) {
            throw new Exception();
        } else {
            refugeeRepository.create(mail, password, name, surname1, surname2, phoneNumber,
                    birthdate, sex, country,
                    town, ethnic, bloodType, eyeColor);
            refugee = refugeeRepository.findByMail(mail);
        }
        return refugee;
    }

    public Object logIn(String mail, String password) {
        Object user = new Object();
        Refugee refugee = logInRefugee(mail, password);
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
        } else
            System.out.println(volunteer.getMail() == null);
        return volunteer;
    }

    public Refugee logInRefugee(String mail, String password) {
        Optional<Refugee> oRefugee = refugeeRepository.findOptionalByMailAndPassword(mail,
                password);
        Refugee refugee = new Refugee();
        if (oRefugee.isPresent()) {
            refugee = oRefugee.get();
        } else
            System.out.println(refugee.getMail() == null);
        return refugee;
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

    public void modifyProfileRefugee(String mail, String name, String surname1, String surname2,
            Integer phoneNumber,
            Date birthdate, String sex, String country, String town, String ethnic,
            String bloodType, String eyeColor) {
        Refugee refugee = refugeeRepository.findByMail(mail);
        refugee.setName(name);
        refugee.setSurname1(surname1);
        refugee.setSurname2(surname2);
        refugee.setPhoneNumber(phoneNumber);
        refugee.setBirthdate(birthdate);
        refugee.setSex(sex);
        refugee.setCountry(country);
        refugee.setTown(town);
        refugee.setEthnic(ethnic);
        refugee.setBloodType(bloodType);
        refugee.setEyeColor(eyeColor);
        refugeeRepository.flush();

    }

    @Override
    public Volunteer infoVolunteer(String mail) {
        Volunteer volunteer = volunteerRepository.findByMail(mail);
        return volunteer;
    }

    @Override
    public Refugee infoRefugee(String mail) {
        Refugee refugee = refugeeRepository.findByMail(mail);
        return refugee;
    }

    @Override
    public Pair<Integer, Object> exists(String mail, String password) {
        Optional<Refugee> oRefugee = refugeeRepository.findOptionalByMailAndPassword(mail,
                password);
        if (oRefugee.isPresent()) {
        	// Super sucio, se tiene que cambiar
        	// Alex muerdealmohadas
        	Refugee r = oRefugee.get();
        	r.setCourses(new HashSet<Education>());
        	r.setJobs(new HashSet<Job>());
        	r.setLodges(new HashSet<Lodge>());
        	r.setDonations(new HashSet<Donation>());
        	return Pair.of(0, r);
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
		
		refugees.add(r);
		courses.add(e);
		
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
}

/*
 * public String createVolunteer(String mail, String password, String name, String surname1, String
 * surname2){ System.out.println("Mail "+mail+"   Password "+password); Optional<Volunteer>
 * oVolunteer = volunteerRepository.findOptionalByMail(mail); String creationResult =
 * "El voluntario ya existe"; if(!oVolunteer.isPresent()) { Volunteer v = new Volunteer();
 * v.setMail(mail); v.setPassword(password); v.setName(name); v.setSurname1(surname1);
 * v.setSurname2(surname2); creationResult = volunteerRepository.save(v); } return creationResult; }
 */

/*
 * public boolean logIn(String mail, String password) { Optional<User> oUser =
 * userRepository.findOptionalByMail(mail); boolean correctPassword = false; if(oUser.isPresent()) {
 * User user = oUser.get(); correctPassword = (user.getPassword() == password); } return
 * correctPassword; } spring.datasource.url=jdbc:mysql://10.4.41.149:3306/pes
 * spring.datasource.username=aplicacion
 */
