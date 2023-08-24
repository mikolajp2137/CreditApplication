package pl.mikolajp.creditapp.core.model;

public class PersonTestFactory {
    public static Person create() {
        PersonalData personalData = PersonalData.Builder
                .create()
                .withName("test").withLastName("test").withMothersMaidenName("test")
                .withEducation(Education.MIDDLE).withNumOfDependants(2)
                .withMaritalStatus(MaritalStatus.SINGLE)
                .build();
        return Person.Builder.create()
                .withPersonalData(personalData)
                .build();
    }

    public static Person create(Education education) {
        PersonalData personalData = PersonalData.Builder
                .create()
                .withName("test").withLastName("test").withMothersMaidenName("test")
                .withEducation(education).withNumOfDependants(2)
                .withMaritalStatus(MaritalStatus.SINGLE)
                .build();
        return Person.Builder.create()
                .withPersonalData(personalData)
                .build();
    }

    public static Person create(MaritalStatus maritalStatus) {
        PersonalData personalData = PersonalData.Builder
                .create()
                .withName("test").withLastName("test").withMothersMaidenName("test")
                .withEducation(Education.MIDDLE).withNumOfDependants(2)
                .withMaritalStatus(maritalStatus)
                .build();
        return Person.Builder.create()
                .withPersonalData(personalData)
                .build();
    }

    public static Person create(int numOfDependants, SourceOfIncome... sourcesOfIncome) {
        PersonalData personalData = PersonalData.Builder
                .create()
                .withName("test").withLastName("test").withMothersMaidenName("test")
                .withEducation(Education.MIDDLE).withNumOfDependants(numOfDependants)
                .withMaritalStatus(MaritalStatus.SINGLE)
                .build();
        return Person.Builder.create()
                .withPersonalData(personalData)
                .withFinanceData(new FinanceData(sourcesOfIncome))
                .build();
    }

    public static Person create(double totalMonthlyIncomeInPln, int numOfDependants, Education education, MaritalStatus maritalStatus) {
        PersonalData personalData = PersonalData.Builder
                .create()
                .withName("test").withLastName("test").withMothersMaidenName("test")
                .withEducation(education).withNumOfDependants(numOfDependants)
                .withMaritalStatus(maritalStatus)
                .build();
        return Person.Builder.create()
                .withPersonalData(personalData)
                .withFinanceData(new FinanceData(new SourceOfIncome(IncomeType.SELF_EMPLOYMENT, totalMonthlyIncomeInPln)))
                .build();
    }
}
