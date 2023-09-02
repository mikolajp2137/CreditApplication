package pl.mikolajp.creditapp.core.model;

import pl.mikolajp.creditapp.core.anotation.NotNull;
import pl.mikolajp.creditapp.core.anotation.Regex;

import java.util.Objects;

import static pl.mikolajp.creditapp.core.Constants.PESEL_REGEX;

public class Guarantor implements Comparable<Guarantor> {
    @NotNull
    @Regex(PESEL_REGEX)
    private final String pesel;
    @NotNull
    private final Integer age;

    protected Guarantor(String pesel, int age) {
        this.pesel = pesel;
        this.age = age;
    }

    public String getPesel() {
        return pesel;
    }

    public int getAge() {
        return age;
    }

    @Override
    public int compareTo(Guarantor o) {
        if (o.pesel.compareTo(this.pesel) != 0) return o.pesel.compareTo(this.pesel);
        return this.age.compareTo(o.age);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guarantor guarantor = (Guarantor) o;
        return pesel.equals(guarantor.pesel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pesel);
    }

    public static class Builder {
        private String pesel;
        private Integer age;

        private Builder() {
        }

        public static Builder create() {
            return new Builder();
        }

        public Builder withPesel(String pesel) {
            this.pesel = pesel;
            return this;
        }

        public Builder withAge(Integer age) {
            this.age = age;
            return this;
        }

        public Guarantor build() {
            return new Guarantor(pesel, age);
        }
    }
}
