package pl.mikolajp.creditapp.core.scoring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.mikolajp.creditapp.core.model.CreditApplication;
import pl.mikolajp.creditapp.core.model.Person;

public class IncomeCalculator {
    private static final Logger log = LoggerFactory.getLogger(IncomeCalculator.class);

    public int calculateIncome(Person person) {
        double incomePerFamilyMember = person.getIncomePerFamilyMember();
        int pointsForIncome = (int) (incomePerFamilyMember / 1000) * 100;
        log.info("Income per family member = " + incomePerFamilyMember + ". (+" + ScoringUtils.getPointsString(pointsForIncome));
        return pointsForIncome;
    }
}
