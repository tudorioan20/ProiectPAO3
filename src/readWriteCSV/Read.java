package readWriteCSV;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Read {
    private Read(){}
    private static Read instance = null;

    public static Read create_instance(){
        if(instance == null){
            instance = new Read();
        }
        return instance;
    }

    public List<String[]> readCSVFile(String file){
        List<String[]> list = new ArrayList<>();
        try(var in = new BufferedReader(new FileReader(file))){
            String line = "";

            while((line = in.readLine()) != null){
                String[] fields = line.split(",");
                list.add(fields);
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        return list;
    }
}