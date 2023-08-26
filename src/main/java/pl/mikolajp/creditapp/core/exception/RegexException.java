package pl.mikolajp.creditapp.core.exception;

public class RegexException extends ValidationException {

    public RegexException(String field) {
        super(String.format("Field %s doesn't match regex", field));
    }
}
