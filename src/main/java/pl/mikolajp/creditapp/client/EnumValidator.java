package pl.mikolajp.creditapp.client;

import pl.mikolajp.creditapp.core.model.Education;
import pl.mikolajp.creditapp.core.model.IncomeType;
import pl.mikolajp.creditapp.core.model.MaritalStatus;
import pl.mikolajp.creditapp.core.model.PurposeOfLoanType;

public class EnumValidator {
    public static boolean validateMaritalStatus(String maritalStatusString) {
        for (MaritalStatus maritalStatus : MaritalStatus.values()) {
            if (maritalStatus.name().equals(maritalStatusString)) {
                return true;
            }
            return false;
        }
        return false;
    }

    public static boolean validateEducation(String educationString) {
        for (Education education : Education.values()) {
            if (education.name().equals(educationString)) {
                return true;
            }
            return false;
        }
        return false;
    }

    public static boolean validateIncomeType(String incomeTypeString) {
        for (IncomeType incomeType : IncomeType.values()) {
            if (incomeType.name().equals(incomeTypeString)) {
                return true;
            }
            return false;
        }
        return false;
    }

    public static boolean validatePurposeOfLoanType(String purposeOfLoanTypeString) {
        for (PurposeOfLoanType purposeOfLoanType : PurposeOfLoanType.values()) {
            if (purposeOfLoanType.name().equals(purposeOfLoanTypeString)) {
                return true;
            }
            return false;
        }
        return false;
    }
}
