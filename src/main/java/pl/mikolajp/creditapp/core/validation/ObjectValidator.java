package pl.mikolajp.creditapp.core.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.mikolajp.creditapp.core.anotation.ValidateCollection;
import pl.mikolajp.creditapp.core.anotation.ValidateObject;
import pl.mikolajp.creditapp.core.exception.ValidationException;
import pl.mikolajp.creditapp.core.validation.reflection.ClassAnnotationProcessor;
import pl.mikolajp.creditapp.core.validation.reflection.FieldAnnotationProcessor;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class ObjectValidator {
    private static final Logger log = LoggerFactory.getLogger(ObjectValidator.class);
    private final List<FieldAnnotationProcessor> fieldAnnotationProcessors;
    private final List<ClassAnnotationProcessor> classAnnotationProcessors;

    public ObjectValidator(List<FieldAnnotationProcessor> fieldAnnotationProcessors, List<ClassAnnotationProcessor> classAnnotationProcessors) {
        this.fieldAnnotationProcessors = fieldAnnotationProcessors;
        this.classAnnotationProcessors = classAnnotationProcessors;
    }

    public void validate(Object object) throws IllegalAccessException, ValidationException {
        if (object != null) {
            final Class aClass = object.getClass();
            final Class superClass = object.getClass().getSuperclass();
            log.debug("Starting validation of class " + aClass.getSimpleName());
            processClass(object, aClass);
            processClass(object, superClass);
        }
    }

    private void processClass(Object object, Class aClass) throws IllegalAccessException, ValidationException {
        for (ClassAnnotationProcessor processor : classAnnotationProcessors) {
            processor.process(object, aClass);
        }
        for (Field field : aClass.getDeclaredFields()) {
            processField(object, field);
        }
    }

    private void processField(Object object, Field field) throws IllegalAccessException, ValidationException {
        log.debug("Starting validation of field " + field.getName());
        field.setAccessible(true);
        for (FieldAnnotationProcessor processor : fieldAnnotationProcessors) {
            processor.process(object, field);
        }
        Object fieldObject = field.get(object);
        if (field.isAnnotationPresent(ValidateObject.class)) {
            validate(fieldObject);
        } else if (field.isAnnotationPresent(ValidateCollection.class)) {
            Collection<Object> collection = (Collection) fieldObject;
            for (Object element : collection) {
                validate(element);
            }
        }
    }
}
