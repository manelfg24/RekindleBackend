
package com.pes.rekindle;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.pes.RekindleApplication;
import com.pes.rekindle.controllers.UserController;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RekindleApplication.class)
@ContextConfiguration(classes = {
        ServiceConfig.class,
        RepositoryConfig.class,
        EntityConfig.class
})
@DataJpaTest
@AutoConfigureMockMvc
// @Sql(scripts = "classpath:database-init/inserts.sql")
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
    public void contexLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    /*
     * // Tests User Controller create Volunteer
     * @Test public void userShouldCreateVolunteer() throws Exception { Volunteer volunteer = new
     * Volunteer(); volunteer.setMail("test@gmail.com"); volunteer.setName("Test");
     * volunteer.setPassword("1234"); volunteer.setSurname1("Force"); Gson gson = new Gson(); String
     * json = gson.toJson(volunteer); this.mockMvc
     * .perform(post("/voluntarios").contentType(MediaType.APPLICATION_JSON_UTF8) .content(json))
     * .andExpect(status().isOk()); }
     * @Test public void userAlreadyExistsConflict() throws Exception { Volunteer volunteer = new
     * Volunteer(); volunteer.setMail("roger@gmail.com"); volunteer.setName("Test");
     * volunteer.setPassword("1234"); volunteer.setSurname1("Force"); Gson gson = new Gson(); String
     * json = gson.toJson(volunteer); this.mockMvc
     * .perform(post("/voluntarios").contentType(MediaType.APPLICATION_JSON_UTF8) .content(json))
     * .andExpect(status().isConflict()); }
     */

    /*
     * //Tests user controller get Volunteer
     * @Test public void userAlreadyExistsConflict() throws Exception { String s =
     * "roger@gmail.com"; MvcResult result = mockMvc .perform(get("/voluntarios/{mail}",
     * "roger@gmail.com")) .andReturn(); assertEquals(s, result.getResponse().getContentAsString());
     * }
     */

    @Test
    public void test() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(
                MockMvcRequestBuilders.get("/test")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andReturn();

        System.out.println("-----");
        System.out.println(mvcResult.getResponse().getStatus());
        System.out.println(mvcResult.getResponse().getContentAsString());
        System.out.println("-----");
    }

    /*
     * @Test public void test() throws Exception { this.mockMvc .perform(get("/test/{id}",
     * "Holaxd")) .andExpect(status().isOk()) .andExpect(content().string("Hola")) ; }
     */
}
