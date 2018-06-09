
package com.pes.rekindle.services;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
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
import com.pes.rekindle.entities.Refugee;
import com.pes.rekindle.repositories.DonationRepository;
import com.pes.rekindle.repositories.EducationRepository;
import com.pes.rekindle.repositories.JobRepository;
import com.pes.rekindle.repositories.LodgeRepository;
import com.pusher.rest.Pusher;

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

    @Autowired
    UserService userService;

    @Override
    public void createLodge(DTOLodge dtoLodge) {
        Lodge lodge = new Lodge();
        lodge.setName(dtoLodge.getName());
        lodge.setVolunteer(dtoLodge.getVolunteer());
        lodge.setServiceType("Lodge");
        lodge.setPhoneNumber(dtoLodge.getPhoneNumber());
        lodge.setAdress(dtoLodge.getAdress());
        lodge.setPlaces(dtoLodge.getPlaces());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dateLimit = formatter.parse(dtoLodge.getDateLimit());
            lodge.setDateLimit(dateLimit);
        } catch (Exception e) {

        }
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

    @Override
    public void deleteService(long id, String serviceType) {
        Pusher pusher = new Pusher("525518", "743a4fb4a1370f0ca9a4", "c78f3bfa72330a58ee1f");
        pusher.setCluster("eu");
        pusher.setEncrypted(true);

        switch (serviceType) {
            case "Lodge":
                Lodge lodge = lodgeRepository.findById(id);
                lodgeRepository.deleteById(id);
                pusher.trigger(serviceType + id, "deleted-service",
                        Collections.singletonMap("message", new DTOService(lodge)));
                for (Refugee refugee : lodge.getInscriptions()) {
                    pusher.trigger(refugee.getMail(), "unenroll-service",
                            Collections.singletonMap("message", id));
                }
                break;
            case "Education":
                educationRepository.deleteById(id);
                break;
            case "Donation":
                donationRepository.deleteById(id);
                break;
            case "Job":
                jobRepository.deleteById(id);
                break;
        }
    }

    @Override
    public Boolean userAlreadyEnrolled(String mail, Long id, String serviceType) {
        switch (serviceType) {
            case "Lodge":
                return userService.userAlreadyEnrolledLodge(mail, id);
            case "Education":
                return userService.userAlreadyEnrolledEducation(mail, id);
            case "Donation":
                return userService.userAlreadyEnrolledDonation(mail, id);
            case "Job":
                return userService.userAlreadyEnrolledJob(mail, id);
            default: // no deberia pasar nunca
                return null;
        }
    }

    @Override
    public DTOLodge infoLodge(Long id) {
        Lodge lodge = lodgeRepository.findById(id);
        DTOLodge dtoLodge = new DTOLodge(lodge);
        /*
         * dtoLodge.setName(lodge.getName()); dtoLodge.setVolunteer(lodge.getVolunteer());
         * dtoLodge.setServiceType("Lodge"); dtoLodge.setPhoneNumber(lodge.getPhoneNumber());
         * dtoLodge.setAdress(lodge.getAdress()); dtoLodge.setPlaces(lodge.getPlaces());
         * dtoLodge.setDateLimit(lodge.getDateLimit());
         * dtoLodge.setDescription(lodge.getDescription());
         */
        return dtoLodge;
    }

    @Override
    public DTOEducation infoEducation(Long id) {
        Education education = educationRepository.findById(id);
        DTOEducation dtoEducation = new DTOEducation(education);
        /*
         * dtoEducation.setName(education.getName());
         * dtoEducation.setVolunteer(education.getVolunteer());
         * dtoEducation.setServiceType("Education");
         * dtoEducation.setPhoneNumber(education.getPhoneNumber());
         * dtoEducation.setAdress(education.getAdress());
         * dtoEducation.setAmbit(education.getAmbit());
         * dtoEducation.setRequirements(education.getRequirements());
         * dtoEducation.setSchedule(education.getSchedule());
         * dtoEducation.setPlaces(education.getPlaces());
         * dtoEducation.setDescription(education.getDescription());
         */
        return dtoEducation;
    }

    @Override
    public DTODonation infoDonation(Long id) {
        Donation donation = donationRepository.findById(id);
        DTODonation dtoDonation = new DTODonation(donation);
        /*
         * dtoDonation.setName(donation.getName());
         * dtoDonation.setVolunteer(donation.getVolunteer());
         * dtoDonation.setServiceType("Donation");
         * dtoDonation.setPhoneNumber(donation.getPhoneNumber());
         * dtoDonation.setAdress(donation.getAdress()); dtoDonation.setPlaces(donation.getPlaces());
         * dtoDonation.setStartTime(donation.getStartTime());
         * dtoDonation.setEndTime(donation.getEndTime());
         * dtoDonation.setDescription(donation.getDescription());
         */
        return dtoDonation;
    }

    @Override
    public DTOJob infoJob(Long id) {
        Job job = jobRepository.findById(id);
        DTOJob dtoJob = new DTOJob(job);
        /*
         * dtoJob.setName(job.getName()); dtoJob.setVolunteer(job.getVolunteer());
         * dtoJob.setServiceType("Job"); dtoJob.setPhoneNumber(job.getPhoneNumber());
         * dtoJob.setAdress(job.getAdress()); dtoJob.setCharge(job.getCharge());
         * dtoJob.setRequirements(job.getRequirements()); dtoJob.setHoursDay(job.getHoursDay());
         * dtoJob.setHoursWeek(job.getHoursWeek());
         * dtoJob.setContractDuration(job.getContractDuration()); dtoJob.setPlaces(job.getPlaces());
         * dtoJob.setSalary(job.getSalary()); dtoJob.setDescription(job.getDescription());
         */
        return dtoJob;
    }

    @Override
    public void modifyDonation(long id, DTODonation dtoDonation) {
        Donation donation = new Donation();
        donation.setId(id);
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
        lodge.setId(id);
        lodge.setName(dtoLodge.getName());
        lodge.setVolunteer(dtoLodge.getVolunteer());
        lodge.setServiceType("Lodge");
        lodge.setPhoneNumber(dtoLodge.getPhoneNumber());
        lodge.setAdress(dtoLodge.getAdress());
        lodge.setPlaces(dtoLodge.getPlaces());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dateLimit = formatter.parse(dtoLodge.getDateLimit());
            lodge.setDateLimit(dateLimit);
        } catch (Exception e) {

        }
        lodge.setDescription(dtoLodge.getDescription());
        lodgeRepository.save(lodge);

        Pusher pusher = new Pusher("525518", "743a4fb4a1370f0ca9a4", "c78f3bfa72330a58ee1f");
        pusher.setCluster("eu");
        pusher.setEncrypted(true);
        pusher.trigger(lodge.getServiceType() + lodge.getId(), "updated-service",
                Collections.singletonMap("message", new DTOService(lodge)));

    }

    @Override
    public void modifyEducation(long id, DTOEducation dtoEducation) {
        Education education = new Education();
        education.setId(id);
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
        job.setId(id);
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

    @Override
    public Lodge getLodge(Long id) {
        return lodgeRepository.findById(id);
    }

    @Override
    public Donation getDonation(Long id) {
        return donationRepository.findById(id);
    }

    @Override
    public Education getEducation(Long id) {
        return educationRepository.findById(id);
    }

    @Override
    public Job getJob(Long id) {
        return jobRepository.findById(id);
    }
}
