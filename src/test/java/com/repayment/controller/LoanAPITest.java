package com.repayment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.repayment.dto.LoanDTO;
import com.repayment.dto.RePaymentPlanDTO;
import com.repayment.entities.Loan;
import com.repayment.entities.RePaymentPlan;
import com.repayment.mapper.LoanMapper;
import com.repayment.service.SpecificCalculatorRepayment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author R.Fattahi
 */
@RunWith(SpringRunner.class)
@WebMvcTest
public class LoanAPITest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SpecificCalculatorRepayment specificCalculatorRepayment;

    @MockBean
    private LoanMapper loanMapper;

    @Test
    public void when_GeneratePlanReturnRePaymentPlanList() throws Exception {
        Loan loan = Loan.builder().duration(24).nominalRate(5).build();

        RePaymentPlanDTO rePaymentPlanDTO = RePaymentPlanDTO.builder().date(new Date()).principal(500).build();
        List<RePaymentPlanDTO> rePaymentPlanDTOs = Arrays.asList(rePaymentPlanDTO);

        RePaymentPlan rePaymentPlan = RePaymentPlan.builder().date(new Date()).principal(500).interest(29.5).build();
        List<RePaymentPlan> rePaymentPlans = Arrays.asList(rePaymentPlan);

        doReturn(loan).when(loanMapper).toLoan(any());
        doReturn(rePaymentPlans).when(specificCalculatorRepayment).generatePlan(any());
        doReturn(rePaymentPlanDTOs).when(loanMapper).toRePaymentPlanDTOs(any());
        ObjectMapper objectMapper = new ObjectMapper();
        this.mockMvc.perform(post("/generate-plan").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new LoanDTO())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].principal", is(rePaymentPlanDTO.getPrincipal())));
    }

}
