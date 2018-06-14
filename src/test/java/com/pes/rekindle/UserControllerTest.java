
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
import com.pes.rekindle.dto.DTOUser;
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
        volunteer.setMail("mailRoger");
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
        refugee.setMail("mailFelipe");
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
                        .content("mail=mailAlex&password=1234"))
                .andExpect(status().isOk());
    }

    @Test
    public void nonExistentUserLoginShouldReturnBadRequestTest() throws Exception {
        this.mockMvc
                .perform(post("/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .content("mail=aleixputoamo&password=1234"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void changeUserPasswordTest() throws Exception {
        String mail = "mailRoger";

        this.mockMvc.perform(put("/cambiarPassword/{mail}.com", mail)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content("passwordOld=1234&passwordNew=12345")).andExpect(status().isOk());
    }

    @Test
    public void changeUserPasswordWithIncorrectPasswordShouldReturnKOTest() throws Exception {
        String mail = "mailRoger";

        this.mockMvc.perform(put("/cambiarPassword/{mail}.com", mail)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content("passwordOld=123&passwordNew=12345")).andExpect(status().isBadRequest());
    }

    @Test
    public void recoverUserPasswordTest() throws Exception {
        String mail = "mailRoger";

        this.mockMvc.perform(put("/recuperarPassword/{mail}.com", mail)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content("passwordNew=12345")).andExpect(status().isOk());
    }

    @Test
    public void modifyVolunteerTest() throws Exception {
        String mail = "mailRoger";
        DTOUser dtoVolunteer = new DTOUser();
        dtoVolunteer.setMail("mailRoger");
        dtoVolunteer.setSurname1("pocho");
        Gson gson = new Gson();
        String json = gson.toJson(dtoVolunteer);
        this.mockMvc.perform(put("/voluntarios/{mail}", mail)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json)).andExpect(status().isOk());
    }

    @Test
    public void modifyRefugeeTest() throws Exception {
        String mail = "mailFelipe";
        DTOUser dtoVolunteer = new DTOUser();
        dtoVolunteer.setMail("mailFelipe");
        dtoVolunteer.setEyeColor("blue");
        Gson gson = new Gson();
        String json = gson.toJson(dtoVolunteer);
        this.mockMvc.perform(put("/refugiados/{mail}", mail)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json)).andExpect(status().isOk());
    }

    @Test
    public void getVolunteerTest() throws Exception {
        String mail = "mailRoger";

        this.mockMvc
                .perform(get("/voluntarios/{mail}", mail))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mail").value("mailRoger"))
                .andExpect(jsonPath("$.userType").value("Volunteer"))
                .andExpect(jsonPath("$.surname1").value("poch"))
                .andExpect(jsonPath("$.surname2").value("alonso"));
    }

    @Test
    public void getNotExistentVolunteerShouldReturnNotFound() throws Exception {
        String mail = "mailNotExistent";

        this.mockMvc
                .perform(get("/voluntarios/{mail}", mail))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void getRefugeeTest() throws Exception {
        String mail = "mailFelipe";

        this.mockMvc
                .perform(get("/refugiados/{mail}", mail))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mail").value("mailFelipe"))
                .andExpect(jsonPath("$.userType").value("Refugee"))
                .andExpect(jsonPath("$.surname1").value("betancourt"))
                .andExpect(jsonPath("$.surname2").value("rodriguez"))
                .andExpect(jsonPath("$.phoneNumber").value(942342312))
                .andExpect(jsonPath("$.sex").value("Masculino"))
                .andExpect(jsonPath("$.country").value("Cuba"))
                .andExpect(jsonPath("$.town").value("La Havana"))
                .andExpect(jsonPath("$.ethnic").value("hispano"))
                .andExpect(jsonPath("$.bloodType").value("AB+"))
                .andExpect(jsonPath("$.eyeColor").value("Castaño"))
                .andExpect(jsonPath("$.biography").value("La biografia de Felipe"))
                .andExpect(jsonPath("$.photo").value("photo Felipe"));
    }

    @Test
    public void findRefugeeTest() throws Exception {
        this.mockMvc
                .perform(get("/refugiados")
                        .param("name", "felipe")
                        .param("surname1", "")
                        .param("surname2", "")
                        .param("sex", "")
                        .param("birthdate", "1890-01-01")
                        .param("country", "")
                        .param("town", "")
                        .param("ethnic", "")
                        .param("blood", "")
                        .param("eye", "")
                        .param("mail", ""))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].mail").value("mailFelipe"))
                .andExpect(jsonPath("$.[0].userType").value("Refugee"))
                .andExpect(jsonPath("$.[0].surname1").value("betancourt"))
                .andExpect(jsonPath("$.[0].surname2").value("rodriguez"))
                .andExpect(jsonPath("$.[0].phoneNumber").value(942342312))
                .andExpect(jsonPath("$.[0].sex").value("Masculino"))
                .andExpect(jsonPath("$.[0].country").value("Cuba"))
                .andExpect(jsonPath("$.[0].town").value("La Havana"))
                .andExpect(jsonPath("$.[0].ethnic").value("hispano"))
                .andExpect(jsonPath("$.[0].bloodType").value("AB+"))
                .andExpect(jsonPath("$.[0].eyeColor").value("Castaño"))
                .andExpect(jsonPath("$.[0].biography").value("La biografia de Felipe"))
                .andExpect(jsonPath("$.[0].photo").value("photo Felipe"));
    }
    
    @Test
    public void findRefugeeTest2() throws Exception {
        this.mockMvc
                .perform(get("/refugiados")
                		.param("name", "felipe")
                		.param("surname1", ""))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].mail").value("mailFelipe"))
                .andExpect(jsonPath("$.[0].userType").value("Refugee"))
                .andExpect(jsonPath("$.[0].surname1").value("betancourt"))
                .andExpect(jsonPath("$.[0].surname2").value("rodriguez"))
                .andExpect(jsonPath("$.[0].phoneNumber").value(942342312))
                .andExpect(jsonPath("$.[0].sex").value("Masculino"))
                .andExpect(jsonPath("$.[0].country").value("Cuba"))
                .andExpect(jsonPath("$.[0].town").value("La Havana"))
                .andExpect(jsonPath("$.[0].ethnic").value("hispano"))
                .andExpect(jsonPath("$.[0].bloodType").value("AB+"))
                .andExpect(jsonPath("$.[0].eyeColor").value("Castaño"))
                .andExpect(jsonPath("$.[0].biography").value("La biografia de Felipe"))
                .andExpect(jsonPath("$.[0].photo").value("photo Felipe"));
    }

    @Test
    public void getNotExistentRefugeeShouldReturnNotFound() throws Exception {
        String mail = "mailNotExistent";

        this.mockMvc
                .perform(get("/refugiados/{mail}", mail))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void createLinkTest() throws Exception {
        Link link = new Link();
        link.setType("Test");
        link.setUrl("www.test.com");
        link.setDescription("Link de prueba");
        Gson gson = new Gson();
        String json = gson.toJson(link);
        this.mockMvc
                .perform(post("/links").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(json))
                .andExpect(status().isOk());
    }

    @Test
    public void listLinksTest() throws Exception {
        this.mockMvc
                .perform(get("/links"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.[0].id").value(0))
                .andExpect(jsonPath("$.[0].type").value("Test"))
                .andExpect(jsonPath("$.[0].url").value("www.test.com"))
                .andExpect(jsonPath("$.[0].description").value("Link para testear"));
    }

    @Test
    public void modifyLinkTest() throws Exception {
        Link link = new Link();
        link.setId(0);
        link.setType("Test");
        link.setUrl("www.test.com");
        link.setDescription("La descripcion se ha modificado");
        Gson gson = new Gson();
        String json = gson.toJson(link);

        this.mockMvc
                .perform(put("/links/0").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(json))
                .andExpect(status().isOk())
                .andDo(print());

        this.mockMvc
                .perform(get("/links"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.[0].id").value(0))
                .andExpect(jsonPath("$.[0].type").value("Test"))
                .andExpect(jsonPath("$.[0].url").value("www.test.com"))
                .andExpect(jsonPath("$.[0].description").value("La descripcion se ha modificado"));
    }

    @Test
    public void deleteLinkTest() throws Exception {
        this.mockMvc
                .perform(delete("/links/0"))
                .andExpect(status().isOk());
    }

    /*
     * @Test public void listLinksTest() throws Exception { MvcResult result = this.mockMvc
     * .perform(get("/links")).andReturn(); String s = result.getResponse().getContentAsString();
     * System.out.println("---------------------------------------------"); System.out.println(s);
     * System.out.println("---------------------------------------------"); assertEquals(1, 0); }
     */
}
