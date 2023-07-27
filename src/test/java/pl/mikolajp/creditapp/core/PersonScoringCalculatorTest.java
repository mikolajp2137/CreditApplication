package pl.mikolajp.creditapp.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.mikolajp.creditapp.core.model.Education;
import pl.mikolajp.creditapp.core.model.MaritalStatus;
import pl.mikolajp.creditapp.core.model.Person;
import pl.mikolajp.creditapp.core.model.PersonTestFactory;
import pl.mikolajp.creditapp.core.scoring.EducationCalculator;
import pl.mikolajp.creditapp.core.scoring.IncomeCalculator;
import pl.mikolajp.creditapp.core.scoring.MaritalStatusCalculator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
class PersonScoringCalculatorTest {

    @InjectMocks
    private PersonScoringCalculator cut;

    @Mock
    private EducationCalculator educationCalculatorMock;
    @Mock
    private IncomeCalculator incomeCalculatorMock;
    @Mock
    private MaritalStatusCalculator maritalStatusCalculatorMock;

    @Test
    @DisplayName("Should return sum of calculations")
    public void test1(){
        //given
        Person person = PersonTestFactory.create();
        BDDMockito.given(educationCalculatorMock.calculateEducation(eq(person))).willReturn(100);
        BDDMockito.given(maritalStatusCalculatorMock.calculateMaritalStatus(eq(person))).willReturn(200);
        BDDMockito.given(incomeCalculatorMock.calculateIncome(eq(person))).willReturn(50);
        //when
        int scoring = cut.calculate(person);
        //then
        assertEquals(350, scoring);
    }
}