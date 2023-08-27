package pl.mikolajp.creditapp;

import pl.mikolajp.creditapp.client.CreditApplicationReader;
import pl.mikolajp.creditapp.client.DummyCreditApplicationReader;
import pl.mikolajp.creditapp.core.*;
import pl.mikolajp.creditapp.core.scoring.EducationCalculator;
import pl.mikolajp.creditapp.core.scoring.IncomeCalculator;
import pl.mikolajp.creditapp.core.scoring.MaritalStatusCalculator;
import pl.mikolajp.creditapp.core.validation.CreditApplicationValidator;
import pl.mikolajp.creditapp.core.validation.PersonValidator;
import pl.mikolajp.creditapp.core.validation.PersonalDataValidator;
import pl.mikolajp.creditapp.core.validation.PurposeOfLoanValidator;

public class Main {
    public static void main(String[] args) {
        CreditApplicationReader reader = new DummyCreditApplicationReader();
        EducationCalculator educationCalculator = new EducationCalculator();
        MaritalStatusCalculator maritalStatusCalculator = new MaritalStatusCalculator();
        IncomeCalculator incomeCalculator = new IncomeCalculator();
        SelfEmployedScoringCalculator selfEmployedScoringCalculator = new SelfEmployedScoringCalculator();
        PersonScoringCalculatorFactory personScoringCalculatorFactory = new PersonScoringCalculatorFactory(selfEmployedScoringCalculator, educationCalculator, incomeCalculator, maritalStatusCalculator);
        CreditApplicationValidator creditApplicationValidator = new CreditApplicationValidator(new PersonValidator(new PersonalDataValidator()), new PurposeOfLoanValidator());
        CreditApplicationService service = new CreditApplicationService(personScoringCalculatorFactory, new CreditRatingCalculator(), creditApplicationValidator);
        CreditApplicationManager creditApplicationManager = new CreditApplicationManager(service);

        creditApplicationManager.add(reader.read());
        creditApplicationManager.add(reader.read());
        creditApplicationManager.add(reader.read());

        creditApplicationManager.startProcessing();
    }
}
