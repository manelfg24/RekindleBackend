
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
import java.text.SimpleDateFormat;
import java.util.Date;

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
import com.pes.rekindle.controllers.ServiceController;
import com.pes.rekindle.dto.DTODonation;
import com.pes.rekindle.dto.DTOEducation;
import com.pes.rekindle.dto.DTOJob;
import com.pes.rekindle.dto.DTOLodge;
import com.pes.rekindle.entities.Link;
import com.pes.rekindle.entities.Lodge;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RekindleApplication.class)
@ContextConfiguration(classes = {
        ServiceConfig.class,
        RepositoryConfig.class,
        EntityConfig.class
})
@DataJpaTest
@AutoConfigureMockMvc
@Sql(scripts = "classpath:database-init/inserts.sql")
public class ServiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // @InjectMocks
    @Autowired
    private ServiceController controller;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        this.mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setMessageConverters(getJackson()).build();
    }

    public MappingJackson2HttpMessageConverter getJackson() {
        return new MappingJackson2HttpMessageConverter();
    }

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }
    
    @Test 
    public void createLodgeTest() throws Exception {
    	DTOLodge dtoLodge = new DTOLodge();
    	dtoLodge.setServiceType("Lodge");
    	dtoLodge.setName("Casa Pepe");
    	dtoLodge.setVolunteer("roger@gmail.com");
    	dtoLodge.setPhoneNumber(936666666);
    	dtoLodge.setAdress("Balmes");  
    	dtoLodge.setPlaces(2);
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dateLimit = formatter.parse("2018-07-23");
            dtoLodge.setDateLimit(dateLimit);
        } catch (Exception e) {

        }
        dtoLodge.setDescription("Alojamiento para dos personas");
        Gson gson = new Gson();
        String json = gson.toJson(dtoLodge);
        this.mockMvc
        		.perform(post("/alojamientos").contentType(MediaType.APPLICATION_JSON_UTF8)
        				.content(json))
        		.andExpect(status().isOk());   
    }
    
   /* @Test 
    public void createDonationTest() throws Exception {
    	DTODonation dtoDonation = new DTODonation();
    	dtoDonation.setServiceType("Donation");
    	dtoDonation.setName("Donación de ropa");
    	dtoDonation.setVolunteer("roger@gmail.com");
    	dtoDonation.setPhoneNumber(936666666);
    	dtoDonation.setAdress("Balmes");
    	dtoDonation.setPlaces(2);
    	dtoDonation.setDescription("Donación de ropa");
    	Time t = new Time(10, 0, 0);
    	Time t2 = new Time(11, 0, 0);
    	dtoDonation.setStartTime(t);
    	dtoDonation.setEndTime(t2);
        Gson gson = new Gson();
        String json = gson.toJson(dtoDonation);
        this.mockMvc
        		.perform(post("/donaciones").contentType(MediaType.APPLICATION_JSON_UTF8)
        				.content(json))
        		.andExpect(status().isOk());   
    }*/
    
    
    @Test
    public void createEducationTest() throws Exception {
    	DTOEducation dtoEdu = new DTOEducation();
    	dtoEdu.setServiceType("Education");
    	dtoEdu.setName("Clases de Español de España");
    	dtoEdu.setVolunteer("roger@gmail.com");
    	dtoEdu.setPhoneNumber(936666666);
    	dtoEdu.setAdress("Balmes");
    	dtoEdu.setAmbit("Idioma");
       	dtoEdu.setRequirements("Nada");
    	dtoEdu.setSchedule("Tardes");
    	dtoEdu.setPlaces(2);
    	dtoEdu.setPrice(0);
    	dtoEdu.setDescription("Clases de español basico por M.Rjoy");
    	Gson gson = new Gson();
        String json = gson.toJson(dtoEdu);
        this.mockMvc
        		.perform(post("/cursos").contentType(MediaType.APPLICATION_JSON_UTF8)
        				.content(json))
        		.andExpect(status().isOk());   
    }
    
    @Test
    public void createJobTest() throws Exception {
    	DTOJob dtoJob = new DTOJob();
    	dtoJob.setServiceType("Job");
    	dtoJob.setName("Carpintero");
    	dtoJob.setVolunteer("roger@gmail.com");
    	dtoJob.setPhoneNumber(936666666);
    	dtoJob.setAdress("Balmes");
    	dtoJob.setCharge("Becario");
    	dtoJob.setRequirements("Nada");
    	dtoJob.setHoursDay(4);
    	dtoJob.setHoursWeek(20);
    	dtoJob.setContractDuration(5);
    	dtoJob.setSalary(400);
    	dtoJob.setPlaces(2);
    	dtoJob.setDescription("Trabajo de aprendiz de carpintero");
    	Gson gson = new Gson();
        String json = gson.toJson(dtoJob);
        this.mockMvc
        		.perform(post("/empleos").contentType(MediaType.APPLICATION_JSON_UTF8)
        				.content(json))
        		.andExpect(status().isOk());   
    }
    
    @Test
    public void listServicesTest() throws Exception {
        this.mockMvc
                .perform(get("/servicios"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.[0].id").value(0))
                .andExpect(jsonPath("$.[1].id").value(0))
                .andExpect(jsonPath("$.[0].serviceType").value("e"))
                .andExpect(jsonPath("$.[1].serviceType").value("d"))
                .andExpect(jsonPath("$.[0].name").value("NombreEdu"))
                .andExpect(jsonPath("$.[0].volunteer").value("roger@gmail.com"));
    }

}
