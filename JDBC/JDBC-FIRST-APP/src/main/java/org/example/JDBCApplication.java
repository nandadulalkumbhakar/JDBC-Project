package org.example;

//import java.sql.*;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
public class JDBCApplication
{
    public static void main(String[] args) {
        String url="jdbc:mysql://localhost:3306/student";
        String username="root";
        String password="password";


        try{
            //load and register
            //Class.forName("Com.mysql.cj.jdbc.Driver");


            Connection con= DriverManager.getConnection(url,username,password);
            Statement stmt=con.createStatement();
            ResultSet res=stmt.executeQuery("select * from std");

            while(res.next())
            {
                System.out.println("std id : "+res.getInt("st_id"));
                System.out.println("your name :"+res.getString("name"));
                System.out.println("your email: "+res.getString("email"));
                System.out.println("std phone no: "+res.getString("phoneNo"));

                System.out.println("---------------------------------------------------");

            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
