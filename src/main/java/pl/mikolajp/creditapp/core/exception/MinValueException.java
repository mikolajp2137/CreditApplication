package pl.mikolajp.creditapp.core.exception;

public class MinValueException extends ValidationException {

    public MinValueException(String field, int expMinValue) {
        super(String.format("Field %s is invalid. Min value = %s", field, expMinValue));
    }
    public MinValueException(String field, double expMinValue) {
        super(String.format("Field %s is invalid. Min value = %s", field, expMinValue));
    }
}
