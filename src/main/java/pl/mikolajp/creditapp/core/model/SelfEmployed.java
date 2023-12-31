package pl.mikolajp.creditapp.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import pl.mikolajp.creditapp.core.anotation.ExactlyOneNotNull;

import java.util.ArrayList;
import java.util.List;

@ExactlyOneNotNull({"nip", "regon"})
public class SelfEmployed extends Person {
    public static final long serialVersionUID = 1l;
    @JsonProperty
    private String nip;
    @JsonProperty
    private String regon;
    @JsonProperty
    private int yearsSinceFounded;

    public SelfEmployed(){}
    private SelfEmployed(int yearsSinceFounded, String nip, String regon, PersonalData personalData, ContactData contactData, FinanceData financeData, List<FamilyMember> familyMembers) {
        super(personalData, contactData, financeData, familyMembers);
        this.nip = nip;
        this.regon = regon;
        this.yearsSinceFounded = yearsSinceFounded;
    }

    public int getYearsSinceFounded() {
        return yearsSinceFounded;
    }

    public String getNip() {
        return nip;
    }

    public String getRegon() {
        return regon;
    }

    public static class Builder {
        private int yearsSinceFounded;
        private String nip;
        private String regon;
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

        public Builder withNip(String nip) {
            this.nip = nip;
            return this;
        }

        public Builder withRegon(String regon) {
            this.regon = regon;
            return this;
        }

        public Builder withYearsSinceFounded(int yearsSinceFounded) {
            this.yearsSinceFounded = yearsSinceFounded;
            return this;
        }

        public SelfEmployed build() {
            return new SelfEmployed(yearsSinceFounded, nip, regon, personalData, contactData, financeData, familyMembers);
        }

    }
}
