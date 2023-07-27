package pl.mikolajp.creditapp.core.scoring;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.BDDMockito;
import pl.mikolajp.creditapp.core.CreditApplicationDecision;
import pl.mikolajp.creditapp.core.DecisionType;
import pl.mikolajp.creditapp.core.model.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;

class IncomeCalculatorTest {
    private IncomeCalculator cut = new IncomeCalculator();

    @Test
    @DisplayName("Should return 100 points for each 1000zł per family member")
    public void test1() {
        //given
        Person person = PersonTestFactory.create(5000.00, 2);
        //when
        int scoring = cut.calculateIncome(person);
        //then
        assertEquals(200, scoring);
    }


}