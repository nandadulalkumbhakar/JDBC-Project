/*package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EmployeeManagement {
}*/
package org.example;

//import java.net.URL;
import java.sql.*;

public class EmployeeManagement
{
    private  static final String URL="jdbc:mysql://localhost:3306/student";
    private static final String USER_NAME="root";
    private static final String PASSWORD="password";

    private static Connection connection;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;


    public static void main(String[] args)
    {
        try{
            connection = DriverManager.getConnection(URL,USER_NAME,PASSWORD);//JDBC_URL,USER_NAME,PASSWORD);

            //create a table
            createEmployeeTable();
            //insert data into table
            insertDataIntoDb("Rahul",100,"rahul@gmail.com");
            connection.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    private static void insertDataIntoDb(String name,int age,String email)throws SQLException
    {
        String insertSql="insert into employee(name,age,email) values(?,?,?)";
        preparedStatement=connection.prepareStatement(insertSql);
        preparedStatement.setString(1,name);
        preparedStatement.setInt(2,age);
        preparedStatement.setString(3,email);
        preparedStatement.executeUpdate();
        System.out.println("Data inserted");
    }

    private static void createEmployeeTable() throws SQLException {
        String createTable="create table if not exists Employee ( id int auto_increment primary key,"+
                "name varchar(30),"
                +"age int,"
                +"email varchar(50)"
                +")";
        preparedStatement =connection.prepareStatement(createTable);
        preparedStatement.execute();
        System.out.println("Table create successfully");
    }
}
