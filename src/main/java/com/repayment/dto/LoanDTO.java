package com.repayment.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * to create a json loan according this class.
 *
 * @author R.Fattahi
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "LoanDTO", description = "input a specific loan")
public class LoanDTO {

    @NotNull
    @Min(1)
    @ApiModelProperty(required = true, value = "loan amount base")
    private Integer loanAmount;

    @Min(1)
    @ApiModelProperty(required = true, value = "rate loan")
    private double nominalRate;

    @NotNull
    @Min(1)
    @Max(120)
    @ApiModelProperty(required = true, value = "duration time with months")
    private Integer duration;

    @NotNull
    @ApiModelProperty(required = true, value = "start date")
    private Date startDate;

}
