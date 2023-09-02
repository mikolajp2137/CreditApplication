package pl.mikolajp.creditapp.core.scoring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.mikolajp.creditapp.core.model.CreditApplication;
import pl.mikolajp.creditapp.core.model.Guarantor;

public class GuarantorsCalculator implements ScoringCalculator {
    private static final Logger log = LoggerFactory.getLogger(GuarantorsCalculator.class);

    @Override
    public int calculate(CreditApplication creditApplication) {
        int scoringAgeUnder40 = 0;
        int scoringAgeAbove40 = 0;
        for (Guarantor g : creditApplication.getGuarantors()) {
            if (g.getAge() < 40) scoringAgeUnder40 += 50;
            else scoringAgeAbove40 += 25;
        }
        if (scoringAgeUnder40 > 0)
            log.info("Points for guarantors under the age of 40: " + scoringAgeUnder40 + ". " + ScoringUtils.getPointsString(scoringAgeUnder40));
        if (scoringAgeAbove40 > 0)
            log.info("Points for guarantors above the age of 40: " + scoringAgeAbove40 + ". " + ScoringUtils.getPointsString(scoringAgeAbove40));

        return scoringAgeUnder40 + scoringAgeAbove40 ;
    }
}
