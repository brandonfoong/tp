package seedu.duke.command;

import seedu.duke.Data;
import seedu.duke.Ui;
import seedu.duke.model.Patient;

import java.util.ArrayList;
import java.util.HashMap;

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
    public void execute() throws Exception {
        // Todo Replace by ui after ui is implemented
        HashMap<String, Patient> patients = data.getPatients();
        int patientCount = 0;
        String list = "";

        for (String patientID : patients.keySet()) {
            list += ++patientCount + ". " + patientID + "\n";
        }

        // Todo implement more exceptions
        if (patientCount == 0) {
            throw new Exception("List is currently empty!");
        } else {
            System.out.print(list);
        }
    }
}
