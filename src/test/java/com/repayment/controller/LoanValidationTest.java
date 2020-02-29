package com.repayment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.repayment.Application;
import com.repayment.dto.LoanDTO;
import com.repayment.handler.ErrorResource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author R.Fattahi
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class LoanValidationTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setUp() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void generatePlan_ValidationTest_BadRequest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        RequestBuilder request = MockMvcRequestBuilders.post("/generate-plan")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new LoanDTO()));
        MvcResult mvcResult = mvc.perform(request).andExpect(status().isBadRequest()).andReturn();
        String responseJson = mvcResult.getResponse().getContentAsString();
        ErrorResource[] fieldsErrors = objectMapper.readValue(responseJson, ErrorResource[].class);
        assertEquals("The size of array should be 4", 4, fieldsErrors.length);
    }

}
