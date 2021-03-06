
package com.pes.rekindle.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pes.rekindle.dto.DTODonation;
import com.pes.rekindle.dto.DTODonationEnrollment;
import com.pes.rekindle.dto.DTOEducation;
import com.pes.rekindle.dto.DTOJob;
import com.pes.rekindle.dto.DTOLodge;
import com.pes.rekindle.dto.DTOService;
import com.pes.rekindle.dto.DTOValoration;
import com.pes.rekindle.entities.Donation;
import com.pes.rekindle.entities.DonationEnrollment;
import com.pes.rekindle.entities.Education;
import com.pes.rekindle.entities.EducationEnrollment;
import com.pes.rekindle.entities.Job;
import com.pes.rekindle.entities.JobEnrollment;
import com.pes.rekindle.entities.Lodge;
import com.pes.rekindle.entities.LodgeEnrollment;
import com.pes.rekindle.entities.Refugee;
import com.pes.rekindle.repositories.DonationEnrollmentRepository;
import com.pes.rekindle.repositories.DonationRepository;
import com.pes.rekindle.repositories.EducationEnrollmentRepository;
import com.pes.rekindle.repositories.EducationRepository;
import com.pes.rekindle.repositories.JobEnrollmentRepository;
import com.pes.rekindle.repositories.JobRepository;
import com.pes.rekindle.repositories.LodgeEnrollmentRepository;
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

    @Autowired
    DonationEnrollmentRepository donationEnrollmentRepository;
    @Autowired
    JobEnrollmentRepository jobEnrollmentRepository;
    @Autowired
    LodgeEnrollmentRepository lodgeEnrollmentRepository;
    @Autowired
    EducationEnrollmentRepository educationEnrollmentRepository;

    @Autowired
    DozerBeanMapper mapper;

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
        lodge.setEnded(dtoLodge.getEnded());
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
        // DateTimeFormatter formatter = DateTimeFormatter.ISO_TIME;
        try {
            LocalTime startTime = LocalTime.parse(dtoDonation.getStartTime(),
                    DateTimeFormatter.ofPattern("HH:mm:ss"));
            donation.setStartTime(startTime);
        } catch (Exception e) {

        }
        try {
            LocalTime endTime = LocalTime.parse(dtoDonation.getEndTime(),
                    DateTimeFormatter.ofPattern("HH:mm:ss"));
            donation.setEndTime(endTime);
        } catch (Exception e) {

        }
        // donation.setStartTime(dtoDonation.getStartTime());
        // donation.setEndTime(dtoDonation.getEndTime());
        donation.setDescription(dtoDonation.getDescription());
        donation.setEnded(dtoDonation.getEnded());
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
        education.setPrice(dtoEducation.getPrice());
        education.setDescription(dtoEducation.getDescription());
        education.setEnded(dtoEducation.getEnded());
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
        job.setEnded(dtoJob.getEnded());
        jobRepository.save(job);
    }

    public List<DTOService> listServices() {
        ArrayList<DTOService> dtosService = new ArrayList<DTOService>();
        Set<Donation> donations = donationRepository.findAll();
        for (Donation donation : donations) {

            if (!donation.getEnded() && ((donation.getPlaces() != null
                    && donation.getInscriptions().size() < donation.getPlaces())
                    || donation.getPlaces() == null)) {
                DTOService dtoDonation = new DTOService(donation);
                dtosService.add(dtoDonation);
            }
        }
        Set<Education> courses = educationRepository.findAll();
        for (Education education : courses) {
            if (!education.getEnded() && ((education.getPlaces() != null
                    && education.getInscriptions().size() < education.getPlaces())
                    || education.getPlaces() == null)) {
                DTOService dtoEducation = new DTOService(education);
                dtosService.add(dtoEducation);
            }
        }
        Set<Job> jobs = jobRepository.findAll();
        for (Job job : jobs) {
            if (!job.getEnded() && ((job.getPlaces() != null
                    && job.getInscriptions().size() < job.getPlaces())
                    || job.getPlaces() == null)) {
                DTOService dtoJob = new DTOService(job);
                dtosService.add(dtoJob);
            }
        }
        Set<Lodge> lodges = lodgeRepository.findAll();
        for (Lodge lodge : lodges) {
            if (!lodge.getEnded() && ((lodge.getPlaces() != null
                    && lodge.getInscriptions().size() < lodge.getPlaces())
                    || lodge.getPlaces() == null)) {
                DTOService dtoLodge = new DTOService(lodge);
                dtosService.add(dtoLodge);
            }
        }
        /*
         * dtosService.sort( new Comparator<DTOService>() {
         * @Override public int compare(final DTOService object1, final DTOService object2) { return
         * (int) (object1.getId()-object2.getId()); } });
         */
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
                pusher.trigger(serviceType + id, "deleted-service",
                        Collections.singletonMap("message", new DTOService(lodge)));
                for (Refugee refugee : lodge.getInscriptions()) {
                    pusher.trigger(refugee.getMail(), "unenroll-service",
                            Collections.singletonMap("message", id));
                }
                lodgeRepository.deleteById(id);
                break;
            case "Education":
                Education education = educationRepository.findById(id);
                pusher.trigger(serviceType + id, "deleted-service",
                        Collections.singletonMap("message", new DTOService(education)));
                for (Refugee refugee : education.getInscriptions()) {
                    pusher.trigger(refugee.getMail(), "unenroll-service",
                            Collections.singletonMap("message", id));
                }
                educationRepository.deleteById(id);
                break;
            case "Donation":
                Donation donation = donationRepository.findById(id);
                pusher.trigger(serviceType + id, "deleted-service",
                        Collections.singletonMap("message", new DTOService(donation)));
                for (Refugee refugee : donation.getInscriptions()) {
                    pusher.trigger(refugee.getMail(), "unenroll-service",
                            Collections.singletonMap("message", id));
                }
                donationRepository.deleteById(id);
                break;
            case "Job":
                Job job = jobRepository.findById(id);
                pusher.trigger(serviceType + id, "deleted-service",
                        Collections.singletonMap("message", new DTOService(job)));
                for (Refugee refugee : job.getInscriptions()) {
                    pusher.trigger(refugee.getMail(), "unenroll-service",
                            Collections.singletonMap("message", id));
                }
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
            default:
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
        return dtoEducation;
    }

    @Override
    public DTODonation infoDonation(Long id) {
        Donation donation = donationRepository.findById(id);
        DTODonation dtoDonation = new DTODonation(donation);
        return dtoDonation;
    }

    @Override
    public DTOJob infoJob(Long id) {
        Job job = jobRepository.findById(id);
        DTOJob dtoJob = new DTOJob(job);
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
        LocalTime startTime = LocalTime.parse(dtoDonation.getStartTime(),
                DateTimeFormatter.ofPattern("HH:mm:ss"));
        donation.setStartTime(startTime);
        LocalTime endTime = LocalTime.parse(dtoDonation.getEndTime(),
                DateTimeFormatter.ofPattern("HH:mm:ss"));
        donation.setEndTime(endTime);
        donation.setDescription(dtoDonation.getDescription());
        donation.setEnded(dtoDonation.getEnded());
        donationRepository.save(donation);

        Pusher pusher = new Pusher("525518", "743a4fb4a1370f0ca9a4", "c78f3bfa72330a58ee1f");
        pusher.setCluster("eu");
        pusher.setEncrypted(true);
        pusher.trigger(donation.getServiceType() + donation.getId(), "updated-service",
                Collections.singletonMap("message", new DTOService(donation)));
    }

    @Override
    public void modifyLodge(long id, DTOLodge dtoLodge) throws ParseException {
        Lodge lodge = lodgeRepository.findById(id);
        lodge.setName(dtoLodge.getName());
        lodge.setVolunteer(dtoLodge.getVolunteer());
        lodge.setServiceType("Lodge");
        lodge.setPhoneNumber(dtoLodge.getPhoneNumber());
        lodge.setAdress(dtoLodge.getAdress());
        lodge.setPlaces(dtoLodge.getPlaces());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date dateLimit = formatter.parse(dtoLodge.getDateLimit());
        lodge.setDateLimit(dateLimit);
        lodge.setDescription(dtoLodge.getDescription());
        lodge.setEnded(dtoLodge.getEnded());
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
        education.setEnded(dtoEducation.getEnded());
        educationRepository.save(education);

        Pusher pusher = new Pusher("525518", "743a4fb4a1370f0ca9a4", "c78f3bfa72330a58ee1f");
        pusher.setCluster("eu");
        pusher.setEncrypted(true);
        pusher.trigger(education.getServiceType() + education.getId(), "updated-service",
                Collections.singletonMap("message", new DTOService(education)));

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
        job.setEnded(dtoJob.getEnded());
        jobRepository.save(job);
        Pusher pusher = new Pusher("525518", "743a4fb4a1370f0ca9a4", "c78f3bfa72330a58ee1f");
        pusher.setCluster("eu");
        pusher.setEncrypted(true);
        pusher.trigger(job.getServiceType() + job.getId(), "updated-service",
                Collections.singletonMap("message", new DTOService(job)));

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

    @Override
    public void createDonationRequest(DTODonationEnrollment dtoDonationEnrollment) {
        DonationEnrollment donationEnrollment = new DonationEnrollment(dtoDonationEnrollment);
        donationEnrollmentRepository.save(donationEnrollment);
    }

    @Override
    public ArrayList<DTODonationEnrollment> listDonationRequests() {
        ArrayList<DonationEnrollment> enrollmentList = donationEnrollmentRepository
                .findByRequestStatus("Not Resolved");
        ArrayList<DTODonationEnrollment> dtoEnrollmentList = new ArrayList();

        for (DonationEnrollment donationEnrollment : enrollmentList) {
            DTOService dtoDonation = new DTOService(
                    donationRepository.findById(donationEnrollment.getDonationId()));

            dtoEnrollmentList.add(new DTODonationEnrollment(donationEnrollment, dtoDonation));
        }
        return dtoEnrollmentList;
    }

    @Override
    public Boolean donationIsRequested(Long donationId, String refugeeMail) {
        Optional<DonationEnrollment> oDonationEnrollment = donationEnrollmentRepository
                .findOptionalByRefugeeMailAndDonationId(refugeeMail, donationId);
        if (oDonationEnrollment.isPresent()) {
            return true;
        }
        return false;
    }

    @Override
    public void acceptDonationRequest(Long donationId, String refugeeMail) {
        DonationEnrollment donationEnrollment = donationEnrollmentRepository
                .findByRefugeeMailAndDonationId(refugeeMail, donationId);
        if (!donationEnrollment.getRequestStatus().equals("Accepted")) {
            donationEnrollment.setRequestStatus("Accepted");
            donationEnrollmentRepository.save(donationEnrollment);
        }
    }

    @Override
    public void rejectDonationRequest(Long donationId, String refugeeMail) {
        DonationEnrollment donationEnrollment = donationEnrollmentRepository
                .findByRefugeeMailAndDonationId(refugeeMail, donationId);
        if (!donationEnrollment.getRequestStatus().equals("Rejected")) {
            donationEnrollment.setRequestStatus("Rejected");
            donationEnrollmentRepository.save(donationEnrollment);
        }
    }

    @Override
    public void valorateService(DTOValoration dtoValoration) {
        switch (dtoValoration.getServiceType()) {
            case "Lodge":
                valorateLodge(dtoValoration);
                break;
            case "Donation":
                valorateDonation(dtoValoration);
                break;
            case "Job":
                valorateJob(dtoValoration);
                break;
            case "Education":
                valorateEducation(dtoValoration);
                break;
        }
    }

    public void valorateLodge(DTOValoration dtoValoration) {
        Optional<LodgeEnrollment> oLodgeEnrollment = lodgeEnrollmentRepository
                .findOptionalByLodgeIdAndRefugeeMail(dtoValoration.getIdService(),
                        dtoValoration.getRefugeeMail());
        if (oLodgeEnrollment.isPresent()) {
            LodgeEnrollment lodgeEnrollment = oLodgeEnrollment.get();
            Lodge lodge = lodgeRepository.findById(dtoValoration.getIdService());

            userService.valorateVolunteer(lodge.getVolunteer(), dtoValoration.getPoints(),
                    lodgeEnrollment.getValoration());

            lodgeEnrollment.setValoration(dtoValoration.getPoints());
            lodgeEnrollmentRepository.save(lodgeEnrollment);
        }
    }

    public void valorateDonation(DTOValoration dtoValoration) {
        Optional<DonationEnrollment> oDonationEnrollment = donationEnrollmentRepository
                .findOptionalByDonationIdAndRefugeeMail(dtoValoration.getIdService(),
                        dtoValoration.getRefugeeMail());
        if (oDonationEnrollment.isPresent()) {
            DonationEnrollment donationEnrollment = oDonationEnrollment.get();
            Donation donation = donationRepository.findById(dtoValoration.getIdService());

            userService.valorateVolunteer(donation.getVolunteer(), dtoValoration.getPoints(),
                    donationEnrollment.getValoration());

            donationEnrollment.setValoration(dtoValoration.getPoints());
            donationEnrollmentRepository.save(donationEnrollment);
        }
    }

    public void valorateJob(DTOValoration dtoValoration) {
        Optional<JobEnrollment> oJobEnrollment = jobEnrollmentRepository
                .findOptionalByJobIdAndRefugeeMail(dtoValoration.getIdService(),
                        dtoValoration.getRefugeeMail());
        if (oJobEnrollment.isPresent()) {
            JobEnrollment jobEnrollment = oJobEnrollment.get();
            Job job = jobRepository.findById(dtoValoration.getIdService());

            userService.valorateVolunteer(job.getVolunteer(), dtoValoration.getPoints(),
                    jobEnrollment.getValoration());

            jobEnrollment.setValoration(dtoValoration.getPoints());
            jobEnrollmentRepository.save(jobEnrollment);
        }
    }

    public void valorateEducation(DTOValoration dtoValoration) {
        Optional<EducationEnrollment> oEducationEnrollment = educationEnrollmentRepository
                .findOptionalByEducationIdAndRefugeeMail(dtoValoration.getIdService(),
                        dtoValoration.getRefugeeMail());
        if (oEducationEnrollment.isPresent()) {
            EducationEnrollment educationEnrollment = oEducationEnrollment.get();
            Education education = educationRepository.findById(dtoValoration.getIdService());

            userService.valorateVolunteer(education.getVolunteer(), dtoValoration.getPoints(),
                    educationEnrollment.getValoration());

            educationEnrollment.setValoration(dtoValoration.getPoints());
            educationEnrollmentRepository.save(educationEnrollment);
        }
    }

    @Override
    public Set<Lodge> getLodgeInscriptions(String mail) {
        return lodgeRepository.findByInscriptions_Mail(mail);
    }

    @Override
    public Set<Lodge> getLodgeByVolunteer(String mail) {
        return lodgeRepository.findByVolunteer(mail);
    }

    @Override
    public void saveLodge(Lodge lodge) {
        lodgeRepository.save(lodge);
    }

    @Override
    public Set<Education> getEducationInscriptions(String mail) {
        return educationRepository.findByInscriptions_Mail(mail);
    }

    @Override
    public Set<Education> getCourseByVolunteer(String mail) {
        return educationRepository.findByVolunteer(mail);
    }

    @Override
    public void saveCourse(Education education) {
        educationRepository.save(education);
    }

    @Override
    public Set<Donation> getDonationInscriptions(String mail) {
        return donationRepository.findByInscriptions_Mail(mail);
    }

    @Override
    public Set<Donation> getDonationByVolunteer(String mail) {
        return donationRepository.findByVolunteer(mail);
    }

    @Override
    public void saveDonation(Donation donation) {
        donationRepository.save(donation);
    }

    @Override
    public Set<Job> getJobInscriptions(String mail) {
        return jobRepository.findByInscriptions_Mail(mail);
    }

    @Override
    public Set<Job> getJobByVolunteer(String mail) {
        return jobRepository.findByVolunteer(mail);
    }

    @Override
    public void saveJob(Job job) {
        jobRepository.save(job);
    }
}
