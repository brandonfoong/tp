package seedu.duke.command;

import seedu.duke.Data;
import seedu.duke.Ui;

import java.util.HashMap;

public class LoadCommand extends Command {
    /**
     * This is the constructor of the command. Arguments are passed to parent class.
     *
     * @param ui        Instance of Ui class, for UI input/output
     * @param data      Instance of Data class, for manipulating patient list and read/write miscellaneous config
     * @param arguments Arguments decomposed from the full command given by the user
     */
    public LoadCommand(Ui ui, Data data, HashMap<String, String> arguments) {
        super(ui, data, arguments);
    }

    @Override
    public void execute() {
        // TODO: Replace printed string with actual details of the patient.
        Ui ui = new Ui();
        String id = arguments.get("payload");
        data.loadCurrentPatient(id);
        if (data.currentPatient != null) {
            ui.printMessage("Patient " + data.currentPatient.getID() + "\'s data has been found and loaded.");
        } else {
            ui.printMessage("Patient\'s data is not found.");
        }
        ui.closeScanner();
    }
}
