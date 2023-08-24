package pl.mikolajp.creditapp.core.model;

public class CreditApplicationTestFactory {

    public static CreditApplication create(Person person, PurposeOfLoan purposeOfLoan) {

        CreditApplication creditApplication = new CreditApplication(person, purposeOfLoan);

        return creditApplication;
    }

    public static CreditApplication create() {
        Person person = PersonTestFactory.create(5000.00, 2, Education.MIDDLE, MaritalStatus.SEPARATED);
        PurposeOfLoan purposeOfLoan = new PurposeOfLoan(PurposeOfLoanType.MORTGAGE, 100.00, 35);
        CreditApplication creditApplication = new CreditApplication(person, purposeOfLoan);

        return creditApplication;
    }
    public static CreditApplication create(double expectedLoanAmount) {
        Person person = PersonTestFactory.create(4000.00, 1, Education.MIDDLE, MaritalStatus.SEPARATED);
        PurposeOfLoan purposeOfLoan = new PurposeOfLoan(PurposeOfLoanType.MORTGAGE, expectedLoanAmount, 25);
        CreditApplication creditApplication = new CreditApplication(person, purposeOfLoan);

        return creditApplication;
    }
}
