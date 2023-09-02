package pl.mikolajp.creditapp.core.validation;

import pl.mikolajp.creditapp.core.exception.RequirementsNotMetCause;
import pl.mikolajp.creditapp.core.exception.RequirementsNotMetException;
import pl.mikolajp.creditapp.core.model.CreditApplication;

import static pl.mikolajp.creditapp.core.Constants.MINIMAL_LOAN_AMOUNT_MORTGAGE;

public class PurposeOfLoanPostValidator implements PostValidator{
    @Override
    public void validate(CreditApplication creditApplication, int scoring, double rating) throws RequirementsNotMetException {
        if (creditApplication.getPurposeOfLoan().getAmount() < MINIMAL_LOAN_AMOUNT_MORTGAGE) {
            throw new RequirementsNotMetException(RequirementsNotMetCause.TOO_LOW_LOAN_AMOUNT);
        }
        }
}
