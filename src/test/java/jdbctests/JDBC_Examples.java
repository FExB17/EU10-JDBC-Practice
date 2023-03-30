package jdbctests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.*;

public class JDBC_Examples {

    String dbURL = "jdbc:oracle:thin:@54.89.81.210:1521:XE";
    String dbUsername = "hr";
    String dbPassword = "hr";

    @DisplayName("JDBC_Examples")
    @Test
    public void test1() throws SQLException {


        Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from departments");

        // resultSet.next();
        // resultSet.getString(2);

        // display departments table in following format: 10 - Administration - 200 - 1700
        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1) + " - " + resultSet.getString(2) + " - " + resultSet.getInt(3) + " - " + resultSet.getInt(4));
        }
        System.out.println("================================================================");


        statement = connection.createStatement();
        resultSet = statement.executeQuery("select * from regions");

        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1) + " - " + resultSet.getString(2));
        }

        resultSet.close();
        statement.close();
        connection.close();


    }

    @DisplayName("ResultSet Methods")
    @Test
    public void test2() throws SQLException {

        Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        ResultSet resultSet = statement.executeQuery("select * from employees");

        // find out how many rows we have for the query
        int rowNum = 0;
        while (resultSet.next()) {
            rowNum++;
        }
        System.out.println("number of rows: " + rowNum);

        resultSet.first();
        resultSet.last();
        System.out.println("number of Rows: " + resultSet.getRow());

        //print all second column information
        resultSet.first();
        while (resultSet.next()) {
            System.out.println(resultSet.getString(2));
        }

        resultSet.close();
        statement.close();
        connection.close();

    }

    @DisplayName("MetaData")
    @Test
    public void test3() throws SQLException {

        Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        ResultSet resultSet = statement.executeQuery("select * from employees");

       //get the database related data inside the DB metadata
        DatabaseMetaData dbMetaData = connection.getMetaData();
        System.out.println("dbMetaData.getUserName() = " + dbMetaData.getUserName());
        System.out.println("dbMetaData.getDatabaseProductName() = " + dbMetaData.getDatabaseProductName());
        System.out.println("dbMetaData.getDriverName() = " + dbMetaData.getDriverName());
        System.out.println("dbMetaData.getSQLKeywords() = " + dbMetaData.getSQLKeywords());


        //get the ResultSet Metadata
        ResultSetMetaData rsMetadata = resultSet.getMetaData();
        int colcount = rsMetadata.getColumnCount();
        System.out.println("colcount = " + colcount);


        // print all the column names dynamically
        for (int i = 1; i <= rsMetadata.getColumnCount(); i++) {
            System.out.println(rsMetadata.getColumnName(i));

        }


        resultSet.close();
        statement.close();
        connection.close();

    }
}
