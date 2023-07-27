package pl.mikolajp.creditapp;

import pl.mikolajp.creditapp.client.ConsoleReader;
import pl.mikolajp.creditapp.core.CreditApplicationDecision;
import pl.mikolajp.creditapp.core.CreditApplicationService;
import pl.mikolajp.creditapp.core.CreditRatingCalculator;
import pl.mikolajp.creditapp.core.PersonScoringCalculator;
import pl.mikolajp.creditapp.core.model.CreditApplication;
import pl.mikolajp.creditapp.core.scoring.EducationCalculator;
import pl.mikolajp.creditapp.core.scoring.IncomeCalculator;
import pl.mikolajp.creditapp.core.scoring.MaritalStatusCalculator;

public class Main {
    public static void main(String[] args) {
        PersonScoringCalculator calculator = new PersonScoringCalculator(new EducationCalculator(), new IncomeCalculator(), new MaritalStatusCalculator());
        CreditApplicationService service = new CreditApplicationService(calculator, new CreditRatingCalculator());
        CreditApplication creditApplication = new ConsoleReader().readInputParameters();

        CreditApplicationDecision decision = service.getDecision(creditApplication);

        System.out.println(decision.getDecisionString());
    }
}
