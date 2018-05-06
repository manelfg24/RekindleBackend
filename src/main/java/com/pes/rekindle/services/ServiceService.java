
package com.pes.rekindle.services;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Set;

import com.pes.rekindle.entities.Donation;
import com.pes.rekindle.entities.Education;
import com.pes.rekindle.entities.Job;
import com.pes.rekindle.entities.Lodge;

public interface ServiceService {

    void createLodge(String name, String mail, Integer phoneNumber, String adress,
            Integer places, java.util.Date date, String description);

    Set<Object> listServices();

    void createDonation(String name, String mail, Integer phoneNumber, String adress,
            Integer places, Time startTime,
            Time endTime, String description);

    void createEducation(String name, String mail, Integer phoneNumber, String adress, String ambit,
            String requirements, String schedule, Integer places, Integer price,
            String description);

    void createJob(String name, String mail, Integer phoneNumber, String adress, String charge,
            String requirements,
            Double hoursDay, Double hoursWeek, Integer duration, Integer places, Double salaray,
            String description);

    Object infoService(Long id, char serviceType);

	Boolean deleteService(long id, char serviceType);

	void deleteLodge(Long id);

	Lodge infoLodge(Long id);

	Education infoEducation(Long id);

	Donation infoDonation(Long id);

	Job infoJob(Long id);

}
