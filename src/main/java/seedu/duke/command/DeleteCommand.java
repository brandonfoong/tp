package seedu.duke.command;

import seedu.duke.Commons;
import seedu.duke.Data;
import seedu.duke.Ui;
import seedu.duke.exception.InvalidInputException;
import seedu.duke.exception.StorageException;
import seedu.duke.model.Patient;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;

public class DeleteCommand extends Command {

    /**
     * This is the constructor of the command. Arguments are passed to parent class.
     *
     * @param ui        Instance of Ui class, for UI input/output
     * @param data      Instance of Data class, for manipulating patient list and read/write miscellaneous config
     * @param arguments Arguments decomposed from the full command given by the user
     */
    public DeleteCommand(Ui ui, Data data, HashMap<String, String> arguments) {
        super(ui, data, arguments);
    }

    @Override
    public void execute() throws InvalidInputException, StorageException {

        if (arguments.containsKey(Commons.PATIENT_KEY)) {
            String id = arguments.get(Commons.PATIENT_KEY);
            id = id.toUpperCase();
            data.deletePatient(id);
            data.saveFile();
        } else if (arguments.containsKey(Commons.RECORD_KEY)) {
            String dateString = arguments.get(Commons.RECORD_KEY);
            LocalDate date = Commons.parseDate(dateString);
            data.deleteRecord(date);
            //            Patient patient = data.currentPatient;
            //            if (patient == null) {
            //                throw new InvalidInputException(InvalidInputException.Type.NO_PATIENT_LOADED);
            //            }
            //            deleteRecord(patient, date);
            data.saveFile();
        } else {
            throw new InvalidInputException(InvalidInputException.Type.UNKNOWN_DELETE_ARGUMENT);
        }
    }

    /**
     * Deletes a patient from the list.
     *
     * @param id Unique identifier of the patient to be retrieved
     */
    private void deletePatient(String id) throws InvalidInputException {
        if (data.getPatient(id) == null) {
            throw new InvalidInputException(InvalidInputException.Type.PATIENT_NOT_FOUND);
        }
        //        data.deletePatient(id);
        ui.printMessage("Patient " + id + " has been deleted!");
    }

    /**
     * Deletes a record a patient's consultation details.
     *
     * @param patient    Patient of record that is being deleted
     * @param dateString Date of record that is being deleted
     * @throws InvalidInputException when an invalid date is given
     */
    private void deleteRecord(Patient patient, String dateString) throws InvalidInputException {
        LocalDate date = null;
        try {
            date = Commons.parseDate(dateString);
        } catch (DateTimeParseException dateTimeParseException) {
            throw new InvalidInputException(InvalidInputException.Type.INVALID_DATE);
        }
        if (patient.recordExist(date)) {
            patient.deleteRecord(date);
            ui.printMessage("Record for " + date + " has been deleted!");
        } else {
            ui.printMessage("Record for " + date + " does not exist!");
        }

    }


}
