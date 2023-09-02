package pl.mikolajp.creditapp.core.model;

import pl.mikolajp.creditapp.core.anotation.NotNull;

public class FamilyMember implements Comparable<FamilyMember> {
    @NotNull
    private final String name;
    @NotNull
    private final Integer age;

    public FamilyMember(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    @Override
    public int compareTo(FamilyMember o) {
        return o.age.compareTo(age);
    }

    @Override
    public String toString() {
        return "FamilyMember{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
