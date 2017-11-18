package no.pederyo.util;

import no.pederyo.model.Hendelse;
import no.pederyo.model.Rom;

import java.util.ArrayList;

public class ReaderHjelp {
    public static ArrayList<Rom> allerom;

    public ReaderHjelp() {
        allerom = new ArrayList<>();
    }

    public static Rom lagRom(String romnavn) {
        Rom r = new Rom();
        r.setNavn(romnavn);
        return r;
    }

    public void setOppData(String[] felt) {
        String dato = felt[0];
        String start = felt[1].substring(1);
        String slutt = felt[3].substring(1);
        String romnavn =  parseRomNavn(felt[5].substring(1));
        Hendelse h = lagHendelse(dato, start, slutt, romnavn);
        if(!inneholder(romnavn)) {
            Rom r = lagRom(romnavn);
            r.getHendelser().add(h);
            allerom.add(r);
        }else{
            finnRomOgLeggTil(romnavn,  h);
        }
    }

    public boolean inneholder(String r) {
        for(int i = 0; i < allerom.size(); i ++) {
            if(allerom.get(i).getNavn().equals(r)) {
                return true;
            }
        }
        return false;
    }

    public void finnRomOgLeggTil(String r, Hendelse h ) {
        for(int i = 0; i < allerom.size(); i ++) {
            Rom rom = allerom.get(i);
            if(rom.getNavn() != null) {
                if(r.equals(rom.getNavn())) {
                    allerom.get(i).getHendelser().add(h);
                }
            }
        }
    }

    public String parseData(String data) {
        String dataString = data;
        switch (data) {
            case "romnavn": dataString = parseRomNavn(data);
                break;
        }
        return dataString;

    }

    public String parseRomNavn(String rom) {
        if(rom.contains("\"")) {
            rom = rom.replace('"', ' ').substring(1);
        }
        if(rom.charAt(0) == ' ') {
            rom = rom.substring(1);
        }
        return rom;
    }

    public Hendelse lagHendelse(String dato, String start, String slutt, String romnavn) {
        Hendelse h = new Hendelse();
        h.setDato(dato);
        h.setStart(start);
        h.setSlutt(slutt);
        h.setRom(romnavn);
        return h;
    }

    public ArrayList<Rom> getAllerom() {
        return allerom;
    }

    public void setAllerom(ArrayList<Rom> allerom) {
        ReaderHjelp.allerom = allerom;
    }


}
