package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.command.ListCommand;
import seedu.duke.model.Patient;

import java.util.HashMap;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListCommandTest {
    @Test
    public void executeListCommand_listEmpty_messagePrinted() {
        Data data = new Data();
        Ui ui = new Ui();
        HashMap<String, String> arguments = new HashMap<>();
        arguments.put("command", "list");
        
        ListCommand listCommand = new ListCommand(ui, data, arguments);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));
        listCommand.execute();
        final String standardOutput = myOut.toString();

        assertEquals(Constants.EMPTY_LIST_MESSAGE + System.lineSeparator(), standardOutput);
    }

    @Test
    public void executeListCommand() {
        Data data = new Data();
        Ui ui = new Ui();
        HashMap<String, String> arguments = new HashMap<>();
        arguments.put("command", "list");

        Patient patient = new Patient("S1234567D");
        data.addPatient(patient);
        ListCommand listCommand = new ListCommand(ui, data, arguments);

        final PrintStream originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        try {
            listCommand.execute();
        } catch (Exception exception) {
            System.out.println("An error occurred while running tests");
        }

        assertEquals("List of patients (in alphanumeric order):\n"
                + "1. S1234567D" + System.lineSeparator(), bos.toString());
        System.setOut(originalOut);
    }
}
