
package com.pes.rekindle.services;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pes.rekindle.entities.Donation;
import com.pes.rekindle.entities.Education;
import com.pes.rekindle.entities.Job;
import com.pes.rekindle.entities.Lodge;
import com.pes.rekindle.repositories.DonationRepository;
import com.pes.rekindle.repositories.EducationRepository;
import com.pes.rekindle.repositories.JobRepository;
import com.pes.rekindle.repositories.LodgeRepository;

@Service
public class ServiceServiceImpl implements ServiceService {

    @Autowired
    LodgeRepository lodgeRepository;
    @Autowired
    EducationRepository educationRepository;
    @Autowired
    DonationRepository donationRepository;
    @Autowired
    JobRepository jobRepository;

    @Override
    public void createLodge(String name, String mail, Integer phoneNumber, String adress,
            Integer places,
            java.util.Date date, String description) {
        Lodge lodge = new Lodge();
        lodge.setName(name);
        lodge.setVolunteer(mail);
        lodge.setServiceType('l');
        lodge.setPhoneNumber(phoneNumber);
        lodge.setAdress(adress);
        lodge.setPlaces(places);
        lodge.setDateLimit(date);
        lodge.setDescription(description);
        lodgeRepository.save(lodge);
    }
    /*
     * public Boolean createLodge(String name, String mail, Integer phoneNumber, String adress,
     * Integer places, Date dateLimit, String description) { Lodge lodge = new Lodge();
     * lodge.setName(name); lodge.setVolunteer(mail); lodge.setServiceType('l');
     * lodge.setPhoneNumber(phoneNumber); lodge.setAdress(adress); lodge.setPlaces(places);
     * lodge.setDateLimit(dateLimit); lodge.setDescription(description);
     * lodgeRepository.save(lodge); return true; }
     */

    public void createDonation(String name, String mail, Integer phoneNumber, String adress,
            Integer places, Time startTime,
            Time endTime, String description) {
        Donation donation = new Donation();
        donation.setName(name);
        donation.setVolunteer(mail);
        donation.setServiceType('d');
        donation.setPhoneNumber(phoneNumber);
        donation.setAdress(adress);
        donation.setPlaces(places);
        donation.setStartTime(startTime);
        donation.setEndTime(endTime);
        donation.setDescription(description);
        donationRepository.save(donation);
    }

    public void createEducation(String name, String mail, Integer phoneNumber, String adress,
            String ambit,
            String requirements, String schedule, Integer places, Integer price,
            String description) {
        Education education = new Education();
        education.setName(name);
        education.setVolunteer(mail);
        education.setServiceType('e');
        education.setPhoneNumber(phoneNumber);
        education.setAdress(adress);
        education.setAmbit(ambit);
        education.setRequirements(requirements);
        education.setSchedule(schedule);
        education.setPlaces(places);
        education.setDescription(description);
        educationRepository.save(education);
    }

    public void createJob(String name, String mail, Integer phoneNumber, String adress,
            String charge,
            String requirements, Double hoursDay, Double hoursWeek, Integer duration,
            Integer places, Double salary,
            String description) {
        Job job = new Job();
        job.setName(name);
        job.setVolunteer(mail);
        job.setServiceType('j');
        job.setPhoneNumber(phoneNumber);
        job.setAdress(adress);
        job.setCharge(charge);
        job.setRequirements(requirements);
        job.setHoursDay(hoursDay);
        job.setHoursWeek(hoursWeek);
        job.setContractDuration(duration);
        job.setPlaces(places);
        job.setSalary(salary);
        job.setDescription(description);
        jobRepository.save(job);
    }

    public Map<Integer, Set<Object>> listServices() {
    	Map<Integer, Set<Object>> listServices = new HashMap<Integer, Set<Object>>();
        listServices.put(0, lodgeRepository.findAll());
        listServices.put(1, donationRepository.findAll());
        listServices.put(2, educationRepository.findAll());
        listServices.put(3, jobRepository.findAll());
        return listServices;
    }

    public Object infoService(Long id, char serviceType) {
        Object service = new Object();
        if (serviceType == 'l')
            service = lodgeRepository.findById(id);
        else if (serviceType == 'e')
            service = educationRepository.findById(id);
        else if (serviceType == 'j')
            service = jobRepository.findById(id);
        else if (serviceType == 'd')
            service = donationRepository.findById(id);
        else
            service = "Servicio no encontrado";
        return service;
    }

	@Override
	public Boolean deleteService(long id, char serviceType) {
		return null;
	}

	@Override
	public void deleteLodge(Long id) {
		lodgeRepository.deleteById(id);
	}

	@Override
	public Lodge infoLodge(Long id) {
		return lodgeRepository.findById(id);
	}

	@Override
	public Education infoEducation(Long id) {
		return educationRepository.findById(id);
	}

	@Override
	public Donation infoDonation(Long id) {
		return donationRepository.findById(id);
	}

	@Override
	public Job infoJob(Long id) {
		return jobRepository.findById(id);
	}

	@Override
	public void modifyDonation(long id, String name, String volunteer, Integer phoneNumber, String adress, Integer places,
			Time startTime, Time endTime, String description) {
		Donation donation = new Donation();
		donation.setName(name);
        donation.setVolunteer(volunteer);
        donation.setServiceType('d');
        donation.setPhoneNumber(phoneNumber);
        donation.setAdress(adress);
        donation.setPlaces(places);
        donation.setStartTime(startTime);
        donation.setEndTime(endTime);
        donation.setDescription(description);
        donationRepository.save(donation);
		
	}

	@Override
	public void modifyLodge(long id, String name, String volunteer, Integer phoneNumber, String adress, Integer places,
			Date dateLimit, String description) {
		Lodge lodge = new Lodge();
		lodge.setName(name);
        lodge.setVolunteer(volunteer);
        lodge.setPhoneNumber(phoneNumber);
        lodge.setAdress(adress);
        lodge.setPlaces(places);
        lodge.setDateLimit(dateLimit);
        lodge.setDescription(description);
        lodgeRepository.save(lodge);
		
	}

	@Override
	public void modifyEducation(long id, String name, String volunteer, Integer phoneNumber, String adress,
			String ambit, String requirements, String schedule, Integer places, Integer price, String description) {
		Education education = new Education();
		education.setName(name);
        education.setVolunteer(volunteer);
        education.setServiceType('e');
        education.setPhoneNumber(phoneNumber);
        education.setAdress(adress);
        education.setAmbit(ambit);
        education.setRequirements(requirements);
        education.setSchedule(schedule);
        education.setPlaces(places);
        education.setDescription(description);
        educationRepository.save(education);
		
	}

	@Override
	public void modifyJob(long id, String name, String volunteer, Integer phoneNumber, String adress, String charge,
			String requirements, double hoursDay, double hoursWeek, Integer contractDuration, Integer places,
			double salary, String description) {
		Job job = new Job();
		job.setName(name);
        job.setVolunteer(volunteer);
        job.setServiceType('j');
        job.setPhoneNumber(phoneNumber);
        job.setAdress(adress);
        job.setCharge(charge);
        job.setRequirements(requirements);
        job.setHoursDay(hoursDay);
        job.setHoursWeek(hoursWeek);
        job.setContractDuration(contractDuration);
        job.setPlaces(places);
        job.setSalary(salary);
        job.setDescription(description);
        jobRepository.save(job);
		
	}

}
