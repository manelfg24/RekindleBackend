
package com.pes.rekindle.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
import com.pes.rekindle.services.ServiceService;
import com.pes.rekindle.services.UserService;

@RestController
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/alojamientos", method = RequestMethod.POST)
    public ResponseEntity<Void> createLodge(@RequestHeader("apiKey") String apiKey,
            @RequestBody DTOLodge lodge) {
        if (userService.authenticate(lodge.getVolunteer(), apiKey)) {
            serviceService.createLodge(lodge);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @RequestMapping(value = "/donaciones", method = RequestMethod.POST)
    public ResponseEntity<Void> createDonation(@RequestHeader("apiKey") String apiKey,
            @RequestBody DTODonation donation) {
        if (userService.authenticate(donation.getVolunteer(), apiKey)) {
            serviceService.createDonation(donation);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @RequestMapping(value = "/cursos", method = RequestMethod.POST)
    public ResponseEntity<Void> createEducation(@RequestHeader("apiKey") String apiKey,
            @RequestBody DTOEducation education) {
        if (userService.authenticate(education.getVolunteer(), apiKey)) {
            serviceService.createEducation(education);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @RequestMapping(value = "/empleos", method = RequestMethod.POST)
    public ResponseEntity<Void> createJob(@RequestHeader("apiKey") String apiKey,
            @RequestBody DTOJob job) {
        if (userService.authenticate(job.getVolunteer(), apiKey)) {
            serviceService.createJob(job);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
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
    public ResponseEntity<List<DTOService>> obtainOwnServices(
            @PathVariable("mail") String mail,
            @PathVariable("tipo") String userType,
            @RequestParam("ended") Boolean ended) {
        List<DTOService> result = userService.obtainOwnServices(mail, userType, ended);

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
    public ResponseEntity<String> deleteService(@RequestHeader("apiKey") String apiKey,
            @PathVariable Long id, @PathVariable String tipo,
            @RequestParam("mailUser") String mail) {
        if (userService.authenticate(mail, apiKey)) {
            serviceService.deleteService(id, tipo);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @RequestMapping(value = "/alojamientos/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Lodge> modifyLodge(@RequestHeader("apiKey") String apiKey,
            @PathVariable("id") long id,
            @RequestBody DTOLodge lodge) {
        if (userService.authenticate(lodge.getVolunteer(), apiKey)) {
            serviceService.modifyLodge(id, lodge);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @RequestMapping(value = "/cursos/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Education> modifyEducation(@RequestHeader("apiKey") String apiKey,
            @PathVariable("id") long id,
            @RequestBody DTOEducation education) {
        if (userService.authenticate(education.getVolunteer(), apiKey)) {
            serviceService.modifyEducation(id, education);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @RequestMapping(value = "/donaciones/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Donation> modifyDonation(@RequestHeader("apiKey") String apiKey,
            @PathVariable("id") long id,
            @RequestBody DTODonation donation) {
        if (userService.authenticate(donation.getVolunteer(), apiKey)) {
            serviceService.modifyDonation(id, donation);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @RequestMapping(value = "/empleos/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Job> modifyJob(@RequestHeader("apiKey") String apiKey,
            @PathVariable("id") long id, @RequestBody DTOJob job) {
        if (userService.authenticate(job.getVolunteer(), apiKey)) {
            serviceService.modifyJob(id, job);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @RequestMapping(value = "/solicituddonacion", method = RequestMethod.POST)
    public ResponseEntity<Void> createDonationRequest(@RequestHeader("apiKey") String apiKey,
            @RequestBody DTODonationEnrollment dtoDonationEnrollment) {
        if (userService.authenticate(dtoDonationEnrollment.getDonation().getVolunteer(), apiKey)) {
            serviceService.createDonationRequest(dtoDonationEnrollment);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @RequestMapping(value = "/solicituddonacion", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<DTODonationEnrollment>> listDonationRequests() {
        return ResponseEntity.status(HttpStatus.OK).body(serviceService.listDonationRequests());
    }

    @RequestMapping(value = "/solicituddonacion/{donationId}", method = RequestMethod.GET)
    public ResponseEntity<Boolean> donationIsRequested(@PathVariable Long donationId,
            @RequestParam String refugeeMail) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(serviceService.donationIsRequested(donationId, refugeeMail));
    }

    @RequestMapping(value = "/solicituddonacion/accept/{idDonation}", method = RequestMethod.GET)
    public ResponseEntity<Void> acceptDonationRequest(@PathVariable Long donationId,
            @RequestParam String refugeeMail) {
        serviceService.acceptDonationRequest(donationId, refugeeMail);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @RequestMapping(value = "/solicituddonacion/reject/{idDonation}", method = RequestMethod.GET)
    public ResponseEntity<Void> rejectDonationRequest(@PathVariable Long donationId,
            @RequestParam String refugeeMail) {
        serviceService.rejectDonationRequest(donationId, refugeeMail);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @RequestMapping(value = "/servicios/{id}/{tipo}/valoraciones", method = RequestMethod.POST)
    public ResponseEntity<Void> valorateService(@RequestHeader("apiKey") String apiKey,
            @RequestBody DTOValoration dtoValoration) {
        if (userService.authenticate(dtoValoration.getRefugeeMail(), apiKey)) {
            serviceService.valorateService(dtoValoration);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

    }
}
