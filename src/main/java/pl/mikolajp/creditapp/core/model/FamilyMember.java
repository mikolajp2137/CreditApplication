package pl.mikolajp.creditapp.core.model;

import pl.mikolajp.creditapp.core.anotation.NotNull;

import java.time.LocalDate;
import java.time.Period;

public class FamilyMember implements Comparable<FamilyMember> {
    @NotNull
    private final String name;
    @NotNull
    private final LocalDate birthDate;

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
