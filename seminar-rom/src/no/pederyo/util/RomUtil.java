package no.pederyo.util;

import no.pederyo.model.Hendelse;
import no.pederyo.model.Rom;

import java.util.ArrayList;

import static no.pederyo.util.ReaderHjelp.allerom;

public class RomUtil {
    public static String FOERSTE_LEDIG = "";

    public static void printUtRomOgHendelse() {
        for (Rom r : allerom) {
            System.out.print("romnavn " + r.getNavn() + " ");
            for (Hendelse h : r.getHendelser()) {
                System.out.print(h.toString() + ", ");
            }
            System.out.println();
        }
    }

    private static ArrayList<String> finnLedig() {
        String ledige;
        ArrayList<String> ledigerom = new ArrayList<>();
        for (int i = 0; i < allerom.size(); i++) {
            Rom r = allerom.get(i);
            int lengde = r.getHendelser().size();
            for (int j = 0; j < lengde - 1; j++) {
                Hendelse h = r.getHendelser().get(j);
                Hendelse h1 = r.getHendelser().get(j + 1);
                if (erLedig(h, h1)) {
                    ledige = "Rom: " + r.getNavn() + " Er ledig fra: " + h.getSlutt() + " til: " + h1.getStart();
                    ledigerom.add(ledige);
                }
            }
        }
        FOERSTE_LEDIG = ledigerom.get(0);
        return ledigerom;
    }

    private static boolean erLedig(Hendelse h, Hendelse h1) {
        String slutt = h.getSlutt().substring(0, 2) + h.getSlutt().substring(3, 5);
        String start = h1.getStart().substring(0, 2) + h1.getStart().substring(3, 5);
        int diff = Integer.parseInt(start) - Integer.parseInt(slutt);
        return diff >= 100;
    }

    public static String lagMsg() {
        StringBuilder sb = new StringBuilder();
        for (String s : finnLedig()) {
            sb.append(s);
            sb.append("\n");
        }
        return sb.toString();
    }

}
