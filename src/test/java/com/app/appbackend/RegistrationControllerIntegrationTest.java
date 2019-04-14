package com.app.appbackend;

import com.app.appbackend.registration.RegistrationController;
import com.app.appbackend.registration.UserAuthorizationDao;
import com.app.appbackend.validation.Validation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RegistrationController.class)
public class RegistrationControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    UserAuthorizationDao userAuthorizationDao;

    @MockBean
    Validation validation;

    @Test
    public void userRegistrationSuccessfulTest() throws Exception {
        String test = "{\n" +
                "\t\"name\":\"test\",\n" +
                "\t\"surname\":\"test\",\n" +
                "\t\"email\":\"test@test.ee\",\n" +
                "\t\"password\":\"test1\",\n" +
                "\t\"password2\":\"test1\",\n" +
                "\t\"city\":\"Tallinn\",\n" +
                "\t\"country\":\"Estonia\",\n" +
                "\t\"gender\":\"MALE\",\n" +
                "\t\"birth\": \"2019-01-01\"\n" +
                "}";

        mvc.perform(post("/register").contentType(MediaType.APPLICATION_JSON).content(test))
                .andExpect(status().isOk());

    }


    @Test
    public void userRegistrationFailureNullValuesTest() throws Exception {
        String test = "{\n" +
                "\t\"name\":\"test\",\n" +
                "\t\"surname\":\"test\",\n" +
                "\t\"email\":\"null\",\n" +
                "\t\"password\":\"test1\",\n" +
                "\t\"password2\":\"test1\",\n" +
                "\t\"city\":\"Tallinn\",\n" +
                "\t\"country\":\"Estonia\",\n" +
                "\t\"gender\":\"MALE\",\n" +
                "\t\"birth\": \"2019-01-01\"\n" +
                "}";

        mvc.perform(post("/register").contentType(MediaType.APPLICATION_JSON).content(test))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void userRegistrationShortPasswordFailureTest() throws Exception {
        String test = "{\n" +
                "\t\"name\":\"test\",\n" +
                "\t\"surname\":\"test\",\n" +
                "\t\"email\":\"test1@test.ee\",\n" +
                "\t\"password\":\"te\",\n" +
                "\t\"password2\":\"te\",\n" +
                "\t\"city\":\"Tallinn\",\n" +
                "\t\"country\":\"Estonia\",\n" +
                "\t\"gender\":\"MALE\",\n" +
                "\t\"birth\": \"2019-01-01\"\n" +
                "}";

        mvc.perform(post("/register").contentType(MediaType.APPLICATION_JSON).content(test))
                .andExpect(status().isBadRequest());
    }
}
