package org.example;

import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Operation {

    public static void menu()
    {
        System.out.println("1. Data Fetch/Read/Display ");
        System.out.println("2. Data Insert");
        System.out.println("3. Data Update");
        System.out.println("4. Data Deletion");
        System.out.println("Enter Your choice: ");
    }

    public static void FetchOperation(Statement stmt) throws SQLException
    {
        ResultSet res=stmt.executeQuery("select * from std");
        while(res.next())
        {
            System.out.println("std id: "+res.getInt("st_id"));
            System.out.println("student name: "+res.getString("name"));
            System.out.println("student email: "+res.getString("email"));
            System.out.println("student phoneNo: "+res.getString("phoneNo"));
            System.out.println("----------------------------------------------------");
        }
    }
    public static void insert(Statement stmt) throws SQLException
    {
        Scanner sc=new Scanner(System.in);
        ResultSet MaxId=stmt.executeQuery("select MAX(st_id) as max_st_id from std");
        int max_id=0;
        while(MaxId.next())
        {
            max_id=MaxId.getInt("max_st_id");
        }
        max_id++;
        System.out.println("Enter student name: ");
        String name=sc.next();
        System.out.println("Enter student email id: ");
        String email=sc.next();
        System.out.println("Enter student ph number , only 10 digits allow:  ");
        String ph=sc.next();
        int count=stmt.executeUpdate("insert into std values("+max_id+",'"+name+"','"+email+"','"+ph+"')");
        if(count>0)
            System.out.println("insert successFully");
        else
            System.out.println("insert failed");
    }
    public static void update(Statement stmt) throws SQLException
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter id number: ");
        int id=sc.nextInt();
        System.out.println("Enter student name: ");
        String name=sc.next();
        System.out.println("Enter student email id: ");
        String email=sc.next();
        System.out.println("Enter student ph number , only 10 digits allow:  ");
        String ph=sc.next();
        int  count=stmt.executeUpdate("update std set name='"+name+"',email='"+email+"',phoneNo='"+ph+"' where st_id= "+id);
        //int  count=stmt.executeUpdate("update std set name='"+name+"' where st_id="+id);
        if(count>0)
            System.out.println("Update successFully");
        else
            System.out.println("update failed");
    }

    public static void delete(Statement stmt) throws SQLException
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter id number: ");
        int id=sc.nextInt();
        int del=stmt.executeUpdate("delete from std where st_id="+id);
        if(del>0)
            System.out.println("Delete successfully");
        else
            System.out.println("deletion failed");
    }
}
