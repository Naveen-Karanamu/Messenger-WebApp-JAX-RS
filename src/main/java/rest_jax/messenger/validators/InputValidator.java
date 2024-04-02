package rest_jax.messenger.validators;

import java.util.regex.Pattern;

public class InputValidator {
    public static boolean isValidNumber(long input) {
    	String strInput = String.valueOf(input);
    	return Pattern.matches("\\d+", strInput);
    }
}
