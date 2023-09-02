package pl.mikolajp.creditapp.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.mikolajp.creditapp.core.model.CreditApplication;
import pl.mikolajp.creditapp.core.model.CreditApplicationTestFactory;
import pl.mikolajp.creditapp.core.scoring.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
class CompoundScoringCalculatorTest {
    private ScoringCalculator calculator1Mock = Mockito.mock(ScoringCalculator.class);
    private ScoringCalculator calculator2Mock = Mockito.mock(ScoringCalculator.class);
    private ScoringCalculator calculator3Mock = Mockito.mock(ScoringCalculator.class);

    private CompoundScoringCalculator cut = new CompoundScoringCalculator(calculator1Mock, calculator2Mock, calculator3Mock);


    @Test
    @DisplayName("Should return sum of calculations")
    public void test1() {
        //given
        CreditApplication creditApplication = CreditApplicationTestFactory.create();
        BDDMockito.given(calculator1Mock.calculate(eq(creditApplication))).willReturn(100);
        BDDMockito.given(calculator2Mock.calculate(eq(creditApplication))).willReturn(200);
        BDDMockito.given(calculator3Mock.calculate(eq(creditApplication))).willReturn(50);
        //when
        int scoring = cut.calculate(creditApplication);
        //then
        assertEquals(350, scoring);
    }
}