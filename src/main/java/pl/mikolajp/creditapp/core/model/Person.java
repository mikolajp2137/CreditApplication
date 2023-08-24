package pl.mikolajp.creditapp.core.model;

public class Person {
    private final PersonalData personalData;
    private final ContactData contactData;
    private final FinanceData financeData;

    private Person(PersonalData personalData, ContactData contactData, FinanceData financeData) {
        this.personalData = personalData;
        this.contactData = contactData;
        this.financeData = financeData;
    }

    public FinanceData getFinanceData() {
        return financeData;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public ContactData getContactData() {
        return contactData;
    }

    public static class Builder{
        private PersonalData personalData;
        private ContactData contactData;
        private FinanceData financeData;

        private Builder(){}
        public static Builder create(){ return new Builder();}
        public Builder withPersonalData(PersonalData personalData){
            this.personalData = personalData;
            return this;
        }
        public Builder withContactData(ContactData contactData){
            this.contactData = contactData;
            return this;
        }
        public Builder withFinanceData(FinanceData financeData){
            this.financeData = financeData;
            return this;
        }
        public Person build(){
            return new Person(personalData, contactData, financeData);
        }

    }

    public double getIncomePerFamilyMember(){
        double totalMonthlyIncome = 0;
        for (SourceOfIncome sourceOfIncome : financeData.getSourcesOfIncome()){
            totalMonthlyIncome += sourceOfIncome.getNetMonthlyIncome();
        }
        return totalMonthlyIncome / this.getPersonalData().getNumOfDependants();
    }
}
