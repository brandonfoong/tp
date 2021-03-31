package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.command.HelpCommand;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelpCommandTest {
    @Test
    public void executeHelpCommand_multipleCommands_allMessagesPrinted() {
        Ui ui = new Ui();
        Data data = new Data();
        HashMap<String, String> arguments = new HashMap<>();
        arguments.put("command", "help");
        arguments.put("payload", "list add exit");
        HelpCommand helpCommand = new HelpCommand(ui, data, arguments);

        final PrintStream originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        try {
            helpCommand.execute();
        } catch (Exception e) {
            System.out.println("An error occurred while running tests");
        }

        assertEquals(Commons.LIST_INFO_MESSAGE + System.lineSeparator()
                + Commons.ADD_INFO_MESSAGE + System.lineSeparator()
                + Commons.EXIT_INFO_MESSAGE  + System.lineSeparator(), bos.toString());
        System.setOut(originalOut);
    }

    @Test
    public void executeHelpCommand_multipleCommandsWithInvalidCommands_messagesPrinted() {
        Ui ui = new Ui();
        Data data = new Data();
        HashMap<String, String> arguments = new HashMap<>();
        arguments.put("command", "help");
        arguments.put("payload", "list add bye");
        HelpCommand helpCommand = new HelpCommand(ui, data, arguments);

        final PrintStream originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        try {
            helpCommand.execute();
        } catch (Exception e) {
            System.out.println("An error occurred while running tests");
        }

        assertEquals(Commons.LIST_INFO_MESSAGE + System.lineSeparator()
                + Commons.ADD_INFO_MESSAGE + System.lineSeparator()
                + String.format(Commons.INVALID_COMMAND_MESSAGE, "bye")
                + System.lineSeparator() + System.lineSeparator(), bos.toString());
        System.setOut(originalOut);
    }
}
