package com.repayment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.repayment.Application;
import com.repayment.dto.LoanDTO;
import com.repayment.dto.RePaymentPlanDTO;
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

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author R.Fattahi
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class LoanIntegrationTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setUp() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void generatePlan_ByRate5_ok() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        RequestBuilder request = MockMvcRequestBuilders.post("/generate-plan")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(LoanDTO.builder().duration(24).loanAmount(5000).nominalRate(5).startDate(new Date()).build()));
        MvcResult mvcResult = mvc.perform(request).andExpect(status().isOk()).andReturn();
        String responseJson = mvcResult.getResponse().getContentAsString();
        RePaymentPlanDTO[] rePaymentPlans = objectMapper.readValue(responseJson, RePaymentPlanDTO[].class);
        assertEquals("The size of array should be 24", 24, rePaymentPlans.length);
    }

    @Test
    public void generatePlan_ByRate7_ok() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        RequestBuilder request = MockMvcRequestBuilders.post("/generate-plan")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(LoanDTO.builder().duration(30).loanAmount(10000).nominalRate(7).startDate(new Date()).build()));
        MvcResult mvcResult = mvc.perform(request).andExpect(status().isOk()).andReturn();
        String responseJson = mvcResult.getResponse().getContentAsString();
        RePaymentPlanDTO[] rePaymentPlans = objectMapper.readValue(responseJson, RePaymentPlanDTO[].class);
        assertEquals("The size of array should be 30", 30, rePaymentPlans.length);
    }

}
