package pl.mikolajp.creditapp.core.scoring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.mikolajp.creditapp.core.model.CreditApplication;
import pl.mikolajp.creditapp.core.model.MaritalStatus;
import pl.mikolajp.creditapp.core.model.Person;

public class MaritalStatusCalculator {
    private static final Logger log = LoggerFactory.getLogger(EducationCalculator.class);

    public int calculateMaritalStatus(Person person) {
        MaritalStatus maritalStatus = person.getPersonalData().getMaritalStatus();
        int pointsForMaritalStatus = maritalStatus.getScoringPoints();

        log.info("Marital status = " + maritalStatus + ScoringUtils.getPointsString(pointsForMaritalStatus));
        return pointsForMaritalStatus;
    }
}
