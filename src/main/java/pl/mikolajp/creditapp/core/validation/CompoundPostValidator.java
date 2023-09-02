package pl.mikolajp.creditapp.core.validation;

import pl.mikolajp.creditapp.core.exception.RequirementsNotMetException;
import pl.mikolajp.creditapp.core.model.CreditApplication;

public class CompoundPostValidator implements PostValidator {
    private final PostValidator[] postValidators;

    public CompoundPostValidator(PostValidator... postValidators) {
        this.postValidators = postValidators;
    }

    @Override
    public void validate(CreditApplication creditApplication, int scoring, double rating) throws RequirementsNotMetException {
        for (PostValidator postValidator : postValidators) {
            postValidator.validate(creditApplication, scoring, rating);
        }
    }
}
