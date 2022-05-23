package cabinet;

import persoana.Client;
import persoana.ClientVIP;
import persoana.Dentist;
import persoana.Secretara;
import programare.Programare;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Cabinet{
    Set<Client> clienti;

    Set<ClientVIP> clientiVIP;
    Set<Dentist> dentisti;

    Set <Secretara> secretare;
    List<Programare> programari;
    Adresa adresa;

    public Cabinet(){
        clienti = new HashSet<Client>();
        clientiVIP = new HashSet<ClientVIP>();
        dentisti = new HashSet<Dentist>();
        secretare = new HashSet<Secretara>();
        programari = new ArrayList<Programare>();
        adresa = new Adresa();
    }

    public Cabinet(Set<Client> clienti,Set<ClientVIP> clientiVIP,Set<Dentist> dentisti,Set<Secretara> secretare, List<Programare> programari, Adresa adresa){
        this.clienti = clienti;
        this.clientiVIP = clientiVIP;
        this.dentisti = dentisti;
        this.secretare = secretare;
        this.programari = programari;
        this.adresa = adresa;
    }

    public void set_clienti(Set<Client> clienti) {
        this.clienti = clienti;
    }
    public void set_clientiVIP(Set<ClientVIP> clientiVIP) {
        this.clientiVIP = clientiVIP;
    }
    public void set_dentisti(Set<Dentist> dentisti) {
        this.dentisti = dentisti;
    }

    public void set_secretare(Set<Secretara> secretare) {
        this.secretare = secretare;
    }
    public void set_programari(List<Programare> programari) {
        this.programari = programari;
    }

    public void set_adresa(Adresa adresa) {
        this.adresa = adresa;
    }

    public Set<Client> get_clienti() {
        return clienti;
    }
    public Set<ClientVIP> get_clientiVIP() {
        return clientiVIP;
    }
    public Set<Dentist> get_dentisti() {
        return dentisti;
    }

    public Set<Secretara> get_secretare() {
        return secretare;
    }
    public List<Programare> get_programari() {
        return programari;
    }

    public Adresa get_adresa() {
        return adresa;
    }

    @Override
    public String toString(){
        String output = "cabinet\n";

        if(clienti.size() == 0){
            output += "Nu exista clienti inca. \n";
        }
        if(clientiVIP.size() == 0){
            output += "Nu exista clienti VIP inca. \n";
        }
        for(Client client : clienti){
            output += client.toString() + "\n";
        }
        for(ClientVIP clientVIP : clientiVIP){
            output += clientVIP.toString() + "\n";
        }

        if(dentisti.size() == 0){
            output += "Acest cabinet nu are dentisti disponibili. \n";
        }
        for(Dentist d : dentisti) {
            output += d.toString() + "\n";
        }

        if(secretare.size() == 0){
            output += "Acest cabinet nu are secretare disponibile. \n";
        }
        for(Secretara s : secretare) {
            output += s.toString() + "\n";
        }
        int i = 1;
        for(Programare programare : programari) {

            output += i + "." + "\n" + programare.toString() + "\n";
            i++;
        }
        if(adresa != null) {
            output += "Adresa acestui cabinet: \n" + this.adresa.toString() + "\n\n";
        }
        else{
            output += "Acest cabinet nu are o adresa inca. \n\n";
        }
        return output;
    }

    public void adauga_client(Client client){
        clienti.add(client);
    }

    public void adauga_clientVIP(ClientVIP clientVIP){
        clientiVIP.add(clientVIP);
    }
    public void adauga_dentist(Dentist dentist){
        dentisti.add(dentist);
    }
    public void adauga_secretara(Secretara secretara){
        secretare.add(secretara);
    }
    public void adauga_programare(Programare programare){
        programari.add(programare);
    }

    public boolean exista_client(String CNP){
        for(Client c : clienti){
            if(CNP.equals(c.get_CNP())){
                return true;
            }
        }
        return  false;
    }

    public void sterge_client(String CNP) {
        for(Client c : clienti){
            if(CNP.equals(c.get_CNP())){
                clienti.remove(c);
                break;
            }
        }
    }
    public boolean exista_clientVIP(String CNP){
        for(ClientVIP c : clientiVIP){
            if(CNP.equals(c.get_CNP())){
                return true;
            }
        }
        return  false;
    }
    public void sterge_clientVIP(String CNP) {
        for(ClientVIP c : clientiVIP){
            if(CNP.equals(c.get_CNP())){
                clientiVIP.remove(c);
                break;
            }
        }
    }
    public boolean exista_dentist(String CNP){
        for(Dentist d : dentisti){
            if(CNP.equals(d.get_CNP())){
                return true;
            }
        }
        return  false;
    }

    public void sterge_dentist(String CNP) {
        for(Dentist d : dentisti){
            if(CNP.equals(d.get_CNP())){
                dentisti.remove(d);
                break;
            }
        }
    }

    public boolean exista_secretara(String CNP){
        for(Secretara s : secretare){
            if(CNP.equals(s.get_CNP())){
                return true;
            }
        }
        return  false;
    }

    public void sterge_secretara(String CNP) {
        for(Secretara s : secretare){
            if(CNP.equals(s.get_CNP())){
                secretare.remove(s);
                break;
            }
        }
    }
    public Client gaseste_client(String CNP){
        for(Client c : clienti){
            if(CNP.equals(c.get_CNP())){
                return c;
            }
        }
        return null;
    }

    public ClientVIP gaseste_clientVIP(String CNP){
        for(ClientVIP c : clientiVIP){
            if(CNP.equals(c.get_CNP())){
                return c;
            }
        }
        return null;
    }
    public Dentist gaseste_dentist(String CNP){
        for(Dentist d : dentisti){
            if(CNP.equals(d.get_CNP())){
                return d;
            }
        }
        return null;
    }

    public Secretara gaseste_secretara(String CNP){
        for(Secretara s : secretare){
            if(CNP.equals(s.get_CNP())){
                return s;
            }
        }
        return null;
    }
    public void adauga_serviciu(String CNP, String serviciu){
        for(Client c : clienti){
            if(CNP.equals(c.get_CNP())){
                c.adauga_serviciu(serviciu);
                break;
            }
        }
    }

    public void adauga_serviciuVIP(String CNP, String serviciu){
        for(ClientVIP c : clientiVIP){
            if(CNP.equals(c.get_CNP())){
                c.adauga_serviciu(serviciu);
                break;
            }
        }
    }
    public void afisare_clienti(String serviciu){
        for(Client c : clienti){
            if(c.get_servicii().contains(serviciu)){
                System.out.print(c.get_prenume() + " "+ c.get_prenume()+"\n");
            }
        }
        System.out.print("\n");
    }

    public void afisare_clientiVIP(String serviciu){
        for(ClientVIP c : clientiVIP){
            if(c.get_servicii().contains(serviciu)){
                System.out.print(c.get_prenume() + " "+ c.get_prenume()+"\n");
            }
        }
        System.out.print("\n");
    }
    public List<Programare> programari_clienti(String CNP){
        List<Programare> lista_programari = new ArrayList<Programare>();

        for(Programare p : programari){
            if(CNP.equals(p.get_client().get_CNP())){
                lista_programari.add(p);
            }
        }
        return lista_programari;
    }

    public List<Programare> programari_clientiVIP(String CNP){
        List<Programare> lista_programariVIP = new ArrayList<Programare>();

        for(Programare p : programari){
            if(CNP.equals(p.get_client().get_CNP())){
                lista_programariVIP.add(p);
            }
        }
        return lista_programariVIP;
    }
    public Programare cauta_programare( Client client,Dentist dentist, Date data, String ora){
        for(Programare p : programari){
            if(dentist == p.get_dentist() && client == p.get_client() && data.compareTo(p.get_data()) == 0 && ora.equals(p.get_ora())){
                return p;
            }
        }
        return null;
    }
    public Programare cauta_programareVIP( ClientVIP clientVIP,Dentist dentist, Date data, String ora){
        for(Programare p : programari){
            if(dentist == p.get_dentist() && clientVIP == p.get_client() && data.compareTo(p.get_data()) == 0 && ora.equals(p.get_ora())){
                return p;
            }
        }
        return null;
    }
    public void sterge_programare(Programare p){
        programari.remove(p);
    }

    public void modifica_programare(Programare p){
        Scanner console = new Scanner(System.in);
        SimpleDateFormat zi_luna_an = new SimpleDateFormat("dd/MM/yyyy");
        System.out.print("Data: ");
        String d = console.nextLine();
        Date data = new Date();
        try {
            data = zi_luna_an.parse(d);
        } catch (ParseException e) {

            e.printStackTrace();
        }

        System.out.print("Ora noua: ");
        String ora = console.nextLine();

        p.set_data(data);
        p.set_ora(ora);
    }
}