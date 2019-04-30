package com.holiday.finder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class LoginTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void loginValidTest() throws Exception {
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder
                login = formLogin().user("admin").password("admin");

        mockMvc.perform(login).andExpect(authenticated().withUsername("admin"));
    }
}