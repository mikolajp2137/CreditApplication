package pl.mikolajp.creditapp.core.model;

import java.time.ZoneId;
import java.util.Locale;
import java.util.Set;

public class CreditApplicationTestFactory {

    public static CreditApplication create(NaturalPerson person, PurposeOfLoan purposeOfLoan, Set<Guarantor> guarantors) {
        return new CreditApplication(Locale.US, ZoneId.of("Europe/Warsaw"), person, purposeOfLoan, guarantors);
    }

    public static CreditApplication create(NaturalPerson person, PurposeOfLoan purposeOfLoan) {
        return new CreditApplication(Locale.US, ZoneId.of("Europe/Warsaw"), person, purposeOfLoan);
    }

    public static CreditApplication create(SelfEmployed person, PurposeOfLoan purposeOfLoan) {
        return new CreditApplication(Locale.US, ZoneId.of("Europe/Warsaw"), person, purposeOfLoan);
    }

    public static CreditApplication create() {
        NaturalPerson person = PersonTestFactory.create(5000.00, 2, Education.MIDDLE, MaritalStatus.SEPARATED);
        PurposeOfLoan purposeOfLoan = new PurposeOfLoan(PurposeOfLoanType.MORTGAGE, 100.00, 35);
        CreditApplication creditApplication = new CreditApplication(Locale.US, ZoneId.of("Europe/Warsaw"), person, purposeOfLoan);

        return creditApplication;
    }

    public static CreditApplication create(double expectedLoanAmount) {
        NaturalPerson person = PersonTestFactory.create(4000.00, 1, Education.MIDDLE, MaritalStatus.SEPARATED);
        PurposeOfLoan purposeOfLoan = new PurposeOfLoan(PurposeOfLoanType.MORTGAGE, expectedLoanAmount, 25);
        CreditApplication creditApplication = new CreditApplication(Locale.US, ZoneId.of("Europe/Warsaw"), person, purposeOfLoan);

        return creditApplication;
    }
}
