package jdbctests;

import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DynamicList {
    String dbURL = "jdbc:oracle:thin:@54.89.81.210:1521:XE";
    String dbUsername = "hr";
    String dbPassword = "hr";

    @Test
    public void test1() throws SQLException {


        Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select  FIRST_NAME, LAST_NAME, SALARY,JOB_ID\n" +
                "from EMPLOYEES\n" +
                "where rownum < 6");


// create a dynamic logic to keep all the information from the query
       ResultSetMetaData rsmd = resultSet.getMetaData();

       List<Map<String,Object>> queryData = new ArrayList<>();

       int columnCount = rsmd.getColumnCount();
       while(resultSet.next()) {
           Map<String,Object> row = new HashMap<>();
           for (int i = 1; i <= columnCount; i++) {
               row.put(rsmd.getColumnName(i),resultSet.getObject(i));
           }
           queryData.add(row);
       }
        for (Map<String, Object> each : queryData) {
            System.out.println(each);
        }

        resultSet.close();
        statement.close();
        connection.close();

    }
}