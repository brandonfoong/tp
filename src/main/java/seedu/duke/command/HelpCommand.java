package seedu.duke.command;

import seedu.duke.Constants;
import seedu.duke.Data;
import seedu.duke.Ui;

import java.util.HashMap;

public class HelpCommand extends Command {
    /**
     * This is the constructor of the command. Arguments are passed to parent class.
     * @param ui Instance of Ui class, for UI input/output
     * @param data Instance of Data class, for manipulating patient list and read/write miscallaneous config
     * @param arguments Arguments decomposed from the full command given by the user
     */
    public HelpCommand(Ui ui, Data data, HashMap<String, String> arguments) {
        super(ui, data, arguments);
    }

    @Override
    public void execute() {
        ui.printMessage(Constants.ADD_INFO_MESSAGE
                + Constants.LIST_INFO_MESSAGE
                + Constants.LOAD_INFO_MESSAGE
                + Constants.RECORD_CONSULTATION_INFO_MESSAGE
                + Constants.RETRIEVE_INFO_MESSAGE
                + Constants.EXIT_INFO_MESSAGE
        );
    }
}
