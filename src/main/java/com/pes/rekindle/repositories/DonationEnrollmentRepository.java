
package com.pes.rekindle.repositories;

import java.util.ArrayList;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.pes.rekindle.entities.Admin;
import com.pes.rekindle.entities.DonationEnrollment;

@Transactional
public interface DonationEnrollmentRepository extends Repository<DonationEnrollment, String> {

	void save(DonationEnrollment donationEnrollment);

	ArrayList<DonationEnrollment> findByRequestStatus(String status);

	Optional<DonationEnrollment> findOptionalByRefugeeMailAndDonationId(String refugeeMail, Long donationId);

	DonationEnrollment findByRefugeeMailAndDonationId(String refugeeMail, Long donationId);

	Optional<DonationEnrollment> findOptionalByDonationIdAndRefugeeMail(long idService, String refugeeMail);
}
