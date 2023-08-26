package pl.mikolajp.creditapp.core.validation;

import pl.mikolajp.creditapp.core.Constants;
import pl.mikolajp.creditapp.core.exception.ValidationException;
import pl.mikolajp.creditapp.core.model.CreditApplication;
import pl.mikolajp.creditapp.core.model.PersonalData;

import static pl.mikolajp.creditapp.core.Constants.*;
import static pl.mikolajp.creditapp.core.Constants.NAME_REGEX;

public class PersonalDataValidator implements Validator {

    @Override
    public void validate(CreditApplication creditApplication) throws ValidationException {
        PersonalData personalData = creditApplication.getPerson().getPersonalData();

        ValidationUtils.validateNotNull("name",personalData.getName());
        ValidationUtils.validateRegex("name",personalData.getName(), NAME_REGEX);

        ValidationUtils.validateNotNull("lastName",personalData.getLastName());
        ValidationUtils.validateRegex("lastName",personalData.getLastName(), LAST_NAME_REGEX);

        ValidationUtils.validateNotNull("mothersMaidenName",personalData.getMothersMaidenName());
        ValidationUtils.validateRegex("mothersMaidenName",personalData.getMothersMaidenName(), LAST_NAME_REGEX);

        ValidationUtils.validateNotNull("education",personalData.getEducation());

        ValidationUtils.validateNotNull("maritalStatus",personalData.getMaritalStatus());
    }
}
