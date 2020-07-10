package space.ablackack.PatientOverview.utils.database;

import space.ablackack.PatientOverview.enums.StatusEnm;
import space.ablackack.PatientOverview.enums.TriageCatEnm;
import space.ablackack.PatientOverview.pojo.Patient;
import space.ablackack.PatientOverview.utils.DatabaseUtils;
import space.ablackack.PatientOverview.utils.OverviewUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientDatabaseUtils {
    private static final String GET_ALL_PATIENTS_QUERY = "SELECT * FROM patient ORDER BY idpatient DESC";
    private static final String GET_LATEST_PATIENT = "SELECT * FROM patient ORDER BY idpatient DESC LIMIT 0, 1";
    private static final String PREPARED_GET_SINGLE_PATIENT = "SELECT * FROM patient WHERE patient_number = ?";
    private static final String PREPARED_SAVE_PATIENT =
            "INSERT INTO patient (patient_number, name, status, triage_category) VALUES (?, ?, ?, ?)";
    private static final String PREPARED_GET_COUNT_TODAYS_PATIENTS =
            "SELECT count(*) FROM patient WHERE patient_number LIKE ?";
    private static final String PREPARED_GET_ALL_PATIENTS_WITH_CATEGORY =
            "SELECT * FROM patient WHERE triage_category LIKE ? ORDER BY idpatient DESC";

    private static Patient mapDbColumnsToPatient(String patientNumber, String name,
                                                 StatusEnm statusEnm, TriageCatEnm triageCatEnm) {
        Patient patient = null;

        if (patientNumber != null && name != null && statusEnm != null && triageCatEnm != null) {
            patient = new Patient(patientNumber, name, statusEnm, triageCatEnm);
        } else if (patientNumber != null && name != null && statusEnm != null) {
            patient = new Patient(patientNumber, name, statusEnm);
        } else if (patientNumber != null && name != null && triageCatEnm != null) {
            patient = new Patient(patientNumber, name, triageCatEnm);
        } else if (patientNumber != null && name != null) {
            patient = new Patient(patientNumber, name);
        } else if (patientNumber != null) {
            patient = new Patient(patientNumber);
        }

        return patient;
    }

    public static List<Patient> getAllPatients() {
        List<Patient> patientList = new ArrayList<>();
        ResultSet dbResult = DatabaseUtils.executeQuery(GET_ALL_PATIENTS_QUERY);

        if (dbResult != null) {
            try {
                while (dbResult.next()) {
                    Patient patient = mapDbColumnsToPatient(dbResult.getString("patient_number"),
                            dbResult.getString("name"),
                            StatusEnm.fromString(dbResult.getString("status")),
                            TriageCatEnm.fromString(dbResult.getString("triage_category")));
                    if (patient != null) {
                        patientList.add(patient);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return patientList;

    }

    public static Patient getPatient(String patientNumber) {
        Patient patient = null;
        String[] parameters = {patientNumber};
        ResultSet dbResult =
                DatabaseUtils.executePreparedStatementRead(PREPARED_GET_SINGLE_PATIENT, parameters);

        try {
            if (dbResult != null) {
                while (dbResult.next()) {
                    patient = mapDbColumnsToPatient(dbResult.getString("patient_number"),
                            dbResult.getString("name"),
                            StatusEnm.fromString(dbResult.getString("status")),
                            TriageCatEnm.fromString(dbResult.getString("triage_category")));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return patient;
    }

    public static void savePatient(Patient patient) {
        String[] parameters = {
                patient.getPatientNumber(),
                patient.getName(),
                patient.getStatus().getDisplayValue(),
                patient.getCategory().getDisplayValue()
        };

        DatabaseUtils.executePreparedStatementUpdate(PREPARED_SAVE_PATIENT, parameters);
    }

    public static Patient getLatestPatient() {
        Patient patient = null;
        ResultSet dbResult = DatabaseUtils.executeQuery(GET_LATEST_PATIENT);

        try {
            if (dbResult != null) {
                while (dbResult.next()) {
                    patient = mapDbColumnsToPatient(dbResult.getString("patient_number"),
                            dbResult.getString("name"),
                            StatusEnm.fromString(dbResult.getString("status")),
                            TriageCatEnm.fromString(dbResult.getString("triage_category")));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return patient;
    }

    public static void changePatient(String patientNumber, Patient newPatient) {
    }

    public static Integer getNumberOfTodaysPatients() {
        String[] parameters = {OverviewUtils.getTodaysDay().toString() + "%"};
        ResultSet resultSet = DatabaseUtils.executePreparedStatementRead(PREPARED_GET_COUNT_TODAYS_PATIENTS, parameters);
        int result = 0;

        try {
            if (resultSet != null) {
                while (resultSet.next()) {
                    result = resultSet.getInt(1);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }

    public static List<Patient> getAllPatientsWithCategory(String triageCat) {
        List<Patient> patientList = new ArrayList<>();
        TriageCatEnm filterEnm = TriageCatEnm.fromValueString(triageCat);
        String[] parameters = {filterEnm.getDisplayValue()};

        ResultSet dbResult =
                DatabaseUtils.executePreparedStatementRead(PREPARED_GET_ALL_PATIENTS_WITH_CATEGORY, parameters);

        try {
            if (dbResult != null) {
                while (dbResult.next()) {
                    Patient patient = mapDbColumnsToPatient(dbResult.getString("patient_number"),
                            dbResult.getString("name"),
                            StatusEnm.fromString(dbResult.getString("status")),
                            TriageCatEnm.fromString(dbResult.getString("triage_category")));

                    if (patient != null) {
                        patientList.add(patient);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return patientList;
    }
}