package Utils;

import Utils.ConfigReader;
import io.cucumber.java.it.Ma;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DBUtility {

    private static ResultSet rset;
    private static ResultSetMetaData rSetMetaData;

//this method create connection to the database, execute query
//and return object of the Resultset
    public static ResultSet getResultSet(String sqlQuery){

        Connection connection=null;
        Statement statement=null;
        try {
            connection = DriverManager.getConnection(
                    ConfigReader.getPropertyValue("dbUrl"), //or we can pass it as a parameter
                    ConfigReader.getPropertyValue("dbUsername"),
                    ConfigReader.getPropertyValue("dbPassword"));

            statement=connection.createStatement();
            rset=statement.executeQuery(sqlQuery);

        }catch (SQLException e){
            e.printStackTrace();
        }

        return rset;
    } //we need close the connection

    //method return an object of result set metadata
    public static ResultSetMetaData getRsetMetaData(String query){
        rset=getResultSet(query);
        rSetMetaData=null;

        try {
            rSetMetaData=rset.getMetaData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rSetMetaData;
    }

    //extract data from result set and store into list of map
    public static List<Map<String, String>> getListOfMapFromRset(String query){

        rSetMetaData=getRsetMetaData(query);

        List<Map<String, String>> listFromRset = new ArrayList<>();
        Map<String, String> mapData;

        try{
            //iterates over the rows
            while (rset.next()) {
                mapData = new LinkedHashMap<>();
                //iterates over the columns
                for (int i = 1; i <= rSetMetaData.getColumnCount(); i++) {
                    String key = rSetMetaData.getColumnName(i);
                    String value = rset.getString(key);

                    //store data from every column into a map
                    mapData.put(key, value);
                }
                //we store map with Data into a list
                listFromRset.add(mapData);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return listFromRset;
    }

}
