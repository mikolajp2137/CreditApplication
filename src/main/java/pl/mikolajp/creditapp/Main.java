package pl.mikolajp.creditapp;

import pl.mikolajp.creditapp.client.ConsoleReader;
import pl.mikolajp.creditapp.core.CreditApplicationService;
import pl.mikolajp.creditapp.core.model.CreditApplication;
import pl.mikolajp.creditapp.core.model.Person;

public class Main {
    public static void main(String[] args) {
        CreditApplicationService service = new CreditApplicationService();
        CreditApplication creditApplication = new ConsoleReader().readInputParameters();

        String decision = service.getDecision(creditApplication);

        System.out.println(decision);
    }
}
