package cabinet;

public class Adresa {
    private String oras;
    private String strada;
    private String cod_postal;

    public Adresa(){}

    public Adresa(String oras, String strada,String cod_postal){
        this.oras = oras;
        this.strada = strada;
        this.cod_postal = cod_postal;
    }

    public void set_oras(String oras) {
        this.oras = oras;
    }

    public void set_strada(String strada) {
        this.strada = strada;
    }

    public void set_cod_postal(String cod_postal) {
        this.cod_postal = cod_postal;
    }

    public String get_oras() {
        return oras;
    }

    public String get_strada() {
        return strada;
    }

    public String get_cod_postal() {
        return cod_postal;
    }

    @Override
    public String toString(){
        String output = "Oras: "+ this.oras +"\n";
        output += "Strada: "+ this.strada +"\n";
        output += "Cod postal: "+ this.cod_postal +"\n";

        return output;
    }

}