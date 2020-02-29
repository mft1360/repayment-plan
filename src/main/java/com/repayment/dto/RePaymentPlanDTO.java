package com.repayment.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * to get a json repayment plan according this class.
 *
 * @author R.Fattahi
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "RePaymentPlanDTO", description = "response of repayment plan")
public class RePaymentPlanDTO {

    @ApiModelProperty(value = "Annuity (Borrower Payment Amount)")
    private double borrowerPaymentAmount;

    @ApiModelProperty(value = "date")
    private Date date;

    @ApiModelProperty(value = "Initial Outstanding Principal")
    private double initialOutstandingPrincipal;

    @ApiModelProperty(value = "interest")
    private double interest;

    @ApiModelProperty(value = "principal")
    private double principal;

    @ApiModelProperty(value = "Remaining Outstanding Principal")
    private double remainingOutstandingPrincipal;

}
