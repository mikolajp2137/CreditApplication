package pl.mikolajp.creditapp.client;

import pl.mikolajp.creditapp.core.model.CreditApplication;
import pl.mikolajp.creditapp.util.ObjectMapperService;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileCreditApplicationReader implements CreditApplicationReader{
    private final Path path;

    public FileCreditApplicationReader(Path path) {
        this.path = path;
    }

    @Override
    public CreditApplication read() {
        try {
            String content = Files.readString(path);
            return ObjectMapperService.OBJECT_MAPPER.readValue(content, CreditApplication.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }
}
