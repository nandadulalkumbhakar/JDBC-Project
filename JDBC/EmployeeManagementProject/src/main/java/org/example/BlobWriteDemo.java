package org.example;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;

public class BlobWriteDemo {
    private  static final String URL="jdbc:mysql://localhost:3306/student";
    private static final String USER_NAME="root";
    private static final String PASSWORD="password";

    private static Connection connection;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;

    public static void main(String[] args) {
        try{
            connection= DriverManager.getConnection(URL,USER_NAME,PASSWORD);//connection b/w java application and database
            File f=new File("E:\\intellij\\JDBC\\EmployeeManagementProject\\src\\main\\java\\res\\.trashed-1704296416-IMG20231204163534 - Copy.jpg");//file load
            FileInputStream fis=new FileInputStream(f);// link with file system and DataBase
            preparedStatement =connection.prepareStatement("insert into blobdemo values(?,?)");//data inserted
            preparedStatement.setInt(1,3);//
            preparedStatement.setBinaryStream(2,fis,(int)f.length());
            LocalDateTime now =LocalDateTime.now();//time before execution
            System.out.println(now);
            preparedStatement.executeUpdate();
            LocalDateTime now1=LocalDateTime.now();//time after execution
            System.out.println(now1);
            System.out.println("Data Inserted...");
            connection.close();


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
