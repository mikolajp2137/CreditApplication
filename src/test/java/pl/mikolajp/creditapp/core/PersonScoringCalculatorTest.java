package pl.mikolajp.creditapp.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.mikolajp.creditapp.core.model.Education;
import pl.mikolajp.creditapp.core.model.MaritalStatus;
import pl.mikolajp.creditapp.core.model.Person;
import pl.mikolajp.creditapp.core.model.PersonTestFactory;

import static org.junit.jupiter.api.Assertions.*;

class PersonScoringCalculatorTest {
    private PersonScoringCalculator cut = new PersonScoringCalculator();
    @Test
    @DisplayName("")
    public void test1(){
        //given
        Person person = PersonTestFactory.create(5000.00, 2, Education.PRIMARY, MaritalStatus.MARRIED);
        //when
        int scoring = cut.calculate(person);
        //then
        assertEquals(200,scoring);
    }

    @Test
    @DisplayName("")
    public void test2(){
        //given
        Person person = PersonTestFactory.create(5500.00, 1, Education.MIDDLE, MaritalStatus.DIVORCED);
        //when
        int scoring = cut.calculate(person);
        //then
        assertEquals(500,scoring);
    }

    @Test
    @DisplayName("")
    public void test3(){
        //given
        Person person = PersonTestFactory.create(9000.00, 3, Education.NONE, MaritalStatus.SINGLE);
        //when
        int scoring = cut.calculate(person);
        //then
        assertEquals(100,scoring);
    }
}