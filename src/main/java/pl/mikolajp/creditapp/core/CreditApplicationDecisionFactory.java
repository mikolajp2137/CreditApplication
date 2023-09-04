package pl.mikolajp.creditapp.core;

import pl.mikolajp.creditapp.core.model.CreditApplication;
import pl.mikolajp.creditapp.core.model.PersonalData;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ResourceBundle;

import static pl.mikolajp.creditapp.core.Constants.MINIMAL_LOAN_AMOUNT_MORTGAGE;

public class CreditApplicationDecisionFactory {

    public String getDecisionString(CreditApplication creditApplication, CreditApplicationDecision decision) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("translations", creditApplication.getClientLocale());
        PersonalData personalData = creditApplication.getPerson().getPersonalData();
        NumberFormat numberFormat = NumberFormat.getNumberInstance(creditApplication.getClientLocale());
        switch (decision.getDecisionType()) {
            case POSITIVE: {
                return String.format(resourceBundle.getString("decision1"), personalData.getName(), personalData.getLastName());
            }
            case NEGATIVE_SCORING: {
                return String.format(resourceBundle.getString("decision2"), personalData.getName(), personalData.getLastName());
            }
            case CONTACT_REQUIRED: {
                return String.format(resourceBundle.getString("decision3"), personalData.getName(), personalData.getLastName());
            }
            case NEGATIVE_RATING: {
                BigDecimal roundedCreditRate = new BigDecimal(decision.getCreditRate()).setScale(2);
                return String.format(resourceBundle.getString("decision4"), personalData.getName(), personalData.getLastName(), numberFormat.format(roundedCreditRate.doubleValue()));
            }
            case NEGATIVE_REQUIREMENTS_NOT_MET: {
                switch (decision.getRequirementsNotMetCause().get()) {
                    case TOO_HIGH_PERSONAL_EXPENSES: {
                        return String.format(resourceBundle.getString("decision5"), personalData.getName(), personalData.getLastName());
                    }
                    case TOO_LOW_LOAN_AMOUNT: {
                        return String.format(resourceBundle.getString("decision6"), personalData.getName(), personalData.getLastName(), numberFormat.format(MINIMAL_LOAN_AMOUNT_MORTGAGE));
                    }
                }
            }
        }
        return null;
    }
}
