package pl.mikolajp.creditapp.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import pl.mikolajp.creditapp.core.CreditApplicationDecision;

import java.io.Serializable;
import java.nio.file.Path;

public class ProcessedCreditApplication implements Serializable {
    public static final long serialVersionUID = 1l;
    @JsonProperty
    private CreditApplication application;
    @JsonProperty
    private CreditApplicationDecision decision;

    public ProcessedCreditApplication(){}
    public ProcessedCreditApplication(CreditApplication application, CreditApplicationDecision decision) {
        this.application = application;
        this.decision = decision;
    }

    public CreditApplication getApplication() {
        return application;
    }

    public CreditApplicationDecision getDecision() {
        return decision;
    }
}
