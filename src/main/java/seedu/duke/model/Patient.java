package seedu.duke.model;

import seedu.duke.exception.InvalidInputException;

import java.time.LocalDate;
import java.util.TreeMap;

/**
 * Each instance of this class represents a patient, and no two patients should
 * contain the same ID field. The instance contains records for all visits.
 */
public class Patient {
    /**
     * This is the unique identifier of the patient.
     * In SG's context, we use NRIC/FIN for this field
     */
    protected String id;
    protected TreeMap<LocalDate, Record> records;
    protected String symptom;
    protected String diagnosis;
    protected String prescription;

    /**
     * Initialize a patient instance with an empty record list.
     *
     * @param id Patient's unique identifier
     */
    public Patient(String id) {
        this(id, new TreeMap<LocalDate, Record>());
    }

    /**
     * Initialize a patient instance with a pre-defined record TreeMap.
     *
     * @param id      Patient's unique identifier
     * @param records Patient's visit record list
     */
    public Patient(String id, TreeMap<LocalDate, Record> records) {
        this.id = id;
        this.records = records;
        this.symptom = null;
        this.diagnosis = null;
        this.prescription = null;
    }

    /**
     * Get unique identifier of the patient.
     *
     * @return Patient's unique identifier
     */
    public String getID() {
        return id;
    }

    /* Functionals for manipulating records */

    /**
     * Get all records associated with this patient.
     *
     * @return All records in a TreeMap, mapping consultation dates to records
     */
    public TreeMap<LocalDate, Record> getRecords() {
        return records;
    }

    /**
     * Add a single record into the patient's record list.
     *
     * @param date         Appointment date to add the record to
     * @param symptom      Patient's symptoms to add to the record
     * @param diagnosis    Patient's diagnosis to add to the record
     * @param prescription Patient's prescription to add to the record
     */
    public void addRecord(LocalDate date, String symptom, String diagnosis, String prescription) {
        if (!records.containsKey(date)) {
            records.put(date, new Record());
        }
        Record record = records.get(date);
        record.addDetails(symptom, diagnosis, prescription);
        this.symptom = symptom;
        this.diagnosis = diagnosis;
        this.prescription = prescription;
    }

    /**
     * Checks if a record exists from the patient's record list.
     *
     * @param date Appointment date of record to check
     * @return Boolean for whether the record exists
     */
    public boolean recordExist(LocalDate date) {
        if (records.containsKey(date)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Deletes a record from the patient's record list.
     *
     * @param date Appointment date of record to delete
     * @throws InvalidInputException if there patient does not have any records on the specified date
     */
    public void deleteRecord(LocalDate date) throws InvalidInputException {
        if (!records.containsKey(date)) {
            throw new InvalidInputException(InvalidInputException.Type.NO_RECORD_FOUND);
        }
        records.remove(date);
    }

    /**
     * This returns a printable string with recently added information.
     *
     * @return a printable string for information about recently added symptoms, diagnosis and prescription
     */
    public String recentlyAdded() {
        String recentDetails = System.lineSeparator();
        if (symptom != null) {
            recentDetails += "Symptom: " + symptom + System.lineSeparator();
        }
        if (diagnosis != null) {
            recentDetails += "Diagnosis: " + diagnosis + System.lineSeparator();
        }
        if (prescription != null) {
            recentDetails += "Prescription: " + prescription + System.lineSeparator();
        }
        return recentDetails;
    }
}
