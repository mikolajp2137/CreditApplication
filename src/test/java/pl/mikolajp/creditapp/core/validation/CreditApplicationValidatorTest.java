package pl.mikolajp.creditapp.core.validation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.mikolajp.creditapp.core.exception.ValidationException;
import pl.mikolajp.creditapp.core.model.*;
import pl.mikolajp.creditapp.core.validation.reflection.*;

import java.util.Arrays;
import java.util.List;
import java.util.Set;


class CreditApplicationValidatorTest {
    private List<ClassAnnotationProcessor> classProcessors = List.of(new ExactlyOneNotNullAnnotationProcessor());
    private List<FieldAnnotationProcessor> fieldProcessors = List.of(new NotNullAnnotationProcessor(), new RegexAnnotationProcessor());
    private CreditApplicationValidator cut = new CreditApplicationValidator(new ObjectValidator(fieldProcessors, classProcessors));

    @Test
    @DisplayName("")
    public void test() throws ValidationException {
        //given
        FamilyMember john = new FamilyMember("Ann", 18);
        FamilyMember jane = new FamilyMember("Beatrice", 40);
        FamilyMember jack = new FamilyMember("Jack", 5);
        List<FamilyMember> familyMembers = Arrays.asList(john, jane, jack);

        NaturalPerson person = NaturalPerson.Builder.create()
                .withPesel("12345678909")
                .withPersonalData(PersonalData.Builder.create()
                        .withName("Test").withLastName("Test")
                        .withMothersMaidenName("Test")
                        .withEducation(Education.MIDDLE)
                        .withMaritalStatus(MaritalStatus.MARRIED)
                        .build())
                .withFinanceData(new FinanceData(new SourceOfIncome(IncomeType.SELF_EMPLOYMENT, 10000.00)))
                .withContactData(ContactData.Builder.create()
                        .withHomeAddress(Address.Builder.create()
                                .withState("test").withZipCode("11-111")
                                .withCity("test").withStreet("test").withHouseNumber("1")
                                .build())
                        .withCorrespondenceAddress(Address.Builder.create()
                                .withState("test").withZipCode("11-111")
                                .withCity("test").withStreet("test").withHouseNumber("1")
                                .build())
                        .withEmail("email@e.mail")
                        .withPhoneNumber("123456789")
                        .build())
                .withFamilyMembers(familyMembers)
                .build();
        PurposeOfLoan purposeOfLoan = new PurposeOfLoan(PurposeOfLoanType.MORTGAGE, 50000.00, 30);
        Set<Guarantor> guarantors = Set.of(Guarantor.Builder.create().withPesel("12345678901").withAge(18).build(),
                Guarantor.Builder.create().withPesel("12345678902").withAge(41).build());

        CreditApplication creditApplication = new CreditApplication(person, purposeOfLoan, guarantors);
        //when
        cut.validate(creditApplication);
    }

}