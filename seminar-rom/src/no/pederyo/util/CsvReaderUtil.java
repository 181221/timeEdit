package no.pederyo.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class CsvReaderUtil {
    public static void readCSVInternett(String urlen) throws IOException {

        String URL = urlen;
        if (URL.contains("html")) {
            URL = URL.replace("html", "csv");
        }
        try {
            java.net.URL url = new URL(URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            if (connection.getResponseCode() == 200) {
                InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());
                BufferedReader br = new BufferedReader(streamReader);
                String line = br.readLine();
                line = br.readLine() + br.readLine() + br.readLine();
                String[] fieldsene = line.split(",");
                fieldsene = Arrays.copyOfRange(fieldsene, 3, fieldsene.length);
                while ((line = br.readLine()) != null && !line.isEmpty()) {
                    fieldsene = line.split(",");
                    ReaderHjelp.setOppData(fieldsene);
                    String dato = fieldsene[0].substring(0);
                    String tidFra = fieldsene[1].substring(1);
                    String tidTil = fieldsene[3].substring(1);
                    String aktivitet = fieldsene[6].substring(1);
                    String navn = fieldsene[8].substring(1);
                    String beskrivelse = fieldsene[10].substring(1);
                    String sted = fieldsene[9].substring(1);
                    System.out.println(Arrays.toString(fieldsene));

                }
                br.close();
            }
        }catch (MalformedURLException e) {
            System.out.println(e);
        }

    }
}