package repository;
import java.sql.*;
import db.*;
import persoana.Client;
import java.util.*;
import java.util.Scanner;


public class ClientRepository {

    public void add_client(Client client) {


        System.out.println(client.toString());
        String sql = "insert into client values (null, ?, ?, ?, ?, null)";
        try (PreparedStatement statement = Db.getInstance().prepareStatement(sql)) {
            statement.setString(1, client.get_nume());
            statement.setString(2, client.get_prenume());
            statement.setString(3, client.get_data_nastere());
            statement.setString(4, client.get_CNP());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



    public void delete_client(String CNP) {
        String sql = "delete from client where CNP = ?";
        try (PreparedStatement statement = Db.getInstance().prepareStatement(sql)) {
            statement.setString(1, CNP);
            statement.execute();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    public Client read_client(String CNP) {
        Client c = null;
        String sql = "select * from client where CNP = ?";
        try (PreparedStatement statement = Db.getInstance().prepareStatement(sql)) {
            statement.setString(1, CNP);
            ResultSet s = statement.executeQuery();
            s.next();
            c = new Client(s);
        } catch(SQLException e) {
            e.printStackTrace();
        }
        System.out.println(c);
        return c;
    }
    public void update_client(Client client){

        String sql = "update client set nume = ?, prenume = ? where CNP = ?";
        try (PreparedStatement statement = Db.getInstance().prepareStatement(sql)) {
            statement.setString(1, client.get_nume());
            statement.setString(2, client.get_prenume());
            statement.setString(3, client.get_CNP());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



}
