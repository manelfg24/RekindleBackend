
package com.pes.rekindle.services;

import java.util.Map;
import java.util.Set;

import com.pes.rekindle.dto.DTODonation;
import com.pes.rekindle.dto.DTOEducation;
import com.pes.rekindle.dto.DTOJob;
import com.pes.rekindle.dto.DTOLodge;
import com.pes.rekindle.entities.Donation;
import com.pes.rekindle.entities.Education;
import com.pes.rekindle.entities.Job;
import com.pes.rekindle.entities.Lodge;

public interface ServiceService {

    void createLodge(DTOLodge lodge);

    Map<Integer, Set<Object>> listServices();

    void createDonation(DTODonation donation);

    void createEducation(DTOEducation education);

    void createJob(DTOJob job);

    Object infoService(Long id, char serviceType);

    Boolean deleteService(long id, char serviceType);

    void deleteLodge(Long id);

    Lodge infoLodge(Long id);

    Education infoEducation(Long id);

    Donation infoDonation(Long id);

    Job infoJob(Long id);

    void modifyDonation(long id, DTODonation donation);

    void modifyLodge(long id, DTOLodge lodge);

    void modifyEducation(long id, DTOEducation education);

    void modifyJob(long id, DTOJob job);

}
