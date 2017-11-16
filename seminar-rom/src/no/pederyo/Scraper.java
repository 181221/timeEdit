package no.pederyo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import util.CsvReaderUtil;

import java.io.IOException;

public class Scraper {
    public static final String SEMINAR_ROM1 = "https://no.timeedit.net/web/hib/db1/student/ri1Q54.csv";
    public static final String LOGIN_FEIDE = "https://idp.feide.no/simplesaml/module.php/feide/login.php?asLen=196&AuthState=_9fca4163f7ea1def117e4e14f389d3a338a30db096%3Ahttps%3A%2F%2Fidp.feide.no%2Fsimplesaml%2Fsaml2%2Fidp%2FSSOService.php%3Fspentityid%3Durn%253Amace%253Afeide.no%253Aservices%253Ase.timeedit.hib%26cookieTime%3D1510783130%26RelayState%3D";
    public static final String DRIVER_LOKAL = "C:\\Program Files (x86)\\chromeDriver\\chromedriver.exe";

    public static void main(String[] args) throws IOException {
        String url = LOGIN_FEIDE;
        // setup driver.
        System.setProperty("webdriver.chrome.driver", DRIVER_LOKAL);
        WebDriver driver = new ChromeDriver();
        driver.navigate().to(url);

        // manipuler dom
        WebElement login = driver.findElement(By.className("submit"));
        WebElement username = driver.findElement(By.id("username"));
        WebElement pw = driver.findElement(By.id("password"));

        username.sendKeys(System.getenv("FEIDE_B"));
        pw.sendKeys(System.getenv("FEIDE_P"));


        login.submit();
        driver.navigate().to("https://no.timeedit.net/web/hib/db1/student/ri1Q54.html");
        CsvReaderUtil.readCSVInternett(SEMINAR_ROM1);
    }
}
