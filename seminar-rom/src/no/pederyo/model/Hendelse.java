package no.pederyo.model;

public class Hendelse {
    private String dato;
    private String start;
    private String slutt;
    private String rom;

    public Hendelse() {
        this("","","",null);
    }

    public Hendelse(String dato, String start, String slutt, String rom) {
        this.dato = dato;
        this.start = start;
        this.slutt = slutt;
        this.rom = rom;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getSlutt() {
        return slutt;
    }

    public void setSlutt(String slutt) {
        this.slutt = slutt;
    }

    public String getRom() {
        return rom;
    }

    public void setRom(String rom) {
        this.rom = rom;
    }

    @Override
    public String toString() {
        return "Hendelse{" +
                "dato='" + dato + '\'' +
                ", start='" + start + '\'' +
                ", slutt='" + slutt + '\'' +
                ", rom=" + rom +
                '}';
    }
}
