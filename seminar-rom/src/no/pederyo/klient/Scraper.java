package no.pederyo.klient;

import no.pederyo.util.RomUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;

import static no.pederyo.model.Mail.TILMAIL;
import static no.pederyo.util.CsvReaderUtil.readCSVInternett;
import static no.pederyo.util.MailUtil.setUpMail;
import static no.pederyo.util.RomUtil.LedigNaa;

public class Scraper {
    private static final String SUBJECT_LEDIGE_ROM = "Ledige rom: ";
    private static final String ALLESEMINAR = "https://no.timeedit.net/web/hib/db1/service/ri1AY6YYcnd8v5QYwYQrxgb1ZxgYxm98KaYravr5jY5awSadjc8vm5ZQ0Q522x60Yy5505YgX6g5Z5252Yg.html";
    private static final String LOGIN_FEIDE = "https://idp.feide.no/simplesaml/module.php/feide/login.php?asLen=196&AuthState=_9fca4163f7ea1def117e4e14f389d3a338a30db096%3Ahttps%3A%2F%2Fidp.feide.no%2Fsimplesaml%2Fsaml2%2Fidp%2FSSOService.php%3Fspentityid%3Durn%253Amace%253Afeide.no%253Aservices%253Ase.timeedit.hib%26cookieTime%3D1510783130%26RelayState%3D";
    private static final String DRIVER_LOKAL = "C:\\Program Files (x86)\\chromeDriver\\chromedriver.exe";

    public static void main(String[] args) throws IOException {
        // setup driver.
        WebDriver driver = setUpDriver();
        driver.navigate().to(LOGIN_FEIDE);

        // manipuler dom
        WebElement login = driver.findElement(By.className("submit"));
        WebElement username = driver.findElement(By.id("username"));
        WebElement pw = driver.findElement(By.id("password"));
        username.sendKeys(System.getenv("FEIDE_B"));
        pw.sendKeys(System.getenv("FEIDE_P"));

        // login og submit.
        login.submit();
        driver.navigate().to(ALLESEMINAR);
        readCSVInternett(ALLESEMINAR);
        driver.quit();

        // send sms og mail.
        String msg = RomUtil.lagMsg();
        setUpMail(TILMAIL, msg, SUBJECT_LEDIGE_ROM);
        TwilioSMS.SendSMS(LedigNaa());
    }

    private static WebDriver setUpDriver() {
        System.setProperty("webdriver.chrome.driver", DRIVER_LOKAL);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        return new ChromeDriver(chromeOptions);
    }

}
