package pl.mikolajp.creditapp.core.scoring;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.internal.util.collections.Sets;
import pl.mikolajp.creditapp.core.model.*;
import pl.mikolajp.creditapp.util.AgeUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class GuarantorsCalculatorTest {
    private GuarantorsCalculator cut = new GuarantorsCalculator();
    @Test
    @DisplayName("")
    public void test1(){
        //given
        NaturalPerson person = getNaturalPerson();
        PurposeOfLoan purposeOfLoan = new PurposeOfLoan(PurposeOfLoanType.MORTGAGE, 50000.00, 30);
        Set<Guarantor> guarantorSet = Sets.newSet(Guarantor.Builder.create().withPesel("12345678901").withAge(AgeUtils.generateBirthDate(18)).build());
        CreditApplication creditApplication = CreditApplicationTestFactory.create(person, purposeOfLoan, guarantorSet);
        //when
        int scoring = cut.calculate(creditApplication);
        //then
        assertEquals(50, scoring);
    }
    @Test
    @DisplayName("")
    public void test2(){
        //given
        NaturalPerson person = getNaturalPerson();
        PurposeOfLoan purposeOfLoan = new PurposeOfLoan(PurposeOfLoanType.MORTGAGE, 50000.00, 30);
        Set<Guarantor> guarantorSet = Sets.newSet(Guarantor.Builder.create().withPesel("12345678901").withAge(AgeUtils.generateBirthDate(18)).build(),
                Guarantor.Builder.create().withPesel("12345678902").withAge(AgeUtils.generateBirthDate(41)).build());
        CreditApplication creditApplication = CreditApplicationTestFactory.create(person, purposeOfLoan, guarantorSet);
        //when
        int scoring = cut.calculate(creditApplication);
        //then
        assertEquals(75, scoring);
    }

    private NaturalPerson getNaturalPerson() {
        List<FamilyMember> familyMembers = Arrays.asList(new FamilyMember("Ann", AgeUtils.generateBirthDate(18)));
        NaturalPerson person = NaturalPerson.Builder.create()
                .withFamilyMembers(familyMembers)
                .withPersonalData(PersonalData.Builder.create()
                        .withName("Test").withLastName("Test")
                        .withMothersMaidenName("Test")
                        .withEducation(Education.MIDDLE)
                        .withMaritalStatus(MaritalStatus.MARRIED)
                        .build())
                .withFinanceData(new FinanceData(new SourceOfIncome(IncomeType.SELF_EMPLOYMENT, 10000.00)))
                .build();
        return person;
    }

}