package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.command.DeleteCommand;
import seedu.duke.model.Patient;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeleteCommandTest {
    @Test
    public void executeDeleteCommand_patientDeleted() {
        Data data = new Data();
        Patient patient = new Patient("S1234567D");
        data.addPatient(patient);
        data.loadCurrentPatient(patient.getID());
        HashMap<String, String> arguments = new HashMap<>();
        arguments.put("command", "delete");
        arguments.put("p", "S1234567D");
        Ui ui = new Ui();
        DeleteCommand deleteCommand = new DeleteCommand(ui, data, arguments);

        final PrintStream originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        try {
            deleteCommand.execute();
        } catch (Exception e) {
            System.out.println("An error occurred while running tests");
        }

        assertEquals("Patient S1234567D has been deleted!" + System.lineSeparator(), bos.toString());
        System.setOut(originalOut);
    }

    @Test
    public void executeDeleteCommand_recordDeleted() {
        Data data = new Data();
        Patient patient = new Patient("S1234567D");
        data.addPatient(patient);
        data.loadCurrentPatient(patient.getID());
        String date = "29/03/2021";
        LocalDate parseDate = LocalDate.parse(date, DateTimeFormatter.ofPattern(Commons.DATE_PATTERN));
        patient.addRecord(parseDate, "coughing", "","");
        HashMap<String, String> arguments = new HashMap<>();
        arguments.put("command", "delete");
        arguments.put("r", "29/03/2021");
        Ui ui = new Ui();
        DeleteCommand deleteCommand = new DeleteCommand(ui, data, arguments);

        final PrintStream originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        try {
            deleteCommand.execute();
        } catch (Exception e) {
            System.out.println("An error occurred while running tests");
        }

        assertEquals("Record for 2021-03-29 has been deleted!" + System.lineSeparator(), bos.toString());
        System.setOut(originalOut);
    }

    @Test
    public void executeDeleteCommand_invalidInput() {
        Data data = new Data();
        Ui ui = new Ui();
        HashMap<String, String> arguments = new HashMap<>();
        arguments.put("command", "delete");
        DeleteCommand deleteCommand = new DeleteCommand(ui, data, arguments);

        Exception exception = assertThrows(Exception.class, () -> {
            deleteCommand.execute();
        });
        assertEquals("Kindly use /p or /r to indicate patient or record, refer to help for more clarification!",
                exception.getMessage());
    }

    @Test
    public void executeDeleteCommand_patientDoesNotExist() {
        Data data = new Data();
        Ui ui = new Ui();
        HashMap<String, String> arguments = new HashMap<>();
        arguments.put("command", "delete");
        arguments.put("p", "S1234567D");
        DeleteCommand deleteCommand = new DeleteCommand(ui, data, arguments);

        Exception exception = assertThrows(Exception.class, () -> {
            deleteCommand.execute();
        });
        assertEquals("Patient does not exist!", exception.getMessage());
    }

    @Test
    public void executeDeleteCommand_recordDoesNotExist() {
        Data data = new Data();
        Patient patient = new Patient("S1234567D");
        data.addPatient(patient);
        data.loadCurrentPatient(patient.getID());
        HashMap<String, String> arguments = new HashMap<>();
        arguments.put("command", "delete");
        arguments.put("r", "29/03/2021");
        Ui ui = new Ui();
        DeleteCommand deleteCommand = new DeleteCommand(ui, data, arguments);

        final PrintStream originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        try {
            deleteCommand.execute();
        } catch (Exception e) {
            System.out.println("An error occurred while running tests");
        }

        assertEquals("Record for 2021-03-29 does not exist!" + System.lineSeparator(), bos.toString());
        System.setOut(originalOut);
    }
}
