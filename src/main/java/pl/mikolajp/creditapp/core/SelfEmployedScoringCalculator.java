package pl.mikolajp.creditapp.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.mikolajp.creditapp.core.model.Person;
import pl.mikolajp.creditapp.core.model.SelfEmployed;
import pl.mikolajp.creditapp.core.scoring.*;

public class SelfEmployedScoringCalculator implements PersonCalculator {
    private static final Logger log = LoggerFactory.getLogger(SelfEmployedScoringCalculator.class);


    @Override
    public int calculate(SelfEmployed person) {
        SelfEmployed selfEmployed = (SelfEmployed) person;
        if (selfEmployed.getYearsSinceFounded() < 2) {
            log.info("Years since founded = " + selfEmployed.getYearsSinceFounded() + ScoringUtils.getPointsString(-200));
            return -200;
        }
        return 0;
    }
}
