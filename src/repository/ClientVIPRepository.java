package repository;
import java.sql.*;
import db.*;
import persoana.Client;
import persoana.ClientVIP;

import java.util.*;
import java.util.Scanner;


public class ClientVIPRepository {

    public void add_clientVIP(ClientVIP clientVIP) {


        System.out.println(clientVIP.toString());
        String sql = "insert into client values (null, ?, ?, ?, ?, null)";
        try (PreparedStatement statement = Db.getInstance().prepareStatement(sql)) {
            statement.setString(1, clientVIP.get_nume());
            statement.setString(2, clientVIP.get_prenume());
            statement.setString(3, clientVIP.get_data_nastere());
            statement.setString(4, clientVIP.get_CNP());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void delete_clientVIP(String CNP) {
        String sql = "delete from clientVIP where CNP = ?";
        try (PreparedStatement statement = Db.getInstance().prepareStatement(sql)) {
            statement.setString(1, CNP);
            statement.execute();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    public ClientVIP read_client(String CNP) {
        ClientVIP c = null;
        String sql = "select * from ClientVIP where CNP = ?";
        try (PreparedStatement statement = Db.getInstance().prepareStatement(sql)) {
            statement.setString(1, CNP);
            ResultSet s = statement.executeQuery();
            s.next();
            c = new ClientVIP(s);
        } catch(SQLException e) {
            e.printStackTrace();
        }
        System.out.println(c);
        return c;
    }


    public void update_clientVIP(ClientVIP clientVIP){

        String sql = "update clientVIP set nume = ?, prenume = ? where CNP = ?";
        try (PreparedStatement statement = Db.getInstance().prepareStatement(sql)) {
            statement.setString(1, clientVIP.get_nume());
            statement.setString(2, clientVIP.get_prenume());
            statement.setString(3, clientVIP.get_CNP());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



}
