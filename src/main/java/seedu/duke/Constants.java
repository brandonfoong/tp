package seedu.duke;

public class Constants {
    public static final String ADD_INFO_MESSAGE = "Add a patient to the list\n"
            + "Command prefix: add\n"
            + "Argument(s): name, IC number\n"
            + "Usage: add /<IC_NUMBER>\n"
            + "Example: add S1234567A\n\n";
    public static final String LIST_INFO_MESSAGE = "Show the list of all patients\n"
            + "Usage: list\n\n";
    public static final String LOAD_INFO_MESSAGE = "<WIP> Select a specified patient to add and retrieve records\n"
            + "Command prefix: load\n"
            + "Arguments(s): IC number\n"
            + "Usage: load <IC_NUMBER>\n"
            + "Example: load S1234567A\n\n";
    public static final String RECORD_CONSULTATION_INFO_MESSAGE = "Add a consultation record to the selected patient\n"
            + "Command prefix: record\n"
            + "Arguments(s): consultation details\n"
            + "Usage: record <CONSULTATION DETAILS>\n"
            + "Example: record fever\n\n";
    public static final String RETRIEVE_INFO_MESSAGE = "Retrieve past consultation record from the selected patient\n"
            + "Usage: retrieve\n\n";
    public static final String EXIT_INFO_MESSAGE = "Exit the program\n"
            + "Usage: exit\n\n";

    public static final String EXIT_MESSAGE = "Goodbye, we hope to see you again!";
}
