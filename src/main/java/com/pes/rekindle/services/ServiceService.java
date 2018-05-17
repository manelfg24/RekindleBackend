
package com.pes.rekindle.services;

import java.util.Set;

import com.pes.rekindle.dto.DTODonation;
import com.pes.rekindle.dto.DTOEducation;
import com.pes.rekindle.dto.DTOJob;
import com.pes.rekindle.dto.DTOLodge;
import com.pes.rekindle.dto.DTOService;

public interface ServiceService {

    void createLodge(DTOLodge lodge);

    Set<DTOService> listServices();

    void createDonation(DTODonation donation);

    void createEducation(DTOEducation education);

    void createJob(DTOJob job);

    Boolean deleteService(long id, char serviceType);

    void deleteLodge(Long id);

    DTOLodge infoLodge(Long id);

    DTOEducation infoEducation(Long id);

    DTODonation infoDonation(Long id);

    DTOJob infoJob(Long id);

    void modifyDonation(long id, DTODonation donation);

    void modifyLodge(long id, DTOLodge lodge);

    void modifyEducation(long id, DTOEducation education);

    void modifyJob(long id, DTOJob job);

}
