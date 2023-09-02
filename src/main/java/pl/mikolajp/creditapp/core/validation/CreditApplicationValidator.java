package pl.mikolajp.creditapp.core.validation;

import pl.mikolajp.creditapp.core.exception.ValidationException;
import pl.mikolajp.creditapp.core.model.CreditApplication;
import pl.mikolajp.creditapp.di.Inject;

public class CreditApplicationValidator implements Validator {
    @Inject
    private ObjectValidator objectValidator;

    public CreditApplicationValidator(ObjectValidator objectValidator) {
        this.objectValidator = objectValidator;
    }
    public CreditApplicationValidator(){}


    @Override
    public void validate(CreditApplication creditApplication) throws ValidationException {
        try {
            objectValidator.validate(creditApplication);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
