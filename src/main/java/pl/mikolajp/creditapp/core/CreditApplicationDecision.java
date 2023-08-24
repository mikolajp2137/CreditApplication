package pl.mikolajp.creditapp.core;

import pl.mikolajp.creditapp.core.model.PersonalData;

import java.math.BigDecimal;

public class CreditApplicationDecision {
    public DecisionType getDecisionType() {
        return decisionType;
    }

    private final DecisionType decisionType;
    private final PersonalData personalData;
    private final Double creditRate;
    private final int scoring;

    public CreditApplicationDecision(DecisionType decisionType, PersonalData personalData, Double creditRate, int scoring) {
        this.decisionType = decisionType;
        this.personalData = personalData;
        this.creditRate = creditRate;
        this.scoring = scoring;
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

    public int getScoring() {
        return scoring;
    }

    public Double getCreditRate() {
        return creditRate;
    }
}
