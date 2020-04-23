package space.ablackack.PatientOverview.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import space.ablackack.PatientOverview.pojo.Patient;

public class DatabaseUtils {

  private void newDriverInstance()
      throws ClassNotFoundException, IllegalAccessException, InstantiationException {
    Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
  }

  private Connection getConnection()
      throws IllegalAccessException, InstantiationException, ClassNotFoundException {
    this.newDriverInstance();

    Connection connection = null;
    DriverManager.getConnection("jdbc:mysql://localhost/overview?" +
        "user=user&password=user");

    return connection;
  }

  public List<Patient> getAllPatients() {
    this.getConnection().
  }

}
