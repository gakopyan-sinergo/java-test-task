package com.gakopyan.cawa.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AccountCrudControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createAccount() throws Exception {
        this.mockMvc.perform(put("/account/15")).andDo(print()).andExpect(status().isOk());
        this.mockMvc.perform(get("/account/15")).andDo(print()).andExpect(status().isOk());
        this.mockMvc.perform(post("/account/15")).andDo(print()).andExpect(status().isOk());
        this.mockMvc.perform(delete("/account/15")).andDo(print()).andExpect(status().isOk());
    }
}
