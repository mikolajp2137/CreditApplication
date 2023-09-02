package pl.mikolajp.creditapp.core.validation.reflection;

import pl.mikolajp.creditapp.core.exception.ValidationException;

public interface ClassAnnotationProcessor {
    void process(Object object, Class aClass) throws IllegalAccessException, ValidationException;
}
