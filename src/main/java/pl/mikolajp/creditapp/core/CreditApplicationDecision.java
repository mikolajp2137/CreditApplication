package pl.mikolajp.creditapp.core;

import pl.mikolajp.creditapp.core.model.CreditApplication;
import pl.mikolajp.creditapp.core.model.Person;
import pl.mikolajp.creditapp.core.model.PersonalData;

import java.math.BigDecimal;

public class CreditApplicationDecision {
    public DecisionType getDecisionType() {
        return decisionType;
    }

    private final DecisionType decisionType;
    private final PersonalData personalData;
    private final Double creditRate;

    public CreditApplicationDecision(DecisionType decisionType, PersonalData personalData, Double creditRate) {
        this.decisionType = decisionType;
        this.personalData = personalData;
        this.creditRate = creditRate;
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
        }
        return null;
    }
}
