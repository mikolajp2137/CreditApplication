package pl.mikolajp.creditapp.core.validation;

import pl.mikolajp.creditapp.core.exception.ValidationException;
import pl.mikolajp.creditapp.core.model.CreditApplication;

public interface Validator {
    void validate(CreditApplication creditApplication) throws ValidationException;
}
