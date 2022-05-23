package repository;
import java.sql.*;
import db.*;
import persoana.Dentist;
import java.util.*;
import java.util.Scanner;


public class DentistRepository {

    public void add_dentist(Dentist dentist) {


        System.out.println(dentist.toString());
        String sql = "insert into dentist values (null, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = Db.getInstance().prepareStatement(sql)) {
            statement.setString(1, dentist.get_nume());
            statement.setString(2, dentist.get_prenume());
            statement.setString(3, dentist.get_data_nastere());
            statement.setString(4, dentist.get_CNP());
            statement.setString(5, dentist.get_calificare());
            statement.setInt(6, dentist.get_salariu());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void delete_dentist(String CNP) {
        String sql = "delete from dentist where CNP = ?";
        try (PreparedStatement statement = Db.getInstance().prepareStatement(sql)) {
            statement.setString(1, CNP);
            statement.execute();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    public Dentist read_dentist(String CNP) {
        Dentist d = null;
        String sql = "select * from dentist where CNP = ?";
        try (PreparedStatement statement = Db.getInstance().prepareStatement(sql)) {
            statement.setString(1, CNP);
            ResultSet s = statement.executeQuery();
            s.next();
            d = new Dentist(s);
        } catch(SQLException e) {
            e.printStackTrace();
        }
        System.out.println(d);
        return d;
    }
    public void update_dentist(Dentist dentist){

            String sql = "update dentist set nume = ?, prenume = ?, data_nastere = ?, calificare = ?, salariu = ? where CNP = ?";
        try (PreparedStatement statement = Db.getInstance().prepareStatement(sql)) {
            statement.setString(1, dentist.get_nume());
            statement.setString(2, dentist.get_prenume());
            statement.setString(3, dentist.get_data_nastere());
            statement.setString(6, dentist.get_CNP());
            statement.setString(4, dentist.get_calificare());
            statement.setInt(5, dentist.get_salariu());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



}
