package pl.mikolajp.creditapp.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import pl.mikolajp.creditapp.core.anotation.NotNull;
import pl.mikolajp.creditapp.core.anotation.Regex;

import java.util.ArrayList;
import java.util.List;

import static pl.mikolajp.creditapp.core.Constants.PESEL_REGEX;

public class NaturalPerson extends Person {
    public static final long serialVersionUID = 1l;
    @NotNull
    @Regex(PESEL_REGEX)
    @JsonProperty
    private String pesel;

    public NaturalPerson(){}
    private NaturalPerson(String pesel, PersonalData personalData, ContactData contactData, FinanceData financeData, List<FamilyMember> familyMembers) {
        super(personalData, contactData, financeData, familyMembers);
        this.pesel = pesel;
    }

    public String getPesel() {
        return pesel;
    }

    public static class Builder {
        private String pesel;
        private PersonalData personalData;
        private ContactData contactData;
        private FinanceData financeData;
        private List<FamilyMember> familyMembers = new ArrayList<>();

        private Builder() {
        }

        public static Builder create() {
            return new Builder();
        }

        public Builder withPersonalData(PersonalData personalData) {
            this.personalData = personalData;
            return this;
        }
        public Builder withFamilyMembers(List<FamilyMember> familyMembers) {
            this.familyMembers = familyMembers;
            return this;
        }
        public Builder withContactData(ContactData contactData) {
            this.contactData = contactData;
            return this;
        }

        public Builder withFinanceData(FinanceData financeData) {
            this.financeData = financeData;
            return this;
        }

        public Builder withPesel(String pesel) {
            this.pesel = pesel;
            return this;
        }

        public NaturalPerson build() {
            return new NaturalPerson(pesel, personalData, contactData, financeData, familyMembers);
        }

    }
}
