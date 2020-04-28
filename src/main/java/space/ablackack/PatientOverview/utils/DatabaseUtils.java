package space.ablackack.PatientOverview.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import space.ablackack.PatientOverview.enums.StatusEnm;
import space.ablackack.PatientOverview.enums.TriageCatEnm;
import space.ablackack.PatientOverview.pojo.Patient;

public class DatabaseUtils {

  private static void newDriverInstance()
      throws ClassNotFoundException, IllegalAccessException, InstantiationException {
    Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
  }

  private static Connection getConnection()
      throws IllegalAccessException, InstantiationException, ClassNotFoundException, SQLException {
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
      resultSet = statement.executeQuery("SELECT * FROM patient");

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

    } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {
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
    return null;
  }

  public static void savePatient(Patient patient) {
  }

  public static void changePatient(String patientNumber, Patient newPatient) {
  }
}
