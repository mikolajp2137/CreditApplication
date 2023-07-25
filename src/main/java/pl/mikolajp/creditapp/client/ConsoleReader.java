package pl.mikolajp.creditapp.client;

import pl.mikolajp.creditapp.core.model.*;

import java.util.Scanner;

public class ConsoleReader {

    public CreditApplication readInputParameters() {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter your name");
        String name = in.next();

        System.out.println("Enter your last name");
        String lastName = in.next();

        System.out.println("Enter your mothers maiden name");
        String mothersMaidenName = in.next();

        System.out.println("What is your marital status? (SINGLE, MARRIED, DIVORCED, WIDOWED, SEPARATED)");
        MaritalStatus maritalStatus = MaritalStatus.valueOf(in.next());

        System.out.println("What is your education level? (NONE, PRIMARY, MIDDLE, SECONDARY, POST_SECONDARY, TERTIARY)");
        Education education = Education.valueOf(in.next());

        System.out.println("Enter your email address:");
        String email = in.next();

        System.out.println("Enter your phone number:");
        String phoneNumber = in.next();

        System.out.println("Enter total monthly income in PLN");
        double income = in.nextDouble();

        System.out.println("Enter number of family dependants (including applicant):");
        int numOfDependant = in.nextInt();

        System.out.println("What is purpose of loan? (MORTGAGE | PERSONAL_LOAN):");
        PurposeOfLoanType purposeOfLoanType = PurposeOfLoanType.valueOf(in.next());

        System.out.println("Enter loan amount");
        double purposeOfLoanAmount = in.nextDouble();

        System.out.println("Enter loan period (in years)");
        int period = in.nextInt();

        PersonalData personalData = new PersonalData(name, lastName, mothersMaidenName, income, maritalStatus, education, numOfDependant);
        ContactData contactData = new ContactData(email, phoneNumber);
        PurposeOfLoan purposeOfLoan = new PurposeOfLoan(purposeOfLoanType,purposeOfLoanAmount, period);


        return new CreditApplication(new Person(personalData,contactData), purposeOfLoan);
    }
}
