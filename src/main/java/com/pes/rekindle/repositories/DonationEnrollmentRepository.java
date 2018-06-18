
package com.pes.rekindle.repositories;

import java.util.ArrayList;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.repository.Repository;

import com.pes.rekindle.entities.DonationEnrollment;

@Transactional
public interface DonationEnrollmentRepository extends Repository<DonationEnrollment, String> {

    void save(DonationEnrollment donationEnrollment);

    ArrayList<DonationEnrollment> findByRequestStatus(String status);

    Optional<DonationEnrollment> findOptionalByRefugeeMailAndDonationId(String refugeeMail,
            Long donationId);

    DonationEnrollment findByRefugeeMailAndDonationId(String refugeeMail, Long donationId);

    Optional<DonationEnrollment> findOptionalByDonationIdAndRefugeeMail(Long idService,
            String refugeeMail);
}
