package com.repayment.service;

import com.repayment.entities.Loan;
import com.repayment.entities.RePaymentPlan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * to create repayment plan from a loan object.
 *
 * @author R.Fattahi
 */
@Service
public class SpecificCalculatorRepayment extends CalculatorRepayment {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpecificCalculatorRepayment.class);

    /*
     * this method Accroding a loan that contains duration,rate,amount and date
     * calculates some RePaymentPlan
     *
     * @see
     * com.repayment.service.SpecificCalculatorRepayment#generatePlan(com.repayment.entities.
     * Loan)
     */
    @Override
    public List<RePaymentPlan> generatePlan(Loan loan) {
        LOGGER.debug("start {}", "generatePlan Service");
        double rate = loan.getNominalRate();
        List<RePaymentPlan> rePaymentPlans = Stream.iterate(1, i -> i).limit(loan.getDuration()).map(x -> {
            Integer duration = loan.getDuration();
            double initialOutstandingPrincipal = loan.getLoanAmount();
            double interest = calcInterested(rate, initialOutstandingPrincipal);
            double annuity = calcAnnuity(initialOutstandingPrincipal, rate, duration);
            duration--;
            loan.setDuration(duration);
            double principal = round(annuity - interest);
            RePaymentPlan rePaymentPlan = RePaymentPlan.builder().interest(interest)
                    .borrowerPaymentAmount(annuity)
                    .principal(principal)
                    .initialOutstandingPrincipal(initialOutstandingPrincipal)
                    .remainingOutstandingPrincipal(round(initialOutstandingPrincipal - principal))
                    .date(loan.getStartDate()).build();
            loan.setLoanAmount(rePaymentPlan.getRemainingOutstandingPrincipal());
            loan.setStartDate(nextMonth(loan.getStartDate()));
            return rePaymentPlan;
        }).collect(Collectors.toList());
        LOGGER.debug("executed successfully {}", "generatePlan Service");
        return rePaymentPlans;
    }
}
