package persoana;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class ClientVIP extends Client{
    private float discount;
    public ClientVIP(){
    }
    public ClientVIP(String nume, String prenume, String data_nastere, String CNP, Set<String> servicii, float discount) {
        super(nume, prenume, data_nastere,CNP, servicii);
        this.discount = discount;
    }

    public ClientVIP(ResultSet s) throws SQLException {
        this.nume = s.getString("nume");
        this.prenume = s.getString("prenume");
        this.data_nastere = s.getString("data_nastere");
        this.CNP = s.getString("CNP");
        this.discount = s.getFloat("discount");
    }
    public ClientVIP(String nume, String prenume, String data_nastere, String CNP, float discount) {
        super(nume, prenume, data_nastere,CNP);
        this.discount = discount;
    }
    public void set_discount(String discount) {
        this.discount = Float.parseFloat(discount);
    }
    public float get_discount(float discount) {
        return discount;
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
        output += discount + "," ;
        output = output.substring(0, output.length() - 1);
        }
        return output;
    }
    @Override
    public String toString() {
        String output = "Client VIP\n";
        output += "Nume: " + this.nume + "\n";
        output += "Prenume: " + this.prenume + "\n";
        output += "Data nastere: " + this.data_nastere + "\n";
        output += "CNP: " + this.CNP + "\n";
        if (servicii.size() > 0) {
            output += "Lista servicii de efectuat: ";
            for (String s : servicii) {
                output += s + " ";
            }
            output += "\n";

        }
        output += "Discount: " + this.discount + "\n";
        return output;
    }

}
