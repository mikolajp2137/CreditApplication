package pl.mikolajp.creditapp.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.mikolajp.creditapp.core.model.Person;
import pl.mikolajp.creditapp.core.scoring.EducationCalculator;
import pl.mikolajp.creditapp.core.scoring.IncomeCalculator;
import pl.mikolajp.creditapp.core.scoring.MaritalStatusCalculator;

public class PersonScoringCalculator {
    private static final Logger log = LoggerFactory.getLogger(PersonScoringCalculator.class);
    private final EducationCalculator educationCalculator;
    private final IncomeCalculator incomeCalculator;
    private final MaritalStatusCalculator maritalStatusCalculator;

    public PersonScoringCalculator(EducationCalculator educationCalculator, IncomeCalculator incomeCalculator, MaritalStatusCalculator maritalStatusCalculator) {
        this.educationCalculator = educationCalculator;
        this.incomeCalculator = incomeCalculator;
        this.maritalStatusCalculator = maritalStatusCalculator;
    }

    public int calculate(Person person) {
        int scoring = educationCalculator.calculateEducation(person) + incomeCalculator.calculateIncome(person) + maritalStatusCalculator.calculateMaritalStatus(person);
        log.info("Calculated scoring = " + scoring + " points");
        return scoring;
    }
}
