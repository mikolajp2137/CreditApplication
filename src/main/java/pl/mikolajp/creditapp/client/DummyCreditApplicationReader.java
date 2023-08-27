package pl.mikolajp.creditapp.client;

import pl.mikolajp.creditapp.core.model.*;

import java.util.Arrays;
import java.util.List;

public class DummyCreditApplicationReader implements CreditApplicationReader {

    @Override
    public CreditApplication read() {
        FamilyMember john = new FamilyMember("Ann", 18);
        FamilyMember jane = new FamilyMember("Beatrice", 40);
        FamilyMember jack = new FamilyMember("Jack", 5);
        List<FamilyMember> familyMembers = Arrays.asList(john, jane, jack);

        SelfEmployed person = SelfEmployed.Builder.create()
                .withPersonalData(PersonalData.Builder.create()
                        .withName("Test").withLastName("Test")
                        .withMothersMaidenName("Test")
                        .withEducation(Education.MIDDLE)
                        .withMaritalStatus(MaritalStatus.MARRIED)
                        .build())
                .withFinanceData(new FinanceData(new SourceOfIncome(IncomeType.SELF_EMPLOYMENT, 10000.00)))
                .withFamilyMembers(familyMembers)
                .withYearsSinceFounded(3)
                .build();
        PurposeOfLoan purposeOfLoan = new PurposeOfLoan(PurposeOfLoanType.MORTGAGE, 50000.00, 30);

        return new CreditApplication(person,purposeOfLoan);
    }
}
