package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.command.RecordCommand;
import seedu.duke.model.Patient;
import seedu.duke.model.Record;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RecordCommandTest {
    @Test
    public void executeRecordCommand_noPatientLoaded_exceptionThrown() {
        Data data = new Data();
        Patient patient = new Patient("S1234567D");
        data.addPatient(patient);
        HashMap<String, String> arguments = new HashMap<>();
        arguments.put("command", "record");
        arguments.put("payload", "2021-03-31");
        arguments.put("s", "coughing");
        Ui ui = new Ui();
        RecordCommand recordCommand = new RecordCommand(ui, data, arguments);
        Exception exception = assertThrows(Exception.class, () -> {
            recordCommand.execute();
        });
        assertEquals("No patient loaded!", exception.getMessage());
    }

    @Test
    public void executeRecordCommand_patientLoaded_recordAdded() {
        Data data = new Data();
        Patient patient = new Patient("S1234567D");
        data.addPatient(patient);
        data.loadCurrentPatient(patient.getID());
        HashMap<String, String> arguments = new HashMap<>();
        arguments.put("command", "record");
        arguments.put("payload", "31/03/2021");
        arguments.put("s", "coughing");
        Ui ui = new Ui();
        RecordCommand recordCommand = new RecordCommand(ui, data, arguments);
        try {
            recordCommand.execute();
        } catch (Exception exception) {
            System.out.println("An error occurred while running tests");
        }
        TreeMap<LocalDate, Record> records = patient.getRecords();
        assertEquals(1, records.size());
    }
}
