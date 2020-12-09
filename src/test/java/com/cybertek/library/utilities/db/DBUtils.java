package com.cybertek.library.utilities.db;

import com.cybertek.library.utilities.common.Environment;

import java.sql.*;
import java.util.*;

public class DBUtils {

    private DBUtils(){}

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    public static void createConnection() {
        String dB_URL = Environment.getProperty("database_url");
        String dB_USERNAME = Environment.getProperty("database_username");
        String dB_PASSWORD = Environment.getProperty("database_password");
        try{
            connection = DriverManager.getConnection(dB_URL, dB_USERNAME, dB_PASSWORD);
        } catch (SQLException q) {
            q.printStackTrace();
            System.out.println("Invalid Credentials !!!");
        }
    }

    public static void createConnection(String dB_URL, String dB_USERNAME, String dB_PASSWORD) {
        try{
            connection = DriverManager.getConnection(dB_URL, dB_USERNAME, dB_PASSWORD);
        } catch (SQLException q) {
            q.printStackTrace();
            System.out.println("Invalid Credentials !!!");
        }
    }

    public static void destroy(){
        try{
            if(resultSet != null){
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException q) {
            q.printStackTrace();
        }
    }

    private static void runQuery(String query) {
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Count how many row we have
     *
     * @return the row count of the resultset we got
     */
    public static int getRowCount() {

        int rowCount = 0;

        try {
            resultSet.last();
            rowCount = resultSet.getRow();

            // move the cursor back to beforeFirst location to avoid accident
            resultSet.beforeFirst();

        } catch (SQLException e) {

            System.out.println("ERROR WHILE GETTING ROW COUNT " + e.getMessage());
        }

        return rowCount;

    }

    /**
     * Get the column count
     *
     * @return count of column the result set have
     */
    public static int getColumnCount() {

        int columnCount = 0;

        try {
            ResultSetMetaData rsmd = resultSet.getMetaData();
            columnCount = rsmd.getColumnCount();

        } catch (SQLException e) {
            System.out.println("ERROR WHILE COUNTING THE COLUMNS " + e.getMessage());
        }

        return columnCount;
    }

    /**
     * a method that return all column names as List<String>
     */
    public static List<String> getColumnNames() {

        List<String> columnList = new ArrayList<>();

        try {
            ResultSetMetaData rsmd = resultSet.getMetaData();

            for (int colNum = 1; colNum <= getColumnCount(); colNum++) {

                String columnName = rsmd.getColumnLabel(colNum);
                columnList.add(columnName);
            }

        } catch (SQLException e) {
            System.out.println("ERROR WHILE GETTING ALL COLUMN NAMES " + e.getMessage());
        }
        return columnList;

    }

    /**
     * Create a method that return all row data as a List<String>
     *
     * @param rowNum Row number you want to get the data
     * @return the row data as List object
     */
    public static List<String> getRowDataAsList(int rowNum) {

        List<String> rowDataList = new ArrayList<>();

        // first we need to move the pointer to the location the rowNum specified
        try {
            resultSet.absolute(rowNum);

            for (int colNum = 1; colNum <= getColumnCount(); colNum++) {

                String cellValue = resultSet.getString(colNum);
                rowDataList.add(cellValue);

            }
            resultSet.beforeFirst();

        } catch (SQLException e) {
            System.out.println("ERROR WHILE GETTING ROW DATA AS LIST " + e.getMessage());
        }
        return rowDataList;

    }


    /**
     * Create a method to return the cell value at certain row certain column
     *
     * @param rowNum row number
     * @param colNum column number
     * @return Cell value as String
    =     */
    public static String getColumnDataAtRow(int rowNum, int colNum) {

        String result = "";

        try {
            resultSet.absolute(rowNum);
            result = resultSet.getString(colNum);
            resultSet.beforeFirst(); // moving back to before first location

        } catch (SQLException e) {
            System.out.println("ERROR WHILE GETTING CELL VALUE AT ROWNUM COLNUM " + e.getMessage());
        }

        return result;
    }

    /**
     * Create a method to return the cell value at certain row certain column
     *
     * @param rowNum row number
     * @param colName column name
     * @return Cell value as String at specified row numeber and column number
     */
    public static String getColumnDataAtRow(int rowNum, String colName) {

        String result = "";

        try {
            resultSet.absolute(rowNum);
            result = resultSet.getString(colName);
            resultSet.beforeFirst(); // moving back to before first location

        } catch (SQLException e) {
            System.out.println("ERROR WHILE GETTING CELL VALUE AT ROWNUM column name " + e.getMessage());
        }

        return result;
    }

    /**
     * return value of all cells in that column
     *
     * @param colNum the column number you want to get the list out of
     * @return value of all cells in that column as a List<String>
     */
    public static List<String> getColumnDataAsList(int colNum) {

        List<String> cellValuesList = new ArrayList<>();

        try {

            while (resultSet.next()) {

                String cellValue = resultSet.getString(colNum);
                cellValuesList.add( cellValue ) ;

            }
            resultSet.beforeFirst();

        } catch (SQLException e) {
            System.out.println("ERROR WHILE GETTING ONE COLUMN DATA AS LIST " + e.getMessage() );
        }
        return cellValuesList ;

    }
    /**
     * return value of all cells in that column using column name
     *
     * @param colName the column name you want to get the list out of
     * @return value of all cells in that column as a List<String>
     */
    public static List<String> getColumnDataAsList(String colName) {

        List<String> cellValuesList = new ArrayList<>();

        try {

            while (resultSet.next()) {

                String cellValue = resultSet.getString(colName);
                cellValuesList.add( cellValue ) ;

            }
            resultSet.beforeFirst(); //Move it back to before first location

        } catch (SQLException e) {
            System.out.println("ERROR WHILE GETTING ONE COLUMN DATA AS LIST " + e.getMessage() );
        }
        return cellValuesList ;

    }

    /**
     * A method that display all the result set data on console
     */
    public static void displayAllData(){

        try {
            resultSet.beforeFirst();

            while (resultSet.next()) {

                for (int colNum = 1; colNum <= getColumnCount(); colNum++) {
//                    System.out.print(rs.getString(colNum) + "\t");
                    //  for making it pretty
                    System.out.printf("%-35s", resultSet.getString(colNum));
                }
                System.out.println();
            }
            resultSet.beforeFirst();

        } catch (SQLException e) {
            System.out.println("ERROR WHILE PRINTING WHOLE TABLE " + e.getMessage());
        }
    }

    /**
     * A method that return the row data along with column name as Map object
     * @param rowNum row numebr you want to get the data
     * @return Map object -- column name as key and cell value as value
     */
    public static Map<String,String> getRowMap(int rowNum){

        Map<String,String>  rowMap = new LinkedHashMap<>() ;

        try{

            resultSet.absolute(rowNum) ;
            ResultSetMetaData rsmd = resultSet.getMetaData() ;

            for (int colNum = 1; colNum <= rsmd.getColumnCount() ; colNum++) {

                String columnName   =  rsmd.getColumnLabel( colNum ) ;
                String cellValue    =  resultSet.getString( colNum ) ;
                rowMap.put(columnName, cellValue) ;

            }
            resultSet.beforeFirst();

        } catch (SQLException e) {
            System.out.println("ERROR WHILE getting RowMap " + e.getMessage());
        }
        return rowMap ;

    }

    public static List<Map<String,String> > getAllDataAsListOfMaps(){

        List<Map<String,String> > rowMapList = new ArrayList<>();

        for (int rowNum = 1; rowNum <= getRowCount() ; rowNum++) {

            rowMapList.add(   getRowMap(rowNum)    ) ;

        }
        return  rowMapList ;
    }

    //APEarth starts here

    /**
     * @param query
     * @return returns query result in a list of lists where outer list represents
     * collection of rows and inner lists represent a single row
     */
    public static List<List<Object>> getQueryResultList(String query) {
        runQuery(query);
        List<List<Object>> rowList = new ArrayList<>();
        ResultSetMetaData rsmd;
        try {
            rsmd = resultSet.getMetaData();
            while (resultSet.next()) {
                List<Object> row = new ArrayList<>();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    row.add(resultSet.getObject(i));
                }
                rowList.add(row);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return rowList;
    }

    /**
     * @param query
     * @return returns a single cell value. If the results in multiple rows and/or
     * columns of data, only first column of the first row will be returned.
     * The rest of the data will be ignored
     */
    public static Object getCellValue(String query) {
        return getQueryResultList(query).get(0).get(0);
    }

    /**
     * @param query
     * @return returns a list of Strings which represent a row of data. If the query
     * results in multiple rows and/or columns of data, only first row will
     * be returned. The rest of the data will be ignored
     */
    public static List<Object> getRowList(String query) {
        return getQueryResultList(query).get(0);
    }

    /**
     * @param query
     * @return returns a map which represent a row of data where key is the column
     * name. If the query results in multiple rows and/or columns of data,
     * only first row will be returned. The rest of the data will be ignored
     */
    public static Map<String, Object> getRowMap(String query) {
        return getQueryResultMap(query).get(0);
    }

    /**
     * @param query
     * @param columnHead
     * @return list of values of a single columnHead from the result set
     */
    public static List<Object> getColumnData(String query, String columnHead) {
        runQuery(query);
        List<Object> rowList = new ArrayList<>();
        ResultSetMetaData rsmd;
        try {
            rsmd = resultSet.getMetaData();
            while (resultSet.next()) {
                rowList.add(resultSet.getObject(columnHead));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return rowList;
    }

    /**
     * @param query
     * @return returns query result in a list of maps where the list represents
     * collection of rows and a map represents represent a single row with
     * key being the column name
     */
    public static List<Map<String, Object>> getQueryResultMap(String query) {
        runQuery(query);
        List<Map<String, Object>> rowList = new ArrayList<>();
        ResultSetMetaData rsmd;
        try {
            rsmd = resultSet.getMetaData();
            while (resultSet.next()) {
                Map<String, Object> colNameValueMap = new HashMap<>();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    colNameValueMap.put(rsmd.getColumnName(i), resultSet.getObject(i));
                }
                rowList.add(colNameValueMap);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return rowList;
    }

    /**
     * @param query
     * @return List of columns returned in result set
     */
    public static List<String> getColumnNames(String query) {
        runQuery(query);
        List<String> columns = new ArrayList<>();
        ResultSetMetaData rsmd;
        try {
            rsmd = resultSet.getMetaData();
            int columnCount = rsmd.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                columns.add(rsmd.getColumnName(i));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return columns;
    }

    /**
     * shows the row count of the most recent query result
     * @return
     * @throws Exception
     */

}
