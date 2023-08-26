package pl.mikolajp.creditapp.core.validation;

import pl.mikolajp.creditapp.core.exception.*;

public class ValidationUtils {
    public static void validateNotNull(String field, Object object) throws ValidationException{
        if(object == null) throw new NotNullException(field);
    }

    public static void validateRegex(String field, String value, String regex) throws ValidationException{
        if(!value.matches(regex)) throw new RegexException(field);
    }

    public static void validateMinValue(String field, int expMinValue, int actualMinValue) throws ValidationException{
        if(expMinValue >= actualMinValue) throw new MinValueException(field, expMinValue);
    }
    public static void validateMinValue(String field, double expMinValue, double actualMinValue) throws ValidationException{
        if(expMinValue >= actualMinValue) throw new MinValueException(field, expMinValue);
    }

    public static void validateMaxValue(String field, int expMaxValue, int actualMaxValue) throws ValidationException{
        if(expMaxValue <= actualMaxValue) throw new MaxValueException(field, expMaxValue);
    }
    public static void validateMaxValue(String field, double expMaxValue, double actualMaxValue) throws ValidationException{
        if(expMaxValue <= actualMaxValue) throw new MaxValueException(field, expMaxValue);
    }
}
