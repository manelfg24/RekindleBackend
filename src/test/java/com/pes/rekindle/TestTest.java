
package com.pes.rekindle;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Time;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.google.gson.Gson;
import com.pes.RekindleApplication;
import com.pes.rekindle.controllers.UserController;
import com.pes.rekindle.dto.DTODonation;
import com.pes.rekindle.dto.DTODonationEnrollment;
import com.pes.rekindle.dto.DTOService;
import com.pes.rekindle.dto.DTOUser;
import com.pes.rekindle.entities.Donation;
import com.pes.rekindle.entities.DonationEnrollment;
import com.pes.rekindle.entities.Link;
import com.pes.rekindle.entities.Refugee;
import com.pes.rekindle.entities.Volunteer;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RekindleApplication.class)
@ContextConfiguration(classes = {
        ServiceConfig.class,
        RepositoryConfig.class,
        EntityConfig.class
})
@DataJpaTest
@AutoConfigureMockMvc
@Sql(scripts = "classpath:database-init/insertest.sql")
public class TestTest {

    @Autowired
    private MockMvc mockMvc;

    // @InjectMocks
    @Autowired
    private UserController controller;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        this.mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setMessageConverters(getJackson()).build();
    }

    public MappingJackson2HttpMessageConverter getJackson() {
        return new MappingJackson2HttpMessageConverter();
    }
    
    @Before
    public void ini() throws Exception {
        DTODonation dtoDonation = new DTODonation();
        dtoDonation.setId(0);
        dtoDonation.setServiceType("Donation");
        dtoDonation.setName("Donacion de ropa");
        dtoDonation.setVolunteer("mailAlex");
        dtoDonation.setPhoneNumber(93427512);
        dtoDonation.setAdress("adress donation cero");
        dtoDonation.setDescription("Ropa para sucias cucarachas");
        dtoDonation.setStartTime(new Time(10, 0, 0));
        dtoDonation.setEndTime(new Time(11, 0, 0));
        dtoDonation.setPlaces(5);
        
        Gson gson = new Gson();
        String json = gson.toJson(dtoDonation);
        this.mockMvc
        		.perform(post("/donaciones").contentType(MediaType.APPLICATION_JSON_UTF8)
        				.content(json))
        		.andExpect(status().isOk()); 
    }

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }
    
    @Test
    public void createDonationEnrollment() throws Exception {
        DTODonationEnrollment dtoDonationEnrollment = new DTODonationEnrollment();
        
        DTOService dtoDonation = new DTOService();
        dtoDonation.setId(1);
        dtoDonation.setServiceType("Donation");
        dtoDonation.setName("Donacion de ropa");
        dtoDonation.setVolunteer("mailAlex");
        dtoDonation.setPhoneNumber(93427512);
        dtoDonation.setAdress("adress donation cero");
        dtoDonation.setDescription("Ropa para sucias cucarachas");
        
        dtoDonationEnrollment.setDonation(dtoDonation);        
        dtoDonationEnrollment.setRefugeeMail("mailFelipe");
        dtoDonationEnrollment.setMotive("Ando recorto de harapos");
        
        Gson gson = new Gson();
        String json = gson.toJson(dtoDonationEnrollment);
        
        this.mockMvc
	        .perform(post("/solicituddonacion").contentType(MediaType.APPLICATION_JSON_UTF8)
	                .content(json))
	        .andDo(print())
	        .andExpect(status().isNotFound());
    }
}