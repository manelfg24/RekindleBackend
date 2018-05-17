
package com.pes.rekindle.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pes.rekindle.dto.DTODonation;
import com.pes.rekindle.dto.DTOEducation;
import com.pes.rekindle.dto.DTOJob;
import com.pes.rekindle.dto.DTOLodge;
import com.pes.rekindle.dto.DTOService;
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
        lodge.setServiceType("Lodge");
        lodge.setPhoneNumber(dtoLodge.getPhoneNumber());
        lodge.setAdress(dtoLodge.getAdress());
        lodge.setPlaces(dtoLodge.getPlaces());
        lodge.setDateLimit(dtoLodge.getDateLimit());
        lodge.setDescription(dtoLodge.getDescription());
        lodgeRepository.save(lodge);
    }

    public void createDonation(DTODonation dtoDonation) {
        Donation donation = new Donation();
        donation.setName(dtoDonation.getName());
        donation.setVolunteer(dtoDonation.getVolunteer());
        donation.setServiceType("Donation");
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
        education.setServiceType("Education");
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
        job.setServiceType("Job");
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

    public Set<DTOService> listServices() {
        Set<DTOService> dtosService = new HashSet<DTOService>();
        Set<Lodge> lodges = lodgeRepository.findAll();
        for (Lodge lodge : lodges) {
        	DTOService dtoLodge = new DTOService(lodge);
            dtosService.add(dtoLodge);
        }
        Set<Donation> donations = donationRepository.findAll();
        for (Donation donation : donations) {
        	DTOService dtoDonation = new DTOService(donation);
            dtosService.add(dtoDonation);
        }
        Set<Education> courses = educationRepository.findAll();
        for (Education education : courses) {
        	DTOService dtoEducation = new DTOService(education);
            dtosService.add(dtoEducation);
        }
        Set<Job> jobs = jobRepository.findAll();
        for (Job job : jobs) {
        	DTOService dtoJob = new DTOService(job);
            dtosService.add(dtoJob);
        }

        return dtosService;
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
    public DTOLodge infoLodge(Long id) {
        Lodge lodge = lodgeRepository.findById(id);
        DTOLodge dtoLodge = new DTOLodge();
        dtoLodge.setName(lodge.getName());
        dtoLodge.setVolunteer(lodge.getVolunteer());
        dtoLodge.setServiceType("Lodge");
        dtoLodge.setPhoneNumber(lodge.getPhoneNumber());
        dtoLodge.setAdress(lodge.getAdress());
        dtoLodge.setPlaces(lodge.getPlaces());
        dtoLodge.setDateLimit(lodge.getDateLimit());
        dtoLodge.setDescription(lodge.getDescription());
        return dtoLodge;
    }

    @Override
    public DTOEducation infoEducation(Long id) {
        Education education = educationRepository.findById(id);
        DTOEducation dtoEducation = new DTOEducation();
        dtoEducation.setName(education.getName());
        dtoEducation.setVolunteer(education.getVolunteer());
        dtoEducation.setServiceType("Education");
        dtoEducation.setPhoneNumber(education.getPhoneNumber());
        dtoEducation.setAdress(education.getAdress());
        dtoEducation.setAmbit(education.getAmbit());
        dtoEducation.setRequirements(education.getRequirements());
        dtoEducation.setSchedule(education.getSchedule());
        dtoEducation.setPlaces(education.getPlaces());
        dtoEducation.setDescription(education.getDescription());
        return dtoEducation;
    }

    @Override
    public DTODonation infoDonation(Long id) {
        Donation donation = donationRepository.findById(id);
        DTODonation dtoDonation = new DTODonation();
        dtoDonation.setName(donation.getName());
        dtoDonation.setVolunteer(donation.getVolunteer());
        dtoDonation.setServiceType("Donation");
        dtoDonation.setPhoneNumber(donation.getPhoneNumber());
        dtoDonation.setAdress(donation.getAdress());
        dtoDonation.setPlaces(donation.getPlaces());
        dtoDonation.setStartTime(donation.getStartTime());
        dtoDonation.setEndTime(donation.getEndTime());
        dtoDonation.setDescription(donation.getDescription());
        return dtoDonation;
    }

    @Override
    public DTOJob infoJob(Long id) {
        Job job = jobRepository.findById(id);
        DTOJob dtoJob = new DTOJob();
        dtoJob.setName(job.getName());
        dtoJob.setVolunteer(job.getVolunteer());
        dtoJob.setServiceType("Job");
        dtoJob.setPhoneNumber(job.getPhoneNumber());
        dtoJob.setAdress(job.getAdress());
        dtoJob.setCharge(job.getCharge());
        dtoJob.setRequirements(job.getRequirements());
        dtoJob.setHoursDay(job.getHoursDay());
        dtoJob.setHoursWeek(job.getHoursWeek());
        dtoJob.setContractDuration(job.getContractDuration());
        dtoJob.setPlaces(job.getPlaces());
        dtoJob.setSalary(job.getSalary());
        dtoJob.setDescription(job.getDescription());
        return dtoJob;
    }

    @Override
    public void modifyDonation(long id, DTODonation dtoDonation) {
        Donation donation = new Donation();
        donation.setName(dtoDonation.getName());
        donation.setVolunteer(dtoDonation.getVolunteer());
        donation.setServiceType("Donation");
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
        lodge.setServiceType("Lodge");
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
        education.setServiceType("Education");
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
        job.setServiceType("Job");
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
