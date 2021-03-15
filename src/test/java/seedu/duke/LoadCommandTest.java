package seedu.duke;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import seedu.duke.command.LoadCommand;
import seedu.duke.model.Patient;

public class LoadCommandTest {
    @Test
    public void executeLoadCommand() {
        Data data = new Data();
        HashMap<String, String> arguments = new HashMap<>();
        arguments.put("command", "load");
        arguments.put("payload", "S7654321B");

        Patient patient = new Patient("S1234567A");
        data.setPatient(patient);
        patient = new Patient("S7654321B");
        data.setPatient(patient);
        Ui ui = new Ui();
        LoadCommand loadCommand = new LoadCommand(ui, data, arguments);
        try {
            loadCommand.execute();
        } catch (Exception exception) {
            System.out.println("Error occurred while loading patient data.");
        }
        ui.closeScanner();
    }

}
