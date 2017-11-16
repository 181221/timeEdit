package no.pederyo.util;

import no.pederyo.model.Rom;
import org.openqa.selenium.WebDriver;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvReaderUtil {
    public static List<Rom> readCSVInternett(String urlen) throws IOException {
        List<Rom> allerom = new ArrayList<>();
        String URL = "https://no.timeedit.net/web/hib/db1/service/ri1AY6YYcnd8v5QYwYQrxgb1ZxgYxm98KaYravr5jY5awSadjc8vm5ZQ0Q522x60Yy5505YgX6g5Z5252Yg.html";
        if (URL.contains(".html")) {
            URL = URL.replace("html", "csv");
        }
        java.net.URL url = new URL(URL);
        System.out.println(url.toString());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try {
            if (connection.getResponseCode() == 200) {
                System.out.println("ok");
                InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());
                BufferedReader br = new BufferedReader(streamReader);
                String line = br.readLine();
                line = br.readLine() + br.readLine() + br.readLine();
                String[] fieldsene = line.split(",");
                fieldsene = Arrays.copyOfRange(fieldsene, 3, fieldsene.length);
                while ((line = br.readLine()) != null && !line.isEmpty()) {
                    fieldsene = line.split(",");
                    Rom r = ReaderHjelp.setOppData(fieldsene);
                    allerom.add(r);
                }
                br.close();
            }else {
                System.out.println("feil");
            }
        }catch (MalformedURLException e) {
            System.out.println(e);
        }
        return allerom;

    }
}
