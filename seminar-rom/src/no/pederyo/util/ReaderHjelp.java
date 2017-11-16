package no.pederyo.util;

import no.pederyo.model.Hendelse;
import no.pederyo.model.Rom;

import java.util.Arrays;

public class ReaderHjelp {

    public static Rom setOppData(String[] felt) {
        String dato = felt[0];
        String start = felt[1].substring(1);
        String slutt = felt[3].substring(1);
        String romnavn =  felt[5].substring(1);
        Rom r = lagRom(romnavn);
        Hendelse h = lagHendelse(dato, start, slutt);
        r.getHendelser().add(h);
        //System.out.println(Arrays.toString(felt));
        return r;
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
