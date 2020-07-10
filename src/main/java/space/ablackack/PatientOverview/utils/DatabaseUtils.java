package space.ablackack.PatientOverview.utils;

import java.sql.*;

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

    public static ResultSet executeQuery(String query) {
        try {
            Statement statement = getConnection().createStatement();
            return statement.executeQuery(query);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static ResultSet executePreparedStatementRead(String query, String[] parameters) {
        try {
            PreparedStatement statement = getConnection().prepareStatement(query);
            int parameterIndex = 1;

            for (String parameter : parameters) {
                statement.setString(parameterIndex, parameter);
                parameterIndex++;
            }

            return statement.executeQuery();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    public static void executePreparedStatementUpdate(String query, String[] parameters) {
        try {
            PreparedStatement statement = getConnection().prepareStatement(query);
            int parameterIndex = 1;

            for (String parameter : parameters) {
                statement.setString(parameterIndex, parameter);
                parameterIndex++;
            }

            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
}
