package no.pederyo;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;

import static java.lang.Thread.sleep;


public class Booker {
    public static final String FEIDE_URL = "https://idp.feide.no/simplesaml/module.php/feide/login.php?asLen=196&AuthState=_9fca4163f7ea1def117e4e14f389d3a338a30db096%3Ahttps%3A%2F%2Fidp.feide.no%2Fsimplesaml%2Fsaml2%2Fidp%2FSSOService.php%3Fspentityid%3Durn%253Amace%253Afeide.no%253Aservices%253Ase.timeedit.hib%26cookieTime%3D1510783130%26RelayState%3D";
    public static final String ROM_URL = "https://no.timeedit.net/web/hib/db1/student/ri1Q51.html#006177";

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\chromeDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.navigate().to(FEIDE_URL);

        Cookie cookie = new Cookie("SimpleSAMLAuthToken", "_9287d4df126a74cce8048b2a5e89e228275db1c353");
        Cookie cookie2 = new Cookie("org_feide", "3Ahib");
        driver.manage().addCookie(cookie);
        Set<Cookie> allCookies = driver.manage().getCookies();

        WebElement submit = driver.findElement(By.className("submit"));
        WebElement username = driver.findElement(By.id("username"));
        WebElement pw = driver.findElement(By.id("password"));
        WebElement js = driver.findElement(By.id("input_has_js"));
        username.sendKeys(System.getenv("FEIDE_B"));
        pw.sendKeys(System.getenv("FEIDE_P"));
        submit.submit();

        driver.navigate().to(ROM_URL);
        driver.findElement(By.id("timeHourSpec3")).click();
        WebElement velgStart = driver.findElement(By.className("timeHourStart3"));
        WebElement velgSlutt = driver.findElement(By.className("timeHourEnd3"));
        velgStart.sendKeys("08");
        velgSlutt.sendKeys("09");
        velgStart.click();
        velgSlutt.click();

        driver.findElement(By.className("objectinputsearchbutton")).click();
        sleep(1000);
        WebElement gruppeRom = driver.findElement(By.className("infolinkobject"));
        gruppeRom.click();
        long start = System.currentTimeMillis();
        sleep(1000);
        ((JavascriptExecutor) driver).executeAsyncScript(
                "console.log(\"hei\")",
                "var book = document.querySelector(\"#newResTimeDiv > tbody > tr > td.first > select.ui-widget.ui-state-default.timedrop.timeHourStart2\")",
                "window.setTimeout(arguments[arguments.length - 1], 500);",
                "console.log(book)"

        );


        WebElement k = driver.findElement(By.xpath("//*[@id=\"newResTimeDiv\"]/tbody/tr/td[1]/select[1]"));
        System.out.println(k.getText());
        System.out.println("Elapsed time: " + (System.currentTimeMillis() - start));
        //WebElement book = driver.findElement(By.tagName("#objectselectionresult > table > tbody > tr:nth-child(3) > td > div.weekContainer > div:nth-child(5) > div.weekDiv > div.slotfree2.slotfreetarget"));
        //book.click();
        //sleep(10000);
        //driver.quit();
    }
}
