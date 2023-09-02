package pl.mikolajp.creditapp.core.validation;

import pl.mikolajp.creditapp.core.exception.RequirementsNotMetException;
import pl.mikolajp.creditapp.core.model.CreditApplication;

public interface PostValidator {
    void validate(CreditApplication creditApplication, int scoring, double rating) throws RequirementsNotMetException;
}
