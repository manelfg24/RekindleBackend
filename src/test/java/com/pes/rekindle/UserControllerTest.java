
package com.pes.rekindle;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.google.gson.Gson;
import com.pes.RekindleApplication;
import com.pes.rekindle.controllers.UserController;
import com.pes.rekindle.entities.Volunteer;
import com.pes.rekindle.services.UserService;
import com.pes.rekindle.services.UserServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(
		classes = RekindleApplication.class
		)
@DataJpaTest
@AutoConfigureMockMvc
@EnableWebMvc
@Sql(scripts = "classpath:database-init/inserts.sql")
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private WebApplicationContext wac;

    @InjectMocks
    private UserController controller;
    
    @Mock
    private UserService userService;
    
    @Mock
    private UserServiceImpl userServiceImpl;
    
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void contexLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    /*
    // Tests User Controller create Volunteer
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

	*/
    
    /*
    //Tests user controller get Volunteer 
    @Test
    public void userAlreadyExistsConflict() throws Exception {
    	String s = "roger@gmail.com";

        MvcResult result = mockMvc
                .perform(get("/voluntarios/{mail}", "roger@gmail.com"))
                	.andReturn();
        assertEquals(s, result.getResponse().getContentAsString());

    }
    */
    
    @Test
    public void test() throws Exception {
    	MvcResult mvcResult = this.mockMvc.perform(
    			MockMvcRequestBuilders.get("/test")
    			.accept(MediaType.APPLICATION_JSON)
    	).andDo(print()).andReturn();
    	
    	System.out.println("-----");
    	System.out.println(mvcResult.getResponse().getStatus());
    	System.out.println(mvcResult.getResponse().getContentAsString());
    	System.out.println("-----");
    }
    
    /*
    @Test
    public void test() throws Exception {
        this.mockMvc
        	.perform(get("/test/{id}", "Holaxd"))
        	.andExpect(status().isOk())
        	.andExpect(content().string("Hola"))
        ;
    }
    */
}
