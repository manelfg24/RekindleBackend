
package com.pes.rekindle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.pes.rekindle.entities.Volunteer;
import com.pes.rekindle.repositories.VolunteerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
@Sql(scripts = "classpath:database-init/inserts.sql")
public class VolunteerRepositoryTest {

    @Autowired
    private VolunteerRepository volunteerRepository;

    private Volunteer volunteer;

    /*
     * @Before public void init() { volunteer = new Volunteer();
     * volunteer.setMail("alex@gmail.com"); volunteer.setPassword("1234");
     * volunteer.setName("alex"); volunteer.setSurname1("sanchez"); }
     */

    @Test
    public void saveVolunteerTest() {
        Volunteer findVolunteer = volunteerRepository.findByMail("falip@gmail.com");
        assertEquals("falip@gmail.com", findVolunteer.getMail());
        assertEquals("1234", findVolunteer.getPassword());
        assertEquals("falip", findVolunteer.getName());
        assertEquals("sanchon", findVolunteer.getSurname1());
    }

    @Test
    public void findNotExistingVolunteer() {
        Volunteer volunteer = volunteerRepository.findByMail("sanchon@");
        assertNull(volunteer);
    }

    @Test
    public void findVolunteerByMailAndPasswordTest() {
        Volunteer volunteer = volunteerRepository.findByMailAndPassword("alex@gmail.com", "1234");
        assertEquals("alex@gmail.com", volunteer.getMail());
        assertEquals("1234", volunteer.getPassword());
        assertEquals("alex", volunteer.getName());
        assertEquals("sanchez", volunteer.getSurname1());
    }
}
