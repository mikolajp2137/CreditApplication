package pl.mikolajp.creditapp.core.model;

public class PersonTestFactory {
    public static Person create(double totalMonthlyIncomeInPln, int numOfDependants, Education education, MaritalStatus maritalStatus) {
        PersonalData personalData = new PersonalData("t", "t", "t", totalMonthlyIncomeInPln, maritalStatus, education, numOfDependants);
        return new Person(personalData,null);
    }
}
