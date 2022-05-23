package persoana;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class Client extends Persoana {
    protected Set<String> servicii;

    public Client()
    {
    }

    public Client(String nume, String prenume, String data_nastere, String CNP, Set<String> servicii)
    {
        super(nume, prenume, data_nastere, CNP);
        this.servicii = servicii;
    }

    public Client(String nume, String prenume, String data_nastere, String CNP)
    {
        super(nume, prenume, data_nastere, CNP);

    }

    public Client(ResultSet s) throws SQLException {
        this.nume = s.getString("nume");
        this.prenume = s.getString("prenume");
        this.data_nastere = s.getString("data_nastere");
        this.CNP = s.getString("CNP");
    }
    public void adauga_serviciu (String serviciu)
    {
        this.servicii.add(serviciu);
    }

    public void set_serviciu(Set<String> servicii)
    {
        this.servicii = servicii;
    }

    public Set<String> get_servicii()
    {
        return servicii;
    }

    @Override
    public String CSV(){
        String output = "";
        output += nume + ",";
        output += prenume + ",";
        output += data_nastere + ",";
        output += CNP + ",";
        output += servicii.size() +",";
        if (servicii.size() > 0) {
            for (String s : servicii) {
                output += s + " ";
            }
            output = output.substring(0, output.length() - 1);
        }
        return output;
    }
//    @Override Etapa 2
//    public String toString() {
//        String output = "Client\n";
//        output += "Nume: " + this.nume + "\n";
//        output += "Prenume: " + this.prenume + "\n";
//        output += "Data nastere: " + this.data_nastere + "\n";
//        output += "CNP: " + this.CNP + "\n";
//        if (servicii.size() > 0) {
//            output += "Lista servicii de efectuat: ";
//            for (String s : servicii) {
//                output += s + " ";
//            }
//            output += "\n";
//
//        }
//            return output;
//    }
        @Override
    public String toString() {
        String output = "Client\n";
        output += "Nume: " + this.nume + "\n";
        output += "Prenume: " + this.prenume + "\n";
        output += "Data nastere: " + this.data_nastere + "\n";
        output += "CNP: " + this.CNP + "\n";


            return output;
    }
    }
