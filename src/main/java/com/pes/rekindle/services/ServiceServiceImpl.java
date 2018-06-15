
package com.pes.rekindle.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pes.rekindle.dto.DTODonation;
import com.pes.rekindle.dto.DTODonationEnrollment;
import com.pes.rekindle.dto.DTOEducation;
import com.pes.rekindle.dto.DTOFilterService;
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
    EntityManagerFactory emf;

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
    public void createLodge(DTOLodge dtoLodge) throws ParseException {
        Lodge lodge = new Lodge();
        lodge.setName(dtoLodge.getName());
        lodge.setVolunteer(dtoLodge.getVolunteer());
        lodge.setServiceType("Lodge");
        lodge.setPhoneNumber(dtoLodge.getPhoneNumber());
        lodge.setAdress(dtoLodge.getAdress());
        lodge.setPlaces(dtoLodge.getPlaces());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date dateLimit = formatter.parse(dtoLodge.getDateLimit());
        lodge.setDateLimit(dateLimit);
        Date expiresOn = formatter.parse(dtoLodge.getExpiresOn());
        lodge.setExpiresOn(expiresOn);
        lodge.setDescription(dtoLodge.getDescription());
        lodgeRepository.save(lodge);
    }

    public void createDonation(DTODonation dtoDonation) throws ParseException {
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
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date expiresOn = formatter.parse(dtoDonation.getExpiresOn());
        donation.setExpiresOn(expiresOn);
        donationRepository.save(donation);
    }

    public void createEducation(DTOEducation dtoEducation) throws ParseException {
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
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date expiresOn = formatter.parse(dtoEducation.getExpiresOn());
        education.setExpiresOn(expiresOn);
        educationRepository.save(education);
    }

    public void createJob(DTOJob dtoJob) throws ParseException {
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
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date expiresOn = formatter.parse(dtoJob.getExpiresOn());
        job.setExpiresOn(expiresOn);
        jobRepository.save(job);
    }

    public List<DTOService> listServices() {
        ArrayList<DTOService> dtosService = new ArrayList<DTOService>();
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
        Set<Lodge> lodges = lodgeRepository.findAll();
        for (Lodge lodge : lodges) {
            DTOService dtoLodge = new DTOService(lodge);
            dtosService.add(dtoLodge);
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
        Lodge lodge = lodgeRepository.findById(id);
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

        // mirar como se guarda la PK en bd para no hacer llamadas inecesarias
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
        } else {
            System.out.println("El refugiado no esta enrolado");
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
        } else {
            System.out.println("El refugiado no esta enrolado");
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
        } else {
            System.out.println("El refugiado no esta enrolado");
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
        } else {
            System.out.println("El refugiado no esta enrolado");
        }
    }
    
 

	@Override
	public List<DTOFilterService> filterServices(String fromDateString, String toDateString, double minimumRating,
			double positionLat, double positionLng, double distance) throws ParseException {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		String query = "SELECT id,  serviceType, name, volunteer, phoneNumber, adress, description, expiresOn, positionLat, positionLng, distance\r\n" + 
				"FROM (\r\n" + 
				"	SELECT id,  serviceType, l.name, volunteer, phoneNumber, adress, description, expiresOn, positionLat, positionLng,\r\n" + 
				"		p.radius,\r\n"+
				"		p.distance_unit\r\n" + 
				"				 * DEGREES(ACOS(COS(RADIANS(p.latpoint))\r\n" + 
				"				 * COS(RADIANS(l.positionLat))\r\n" + 
				"				 * COS(RADIANS(p.longpoint - l.positionLng))\r\n" + 
				"				 + SIN(RADIANS(p.latpoint))\r\n" + 
				"				 * SIN(RADIANS(l.positionLat)))) AS distance\r\n" + 
				"	FROM Lodge AS l\r\n" + 
				"	JOIN (   /* these are the query parameters */\r\n" + 
				"		SELECT  :positionLat  AS latpoint,  :positionLng AS longpoint,\r\n" + 
				"				:distance AS radius,      111.045 AS distance_unit\r\n" + 
				"	) AS p ON 1=1\r\n" + 
				"    JOIN Volunteer v ON l.volunteer = v.mail\r\n" + 
				"	WHERE not l.ended and\r\n" + 
				"		l.positionLat\r\n" + 
				"	 BETWEEN p.latpoint  - (p.radius / p.distance_unit)\r\n" + 
				"		 AND p.latpoint  + (p.radius / p.distance_unit)\r\n" + 
				"	AND l.positionLng\r\n" + 
				"	 BETWEEN p.longpoint - (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint))))\r\n" + 
				"		 AND p.longpoint + (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint))))\r\n" + 
				"	AND l.expiresOn between :fromDate and :toDate\r\n" + 
				"		and ( (v.averageValoration/v.numberOfValorations) >= :minimumRating\r\n" + 
				"			or :minimumRating is null)\r\n" + 
				") AS d\r\n" + 
				"WHERE distance <= radius\r\n";
		
		query += "ORDER BY expiresOn";
		
		Query q = em.createNativeQuery(query);
		
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date fromDate = formatter.parse(fromDateString);
        Date toDate = toDate = formatter.parse(toDateString);
		
		q.setParameter("fromDate", fromDate);
		q.setParameter("toDate", toDate);
		q.setParameter("minimumRating", minimumRating);
		q.setParameter("positionLat", positionLat);
		q.setParameter("positionLng", positionLng);
		q.setParameter("distance", distance);
		
		List<Object[]> daoServices = q.getResultList();
		
		for (Object[] aux : daoServices) {
			DTOFilterService dao = new DTOFilterService(aux);
			System.out.println(dao.getId());
			System.out.println(dao.getName());
			System.out.println(dao.getDistance());
		}
		
		/*
		for (Object ds : daoServices) {
			System.out.println(ds.toString());
			DAOService daoService = mapper.map(ds, DAOService.class);
			System.out.println("ID: " + daoService.getId());
			System.out.println("Distancia: " + daoService.getDistance());
			System.out.println("----------------------------------");
		}*/
		
		/*
		for (Object ds : daoService) {
			ds[0]
			System.out.println("ID: " + ds.getId());
			System.out.println("Distancia: " + ds.getDistance());
			System.out.println("----------------------------------");
		}
		*/
		return null;
	}

}
