package pl.mikolajp.creditapp.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import pl.mikolajp.creditapp.core.anotation.NotNull;
import pl.mikolajp.creditapp.core.anotation.Regex;
import pl.mikolajp.creditapp.core.anotation.ValidateObject;

import java.io.Serializable;
import java.util.Optional;

import static pl.mikolajp.creditapp.core.Constants.EMAIL_REGEX;
import static pl.mikolajp.creditapp.core.Constants.PHONE_REGEX;

public class ContactData implements Serializable {
    public static final long serialVersionUID = 1l;
    @NotNull
    @Regex(EMAIL_REGEX)
    @JsonProperty
    private String email;
    @NotNull
    @Regex(PHONE_REGEX)
    @JsonProperty
    private String phoneNumber;
    @NotNull
    @ValidateObject
    @JsonProperty
    private Address homeAddress;
    @JsonProperty
    private transient Optional<Address> correspondenceAddress = Optional.empty();

    public ContactData(){}
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
