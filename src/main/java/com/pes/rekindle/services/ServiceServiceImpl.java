
package com.pes.rekindle.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pes.rekindle.dto.DTODonation;
import com.pes.rekindle.dto.DTOEducation;
import com.pes.rekindle.dto.DTOJob;
import com.pes.rekindle.dto.DTOLodge;
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
    public void createLodge(DTOLodge dtoLodge) {
        Lodge lodge = new Lodge();
        lodge.setName(dtoLodge.getName());
        lodge.setVolunteer(dtoLodge.getVolunteer());
        lodge.setServiceType('l');
        lodge.setPhoneNumber(dtoLodge.getPhoneNumber());
        lodge.setAdress(dtoLodge.getAdress());
        lodge.setPlaces(dtoLodge.getPlaces());
        lodge.setDateLimit(dtoLodge.getDateLimit());
        lodge.setDescription(dtoLodge.getDescription());
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

    public void createDonation(DTODonation dtoDonation) {
        Donation donation = new Donation();
        donation.setName(dtoDonation.getName());
        donation.setVolunteer(dtoDonation.getVolunteer());
        donation.setServiceType('d');
        donation.setPhoneNumber(dtoDonation.getPhoneNumber());
        donation.setAdress(dtoDonation.getAdress());
        donation.setPlaces(dtoDonation.getPlaces());
        donation.setStartTime(dtoDonation.getStartTime());
        donation.setEndTime(dtoDonation.getEndTime());
        donation.setDescription(dtoDonation.getDescription());
        donationRepository.save(donation);
    }

    public void createEducation(DTOEducation dtoEducation) {
        Education education = new Education();
        education.setName(dtoEducation.getName());
        education.setVolunteer(dtoEducation.getVolunteer());
        education.setServiceType('e');
        education.setPhoneNumber(dtoEducation.getPhoneNumber());
        education.setAdress(dtoEducation.getAdress());
        education.setAmbit(dtoEducation.getAmbit());
        education.setRequirements(dtoEducation.getRequirements());
        education.setSchedule(dtoEducation.getSchedule());
        education.setPlaces(dtoEducation.getPlaces());
        education.setDescription(dtoEducation.getDescription());
        educationRepository.save(education);
    }

    public void createJob(DTOJob dtoJob) {
        Job job = new Job();
        job.setName(dtoJob.getName());
        job.setVolunteer(dtoJob.getVolunteer());
        job.setServiceType('j');
        job.setPhoneNumber(dtoJob.getPhoneNumber());
        job.setAdress(dtoJob.getAdress());
        job.setCharge(dtoJob.getCharge());
        job.setRequirements(dtoJob.getRequirements());
        job.setHoursDay(dtoJob.getHoursDay());
        job.setHoursWeek(dtoJob.getHoursWeek());
        job.setContractDuration(dtoJob.getContractDuration());
        job.setPlaces(dtoJob.getPlaces());
        job.setSalary(dtoJob.getSalary());
        job.setDescription(dtoJob.getDescription());
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

    // tienen que devolver DTO
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
    public void modifyDonation(long id, DTODonation dtoDonation) {
        Donation donation = new Donation();
        donation.setName(dtoDonation.getName());
        donation.setVolunteer(dtoDonation.getVolunteer());
        donation.setServiceType('d');
        donation.setPhoneNumber(dtoDonation.getPhoneNumber());
        donation.setAdress(dtoDonation.getAdress());
        donation.setPlaces(dtoDonation.getPlaces());
        donation.setStartTime(dtoDonation.getStartTime());
        donation.setEndTime(dtoDonation.getEndTime());
        donation.setDescription(dtoDonation.getDescription());
        donationRepository.save(donation);

    }

    @Override
    public void modifyLodge(long id, DTOLodge dtoLodge) {
        Lodge lodge = new Lodge();
        lodge.setName(dtoLodge.getName());
        lodge.setVolunteer(dtoLodge.getVolunteer());
        lodge.setPhoneNumber(dtoLodge.getPhoneNumber());
        lodge.setAdress(dtoLodge.getAdress());
        lodge.setPlaces(dtoLodge.getPlaces());
        lodge.setDateLimit(dtoLodge.getDateLimit());
        lodge.setDescription(dtoLodge.getDescription());
        lodgeRepository.save(lodge);

    }

    @Override
    public void modifyEducation(long id, DTOEducation dtoEducation) {
        Education education = new Education();
        education.setName(dtoEducation.getName());
        education.setVolunteer(dtoEducation.getVolunteer());
        education.setServiceType('e');
        education.setPhoneNumber(dtoEducation.getPhoneNumber());
        education.setAdress(dtoEducation.getAdress());
        education.setAmbit(dtoEducation.getAmbit());
        education.setRequirements(dtoEducation.getRequirements());
        education.setSchedule(dtoEducation.getSchedule());
        education.setPlaces(dtoEducation.getPlaces());
        education.setDescription(dtoEducation.getDescription());
        educationRepository.save(education);

    }

    @Override
    public void modifyJob(long id, DTOJob dtoJob) {
        Job job = new Job();
        job.setName(dtoJob.getName());
        job.setVolunteer(dtoJob.getVolunteer());
        job.setServiceType('j');
        job.setPhoneNumber(dtoJob.getPhoneNumber());
        job.setAdress(dtoJob.getAdress());
        job.setCharge(dtoJob.getCharge());
        job.setRequirements(dtoJob.getRequirements());
        job.setHoursDay(dtoJob.getHoursDay());
        job.setHoursWeek(dtoJob.getHoursWeek());
        job.setContractDuration(dtoJob.getContractDuration());
        job.setPlaces(dtoJob.getPlaces());
        job.setSalary(dtoJob.getSalary());
        job.setDescription(dtoJob.getDescription());
        jobRepository.save(job);

    }

}
