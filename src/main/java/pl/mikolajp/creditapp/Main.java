package pl.mikolajp.creditapp;

import pl.mikolajp.creditapp.client.CreditApplicationReader;
import pl.mikolajp.creditapp.client.DummyCreditApplicationReader;
import pl.mikolajp.creditapp.core.*;
import pl.mikolajp.creditapp.core.scoring.EducationCalculator;
import pl.mikolajp.creditapp.core.scoring.GuarantorsCalculator;
import pl.mikolajp.creditapp.core.scoring.IncomeCalculator;
import pl.mikolajp.creditapp.core.scoring.MaritalStatusCalculator;
import pl.mikolajp.creditapp.core.validation.*;
import pl.mikolajp.creditapp.core.validation.reflection.*;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        CreditApplicationReader reader = new DummyCreditApplicationReader();
        GuarantorsCalculator guarantorsCalculator = new GuarantorsCalculator();
        EducationCalculator educationCalculator = new EducationCalculator();
        MaritalStatusCalculator maritalStatusCalculator = new MaritalStatusCalculator();
        IncomeCalculator incomeCalculator = new IncomeCalculator();
        SelfEmployedScoringCalculator selfEmployedScoringCalculator = new SelfEmployedScoringCalculator();
        PersonScoringCalculatorFactory personScoringCalculatorFactory = new PersonScoringCalculatorFactory(selfEmployedScoringCalculator, educationCalculator, incomeCalculator, maritalStatusCalculator, guarantorsCalculator);
        Set<ClassAnnotationProcessor> classProcessors = Set.of(new ExactlyOneNotNullAnnotationProcessor());
        Set<FieldAnnotationProcessor> fieldProcessors = Set.of(new NotNullAnnotationProcessor(), new RegexAnnotationProcessor());
        CreditApplicationValidator creditApplicationValidator = new CreditApplicationValidator(new ObjectValidator(fieldProcessors, classProcessors));
        CompoundPostValidator compoundPostValidator= new CompoundPostValidator(new PurposeOfLoanPostValidator(), new ExpensesPostValidator());
        CreditApplicationService service = new CreditApplicationService(personScoringCalculatorFactory, new CreditRatingCalculator(), creditApplicationValidator, compoundPostValidator);
        CreditApplicationManager creditApplicationManager = new CreditApplicationManager(service);

        creditApplicationManager.add(reader.read());


        creditApplicationManager.startProcessing();
    }
}
