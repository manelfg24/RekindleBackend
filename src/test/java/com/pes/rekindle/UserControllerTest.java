
package com.pes.rekindle;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.google.gson.Gson;
import com.pes.rekindle.controllers.UserController;
import com.pes.rekindle.entities.Volunteer;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
@AutoConfigureMockMvc
@EnableWebMvc
@Sql(scripts = "classpath:database-init/inserts.sql")
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserController controller;

    @Test
    public void contexLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    public void userShouldCreateVolunteer() throws Exception {
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
    public void userAlreadyExistsConflict() throws Exception {
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

}
