package pl.mikolajp.creditapp.core;

import pl.mikolajp.creditapp.core.model.NaturalPerson;
import pl.mikolajp.creditapp.core.model.Person;
import pl.mikolajp.creditapp.core.model.SelfEmployed;
import pl.mikolajp.creditapp.core.scoring.*;

public class PersonScoringCalculatorFactory {
    private final SelfEmployedScoringCalculator selfEmployedScoringCalculator;
    private final EducationCalculator educationCalculator;
    private final IncomeCalculator incomeCalculator;
    private final MaritalStatusCalculator maritalStatusCalculator;
    private final GuarantorsCalculator guarantorsCalculator;

    public PersonScoringCalculatorFactory(SelfEmployedScoringCalculator selfEmployedScoringCalculator, EducationCalculator educationCalculator, IncomeCalculator incomeCalculator, MaritalStatusCalculator maritalStatusCalculator, GuarantorsCalculator guarantorsCalculator) {
        this.selfEmployedScoringCalculator = selfEmployedScoringCalculator;
        this.educationCalculator = educationCalculator;
        this.incomeCalculator = incomeCalculator;
        this.maritalStatusCalculator = maritalStatusCalculator;
        this.guarantorsCalculator = guarantorsCalculator;
    }

    public ScoringCalculator getCalculator(Person person) {
        if (person instanceof NaturalPerson)
            return new CompoundScoringCalculator(guarantorsCalculator, educationCalculator, maritalStatusCalculator, incomeCalculator);
        else if (person instanceof SelfEmployed)
            return new CompoundScoringCalculator(guarantorsCalculator, educationCalculator, maritalStatusCalculator, incomeCalculator, selfEmployedScoringCalculator);
        return null;
    }
}
