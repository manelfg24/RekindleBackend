
package com.pes.rekindle.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.pes.rekindle.dto.DTODonation;
import com.pes.rekindle.dto.DTODonationEnrollment;
import com.pes.rekindle.dto.DTOEducation;
import com.pes.rekindle.dto.DTOJob;
import com.pes.rekindle.dto.DTOLodge;
import com.pes.rekindle.dto.DTOService;
import com.pes.rekindle.dto.DTOValoration;
import com.pes.rekindle.entities.Donation;
import com.pes.rekindle.entities.Education;
import com.pes.rekindle.entities.Job;
import com.pes.rekindle.entities.Lodge;

public interface ServiceService {

    void createLodge(DTOLodge lodge);

    List<DTOService> listServices();

    void createDonation(DTODonation donation);

    void createEducation(DTOEducation education);

    void createJob(DTOJob job);

    void deleteService(long id, String serviceType);
    
    DTOLodge infoLodge(Long id);

    DTOEducation infoEducation(Long id);

    DTODonation infoDonation(Long id);

    DTOJob infoJob(Long id);

    void modifyDonation(long id, DTODonation donation);

    void modifyLodge(long id, DTOLodge lodge);

    void modifyEducation(long id, DTOEducation education);

    void modifyJob(long id, DTOJob job);

	Boolean userAlreadyEnrolled(String mail, Long id, String serviceType);

	Lodge getLodge(Long id);
	
	Donation getDonation(Long id);
	
	Education getEducation(Long id);
	
	Job getJob(Long id);

	void createDonationRequest(DTODonationEnrollment dtoDonationEnrollment);

	ArrayList<DTODonationEnrollment> listDonationRequests();

	Boolean donationIsRequested(Long donationId, String refugeeMail);

	void acceptDonationRequest(Long donationId, String refugeeMail);

	void rejectDonationRequest(Long donationId, String refugeeMail);

	void valorateService(DTOValoration dtoValoration);

	ArrayList<DTOService> filterServices(String fromDate, String toDate, double minimumRating,
			double positionLat, double positionLng, double distance);

}
