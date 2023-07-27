package pl.mikolajp.creditapp.core.scoring;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import pl.mikolajp.creditapp.core.model.MaritalStatus;
import pl.mikolajp.creditapp.core.model.Person;
import pl.mikolajp.creditapp.core.model.PersonTestFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MaritalStatusCalculatorTest {
    private MaritalStatusCalculator cut = new MaritalStatusCalculator();

    @ParameterizedTest
    @DisplayName("Should return points attached to enum element")
    @EnumSource(MaritalStatus.class)
    public void test1(MaritalStatus maritalStatus) {
        //given
        Person person = PersonTestFactory.create(maritalStatus);
        //when
        int scoring = cut.calculateMaritalStatus(person);
        //then
        assertEquals(maritalStatus.getScoringPoints(), scoring);
    }
}
