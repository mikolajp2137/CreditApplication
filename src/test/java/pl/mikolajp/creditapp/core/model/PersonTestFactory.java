package pl.mikolajp.creditapp.core.model;

public class PersonTestFactory {
    public static Person create() {
        PersonalData personalData = new PersonalData("t", "t", "t", MaritalStatus.SINGLE, Education.MIDDLE, 2);
        return new Person(personalData, null, null);
    }

    public static Person create(Education education) {
        PersonalData personalData = new PersonalData("t", "t", "t", MaritalStatus.SINGLE, education, 2);
        return new Person(personalData, null, null);
    }

    public static Person create(MaritalStatus maritalStatus) {
        PersonalData personalData = new PersonalData("t", "t", "t", maritalStatus, Education.MIDDLE, 2);
        return new Person(personalData, null, null);
    }

    public static Person create(int numOfDependants, SourceOfIncome... sourcesOfIncome) {
        PersonalData personalData = new PersonalData("t", "t", "t", MaritalStatus.SINGLE, Education.MIDDLE, numOfDependants);
        return new Person(personalData, null, new FinanceData(sourcesOfIncome));
    }

    public static Person create(double totalMonthlyIncomeInPln, int numOfDependants, Education education, MaritalStatus maritalStatus) {
        PersonalData personalData = new PersonalData("t", "t", "t", maritalStatus, education, numOfDependants);
        return new Person(personalData, null, new FinanceData(new SourceOfIncome(IncomeType.SELF_EMPLOYMENT, totalMonthlyIncomeInPln)));
    }
}
