import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class LanguageAndCurrency {
    private static WebDriver webDriver;
    private static String baseUrl;
    private static WebDriverWait wait;

    @BeforeAll
    public static void SetUp(){
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--start-maximized");

        webDriver = new ChromeDriver(options);
        baseUrl = "https://www.airbnb.com";
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
    }

    @Test
    public void languageTest() throws InterruptedException{
        webDriver.get(baseUrl);
        Thread.sleep(16000);

        WebElement globeIcon = webDriver.findElement(By.xpath("//*[@id=\"react-application\"]/div/div/div[1]/div/div[3]/div[3]/div/div/div/header/div/div[3]/nav/div[1]/div/button/div/div"));
        globeIcon.click();
        Thread.sleep(5000);

        //assert the language
        WebElement currentLanguage = webDriver.findElement(By.xpath("//*[@id=\"panel--language_region_and_currency--0\"]/section[3]/div/ul/li[1]/a/div[1]"));
        assertEquals("English", currentLanguage.getText());
        Thread.sleep(1000);

        WebElement currentLanguageType = webDriver.findElement(By.xpath("//*[@id=\"panel--language_region_and_currency--0\"]/section[3]/div/ul/li[1]/a/div[2]"));
        assertEquals("United States", currentLanguageType.getText());
        Thread.sleep(1000);

        //switch to Bosnian language and assert the changes
        WebElement Bosnian = webDriver.findElement(By.xpath("//*[@id=\"panel--language_region_and_currency--0\"]/section[3]/div/ul/li[4]/a"));
        Bosnian.click();
        Thread.sleep(60000);

        WebElement globeIcon2 = webDriver.findElement(By.xpath("/html/body/div[5]/div/div/div[1]/div/div[3]/div[2]/div/div/div/header/div/div[3]/nav/div[1]/div/button/div/div"));
        globeIcon2.click();
        Thread.sleep(5000);

        WebElement newCurrentLanguage = webDriver.findElement(By.xpath("//*[@id=\"panel--language_region_and_currency--0\"]/section[3]/div/ul/li[1]/a/div[1]"));
        assertEquals("Bosanski", newCurrentLanguage.getText());
        Thread.sleep(1000);

        WebElement newCorrespondingCountry = webDriver.findElement(By.xpath("//*[@id=\"panel--language_region_and_currency--0\"]/section[3]/div/ul/li[1]/a/div[2]"));
        assertEquals("Bosna i Hercegovina", newCorrespondingCountry.getText());
        Thread.sleep(1000);

        WebElement translationButton = webDriver.findElement(By.xpath("//*[@id=\"panel--language_region_and_currency--0\"]/section[1]/div/ul/li/div/div[2]/button"));
        translationButton.click(); //deselect automated translation of property descriptions/reviews on Bosnian
        Thread.sleep(30000);

        /*
        WebElement clickOnProperty = webDriver.findElement(By.xpath("//*[@id=\"site-content\"]/div[2]/div[1]/div/div/div/div/div[1]/div/div[2]/div/div/div/div/div/div[1]/div/div/div[2]/div/div/div/div/a[1]/div/div/picture/img"));
        clickOnProperty.click();
        Thread.sleep(40000);

        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        WebElement review = webDriver.findElement(By.xpath("//*[@id=\"site-content\"]/div/div[1]/div[4]/div/div/div/div[2]/div/section/div[3]/div/div/div[1]/div/div[2]/div/div/span/span"));
        js.executeScript("arguments[0].scrollIntoView(true);", review);
        Thread.sleep(20000);

        assertEquals("Nagyon szép és rendezett,tökéletes volt minden.", review.getText());
        Thread.sleep(2000);
         */
    }

    @Test
    public void currencyTest() throws InterruptedException{
        webDriver.get(baseUrl);
        Thread.sleep(50000);

        //assert that the current currency is bosnian KM
        WebElement priceBosnian = webDriver.findElement(By.xpath("//*[@id=\"site-content\"]/div[2]/div[1]/div/div/div/div/div[1]/div/div[2]/div/div/div/div/div/div[2]/div[4]/div[2]/div/div/span/div/span[1]"));
        assertEquals("KM330 ", priceBosnian.getText());
        Thread.sleep(1000);

        WebElement globeButton = webDriver.findElement(By.xpath("//*[@id=\"react-application\"]/div/div/div[1]/div/div[3]/div[3]/div/div/div/header/div/div[3]/nav/div[1]/div/button/div/div"));
        globeButton.click();
        Thread.sleep(5000);

        //switch to currency section
        WebElement currencySection = webDriver.findElement(By.xpath("//*[@id=\"tab--language_region_and_currency--1\"]"));
        currencySection.click();
        Thread.sleep(2000);

        //choose chinese yuan as currency, and assert that it is a new currency
        WebElement chineseYuan = webDriver.findElement(By.xpath("//*[@id=\"panel--language_region_and_currency--1\"]/section/div/ul/li[6]/button"));
        chineseYuan.click();
        Thread.sleep(50000);

        WebElement priceExample = webDriver.findElement(By.xpath("//*[@id=\"site-content\"]/div[2]/div[1]/div/div/div/div/div[1]/div/div[2]/div/div/div/div/div/div[2]/div[4]/div[2]/div/div/span/div/span[1]"));
        assertEquals("￥1,280 ", priceExample.getText());

    }

    @AfterAll
    public static void TearDown(){
        if(webDriver!=null){
            webDriver.quit();
        }
    }
}
