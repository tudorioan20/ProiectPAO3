package persoana;

public abstract class Persoana {
    protected String nume;

    protected String prenume;
    protected String data_nastere;

    protected String CNP;

    //constructori
    public Persoana(){}

    public Persoana(String nume,String prenume,String data_nastere ,String CNP){
        this.nume = nume;
        this.prenume = prenume;
        this.data_nastere = data_nastere;
        this.CNP = CNP;
    }

    //Setteri
    public void set_nume(String nume)
    {
        this.nume = nume;
    }
    public void set_prenume(String prenume)
    {
        this.prenume = prenume;
    }
    public void set_data_nastere(String data_nastere)
    {
        this.data_nastere = data_nastere;
    }
    public void set_CNP(String CNP)
    {
        this.CNP = CNP;
    }

    public String get_nume()
    {
        return nume;
    }
    public String get_prenume()
    {
        return prenume;
    }
    public String get_data_nastere()
    {
        return data_nastere;
    }

    public String get_CNP()
    {
        return CNP;
    }

    public abstract String CSV();

    @Override
    public String toString()
    {
        String output = "Nume: " + this.nume + "\n";
        output += "Prenume: " + this.prenume + "\n";
        output += "Data nastere: " + this.data_nastere + "\n";
        output+= "CNP: " + this.CNP + "\n";

        return output;
    }
}