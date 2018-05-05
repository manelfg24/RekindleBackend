
package com.pes.rekindle.controllers;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pes.rekindle.entities.Donation;
import com.pes.rekindle.entities.Education;
import com.pes.rekindle.entities.Job;
import com.pes.rekindle.entities.Lodge;
import com.pes.rekindle.services.ServiceService;

@RestController
public class ServiceController {

	public static class DTODonation {
		private char serviceType;
	    private String name;
	    private String volunteer;
	    private Integer phoneNumber;
	    private String adress;
	    private Integer places;
	    private Time startTime;
	    private Time endTime;
	    private String description;
	    
		public char getServiceType() {
			return serviceType;
		}
		public void setServiceType(char serviceType) {
			this.serviceType = serviceType;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getVolunteer() {
			return volunteer;
		}
		public void setVolunteer(String volunteer) {
			this.volunteer = volunteer;
		}
		public Integer getPhoneNumber() {
			return phoneNumber;
		}
		public void setPhoneNumber(Integer phoneNumber) {
			this.phoneNumber = phoneNumber;
		}
		public String getAdress() {
			return adress;
		}
		public void setAdress(String adress) {
			this.adress = adress;
		}
		public Integer getPlaces() {
			return places;
		}
		public void setPlaces(Integer places) {
			this.places = places;
		}
		public Time getStartTime() {
			return startTime;
		}
		public void setStartTime(Time startTime) {
			this.startTime = startTime;
		}
		public Time getEndTime() {
			return endTime;
		}
		public void setEndTime(Time endTime) {
			this.endTime = endTime;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
	}
	
	public static class DTOLodge{
		private char serviceType;
	    private String name;
	    private String volunteer;
	    private Integer phoneNumber;
	    private String adress;
	    private Integer places;
	    private Date dateLimit;
	    private String description;
	    
		public char getServiceType() {
			return serviceType;
		}
		public void setServiceType(char serviceType) {
			this.serviceType = serviceType;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getVolunteer() {
			return volunteer;
		}
		public void setVolunteer(String volunteer) {
			this.volunteer = volunteer;
		}
		public Integer getPhoneNumber() {
			return phoneNumber;
		}
		public void setPhoneNumber(Integer phoneNumber) {
			this.phoneNumber = phoneNumber;
		}
		public String getAdress() {
			return adress;
		}
		public void setAdress(String adress) {
			this.adress = adress;
		}
		public Integer getPlaces() {
			return places;
		}
		public void setPlaces(Integer places) {
			this.places = places;
		}
		public Date getDateLimit() {
			return dateLimit;
		}
		public void setDateLimit(Date dateLimit) {
			this.dateLimit = dateLimit;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
	}
    
	public static class DTOEducation{
		private char serviceType;
	    private String name;
	    private String volunteer;
	    private Integer phoneNumber;
	    private String adress;
	    private String ambit;
	    private String requirements;
	    private String schedule;
	    private Integer places;
	    private Integer price;
	    private String description;
	    
		public char getServiceType() {
			return serviceType;
		}
		public void setServiceType(char serviceType) {
			this.serviceType = serviceType;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getVolunteer() {
			return volunteer;
		}
		public void setVolunteer(String volunteer) {
			this.volunteer = volunteer;
		}
		public Integer getPhoneNumber() {
			return phoneNumber;
		}
		public void setPhoneNumber(Integer phoneNumber) {
			this.phoneNumber = phoneNumber;
		}
		public String getAdress() {
			return adress;
		}
		public void setAdress(String adress) {
			this.adress = adress;
		}
		public String getAmbit() {
			return ambit;
		}
		public void setAmbit(String ambit) {
			this.ambit = ambit;
		}
		public String getRequirements() {
			return requirements;
		}
		public void setRequirements(String requirements) {
			this.requirements = requirements;
		}
		public String getSchedule() {
			return schedule;
		}
		public void setSchedule(String schedule) {
			this.schedule = schedule;
		}
		public Integer getPlaces() {
			return places;
		}
		public void setPlaces(Integer places) {
			this.places = places;
		}
		public Integer getPrice() {
			return price;
		}
		public void setPrice(Integer price) {
			this.price = price;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
	}
	
	public static class DTOJob{
		private char serviceType;
	    private String name;
	    private String volunteer;
	    private Integer phoneNumber;
	    private String adress;
	    private String charge;
	    private String requirements;
	    private double hoursDay;
	    private double hoursWeek;
	    private Integer contractDuration;
	    private Integer places;
	    private double salary;
	    private String description;
	    
		public char getServiceType() {
			return serviceType;
		}
		public void setServiceType(char serviceType) {
			this.serviceType = serviceType;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getVolunteer() {
			return volunteer;
		}
		public void setVolunteer(String volunteer) {
			this.volunteer = volunteer;
		}
		public Integer getPhoneNumber() {
			return phoneNumber;
		}
		public void setPhoneNumber(Integer phoneNumber) {
			this.phoneNumber = phoneNumber;
		}
		public String getAdress() {
			return adress;
		}
		public void setAdress(String adress) {
			this.adress = adress;
		}
		public String getCharge() {
			return charge;
		}
		public void setCharge(String charge) {
			this.charge = charge;
		}
		public String getRequirements() {
			return requirements;
		}
		public void setRequirements(String requirements) {
			this.requirements = requirements;
		}
		public double getHoursDay() {
			return hoursDay;
		}
		public void setHoursDay(double hoursDay) {
			this.hoursDay = hoursDay;
		}
		public double getHoursWeek() {
			return hoursWeek;
		}
		public void setHoursWeek(double hoursWeek) {
			this.hoursWeek = hoursWeek;
		}
		public Integer getContractDuration() {
			return contractDuration;
		}
		public void setContractDuration(Integer contractDuration) {
			this.contractDuration = contractDuration;
		}
		public Integer getPlaces() {
			return places;
		}
		public void setPlaces(Integer places) {
			this.places = places;
		}
		public double getSalary() {
			return salary;
		}
		public void setSalary(double salary) {
			this.salary = salary;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
	}
	
	@Autowired
    private ServiceService serviceService;

    @RequestMapping(value = "/test2")
    public int test2() {
        return 1;
    }

    @RequestMapping(value = "/crearAlojamiento", method = RequestMethod.POST)
    public ResponseEntity<String> createLodge(@RequestBody DTOLodge lodge) {
        serviceService.createLodge(lodge.getName(), lodge.getVolunteer(), lodge.getPhoneNumber(),
                lodge.getAdress(), lodge.getPlaces(), lodge.getDateLimit(),
                lodge.getDescription());
        return ResponseEntity.status(HttpStatus.OK).body(null);

    }

    @RequestMapping(value = "/crearDonacion", method = RequestMethod.POST)
    public ResponseEntity<String> createDonation(@RequestBody DTODonation donation) {
        serviceService.createDonation(donation.getName(), donation.getVolunteer(),
                donation.getPhoneNumber(), donation.getAdress(), donation.getPlaces(),
                donation.getStartTime(), donation.getEndTime(), donation.getDescription());
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @RequestMapping(value = "/crearCursoEducativo", method = RequestMethod.POST)
    public ResponseEntity<String> createEducation(@RequestBody DTOEducation education) {
        serviceService.createEducation(education.getName(), education.getVolunteer(),
                education.getPhoneNumber(), education.getAdress(), education.getAmbit(),
                education.getRequirements(), education.getSchedule(), education.getPlaces(),
                education.getPrice(), education.getDescription());
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    // Cambiar prerequisite por requirements
    /*
     * @RequestMapping(value=
     * "/crearCursoEducativo/nombre={name}&email={mail}&telefono={phoneNumber}" +
     * "&direccion={adress}&ambito={ambit}&requisitos-previos={prerequisite}" +
     * "&horario={schedule}&plazas-disponibles={places}&precio={price}&descripcion={description}",
     * method=RequestMethod.POST) public String createEducation(@PathVariable("name")String
     * name, @PathVariable("mail")String mail,
     * @PathVariable("phoneNumber")Integer phoneNumber, @PathVariable("adress")String adress,
     * @PathVariable("ambit")String ambit,@PathVariable("prerequisite")String prerequisite,
     * @PathVariable("schedule")String schedule, @PathVariable("places")Integer
     * places, @PathVariable("price")Integer price,
     * @PathVariable("description")String description) { String creationResult =
     * serviceService.createEducation(name, mail, phoneNumber, adress,ambit, prerequisite, schedule,
     * places, price, description); return creationResult; }
     */

    @RequestMapping(value = "/crearOfertaEmpleo", method = RequestMethod.POST)
    public ResponseEntity<String> createJob(@RequestBody DTOJob job) {
        serviceService.createJob(job.getName(), job.getVolunteer(),
                job.getPhoneNumber(), job.getAdress(), job.getCharge(), job.getRequirements(),
                job.getHoursDay(), job.getHoursWeek(), job.getContractDuration(), job.getPlaces(),
                job.getSalary(), job.getDescription());
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    /*
     * @RequestMapping(value="/crearOfertaEmpleo/nombre={name}&email={mail}&telefono={phoneNumber}"
     * + "&direccion={adress}&puesto={charge}&requisitos-necesarios={requirements}" +
     * "&jornada={hoursDay}&horas-semanales={hoursWeek}&duracion={duration}&plazas-disponibles={places}"
     * + "&sueldo={salary}&descripcion={description}", method=RequestMethod.POST) public String
     * createEducation(@PathVariable("name")String name, @PathVariable("mail")String mail,
     * @PathVariable("phoneNumber")Integer phoneNumber, @PathVariable("adress")String adress,
     * @PathVariable("charge")String charge,@PathVariable("requirements")String requirements,
     * @PathVariable("hoursDay")Double hoursDay, @PathVariable("hoursWeek")Double hoursWeek,
     * @PathVariable("duration")Integer duration, @PathVariable("places")Integer places,
     * @PathVariable("salary")Double salary, @PathVariable("description")String description) {
     * String creationResult = serviceService.createJob(name, mail, phoneNumber, adress, charge,
     * requirements, hoursDay, hoursWeek, duration, places, salary, description); return
     * creationResult; }
     */

    @RequestMapping(value = "/listarServicios", method = RequestMethod.GET)
    public Map<String, ArrayList<Object>> listServices() {
        Map<String, ArrayList<Object>> result = new HashMap<String, ArrayList<Object>>();
        result.put("Servicios", serviceService.listServices());
        return result;
    }

    @RequestMapping(value = "/seleccionarServicio/id={id}&tipo-servicio={serviceType}", method = RequestMethod.POST)
    public Object infoService(@PathVariable("id") Long id,
            @PathVariable("serviceType") char serviceType) {
        Object service = serviceService.infoService(id, serviceType);
        return service;
    }
    
    @RequestMapping(value = "/eliminarServicio", method = RequestMethod.POST)
    public ResponseEntity<Boolean> deleteService() { //id servicio, tipo servicio
    	long id;
    	char serviceType;
    	
    	id = 1;
    	serviceType = 'l';
    	
    	if (serviceService.deleteService(id, serviceType))
    		return ResponseEntity.status(HttpStatus.OK).body(null);
    	else
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    		
    }
}
