package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.model.Patient;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.SortedMap;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    public static final String TEST_FILE = "testFile.txt";

    @Test
    public void storeData() {
        Storage storage = new Storage(TEST_FILE);
        Data data = new Data(storage);
        Patient patient = new Patient("S1234567D");
        LocalDate date = LocalDate.now();
        patient.addRecord(date, "head pain, dizziness", "heat stroke", "cooling packs, medicine");
        patient.addRecord(date.plus(1, ChronoUnit.DAYS), "fainting", "severe heat stroke", "referral to hospital");
        data.addPatient(patient);
        patient = new Patient("S7654321B");
        patient.addRecord(date, "abdominal pain", "mild UTI", "antibiotics, referral to hospital");
        data.addPatient(patient);

        try {
            data.saveFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void loadData() {
        Storage storage = new Storage(TEST_FILE);

        final PrintStream originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        // No exception should be thrown if everything works fine
        assertDoesNotThrow(() -> {
            SortedMap<String, Patient> data = storage.load();
            for (String patientID : data.keySet()) {
                System.out.println(patientID);
            }
        });

        assertEquals("S1234567D" + System.lineSeparator()
                + "S7654321B" + System.lineSeparator(), bos.toString());
        System.setOut(originalOut);
    }
    //TODO: better tests
}
