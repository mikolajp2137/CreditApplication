package pl.mikolajp.creditapp.core.exception;

public class MaxValueException extends ValidationException {

    public MaxValueException(String field, int expMaxValue) {
        super(String.format("Field %s is invalid. Max value = %s", field, expMaxValue));
    }
    public MaxValueException(String field, double expMaxValue) {
        super(String.format("Field %s is invalid. Max value = %s", field, expMaxValue));
    }
}
