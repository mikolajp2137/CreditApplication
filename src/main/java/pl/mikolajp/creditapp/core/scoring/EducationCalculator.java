package pl.mikolajp.creditapp.core.scoring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.mikolajp.creditapp.core.model.Education;
import pl.mikolajp.creditapp.core.model.Person;

public class EducationCalculator implements ScoringCalculator {
    private static final Logger log = LoggerFactory.getLogger(EducationCalculator.class);

    @Override
    public int calculate(Person person) {
        Education education = person.getPersonalData().getEducation();
        int pointsForEducation = education.getScoringPoints();

        log.info("Education = " + education + ScoringUtils.getPointsString(pointsForEducation));
        return pointsForEducation;
    }
}
