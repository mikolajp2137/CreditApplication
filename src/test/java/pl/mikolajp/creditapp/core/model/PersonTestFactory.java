package pl.mikolajp.creditapp.core.model;

import pl.mikolajp.creditapp.util.AgeUtils;

import java.util.ArrayList;
import java.util.List;

public class PersonTestFactory {
    public static NaturalPerson create() {
        List<FamilyMember> familyMembers = getFamilyMembers(2);
        PersonalData personalData = PersonalData.Builder
                .create()
                .withName("test").withLastName("test").withMothersMaidenName("test")
                .withEducation(Education.MIDDLE)
                .withMaritalStatus(MaritalStatus.SINGLE)
                .build();
        return NaturalPerson.Builder.create()
                .withFamilyMembers(familyMembers)
                .withPersonalData(personalData)
                .build();
    }

    public static NaturalPerson create(Education education) {
        List<FamilyMember> familyMembers = getFamilyMembers(2);
        PersonalData personalData = PersonalData.Builder
                .create()
                .withName("test").withLastName("test").withMothersMaidenName("test")
                .withEducation(education)
                .withMaritalStatus(MaritalStatus.SINGLE)
                .build();
        return NaturalPerson.Builder.create()
                .withFamilyMembers(familyMembers)
                .withPersonalData(personalData)
                .build();
    }

    public static NaturalPerson create(MaritalStatus maritalStatus) {
        List<FamilyMember> familyMembers = getFamilyMembers(2);
        PersonalData personalData = PersonalData.Builder
                .create()
                .withName("test").withLastName("test").withMothersMaidenName("test")
                .withEducation(Education.MIDDLE)
                .withMaritalStatus(maritalStatus)
                .build();
        return NaturalPerson.Builder.create()
                .withFamilyMembers(familyMembers)
                .withPersonalData(personalData)
                .build();
    }

    public static NaturalPerson create(int numOfDependants, SourceOfIncome... sourcesOfIncome) {
        List<FamilyMember> familyMembers = getFamilyMembers(numOfDependants);
        PersonalData personalData = PersonalData.Builder
                .create()
                .withName("test").withLastName("test").withMothersMaidenName("test")
                .withEducation(Education.MIDDLE)
                .withMaritalStatus(MaritalStatus.SINGLE)
                .build();
        return NaturalPerson.Builder.create()
                .withFamilyMembers(familyMembers)
                .withPersonalData(personalData)
                .withFinanceData(new FinanceData(sourcesOfIncome))
                .build();
    }

    private static List<FamilyMember> getFamilyMembers(int numOfDependants) {
        List<FamilyMember> familyMembers = new ArrayList<>();
        for (int i = 0; i < numOfDependants-1; i++) familyMembers.add(new FamilyMember("Ann", AgeUtils.generateBirthDate(18)));
        return familyMembers;
    }

    public static NaturalPerson create(double totalMonthlyIncomeInPln, int numOfDependants, Education education, MaritalStatus maritalStatus) {
        List<FamilyMember> familyMembers = getFamilyMembers(numOfDependants);
        PersonalData personalData = PersonalData.Builder
                .create()
                .withName("test").withLastName("test").withMothersMaidenName("test")
                .withEducation(education)
                .withMaritalStatus(maritalStatus)
                .build();
        return NaturalPerson.Builder.create()
                .withFamilyMembers(familyMembers)
                .withPersonalData(personalData)
                .withFinanceData(new FinanceData(new SourceOfIncome(IncomeType.SELF_EMPLOYMENT, totalMonthlyIncomeInPln)))
                .build();
    }
}
