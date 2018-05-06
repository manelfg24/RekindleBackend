
package com.pes.rekindle.controllers;

import java.sql.Date;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pes.rekindle.entities.Lodge;
import com.pes.rekindle.entities.Refugee;
import com.pes.rekindle.entities.Volunteer;
import com.pes.rekindle.services.UserService;

@RestController
public class UserController {
    
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
        // r.getSurname2("JASJD");
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
    public ResponseEntity<Refugee> createRefugee(@RequestBody DTORefugee refugee) {
        Refugee createdRefugee;
        try {
            createdRefugee = userService.createRefugee(refugee.getMail(),
                    refugee.getPassword(),
                    refugee.getName(), refugee.getSurname1(),
                    refugee.getSurname2(), refugee.getPhoneNumber(), refugee.getBirthdate(),
                    refugee.getSex(), refugee.getCountry(), refugee.getTown(),
                    refugee.getEthnic(), refugee.getBloodType(), refugee.getEyeColor(), refugee.getBiography());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(createdRefugee);
    }

    /*
     * @RequestMapping(value = "/login/{mail}&{password}", method = RequestMethod.GET) public
     * ResponseEntity<Object> logIn(@PathVariable String mail, @PathVariable String password) {
     * Pair<Integer, Object> user = userService.exists(mail, password); if (user.getFirst()==0 ||
     * user.getFirst()==1) return ResponseEntity.status(HttpStatus.OK).header("Tipo",
     * user.getFirst().toString()).body(user.getSecond()); //return
     * ResponseEntity.status(HttpStatus.OK).body(user.getSecond()); else return
     * ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); }
     */

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Object> logIn(LogInInfo logInInfo) {
        System.out.println(
                "Informacion del usuario: " + logInInfo.getMail() + " " + logInInfo.getPassword());
        Pair<Integer, Object> user = userService.exists(logInInfo.getMail(),
                logInInfo.getPassword());
        if (user.getFirst() == 0 || user.getFirst() == 1) {
            return ResponseEntity.status(HttpStatus.OK).header("Tipo", user.getFirst().toString())
                    .body(user.getSecond());
        } else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
    
    @RequestMapping(value = "/cambiarPassword/{mail}", method = RequestMethod.PUT)
    public ResponseEntity<Object> changePassword(@PathVariable String mail, String passwordOld, String passwordNew) {
    	if (userService.changePassword(mail, passwordOld, passwordNew)) {
    		return ResponseEntity.status(HttpStatus.OK).body(null);
    	} else {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    	}
    }
    
    @RequestMapping(value = "/recuperarPassword/{mail}", method = RequestMethod.PUT)
    public ResponseEntity<Object> changePassword(@PathVariable String mail, String passwordNew) {
    	if (userService.recoverPassword(mail, passwordNew)) {
    		return ResponseEntity.status(HttpStatus.OK).body(null);
    	} else {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    	}
    }
    
    /*@RequestMapping(value = "/cambiarPasswordVoluntario", method = RequestMethod.POST)
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
    }*/

    @RequestMapping(value = "/voluntarios/{mail}", method = RequestMethod.PUT)
    public ResponseEntity<String> modifyProfileVolunteer(@RequestBody Volunteer volunteer) {
        // Cuidado tema seguridad
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
                refugee.getEthnic(), refugee.getBloodType(), refugee.getEyeColor(), refugee.getBiography());
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
    
    
    //Busqueda de refugiados
    @RequestMapping(value = "/refugiados", method = RequestMethod.GET)
    public ResponseEntity<Set<Refugee>> findRefugee(@RequestParam("name") String name, @RequestParam("surname1") String surname1, 
    		@RequestParam("surname2") String surname2, @RequestParam("birthdate") Date birthdate, @RequestParam("sex") String sex, 
    		@RequestParam("country") String country, @RequestParam("town") String town, @RequestParam("ethnic") String ethnic, 
    		@RequestParam("blood") String blood, @RequestParam("eye") String eye) {
        Set<Refugee> refugees = userService.findRefugee(name, surname1,surname2, birthdate, sex, country, town, ethnic, blood, eye);
        return ResponseEntity.status(HttpStatus.OK).body(refugees);
    }
    
    
    @RequestMapping(value = "/refugiados/{mail}/servicios", method = RequestMethod.GET)
    public ResponseEntity<Set<Lodge>> refugeeServices(@PathVariable String mail) {
        Set<Lodge> lodges = userService.refugeeLodges(mail);
        ServiceRefugee services = new ServiceRefugee();
    	for (Lodge lodge : lodges) {
    		System.out.println(lodge.getId());
    		System.out.println(lodge.getDescription());
    		System.out.println(lodge.getServiceType());
    		System.out.println(lodge.getName());
    		System.out.println(lodge.getVolunteer());
    		System.out.println(lodge.getInscriptions());
    	}
        // services.setLodgesRefugge(lodges);
       return ResponseEntity.status(HttpStatus.OK).body(lodges);
        //return null;
    }

    @RequestMapping(value = "/testLodge", method = RequestMethod.GET)
    public ResponseEntity<Refugee> lodgeTest() {
        String refugeeMail;
        long serviceId;
        refugeeMail = "felipe@gmail.com";
        serviceId = 1;
        userService.enrollRefugeeLodge(refugeeMail, serviceId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @RequestMapping(value = "/testDonation", method = RequestMethod.GET)
    public ResponseEntity<Refugee> donationTest() {
        String refugeeMail;
        long serviceId;
        refugeeMail = "felipe@gmail.com";
        serviceId = 1;
        userService.enrollRefugeeDonation(refugeeMail, serviceId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @RequestMapping(value = "/testJob", method = RequestMethod.GET)
    public ResponseEntity<Refugee> jobTest() {
        String refugeeMail;
        long serviceId;
        refugeeMail = "felipe@gmail.com";
        serviceId = 1;
        userService.enrollRefugeeJob(refugeeMail, serviceId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @RequestMapping(value = "/testEducation", method = RequestMethod.GET)
    public ResponseEntity<Refugee> educationTest() {
        String refugeeMail;
        long serviceId;
        refugeeMail = "felipe@gmail.com";
        serviceId = 1;
        userService.enrollRefugeeEducation(refugeeMail, serviceId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    
    //------------------------------------------------
    
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

    public static class ServiceRefugee {

        public Set<Lodge> lodgesRefugge;

        public Set<Lodge> getLodgesRefugge() {
            return lodgesRefugge;
        }

        public void setLodgesRefugge(Set<Lodge> lodgesRefugge) {
            this.lodgesRefugge = lodgesRefugge;
        }

    }

    public static class DTORefugee {
        private String mail;
        private String password;
        private String name;
        private String surname1;
        private String surname2;
        private Integer phoneNumber;
        private Date birthdate;
        private String sex;
        private String country;
        private String town;
        private String ethnic;
        private String bloodType;
        private String eyeColor;
        private String biography;
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
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getSurname1() {
			return surname1;
		}
		public void setSurname1(String surname1) {
			this.surname1 = surname1;
		}
		public String getSurname2() {
			return surname2;
		}
		public void setSurname2(String surname2) {
			this.surname2 = surname2;
		}
		public Integer getPhoneNumber() {
			return phoneNumber;
		}
		public void setPhoneNumber(Integer phoneNumber) {
			this.phoneNumber = phoneNumber;
		}
		public Date getBirthdate() {
			return birthdate;
		}
		public void setBirthdate(Date birthdate) {
			this.birthdate = birthdate;
		}
		public String getSex() {
			return sex;
		}
		public void setSex(String sex) {
			this.sex = sex;
		}
		public String getCountry() {
			return country;
		}
		public void setCountry(String country) {
			this.country = country;
		}
		public String getTown() {
			return town;
		}
		public void setTown(String town) {
			this.town = town;
		}
		public String getEthnic() {
			return ethnic;
		}
		public void setEthnic(String ethnic) {
			this.ethnic = ethnic;
		}
		public String getBloodType() {
			return bloodType;
		}
		public void setBloodType(String bloodType) {
			this.bloodType = bloodType;
		}
		public String getEyeColor() {
			return eyeColor;
		}
		public void setEyeColor(String eyeColor) {
			this.eyeColor = eyeColor;
		}
		public String getBiography() {
			return biography;
		}
		public void setBiography(String biography) {
			this.biography = biography;
		}    	
    }
}
