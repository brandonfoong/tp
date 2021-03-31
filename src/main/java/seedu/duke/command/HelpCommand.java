package seedu.duke.command;

import seedu.duke.Commons;
import seedu.duke.Data;
import seedu.duke.Ui;

import java.util.HashMap;

public class HelpCommand extends Command {
    /**
     * This is the constructor of the command. Arguments are passed to parent class.
     * @param ui Instance of Ui class, for UI input/output
     * @param data Instance of Data class, for manipulating patient list and read/write miscellaneous config
     * @param arguments Arguments decomposed from the full command given by the user
     */
    public HelpCommand(Ui ui, Data data, HashMap<String, String> arguments) {
        super(ui, data, arguments);
    }

    @Override
    public void execute() {
        String[] commands = arguments.get("payload").toLowerCase().split(" ");
        if (commands[0].isEmpty()) {
            ui.printMessage(Commons.ADD_INFO_MESSAGE + System.lineSeparator()
                    + Commons.DELETE_INFO_MESSAGE + System.lineSeparator()
                    + Commons.LIST_INFO_MESSAGE + System.lineSeparator()
                    + Commons.LOAD_INFO_MESSAGE + System.lineSeparator()
                    + Commons.RECORD_CONSULTATION_INFO_MESSAGE + System.lineSeparator()
                    + Commons.RETRIEVE_INFO_MESSAGE + System.lineSeparator()
                    + Commons.CURRENT_INFO_MESSAGE + System.lineSeparator()
                    + Commons.HELP_INFO_MESSAGE + System.lineSeparator()
                    + Commons.EXIT_INFO_MESSAGE
            );
        } else {
            for (String command : commands) {
                switch (command) {
                case "add":
                    ui.printMessage(Commons.ADD_INFO_MESSAGE);
                    break;
                case "delete":
                    ui.printMessage(Commons.DELETE_INFO_MESSAGE);
                    break;
                case "list":
                    ui.printMessage(Commons.LIST_INFO_MESSAGE);
                    break;
                case "load":
                    ui.printMessage(Commons.LOAD_INFO_MESSAGE);
                    break;
                case "record":
                    ui.printMessage(Commons.RECORD_CONSULTATION_INFO_MESSAGE);
                    break;
                case "retrieve":
                    ui.printMessage(Commons.RETRIEVE_INFO_MESSAGE);
                    break;
                case "current":
                    ui.printMessage(Commons.CURRENT_INFO_MESSAGE);
                    break;
                case "help":
                    ui.printMessage(Commons.HELP_INFO_MESSAGE);
                    break;
                case "exit":
                    ui.printMessage(Commons.EXIT_INFO_MESSAGE);
                    break;
                default:
                    ui.printMessage(String.format(Commons.INVALID_COMMAND_MESSAGE, command) + System.lineSeparator());
                }
            }
        }
    }
}
