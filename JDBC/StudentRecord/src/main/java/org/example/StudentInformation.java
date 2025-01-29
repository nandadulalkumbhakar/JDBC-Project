package org.example;


import java.sql.*;
import java.util.Scanner;

public class StudentInformation {
    public static Scanner sc=new Scanner(System.in);
    public static void createTable(Connection con) throws SQLException
    {
        String create="create table if not exists studentInformation(id int auto_increment primary key,"
                +"name varchar(30),"
                +"age int ,"
                +"email varchar(50)"
                +")";

        PreparedStatement ps=con.prepareStatement(create);
        ps.execute();
        System.out.println("Table create successfully...");
    }
    public static void insertData(Connection con)throws SQLException
    {
        System.out.println("Enter student name ");
        String name=sc.next();
        System.out.println("Enter Student age: ");
        int age= sc.nextInt();
        System.out.println("Enter Student email : ");
        String email=sc.next();
        PreparedStatement ps=con.prepareStatement("insert into studentInformation(name,age,email) values(?,?,?)");
        ps.setString(1,name);
        ps.setInt(2,age);
        ps.setString(3,email);
        ps.executeUpdate();
        System.out.println("Data Insert Successfully");
    }

    public static void Update(Connection con)throws SQLException
    {
        Statement stmt=con.createStatement();
        System.out.println("Enter a id which you want to update");
        int id= sc.nextInt();
        System.out.println("Enter student name ");
        String name=sc.next();
        System.out.println("Enter Student age: ");
        int age= sc.nextInt();
        System.out.println("Enter Student email : ");
        String email=sc.next();
        int c = stmt.executeUpdate("update studentInformation set name='"+name+"',age='"+age+"',email='"+email+"' where id=" + id);
        if(c>0)
            System.out.println("Data Update Successfully");
        else
            System.out.println("Data Update failed");
    }
    public static void display(Connection con) throws SQLException
    {
        Statement stmt= con.createStatement();
        ResultSet res=stmt.executeQuery("select * from studentInformation");
        while (res.next())
        {
            System.out.println("\nStudent id= "+res.getInt("id"));
            System.out.println("Student name= "+res.getString("name"));
            System.out.println("Student age= "+res.getInt("age"));
            System.out.println("Student Email= "+res.getString("email"));
            System.out.println("-------------------------------------------------------------");
        }
    }
    public static void Remove(Connection con)throws SQLException
    {
        System.out.println("Enter a Student id which you want to remove: ");
        int id=sc.nextInt();
        Statement stmt=con.createStatement();
        int c= stmt.executeUpdate("delete from studentInformation where id="+id);
        if(c>0)
        {
            System.out.println("Remove successfully");
        }
        else
        {
            System.out.println("Remove failed");
        }

    }
    public static void Delete(Connection con)throws SQLException
    {
        Statement stmt=con.createStatement();
        int c=stmt.executeUpdate("drop table studentInformation");
        if(c>0)
            System.out.println("Table delete successfully");
        else
            System.out.println("Table delete failed");
    }
    public static void menu()
    {
        System.out.println("1. Create table");
        System.out.println("2. Insert data");
        System.out.println("3. Update data");
        System.out.println("4. Display");
        System.out.println("5. Remove student data");
        System.out.println("6. Delete Table");
        System.out.println("Enter your choice : ");
    }
    public static void main(String[] args)
    {
        String url="jdbc:mysql://localhost:3306/student";
        String username="root";
        String password="password";
        char c;
        do {
            menu();
            try
            {
                int ch=sc.nextInt();
                Connection con = DriverManager.getConnection(url, username, password);
                switch (ch)
                {
                    case 1:
                        createTable(con);
                        break;
                    case 2:
                        insertData(con);
                        break;
                    case 3:
                        Update(con);
                        break;
                    case 4:
                        display(con);
                        break;
                    case 5:
                        Remove(con);
                        break;
                    case 6:
                        Delete(con);
                        break;
                    default:
                        System.out.println("Invalid your choice... ");
                }
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
            System.out.println("Do you want to continue?(y/n)");
            c=sc.next().charAt(0);

        }while (c=='y' || c=='Y');
    }
}
