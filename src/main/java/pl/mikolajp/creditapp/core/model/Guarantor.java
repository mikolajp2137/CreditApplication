package pl.mikolajp.creditapp.core.model;

import pl.mikolajp.creditapp.core.anotation.NotNull;
import pl.mikolajp.creditapp.core.anotation.Regex;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

import static pl.mikolajp.creditapp.core.Constants.PESEL_REGEX;

public class Guarantor implements Comparable<Guarantor> {
    @NotNull
    @Regex(PESEL_REGEX)
    private final String pesel;
    @NotNull
    private final LocalDate birthDate;

    protected Guarantor(String pesel, LocalDate birthDate) {
        this.pesel = pesel;
        this.birthDate = birthDate;
    }

    public String getPesel() {
        return pesel;
    }

    public Integer getAge() {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    @Override
    public int compareTo(Guarantor o) {
        if (o.pesel.compareTo(this.pesel) != 0) return o.pesel.compareTo(this.pesel);
        return this.birthDate.compareTo(o.birthDate);
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
        private LocalDate birthDate;

        private Builder() {
        }

        public static Builder create() {
            return new Builder();
        }

        public Builder withPesel(String pesel) {
            this.pesel = pesel;
            return this;
        }

        public Builder withAge(LocalDate birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public Guarantor build() {
            return new Guarantor(pesel, birthDate);
        }
    }
}
