
package com.pes.rekindle.services;

import java.sql.Date;
import java.util.Map;
import java.util.Set;

import org.springframework.data.util.Pair;

import com.pes.rekindle.dto.DTOChat;
import com.pes.rekindle.dto.DTOService;
import com.pes.rekindle.dto.DTOUser;
import com.pes.rekindle.entities.Chat;
import com.pes.rekindle.entities.Lodge;
import com.pes.rekindle.entities.Volunteer;

public interface UserService {

    // boolean logIn(String user, String password);
    Object logIn(String user, String password);

    Volunteer createVolunteer(Volunteer volunteer) throws Exception;

    DTOUser createRefugee(DTOUser refugee) throws Exception;

    Volunteer logInVolunteer(String mail, String password);

    DTOUser logInRefugee(String mail, String password);

    Boolean changePasswordVolunteer(String mail, String password, String newPassword);

    Boolean changePasswordRefugee(String mail, String password, String newPassword);

    void modifyProfileVolunteer(String mail, String name, String surname1, String surname2);

    void modifyProfileRefugee(DTOUser refugee);

    Volunteer infoVolunteer(String mail);

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

	DTOUser newChat(String mailUser1, String mailUser2);

}
