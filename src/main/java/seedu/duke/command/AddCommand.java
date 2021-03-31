package seedu.duke.command;

import seedu.duke.Constants;
import seedu.duke.Data;
import seedu.duke.Ui;
import seedu.duke.exception.InvalidInputException;
import seedu.duke.exception.StorageException;
import seedu.duke.model.Patient;

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
    public void execute() throws InvalidInputException, StorageException {

        String id = arguments.get("payload");
        id = id.toUpperCase();

        if (!checkID(id)) {
            throw new InvalidInputException(InvalidInputException.Type.INVALID_NRIC);
        } else if (data.getPatients().containsKey(id)) {
            throw new InvalidInputException(InvalidInputException.Type.PATIENT_EXISTED);
        }

        assert checkID(id) : "validID should be true";
        Patient patient = new Patient(id);
        data.addPatient(patient);
        data.saveFile();

        ui.printMessage("Patient " + id + " has been added!");
    }

    /**
     * Checks whether the patient's ID is valid.
     * @param id Unique identifier of the patient to be retrieved
     * @return Flag on whether the patient's ID is valid
     */
    private boolean checkID(String id) {
        int stringLength = id.length();
        int checksum = 0;
        char firstLetter = id.charAt(Constants.INDEX_OF_FIRST_CHARACTER);
        char[] st = {'J', 'Z', 'I', 'H', 'G', 'F', 'E', 'D', 'C', 'B', 'A'};
        char[] fg = {'X', 'W', 'U', 'T', 'R', 'Q', 'P', 'N', 'M', 'L', 'K'};

        // Checks if ID has 9 characters
        if (stringLength != Constants.ID_NUMBER_OF_CHARACTERS) {
            return false;
        }
        // Checks if ID is valid
        for (int i = 0; i < stringLength; i++) {
            char c = id.charAt(i);
            if (i == Constants.INDEX_OF_FIRST_CHARACTER) {
                // Checks if first index of ID is S,T,F or G
                if (c != 'S' && c != 'T' && c != 'F' && c != 'G') {
                    return false;
                }
            } else if (i == Constants.INDEX_OF_LAST_CHARACTER) {
                // Checks if last index of ID is a letter
                if (!Character.isLetter(c)) {
                    return false;
                }
                if (firstLetter == 'T' || firstLetter == 'G') {
                    checksum += Constants.CHECKSUM_DIGIT;
                }
                checksum = checksum % Constants.CHECKSUM_MOD;
                if (firstLetter == 'S' || firstLetter == 'T') {
                    if (c != st[checksum]) {
                        return false;
                    }
                } else {
                    if (c != fg[checksum]) {
                        return false;
                    }
                }
            } else {
                // Checks if the rest of the indexes are digits
                if (!Character.isDigit(c)) {
                    return false;
                }
                // Calculates the checksum of digits
                switch (i) {
                case Constants.FIRST_DIGIT:
                case Constants.LAST_DIGIT:
                    checksum += Integer.parseInt(String.valueOf(c)) * 2;
                    break;
                case Constants.SECOND_DIGIT:
                    checksum += Integer.parseInt(String.valueOf(c)) * 7;
                    break;
                case Constants.THIRD_DIGIT:
                    checksum += Integer.parseInt(String.valueOf(c)) * 6;
                    break;
                case Constants.FOURTH_DIGIT:
                    checksum += Integer.parseInt(String.valueOf(c)) * 5;
                    break;
                case Constants.FIFTH_DIGIT:
                    checksum += Integer.parseInt(String.valueOf(c)) * 4;
                    break;
                case Constants.SIXTH_DIGIT:
                    checksum += Integer.parseInt(String.valueOf(c)) * 3;
                    break;
                default:
                }
            }
        }
        return true;
    }

}

