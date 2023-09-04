package pl.mikolajp.creditapp.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import pl.mikolajp.creditapp.core.anotation.NotNull;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

public class FamilyMember implements Comparable<FamilyMember>, Serializable {
    public static final long serialVersionUID = 1l;
    @NotNull
    @JsonProperty
    private String name;
    @NotNull
    @JsonProperty
    private LocalDate birthDate;

    public FamilyMember(){}
    public FamilyMember(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    @Override
    public int compareTo(FamilyMember o) {
        return o.birthDate.compareTo(birthDate);
    }

    @Override
    public String toString() {
        return "FamilyMember{" +
                "name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
