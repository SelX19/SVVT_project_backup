import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

//unifying all search functionalities into one:
public class MainFunctionalitySearchBar {

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
    public void SearchStaysTest() throws InterruptedException{

        webDriver.get(baseUrl);
        Thread.sleep(1000);

        //already at stays' section

        //inputting location
        WebElement whereField = webDriver.findElement(By.xpath("//*[@id='search-tabpanel']/div/div[1]/div"));
        whereField.click();
        Thread.sleep(1000);

        WebElement whereInput = webDriver.findElement(By.xpath("//*[@id='search-tabpanel']/div/div[1]/div/label/div/input"));
        whereInput.sendKeys("Sarajevo");
        whereInput.sendKeys(Keys.ENTER);
        Thread.sleep(1000);

        //selecting dates
        WebElement checkInDate = webDriver.findElement(By.xpath("//*[@id='panel--tabs--0']/div/div[1]/div/div/div/div[2]/div[2]/div/div[2]/div/table/tbody/tr[3]/td[6]/div/div"));
        checkInDate.click();
        Thread.sleep(1000);

        WebElement checkOutDate = webDriver.findElement(By.xpath("//*[@id='panel--tabs--0']/div/div[1]/div/div/div/div[2]/div[2]/div/div[2]/div/table/tbody/tr[4]/td[5]/div/div"));
        checkOutDate.click();
        Thread.sleep(1000);

        //selecting people
        WebElement searchBarWho = webDriver.findElement(By.xpath("//*[@id='search-tabpanel']/div/div[5]/div[2]/div[1]"));
        searchBarWho.click();
        Thread.sleep(1000);

        WebElement adultsPlusSign = webDriver.findElement(By.xpath("//*[@id='stepper-adults']/button[2]/span"));
        for(int i=0; i<2; i++){
            adultsPlusSign.click();
            Thread.sleep(1000); //selecting 2 adults
        }

        WebElement childrenPlusSign = webDriver.findElement(By.xpath("//*[@id='stepper-children']/button[2]/span"));
        for(int i=0; i<3; i++){ //selecting 3 kids
            childrenPlusSign.click();
            Thread.sleep(1000);
        }

        //search
        String urlBeforeSearch = webDriver.getCurrentUrl();
        Thread.sleep(1000);

        WebElement searchButton = webDriver.findElement(By.xpath("//*[@id='search-tabpanel']/div/div[5]/div[2]/div[3]/button"));
        searchButton.click();
        Thread.sleep(1000);

        String urlAfterSearch = webDriver.getCurrentUrl();
        Thread.sleep(1000);

        assertNotEquals(urlBeforeSearch, urlAfterSearch);
    }

    @Test
    public void SearchExperiencesTest() throws InterruptedException{

        webDriver.get(baseUrl);
        Thread.sleep(1000);

        // checking if experiences are selected, and selecting if not
        WebElement experiences = webDriver.findElement(By.id("search-block-tab-EXPERIENCES"));
        String ariaSelected = experiences.getAttribute("aria-selected");
        if("false".equals(ariaSelected)){
            experiences.click();
            ariaSelected = "true";
        }
        else{
            System.out.println("Stays already selected.");
        }
        assertEquals("true", ariaSelected);

        WebElement whereInputField = webDriver.findElement(By.xpath("//*[@id='search-tabpanel']/div/div/div/label/div/input"));
        whereInputField.sendKeys("Belgrade");
        whereInputField.sendKeys(Keys.ENTER);
        Thread.sleep(1000);

        //selecting dates
        WebElement date = webDriver.findElement(By.xpath("//*[@id='search-tabpanel']/div/div[3]/div[2]/div/div"));
        date.click();
        Thread.sleep(15000);

        WebElement January19th = webDriver.findElement(By.xpath("//*[@id='search-tabpanel']/div/div[3]/div[1]/div/div/div/div/div/div/div/div/div/div[2]/div[2]/div/div[2]/div/table/tbody/tr[4]/td[1]/div/div"));
        January19th.click();
        Thread.sleep(1000);

        //selecting people
        WebElement searchBarWho = webDriver.findElement(By.xpath("//*[@id='search-tabpanel']/div/div[5]/div[2]/div[1]"));
        searchBarWho.click();
        Thread.sleep(1000);

        WebElement adultsPlusSign = webDriver.findElement(By.xpath("//*[@id='stepper-adults']/button[2]/span"));
        adultsPlusSign.click();

        WebElement childrenPlusSign = webDriver.findElement(By.xpath("//*[@id='stepper-children']/button[2]/span"));
        for(int i=0; i<2; i++){ //selecting 2 kids, and 1 adult previously
            childrenPlusSign.click();
            Thread.sleep(1000);
        }

        //search
        String urlBeforeSearch = webDriver.getCurrentUrl();
        Thread.sleep(1000);

        WebElement searchButton = webDriver.findElement(By.xpath("//*[@id='search-tabpanel']/div/div[5]/div[2]/div[3]/button"));
        searchButton.click();
        Thread.sleep(1000);

        String urlAfterSearch = webDriver.getCurrentUrl();
        Thread.sleep(1000);

        assertNotEquals(urlBeforeSearch, urlAfterSearch);
    }

    @AfterAll
    public static void TearDown(){
        if(webDriver!=null){
            webDriver.quit();
        }
    }
}
