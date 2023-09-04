package pl.mikolajp.creditapp.core;

import pl.mikolajp.creditapp.core.exception.RequirementsNotMetCause;
import pl.mikolajp.creditapp.core.model.PersonalData;

import java.io.Serializable;
import java.util.Optional;


public class CreditApplicationDecision implements Serializable {
    public static final long serialVersionUID = 1l;
    private final transient Optional<RequirementsNotMetCause> requirementsNotMetCause;
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
