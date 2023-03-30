package jdbctests;

import utilities.DBUtils;

import java.sql.*;

public class TestConnection {
    public static void main(String[] args) throws SQLException {

        String dbURL = "jdbc:oracle:thin:@54.89.81.210:1521:XE";
        String dbUsername = "hr";
        String dbPassword = "hr";

        Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from regions");
/*

        // move pointer to first row
        // because resultSet points to index 0
        resultSet.next();

        // getting information with column name
        System.out.println(resultSet.getString("region_name"));

        // getting information with index (starts from 1)
        System.out.println(resultSet.getString(1));

        System.out.println(resultSet.getString("region_name"));
*/

        while (resultSet.next()) {
            System.out.println(resultSet.getString(1) + " - " + resultSet.getString(2));
        }


        // close connection
        resultSet.close();
        statement.close();
        connection.close();


    }

}
