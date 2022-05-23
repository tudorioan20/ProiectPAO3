package Main;
import cabinet.Cabinet;
import persoana.Client;
import persoana.ClientVIP;
import persoana.Dentist;
import persoana.Secretara;
import programare.Programare;
import readWriteCSV.Write;
import readWriteCSV.Read;
import readWriteCSV.Audit;

import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import repository.DentistRepository;
import repository.ClientRepository;
import repository.ClientVIPRepository;
import java.util.*;

public class Service {
    final private Cabinet cabinet;
    final private Scanner console;
    Read read;
    Write write;
    Audit audit;

    DentistRepository dentistRepository;
    ClientRepository clientRepository;

    ClientVIPRepository clientVIPRepository;
    public Service(){
        this.cabinet = new Cabinet();
        this.console = new Scanner(System.in);
        this.read = Read.create_instance();
        this.write = Write.create_instance();
        this.audit = Audit.create_instance();
        this.dentistRepository = new DentistRepository();
        this.clientRepository = new ClientRepository();
        this.clientVIPRepository = new ClientVIPRepository();
    }
    public Dentist buildDentist(String DentistDetails) {

        String[] attributes = DentistDetails.split("\\s");
        String nume = attributes[0];
        System.out.println(attributes);
        String prenume = attributes[1];
        String data_nastere = attributes[2];
        String CNP = attributes[3];
        String calificare = attributes[4];
        int salariu = Integer.parseInt(attributes[5]);
        return new Dentist(nume, prenume, data_nastere, CNP,calificare,salariu);
    }
    public void addDentist(Dentist dentist) {

        dentistRepository.add_dentist(dentist);
    }
    public void deleteDentist(String CNP) {

        dentistRepository.delete_dentist(CNP);
    }
    public void updateDentist(String CNP) {
        Dentist dentist = dentistRepository.read_dentist(CNP);
        System.out.print("Nume dentist: ");
        String nume = console.nextLine();
        System.out.print("Prenume dentist: ");
        String prenume = console.nextLine();
        System.out.print("Calificare: ");
        String calificare = console.nextLine();
        System.out.print("Salariu: ");
        int salariu = console.nextInt();
        console.nextLine();
        dentist.set_nume(nume);
        dentist.set_prenume(prenume);
        dentist.set_calificare(calificare);
        dentist.set_salariu(salariu);
        dentistRepository.update_dentist(dentist);
    }
    public Dentist readDentist(String CNP){
        return dentistRepository.read_dentist(CNP);
    }

    public Client  buildClient(String ClientDetails) {

        String[] attributes = ClientDetails.split("\\s");
        String nume = attributes[0];
        String prenume = attributes[1];
        String data_nastere = attributes[2];
        String CNP = attributes[3];
        Client c = new Client(nume, prenume, data_nastere, CNP);
        return c;
    }

    public void addClient(Client client) {

        clientRepository.add_client(client);
    }
    public void deleteClient(String CNP) {

        clientRepository.delete_client(CNP);
    }
    public void updateClient(String CNP) {
        Client client = clientRepository.read_client(CNP);
        System.out.print("Nume client: ");
        String nume = console.nextLine();
        System.out.print("Prenume client: ");
        String prenume = console.nextLine();
        client.set_nume(nume);
        client.set_prenume(prenume);
        clientRepository.update_client(client);
    }
    public Client readClient(String CNP){
        return clientRepository.read_client(CNP);
    }
    public ClientVIP buildClientVIP(String ClientDetails) {

        String[] attributes = ClientDetails.split("\\s");
        String nume = attributes[0];
        System.out.println(attributes);
        String prenume = attributes[1];
        String data_nastere = attributes[2];
        String CNP = attributes[3];
        float discount = Float.parseFloat(attributes[4]);
        return new ClientVIP(nume, prenume, data_nastere, CNP, discount);
    }
    public void addClientVIP(ClientVIP clientVIP) {

        clientVIPRepository.add_clientVIP(clientVIP);
    }
    public void deleteClientVIP(String CNP) {

        clientVIPRepository.delete_clientVIP(CNP);
    }
    public void updateClientVIP(String CNP) {
        ClientVIP clientVIP = clientVIPRepository.read_client(CNP);
        System.out.print("Nume client: ");
        String nume = console.nextLine();
        System.out.print("Prenume client: ");
        String prenume = console.nextLine();
        System.out.print("Discount: ");
        String discount = console.nextLine();
        clientVIP.set_nume(nume);
        clientVIP.set_prenume(prenume);
        clientVIPRepository.update_clientVIP(clientVIP);
    }
    public ClientVIP readClientVIP(String CNP){
        return clientVIPRepository.read_client(CNP);
    }
    public void read_data_from_CSV(){
        SimpleDateFormat zi_luna_an = new SimpleDateFormat("dd/MM/yyyy");

        String file = "src/clienti";
        List<String[]> list = read.readCSVFile(file);
        for(String[] line : list){
            int nr_servicii = Integer.parseInt(line[4]);
            Set<String> servicii = new HashSet<String>();
            for(var j = 0; j < nr_servicii; j++){
                servicii.add(line[j+5]);
            }
            Client client = new Client(line[0], line[1], line[2],line[3],servicii);
            this.cabinet.adauga_client(client);
        }
        file = "src/clientiVIP";
        list = read.readCSVFile(file);
        for(String[] line : list){
            int nr_servicii = Integer.parseInt(line[4]);
            Set<String> servicii = new HashSet<String>();
            for(var j = 0; j <= nr_servicii; j++){
                servicii.add(line[j+5]);
            }
            ClientVIP clientVIP = new ClientVIP(line[0], line[1], line[2],line[3],servicii,Float.parseFloat(line[4+nr_servicii+1]));
            this.cabinet.adauga_client(clientVIP);
        }

        file = "src/dentisti";
        list = read.readCSVFile(file);
        for(String[] line : list){
            Dentist dentist = new Dentist(line[0], line[1], line[2],line[3],line[4],Integer.parseInt(line[5]));
            this.cabinet.adauga_dentist(dentist);
        }


        file = "src/programari";
        list = read.readCSVFile(file);
        for(String[] line : list){
            Date data_programare = new Date();
            try {
                data_programare = zi_luna_an.parse(line[0]);
            } catch (ParseException e) {

                e.printStackTrace();
            }
            Programare prog_noua = new Programare( this.cabinet.gaseste_client(line[3]),this.cabinet.gaseste_dentist(line[2]), data_programare, line[2]);
            this.cabinet.adauga_programare(prog_noua);
        }
    }


    public void meniu(){
        String meniu = "Cabinet Stomatologic" + "\n";
        meniu += "0. Afisare detalii cabinet" + "\n";
        meniu += "1. Adauga client" + "\n";
        meniu += "2. Adauga client VIP" + "\n";
        meniu += "3. Adauga dentist" + "\n";
        meniu += "4. Adauga secretara" + "\n";
        meniu += "5. Sterge client" + "\n";
        meniu += "6. Sterge client VIP" + "\n";
        meniu += "7. Sterge dentist" + "\n";
        meniu += "8. Sterge secretara" + "\n";
        meniu += "9. Setare adresa" + "\n";
        meniu += "10. Adauga serviciu" + "\n";
        meniu += "11. Adauga programare" + "\n";
        meniu += "12. Sterge programare" + "\n";
        meniu += "13. Modifica programare" + "\n";
        meniu += "14. Afisare programari" + "\n";
        meniu += "15. EXIT" + "\n";
        System.out.println(meniu);
    }

    public void print_cabinet(){
        audit.output_audit("Afisare detalii cabinet");
        System.out.print(cabinet.toString());

    }

    public void adauga_client() {
        audit.output_audit("Adauga client");
        System.out.print("Nume client: ");
        String nume = console.nextLine();
        System.out.print("Prenume client: ");
        String prenume = console.nextLine();
        System.out.print("Data nastere: ");
        String data_nastere = console.nextLine();
        System.out.print("CNP: ");
        String CNP = console.nextLine();
        Set<String> servicii = new HashSet<String>();

        System.out.print("Numar servicii dorite: ");
        int nr = console.nextInt();
        String serviciu;
        serviciu = console.nextLine();

        for(int i = 1; i <= nr; i++){
            System.out.print("Serviciul "+i+": ");
            serviciu = console.nextLine();
            servicii.add(serviciu);
        }

        Client client = new Client(nume, prenume, data_nastere,CNP,servicii);
        this.cabinet.adauga_client(client);
        write.output(client);
        System.out.print("Clientul a fost adaugat cu succes! \n\n");
    }

    public void adauga_client_VIP() {

        audit.output_audit("Adauga client VIP");
        System.out.print("Nume client VIP: ");
        String nume = console.nextLine();
        System.out.print("Prenume client VIP: ");
        String prenume = console.nextLine();
        System.out.print("Data nastere: ");
        String data_nastere = console.nextLine();
        System.out.print("CNP: ");
        String CNP = console.nextLine();
        Set<String> servicii = new HashSet<String>();

        System.out.print("Numar servicii dorite: ");
        int nr = console.nextInt();
        String serviciu;
        serviciu = console.nextLine();

        for(int i = 1; i <= nr; i++){
            System.out.print("Serviciul "+i+": ");
            serviciu = console.nextLine();
            servicii.add(serviciu);
        }

        System.out.print("Discount: ");
        int discount = console.nextInt();
        console.nextLine();
        ClientVIP client = new ClientVIP(nume, prenume, data_nastere,CNP,servicii,discount);
        this.cabinet.adauga_clientVIP(client);
        write.output(client);
        System.out.print("Clientul a fost adaugat cu succes! \n\n");
    }
    public void adauga_dentist() {

        audit.output_audit("Adauga dentist");
        System.out.print("Nume dentist: ");
        String nume = console.nextLine();
        System.out.print("Prenume dentist: ");
        String prenume = console.nextLine();
        System.out.print("Data nastere: ");
        String data_nastere = console.nextLine();
        System.out.print("CNP: ");
        String CNP = console.nextLine();
        Set<String> servicii = new HashSet<String>();
        System.out.print("Calificare: ");
        String calificare = console.nextLine();
        System.out.print("Salariu: ");
        int salariu = console.nextInt();
        console.nextLine();
        Dentist dentist = new Dentist(nume, prenume, data_nastere,CNP, calificare,salariu);
        this.cabinet.adauga_dentist(dentist);
        write.output(dentist);
        System.out.print("Dentistul a fost adaugat cu succes! \n\n");
    }

    public void adauga_secretara() {

        audit.output_audit("Adauga secretara");
        System.out.print("Nume secretara: ");
        String nume = console.nextLine();
        System.out.print("Prenume secretara: ");
        String prenume = console.nextLine();
        System.out.print("Data nastere: ");
        String data_nastere = console.nextLine();
        System.out.print("CNP: ");
        String CNP = console.nextLine();
        System.out.print("Salariu: ");
        int salariu = console.nextInt();
        console.nextLine();
        Secretara secretara = new Secretara(nume, prenume, data_nastere,CNP,salariu);
        this.cabinet.adauga_secretara(secretara);
        System.out.print("Secretara a fost adaugata cu succes! \n\n");
    }



    public void sterge_client(){
        audit.output_audit("Sterge client");
        System.out.print("CNP client: ");
        String CNP_client = console.nextLine();

        if(cabinet.exista_client(CNP_client)){
            cabinet.sterge_client(CNP_client);
            System.out.print("Clientul a fost sters!\n");
        }
        else{
            System.out.print("Nu exista acest client in sistem! \n\n");
        }
    }

    public void sterge_clientVIP(){
        audit.output_audit("Sterge client VIP");
        System.out.print("CNP client VIP: ");
        String CNP_clientVIP = console.nextLine();

        if(cabinet.exista_clientVIP(CNP_clientVIP)){
            cabinet.sterge_clientVIP(CNP_clientVIP);
            System.out.print("Clientul VIP a fost sters!\n");
        }
        else{
            System.out.print("Nu exista acest client VIP in sistem! \n\n");
        }
    }

    public void sterge_dentist(){
        audit.output_audit("Sterge dentist");
        System.out.print("CNP dentist: ");
        String CNP_dentist = console.nextLine();
        if(cabinet.exista_dentist(CNP_dentist)){
            cabinet.sterge_dentist(CNP_dentist);
            System.out.print("Dentistul a fost sters! \n\n");
        }
        else{
            System.out.print("Nu exista acest dentist in sistem! \n\n");
        }
    }

    public void sterge_secretara(){
        audit.output_audit("Sterge secretara");
        System.out.print("CNP secretara: ");
        String CNP_secretara = console.nextLine();
        if(cabinet.exista_secretara(CNP_secretara)){
            cabinet.sterge_secretara(CNP_secretara);
            System.out.print("Secretara a fost stearsa! \n\n");
        }
        else{
            System.out.print("Nu exista aceasta secretara in sistem! \n\n");
        }
    }
    public void setare_adresa(){
        audit.output_audit("Setare adresa");
        System.out.print("Oras: ");
        String oras = console.nextLine();

        System.out.print("Strada: ");
        String strada = console.nextLine();

        System.out.print("Cod postal: ");
        String cod_postal = console.nextLine();
        System.out.print("\n");

        cabinet.get_adresa().set_oras(oras);
        cabinet.get_adresa().set_strada(strada);
        cabinet.get_adresa().set_cod_postal(cod_postal);
    }

    public void adauga_serviciu(){

        audit.output_audit("Adauga serviciu");
        System.out.print("CNP Client:");
        String CNP = console.nextLine();

        boolean verificare = cabinet.exista_client(CNP);
        if(verificare){
            System.out.print("Serviciu: ");
            String serviciu = console.nextLine();
            cabinet.adauga_serviciu(CNP, serviciu);
            System.out.print("Serviciul a fost adaugat cu succes! \n\n");
        }
        else{
            System.out.print("Nu exista acest client in sistem! \n\n");
        }
    }
    public void adauga_programare() {

        audit.output_audit("Adauga programare");
        SimpleDateFormat zi_luna_an = new SimpleDateFormat("dd/MM/yyyy");

        System.out.print("CNP dentist:\n");
        String CNP_dentist = console.nextLine();
        boolean verificare1 = cabinet.exista_dentist(CNP_dentist);
        Dentist d = cabinet.gaseste_dentist(CNP_dentist);

        if(verificare1) {
            System.out.print("Client VIP?:Da/Nu ");
            String answer = console.nextLine();

            while("Da".equals(answer) == false && "Nu".equals(answer) == false) {
                System.out.print(answer);
                System.out.print("Client VIP?:Da/Nu ");
                answer = console.nextLine();
            }

            if ("Nu".equals(answer) == true) {
                System.out.print("CNP client: ");
                String CNP_client = console.nextLine();
                boolean verificare2 = cabinet.exista_client(CNP_client);
                Client c = cabinet.gaseste_client(CNP_client);

                if (verificare2) {
                    System.out.print("Data programare: ");
                    String data = console.nextLine();
                    Date data_prog = new Date();

                    try {
                        data_prog = zi_luna_an.parse(data);
                    } catch (ParseException e) {

                        e.printStackTrace();
                    }

                    System.out.print("Ora programare: ");
                    String ora = console.nextLine();

                    Programare prog_noua;
                    prog_noua = new Programare(c,d, data_prog, ora);
                    this.cabinet.adauga_programare(prog_noua);
                    write.output(prog_noua);
                    System.out.print("Programarea a fost adaugata cu succes! \n\n");
                }
                else {
                    System.out.print("Nu exista acest client in sistem! \n\n");
                }
            } else if ("Da".equals(answer) == true) {

                System.out.print("CNP client VIP: ");
                String CNP_clientVIP = console.nextLine();
                boolean verificare2 = cabinet.exista_clientVIP(CNP_clientVIP);
                ClientVIP c = cabinet.gaseste_clientVIP(CNP_clientVIP);

                if (verificare2) {
                    System.out.print("Data programare: ");
                    String data = console.nextLine();
                    Date data_prog = new Date();

                    try {
                        data_prog = zi_luna_an.parse(data);
                    } catch (ParseException e) {

                        e.printStackTrace();
                    }

                    System.out.print("Ora programare: ");
                    String ora = console.nextLine();

                    Programare prog_noua;
                    prog_noua = new Programare(c,d, data_prog, ora);
                    this.cabinet.adauga_programare(prog_noua);
                    write.output(prog_noua);
                    System.out.print("Programarea a fost adaugata cu succes! \n\n");
                }
                else {
                    System.out.print("Nu exista acest client VIP in sistem! \n\n");
                }
            }
        }
        else {
            System.out.print("Nu exista acest dentist in sistem! \n\n");
        }
    }
    public void sterge_programare(){

        audit.output_audit("Sterge programare");
        SimpleDateFormat zi_luna_an = new SimpleDateFormat("dd/MM/yyyy");
        System.out.print("CNP dentist: ");
        String CNP_dentist = console.nextLine();

        boolean verificare1 = cabinet.exista_dentist(CNP_dentist);
        Dentist d = cabinet.gaseste_dentist(CNP_dentist);

        if(verificare1) {
            System.out.print("Client VIP?:Da/Nu ");
            String answer = console.nextLine();

            while ("Da".equals(answer) == false && "Nu".equals(answer) == false) {
                System.out.print(answer);
                System.out.print("Client VIP?:Da/Nu ");
                answer = console.nextLine();
            }
            if ("Nu".equals(answer) == true) {
                System.out.print("CNP client: ");
                String CNP_client = console.nextLine();

                boolean verficare2 = cabinet.exista_client(CNP_client);
                Client client = cabinet.gaseste_client(CNP_client);

                if (verficare2) {

                    System.out.print("Data programare: ");
                    String data = console.nextLine();
                    Date data_prog = new Date();

                    try {
                        data_prog = zi_luna_an.parse(data);
                    } catch (ParseException e) {

                        e.printStackTrace();
                    }

                    System.out.print("Ora programare: ");
                    String ora = console.nextLine();
                    Programare prog = cabinet.cauta_programare(cabinet.gaseste_client(CNP_client), cabinet.gaseste_dentist(CNP_dentist), data_prog, ora);
                    if (prog != null) {
                        this.cabinet.sterge_programare(prog);
                        System.out.print("\n");
                        System.out.print("Programarea a fost stearsa cu succes! \n\n");
                    } else {
                        System.out.print("Programarea nu a fost gasita! \n\n");
                    }
                } else {
                    System.out.print("Nu exista acest client in sistem! \n\n");
                }
            } else if ("Da".equals(answer) == true) {

                System.out.print("CNP client VIP: ");
                String CNP_clientVIP = console.nextLine();

                boolean verficare2 = cabinet.exista_clientVIP(CNP_clientVIP);
                ClientVIP client = cabinet.gaseste_clientVIP(CNP_clientVIP);

                if (verficare2) {

                    System.out.print("Data programare: ");
                    String data = console.nextLine();
                    Date data_prog = new Date();

                    try {
                        data_prog = zi_luna_an.parse(data);
                    } catch (ParseException e) {

                        e.printStackTrace();
                    }

                    System.out.print("Ora programare: ");
                    String ora = console.nextLine();
                    Programare prog = cabinet.cauta_programareVIP(cabinet.gaseste_clientVIP(CNP_clientVIP), cabinet.gaseste_dentist(CNP_dentist), data_prog, ora);
                    if (prog != null) {
                        this.cabinet.sterge_programare(prog);
                        System.out.print("\n");
                        System.out.print("Programarea a fost stearsa cu succes! \n\n");
                    } else {
                        System.out.print("Programarea nu a fost gasita! \n\n");
                    }
                }
            }
        }
        else{
            System.out.print("Nu exista acest dentist in sistem! \n\n");
        }
    }

    public void modifica_programare(){

        audit.output_audit("Modifica programare");
        SimpleDateFormat zi_luna_an = new SimpleDateFormat("dd/MM/yyyy");
        System.out.print("CNP dentist: ");
        String CNP_dentist = console.nextLine();

        boolean verificare1 = cabinet.exista_dentist(CNP_dentist);
        Dentist d = cabinet.gaseste_dentist(CNP_dentist);

        if(verificare1) {
            System.out.print("Client VIP?:Da/Nu ");
            String answer = console.nextLine();
            while ("Da".equals(answer) == false && "Nu".equals(answer) == false) {
                System.out.print(answer);
                System.out.print("Client VIP?:Da/Nu ");
                answer = console.nextLine();
            }
            if ("Nu".equals(answer) == true) {
                System.out.print("CNP client: ");
                String CNP_client = console.nextLine();

                boolean verficare2 = cabinet.exista_client(CNP_client);
                Client client = cabinet.gaseste_client(CNP_client);


                if(verficare2) {

                System.out.print("Data programare: ");
                String data = console.nextLine();
                Date data_prog = new Date();
                try {
                    data_prog = zi_luna_an.parse(data);
                } catch (ParseException e) {

                    e.printStackTrace();
                }

                System.out.print("Ora programare: ");
                String ora = console.nextLine();
                Programare prog = cabinet.cauta_programare( cabinet.gaseste_client(CNP_client),cabinet.gaseste_dentist(CNP_dentist), data_prog, ora);
                if(prog != null) {
                    this.cabinet.modifica_programare(prog);
                    System.out.print("\n");
                    System.out.print("Programarea a fost modificata cu succes! \n\n");
                }
                else{
                    System.out.print("Programarea nu a fost gasita! \n\n");
                }
            }
            else{
                System.out.print("Nu exista acest client in sistem! \n\n");
            }
        } else if ("Da".equals(answer) == true) {
                System.out.print("CNP client VIP: ");
                String CNP_clientVIP = console.nextLine();

                boolean verficare2 = cabinet.exista_clientVIP(CNP_clientVIP);
                ClientVIP clientVIP = cabinet.gaseste_clientVIP(CNP_clientVIP);


                if(verficare2) {

                    System.out.print("Data programare: ");
                    String data = console.nextLine();
                    Date data_prog = new Date();

                    try {
                        data_prog = zi_luna_an.parse(data);
                    } catch (ParseException e) {

                        e.printStackTrace();
                    }

                    System.out.print("Ora programare: ");
                    String ora = console.nextLine();
                    Programare prog = cabinet.cauta_programare( cabinet.gaseste_clientVIP(CNP_clientVIP),cabinet.gaseste_dentist(CNP_dentist), data_prog, ora);
                    if(prog != null) {
                        this.cabinet.modifica_programare(prog);
                        System.out.print("\n");
                        System.out.print("Programarea a fost modificata cu succes! \n\n");
                    }
                    else{
                        System.out.print("Nu exista aceasta programare in sistem! \n\n");
                    }
                }
                else{
                    System.out.print("Nu exista acest client in sistem! \n\n");
                }
            }
        } else{
            System.out.print("Nu exista acest dentist in sistem! \n\n");
        }
    }
    public void afisareProgramari(){
        audit.output_audit("Afisare programari");
        for(Programare prog : cabinet.get_programari()){
            System.out.print(prog + "\n");
        }

    }

}