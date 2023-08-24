package pl.mikolajp.creditapp.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.mikolajp.creditapp.core.model.CreditApplication;
import pl.mikolajp.creditapp.core.model.CreditApplicationTestFactory;
import pl.mikolajp.creditapp.core.model.PurposeOfLoanType;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
class CreditApplicationServiceTest {

    @InjectMocks
    private CreditApplicationService cut;

    @Mock
    private PersonScoringCalculator calculatorMock;

    @Mock
    private CreditRatingCalculator creditRatingCalculatorMock;

    @Test
    @DisplayName("Should return negative_scoring, when scoring is < 300")
    public void test1() {
        //given
        CreditApplication creditApplication = CreditApplicationTestFactory.create();
        BDDMockito.given(calculatorMock.calculate(eq(creditApplication.getPerson()))).willReturn(100);
        //when
        CreditApplicationDecision decision = cut.getDecision(creditApplication);
        //then
        assertEquals(DecisionType.NEGATIVE_SCORING, decision.getDecisionType());
    }

    @Test
    @DisplayName("Should return CONTACT_REQUIRED, when scoring is <= 400")
    public void test2() {
        //given
        CreditApplication creditApplication = CreditApplicationTestFactory.create();
        BDDMockito.given(calculatorMock.calculate(eq(creditApplication.getPerson()))).willReturn(350);
        //when
        CreditApplicationDecision decision = cut.getDecision(creditApplication);
        //then
        assertEquals(DecisionType.CONTACT_REQUIRED, decision.getDecisionType());
    }

    @Test
    @DisplayName("Should return negative_rating, when scoring is > 400 and credit rating > expected loan amount")
    public void test3() {
        //given
        CreditApplication creditApplication = CreditApplicationTestFactory.create(190000);
        BDDMockito.given(calculatorMock.calculate(eq(creditApplication.getPerson()))).willReturn(450);

        BDDMockito.given(creditRatingCalculatorMock.calculateCreditRating(eq(creditApplication))).willReturn(189000.00);
        //when
        CreditApplicationDecision decision = cut.getDecision(creditApplication);
        //then
        assertEquals(DecisionType.NEGATIVE_RATING, decision.getDecisionType());
    }

    @Test
    @DisplayName("Should return positive, when scoring is > 400 and credit rating <= expected loan amount")
    public void test4() {
        //given
        CreditApplication creditApplication = CreditApplicationTestFactory.create(150000);
        BDDMockito.given(calculatorMock.calculate(eq(creditApplication.getPerson()))).willReturn(450);

        BDDMockito.given(creditRatingCalculatorMock.calculateCreditRating(eq(creditApplication))).willReturn(151000.00);
        //when
        CreditApplicationDecision decision = cut.getDecision(creditApplication);
        //then
        assertEquals(DecisionType.POSITIVE, decision.getDecisionType());
    }
}