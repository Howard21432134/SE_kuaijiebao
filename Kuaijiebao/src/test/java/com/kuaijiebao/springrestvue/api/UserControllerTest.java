package com.kuaijiebao.springrestvue.api;

import com.kuaijiebao.springrestvue.SpringRestReactApplication;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.kuaijiebao.springrestvue.domain.User;
import com.kuaijiebao.springrestvue.repository.UserRepository;
import com.kuaijiebao.springrestvue.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = SpringRestReactApplication.class)
@AutoConfigureMockMvc
// @TestPropertySource(locations = "classpath:application-integrationtest.properties")
@AutoConfigureTestDatabase
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserRepository repository;

    @After
    public void resetDb() {
        repository.deleteAll();
    }

    @Test
    public void whenValidInput_thenCreateEmployee() throws IOException, Exception {
        User bob = new User("bob","bob",
                "kl48394593","pilot",0,
                "hongkong", "Hello","11122223333",
                "bob@qq.com");
        mvc.perform(post("/api/user/createUser").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(bob)));

        List<User> found = repository.findAll();
        assertThat(found).extracting(User::getName).containsOnly("bob");
    }

    @Test
    public void givenEmployees_whenGetEmployees_thenStatus200() throws Exception {
        createTestEmployee("john","john",
                "MG234143","student",0,
                "SJTU", "Hello","11122223333",
                "john@qq.com");
        createTestEmployee("bob","bob",
                "jh23453245","engineer",0,
                "shanghai", "Hello","11122223333",
                "bob@qq.com");

        createTestEmployee("alex","alex",
                "kl48394593","pilot",0,
                "hongkong", "Hello","11122223333",
                "alex@qq.com");

        // @formatter:off
        mvc.perform(get("/api/user").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
                .andExpect(jsonPath("$[0].name", is("john")))
                .andExpect(jsonPath("$[1].name", is("bob")));
        // @formatter:on
    }

    //

    private void createTestEmployee(String nickname, String name, String identity,
                                    String job, Integer income, String address,
                                    String introduction, String phone,
                                    String email) {
        User user = new User(nickname, name, identity,
                job, income, address, introduction,
                phone, email);
        repository.saveAndFlush(user);
    }


}