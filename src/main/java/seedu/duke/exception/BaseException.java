package seedu.duke.exception;

import seedu.duke.Commons;

@SuppressWarnings("serial")
public abstract class BaseException extends Exception {
    public final String classMessage;

    /**
     * This is the constructor of the base exception class.
     * @param classMessage message for the subclass of exception
     * @param message message for with detail on the actual type of exception
     * @param cause cause of this subclass of exception being thrown
     */
    public BaseException(String classMessage, String message, Throwable cause) {
        super(message, cause);
        this.classMessage = classMessage;
    }

    @Override
    public String toString() {
        String s = classMessage + ":" + System.lineSeparator()
                + Commons.EXCEPTION_INDENT + getMessage();

        Throwable cause = this.getCause();
        if (cause != null) {
            s += System.lineSeparator()
                    + "... and is caused by ..." + System.lineSeparator()
                    + Commons.EXCEPTION_INDENT + cause.toString();
        }

        return s;
    }
}
