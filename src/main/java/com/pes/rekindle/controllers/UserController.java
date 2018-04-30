
package com.pes.rekindle.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @RequestMapping(value = "/registrarVoluntario", method = RequestMethod.POST)
    public ResponseEntity<String> createVolunteer(@RequestBody Volunteer volunteer) {
        String creationResult = userService.createVolunteer(volunteer.getMail(),
                volunteer.getPassword(), volunteer.getName(),
                volunteer.getSurname1(), volunteer.getSurname2());
        if (creationResult.equals("Usuario creado con exito")) {
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
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
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(createdRefugee);
    }

    @RequestMapping(value = "/inicioSesion", method = RequestMethod.POST)
    public ResponseEntity<String> logIn(@RequestBody LogInInfo logInInfo) {
        if (userService.exists(logInInfo.getMail(), logInInfo.getPassword())) {
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
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

    @RequestMapping(value = "/modificarPerfilVoluntario", method = RequestMethod.POST)
    public ResponseEntity<String> modifyProfileVolunteer(@RequestBody Volunteer volunteer) {
        userService.modifyProfileVolunteer(volunteer.getMail(), volunteer.getName(),
                volunteer.getSurname1(), volunteer.getSurname2());
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @RequestMapping(value = "/modificarPerfil", method = RequestMethod.POST)
    public ResponseEntity<String> modifyProfileRefugee(@RequestBody Refugee refugee) {
        userService.modifyProfileRefugee(refugee.getMail(), refugee.getName(),
                refugee.getSurname1(),
                refugee.getSurname2(), refugee.getPhoneNumber(), refugee.getBirthdate(),
                refugee.getSex(), refugee.getCountry(), refugee.getTown(),
                refugee.getEthnic(), refugee.getBloodType(), refugee.getEyeColor());
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @RequestMapping(value = "/verPerfilVoluntario", method = RequestMethod.POST)
    public ResponseEntity<Volunteer> infoVolunteer(@RequestBody LogInInfo logInInfo) {
        Volunteer volunteer = userService.infoVolunteer(logInInfo.getMail());
        return ResponseEntity.status(HttpStatus.OK).body(volunteer);
    }

    @RequestMapping(value = "/verPerfilRefugiado", method = RequestMethod.POST)
    public ResponseEntity<Refugee> infoRefugee(@RequestBody LogInInfo logInInfo) {
        Refugee refugee = userService.infoRefugee(logInInfo.getMail());
        return ResponseEntity.status(HttpStatus.OK).body(refugee);
    }

}
