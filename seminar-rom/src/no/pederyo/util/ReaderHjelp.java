package no.pederyo.util;

import no.pederyo.model.Hendelse;
import no.pederyo.model.Rom;

import java.util.Arrays;

public class ReaderHjelp {

    public static Rom setOppData(String[] felt) {
        String dato = felt[0];
        String start = felt[1].substring(1);
        String slutt = felt[3].substring(1);
        String romnavn =  parseRomNavn(felt[5].substring(1));
        Rom r = lagRom(romnavn);
        Hendelse h = lagHendelse(dato, start, slutt);
        r.getHendelser().add(h);
        return r;
    }
    public static String parseData(String data) {
        String dataString = data;
        switch (data) {
            case "romnavn": dataString = parseRomNavn(data);
            break;
        }
        return dataString;

    }
    public static String parseRomNavn(String rom) {
        if(rom.contains("\"")) {
            rom = rom.replace('"', ' ').substring(1);
        }
        if(rom.charAt(0) == ' ') {
            rom = rom.substring(1);
        }
        return rom;
    }

    public static Hendelse lagHendelse(String dato, String start, String slutt) {
        Hendelse h = new Hendelse();
        h.setDato(dato);
        h.setStart(start);
        h.setSlutt(slutt);
        return h;
    }

    public static Rom lagRom(String romnavn) {
        Rom r = new Rom();
        r.setNavn(romnavn);
        return r;
    }
}
