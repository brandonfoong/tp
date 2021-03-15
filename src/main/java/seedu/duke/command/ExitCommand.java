package seedu.duke.command;

import seedu.duke.Constants;
import seedu.duke.Data;
import seedu.duke.Ui;

import java.util.HashMap;

public class ExitCommand extends Command {

    /**
     * This is the constructor of the command. Arguments are passed to parent class.
     *
     * @param ui        Instance of Ui class, for UI input/output
     * @param data      Instance of Data class, for manipulating patient list and read/write miscellaneous config
     * @param arguments Arguments decomposed from the full command given by the user
     */

    public ExitCommand(Ui ui, Data data, HashMap<String, String> arguments) {
        super(ui, data, arguments);
        isExit = true;
    }

    @Override
    public void execute() throws Exception {
        ui.printMessage(Constants.EXIT_MESSAGE);
    }
}
