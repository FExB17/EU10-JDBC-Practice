package jdbctests;

import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class listOfMapExample {
    String dbURL = "jdbc:oracle:thin:@54.89.81.210:1521:XE";
    String dbUsername = "hr";
    String dbPassword = "hr";


    @Test
    public void test1() {
        //creating list for storing all the rows

        List<Map<String, Object>> queryData = new ArrayList<>();

        Map<String, Object> row1 = new HashMap<>();
        row1.put("first_name", "Steven");
        row1.put("last_name", "King");
        row1.put("salary", 24000);
        row1.put("job_id", "AD_PRES");

        System.out.println(row1.toString());

        Map<String, Object> row2 = new HashMap<>();
        row2.put("first_name", "Neena");
        row2.put("last_name", "Kochaar");
        row2.put("salary", 17000);
        row2.put("job_id", "AD_VP");

        System.out.println(row2.toString());

        queryData.add(row1);
        queryData.add(row2);

        // get the steven lastname from the list

        System.out.println(queryData.get(0).get("last_name"));
        System.out.println(queryData.get(1).get("salary"));


    }

    @Test
    public void test2() throws SQLException {


        Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select  FIRST_NAME, LAST_NAME, SALARY,JOB_ID\n" +
                "from EMPLOYEES\n" +
                "where rownum < 6");

        //creating list for storing all the rows

        List<Map<String, Object>> queryData = new ArrayList<>();
        ResultSetMetaData rsmd = resultSet.getMetaData();

        Map<String, Object> row1 = new HashMap<>();
        row1.put(rsmd.getColumnName(1), resultSet.getString(1));
        row1.put(rsmd.getColumnName(2), resultSet.getString(2));
        row1.put(rsmd.getColumnName(3), resultSet.getString(3));
        row1.put(rsmd.getColumnName(4), resultSet.getString(4));


        resultSet.next();


        Map<String, Object> row2 = new HashMap<>();
        row2.put(rsmd.getColumnName(1), resultSet.getString(1));
        row2.put(rsmd.getColumnName(2), resultSet.getString(2));
        row2.put(rsmd.getColumnName(3), resultSet.getString(3));
        row2.put(rsmd.getColumnName(4), resultSet.getString(4));

        System.out.println(row1);


        System.out.println(row2);

        queryData.add(row1);
        queryData.add(row2);

        // get the steven lastname from the list

        System.out.println(queryData.get(0).get("last_name"));
        System.out.println(queryData.get(1).get("salary"));



        resultSet.close();
        statement.close();
        connection.close();






    }


}
