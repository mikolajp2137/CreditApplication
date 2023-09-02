package pl.mikolajp.creditapp.core;

import pl.mikolajp.creditapp.core.model.NaturalPerson;
import pl.mikolajp.creditapp.core.model.Person;
import pl.mikolajp.creditapp.core.model.SelfEmployed;
import pl.mikolajp.creditapp.core.scoring.*;
import pl.mikolajp.creditapp.di.Inject;

public class PersonScoringCalculatorFactory {
    @Inject
    private SelfEmployedScoringCalculator selfEmployedScoringCalculator;
    @Inject
    private EducationCalculator educationCalculator;
    @Inject
    private IncomeCalculator incomeCalculator;
    @Inject
    private MaritalStatusCalculator maritalStatusCalculator;
    @Inject
    private GuarantorsCalculator guarantorsCalculator;

    public PersonScoringCalculatorFactory(SelfEmployedScoringCalculator selfEmployedScoringCalculator, EducationCalculator educationCalculator, IncomeCalculator incomeCalculator, MaritalStatusCalculator maritalStatusCalculator, GuarantorsCalculator guarantorsCalculator) {
        this.selfEmployedScoringCalculator = selfEmployedScoringCalculator;
        this.educationCalculator = educationCalculator;
        this.incomeCalculator = incomeCalculator;
        this.maritalStatusCalculator = maritalStatusCalculator;
        this.guarantorsCalculator = guarantorsCalculator;
    }
    public PersonScoringCalculatorFactory(){}

    public ScoringCalculator getCalculator(Person person) {
        if (person instanceof NaturalPerson)
            return new CompoundScoringCalculator(guarantorsCalculator, educationCalculator, maritalStatusCalculator, incomeCalculator);
        else if (person instanceof SelfEmployed)
            return new CompoundScoringCalculator(guarantorsCalculator, educationCalculator, maritalStatusCalculator, incomeCalculator, selfEmployedScoringCalculator);
        return null;
    }
}
