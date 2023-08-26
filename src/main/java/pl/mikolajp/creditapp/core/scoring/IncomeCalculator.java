package pl.mikolajp.creditapp.core.scoring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.mikolajp.creditapp.core.model.CreditApplication;
import pl.mikolajp.creditapp.core.model.Person;

public class IncomeCalculator implements PersonCalculator{
    private static final Logger log = LoggerFactory.getLogger(IncomeCalculator.class);

    @Override
    public int calculate(Person person) {
        double incomePerFamilyMember = person.getIncomePerFamilyMember();
        int pointsForIncome = (int) (incomePerFamilyMember / 1000) * 100;
        log.info("Income per family member = " + incomePerFamilyMember + ". (+" + ScoringUtils.getPointsString(pointsForIncome));
        if(person.getFinanceData().getSourcesOfIncome().size() > 1) {
            log.info("Extra 100 points for multiple sources of income = " + ScoringUtils.getPointsString(100));
            pointsForIncome += 100;
        }

        return pointsForIncome;
    }
}
