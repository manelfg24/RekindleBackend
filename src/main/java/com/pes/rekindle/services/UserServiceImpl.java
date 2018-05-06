
package com.pes.rekindle.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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
            String bloodType, String eyeColor, String biography) throws Exception {
        Refugee refugee = new Refugee();
        Optional<Refugee> oRefugee = refugeeRepository.findOptionalByMail(mail);
        Optional<Volunteer> oVolunteer = volunteerRepository.findOptionalByMail(mail);
        if (oRefugee.isPresent() || oVolunteer.isPresent()) {
            throw new Exception();
        } else {
            refugeeRepository.create(mail, password, name, surname1, surname2, phoneNumber,
                    birthdate, sex, country,
                    town, ethnic, bloodType, eyeColor, biography);
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
            String bloodType, String eyeColor, String biography) {
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
        refugee.setBiography(biography);
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
	public Set<Refugee> findRefugee(String name, String surname1, String surname2, Date birthdate, String sex,
			String country, String town, String ethnic, String blood, String eye) {
		Set<Refugee> result = new HashSet<Refugee>();
		result = refugeeRepository.findAll();
		if(!name.equals("")) {
			result.retainAll(refugeeRepository.findByName(name));
		}
		if (!surname1.equals("")) {
			result.retainAll(refugeeRepository.findBySurname1(surname1));
		}
		if (!surname2.equals("")) {
			result.retainAll(refugeeRepository.findBySurname2(surname2));
		}
		if(!birthdate.equals(Date.valueOf("1890-01-01"))) {
			result.retainAll(refugeeRepository.findByBirthdate(birthdate));
		}
		if(!sex.equals("-")) 
			result.retainAll(refugeeRepository.findBySex(sex));
		
		if(!country.equals("")) {
			result.retainAll(refugeeRepository.findByCountry(country));
		}	
		if(!town.equals("")) {
			result.retainAll(refugeeRepository.findByTown(town));
		}	
		if(!ethnic.equals("")) {
			result.retainAll(refugeeRepository.findByEthnic(ethnic));
		}	
		if(!blood.equals("-")) {
			result.retainAll(refugeeRepository.findByBloodType(blood));
		}	
		if(!eye.equals("-")) {
			result.retainAll(refugeeRepository.findByEyeColor(eye));
		}
		return result;
	}

    /*
	@Override
	public Set<Refugee> findRefugee(String name, String surname1, String surname2, Date birthdate, String sex,
			String country, String town, String ethnic, String blood, String eye) {
		Set<Refugee> result = new HashSet<Refugee>();
		result = refugeeRepository.findAll();
		if(name != "") {
			if (refugeeRepository.existsByName(name)) {
				result.retainAll(refugeeRepository.findByName(name));
			}
		}
		if (surname1 != "") {
			if (refugeeRepository.existsBySurname1(surname1)) {
				result.retainAll(refugeeRepository.findBySurname1(surname1));
			}
		}
		if (surname2 != "") {
			if (refugeeRepository.existsBySurname2(surname2)) {
				result.retainAll(refugeeRepository.findBySurname2(surname2));
			}
		}
		if(birthdate != Date.valueOf("1890-01-01")) {
			if (refugeeRepository.existsByBirthdate(birthdate)) {
				result.retainAll(refugeeRepository.findByBirthdate(birthdate));
			}
		}
		if(sex != "") {
			if (refugeeRepository.existsBySex(sex)) {
				result.retainAll(refugeeRepository.findBySex(sex));
			}
		}		
		if(country != "") {
			if (refugeeRepository.existsByCountry(country)) {
				result.retainAll(refugeeRepository.findByCountry(country));
			}
		}	
		if(town != "") {
			result.retainAll(refugeeRepository.findByTown(town));
		}	
		if(ethnic != "") {
			if (refugeeRepository.existsByEthnic(ethnic)) {
				result.retainAll(refugeeRepository.findByEthnic(ethnic));
			}
		}	
		if(blood != "-") {
			if (refugeeRepository.existsByBloodType(blood)) {
				result.retainAll(refugeeRepository.findByBloodType(blood));
			}
		}	
		if(eye != "-") {
			if (refugeeRepository.existsByEyeColor(eye)) {
				result.retainAll(refugeeRepository.findByEyeColor(eye));
			}
		}	
		return result;
	}
	*/
    
/*
	@Override
	public Set<Refugee> findRefugee(String name, String surname1, String surname2, Date birthdate, String sex,
			String country, String town, String ethnic, String blood, String eye) {
		Set<Refugee> result = new HashSet<Refugee>();
		result = refugeeRepository.findAll();
		if(name != null) {
			Set<Optional<Refugee>> oSetRefugee = refugeeRepository.findOptionalByName(name);
			if (!oSetRefugee.isEmpty()) {
				Set<Refugee> sRefugee = new HashSet<Refugee>();
				for (Optional<Refugee> oRefugee : oSetRefugee) {
					sRefugee.add(oRefugee.get());
				}
				result.retainAll(sRefugee);
			}
		}
		if (surname1 != null) {
			
		}
		return result;
	}
*/
  
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
		if (userType == 0) { //refugee	
			result.put(0, lodgeRepository.findByInscriptions_Mail(mail));
			result.put(1, donationRepository.findByInscriptions_Mail(mail));
			result.put(2, educationRepository.findByInscriptions_Mail(mail));
			result.put(3, jobRepository.findByInscriptions_Mail(mail));
		}
		else { //volunteer
			result.put(0, lodgeRepository.findByVolunteer(mail));
			result.put(1, donationRepository.findByVolunteer(mail));
			result.put(2, educationRepository.findByVolunteer(mail));
			result.put(3, jobRepository.findByVolunteer(mail));
		}
		return result;
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
