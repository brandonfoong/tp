package seedu.duke.command;

import seedu.duke.Data;
import seedu.duke.Ui;
import seedu.duke.model.Patient;
import seedu.duke.model.Record;

import java.util.ArrayList;
import java.util.HashMap;

public class RetrieveCommand extends Command {
    /**
     * This is the constructor of the command. Arguments are passed to parent class.
     *
     * @param ui        Instance of Ui class, for UI input/output
     * @param data      Instance of Data class, for manipulating patient list and read/write miscellaneous config
     * @param arguments Arguments decomposed from the full command given by the user
     */
    public RetrieveCommand(Ui ui, Data data, HashMap<String, String> arguments) {
        super(ui, data, arguments);
    }

    @Override
    public void execute() throws Exception {
        // TODO: Implement proper exception
        Patient patient = data.currentPatient;
        if (patient == null) {
            throw new Exception("No patient loaded!");
        }
        ArrayList<Record> records = patient.getRecords();
        ui.printMessage("Here are the records for patient " + patient.getID());
        for (Record record : records) {
            ui.printMessage(record.toString());
        }
    }
}
