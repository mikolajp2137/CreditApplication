package pl.mikolajp.creditapp.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import pl.mikolajp.creditapp.core.exception.*;
import pl.mikolajp.creditapp.core.model.CreditApplication;
import pl.mikolajp.creditapp.core.model.Person;
import pl.mikolajp.creditapp.core.validation.CreditApplicationValidator;

import static pl.mikolajp.creditapp.core.Constants.MINIMAL_LOAN_AMOUNT_MORTGAGE;
import static pl.mikolajp.creditapp.core.DecisionType.*;

public class CreditApplicationService {
    private static final Logger log = LoggerFactory.getLogger(CreditApplicationService.class);
    private final PersonScoringCalculatorFactory personScoringCalculatorFactory;
    private final CreditRatingCalculator creditRatingCalculator;
    private final CreditApplicationValidator creditApplicationValidator;

    public CreditApplicationService(PersonScoringCalculatorFactory personScoringCalculatorFactory, CreditRatingCalculator creditRatingCalculator, CreditApplicationValidator creditApplicationValidator) {
        this.personScoringCalculatorFactory = personScoringCalculatorFactory;
        this.creditRatingCalculator = creditRatingCalculator;
        this.creditApplicationValidator = creditApplicationValidator;
    }

    public CreditApplicationDecision getDecision(CreditApplication creditApplication) {
        String id = creditApplication.getId().toString();
        MDC.put("id", id);

        try {
            creditApplicationValidator.validate(creditApplication);
            Person person = creditApplication.getPerson();
            int scoring = personScoringCalculatorFactory.getCalculator(person).calculate(person);
            CreditApplicationDecision decision;
            if (scoring < 300) {
                decision = new CreditApplicationDecision(NEGATIVE_SCORING, person.getPersonalData(), null, scoring);
            } else if (scoring <= 400) {
                decision = new CreditApplicationDecision(CONTACT_REQUIRED, person.getPersonalData(), null, scoring);
            } else {
                double creditRate = creditRatingCalculator.calculateCreditRating(creditApplication);
                if (creditRate >= creditApplication.getPurposeOfLoan().getAmount()) {
                    if (creditApplication.getPurposeOfLoan().getAmount() < MINIMAL_LOAN_AMOUNT_MORTGAGE) {
                        decision = new CreditApplicationDecision(NEGATIVE_REQUIREMENTS_NOT_MET, person.getPersonalData(), creditRate, scoring);
                    } else {
                        decision = new CreditApplicationDecision(POSITIVE, person.getPersonalData(), creditRate, scoring);
                    }
                } else {
                    decision = new CreditApplicationDecision(NEGATIVE_RATING, person.getPersonalData(), creditRate, scoring);
                }

            }
            log.info("Decission = " + decision.getDecisionType());
            return decision;
        } catch (ValidationException validationException) {
            log.error(validationException.getMessage());
            throw new IllegalStateException();
        } catch (Exception exception) {
            log.error(exception.getMessage());
            throw new IllegalStateException();
        } finally {
            log.info("Application processing is finished.");
        }
    }
}
