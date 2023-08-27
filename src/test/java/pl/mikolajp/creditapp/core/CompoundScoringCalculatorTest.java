package pl.mikolajp.creditapp.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.mikolajp.creditapp.core.model.Person;
import pl.mikolajp.creditapp.core.model.PersonTestFactory;
import pl.mikolajp.creditapp.core.scoring.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
class CompoundScoringCalculatorTest {
    private PersonCalculator calculator1Mock = Mockito.mock(PersonCalculator.class);
    private PersonCalculator calculator2Mock = Mockito.mock(PersonCalculator.class);
    private PersonCalculator calculator3Mock = Mockito.mock(PersonCalculator.class);

    private CompoundScoringCalculator cut = new CompoundScoringCalculator(calculator1Mock, calculator2Mock, calculator3Mock);


    @Test
    @DisplayName("Should return sum of calculations")
    public void test1() {
        //given
        Person person = PersonTestFactory.create();
        BDDMockito.given(calculator1Mock.calculate(eq(person))).willReturn(100);
        BDDMockito.given(calculator2Mock.calculate(eq(person))).willReturn(200);
        BDDMockito.given(calculator3Mock.calculate(eq(person))).willReturn(50);
        //when
        int scoring = cut.calculate(person);
        //then
        assertEquals(350, scoring);
    }
}