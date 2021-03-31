package seedu.duke.exception;

import seedu.duke.Constants;

@SuppressWarnings("serial")
public class InvalidInputException extends BaseException {
    public static enum Type {
        EMPTY_STRING(Constants.INVALID_INPUT_EMPTY_STRING),
        UNKNOWN_COMMAND(Constants.INVALID_INPUT_UNKNOWN_COMMAND),
        INVALID_NRIC(Constants.INVALID_INPUT_INVALID_NRIC),
        PATIENT_EXISTED(Constants.INVALID_INPUT_PATIENT_EXISTED),
        NO_PATIENT_LOADED(Constants.INVALID_INPUT_NO_PATIENT_LOADED),
        EMPTY_DESCRIPTION(Constants.INVALID_INPUT_EMPTY_DESCRIPTION),
        INVALID_DATE(Constants.INVALID_INPUT_INVALID_DATE),
        INVALID_PATIENT(Constants.INVALID_INPUT_INVALID_PATIENT);

        public final String message;

        private Type(String message) {
            this.message = message;
        }
    }

    /**
     * This is the constructor of the exception class for invalid input.
     * @param type type of invalid input received from user
     */
    public InvalidInputException(Type type) {
        this(type, null);
    }

    /**
     * This is the constructor of the exception class for invalid input, with a cause as parameter.
     * @param type type of invalid input received from user
     * @param cause cause of this subclass of exception being thrown
     */
    public InvalidInputException(Type type, Throwable cause) {
        super(Constants.INVALID_INPUT, type.message, cause);
    }
}
