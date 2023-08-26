package pl.mikolajp.creditapp.core.model;

import java.util.Objects;

public class Address {
    private final String street;
    private final String houseNumber;
    private final String zipCode;
    private final String city;
    private final String state;

    private Address(String street, String houseNumber, String zipCode, String city, String state) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.zipCode = zipCode;
        this.city = city;
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(street, address.street) &&
                Objects.equals(houseNumber, address.houseNumber) &&
                Objects.equals(zipCode, address.zipCode) &&
                Objects.equals(city, address.city) &&
                Objects.equals(state, address.state);
    }

    public static class Builder {
        private String street;
        private String houseNumber;
        private String zipCode;
        private String city;
        private String state;

        public static Builder create() {
            return new Builder();
        }

        public Builder withStreet(String street) {
            this.street = street;
            return this;
        }

        public Builder withHouseNumber(String houseNumber) {
            this.houseNumber = houseNumber;
            return this;
        }

        public Builder withZipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public Builder withCity(String city) {
            this.city = city;
            return this;
        }

        public Builder withState(String state) {
            this.state = state;
            return this;
        }

        public Address build() {
            return new Address(street, houseNumber, zipCode, city, state);
        }
    }
}
