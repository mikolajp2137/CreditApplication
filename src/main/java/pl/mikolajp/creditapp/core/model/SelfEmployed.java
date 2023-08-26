package pl.mikolajp.creditapp.core.model;

import java.util.List;

public class SelfEmployed extends Person {
    private final String nip;
    private final String regon;
    private final int yearsSinceFounded;

    private SelfEmployed(int yearsSinceFounded, String nip, String regon, PersonalData personalData, ContactData contactData, FinanceData financeData, List<FamilyMember> familyMembers) {
        super(personalData, contactData, financeData, familyMembers);
        this.nip = nip;
        this.regon = regon;
        this.yearsSinceFounded = yearsSinceFounded;
    }

    public int getYearsSinceFounded() {
        return yearsSinceFounded;
    }

    public static class Builder {
        private int yearsSinceFounded;
        private String nip;
        private String regon;
        private PersonalData personalData;
        private ContactData contactData;
        private FinanceData financeData;
        private List<FamilyMember> familyMembers;

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
