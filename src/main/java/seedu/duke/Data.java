package seedu.duke;

import java.time.LocalDate;
import java.util.SortedMap;
import java.util.TreeMap;

import seedu.duke.exception.InvalidInputException;
import seedu.duke.exception.StorageException;
import seedu.duke.model.Patient;

/**
 * This class (instance) contains all data of the running application. This
 * includes patient list and miscellaneous config.
 */
public class Data {
    private Storage storage;
    private SortedMap<String, Patient> patients;

    /**
     * This is the patient that is currently being selected. Command sub-classes can read/write
     * this attribute directly.
     * Before modification, if not loaded, it needs to call loadCurrentPatient(id) to load the patient.
     * After modification, saveCurrentPatient() needs to be called to write back any changes on this attribute.
     */
    private Patient currentPatient;

    /**
     * This initializes an empty data instance with no storage instance.
     * This can be used for testing purposes.
     */
    public Data() {
        this(null);
    }

    /**
     * This initializes an empty data instance with a storage instance.
     *
     * @param storage an instance of the storage class
     */
    public Data(Storage storage) {
        this(storage, new TreeMap<>());
    }

    /**
     * This initializes a data instance with an existing patient list.
     * Storage instance must be specified if want to use an existing list of patients. However it can be set
     * to null (i.e. new Data(null, existingPatients)) for testing purposes.
     *
     * @param storage  an instance of the storage class
     * @param patients the patient list
     */
    public Data(Storage storage, SortedMap<String, Patient> patients) {
        this.storage = storage;
        this.patients = patients;
        currentPatient = null;
    }

    private void checkPatientExists(String id) throws InvalidInputException {
        if (!patients.containsKey(id)) {
            throw new InvalidInputException(InvalidInputException.Type.INVALID_PATIENT);
        }
    }

    private void checkPatientLoaded() throws InvalidInputException {
        if (currentPatient == null) {
            throw new InvalidInputException(InvalidInputException.Type.NO_PATIENT_LOADED);
        }
    }

    /**
     * Gets the ID number of the currently loaded patient
     *
     * @return ID number of the currently loaded patient
     * @throws InvalidInputException if there is no loaded patient
     */
    public String getCurrentPatientId() throws InvalidInputException {
        checkPatientLoaded();
        return currentPatient.getID();
    }

    /**
     * Adds a visit record to the currently loaded patient
     *
     * @param date         the date of visit
     * @param symptom      symptom(s) experienced by the patient
     * @param diagnosis    diagnosis/es made by the doctor
     * @param prescription prescription(s) made by the doctor
     * @throws InvalidInputException if there is no loaded patient
     */
    public void addRecord(LocalDate date, String symptom, String diagnosis, String prescription)
            throws InvalidInputException {
        checkPatientLoaded();
        currentPatient.addRecord(date, symptom, diagnosis, prescription);
    }

    /**
     * Prints out all records of the currently loaded patient
     *
     * @return a String containing the patient's records
     * @throws InvalidInputException if there is no loaded patient
     */
    public String getRecords() throws InvalidInputException {
        return "";
    }

    /**
     * Prints out all records of the currently loaded patient for a specific date
     *
     * @param date the date to retrieve records for
     * @return a String containing the patient's records for that date
     * @throws InvalidInputException if there is no loaded patient,
     *                               or if the patient does not have any records on that date
     */
    public String getRecords(LocalDate date) throws InvalidInputException {
        return "";
    }

    /**
     * Removes a patient, based on the ID number specified
     *
     * @param id the ID number of the patient to remove
     * @throws InvalidInputException if the patient does not exist, or if the specified patient is loaded
     */
    public void deletePatient(String id) throws InvalidInputException {
        checkPatientExists(id);
        if (currentPatient.getID() == id) {
            throw new InvalidInputException(InvalidInputException.Type.REMOVE_LOADED_PATIENT);
        }
        patients.remove(id);
    }

    /**
     * Removes a visit record from the currently loaded patient, based on the visit date
     *
     * @param date the date of the record to be removed
     * @throws InvalidInputException if there is no loaded patient,
     *                               or if the patient does not have any records on that date
     */
    public void deleteRecord(LocalDate date) throws InvalidInputException {
        checkPatientLoaded();
        currentPatient.deleteRecord(date);
    }

    /**
     * Loads a existing patient into the currentPatient variable, based on the ID number specified
     *
     * @param id the ID number of the patient to be loaded
     * @throws InvalidInputException if no patient corresponds to the specified ID number
     */
    public void loadPatient(String id) throws InvalidInputException {
        checkPatientExists(id);
        currentPatient = patients.get(id);
    }


    /**
     * This retrieves the full hashmap of patients.
     *
     * @return the patient hashmap
     */
    public SortedMap<String, Patient> getPatients() {
        return patients;
    }

    /**
     * This retrieves a single patient bases on its unique identifier.
     *
     * @param id unique identifier of the patient to be retrieved
     * @return the patient instance associated with this ID if found, otherwise null is returned
     */
    public Patient getPatient(String id) {
        return patients.get(id);
    }

    /**
     * Add or update a new patient to the hashmap of this database.
     *
     * @param patient the patient to be added/updated
     */
    public void addPatient(Patient patient) {
        patients.put(patient.getID(), patient);
    }

    /**
     * This loads a patient to the currentPatient attribute.
     * Take note that currentPatient can still be null if there is no patients with this id in the hashmap.
     *
     * @param id unique identifier of the patient to be loaded
     */
    public void loadCurrentPatient(String id) {
        currentPatient = getPatient(id);
    }

    /**
     * This saves current patient list into the file using the storage instance.
     */
    public void saveFile() throws StorageException {
        // If storage is null, we just silently ignore it (for testing)
        if (storage != null) {
            storage.save(patients);
        }
    }
}
