package com.repayment.mapper;

import com.repayment.dto.LoanDTO;
import com.repayment.dto.RePaymentPlanDTO;
import com.repayment.entities.Loan;
import com.repayment.entities.RePaymentPlan;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * to map DTO to entity and reverse.
 *
 * @author R.Fattahi
 */
@Mapper
public interface LoanMapper {

    Loan toLoan(LoanDTO loanDTO);

    List<RePaymentPlanDTO> toRePaymentPlanDTOs(List<RePaymentPlan> rePaymentPlans);

}
