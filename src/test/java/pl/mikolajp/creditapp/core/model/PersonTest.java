package pl.mikolajp.creditapp.core.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.mikolajp.creditapp.util.AgeUtils;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    @DisplayName("familyMembers list should be sorted")
    public void test1() {
        //given
        FamilyMember john = new FamilyMember("John", AgeUtils.generateBirthDate(18));
        FamilyMember jane = new FamilyMember("Jane", AgeUtils.generateBirthDate(40));
        FamilyMember susie = new FamilyMember("Susie", AgeUtils.generateBirthDate(5));
        List<FamilyMember> familyMembers = Arrays.asList(john, jane, susie);
        //when
        Person person = NaturalPerson.Builder.create().withFamilyMembers(familyMembers).build();
        //then
        assertNotNull(person.getFamilyMembers());
        assertEquals(3, person.getFamilyMembers().size());
        assertEquals(susie, person.getFamilyMembers().get(0));
        assertEquals(john, person.getFamilyMembers().get(1));
        assertEquals(jane, person.getFamilyMembers().get(2));
    }

}