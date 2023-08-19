package pl.mikolajp.creditapp.client;

import pl.mikolajp.creditapp.core.Constants;

public class PhoneValidator {
    public static boolean validate(String input){
        return input.matches(Constants.PHONE_REGEX);
    }
}
