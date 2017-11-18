package no.pederyo;

import no.pederyo.model.Hendelse;
import no.pederyo.model.Rom;
import no.pederyo.util.ReaderHjelp;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import static no.pederyo.util.CsvReaderUtil.readCSVInternett;
import static no.pederyo.util.ReaderHjelp.allerom;

public class Scraper {
    public static final String SEMINAR_ROM1 = "https://no.timeedit.net/web/hib/db1/student/ri1Q54.html";
    public static final String ALLESEMINAR = "https://no.timeedit.net/web/hib/db1/service/ri1AY6YYcnd8v5QYwYQrxgb1ZxgYxm98KaYravr5jY5awSadjc8vm5ZQ0Q522x60Yy5505YgX6g5Z5252Yg.html";
    public static final String LOGIN_FEIDE = "https://idp.feide.no/simplesaml/module.php/feide/login.php?asLen=196&AuthState=_9fca4163f7ea1def117e4e14f389d3a338a30db096%3Ahttps%3A%2F%2Fidp.feide.no%2Fsimplesaml%2Fsaml2%2Fidp%2FSSOService.php%3Fspentityid%3Durn%253Amace%253Afeide.no%253Aservices%253Ase.timeedit.hib%26cookieTime%3D1510783130%26RelayState%3D";
    public static final String DRIVER_LOKAL = "C:\\Program Files (x86)\\chromeDriver\\chromedriver.exe";
    public static final String TESTER = "https://no.timeedit.net/web/hib/db1/student/ri18840446X41YQ0X8Q82016Z011817XXY100Y851Y53431X8Y0YXXX5351Y1Y08431X1XY4YY8400Y0Y1180338XX08980901X8381xYY21Z5g85Z34W5022608X532yQ655Q5005.html";

    public static void main(String[] args) throws IOException {
        String url = LOGIN_FEIDE;
        // setup driver.
        System.setProperty("webdriver.chrome.driver", DRIVER_LOKAL);
        WebDriver driver = new ChromeDriver();
        driver.navigate().to(url);

        Cookie cookie = new Cookie("SimpleSAMLAuthToken", "_9287d4df126a74cce8048b2a5e89e228275db1c353");
        Cookie cookie2 = new Cookie("org_feide", "3Ahib");
        driver.manage().addCookie(cookie);
        // And now use this to visit Google
        Set<Cookie> allCookies = driver.manage().getCookies();

        // manipuler dom
        WebElement login = driver.findElement(By.className("submit"));
        WebElement username = driver.findElement(By.id("username"));
        WebElement pw = driver.findElement(By.id("password"));

        username.sendKeys(System.getenv("FEIDE_B"));
        pw.sendKeys(System.getenv("FEIDE_P"));

        login.submit();
        driver.navigate().to(ALLESEMINAR);
        readCSVInternett(ALLESEMINAR);
        driver.quit();
        printUtRomOgHendelse();
        finnFoersteLedig();
    }
    public static void printUtRomOgHendelse(){
        for(Rom r : allerom) {
            System.out.print("romnavn " + r.getNavn() + " ");
            for(Hendelse h : r.getHendelser()) {
                System.out.print(h.toString() + ", ");
            }
            System.out.println();
        }
    }
    public static void finnFoersteLedig() {
        for(int i = 0; i < allerom.size(); i ++) {
            Rom r = allerom.get(i);
            int lengde = r.getHendelser().size();
            for(int j = 0; j < lengde -1; j ++) {
                Hendelse h = r.getHendelser().get(j);
                Hendelse h1 = r.getHendelser().get(j+1);
                if(erLedig(h, h1)) {
                    System.out.println("Rom: " + r.getNavn() + " Er ledig fra: " + h.getSlutt() + " til: " + h1.getStart());
                }
            }
        }

    }
    public static boolean erLedig(Hendelse h, Hendelse h1) {
        String slutt = h.getSlutt().substring(0,2) +  h.getSlutt().substring(3,5);
        String start = h1.getStart().substring(0,2) +  h1.getStart().substring(3,5);
        int diff = Integer.parseInt(start) - Integer.parseInt(slutt);
        return diff >= 100;
    }
}
