package pl.mikolajp.creditapp.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.mikolajp.creditapp.core.exception.RequirementsNotMetException;
import pl.mikolajp.creditapp.core.exception.ValidationException;
import pl.mikolajp.creditapp.core.model.CreditApplication;
import pl.mikolajp.creditapp.core.model.CreditApplicationTestFactory;
import pl.mikolajp.creditapp.core.model.Person;
import pl.mikolajp.creditapp.core.scoring.ScoringCalculator;
import pl.mikolajp.creditapp.core.validation.CompoundPostValidator;
import pl.mikolajp.creditapp.core.validation.CreditApplicationValidator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
class CreditApplicationServiceTest {

    @InjectMocks
    private CreditApplicationService cut;

    @Mock
    private ScoringCalculator scoringCalculatorMock;

    @Mock
    private CreditApplicationValidator creditApplicationValidatorMock;

    @Mock
    private CompoundPostValidator compoundPostValidatorMock;

    @Mock
    private PersonScoringCalculatorFactory personScoringCalculatorFactoryMock;

    @Mock
    private CreditRatingCalculator creditRatingCalculatorMock;

    @BeforeEach
    public void init() throws ValidationException, RequirementsNotMetException {
        BDDMockito.given(personScoringCalculatorFactoryMock.getCalculator(any(Person.class))).willReturn(scoringCalculatorMock);

        BDDMockito.doNothing().when(creditApplicationValidatorMock).validate(any(CreditApplication.class));

        BDDMockito.doNothing().when(compoundPostValidatorMock).validate(any(CreditApplication.class), anyInt(), anyDouble());
    }

    @Test
    @DisplayName("Should return negative_scoring, when scoring is < 300")
    public void test1() {
        //given
        CreditApplication creditApplication = CreditApplicationTestFactory.create();
        BDDMockito.given(scoringCalculatorMock.calculate(eq(creditApplication))).willReturn(100);
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
        BDDMockito.given(scoringCalculatorMock.calculate(eq(creditApplication))).willReturn(350);
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
        BDDMockito.given(scoringCalculatorMock.calculate(eq(creditApplication))).willReturn(450);

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
        BDDMockito.given(scoringCalculatorMock.calculate(eq(creditApplication))).willReturn(450);

        BDDMockito.given(creditRatingCalculatorMock.calculateCreditRating(eq(creditApplication))).willReturn(151000.00);
        //when
        CreditApplicationDecision decision = cut.getDecision(creditApplication);
        //then
        assertEquals(DecisionType.POSITIVE, decision.getDecisionType());
    }
}