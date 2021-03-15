package seedu.duke.command;

import seedu.duke.Data;
import seedu.duke.Ui;
import seedu.duke.model.Patient;

import java.util.ArrayList;
import java.util.HashMap;

public class AddCommand extends Command {
    /**
     * This is the constructor of the command. Arguments are passed to parent class.
     *
     * @param ui        Instance of Ui class, for UI input/output
     * @param data      Instance of Data class, for manipulating patient list and read/write miscellaneous config
     * @param arguments Arguments decomposed from the full command given by the user
     */
    public AddCommand(Ui ui, Data data, HashMap<String, String> arguments) {
        super(ui, data, arguments);
    }

    @Override
    public void execute() throws Exception {
        // TODO Replace by ui after ui is implemented
        String patientID = arguments.get("payload");
        int stringLength = patientID.length();

        // TODO more ways to check for invalid NRIC and other exceptions
        if (stringLength != 9) {
            throw new Exception("Please key in a valid NRIC number!");
        } else if (data.getPatients().containsKey(patientID)) {
            throw new Exception("Patient already exists!");
        }

        Patient patient = new Patient(patientID);
        data.setPatient(patient);

        System.out.print("Patient " + patientID + " has been added!\n");
    }
}
