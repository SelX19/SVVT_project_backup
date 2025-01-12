import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WhoSearchBar {

    private static WebDriver webDriver;
    private static String baseUrl;

    @BeforeAll
    public static void SetUp(){

        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--start-maximized");

        webDriver = new ChromeDriver(options);
        baseUrl = "https://www.airbnb.com/?refinement_paths%5B%5D=%2Fhomes&search_mode=flex_destinations_search&flexible_trip_lengths%5B%5D=one_week&location_search=MIN_MAP_BOUNDS&monthly_start_date=2025-01-01&monthly_length=3&monthly_end_date=2025-04-01&price_filter_input_type=0&channel=EXPLORE&price_max=83&search_type=category_change&price_filter_num_nights=5&selected_filter_order%5B%5D=price_max%3A83&category_tag=Tag%3A634";
    }

    @Test
    public void WhoTest() throws InterruptedException{
        webDriver.get(baseUrl);
        Thread.sleep(2000);

        WebElement searchBarWho = webDriver.findElement(By.xpath("//*[@id='search-tabpanel']/div/div[5]/div[2]/div[1]"));
        searchBarWho.click();
        Thread.sleep(1000);

        WebElement adultsPlusSign = webDriver.findElement(By.xpath("//*[@id='stepper-adults']/button[2]/span"));
        for(int i=0; i<3; i++){
            adultsPlusSign.click();
            Thread.sleep(1000); //selecting 2 adults, and waiting 1 second after each selection
        }

        WebElement adultsMinusSign = webDriver.findElement(By.xpath("//*[@id='stepper-adults']/button[1]/span"));
        adultsMinusSign.click();
        Thread.sleep(1000);

        WebElement childrenPlusSign = webDriver.findElement(By.xpath("//*[@id='stepper-children']/button[2]/span"));
        for(int i=0; i<4; i++){
            childrenPlusSign.click();
            Thread.sleep(1000);
        }

        WebElement childrenMinusSign = webDriver.findElement(By.xpath("//*[@id='stepper-children']/button[1]/span"));
        childrenMinusSign.click();
        Thread.sleep(1000);

        WebElement infantsPlusSign = webDriver.findElement(By.xpath("//*[@id='stepper-infants']/button[2]/span"));
        infantsPlusSign.click();
        Thread.sleep(1000);
        infantsPlusSign.click();
        Thread.sleep(1000);

        WebElement infantsMinusSign = webDriver.findElement(By.xpath("//*[@id='stepper-infants']/button[1]/span"));
        infantsMinusSign.click();
        Thread.sleep(1000);

        WebElement petsPlusSign = webDriver.findElement(By.xpath("//*[@id='stepper-pets']/button[2]/span"));
        for(int i=0; i<3; i++){
            petsPlusSign.click();
            Thread.sleep(1000);
        }

        WebElement petsMinusSign = webDriver.findElement(By.xpath("//*[@id='stepper-pets']/button[1]/span"));
        petsMinusSign.click();
        Thread.sleep(1000);
    }

    @AfterAll
    public static void TearDown(){
        if(webDriver!=null){
            webDriver.quit();
        }
    }
}
