package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.command.RecordCommand;
import seedu.duke.command.RetrieveCommand;
import seedu.duke.model.Patient;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RetrieveCommandTest {
    @Test
    public void executeRetrieveCommand_noPatientLoaded_exceptionThrown() {
        Data data = new Data();
        Ui ui = new Ui();
        Patient patient = new Patient("S1234567A");
        data.setPatient(patient);
        HashMap<String, String> arguments = new HashMap<>();
        arguments.put("command", "record");
        arguments.put("payload", "coughing");
        RetrieveCommand retrieveCommand = new RetrieveCommand(ui, data, arguments);
        Exception exception = assertThrows(Exception.class, () -> {
            retrieveCommand.execute();
        });
        assertEquals("No patient loaded!", exception.getMessage());
    }

    @Test
    public void executeRetrieveCommand_patientLoadedAndRecordsAdded_printsRecords() {
        Data data = new Data();
        Ui ui = new Ui();
        Patient patient = new Patient("S1234567A");
        data.setPatient(patient);
        data.loadCurrentPatient(patient.getID());
        HashMap<String, String> arguments = new HashMap<>();
        arguments.put("command", "record");
        arguments.put("payload", "coughing");
        RecordCommand recordCommand = new RecordCommand(ui, data, arguments);
        try {
            recordCommand.execute();
        } catch (Exception exception) {
            System.out.println("An error occurred while running tests");
        }

        // Bind System.out to a ByteArrayOutputStream
        final PrintStream originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        RetrieveCommand retrieveCommand = new RetrieveCommand(ui, data, arguments);
        try {
            retrieveCommand.execute();
        } catch (Exception exception) {
            System.out.println("An error occurred while running tests");
        }
        assertEquals("Here are the records for patient " + patient.getID() + System.lineSeparator() +
                "coughing" + System.lineSeparator(), bos.toString());

        // Bind System.out back to standard output
        System.setOut(originalOut);
    }
}
