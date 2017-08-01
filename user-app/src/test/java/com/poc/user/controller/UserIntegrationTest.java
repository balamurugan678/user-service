package com.poc.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.user.UserApplication;
import com.poc.user.models.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = UserApplication.class)
@AutoConfigureMockMvc
public class UserIntegrationTest {


    @Autowired
    private MockMvc mvc;

    @Test
    public void getUsersTest() throws Exception {

        mvc.perform(get("/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name", is("Leanne Graham")));
    }

    @Test
    public void getUsersHeadersTest() throws Exception {

        mvc.perform(get("/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name", is("Leanne Graham")))
                .andExpect(header().string("Cache-Control", "public"))
                .andExpect(header().string("Connection", "keep-alive"))
                .andExpect(header().string("Content-Type", "application/json"));
    }


    @Test
    public void getIndividualUserTest() throws Exception {

        mvc.perform(get("/user/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("Ervin Howell")));
    }


    @Test
    public void postUserTest() throws Exception {

        User user = new User();
        user.setName("Lewis Hamilton");
        user.setEmail("LewisHamilton@gmail.com");
        user.setUsername("LH");

        mvc.perform(post("/user")
                .content(asJsonString(user))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("Lewis Hamilton")))
                .andExpect(jsonPath("$.id", is(11)));
    }


    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getInvalidUserTest() throws Exception {

        mvc.perform(get("/user/11")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }

    @Test
    public void deleteIndividualUserTest() throws Exception {

        mvc.perform(delete("/user/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
