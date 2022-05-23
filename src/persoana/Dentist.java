package persoana;

import javax.xml.transform.Result;
import java.sql.*;
public class Dentist extends Persoana {
    private String calificare;
    private int salariu;
    public Dentist(){}

    public Dentist(String nume,String prenume,String data_nastere ,String CNP, String calificare,int salariu){
        super(nume, prenume,data_nastere,CNP);
        this.calificare = calificare;
        this.salariu = salariu;
    }
    public Dentist(ResultSet s) throws SQLException {
        this.nume = s.getString("nume");
        this.prenume = s.getString("prenume");
        this.data_nastere = s.getString("data_nastere");
        this.CNP = s.getString("CNP");
        this.calificare = s.getString("calificare");
        this.salariu = s.getInt("salariu");
    }

    public void set_calificare(String calificare) {
        this.calificare = calificare;
    }

    public String get_calificare() {
        return calificare;
    }

    public void set_salariu(int salariu) {
        this.salariu = salariu;
    }

    public int get_salariu() {
        return salariu;
    }
    public String CSV(){
        String output = "";
        output += this.nume + ",";
        output += this.prenume + ",";
        output += this.data_nastere + ",";
        output += this.CNP + ",";
        output += this.calificare + ",";
        output += this.salariu + ",";
        return output;
    }
    @Override
    public String toString() {
        String output = "Dentist:\n";
        output += "Nume: " + this.nume + "\n";
        output += "Prenume: " + this.prenume + "\n";
        output += "Data nastere: " + this.data_nastere + "\n";
        output+= "CNP: " + this.CNP + "\n";
        output+= "Calificare: " +this.calificare + "\n";
        output += "Salariu: " + this.salariu + "\n";
        return output;
    }
}