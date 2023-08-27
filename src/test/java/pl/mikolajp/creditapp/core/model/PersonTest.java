package pl.mikolajp.creditapp.core.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    @DisplayName("familyMembers list should be sorted")
    public void test1(){
        //given
        FamilyMember john = new FamilyMember("John", 18);
        FamilyMember jane = new FamilyMember("Jane", 40);
        FamilyMember jack = new FamilyMember("Jack", 5);
        List<FamilyMember> familyMembers = Arrays.asList(john, jane, jack);
        //when
        Person person = NaturalPerson.Builder.create()
                .withFamilyMembers(familyMembers)
                .build();
        //then
        assertNotNull(person.getFamilyMembers());
        assertEquals(3, person.getFamilyMembers().size());
        assertEquals(jane, person.getFamilyMembers().get(0));
        assertEquals(john, person.getFamilyMembers().get(1));
        assertEquals(jack, person.getFamilyMembers().get(2));
    }

}