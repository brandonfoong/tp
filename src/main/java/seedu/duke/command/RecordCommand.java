package seedu.duke.command;

import seedu.duke.Constants;
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
//        Patient patient = data.currentPatient;
//        if (patient == null) {
//            throw new InvalidInputException(InvalidInputException.Type.NO_PATIENT_LOADED);
//        }
        String dateString = arguments.get(Constants.PAYLOAD_KEY);
        LocalDate date = null;
        try {
            date = parseDate(dateString);
        } catch (DateTimeParseException dateTimeParseException) {
            throw new InvalidInputException(InvalidInputException.Type.INVALID_DATE);
        }
        addRecord(date);
        data.saveFile();
//        printNewRecord(patient);
    }

    private LocalDate parseDate(String dateString) throws DateTimeParseException {
        if (!dateString.isEmpty()) {
            return LocalDate.parse(dateString, DateTimeFormatter.ofPattern(Constants.DATE_PATTERN));
        }
        return LocalDate.now();
    }

    private void addRecord(LocalDate date) throws InvalidInputException {
        String symptom = null;
        String diagnosis = null;
        String prescription = null;
        if (arguments.containsKey(Constants.SYMPTOM_KEY)) {
            symptom = arguments.get(Constants.SYMPTOM_KEY);
        }
        if (arguments.containsKey(Constants.DIAGNOSIS_KEY)) {
            diagnosis = arguments.get(Constants.DIAGNOSIS_KEY);
        }
        if (arguments.containsKey(Constants.PRESCRIPTION_KEY)) {
            prescription = arguments.get(Constants.PRESCRIPTION_KEY);
        }
        data.addRecord(date, symptom, diagnosis, prescription);
//        patient.addRecord(date, symptom, diagnosis, prescription);
    }

    private void printNewRecord(Patient patient) {
        ui.printMessage("Added new record to patient " + patient.getID() + ":");
        ui.printMessage(patient.recentlyAdded());
    }
}
