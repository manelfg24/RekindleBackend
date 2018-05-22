
package com.pes.rekindle.services;

import java.sql.Date;
import java.util.Set;

import com.pes.rekindle.dto.DTOChat;
import com.pes.rekindle.dto.DTOMessage;
import com.pes.rekindle.dto.DTOService;
import com.pes.rekindle.dto.DTOUser;
import com.pes.rekindle.entities.Volunteer;

public interface UserService {

    // boolean logIn(String user, String password);
    Object logIn(String user, String password);

    void createVolunteer(Volunteer volunteer) throws Exception;

    void createRefugee(DTOUser refugee) throws Exception;

    DTOUser logInVolunteer(String mail, String password);

    DTOUser logInRefugee(String mail, String password);

    Boolean changePasswordVolunteer(String mail, String password, String newPassword);

    Boolean changePasswordRefugee(String mail, String password, String newPassword);

    void modifyProfileVolunteer(DTOUser dtoUser);

    void modifyProfileRefugee(DTOUser refugee);

    DTOUser infoVolunteer(String mail);

    DTOUser infoRefugee(String mail);

    DTOUser exists(String mail, String password);

    void enrollRefugeeLodge(String refugeeMail, long serviceId);

    void enrollRefugeeEducation(String refugeeMail, long serviceId);

    void enrollRefugeeJob(String refugeeMail, long serviceId);

    void enrollRefugeeDonation(String refugeeMail, long serviceId);

    Set<DTOUser> findRefugee(String name, String surname1, String surname2, Date birthdate,
            String sex, String country,
            String town, String ethnic, String blood, String eye);

    boolean changePassword(String mail, String passwordOld, String passwordNew);

    boolean recoverPassword(String mail, String passwordNew);

    Set<DTOService> obtainOwnServices(String mail, String userType);

    Set<DTOChat> listUserChats(String mail);

    Set<DTOMessage> listMessagesChat(String mail, long idChat);

    DTOUser newChat(String mailUser1, String mailUser2);

    DTOChat createChat(DTOChat dtoChat);

	String test();

	DTOChat getChat(String mail1, String mail2);

	void sendMessage(String mail, long idChat, DTOMessage dtoMessage);

}
