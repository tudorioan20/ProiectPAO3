package Main;

import persoana.Dentist;
import persoana.Client;
import persoana.ClientVIP;
import programare.Programare;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Service serviciu = new Service();
        serviciu.meniu();
        serviciu.read_data_from_CSV();
        Scanner console  = new Scanner(System.in);

        int optiune = 0;
        while (optiune != 15){
            System.out.print("Optiune: ");
            optiune = console.nextInt();

            switch (optiune) {
//                case 0 -> serviciu.print_cabinet(); Etapa 2
//                case 1 -> serviciu.adauga_client();
//                case 2 -> serviciu.adauga_client_VIP();
//                case 3 -> serviciu.adauga_dentist();
//                case 4 -> serviciu.adauga_secretara();
//                case 5 -> serviciu.sterge_client();
//                case 6 -> serviciu.sterge_clientVIP();
//                case 7 -> serviciu.sterge_dentist();
//                case 8 -> serviciu.sterge_secretara();
//                case 9 -> serviciu.setare_adresa();
//                case 10 -> serviciu.adauga_serviciu();
//                case 11 -> serviciu.adauga_programare();
//                case 12 -> serviciu.sterge_programare();
//                case 13 -> serviciu.modifica_programare();
//                case 14 -> serviciu.afisareProgramari();
                case 15 -> System.out.print("EXIT! \n");
                case 16 -> {console.nextLine();String str = console.nextLine();
                    Dentist d = serviciu.buildDentist(str);System.out.println(d.toString());serviciu.addDentist(d);
                }
                case 17 -> {console.nextLine();String str = console.nextLine();serviciu.deleteDentist(str);}
                case 18 -> {console.nextLine();String str = console.nextLine();serviciu.readDentist(str);}
                case 19 -> {console.nextLine();String str = console.nextLine();serviciu.updateDentist(str);}

                case 20 -> {console.nextLine();String str = console.nextLine();
                    Client c = serviciu.buildClient(str);System.out.println(c);serviciu.addClient(c);
                }
                case 21 -> {console.nextLine();String str = console.nextLine();serviciu.deleteClient(str);}
                case 22 -> {console.nextLine();String str = console.nextLine();serviciu.readClient(str);}
                case 23 -> {console.nextLine();String str = console.nextLine();serviciu.updateClient(str);}

                case 24 -> {console.nextLine();String str = console.nextLine();
                    ClientVIP c = serviciu.buildClientVIP(str);System.out.println(c);serviciu.addClientVIP(c);
                }
                case 25 -> {console.nextLine();String str = console.nextLine();serviciu.deleteClient(str);}
                case 26 -> {console.nextLine();String str = console.nextLine();serviciu.readClient(str);}
                case 27 -> {console.nextLine();String str = console.nextLine();serviciu.updateClient(str);}
                default -> System.out.print("Ati introdus o optiune invalida! \n\n");
            }
        }
    }
}