package com.repayment.controller;

import com.repayment.aspect.CheckBindingResult;
import com.repayment.dto.LoanDTO;
import com.repayment.dto.RePaymentPlanDTO;
import com.repayment.mapper.LoanMapper;
import com.repayment.service.SpecificCalculatorRepayment;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

/**
 * post mapping to create a loan.
 * Post a json of LoanDTO and response is list of RePaymentPlanDTO.
 * @author R.Fattahi
 */
@RestController
@RequestMapping("/generate-plan")
@RequiredArgsConstructor
public class LoadController {

    private final SpecificCalculatorRepayment specificCalculatorRepayment;

    private final LoanMapper loanMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(SpecificCalculatorRepayment.class);

    @PostMapping
    @CheckBindingResult
    @ApiOperation(response = RePaymentPlanDTO.class, value = "this API will use some inputs to create repayment plan schedule")
    public List<RePaymentPlanDTO> generatePlan(@RequestBody @Validated @ApiParam(required = true) LoanDTO loanDTO,
                                               BindingResult bindingResult) {
        LOGGER.debug("start {} with URL {}", "generatePlan", "/generate-plan");
        List<RePaymentPlanDTO> rePaymentPlanDTOS = loanMapper.toRePaymentPlanDTOs(specificCalculatorRepayment.generatePlan(loanMapper.toLoan(loanDTO)));
        LOGGER.debug("executed successfully {} with URL {}", "generatePlan", "/generate-plan");
        return rePaymentPlanDTOS;
    }

}
