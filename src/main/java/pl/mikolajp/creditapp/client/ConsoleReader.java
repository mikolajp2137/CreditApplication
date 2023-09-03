package pl.mikolajp.creditapp.client;

import pl.mikolajp.creditapp.core.Constants;
import pl.mikolajp.creditapp.core.model.*;

import java.time.ZoneId;
import java.util.Locale;
import java.util.Scanner;

// THIS CLASS IS NO LONGER USED
// TO BE DELETED LATER

public class ConsoleReader implements CreditApplicationReader {

    @Override
    public CreditApplication read() {
        Scanner in = new Scanner(System.in);

        String name = getName(in);
        String lastName = getLastName(in);
        String mothersMaidenName = getMothersName(in);
        MaritalStatus maritalStatus = getMaritalStatus(in);
        Education education = getEducation(in);
        String email = getEmail(in);
        String phoneNumber = getPhoneNumber(in);
        SourceOfIncome[] sourcesOfIncome = getSourceOfIncomes(in);
        int numOfDependant = getNumOfDependants(in);
        PurposeOfLoanType purposeOfLoanType = getPurposeOfLoanType(in);
        double purposeOfLoanAmount = getPurposeOfLoanAmount(in);
        int period = getPeriod(in);

        ContactData contactData = ContactData.Builder.create()
                .withEmail(email).withPhoneNumber(phoneNumber)
                .build();
        PurposeOfLoan purposeOfLoan = new PurposeOfLoan(purposeOfLoanType, purposeOfLoanAmount, period);
        FinanceData financeData = new FinanceData(sourcesOfIncome);

        return new CreditApplication(Locale.US, ZoneId.of("Europe/Warsaw"),
                NaturalPerson.Builder.create()
                        .withPersonalData(PersonalData
                                .Builder
                                .create()
                                .withName(name).withLastName(lastName).withMothersMaidenName(mothersMaidenName)
                                .withMaritalStatus(maritalStatus).withEducation(education)
                                .build())
                        .withContactData(contactData)
                        .withFinanceData(financeData)
                        .build(), purposeOfLoan);
    }

    private int getPeriod(Scanner in) {
        String input;
        do {
            System.out.println("Enter loan period (in years)");
            input = in.next();
        } while (!NumberValidator.validateInteger(input, 5, 10, 15, 20, 25, 40));
        int period = Integer.valueOf(input);
        return period;
    }

    private SourceOfIncome[] getSourceOfIncomes(Scanner in) {
        int numOfIncomeSources = getNumOfIncomeSources(in);
        SourceOfIncome[] sourcesOfIncome = new SourceOfIncome[numOfIncomeSources];
        for (int i = 1; i <= numOfIncomeSources; i++) {
            IncomeType incomeType = getIncomeType(in, i);
            double netMonthlyIncome = getNetMonthlyIncome(in, i);
            SourceOfIncome sourceOfIncome = new SourceOfIncome(incomeType, netMonthlyIncome);
            sourcesOfIncome[i - 1] = sourceOfIncome;
        }
        return sourcesOfIncome;
    }

    private double getNetMonthlyIncome(Scanner in, int i) {
        String input;
        do {
            System.out.println("Enter net monthly income of source of income " + i);
            input = in.next();
        } while (!NumberValidator.validateDoube(input, 0.0, Double.MAX_VALUE));
        return Double.valueOf(input);
    }

    private double getPurposeOfLoanAmount(Scanner in) {
        String input;
        do {
            System.out.println("Enter loan amount");
            input = in.next();
        } while (!NumberValidator.validateDoube(input, 0.0, Double.MAX_VALUE));
        return Double.valueOf(input);
    }

    private int getNumOfIncomeSources(Scanner in) {
        String input;
        do {
            System.out.println("How many sources of income do you have?");
            input = in.next();
        } while (!NumberValidator.validateInteger(input, 0, Integer.MAX_VALUE));
        return Integer.valueOf(input);
    }

    private int getNumOfDependants(Scanner in) {
        String input;
        do {
            System.out.println("Enter number of family dependants (including applicant)");
            input = in.next();
        } while (!NumberValidator.validateInteger(input, 1, Integer.MAX_VALUE));
        return Integer.valueOf(input);
    }

    private String getName(Scanner in) {
        String input;
        do {
            System.out.println("Enter your name");
            input = in.next();
        } while (!StringValidator.validateString(input, Constants.NAME_REGEX));
        return input;
    }

    private String getLastName(Scanner in) {
        String input;
        do {
            System.out.println("Enter your last name");
            input = in.next();
        } while (!StringValidator.validateString(input, Constants.LAST_NAME_REGEX));
        return input;
    }

    private String getMothersName(Scanner in) {
        String input;
        do {
            System.out.println("Enter your mothers maiden name");
            input = in.next();
        } while (!StringValidator.validateString(input, Constants.LAST_NAME_REGEX));
        return input;
    }

    private String getEmail(Scanner in) {
        String input;
        do {
            System.out.println("Enter your email address");
            input = in.next();
        } while (!StringValidator.validateString(input, Constants.EMAIL_REGEX));
        return input;
    }

    private String getPhoneNumber(Scanner in) {
        String input;
        do {
            System.out.println("Enter your phone number");
            input = in.next();
        } while (!PhoneValidator.validate(input));
        return input;
    }

    private PurposeOfLoanType getPurposeOfLoanType(Scanner in) {
        String purposeOfLoanTypeInput;
        do {
            System.out.println("What is purpose of loan? " + generatePurposeOfLoanTypeElements());
            purposeOfLoanTypeInput = in.next();
        } while (!EnumValidator.validatePurposeOfLoanType(purposeOfLoanTypeInput));
        PurposeOfLoanType purposeOfLoanType = PurposeOfLoanType.valueOf(purposeOfLoanTypeInput);
        return purposeOfLoanType;
    }

    private IncomeType getIncomeType(Scanner in, int i) {
        String incomeTypeInput;
        do {
            System.out.println("Enter type of source of income " + i + " " + generateIncomeTypeElements());
            incomeTypeInput = in.next();
        } while (EnumValidator.validateIncomeType(incomeTypeInput));
        IncomeType incomeType = IncomeType.valueOf(incomeTypeInput);
        return incomeType;
    }

    private Education getEducation(Scanner in) {
        String educationInput;
        do {
            System.out.println("What is your education level? " + generateEducationElements());
            educationInput = in.next();
        } while (!EnumValidator.validateEducation(educationInput));
        Education education = Education.valueOf(educationInput);
        return education;
    }

    private MaritalStatus getMaritalStatus(Scanner in) {
        String maritalStatusInput;
        do {
            System.out.println("What is your marital status? " + generateMaritalStatusElements());
            maritalStatusInput = in.next();
        } while (!EnumValidator.validateMaritalStatus(maritalStatusInput));
        MaritalStatus maritalStatus = MaritalStatus.valueOf(maritalStatusInput);
        return maritalStatus;
    }

    private String generateMaritalStatusElements() {
        String elements = "(";
        for (int i = 0; i < MaritalStatus.values().length; i++) {
            elements += MaritalStatus.values()[i].name();
            if (i < MaritalStatus.values().length - 1) elements += ", ";
        }
        elements += ")";
        return elements;
    }

    private String generateEducationElements() {
        String elements = "(";
        for (int i = 0; i < Education.values().length; i++) {
            elements += Education.values()[i].name();
            if (i < Education.values().length - 1) elements += ", ";
        }
        elements += ")";
        return elements;
    }

    private String generateIncomeTypeElements() {
        String elements = "(";
        for (int i = 0; i < IncomeType.values().length; i++) {
            elements += IncomeType.values()[i].name();
            if (i < IncomeType.values().length - 1) elements += ", ";
        }
        elements += ")";
        return elements;
    }

    private String generatePurposeOfLoanTypeElements() {
        String elements = "(";
        for (int i = 0; i < PurposeOfLoanType.values().length; i++) {
            elements += PurposeOfLoanType.values()[i].name();
            if (i < PurposeOfLoanType.values().length - 1) elements += ", ";
        }
        elements += ")";
        return elements;
    }
}
