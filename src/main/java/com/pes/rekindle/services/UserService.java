
package com.pes.rekindle.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Set;

import org.springframework.data.util.Pair;

import com.pes.rekindle.entities.Lodge;
import com.pes.rekindle.entities.Refugee;
import com.pes.rekindle.entities.Volunteer;

public interface UserService {

    // boolean logIn(String user, String password);
    Object logIn(String user, String password);

    Volunteer createVolunteer(String mail, String password, String name, String surname1,
            String surname2) throws Exception;

    Refugee createRefugee(String mail, String password, String name, String surname1,
            String surname2,
            Integer phoneNumber, Date birthdate, String sex, String country, String town,
            String ethnic,
            String bloodType, String eyeColor, String biography) throws Exception;

    Volunteer logInVolunteer(String mail, String password);

    Refugee logInRefugee(String mail, String password);

    Boolean changePasswordVolunteer(String mail, String password, String newPassword);

    Boolean changePasswordRefugee(String mail, String password, String newPassword);

    void modifyProfileVolunteer(String mail, String name, String surname1, String surname2);

    void modifyProfileRefugee(String mail, String name, String surname1, String surname2,
            Integer phonNumber, Date birthdate, String sex, String country, String town,
            String ethnic, String bloodType, String eyeColor, String string);

    Volunteer infoVolunteer(String mail);

    Refugee infoRefugee(String mail);

    Pair<Integer, Object> exists(String mail, String password);

    void enrollRefugeeLodge(String refugeeMail, long serviceId);

    void enrollRefugeeEducation(String refugeeMail, long serviceId);

    void enrollRefugeeJob(String refugeeMail, long serviceId);

    void enrollRefugeeDonation(String refugeeMail, long serviceId);

    Set<Lodge> refugeeLodges(String mail);

	Set<Refugee> findRefugee(String name, String surname1, String surname2, Date birthdate, String sex, String country,
			String town, String ethnic, String blood, String eye);


	boolean changePassword(String mail, String passwordOld, String passwordNew);

	boolean recoverPassword(String mail, String passwordNew);

}
