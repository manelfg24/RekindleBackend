
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
import com.pes.rekindle.entities.LodgeEnrollment;

@Transactional
public interface LodgeEnrollmentRepository extends Repository<LodgeEnrollment, String> {

	Optional<LodgeEnrollment> findOptionalByLodgeIdAndRefugeeMail(long lodgeId, String refugeeMail);

	void save(LodgeEnrollment lodgeEnrollment);

}
