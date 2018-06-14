
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
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.junit.Before;
import org.junit.BeforeClass;
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
import org.springframework.test.web.servlet.ResultHandler;
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
  /*@Before
    public void ini() throws Exception {
	  
	      /*Crera servicios de ALOJAMIENTO
    	DTOLodge dtoLodge = new DTOLodge();
    	dtoLodge.setServiceType("Lodge");
    	dtoLodge.setName("Casa Pepe");
    	dtoLodge.setVolunteer("mailRoger");
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
        				.content(json));  
        
        
        DTOLodge dtoLodge1 = new DTOLodge();
    	dtoLodge1.setServiceType("Lodge");
    	dtoLodge1.setName("Casa Pepe2");
    	dtoLodge1.setVolunteer("mailAlex");
    	dtoLodge1.setPhoneNumber(936666666);
    	dtoLodge1.setAdress("Balmes");  
    	dtoLodge1.setPlaces(2);
    	SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dateLimit = formatter1.parse("2018-07-23");
            dtoLodge1.setDateLimit(dateLimit);
        } catch (Exception e) {

        }
        dtoLodge1.setDescription("Alojamiento para dos personas");
        Gson gson2 = new Gson();
        String json2 = gson2.toJson(dtoLodge1);
        this.mockMvc
        		.perform(post("/alojamientos").contentType(MediaType.APPLICATION_JSON_UTF8)
        				.content(json2));
        
        /*Crear servicios de EDUCATION
        DTOEducation dtoEdu = new DTOEducation();
    	dtoEdu.setServiceType("Education");
    	dtoEdu.setName("Clases de Español de España");
    	dtoEdu.setVolunteer("mailRoger");
    	dtoEdu.setPhoneNumber(936666666);
    	dtoEdu.setAdress("Balmes");
    	dtoEdu.setAmbit("Idioma");
       	dtoEdu.setRequirements("Nada");
    	dtoEdu.setSchedule("Tardes");
    	dtoEdu.setPlaces(2);
    	dtoEdu.setPrice(0);
    	dtoEdu.setDescription("Clases de español basico por M.Rjoy");
    	Gson gsonE = new Gson();
        String jsonE = gsonE.toJson(dtoEdu);
        this.mockMvc
        		.perform(post("/cursos").contentType(MediaType.APPLICATION_JSON_UTF8)
        				.content(jsonE));        
    }
        
    */
    @Test 
    public void createLodgeTest() throws Exception {
    	DTOLodge dtoLodge = new DTOLodge();
    	dtoLodge.setServiceType("Lodge");
    	dtoLodge.setName("Casa Pepe");
    	dtoLodge.setVolunteer("mailRoger");
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
        		//.andDo(print())
        		.andExpect(status().isOk());   
    }
    
    @Test 
    public void createDonationTest() throws Exception {
    	DTODonation dtoDonation = new DTODonation();
    	dtoDonation.setServiceType("Donation");
    	dtoDonation.setName("Donación de ropa");
    	dtoDonation.setVolunteer("mailRoger");
    	dtoDonation.setPhoneNumber(936666666);
    	dtoDonation.setAdress("Balmes");
    	dtoDonation.setPlaces(2);
    	dtoDonation.setDescription("Donación de ropa");
    	LocalTime starTime = LocalTime.parse("10:22:11", DateTimeFormatter.ofPattern("HH:mm:ss"));
    	LocalTime endTime = LocalTime.parse("11:22:11", DateTimeFormatter.ofPattern("HH:mm:ss"));
    	dtoDonation.setStartTime(starTime);
    	dtoDonation.setEndTime(endTime);
    	dtoDonation.setEnded(true);
        Gson gson = new Gson();
        String json = gson.toJson(dtoDonation);
        this.mockMvc
        		.perform(post("/donaciones").contentType(MediaType.APPLICATION_JSON_UTF8)
        				.content(json))
        		.andDo(print())
        		.andExpect(status().isOk());   
    }
    
    
    @Test
    public void createEducationTest() throws Exception {
    	DTOEducation dtoEdu = new DTOEducation();
    	dtoEdu.setServiceType("Education");
    	dtoEdu.setName("Clases de Español de España");
    	dtoEdu.setVolunteer("mailRoger");
    	dtoEdu.setPhoneNumber(936666666);
    	dtoEdu.setAdress("Balmes");
    	dtoEdu.setAmbit("Idioma");
       	dtoEdu.setRequirements("Nada");
    	dtoEdu.setSchedule("Tardes");
    	dtoEdu.setPlaces(2);
    	dtoEdu.setPrice(0);
    	dtoEdu.setDescription("Clases de español basico por M.Rjoy");
    	Gson gsonE = new Gson();
        String jsonE = gsonE.toJson(dtoEdu);
        this.mockMvc
        		.perform(post("/cursos").contentType(MediaType.APPLICATION_JSON_UTF8)
        				.content(jsonE))
        		.andExpect(status().isOk());   
    }
    
    @Test
    public void createJobTest() throws Exception {
    	DTOJob dtoJob = new DTOJob();
    	dtoJob.setServiceType("Job");
    	dtoJob.setName("Carpintero");
    	dtoJob.setVolunteer("mailRoger");
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
    	Gson gsonJ = new Gson();
        String jsonJ = gsonJ.toJson(dtoJob);
        this.mockMvc
        		.perform(post("/empleos").contentType(MediaType.APPLICATION_JSON_UTF8)
        				.content(jsonJ))
        		.andExpect(status().isOk());   
    }
    
    @Test
    public void listServicesTest() throws Exception {
        this.mockMvc
                .perform(get("/servicios"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.[0].id").value(1))
                .andExpect(jsonPath("$.[1].id").value(2))
                .andExpect(jsonPath("$.[0].serviceType").value("Donation"))
                .andExpect(jsonPath("$.[1].serviceType").value("Donation"))
                .andExpect(jsonPath("$.[0].name").value("Donation numero cero"))
                .andExpect(jsonPath("$.[1].name").value("Donation numero uno"))
                .andExpect(jsonPath("$.[0].volunteer").value("mailAlex"));
    }
    
    @Test
    public void infoLodgeTest() throws Exception {
        this.mockMvc
                .perform(get("/alojamientos/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.serviceType").value("Lodge"))
                .andExpect(jsonPath("$.name").value("Casa Pepe10"))
                .andExpect(jsonPath("$.volunteer").value("mailRoger"));
    }
   
    @Test
    public void infoEducationTest() throws Exception {
        this.mockMvc
                .perform(get("/cursos/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.serviceType").value("Education"))
                .andExpect(jsonPath("$.name").value("NombreEdu cero"))
                .andExpect(jsonPath("$.volunteer").value("mailAlex"));
    }
    
    @Test
    public void infoDonationTest() throws Exception {
        this.mockMvc
                .perform(get("/donaciones/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.serviceType").value("Donation"))
                .andExpect(jsonPath("$.name").value("Donation numero cero"))
                .andExpect(jsonPath("$.volunteer").value("mailAlex"));
    }
    
    @Test
    public void infoJobTest() throws Exception {
        this.mockMvc
                .perform(get("/empleos/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.serviceType").value("Job"))
                .andExpect(jsonPath("$.name").value("Nombre Job cero"))
                .andExpect(jsonPath("$.volunteer").value("mailRoger"));
    }
    
    @Test
    public void obtainOwnServicesTest() throws Exception {
        this.mockMvc
                .perform(get("/servicios/{mail}/{tipo}", "mailRoger" , "Volunteer").param("ended", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].volunteer").value("mailRoger"))
        		.andExpect(jsonPath("$.[1].volunteer").value("mailRoger"))
        		.andExpect(jsonPath("$.[2].volunteer").value("mailRoger"))
        		.andExpect(jsonPath("$.[3].volunteer").value("mailRoger"))
        		.andExpect(jsonPath("$.[4].volunteer").value("mailRoger"));
    }

    @Test
    public void userAlreadyEnrolledTest() throws Exception {
        this.mockMvc
                .perform(get("/refugiados/{mail}/inscripciones/{id}/{tipo}", "mailRafael" , "1","Donation"))
                .andExpect(status().isOk());                
    }
    
    @Test
    public void deleteServiceTest() throws Exception {
        this.mockMvc
                .perform(delete("/servicios/{id}/{tipo}", "1","Donation"))
                .andExpect(status().isOk());                
    }
    
    @Test 
    public void modifyLodgeTest() throws Exception {
    	DTOLodge dtoLodge = new DTOLodge();
    	dtoLodge.setServiceType("Lodge");
    	dtoLodge.setName("Casa Pepe");
    	dtoLodge.setVolunteer("mailRoger");
    	dtoLodge.setPhoneNumber(931111333);
    	dtoLodge.setAdress("Balmes");  
    	dtoLodge.setPlaces(4);
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
        		.perform(put("/alojamientos/{id}", 1).contentType(MediaType.APPLICATION_JSON_UTF8)
        				.content(json))
        		.andExpect(status().isOk());   
    }
    
}
