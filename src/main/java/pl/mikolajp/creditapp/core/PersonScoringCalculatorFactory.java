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

    public PersonScoringCalculatorFactory(SelfEmployedScoringCalculator selfEmployedScoringCalculator, EducationCalculator educationCalculator, IncomeCalculator incomeCalculator, MaritalStatusCalculator maritalStatusCalculator) {
        this.selfEmployedScoringCalculator = selfEmployedScoringCalculator;
        this.educationCalculator = educationCalculator;
        this.incomeCalculator = incomeCalculator;
        this.maritalStatusCalculator = maritalStatusCalculator;
    }

    public PersonCalculator getCalculator(Person person) {
        if (person instanceof NaturalPerson)
            return new CompoundScoringCalculator(educationCalculator, maritalStatusCalculator, incomeCalculator);
        else if (person instanceof SelfEmployed)
            return new CompoundScoringCalculator(educationCalculator, maritalStatusCalculator, incomeCalculator, selfEmployedScoringCalculator);
        return null;
    }
}
