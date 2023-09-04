package pl.mikolajp.creditapp.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import pl.mikolajp.creditapp.core.anotation.NotNull;
import pl.mikolajp.creditapp.core.anotation.Regex;

import java.io.Serializable;

import static pl.mikolajp.creditapp.core.Constants.*;

public class PersonalData implements Serializable {
    public static final long serialVersionUID = 1l;
    @NotNull
    @Regex(NAME_REGEX)
    @JsonProperty
    private String name;
    @NotNull
    @Regex(LAST_NAME_REGEX)
    @JsonProperty
    private String lastName;
    @NotNull
    @Regex(LAST_NAME_REGEX)
    @JsonProperty
    private String mothersMaidenName;
    @NotNull
    @JsonProperty
    private MaritalStatus maritalStatus;
    @NotNull
    @JsonProperty
    private Education education;

    public PersonalData(){}
    private PersonalData(String name, String lastName, String mothersMaidenName, MaritalStatus maritalStatus, Education education) {
        this.name = name;
        this.lastName = lastName;
        this.mothersMaidenName = mothersMaidenName;
        this.maritalStatus = maritalStatus;
        this.education = education;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMothersMaidenName() {
        return mothersMaidenName;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public Education getEducation() {
        return education;
    }

    public static class Builder {
        private String name;
        private String lastName;
        private String mothersMaidenName;
        private MaritalStatus maritalStatus;
        private Education education;

        public static Builder create() {
            return new Builder();
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder withMothersMaidenName(String mothersMaidenName) {
            this.mothersMaidenName = mothersMaidenName;
            return this;
        }

        public Builder withMaritalStatus(MaritalStatus maritalStatus) {
            this.maritalStatus = maritalStatus;
            return this;
        }

        public Builder withEducation(Education education) {
            this.education = education;
            return this;
        }

        public PersonalData build() {
            return new PersonalData(name, lastName, mothersMaidenName, maritalStatus, education);
        }
    }
}
