
package com.pes.rekindle;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.google.gson.Gson;
import com.pes.RekindleApplication;
import com.pes.rekindle.controllers.UserController;
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
@Sql(scripts = "classpath:database-init/inserts.sql")
public class UserControllerTest {

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

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    public void createVolunteerTest() throws Exception {
        Volunteer volunteer = new Volunteer();
        volunteer.setMail("test@gmail.com");
        volunteer.setName("Test");
        volunteer.setPassword("1234");
        volunteer.setSurname1("Force");
        Gson gson = new Gson();
        String json = gson.toJson(volunteer);
        this.mockMvc
                .perform(post("/voluntarios").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(json))
                .andExpect(status().isOk());
    }

    @Test
    public void volunteerAlreadyExistsShouldReturnConflictStatus() throws Exception {
        Volunteer volunteer = new Volunteer();
        volunteer.setMail("roger@gmail.com");
        volunteer.setName("Test");
        volunteer.setPassword("1234");
        volunteer.setSurname1("Force");
        Gson gson = new Gson();
        String json = gson.toJson(volunteer);
        this.mockMvc
                .perform(post("/voluntarios").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(json))
                .andExpect(status().isConflict());
    }

    @Test
    public void createRefugeeTest() throws Exception {
        Refugee refugee = new Refugee();
        refugee.setMail("test@gmail.com");
        refugee.setName("Test");
        refugee.setPassword("1234");
        refugee.setSurname1("Force");
        Gson gson = new Gson();
        String json = gson.toJson(refugee);
        this.mockMvc
                .perform(post("/refugiados").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(json))
                .andExpect(status().isOk());
    }

    @Test
    public void refugeeAlreadyExistsShouldReturnConflictStatusTest() throws Exception {
        Refugee refugee = new Refugee();
        refugee.setMail("felipe@gmail.com");
        refugee.setName("Test");
        refugee.setPassword("1234");
        refugee.setSurname1("Force");
        Gson gson = new Gson();
        String json = gson.toJson(refugee);
        this.mockMvc
                .perform(post("/refugiados").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(json))
                .andExpect(status().isConflict());
    }

    @Test
    public void userLogInTest() throws Exception {
        this.mockMvc
                .perform(post("/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .content("mail=alex@gmail.com&password=1234"))
                .andExpect(status().isOk());
    }

    public void nonExistentUserLoginShouldReturnBadRequestTest() throws Exception {
        this.mockMvc
                .perform(post("/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .content("mail=test@gmail.com&password=1234"))
                .andExpect(status().isBadRequest());
    }

    public void changeUserPasswordTest() throws Exception {
        MvcResult result = mockMvc
                .perform(put("/cambiarPassword/roger@gmail.com")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .content("passwordOld=1234&passwordNew=12345"))
                .andReturn();

        result = mockMvc
                .perform(get("/voluntarios/roger@gmail.com"))
                .andReturn();
        Volunteer volunteer = new Volunteer();
        volunteer.setMail("roger@gmail.com");
        volunteer.setName("Test");
        volunteer.setPassword("12345");
        volunteer.setSurname1("Force");
        Gson gson = new Gson();
        String json = gson.toJson(volunteer);
        System.out.println(result.getResponse());
        System.out.println("--------------------------------------------------------------");
        assertEquals(result.getResponse(), "aleixdios");
    }

}
