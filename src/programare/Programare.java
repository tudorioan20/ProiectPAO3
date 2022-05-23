package programare;

import persoana.Client;
import persoana.ClientVIP;
import persoana.Dentist;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Programare {
    protected Dentist dentist;
    protected Client client;

    protected ClientVIP clientVIP;
    protected Date data;
    protected String ora;

    public Programare(){}

    public Programare(Client client,Dentist dentist, Date data, String ora){

        this.client = client;
        this.dentist = dentist;
        this.data = data;
        this.ora = ora;
    }

    public Programare(ResultSet s) throws SQLException {
        this.client.set_CNP(s.getString("clientCNP"));
        this.client.set_CNP(s.getString("dentistCNP"));

    }
    public Programare(ClientVIP clientVIP, Dentist dentist, Date data, String ora){

        this.clientVIP = clientVIP;
        this.dentist = dentist;
        this.data = data;
        this.ora = ora;
    }
    public void set_client(Client client) {
        this.client = client;
    }
    public void set_clientVIP(ClientVIP clientVIP) {
        this.clientVIP = clientVIP;
    }
    public void set_dentist(Dentist dentist) {
        this.dentist = dentist;
    }

    public void set_data(Date data) {
        this.data = data;
    }

    public void set_ora(String ora) {
        this.ora = ora;
    }

    public Client get_client() {
        return client;
    }

    public ClientVIP get_clientVIP() {
        return clientVIP;
    }
    public Dentist get_dentist() {
        return dentist;
    }

    public Date get_data() {
        return data;
    }

    public String get_ora() {
        return ora;
    }

    public String CSV(){
        String output = "";
        output += this.data + ",";
        output += this.ora + ",";
        output += this.dentist.get_nume() + ",";output += this.dentist.get_prenume() + ",";
        if (client != null)
        {output += this.client.get_nume() + ",";output += this.client.get_prenume() + ",";}
        if (clientVIP != null)
        {output += this.clientVIP.get_nume() + ",";output += this.clientVIP.get_prenume() + ",";}

        return output;
    }
    @Override
    public String toString(){
        String output = "-- Programare facuta in ziua de ";
        SimpleDateFormat zi_luna_an = new SimpleDateFormat("dd/MM/yyyy");
        output +=  zi_luna_an.format(this.data) + "\n";
        output += "la ora " + this.ora + "\n";
        if (client != null)
        {output += "Nume client: " + client.get_nume() + " " + client.get_prenume() +"\n";}
        if (clientVIP != null)
        {output += "Nume client: " + clientVIP.get_nume() + " " + clientVIP.get_prenume() +"\n";}
        output += "Nume dentist: " + dentist.get_nume() + " " + dentist.get_prenume()+"\n";
        return output;
    }
}