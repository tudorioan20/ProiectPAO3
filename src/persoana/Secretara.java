package persoana;

public class Secretara extends Persoana {
    private int salariu;

    public Secretara()
    {
    }

    public Secretara(String nume, String prenume, String data_nastere, String CNP, int salariu)
    {
        super(nume, prenume, data_nastere, CNP);
        this.salariu = salariu;
    }


    public void set_salariu(int salariu)
    {
        this.salariu = salariu;
    }

    public int get_salariu()
    {
        return salariu;
    }
    public String CSV(){return null;}
    @Override
    public String toString() {
        String output = "Secretara\n";
        output += "Nume: " + this.nume + "\n";
        output += "Prenume: " + this.prenume + "\n";
        output += "Data nastere: " + this.data_nastere + "\n";
        output += "CNP: " + this.CNP + "\n";
        output += "Salariu: " + this.salariu + "\n";
        return output;
    }

}
