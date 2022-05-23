package readWriteCSV;
import persoana.Client;
import persoana.ClientVIP;
import persoana.Dentist;
import programare.Programare;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Write {
    private Write(){}
    private static Write instance = null;

    public static Write create_instance(){
        if(instance == null){
            instance = new Write();
        }
        return instance;
    }



    public void output(Client client){
        try(var out = new BufferedWriter(new FileWriter("src/clienti", true))){
            out.write("\n" + client.CSV());
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void output(ClientVIP clientVIP){
        try(var out = new BufferedWriter(new FileWriter("src/clientiVIP", true))){
            out.write("\n" + clientVIP.CSV());
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public void output(Dentist dentist){
        try(var out = new BufferedWriter(new FileWriter("src/dentisti", true))){
            out.write("\n" + dentist.CSV());
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public void output(Programare programare){
        try(var out = new BufferedWriter(new FileWriter("src/programari", true))){
            out.write("\n" + programare.CSV());
        } catch (IOException e){
            e.printStackTrace();
        }
    }



}