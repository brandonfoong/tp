package seedu.duke.command;

import seedu.duke.Commons;
import seedu.duke.Data;
import seedu.duke.Ui;
import seedu.duke.model.Patient;

import java.util.HashMap;
import java.util.SortedMap;

public class ListCommand extends Command {
    /**
     * This is the constructor of the command. Arguments are passed to parent class.
     *
     * @param ui        Instance of Ui class, for UI input/output
     * @param data      Instance of Data class, for manipulating patient list and read/write miscellaneous config
     * @param arguments Arguments decomposed from the full command given by the user
     */
    public ListCommand(Ui ui, Data data, HashMap<String, String> arguments) {
        super(ui, data, arguments);
    }

    @Override
    public void execute() {

        SortedMap<String, Patient> patients = data.getPatients();
        int patientCount = 0;
        String list = "List of patients (in alphanumeric order):";

        for (String patientID : patients.keySet()) {
            list += "\n" + ++patientCount + ". " + patientID;
        }

        if (patientCount == 0) {
            // There is no patient in the list. We just inform the user about it.
            ui.printMessage(Commons.EMPTY_LIST_MESSAGE);
        } else {
            assert list != "" : "List should not be empty";
            ui.printMessage(list);
        }
    }
}
