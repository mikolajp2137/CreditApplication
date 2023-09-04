package pl.mikolajp.creditapp.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.mikolajp.creditapp.core.model.NaturalPerson;
import pl.mikolajp.creditapp.core.model.ProcessedCreditApplication;
import pl.mikolajp.creditapp.core.model.SelfEmployed;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManager {
    private static final Logger log = LoggerFactory.getLogger(FileManager.class);
    private final static Path HOME_DIR = Paths.get(Constants.OUTPUT_PATH);
    private final static Path NATURAL_PERSON_DIR = HOME_DIR.resolve("natural-person");
    private final static Path SELF_EMPLOYMENT_DIR = HOME_DIR.resolve("self-employment");

    public void write(ProcessedCreditApplication creditApplication) throws IOException {
        Path personDir = getPersonDir(creditApplication);
        if (!Files.exists(personDir)) {
            Files.createDirectory(personDir);
        }
        Path appIdFile = personDir.resolve(creditApplication.getApplication().getId() + ".dat");
        if (!Files.exists(appIdFile)) {
            Files.createFile(appIdFile);
        }

        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             ObjectOutputStream out = new ObjectOutputStream(byteArrayOutputStream)) {
            out.writeObject(creditApplication);
            Files.write(appIdFile, byteArrayOutputStream.toByteArray());
            log.info(String.format("Application with id %s has been successfully saved.", creditApplication.getApplication().getId()));
        }
    }

    public ProcessedCreditApplication read(String appId, String personId) throws IOException, ClassNotFoundException {
        Path personDir = getPersonDir(personId);
        Path appIdFile = personDir.resolve(appId + ".dat");
        if (Files.exists(appIdFile)) {
            try (ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(Files.readAllBytes(appIdFile)))) {
                return (ProcessedCreditApplication) inputStream.readObject();
            }
        } else {
            log.error("Application with id {} not found.", appId);
            throw new IllegalStateException(String.format("Application with id %s not found.", appId));
        }
    }

    private Path getPersonDir(ProcessedCreditApplication creditApplication) {
        return creditApplication.getApplication().isNaturalPerson() ?
                NATURAL_PERSON_DIR.resolve(((NaturalPerson) creditApplication.getApplication().getPerson()).getPesel()) :
                SELF_EMPLOYMENT_DIR.resolve(((SelfEmployed) creditApplication.getApplication().getPerson()).getNip());
    }

    private Path getPersonDir(String personId) {
        String id = personId.replace("N-", "").replace("S-", "");
        return personId.startsWith("N-") ?
                NATURAL_PERSON_DIR.resolve(id) :
                SELF_EMPLOYMENT_DIR.resolve(id);
    }

    public void init() throws IOException {
        if (!Files.exists(HOME_DIR)) {
            Files.createDirectories(HOME_DIR);
        }
        if (!Files.exists(SELF_EMPLOYMENT_DIR)) {
            Files.createDirectories(SELF_EMPLOYMENT_DIR);
        }
        if (!Files.exists(NATURAL_PERSON_DIR)) {
            Files.createDirectories(NATURAL_PERSON_DIR);
        }
    }
}
