package no.pederyo.model;

import java.util.ArrayList;
import java.util.List;

public class Rom {
    private String navn;
    private Boolean erOpptatt;
    private List<Hendelse> hendelser;

    public Rom() {
        this("",null,null);
        hendelser = new ArrayList<>();
    }

    public Rom(String navn) {
        this.navn = navn;
        erOpptatt = null;
        hendelser = new ArrayList<>();
    }

    public Rom(String navn, Boolean erOpptatt, List<Hendelse> hendelser) {
        this.navn = navn;
        this.erOpptatt = erOpptatt;
        hendelser = new ArrayList<>();
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public Boolean getErOpptatt() {
        return erOpptatt;
    }

    public void setErOpptatt(Boolean erOpptatt) {
        this.erOpptatt = erOpptatt;
    }

    public List<Hendelse> getHendelser() {
        return hendelser;
    }

    public void setHendelser(List<Hendelse> hendelser) {
        this.hendelser = hendelser;
    }
}


