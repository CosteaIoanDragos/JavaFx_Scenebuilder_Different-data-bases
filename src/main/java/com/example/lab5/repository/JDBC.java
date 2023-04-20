package com.example.lab5.repository;

import com.example.lab5.domain.Patient ;
import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.Vector;

public class JDBC extends PatientRepo{
    public JDBC() {
        super();
        openConnection();
        //initTables();
        this.getAll();
    }

    @Override
    public void add(com.example.lab5.domain.Patient el) {
        super.add(el);
        AddReservation(el);
    }

    @Override
    public void updateAtIndex(com.example.lab5.domain.Patient el, Integer index) {
        super.updateAtIndex(el, index);
        updateReservation(el,index);
    }

    @Override
    public void remove(com.example.lab5.domain.Patient el) {
        super.remove(el);
        deleteReservation(el.getId());
    }
    private static final String JDBC_URL = "jdbc:sqlite:F:/Info/AdvanceProgramming/lab5/lab5/src/DataBase1";
    //private static final String JDBC_URL = "jdbc:sqlite:F:/Info/AdvanceProgramming/lab2/lab2_w2-CosteaIoan811/app/src/DataBase1";
    private Connection conn = null;
    public void openConnection() {
        try {
            //with DriverManager
            //if (conn == null || conn.isClosed())
            //conn = DriverManager.getConnection(JDBC_URL);

            // with DataSource
            SQLiteDataSource ds = new SQLiteDataSource();
            ds.setUrl(JDBC_URL);
            if (conn == null || conn.isClosed())
                conn = ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void closeConnection()
    {
        try
        {
            conn.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public void createSchema()
    {
        try
        {
            final Statement stmt = conn.createStatement();

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS patients(Name varchar(200),PhoneNumber varchar(200),Id int,Year int,Month int,Day int ,Hour int,Minute int);");
            stmt.close();
        }
        catch (SQLException e) {
            System.err.println("[ERROR] createSchema : " + e.getMessage());
        }
    }
    public void initTables() {
        final String[] patients = new String[]{
                "Vlad 3232 1 2222 11 1 32 32",
                "Ioan 1269 1 2000 11 1 32 32",
                "Matei 2232 1 2022 11 1 32 32"
        };

        try {
            PreparedStatement statement =
                    conn.prepareStatement("INSERT INTO patients VALUES (?, ?, ? ,? ,? ,? ,? ,? )");
             for (String s : patients) {
                String[] tokens = s.split(" ");
                statement.setString(1,tokens[0]);
                statement.setString(2, tokens[1]);
                statement.setInt(3, Integer.parseInt(tokens[2]));
                statement.setInt(4, Integer.parseInt(tokens[3]));
                statement.setInt(5, Integer.parseInt(tokens[4]));
                statement.setInt(6, Integer.parseInt(tokens[5]));
                statement.setInt(7, Integer.parseInt(tokens[6]));
                statement.setInt(8, Integer.parseInt(tokens[7]));
                statement.executeUpdate();
            }
            statement.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void AddReservation(Patient c)
    {
        try
        {
            PreparedStatement statement = conn.prepareStatement(
                    "INSERT INTO patients VALUES (?, ?, ?, ?,?,?,?,?)");
            statement.setString(1, c.getName());
            statement.setString(2, c.getPhonenumber());
            statement.setInt(3, c.getId());
            statement.setInt(4, c.getDate().getYear());
            statement.setInt(5, c.getDate().getMonth());
            statement.setInt(6, c.getDate().getDay());
            statement.setInt(7, c.getDate().getHours());
            statement.setInt(8, c.getDate().getMinutes());



            statement.executeUpdate();
            statement.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateReservation (Patient c, int index){
        try{
            conn.setAutoCommit(false);
            String Name = c.getName();
            String Phonenumber = c.getPhonenumber();
             int id = c.getId();
             int year=c.getDate().getYear();
            int month=c.getDate().getMonth();
            int day=c.getDate().getDay();
            int hour=c.getDate().getHours();
            int minute=c.getDate().getMinutes();

            PreparedStatement updateFinal = conn.prepareStatement(
                    "UPDATE patients set Name  = ?,  Phonenumber=  ?, fId = ?,Year=?,Month=?,Day=?,Hour=?,Minute=? where Id = ?");

            updateFinal.setString(1, c.getName());
            updateFinal.setString(2, c.getPhonenumber());
            updateFinal.setInt(3, c.getId());
            updateFinal.setInt(4, c.getDate().getYear());
            updateFinal.setInt(5, c.getDate().getMonth());
            updateFinal.setInt(6, c.getDate().getDay());
            updateFinal.setInt(7, c.getDate().getHours());
            updateFinal.setInt(8, c.getDate().getMinutes());
            updateFinal.executeUpdate();
            conn.setAutoCommit(true);
            updateFinal.close();

        }catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }
    public void deleteReservation(int id) {

        try {

            PreparedStatement statement = conn.prepareStatement(
                    "DELETE FROM patients WHERE Id=?");

            statement.setInt(1, id);
            statement.executeUpdate();
            statement.close();


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
       public Vector<Patient> getAll()
        {
            PatientRepo patients = new PatientRepo();
            try
            {
                PreparedStatement statement = conn.prepareStatement(
                        "SELECT * from patients");
                ResultSet rs = statement.executeQuery();
                while (rs.next())
                {
                    Patient b = new Patient(
                            rs.getString("Name"),
                            rs.getString("PhoneNumber"),
                            rs.getInt("Id"),
                            rs.getInt("Year"),
                            rs.getInt("Month"),
                            rs.getInt("Day"),
                            rs.getInt("Hour"),
                            rs.getInt("Minute"));
                        this.elements.add(b);

                }
                rs.close();
                statement.close();
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }
            return elements;
        }
    }



