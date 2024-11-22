package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// import the package;  [java.sql.*]
// Load & register the driver; for different databse languages, thers are different  // for mysql the driver is == [com.mysql.jdbc.Driver]
// Establish the connection;
// Create the statement;
// Execute the query;
// Process result;
// Close the connection;
class dbConnect {

    String url = "jdbc:mysql://localhost:3306/student";
    String uname = "root";
    String pass = "daddy";

    String query = "select * from record";
    String query2 = " insert into record(name,age,city,marks)values(? , ? , ? , ?)";
    String query3 = "delete from record where id=7";
    String query4 = "update record set id=7 where id=8";

    Connection con = null;

    public void getConnect() {
        try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, uname, pass);
        } catch(ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
    }

    public void sendData() {

        try{Statement st = con.createStatement();

        int count = st.executeUpdate(query4);
        System.out.println(count + " rows affected");

        // PreparedStatement st1 = con.prepareStatement(); // use preparedStatemment for inserting data into the database
        // st.setString(1, username);
        // st.setInt(2, age);
        // st.setString(3, city);
        // st.setInt(4, marks);
        st.close();
        con.close();} catch(SQLException sex){
            System.out.println(sex);
        }
    }

    public void getData() {
       try{ try (Statement st = con.createStatement()) {
           ResultSet rs = st.executeQuery(query);
           while (rs.next()) {
               String data = rs.getInt(1) + " : " + rs.getString("name") + " : " + rs.getString("city") + " :-      Age: " + rs.getString("age") + " Scored Marks: " + rs.getString("marks");
               System.out.println(data);
               
           }  }
        con.close();
    } catch(SQLException sex){
        System.out.println(sex);
    }
    }

}

//int count = st.executeUpdate();
public class Runner {

    public static void main(String[] args) {

        String username = "Aaditya Katiyar";
        int age = 22;
        String city = "Bihar";
        int marks = 1000;

        dbConnect conn = new dbConnect();

        try {
            conn.getConnect();
        } catch (Exception ex) {
            System.out.println(ex);

        }

        try {
            conn.getData();
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

}
