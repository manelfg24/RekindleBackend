
package com.pes.rekindle.controllers;

import java.sql.Date;
import java.util.Collections;
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

import com.pes.rekindle.dto.DTOChat;
import com.pes.rekindle.dto.DTOLogInInfo;
import com.pes.rekindle.dto.DTOMessage;
import com.pes.rekindle.dto.DTOUser;
import com.pes.rekindle.entities.Refugee;
import com.pes.rekindle.entities.Volunteer;
import com.pes.rekindle.services.UserService;
import com.pusher.rest.Pusher;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/voluntarios", method = RequestMethod.POST)
    public ResponseEntity createVolunteer(@RequestBody Volunteer volunteer) {
        try {
            userService.createVolunteer(volunteer);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @RequestMapping(value = "/refugiados", method = RequestMethod.POST)
    public ResponseEntity createRefugee(@RequestBody DTOUser refugee) {
        try {
            userService.createRefugee(refugee);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<DTOUser> logIn(DTOLogInInfo logInInfo) {
        DTOUser dtoUser = userService.exists(logInInfo.getMail(),
                logInInfo.getPassword());
        if (dtoUser.getUserType().equals("Refugee") || dtoUser.getUserType().equals("Volunteer")) {
            return ResponseEntity.status(HttpStatus.OK).body(dtoUser);
        } else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @RequestMapping(value = "/cambiarPassword/{mail}", method = RequestMethod.PUT)
    public ResponseEntity changePassword(@PathVariable String mail, String passwordOld,
            String passwordNew) {
        if (userService.changePassword(mail, passwordOld, passwordNew)) {
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @RequestMapping(value = "/recuperarPassword/{mail}", method = RequestMethod.PUT)
    public ResponseEntity changePassword(@PathVariable String mail, String passwordNew) {
        if (userService.recoverPassword(mail, passwordNew)) {
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @RequestMapping(value = "/voluntarios/{mail}", method = RequestMethod.PUT)
    public ResponseEntity modifyProfileVolunteer(@RequestBody DTOUser dtoUser) {
        // Cuidado tema seguridad
        userService.modifyProfileVolunteer(dtoUser);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @RequestMapping(value = "/refugiados/{mail}", method = RequestMethod.PUT)
    public ResponseEntity modifyProfileRefugee(@RequestBody DTOUser refugee) {
        userService.modifyProfileRefugee(refugee);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @RequestMapping(value = "/voluntarios/{mail}", method = RequestMethod.GET)
    public ResponseEntity<DTOUser> infoVolunteer(@PathVariable String mail) {
        DTOUser dtoUser = userService.infoVolunteer(mail);
        return ResponseEntity.status(HttpStatus.OK).body(dtoUser);
    }

    @RequestMapping(value = "/refugiados/{mail}", method = RequestMethod.GET)
    public ResponseEntity<DTOUser> infoRefugee(@PathVariable String mail) {
        DTOUser refugee = userService.infoRefugee(mail);
        if (!(refugee == null))
            return ResponseEntity.status(HttpStatus.OK).body(refugee);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    // Busqueda de refugiados
    @RequestMapping(value = "/refugiados", method = RequestMethod.GET)
    public ResponseEntity<Set<DTOUser>> findRefugee(@RequestParam("name") String name,
            @RequestParam("surname1") String surname1,
            @RequestParam("surname2") String surname2, @RequestParam("birthdate") Date birthdate,
            @RequestParam("sex") String sex,
            @RequestParam("country") String country, @RequestParam("town") String town,
            @RequestParam("ethnic") String ethnic,
            @RequestParam("blood") String blood, @RequestParam("eye") String eye,
            @RequestParam("mail") String mail) {
        Set<DTOUser> refugees = userService.findRefugee(name, surname1, surname2, birthdate, sex,
                country, town, ethnic, blood, eye, mail);
        return ResponseEntity.status(HttpStatus.OK).body(refugees);
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

    // Todos chats del mail
    @RequestMapping(value = "/usuarios/{mail}/chats", method = RequestMethod.GET)
    public ResponseEntity<Set<DTOChat>> listUserChats(@PathVariable String mail) {
        Set<DTOChat> dtoChats = userService.listUserChats(mail);
        return ResponseEntity.status(HttpStatus.OK).body(dtoChats);
    }

    // Todos los mensajes de un chat
    @RequestMapping(value = "/usuarios/{mail}/chats/{idChat}/messages", method = RequestMethod.GET)
    public ResponseEntity<List<DTOMessage>> getChatMessages(@PathVariable String mail,
            @PathVariable long idChat) {
        List<DTOMessage> dtoMessages = userService.listMessagesChat(mail, idChat);
        return ResponseEntity.status(HttpStatus.OK).body(dtoMessages);
    }

    // Devuelve el chat entre dos personas, mismo nombre que listUserChats
    @RequestMapping(value = "/usuarios/{mail}/chat", method = RequestMethod.GET)
    public ResponseEntity<DTOChat> getChat(@PathVariable String mail,
            @RequestParam("mail1") String mail1,
            @RequestParam("mail2") String mail2) {
        DTOChat dtoChats = userService.getChat(mail1, mail2);
        if (dtoChats != null) {
            return ResponseEntity.status(HttpStatus.OK).body(dtoChats);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dtoChats);
    }

    // Crea un chat
    @RequestMapping(value = "/usuarios/{mail}/chats", method = RequestMethod.POST)
    public ResponseEntity<DTOChat> createChat(@RequestBody DTOChat dtoChat) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.createChat(dtoChat));
    }

    // Enviar mensaje
    @RequestMapping(value = "/usuarios/{mail}/chats/{idChat}/messages", method = RequestMethod.POST)
    public ResponseEntity sendMessage(@PathVariable String mail,
            @PathVariable long idChat, @RequestBody DTOMessage dtoMessage) {

        userService.sendMessage(mail, idChat, dtoMessage);
        Pusher pusher = new Pusher("525518", "743a4fb4a1370f0ca9a4", "c78f3bfa72330a58ee1f");
        pusher.setCluster("eu");
        pusher.setEncrypted(true);

        pusher.trigger("my-channel", "my-event", Collections.singletonMap("message", dtoMessage));
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    /*
     * @RequestMapping(value = "/usuarios/{mail}/chats", method = RequestMethod.GET) public
     * ResponseEntity<Set<DTOUser>> newChat(@PathVariable String mailUser1, @RequestBody String
     * mailUser2) { return ResponseEntity.status(HttpStatus.OK).body(userService.newChat(mailUser1,
     * mailUser2)); }
     */

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity<String> test() {

        String id = userService.test();
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }
}
