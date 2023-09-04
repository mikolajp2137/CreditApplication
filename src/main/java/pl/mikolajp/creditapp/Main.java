package pl.mikolajp.creditapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.mikolajp.creditapp.client.CreditApplicationReader;
import pl.mikolajp.creditapp.client.DummyCreditApplicationReader;
import pl.mikolajp.creditapp.client.FileCreditApplicationReader;
import pl.mikolajp.creditapp.core.*;
import pl.mikolajp.creditapp.core.model.CreditApplication;
import pl.mikolajp.creditapp.core.validation.*;
import pl.mikolajp.creditapp.core.validation.reflection.*;
import pl.mikolajp.creditapp.di.ClassInitializer;

import java.nio.file.*;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(FileManager.class);

    static {
        TimeZone.setDefault(TimeZone.getTimeZone(Constants.DEFAULT_SYSTEM_ZONE_ID));
        Locale.setDefault(Constants.DEFAULT_LOCALE);
    }

    public static void main(String[] args) throws Exception {
//        CreditApplicationReader reader = new DummyCreditApplicationReader();
        List<ClassAnnotationProcessor> classProcessors = List.of(new ExactlyOneNotNullAnnotationProcessor());
        List<FieldAnnotationProcessor> fieldProcessors = List.of(new NotNullAnnotationProcessor(), new RegexAnnotationProcessor());
        final ObjectValidator objectValidator = new ObjectValidator(fieldProcessors, classProcessors);
        CompoundPostValidator compoundPostValidator = new CompoundPostValidator(new PurposeOfLoanPostValidator(), new ExpensesPostValidator());
        ClassInitializer classInitializer = new ClassInitializer();
        classInitializer.registerInstance(compoundPostValidator);
        classInitializer.registerInstance(objectValidator);
        CreditApplicationManager manager = (CreditApplicationManager) classInitializer.createInstance(CreditApplicationManager.class);


        manager.init();


        if (args != null && args.length == 2 && args[1].matches("[NS]-\\d*")) {
            String appId = args[0];
            String personId = args[1];
            manager.loadApplication(appId, personId);
        } else {
            WatchService watchService = FileSystems.getDefault().newWatchService();
            Path homeDir = Paths.get(Constants.OUTPUT_PATH);
            homeDir.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
            WatchKey watchKey;
            while ((watchKey=watchService.take()) !=null){
                log.info("New event fired");
                for (WatchEvent event : watchKey.pollEvents()){
                    log.info("New file detected {}", event.context());
                    if (event.context().toString().endsWith(".json")){
                        Path pathToFile = homeDir.resolve(event.context().toString());
                        CreditApplication creditApplication = new FileCreditApplicationReader(pathToFile).read();
                        creditApplication.init();
                        manager.add(creditApplication);
                        Files.deleteIfExists(pathToFile);
                    }else {
                        log.info("File {} skipped", event.context());
                    }
                }
                manager.startProcessing();
                watchKey.reset();
            }


//            manager.add(reader.read());
//            manager.startProcessing();
        }
    }
}
