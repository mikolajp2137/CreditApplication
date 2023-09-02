package pl.mikolajp.creditapp.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import pl.mikolajp.creditapp.core.exception.*;
import pl.mikolajp.creditapp.core.model.CreditApplication;
import pl.mikolajp.creditapp.core.model.Person;
import pl.mikolajp.creditapp.core.validation.CompoundPostValidator;
import pl.mikolajp.creditapp.core.validation.CreditApplicationValidator;
import pl.mikolajp.creditapp.di.Inject;

import static pl.mikolajp.creditapp.core.DecisionType.*;

public class CreditApplicationService {
    private static final Logger log = LoggerFactory.getLogger(CreditApplicationService.class);
    @Inject
    private PersonScoringCalculatorFactory personScoringCalculatorFactory;
    @Inject
    private CreditRatingCalculator creditRatingCalculator;
    @Inject
    private CreditApplicationValidator creditApplicationValidator;
    @Inject
    private CompoundPostValidator compoundPostValidator;

    public CreditApplicationService(PersonScoringCalculatorFactory personScoringCalculatorFactory, CreditRatingCalculator creditRatingCalculator, CreditApplicationValidator creditApplicationValidator, CompoundPostValidator compoundPostValidator) {
        this.personScoringCalculatorFactory = personScoringCalculatorFactory;
        this.creditRatingCalculator = creditRatingCalculator;
        this.creditApplicationValidator = creditApplicationValidator;
        this.compoundPostValidator = compoundPostValidator;
    }
    public CreditApplicationService(){}

    public CreditApplicationDecision getDecision(CreditApplication creditApplication) {
        String id = creditApplication.getId().toString();
        MDC.put("id", id);

        try {
            Person person = creditApplication.getPerson();
            //step 1
            creditApplicationValidator.validate(creditApplication);
            //step 2
            int scoring = personScoringCalculatorFactory.getCalculator(person).calculate(creditApplication);
            //step 3
            double creditRate = creditRatingCalculator.calculateCreditRating(creditApplication);
            //step 4
            try {
                compoundPostValidator.validate(creditApplication, scoring, creditRate);
            } catch (RequirementsNotMetException requirementsNotMetException) {
                return new CreditApplicationDecision(NEGATIVE_REQUIREMENTS_NOT_MET, person.getPersonalData(), creditRate, scoring, requirementsNotMetException.getRequirementsNotMetCause());
            }

            CreditApplicationDecision decision = getCreditApplicationDecision(creditApplication, person, scoring, creditRate);
            log.info("Decission = " + decision.getDecisionType());
            return decision;
        } catch (ValidationException validationException) {
            log.error(validationException.getMessage());
            throw new IllegalStateException();
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new IllegalStateException();
        } finally {
            log.info("Application processing is finished.");
        }
    }

    private CreditApplicationDecision getCreditApplicationDecision(CreditApplication creditApplication, Person person, int scoring, double creditRate) {
        CreditApplicationDecision decision;
        if (scoring < 300) {
            decision = new CreditApplicationDecision(NEGATIVE_SCORING, person.getPersonalData(), creditRate, scoring);
        } else if (scoring <= 400) {
            decision = new CreditApplicationDecision(CONTACT_REQUIRED, person.getPersonalData(), creditRate, scoring);
        } else {
            if (creditRate >= creditApplication.getPurposeOfLoan().getAmount()) {
                decision = new CreditApplicationDecision(POSITIVE, person.getPersonalData(), creditRate, scoring);
            } else {
                decision = new CreditApplicationDecision(NEGATIVE_RATING, person.getPersonalData(), creditRate, scoring);
            }
        }
        return decision;
    }
}
