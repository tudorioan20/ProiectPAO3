package repository;
import java.sql.*;
import db.*;
import programare.Programare;

import java.sql.Date;
import java.util.*;
import java.util.Scanner;


public class ProgramareRepository {

    public void add_programare(Programare programare) {


        System.out.println(programare.toString());
        String sql = "insert into programare values (null, ?, ?, ?, ?)";
        try (PreparedStatement statement = Db.getInstance().prepareStatement(sql)) {
            statement.setString(1, programare.get_client().get_CNP());
            statement.setString(2, programare.get_dentist().get_CNP());
            statement.setDate(3, (Date) programare.get_data());
            statement.setString(4,programare.get_ora());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void delete_Programare(String CNP) {
        String sql = "delete from dentist where clientCNP = ? and dentistCNP =?";
        try (PreparedStatement statement = Db.getInstance().prepareStatement(sql)) {
            statement.setString(1, CNP);
            statement.execute();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    public Programare read_programare(String CNP) {
        Programare p = null;
        String sql = "select * from dentist where clientCNP = ? and dentistCNP =?";
        try (PreparedStatement statement = Db.getInstance().prepareStatement(sql)) {
            statement.setString(1, CNP);
            ResultSet s = statement.executeQuery();
            s.next();
            p = new Programare(s);
        } catch(SQLException e) {
            e.printStackTrace();
        }
        System.out.println(p);
        return p;
    }
    public void update_programare(Programare programare){

        String sql = "update programare set  data = ?, ora =? where clientCNP = ? and dentistCNP = ?,";
        try (PreparedStatement statement = Db.getInstance().prepareStatement(sql)) {
            statement.setDate(1, (Date) programare.get_data());
            statement.setString(2, programare.get_ora());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



}
