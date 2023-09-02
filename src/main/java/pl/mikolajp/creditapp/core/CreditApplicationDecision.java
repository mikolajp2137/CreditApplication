package pl.mikolajp.creditapp.core;

import pl.mikolajp.creditapp.core.exception.RequirementsNotMetCause;
import pl.mikolajp.creditapp.core.model.PersonalData;

import java.math.BigDecimal;
import java.util.Optional;

import static pl.mikolajp.creditapp.core.Constants.MINIMAL_LOAN_AMOUNT_MORTGAGE;

public class CreditApplicationDecision {
    private final Optional<RequirementsNotMetCause> requirementsNotMetCause;
    private final DecisionType decisionType;
    private final PersonalData personalData;
    private final Double creditRate;
    private final Integer scoring;

    public CreditApplicationDecision(DecisionType decisionType, PersonalData personalData, Double creditRate, Integer scoring) {
        this.decisionType = decisionType;
        this.personalData = personalData;
        this.creditRate = creditRate;
        this.scoring = scoring;
        this.requirementsNotMetCause = Optional.empty();
    }
    public CreditApplicationDecision(DecisionType decisionType, PersonalData personalData, Double creditRate, Integer scoring, RequirementsNotMetCause requirementsNotMetCause) {
        this.decisionType = decisionType;
        this.personalData = personalData;
        this.creditRate = creditRate;
        this.scoring = scoring;
        this.requirementsNotMetCause = Optional.of(requirementsNotMetCause);
    }


    public DecisionType getDecisionType() {
        return decisionType;
    }

    public String getDecisionString() {
        switch (decisionType) {
            case POSITIVE -> {
                return "Congratulations " + personalData.getName() + " " + personalData.getLastName() + ", decision is positive";
            }
            case NEGATIVE_SCORING -> {
                return "Sorry " + personalData.getName() + " " + personalData.getLastName() + ", decision is negative";
            }
            case CONTACT_REQUIRED -> {
                return "Sorry " + personalData.getName() + " " + personalData.getLastName() + ",  bank requires additional documents. Our Consultant will contact you.";
            }
            case NEGATIVE_RATING -> {
                BigDecimal roundedCreditRate = new BigDecimal(creditRate).setScale(2);
                return "Sorry, " + personalData.getName() + " " + personalData.getLastName() + ", decision is negative. Bank can borrow only " + roundedCreditRate;
            }
            case NEGATIVE_REQUIREMENTS_NOT_MET -> {
                switch (requirementsNotMetCause.get()){
                    case TOO_HIGH_PERSONAL_EXPENSES -> {
                        return "Sorry, " + personalData.getName() + " " + personalData.getLastName() + ", decision is negative. Personal expenses are too high";
                    }
                    case TOO_LOW_LOAN_AMOUNT -> {
                        return "Sorry, " + personalData.getName() + " " + personalData.getLastName() + ", decision is negative. Minimum loan amount for mortgage is "+ MINIMAL_LOAN_AMOUNT_MORTGAGE;
                    }
                }
            }
        }
        return null;
    }

    public Optional<RequirementsNotMetCause> getRequirementsNotMetCause() {
        return requirementsNotMetCause;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public int getScoring() {
        return scoring;
    }

    public Double getCreditRate() {
        return creditRate;
    }
}
