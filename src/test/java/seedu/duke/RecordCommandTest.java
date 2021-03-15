package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.command.RecordCommand;
import seedu.duke.model.Patient;
import seedu.duke.model.Record;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RecordCommandTest {
    @Test
    public void executeRecordCommand_noPatientLoaded_exceptionThrown() {
        Data data = new Data();
        Patient patient = new Patient("S1234567A");
        data.setPatient(patient);
        HashMap<String, String> arguments = new HashMap<>();
        arguments.put("command", "record");
        arguments.put("payload", "coughing");
        RecordCommand recordCommand = new RecordCommand(null, data, arguments);
        Exception exception = assertThrows(Exception.class, () -> {
            recordCommand.execute();
        });
        assertEquals("No patient loaded!", exception.getMessage());
    }

    @Test
    public void executeRecordCommand_patientLoaded_recordAdded() {
        Data data = new Data();
        Patient patient = new Patient("S1234567A");
        data.setPatient(patient);
        data.loadCurrentPatient(patient.getID());
        HashMap<String, String> arguments = new HashMap<>();
        arguments.put("command", "record");
        arguments.put("payload", "coughing");
        RecordCommand recordCommand = new RecordCommand(null, data, arguments);
        try {
            recordCommand.execute();
        } catch (Exception exception) {
            System.out.println("An error occurred while running tests");
        }
        ArrayList<Record> records = patient.getRecords();
        assertEquals(1, records.size());
    }

    @Test
    public void executeRecordCommand_patientLoaded_dataUpdated() {
        Data data = new Data();
        Patient patient = new Patient("S1234567A");
        data.setPatient(patient);
        data.loadCurrentPatient(patient.getID());
        HashMap<String, String> arguments = new HashMap<>();
        arguments.put("command", "record");
        arguments.put("payload", "coughing");
        RecordCommand recordCommand = new RecordCommand(null, data, arguments);
        try {
            recordCommand.execute();
        } catch (Exception exception) {
            System.out.println("An error occurred while running tests");
        }
        Patient patientFromData = data.getPatient(patient.getID());
        ArrayList<Record> records = patientFromData.getRecords();
        assertEquals(1, records.size());
    }
}
