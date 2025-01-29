package org.example;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BoobFetchDemo {
    private static final String URL="jdbc:mysql://localhost:3306/student";
    private static final String userName="root";
    private static final String password="password";

    private static Connection connection;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;

    public static void main(String[] args)
    {
        try{
            connection= DriverManager.getConnection(URL,userName,password);
            preparedStatement=connection.prepareStatement("select image from blobdemo where id=?");
            preparedStatement.setInt(1,2);
            resultSet =preparedStatement.executeQuery();
            if(resultSet.next())
            {
                InputStream is=resultSet.getBinaryStream("image");
                FileOutputStream fis=new FileOutputStream("E:\\intellij\\JDBC\\EmployeeManagementProject\\src\\main\\java\\res\\rahul.jpg");
                byte buffer[]=new byte[1024];
                while(is.read(buffer)>0)
                {
                    fis.write(buffer);
                }
                fis.close();
                is.close();
                System.out.println("Data Fetch ...");

            }
            else
            {
                System.out.println("No image found");
            }

        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }
}
