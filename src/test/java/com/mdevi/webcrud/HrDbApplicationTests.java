package com.mdevi.webcrud;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HrDbApplication.class)
@WebAppConfiguration
public class HrDbApplicationTests {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Autowired
    private Filter springSecurityFilterChain;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.
                webAppContextSetup(this.wac)
                .addFilter(springSecurityFilterChain)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void cantViewPageIfNotLoggedIn() throws Exception {
        mockMvc.perform(get("http://localhost:8080/"))
                .andExpect(status().is(302))
                .andExpect(redirectedUrl("http://localhost:8080/login"))
                .andDo(print());
    }

    @Test
    public void canLoggingInWithCorrectDetails() throws Exception {
        mockMvc.perform(post("http://localhost/login")
                .param("username", "Sergei")
                .param("password", "Sergei")
                .with(csrf()))
                .andExpect(status().is(302))
                .andExpect(redirectedUrl("/"))
                .andDo(print());
    }

    @Test
    public void cantLoggingInWithIncorrectDetails() throws Exception {
        mockMvc.perform(post("http://localhost/login")
                .param("username", "Sergei")
                .param("password", "wrongPassword")
                .with(csrf()))
                .andExpect(status().is(302))
                .andExpect(redirectedUrl("/login?error"))
                .andDo(print());
    }

    @Test
    @WithUserDetails("Sergei")
    public void canAccessBooksPageIfReader() throws Exception {
        mockMvc.perform(get("http://localhost/books"))
                .andExpect(status().isOk())
                .andExpect(view().name("books"))
                .andDo(print());
    }

    @Test
    @WithUserDetails("Sergei")
    public void cantAccessPersonsPageIfReader() throws Exception {
        mockMvc.perform(get("http://localhost/persons"))
                .andExpect(forwardedUrl("/accessDenied"))
                .andExpect(status().is(403))
                .andDo(print());
    }

/*    @Test
    @WithUserDetails("FakeReader")
    public void cantAccessBooksPageIfFakeUser() throws Exception {
        mockMvc.perform(get("http://localhost/books"))
                .andExpect(status().is(403))
                .andExpect(forwardedUrl("/login?error"))
                .andDo(print());
    }*/

    @Test
    @WithUserDetails("Admin")
    public void canAccessPersonsPageIfAdmin() throws Exception {
        mockMvc.perform(get("https://localhost/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andDo(print());
    }

}
