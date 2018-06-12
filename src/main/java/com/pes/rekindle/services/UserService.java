
package com.pes.rekindle.services;

import java.util.List;
import java.util.Set;

import javax.security.auth.login.LoginException;

import com.pes.rekindle.dto.DTOChat;
import com.pes.rekindle.dto.DTOLink;
import com.pes.rekindle.dto.DTOLogInInfo;
import com.pes.rekindle.dto.DTOMessage;
import com.pes.rekindle.dto.DTOReport;
import com.pes.rekindle.dto.DTOService;
import com.pes.rekindle.dto.DTOUser;
import com.pes.rekindle.exceptions.UserAlreadyExistsException;
import com.pes.rekindle.exceptions.UserNotExistsException;

public interface UserService {

    // boolean logIn(String user, String password);
    Object logIn(String user, String password);

    void createVolunteer(DTOUser volunteer) throws UserAlreadyExistsException;

    void createRefugee(DTOUser refugee) throws UserAlreadyExistsException;

    DTOUser logInVolunteer(String mail, String password);

    DTOUser logInAdmin(String mail, String password);

    DTOUser logInRefugee(String mail, String password);

    Boolean changePasswordVolunteer(String mail, String password, String newPassword);

    Boolean changePasswordRefugee(String mail, String password, String newPassword);

    void modifyProfileVolunteer(DTOUser dtoUser);

    void modifyProfileRefugee(DTOUser refugee);

    DTOUser getVolunteer(String mail) throws UserNotExistsException;

    DTOUser getRefugee(String mail) throws UserNotExistsException;

    DTOUser getUser(DTOLogInInfo dtoLogInInfo) throws LoginException;

    void enrollUserToService(String mail, Long id, String userType) throws Exception;

    Set<DTOUser> findRefugee(String name, String surname1, String surname2, String birthdate,
            String sex, String country,
            String town, String ethnic, String blood, String eye, String mail);

    void changePassword(String mail, String passwordOld, String passwordNew) throws LoginException;

    void recoverPassword(String mail, String passwordNew) throws LoginException;

    Set<DTOService> obtainOwnServices(String mail, String userType);

    void unenrollUserFromService(String mail, Long id, String serviceType);

    Set<DTOChat> listUserChats(String mail);

    List<DTOMessage> listMessagesChat(String mail, long idChat);

    DTOChat createChat(DTOChat dtoChat);

    String test();

    DTOChat getChat(String mail1, String mail2);

    void sendMessage(String mail, long idChat, DTOMessage dtoMessage);

    // Se llama desde el ServiceService
    Boolean userAlreadyEnrolledLodge(String mail, Long id);

    Boolean userAlreadyEnrolledEducation(String mail, Long id);

    Boolean userAlreadyEnrolledDonation(String mail, Long id);

    Boolean userAlreadyEnrolledJob(String mail, Long id);

    void createReport(DTOReport dtoReport);

    Set<DTOReport> listReports();

    DTOReport getReport(Long id);

    void createLink(DTOLink dtoLink);

    Set<DTOLink> listLinks();

    void modifyLink(DTOLink dtoLink);

    void deleteLink(Long id);

}
