package pl.mikolajp.creditapp.core.scoring;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.mikolajp.creditapp.core.model.*;

import static org.junit.jupiter.api.Assertions.*;

class IncomeCalculatorTest {
    private IncomeCalculator cut = new IncomeCalculator();

    @Test
    @DisplayName("Should return 100 points for each 1000zł per family member")
    public void test1() {
        //given
        SourceOfIncome s1 = new SourceOfIncome(IncomeType.SELF_EMPLOYMENT,5000.00);
        Person person = PersonTestFactory.create(2, s1);
        //when
        int scoring = cut.calculate(person);
        //then
        assertEquals(200, scoring);
    }

    @Test
    @DisplayName("Should return extra 100 points when there's more than one source of income")
    public void test2() {
        //given
        SourceOfIncome s1 = new SourceOfIncome(IncomeType.SELF_EMPLOYMENT,4000.00);
        SourceOfIncome s2 = new SourceOfIncome(IncomeType.RETIREMENT,1000.00);
        Person person = PersonTestFactory.create(2, s1, s2);
        //when
        int scoring = cut.calculate(person);
        //then
        assertEquals(300, scoring);
    }
}