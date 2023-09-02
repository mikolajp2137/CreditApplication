package pl.mikolajp.creditapp.core.validation;

import pl.mikolajp.creditapp.core.exception.RequirementsNotMetCause;
import pl.mikolajp.creditapp.core.exception.RequirementsNotMetException;
import pl.mikolajp.creditapp.core.model.CreditApplication;
import pl.mikolajp.creditapp.core.model.ExpenseType;

public class ExpensesPostValidator implements PostValidator {
    @Override
    public void validate(CreditApplication creditApplication, int scoring, double rating) throws RequirementsNotMetException {
        double balance = creditApplication.getPerson().getBalance();
        double personalExpenses = creditApplication.getPerson().getFinanceData().getSumOfExpenses(ExpenseType.PERSONAL);

        double percentage = personalExpenses * 100 / balance;

        if(percentage > 40) throw new RequirementsNotMetException(RequirementsNotMetCause.TOO_HIGH_PERSONAL_EXPENSES);
    }
}
