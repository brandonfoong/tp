package seedu.duke.exception;

import seedu.duke.Commons;

@SuppressWarnings("serial")
public class UnknownException extends BaseException {
    /**
     * This is the constructor of exception for unknown problems encountered.
     * @param cause cause of this subclass of exception being thrown
     */
    public UnknownException(Throwable cause) {
        super(Commons.UNKNOWN, "", cause);
    }
}
