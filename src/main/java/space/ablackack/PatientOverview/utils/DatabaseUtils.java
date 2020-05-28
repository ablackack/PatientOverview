package space.ablackack.PatientOverview.utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import space.ablackack.PatientOverview.enums.StatusEnm;
import space.ablackack.PatientOverview.enums.TriageCatEnm;
import space.ablackack.PatientOverview.pojo.Patient;

public class DatabaseUtils {

    private static void newDriverInstance()
            throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
    }

    private static Connection getConnection()
            throws ClassNotFoundException, SQLException {
        newDriverInstance();

        Connection connection;
        connection =
                DriverManager.getConnection("jdbc:mysql://localhost/overview?" + "user=user&password=user");

        return connection;
    }

    public static List<Patient> getAllPatients() {
        Statement statement = null;
        ResultSet resultSet = null;
        List<Patient> patientList = new ArrayList<>();

        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery("SELECT * FROM patient ORDER BY idpatient DESC");

            while (resultSet.next()) {
                Patient patient = null;

                String patientNumber = resultSet.getString("patient_number");
                String name = resultSet.getString("name");
                String status = resultSet.getString("status");
                String category = resultSet.getString("triage_category");

                StatusEnm statusEnm = StatusEnm.fromString(status);
                TriageCatEnm triageCatEnm = TriageCatEnm.fromString(category);

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

                if (patient != null) {
                    patientList.add(patient);
                }
            }

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ignored) {
                }
            }

            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ignored) {
                }
            }
        }

        return patientList;
    }

    public static Patient getPatient(String patientNumber) {
        PreparedStatement statement;
        ResultSet resultSet = null;
        Patient patient = new Patient();

        try {
            statement = getConnection().prepareStatement("SELECT * FROM patient WHERE patient_number = ?");
            statement.setString(1, patientNumber);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String resultpatientNumber = resultSet.getString("patient_number");
                String name = resultSet.getString("name");
                String status = resultSet.getString("status");
                String category = resultSet.getString("triage_category");

                StatusEnm statusEnm = StatusEnm.fromString(status);
                TriageCatEnm triageCatEnm = TriageCatEnm.fromString(category);

                if (resultpatientNumber!= null) {
                    patient.setPatientNumber(resultpatientNumber);
                }
                if (name != null) {
                    patient.setName(name);
                }
                if (statusEnm != null) {
                    patient.setStatus(statusEnm);
                }
                if (triageCatEnm != null) {
                    patient.setCategory(triageCatEnm);
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ignored) {
                }
            }
        }
        return patient;
    }

    public static void savePatient(Patient patient) {
        PreparedStatement statement;
        String patientNumber = patient.getPatientNumber();
        String name = patient.getName();
        String status = patient.getStatus().getDisplayValue();
        String category = patient.getCategory().getDisplayValue();

        try {
            statement = getConnection().prepareStatement("INSERT INTO patient (patient_number, name, status, triage_category) VALUES (?, ?, ?, ?)");
            statement.setString(1, patientNumber);
            statement.setString(2, name);
            statement.setString(3, status);
            statement.setString(4, category);

            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void changePatient(String patientNumber, Patient newPatient) {
    }

    public static Patient getLatestPatient() {
        Statement statement;
        ResultSet resultSet = null;
        Patient result = null;

        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery("SELECT * FROM patient ORDER BY idpatient DESC LIMIT 0, 1");

            while (resultSet.next()) {
                Patient patient = null;

                String patientNumber = resultSet.getString("patient_number");
                String name = resultSet.getString("name");
                String status = resultSet.getString("status");
                String category = resultSet.getString("triage_category");

                StatusEnm statusEnm = StatusEnm.fromString(status);
                TriageCatEnm triageCatEnm = TriageCatEnm.fromString(category);

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

                if (patient != null) {
                    result = patient;
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ignored) {
                }
            }
        }
        return result;
    }

    public static Integer getNumberOfTodaysPatients() {
        PreparedStatement statement;
        ResultSet resultSet = null;
        int result = 0;

        try {
            statement = getConnection().prepareStatement("SELECT count(*) FROM patient WHERE patient_number LIKE ?");
            statement.setString(1, OverviewUtils.getTodaysDay().toString() + "%");
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                result = resultSet.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ignored) {
                }
            }
        }
        return result;
    }

    public static List<Patient> getAllPatientsWithCategory(String triageCat) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Patient> patientList = new ArrayList<>();
        TriageCatEnm triageCatFilterEnm = TriageCatEnm.fromValueString(triageCat);

        try {
            statement = getConnection().prepareStatement("SELECT * FROM patient WHERE triage_category LIKE ? ORDER BY idpatient DESC");
            statement.setString(1, triageCatFilterEnm.getDisplayValue());
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Patient patient = null;

                String patientNumber = resultSet.getString("patient_number");
                String name = resultSet.getString("name");
                String resultStatus = resultSet.getString("status");
                String category = resultSet.getString("triage_category");

                StatusEnm resultStatusEnm = StatusEnm.fromString(resultStatus);
                TriageCatEnm triageCatEnm = TriageCatEnm.fromString(category);

                if (patientNumber != null && name != null && resultStatusEnm != null && triageCatEnm != null) {
                    patient = new Patient(patientNumber, name, resultStatusEnm, triageCatEnm);
                } else if (patientNumber != null && name != null && resultStatusEnm != null) {
                    patient = new Patient(patientNumber, name, resultStatusEnm);
                } else if (patientNumber != null && name != null && triageCatEnm != null) {
                    patient = new Patient(patientNumber, name, triageCatEnm);
                } else if (patientNumber != null && name != null) {
                    patient = new Patient(patientNumber, name);
                } else if (patientNumber != null) {
                    patient = new Patient(patientNumber);
                }

                if (patient != null) {
                    patientList.add(patient);
                }
            }

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ignored) {
                }
            }

            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ignored) {
                }
            }
        }

        return patientList;
    }
}
