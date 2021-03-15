package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.command.AddCommand;

import java.util.HashMap;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddCommandTest {
    @Test
    public void testAddCommand() {
        Data data = new Data();
        HashMap<String, String> arguments = new HashMap<>();
        arguments.put("command", "add");
        arguments.put("payload", "S9841974H");
        AddCommand addCommand = new AddCommand(null, data, arguments);

        final PrintStream originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        try {
            addCommand.execute();
        } catch (Exception e) {
            System.out.println("Error");
        }

        assertEquals("Patient S9841974H has been added!\n", bos.toString());
        System.setOut(originalOut);
    }
}
