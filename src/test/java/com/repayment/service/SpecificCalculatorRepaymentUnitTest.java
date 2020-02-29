package com.repayment.service;

import com.repayment.entities.Loan;
import com.repayment.entities.RePaymentPlan;
import com.repayment.exception.BusinessException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author R.Fattahi
 */
@RunWith(MockitoJUnitRunner.class)
public class SpecificCalculatorRepaymentUnitTest {

    @InjectMocks
    private SpecificCalculatorRepayment specificCalculatorRepayment;

    @Test
    public void generatePlan_SizeOFRePaymentPlans_24() {
        Loan loan = Loan.builder().loanAmount((double) 5000).startDate(new Date()).duration(24).nominalRate(5).build();
        List<RePaymentPlan> rePaymentPlans = specificCalculatorRepayment.generatePlan(loan);
        assertEquals("The size of array should be 24", 24, rePaymentPlans.size());
    }

    @Test
    public void generatePlan_SizeOFRePaymentPlans_30() {
        Loan loan = Loan.builder().loanAmount((double) 10000).startDate(new Date()).duration(30).nominalRate(7).build();
        List<RePaymentPlan> rePaymentPlans = specificCalculatorRepayment.generatePlan(loan);
        assertEquals("The size of array should be 30", 30, rePaymentPlans.size());
    }

    @Test
    public void generatePlan_RemainingOutstandingPrincipalInEndOfArrayList_0() {
        Loan loan = Loan.builder().loanAmount((double) 250000).startDate(new Date()).duration(50).nominalRate(10).build();
        List<RePaymentPlan> rePaymentPlans = specificCalculatorRepayment.generatePlan(loan);
        assertEquals("The remainingOutstandingPrincipal of end of array should be 0", (Object) 0.0, rePaymentPlans.get(49).getRemainingOutstandingPrincipal());
    }

    @Test
    public void calcInterested_ByRate5_ok() {
        double interested = specificCalculatorRepayment.calcInterested(5, 5000);
        assertEquals((Object) 20.83, interested);
    }

    @Test
    public void calcInterested_ByRate7_ok() {
        double interested = specificCalculatorRepayment.calcInterested(7, 10000);
        assertEquals((Object) 58.33, interested);
    }

    @Test(expected = BusinessException.class)
    public void Should_ThrowExceptionCalcInterested_When_RateEqualsZero() {
        double interested = specificCalculatorRepayment.calcInterested(0, 5000);
        assertEquals((Object) 20.83, interested);
    }


    @Test
    public void calcAnnuity_ByRate5_ok() {
        double annuity = specificCalculatorRepayment.calcAnnuity(5000, 5, 24);
        assertEquals((Object) 219.36, annuity);

    }

    @Test
    public void calcAnnuity_ByRate7_ok() {
        double annuity = specificCalculatorRepayment.calcAnnuity(10000, 7, 30);
        assertEquals((Object) 364.32, annuity);
    }

    @Test(expected = BusinessException.class)
    public void Should_ThrowExceptionCalcAnnuity_When_PrincipalEqualsZero() {
        double annuity = specificCalculatorRepayment.calcAnnuity(0, 5, 24);
        assertEquals((Object) 219.36, annuity);
    }
}
