package seedu.duke.command;

import seedu.duke.Data;
import seedu.duke.Ui;
import seedu.duke.exception.InvalidInputException;

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
    public void execute() throws InvalidInputException {
        assert ui != null : "Ui must not be null";
        String recordString = data.getRecords();
        ui.printMessage("Here are " + data.getCurrentPatientId() + "'s records:");
        ui.printMessage(recordString);
        //        TreeMap<LocalDate, Record> records = patient.getRecords();
        //        for (Map.Entry<LocalDate, Record> entry : records.entrySet()) {
        //            LocalDate date = entry.getKey();
        //            Record record = entry.getValue();
        //            printRecord(date, record);
        //        }
    }

    //    private void printRecord(LocalDate date, Record record) {
    //        ui.printMessage(date.format(DateTimeFormatter.ofPattern(Constants.DATE_PATTERN)) + ":");
    //        ui.printMessage(record.toString());
    //    }
}
