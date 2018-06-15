
package com.pes.rekindle.controllers;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pes.rekindle.dto.DTODonation;
import com.pes.rekindle.dto.DTODonationEnrollment;
import com.pes.rekindle.dto.DTOEducation;
import com.pes.rekindle.dto.DTOFilterService;
import com.pes.rekindle.dto.DTOJob;
import com.pes.rekindle.dto.DTOLodge;
import com.pes.rekindle.dto.DTOService;
import com.pes.rekindle.dto.DTOValoration;
import com.pes.rekindle.entities.Donation;
import com.pes.rekindle.entities.Education;
import com.pes.rekindle.entities.Job;
import com.pes.rekindle.entities.Lodge;
import com.pes.rekindle.services.ServiceService;
import com.pes.rekindle.services.UserService;

@RestController
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/alojamientos", method = RequestMethod.POST)
    public ResponseEntity createLodge(@RequestBody DTOLodge lodge) {
        try {
			serviceService.createLodge(lodge);
		} catch (ParseException e) {
			return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(null);
		}
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @RequestMapping(value = "/donaciones", method = RequestMethod.POST)
    public ResponseEntity createDonation(@RequestBody DTODonation donation) {
        try {
			serviceService.createDonation(donation);
		} catch (ParseException e) {
			return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(null);
		}
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @RequestMapping(value = "/cursos", method = RequestMethod.POST)
    public ResponseEntity createEducation(@RequestBody DTOEducation education) {
        try {
			serviceService.createEducation(education);
		} catch (ParseException e) {
			return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(null);
		}
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @RequestMapping(value = "/empleos", method = RequestMethod.POST)
    public ResponseEntity createJob(@RequestBody DTOJob job) {
        try {
			serviceService.createJob(job);
		} catch (ParseException e) {
			return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(null);
		}
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @RequestMapping(value = "/servicios", method = RequestMethod.GET)
    public ResponseEntity<List<DTOService>> listServices() {
        return ResponseEntity.status(HttpStatus.OK).body(serviceService.listServices());
    }

    @RequestMapping(value = "/alojamientos/{id}", method = RequestMethod.GET)
    public ResponseEntity<DTOLodge> infoLodge(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(serviceService.infoLodge(id));
    }

    @RequestMapping(value = "/cursos/{id}", method = RequestMethod.GET)
    public ResponseEntity<DTOEducation> infoEducation(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(serviceService.infoEducation(id));
    }

    @RequestMapping(value = "/donaciones/{id}", method = RequestMethod.GET)
    public ResponseEntity<DTODonation> infoDonation(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(serviceService.infoDonation(id));
    }

    @RequestMapping(value = "/empleos/{id}", method = RequestMethod.GET)
    public ResponseEntity<DTOJob> infoJob(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(serviceService.infoJob(id));
    }

    // -------------------------------------------------------------------------------------------------------------
    // -------------------------------------------- Falta acabarlas
    // ------------------------------------------------
    // -------------------------------------------------------------------------------------------------------------

    @RequestMapping(value = "/servicios/{mail}/{tipo}", method = RequestMethod.GET)
    public ResponseEntity<Set<DTOService>> obtainOwnServices(
            @PathVariable("mail") String mail,
            @PathVariable("tipo") String userType) {
        Set<DTOService> result = userService.obtainOwnServices(mail, userType);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @RequestMapping(value = "/refugiados/{mail}/inscripciones/{id}/{tipo}", method = RequestMethod.GET)
    public ResponseEntity<Boolean> userAlreadyEnrolled(@PathVariable String mail,
            @PathVariable Long id,
            @PathVariable String tipo) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(serviceService.userAlreadyEnrolled(mail, id, tipo));
    }

    @RequestMapping(value = "/servicios/{id}/{tipo}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteService(@PathVariable Long id, @PathVariable String tipo) {
        serviceService.deleteService(id, tipo);

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @RequestMapping(value = "/alojamientos/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Lodge> modifyLodge(@PathVariable("id") long id,
            @RequestBody DTOLodge lodge) {
        serviceService.modifyLodge(id, lodge);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @RequestMapping(value = "/cursos/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Education> modifyEducation(@PathVariable("id") long id,
            @RequestBody DTOEducation education) {
        serviceService.modifyEducation(id, education);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @RequestMapping(value = "/donaciones/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Donation> modifyDonation(@PathVariable("id") long id,
            @RequestBody DTODonation donation) {
        serviceService.modifyDonation(id, donation);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @RequestMapping(value = "/empleos/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Job> modifyJob(@PathVariable("id") long id, @RequestBody DTOJob job) {
        serviceService.modifyJob(id, job);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    
    @RequestMapping(value = "/solicituddonacion", method = RequestMethod.POST)
    public ResponseEntity<Void> createDonationRequest(@RequestBody DTODonationEnrollment dtoDonationEnrollment) {
    	System.out.println("Incide --------------------------------------------------------------");
    	serviceService.createDonationRequest(dtoDonationEnrollment);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @RequestMapping(value = "/solicituddonacion", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<DTODonationEnrollment>> listDonationRequests() {
        return ResponseEntity.status(HttpStatus.OK).body(serviceService.listDonationRequests());
    }
    
    @RequestMapping(value = "/solicituddonacion/{donationId}", method = RequestMethod.GET)
    public ResponseEntity<Boolean> donationIsRequested(@PathVariable Long donationId, @RequestParam String refugeeMail) {
        return ResponseEntity.status(HttpStatus.OK).body(serviceService.donationIsRequested(donationId, refugeeMail));
    }
    
    @RequestMapping(value = "/solicituddonacion/accept/{idDonation}", method = RequestMethod.GET)
    public ResponseEntity<Void> acceptDonationRequest(@PathVariable Long donationId, @RequestParam String refugeeMail) {
    	serviceService.acceptDonationRequest(donationId, refugeeMail);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    
    @RequestMapping(value = "/solicituddonacion/reject/{idDonation}", method = RequestMethod.GET)
    public ResponseEntity<Void> rejectDonationRequest(@PathVariable Long donationId, @RequestParam String refugeeMail) {
    	serviceService.rejectDonationRequest(donationId, refugeeMail);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    
    @RequestMapping(value = "/servicios/{id}/{tipo}/valoraciones", method = RequestMethod.POST)
    public ResponseEntity<Void> valorateService(@RequestBody DTOValoration dtoValoration) {
    	serviceService.valorateService(dtoValoration);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    
    @RequestMapping(value = "/servicios/filtrar", method = RequestMethod.GET)
    public ResponseEntity<List<DTOFilterService>> filterServices(@RequestParam String fromDate, @RequestParam String toDate,
    		@RequestParam double minimumRating, @RequestParam double positionLat,
    		@RequestParam double positionLng, @RequestParam double distance) {
    	System.out.println();
    	System.out.println(fromDate); // enviar 1900-01-01 
    	System.out.println(toDate); // enviar 2100-01-01
    	System.out.println(minimumRating); // enviar 0
    	System.out.println(positionLat); // se envia siempre
    	System.out.println(positionLng); // se envia siempre
    	System.out.println(distance); // se envia 1000000
    	System.out.println();
    	List<DTOFilterService> filteredServices;
		try {
	    	return ResponseEntity.status(HttpStatus.OK).body(serviceService.filterServices(fromDate, toDate, minimumRating, positionLat,
					positionLng, distance));
		} catch (ParseException e) {
			 return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(null);
		}
    }

}
