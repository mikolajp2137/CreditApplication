package pl.mikolajp.creditapp.core.model;

import pl.mikolajp.creditapp.core.anotation.NotNull;
import pl.mikolajp.creditapp.core.anotation.Regex;
import pl.mikolajp.creditapp.core.anotation.ValidateObject;

import java.util.Optional;

import static pl.mikolajp.creditapp.core.Constants.EMAIL_REGEX;
import static pl.mikolajp.creditapp.core.Constants.PHONE_REGEX;

public class ContactData {
    @NotNull
    @Regex(EMAIL_REGEX)
    private String email;
    @NotNull
    @Regex(PHONE_REGEX)
    private String phoneNumber;
    @NotNull
    @ValidateObject
    private Address homeAddress;
    @NotNull
    private Optional<Address> correspondenceAddress;

    private ContactData(String email, String phoneNumber, Address homeAddress, Optional<Address> correspondenceAddress) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.homeAddress = homeAddress;
        this.correspondenceAddress = correspondenceAddress;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public Optional<Address> getCorrespondenceAddress() {
        return correspondenceAddress;
    }

    public static class Builder {
        private String email;
        private String phoneNumber;
        private Address homeAddress;
        private Address correspondenceAddress;

        private Builder() {
        }

        public static Builder create() {
            return new Builder();
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder withHomeAddress(Address homeAddress) {
            this.homeAddress = homeAddress;
            return this;
        }

        public Builder withCorrespondenceAddress(Address correspondenceAddress) {
            this.correspondenceAddress = correspondenceAddress;
            return this;
        }

        public ContactData build() {
            Optional<Address> correspondenceAddress = this.homeAddress.equals(this.correspondenceAddress) ?
                    Optional.empty() : Optional.ofNullable(this.correspondenceAddress);

            return new ContactData(this.email, this.phoneNumber, this.homeAddress, correspondenceAddress);
        }
    }
}
