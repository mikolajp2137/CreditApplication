package pl.mikolajp.creditapp.core.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ContactDataTest {

    @Test
    @DisplayName("should set Optional.empty correspondence address when home address is the same")
    public void test1(){
        //given & when
        ContactData contactData = ContactData.Builder.create()
                .withHomeAddress(Address.Builder.create()
                        .withStreet("Wrocławska")
                        .withHouseNumber("11")
                        .withCity("Wrocław")
                        .withZipCode("11-111")
                        .withState("Dolnośląskie")
                        .build())
                .withCorrespondenceAddress(Address.Builder.create()
                        .withStreet("Wrocławska")
                        .withHouseNumber("11")
                        .withCity("Wrocław")
                        .withZipCode("11-111")
                        .withState("Dolnośląskie")
                        .build())
                .build();
        //then
        assertTrue(contactData.getCorrespondenceAddress().isEmpty());
    }

    @Test
    @DisplayName("should set Optional.of correspondence address when home address is not the same")
    public void test2(){
        //given
        final Address homeAddress = Address.Builder.create()
                .withStreet("Grunwaldzka")
                .withHouseNumber("11")
                .withCity("Wrocław")
                .withZipCode("11-111")
                .withState("Dolnośląskie")
                .build();
        final Address correspondenceAddress = Address.Builder.create()
                .withStreet("Wrocławska")
                .withHouseNumber("11")
                .withCity("Wrocław")
                .withZipCode("11-111")
                .withState("Dolnośląskie")
                .build();
        // when
        ContactData contactData = ContactData.Builder.create()
                .withHomeAddress(homeAddress)
                .withCorrespondenceAddress(correspondenceAddress)
                .build();
        //then
        assertTrue(contactData.getCorrespondenceAddress().isPresent());
        assertEquals(correspondenceAddress, contactData.getCorrespondenceAddress().get());

    }
}