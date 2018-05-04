
package com.pes.rekindle.controllers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pes.rekindle.entities.Refugee;
import com.pes.rekindle.entities.Volunteer;
import com.pes.rekindle.services.UserService;

@RestController
public class UserController {

    public static class LogInInfo {
        String mail;
        String password;
        String newPassword;

        public String getMail() {
            return mail;
        }

        public void setMail(String mail) {
            this.mail = mail;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getNewPassword() {
            return newPassword;
        }

        public void setNewPassword(String newPassword) {
            this.newPassword = newPassword;
        }
    }
    
    public static class MailandPassword {
    	String mail;
    	String password;
		public String getMail() {
			return mail;
		}
		public void setMail(String mail) {
			this.mail = mail;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
    }
    
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    public Refugee test2() {
        Refugee refugee = new Refugee();
        refugee.setName("DonEscro");
        refugee.setMail("dones@gmail.com");
        refugee.setPassword("1234");
        refugee.setSurname1("Casas");
        return refugee;
    }
    
    @RequestMapping(value = "/test4", method = RequestMethod.GET)
    public ResponseEntity<Object> test4() {
    	Refugee r = new Refugee();
        r.setName("DonEscro");
        r.setMail("dones@gmail.com");
        r.setPassword("1234");
        r.setSurname1("Casas");
        //r.getSurname2("JASJD");
        return ResponseEntity.status(HttpStatus.OK).body(r);
    }  

    @RequestMapping(value = "/voluntarios", method = RequestMethod.POST)
    public ResponseEntity<Volunteer> createVolunteer(@RequestBody Volunteer volunteer) {
        Volunteer createdVolunteer;
        try {
            createdVolunteer = userService.createVolunteer(volunteer.getMail(),
                    volunteer.getPassword(), volunteer.getName(),
                    volunteer.getSurname1(), volunteer.getSurname2());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(createdVolunteer);
    }

    @RequestMapping(value = "/refugiados", method = RequestMethod.POST)
    public ResponseEntity<Refugee> createRefugee(@RequestBody Refugee refugee) {
        System.out.println(refugee.getMail());
        System.out.println(refugee.getName());
        System.out.println(refugee.getSex());
        Refugee createdRefugee;
        try {
            createdRefugee = userService.createRefugee(refugee.getMail(),
                    refugee.getPassword(),
                    refugee.getName(), refugee.getSurname1(),
                    refugee.getSurname2(), refugee.getPhoneNumber(), refugee.getBirthdate(),
                    refugee.getSex(), refugee.getCountry(), refugee.getTown(),
                    refugee.getEthnic(), refugee.getBloodType(), refugee.getEyeColor());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(createdRefugee);
    }
    
    /*
    @RequestMapping(value = "/login/{mail}&{password}", method = RequestMethod.GET)
    public ResponseEntity<Object> logIn(@PathVariable String mail, @PathVariable String password) {
    	Pair<Integer, Object> user = userService.exists(mail, password);
        if (user.getFirst()==0 || user.getFirst()==1)
            return ResponseEntity.status(HttpStatus.OK).header("Tipo", user.getFirst().toString()).body(user.getSecond());
        	//return ResponseEntity.status(HttpStatus.OK).body(user.getSecond());
        else 
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
    */

    /*
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Object> logIn(@RequestBody MailandPassword logInInfo) {
    	System.out.println("-----------------------------------------------------------------------------------------------");
    	System.out.println("-----------------------------------------------------------------------------------------------");
    	System.out.println("-----------------------------------------------------------------------------------------------");
    	System.out.println("-----------------------------------------------------------------------------------------------");
    	System.out.println("-----------------------------------------------------------------------------------------------");
    	System.out.println("-----------------------------------------------------------------------------------------------");

    	System.out.println("Informacion del usuario: " + logInInfo.getMail() + " " + logInInfo.getPassword());
    	
    	System.out.println("-----------------------------------------------------------------------------------------------");
    	System.out.println("-----------------------------------------------------------------------------------------------");
    	System.out.println("-----------------------------------------------------------------------------------------------");
    	System.out.println("-----------------------------------------------------------------------------------------------");
    	System.out.println("-----------------------------------------------------------------------------------------------");

    	Pair<Integer, Object> user = userService.exists(logInInfo.getMail(), logInInfo.getPassword());
        if (user.getFirst()==0 || user.getFirst()==1) {
            return ResponseEntity.status(HttpStatus.OK).header("Tipo", user.getFirst().toString()).body(user.getSecond());
        	//return ResponseEntity.status(HttpStatus.OK).body(user.getSecond());
        }
        else 
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
    */
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Object> logIn(@RequestAttribute("mail") String mail, @RequestAttribute("password") String password) {
    	System.out.println("-----------------------------------------------------------------------------------------------");
    	System.out.println("-----------------------------------------------------------------------------------------------");
    	System.out.println("-----------------------------------------------------------------------------------------------");
    	System.out.println("-----------------------------------------------------------------------------------------------");
    	System.out.println("-----------------------------------------------------------------------------------------------");
    	System.out.println("-----------------------------------------------------------------------------------------------");

    	System.out.println("Informacion del usuario: " + mail + " " + password);
    	
    	System.out.println("-----------------------------------------------------------------------------------------------");
    	System.out.println("-----------------------------------------------------------------------------------------------");
    	System.out.println("-----------------------------------------------------------------------------------------------");
    	System.out.println("-----------------------------------------------------------------------------------------------");
    	System.out.println("-----------------------------------------------------------------------------------------------");

    	Pair<Integer, Object> user = userService.exists(mail, password);
        if (user.getFirst()==0 || user.getFirst()==1) {
            return ResponseEntity.status(HttpStatus.OK).header("Tipo", user.getFirst().toString()).body(user.getSecond());
        	//return ResponseEntity.status(HttpStatus.OK).body(user.getSecond());
        }
        else 
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }    

    @RequestMapping(value = "/cambiarPasswordVoluntario", method = RequestMethod.POST)
    public ResponseEntity<String> changePasswordVolunteer(@RequestBody LogInInfo logInInfo) {
        Boolean cambio = userService.changePasswordVolunteer(logInInfo.getMail(),
                logInInfo.getPassword(), logInInfo.getNewPassword());
        if (cambio) {
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @RequestMapping(value = "/cambiarPasswordRefugiado", method = RequestMethod.POST)
    public ResponseEntity<String> changePasswordRefugee(@RequestBody LogInInfo logInInfo) {
        Boolean cambio = userService.changePasswordRefugee(logInInfo.getMail(),
                logInInfo.getPassword(), logInInfo.getNewPassword());
        if (cambio) {
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @RequestMapping(value = "/voluntarios/{mail}", method = RequestMethod.PUT)
    public ResponseEntity<String> modifyProfileVolunteer(@RequestBody Volunteer volunteer) {
    	//Cuidado tema seguridad
        userService.modifyProfileVolunteer(volunteer.getMail(), volunteer.getName(),
                volunteer.getSurname1(), volunteer.getSurname2());
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @RequestMapping(value = "/refugiados/{mail}", method = RequestMethod.PUT)
    public ResponseEntity<String> modifyProfileRefugee(@RequestBody Refugee refugee) {
        userService.modifyProfileRefugee(refugee.getMail(), refugee.getName(),
                refugee.getSurname1(),
                refugee.getSurname2(), refugee.getPhoneNumber(), refugee.getBirthdate(),
                refugee.getSex(), refugee.getCountry(), refugee.getTown(),
                refugee.getEthnic(), refugee.getBloodType(), refugee.getEyeColor());
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @RequestMapping(value = "/voluntarios/{mail}", method = RequestMethod.GET)
    public ResponseEntity<Volunteer> infoVolunteer(@PathVariable String mail) {
        Volunteer volunteer = userService.infoVolunteer(mail);
        return ResponseEntity.status(HttpStatus.OK).body(volunteer);
    }

    @RequestMapping(value = "/refugiados/{mail}", method = RequestMethod.GET)
    public ResponseEntity<Refugee> infoRefugee(@PathVariable String mail) {
        Refugee refugee = userService.infoRefugee(mail);
        return ResponseEntity.status(HttpStatus.OK).body(refugee);
    }
    
    @RequestMapping(value = "/testLodge", method = RequestMethod.GET)
    public ResponseEntity<Refugee> lodgeTest() {
    	String refugeeMail;
    	long serviceId;
    	refugeeMail = "alex@gmail.com";
    	serviceId = 2;
        userService.enrollRefugeeLodge(refugeeMail, serviceId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    
    @RequestMapping(value = "/testDonation", method = RequestMethod.GET)
    public ResponseEntity<Refugee> donationTest() {
    	String refugeeMail;
    	long serviceId;
    	refugeeMail = "alex@gmail.com";
    	serviceId = 1;
        userService.enrollRefugeeDonation(refugeeMail, serviceId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    
    @RequestMapping(value = "/testJob", method = RequestMethod.GET)
    public ResponseEntity<Refugee> jobTest() {
    	String refugeeMail;
    	long serviceId;
    	refugeeMail = "alex@gmail.com";
    	serviceId = 1;
        userService.enrollRefugeeJob(refugeeMail, serviceId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    
    @RequestMapping(value = "/testEducation", method = RequestMethod.GET)
    public ResponseEntity<Refugee> educationTest() {
    	String refugeeMail;
    	long serviceId;
    	refugeeMail = "alex@gmail.com";
    	serviceId = 2;
        userService.enrollRefugeeEducation(refugeeMail, serviceId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    
}
