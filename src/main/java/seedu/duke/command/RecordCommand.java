package seedu.duke.command;

import seedu.duke.Commons;
import seedu.duke.Data;
import seedu.duke.Ui;
import seedu.duke.exception.InvalidInputException;
import seedu.duke.exception.StorageException;
import seedu.duke.model.Patient;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;

public class RecordCommand extends Command {

    /**
     * This is the constructor of the command. Arguments are passed to parent class.
     *
     * @param ui        Instance of Ui class, for UI input/output
     * @param data      Instance of Data class, for manipulating patient list and read/write miscellaneous config
     * @param arguments Arguments decomposed from the full command given by the user
     */
    public RecordCommand(Ui ui, Data data, HashMap<String, String> arguments) {
        super(ui, data, arguments);
    }

    @Override
    public void execute() throws InvalidInputException, StorageException {
        assert ui != null : "Ui must not be null";
        assert arguments.containsKey("payload") : "Arguments must contain a value for the `payload` key";
        String dateString = arguments.get(Commons.PAYLOAD_KEY);
        LocalDate date = null;
        try {
            date = Commons.parseDate(dateString);
        } catch (DateTimeParseException dateTimeParseException) {
            throw new InvalidInputException(InvalidInputException.Type.INVALID_DATE);
        }
        String recordString = addRecord(date);
        ui.printMessage("Added new record to patient " + data.getCurrentPatientId() + ":");
        ui.printMessage(recordString);
        data.saveFile();
    }

    private String addRecord(LocalDate date) throws InvalidInputException {
        String symptom = null;
        String diagnosis = null;
        String prescription = null;
        if (arguments.containsKey(Commons.SYMPTOM_KEY)) {
            symptom = arguments.get(Commons.SYMPTOM_KEY);
        }
        if (arguments.containsKey(Commons.DIAGNOSIS_KEY)) {
            diagnosis = arguments.get(Commons.DIAGNOSIS_KEY);
        }
        if (arguments.containsKey(Commons.PRESCRIPTION_KEY)) {
            prescription = arguments.get(Commons.PRESCRIPTION_KEY);
        }
        return data.addRecord(date, symptom, diagnosis, prescription);
    }

    private void printNewRecord(Patient patient) {
        ui.printMessage("Added new record to patient " + patient.getID() + ":");
        ui.printMessage(patient.recentlyAdded());
    }
}
