package pl.mikolajp.creditapp;

import pl.mikolajp.creditapp.client.CreditApplicationReader;
import pl.mikolajp.creditapp.client.DummyCreditApplicationReader;
import pl.mikolajp.creditapp.core.*;
import pl.mikolajp.creditapp.core.validation.*;
import pl.mikolajp.creditapp.core.validation.reflection.*;
import pl.mikolajp.creditapp.di.ClassInitializer;

import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class Main {
    static {
        TimeZone.setDefault(TimeZone.getTimeZone(Constants.DEFAULT_SYSTEM_ZONE_ID));
        Locale.setDefault(Constants.DEFAULT_LOCALE);
    }
    public static void main(String[] args) throws Exception {
        CreditApplicationReader reader = new DummyCreditApplicationReader();

        List<ClassAnnotationProcessor> classProcessors = List.of(new ExactlyOneNotNullAnnotationProcessor());
        List<FieldAnnotationProcessor> fieldProcessors = List.of(new NotNullAnnotationProcessor(), new RegexAnnotationProcessor());
        final ObjectValidator objectValidator = new ObjectValidator(fieldProcessors, classProcessors);

        CompoundPostValidator compoundPostValidator= new CompoundPostValidator(new PurposeOfLoanPostValidator(), new ExpensesPostValidator());
        ClassInitializer classInitializer = new ClassInitializer();
        classInitializer.registerInstance(compoundPostValidator);
        classInitializer.registerInstance(objectValidator);
        CreditApplicationManager manager = (CreditApplicationManager) classInitializer.createInstance(CreditApplicationManager.class);

        manager.add(reader.read());
        manager.startProcessing();
    }
}
