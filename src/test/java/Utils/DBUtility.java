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

            statement=connection.createStatement(); //create a statement to execute the query
            rset=statement.executeQuery(sqlQuery); //execute the query and storing the result

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
            rSetMetaData=rset.getMetaData(); //get the data in tabular format that we can
            //use these in column keys and value retrieval operation
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rSetMetaData;
    }

    //extract data from result set and store into list of map
    public static List<Map<String, String>> getListOfMapFromRset(String query){

        rSetMetaData=getRsetMetaData(query);

        List<Map<String, String>> listFromRset = new ArrayList<>();
        Map<String, String> mapData; //to store 1 map

        try{
            //iterates over the rows
            while (rset.next()) {
                mapData = new LinkedHashMap<>();
                //iterates over the columns, 0 - header
                for (int i = 1; i <= rSetMetaData.getColumnCount(); i++) {
                    String key = rSetMetaData.getColumnName(i);
                    String value = rset.getString(key); //return the value against the key
                    //value is located in rset not in tabulate format, in String format

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
