package pl.mikolajp.creditapp.client;

import pl.mikolajp.creditapp.core.model.*;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class DummyCreditApplicationReader implements CreditApplicationReader {

    @Override
    public CreditApplication read() {
        FamilyMember john = new FamilyMember("Ann", 18);
        FamilyMember jane = new FamilyMember("Beatrice", 40);
        FamilyMember jack = new FamilyMember("Jack", 5);
        List<FamilyMember> familyMembers = Arrays.asList(john, jane, jack);

        NaturalPerson person = NaturalPerson.Builder.create()
                .withPesel("11122233344")
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

        return new CreditApplication(person, purposeOfLoan, guarantors);
    }
}
