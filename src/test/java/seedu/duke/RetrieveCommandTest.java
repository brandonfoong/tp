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
        Patient patient = new Patient("S1234567D");
        data.addPatient(patient);
        HashMap<String, String> arguments = new HashMap<>();
        arguments.put("command", "record");
        arguments.put("payload", "31/03/2021");
        arguments.put("s", "coughing");
        Ui ui = new Ui();
        RetrieveCommand retrieveCommand = new RetrieveCommand(ui, data, arguments);
        Exception exception = assertThrows(Exception.class, () -> {
            retrieveCommand.execute();
        });
        assertEquals("No patient loaded!", exception.getMessage());
    }

    @Test
    public void executeRetrieveCommand_patientLoadedAndRecordsAdded_printsRecords() {
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
        String expected = "Here are " + patient.getID() + "'s records:" + System.lineSeparator()
                + "31/03/2021:" + System.lineSeparator()
                + "Symptoms:" + System.lineSeparator()
                + "\tcoughing" + System.lineSeparator()
                + "Diagnoses:" + System.lineSeparator()
                + "Prescriptions:" + System.lineSeparator() + System.lineSeparator();
        assertEquals(expected, bos.toString());

        // Bind System.out back to standard output
        System.setOut(originalOut);
    }
}
