
package com.pes.rekindle.controllers;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.security.auth.login.LoginException;

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

import com.pes.rekindle.dto.DTOChat;
import com.pes.rekindle.dto.DTOLink;
import com.pes.rekindle.dto.DTOLogInInfo;
import com.pes.rekindle.dto.DTOMessage;
import com.pes.rekindle.dto.DTOReport;
import com.pes.rekindle.dto.DTOUser;
import com.pes.rekindle.exceptions.ReportNotExistsException;
import com.pes.rekindle.exceptions.UserAlreadyExistsException;
import com.pes.rekindle.exceptions.UserNotExistsException;
import com.pes.rekindle.exceptions.UserStateAlreadyUpdatedException;
import com.pes.rekindle.services.UserService;
import com.pusher.rest.Pusher;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/voluntarios", method = RequestMethod.POST)
    public ResponseEntity<Void> createVolunteer(@RequestBody DTOUser volunteer) {
        try {
            userService.createVolunteer(volunteer);
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @RequestMapping(value = "/refugiados", method = RequestMethod.POST)
    public ResponseEntity<Void> createRefugee(@RequestBody DTOUser refugee) {
        try {
            userService.createRefugee(refugee);
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<DTOUser> logIn(DTOLogInInfo logInInfo) {
        DTOUser dtoUser;
        try {
            dtoUser = userService.getUser(logInInfo);
            if (dtoUser.getEnabled() == 0) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
            }
            return ResponseEntity.status(HttpStatus.OK).body(dtoUser);
        } catch (LoginException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @RequestMapping(value = "/cambiarPassword/{mail}", method = RequestMethod.PUT)
    public ResponseEntity<Void> changePassword(@RequestHeader("apiKey") String apiKey,
            @PathVariable String mail, String passwordOld,
            String passwordNew) {
        if (userService.authenticate(mail, apiKey)) {
            try {
                userService.changePassword(mail, passwordOld, passwordNew);
                return ResponseEntity.status(HttpStatus.OK).body(null);
            } catch (LoginException e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @RequestMapping(value = "/recuperarPassword/{mail}", method = RequestMethod.PUT)
    public ResponseEntity<Void> recoverPassword(@PathVariable String mail, String passwordNew) {
        try {
            userService.recoverPassword(mail, passwordNew);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (LoginException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }

    @RequestMapping(value = "/voluntarios/{mail}", method = RequestMethod.PUT)
    public ResponseEntity<Void> modifyVolunteer(@RequestHeader("apiKey") String apiKey,
            @RequestBody DTOUser dtoUser) {
        if (userService.authenticate(dtoUser.getMail(), apiKey)) {
            userService.modifyProfileVolunteer(dtoUser);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @RequestMapping(value = "/refugiados/{mail}", method = RequestMethod.PUT)
    public ResponseEntity<Void> modifyRefugee(@RequestHeader("apiKey") String apiKey,
            @RequestBody DTOUser refugee) {
        if (userService.authenticate(refugee.getMail(), apiKey)) {
            userService.modifyProfileRefugee(refugee);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @RequestMapping(value = "/voluntarios/{mail}", method = RequestMethod.GET)
    public ResponseEntity<DTOUser> getVolunteer(@PathVariable String mail) {
        try {
            DTOUser volunteer = userService.getVolunteer(mail);
            volunteer = userService.hideCredentials(volunteer);
            return ResponseEntity.status(HttpStatus.OK).body(volunteer);
        } catch (UserNotExistsException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @RequestMapping(value = "/refugiados/{mail}", method = RequestMethod.GET)
    public ResponseEntity<DTOUser> getRefugee(@PathVariable String mail) {
        try {
            DTOUser refugee = userService.getRefugee(mail);
            return ResponseEntity.status(HttpStatus.OK).body(refugee);
        } catch (UserNotExistsException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @RequestMapping(value = "/usuarios", method = RequestMethod.GET)
    public ResponseEntity<Set<DTOUser>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    @RequestMapping(value = "/usuarios/{mail}/enabled", method = RequestMethod.GET)
    public ResponseEntity<Integer> isUserEnabled(@PathVariable String mail) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.isUserEnabled(mail));
        } catch (UserNotExistsException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @RequestMapping(value = "/usuarios/{mail}/enable", method = RequestMethod.PUT)
    public ResponseEntity<Void> enableUser(@PathVariable String mail) {
        try {
            userService.modifyBannedStatus(mail, 1);
        } catch (UserNotExistsException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (UserStateAlreadyUpdatedException e) {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @RequestMapping(value = "/usuarios/{mail}/disable", method = RequestMethod.PUT)
    public ResponseEntity<Void> disableUser(@PathVariable String mail,
            @RequestParam String motive) {
        try {
            userService.modifyBannedStatus(mail, 0);
        } catch (UserNotExistsException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (UserStateAlreadyUpdatedException e) {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(null);
        }

        Pusher pusher = new Pusher("525518", "743a4fb4a1370f0ca9a4", "c78f3bfa72330a58ee1f");
        pusher.setCluster("eu");
        pusher.setEncrypted(true);

        pusher.trigger(mail, "ban",
                Collections.singletonMap("message", motive));

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @RequestMapping(value = "/refugiados", method = RequestMethod.GET)
    public ResponseEntity<Set<DTOUser>> findRefugees(@RequestParam("name") String name,
            @RequestParam("surname1") String surname1,
            @RequestParam("surname2") String surname2, @RequestParam("birthdate") String birthdate,
            @RequestParam("sex") String sex,
            @RequestParam("country") String country, @RequestParam("town") String town,
            @RequestParam("ethnic") String ethnic,
            @RequestParam("blood") String blood, @RequestParam("eye") String eye,
            @RequestParam("mail") String mail) {

        Set<DTOUser> refugees = userService.findRefugee(name, surname1, surname2, birthdate, sex,
                country, town, ethnic, blood, eye, mail);
        return ResponseEntity.status(HttpStatus.OK).body(refugees);
    }

    @RequestMapping(value = "/usuarios/{mail}/inscripciones/{id}/{tipo}", method = RequestMethod.POST)
    public ResponseEntity<Void> enrollUserToService(@RequestHeader("apiKey") String apiKey,
            @PathVariable String mail,
            @PathVariable Long id,
            @PathVariable String tipo) throws Exception {
        if (userService.authenticate(mail, apiKey)) {
            try {
                userService.enrollUserToService(mail, id, tipo);

            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
            }
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @RequestMapping(value = "/refugiados/{mail}/inscripciones/{id}/{tipo}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> unenrollUserFromService(@RequestHeader("apiKey") String apiKey,
            @PathVariable String mail,
            @PathVariable Long id,
            @PathVariable String tipo) throws Exception {
        if (userService.authenticate(mail, apiKey)) {
            userService.unenrollUserFromService(mail, id, tipo);

            Pusher pusher = new Pusher("525518", "743a4fb4a1370f0ca9a4", "c78f3bfa72330a58ee1f");
            pusher.setCluster("eu");
            pusher.setEncrypted(true);

            pusher.trigger(tipo + mail, "unenroll-service",
                    Collections.singletonMap("message", id));

            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

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
    public ResponseEntity<DTOChat> createChat(@RequestHeader("apiKey") String apiKey,
            @RequestBody DTOChat dtoChat, @PathVariable String mail) {
        if (userService.authenticate(mail, apiKey)) {
            DTOChat createdChat = userService.createChat(dtoChat);

            Pusher pusher = new Pusher("525518", "743a4fb4a1370f0ca9a4", "c78f3bfa72330a58ee1f");
            pusher.setCluster("eu");
            pusher.setEncrypted(true);

            pusher.trigger(dtoChat.getUser2().getMail(), "new-chat",
                    Collections.singletonMap("message", createdChat.getId()));

            return ResponseEntity.status(HttpStatus.OK).body(createdChat);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

    }

    // Enviar mensaje
    @RequestMapping(value = "/usuarios/{mail}/chats/{idChat}/messages", method = RequestMethod.POST)
    public ResponseEntity<Void> sendMessage(@RequestHeader("apiKey") String apiKey,
            @PathVariable String mail,
            @PathVariable long idChat, @RequestBody DTOMessage dtoMessage) {
        if (userService.authenticate(mail, apiKey)) {
            userService.sendMessage(mail, idChat, dtoMessage);
            Pusher pusher = new Pusher("525518", "743a4fb4a1370f0ca9a4", "c78f3bfa72330a58ee1f");
            pusher.setCluster("eu");
            pusher.setEncrypted(true);

            pusher.trigger(Long.toString(idChat), "new-message",
                    Collections.singletonMap("message", dtoMessage));
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

    }

    @RequestMapping(value = "/reportes", method = RequestMethod.POST)
    public ResponseEntity<Void> createReport(@RequestBody DTOReport dtoReport) {
        userService.createReport(dtoReport);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @RequestMapping(value = "/reportes", method = RequestMethod.GET)
    public ResponseEntity<Set<DTOReport>> createReport() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.listReports());
    }

    @RequestMapping(value = "/reportes/{id}", method = RequestMethod.GET)
    public ResponseEntity<DTOReport> getReport(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getReport(id));
    }

    @RequestMapping(value = "/reportes/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteReport(@PathVariable Long id) {
        try {
            userService.deleteReport(id);
        } catch (ReportNotExistsException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @RequestMapping(value = "/links", method = RequestMethod.POST)
    public ResponseEntity<Void> createLink(@RequestBody DTOLink dtoLink) {
        userService.createLink(dtoLink);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @RequestMapping(value = "/links", method = RequestMethod.GET)
    public ResponseEntity<Set<DTOLink>> listLinks() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.listLinks());
    }

    @RequestMapping(value = "/links/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> modifyLink(@PathVariable Long id, @RequestBody DTOLink dtoLink) {
        userService.modifyLink(dtoLink);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @RequestMapping(value = "/links/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteLink(@PathVariable Long id) {
        userService.deleteLink(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity<String> test() {

        String id = userService.test();
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }

    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    public ResponseEntity<Void> test2() {
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
