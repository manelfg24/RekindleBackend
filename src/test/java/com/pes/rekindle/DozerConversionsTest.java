
package com.pes.rekindle;

import static org.junit.Assert.assertEquals;

import org.dozer.DozerBeanMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pes.rekindle.dto.DTOUser;
import com.pes.rekindle.entities.Refugee;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
public class DozerConversionsTest {

    DozerBeanMapper mapper;

    @Before
    public void before() throws Exception {
        mapper = new DozerBeanMapper();
    }

    @Test
    public void conversionRefugeeToDTORefugee() {
        Refugee refugee = new Refugee();
        refugee.setName("alex");
        DTOUser dtoRefugee = mapper.map(refugee, DTOUser.class);

        assertEquals("alex", dtoRefugee.getName());

    }
}
