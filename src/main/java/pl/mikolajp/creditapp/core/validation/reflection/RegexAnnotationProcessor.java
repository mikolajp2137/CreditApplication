package pl.mikolajp.creditapp.core.validation.reflection;

import pl.mikolajp.creditapp.core.anotation.Regex;
import pl.mikolajp.creditapp.core.exception.ValidationException;
import pl.mikolajp.creditapp.core.validation.ValidationUtils;

import java.lang.reflect.Field;

public class RegexAnnotationProcessor implements FieldAnnotationProcessor {
    @Override
    public void process(Object object, Field field) throws IllegalAccessException, ValidationException {
        if (field.isAnnotationPresent(Regex.class)) {
            Regex annotation = field.getAnnotation(Regex.class);
            String fieldValue = (String) field.get(object);
            ValidationUtils.validateRegex(field.getName(), fieldValue, annotation.value());
        }
    }
}
