package pl.mikolajp.creditapp.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pl.mikolajp.creditapp.core.anotation.ExactlyOneNotNull;
import pl.mikolajp.creditapp.core.anotation.NotNull;
import pl.mikolajp.creditapp.core.anotation.ValidateCollection;
import pl.mikolajp.creditapp.core.anotation.ValidateObject;

import java.io.Serializable;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

@ExactlyOneNotNull({"naturalPerson", "selfEmployed"})
public class CreditApplication implements Serializable {
    public static final long serialVersionUID = 1l;
    @NotNull
    @JsonIgnore
    private UUID id;
    @JsonProperty
    private ZoneId clientTimeZone;
    @JsonProperty
    private Locale clientLocale;
    @JsonIgnore
    private ZonedDateTime creationDateClientZone;
    @ValidateObject
    @JsonProperty
    private NaturalPerson naturalPerson;
    @ValidateObject
    @JsonProperty
    private SelfEmployed selfEmployed;
    @NotNull
    @ValidateObject
    @JsonProperty
    private PurposeOfLoan purposeOfLoan;
    @NotNull
    @ValidateCollection
    @JsonProperty
    private Set<Guarantor> guarantors;

    public CreditApplication(){}
    public CreditApplication(Locale clientLocale, ZoneId clientTimeZone, NaturalPerson naturalPerson, PurposeOfLoan purposeOfLoan) {
        this.naturalPerson = naturalPerson;
        this.purposeOfLoan = purposeOfLoan;
        this.id = UUID.randomUUID();
        this.clientTimeZone = clientTimeZone;
        this.creationDateClientZone = ZonedDateTime.now(clientTimeZone);
        this.guarantors = new TreeSet<>();
        this.clientLocale = clientLocale;
    }
    public CreditApplication(Locale clientLocale, ZoneId clientTimeZone, SelfEmployed selfEmployed, PurposeOfLoan purposeOfLoan) {
        this.selfEmployed = selfEmployed;
        this.purposeOfLoan = purposeOfLoan;
        this.id = UUID.randomUUID();
        this.clientTimeZone = clientTimeZone;
        this.creationDateClientZone = ZonedDateTime.now(clientTimeZone);
        this.guarantors = new TreeSet<>();
        this.clientLocale = clientLocale;
    }

    public CreditApplication(Locale clientLocale, ZoneId clientTimeZone, NaturalPerson naturalPerson, PurposeOfLoan purposeOfLoan, Set<Guarantor> guarantors) {
        this.naturalPerson = naturalPerson;
        this.purposeOfLoan = purposeOfLoan;
        this.id = UUID.randomUUID();
        this.clientTimeZone = clientTimeZone;
        this.creationDateClientZone = ZonedDateTime.now(clientTimeZone);
        this.guarantors = new TreeSet<>(guarantors);
        this.clientLocale = clientLocale;
    }
    public CreditApplication(Locale clientLocale, ZoneId clientTimeZone, SelfEmployed selfEmployed, PurposeOfLoan purposeOfLoan, Set<Guarantor> guarantors) {
        this.selfEmployed = selfEmployed;
        this.purposeOfLoan = purposeOfLoan;
        this.id = UUID.randomUUID();
        this.clientTimeZone = clientTimeZone;
        this.creationDateClientZone = ZonedDateTime.now(clientTimeZone);
        this.guarantors = new TreeSet<>(guarantors);
        this.clientLocale = clientLocale;
    }

    @JsonIgnore
    public boolean isNaturalPerson() {
        return naturalPerson != null;
    }

    public Locale getClientLocale() {
        return clientLocale;
    }

    public ZonedDateTime getCreationDateClientZone() {
        return creationDateClientZone;
    }

    public ZoneId getClientTimeZone() {
        return clientTimeZone;
    }

    public Set<Guarantor> getGuarantors() {
        return guarantors;
    }

    public UUID getId() {
        return id;
    }

    @JsonIgnore
    public Person getPerson() {
        return naturalPerson != null ? naturalPerson : selfEmployed;
    }

    public PurposeOfLoan getPurposeOfLoan() {
        return purposeOfLoan;
    }

    public void init() {
        this.id = UUID.randomUUID();
        this.creationDateClientZone = ZonedDateTime.now(clientTimeZone);
    }
}
