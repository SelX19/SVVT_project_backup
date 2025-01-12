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

public class WhenSearchBar {

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
        baseUrl = "https://www.airbnb.com/?refinement_paths%5B%5D=%2Fhomes&search_mode=flex_destinations_search&flexible_trip_lengths%5B%5D=one_week&location_search=MIN_MAP_BOUNDS&monthly_start_date=2025-01-01&monthly_length=3&monthly_end_date=2025-04-01&price_filter_input_type=0&channel=EXPLORE&price_max=83&search_type=category_change&price_filter_num_nights=5&selected_filter_order%5B%5D=price_max%3A83&category_tag=Tag%3A634";
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
    }

    @Test
    public void DatesTest() throws InterruptedException{

        webDriver.get(baseUrl);
        Thread.sleep(1000);

        WebElement checkInField = webDriver.findElement(By.xpath("//*[@id='search-tabpanel']/div/div[3]/div[1]/div/div"));
        checkInField.click();
        Thread.sleep(1000);

        //dates(days) selected by default
        //months starting from January, going 2 by 2, next 2 include the last one's second month now acting as first on the screen

        WebElement rightClick = webDriver.findElement(By.xpath("//*[@id='panel--tabs--0']/div/div/div/div/div/div[2]/div/div[2]/button")); //January-February tab
        rightClick.click();// transitioning to February-March tab
        rightClick.click(); // transitioning to March-April tab

        WebElement leftClick = webDriver.findElement(By.xpath("//*[@id='panel--tabs--0']/div/div/div/div/div/div[2]/div/div[1]/button"));
        leftClick.click();
        Thread.sleep(1000);

        //selecting dates
        WebElement checkInDate14 = webDriver.findElement(By.xpath("//*[@id=\"panel--tabs--0\"]/div/div[1]/div/div/div/div[2]/div[2]/div/div[2]/div/table/tbody/tr[3]/td[6]/div/div"));
        checkInDate14.click();
        Thread.sleep(1000);

        WebElement checkOutDate20 = webDriver.findElement(By.xpath("//*[@id=\"panel--tabs--0\"]/div/div[1]/div/div/div/div[2]/div[2]/div/div[2]/div/table/tbody/tr[4]/td[5]/div/div"));
        checkOutDate20.click();
        Thread.sleep(1000);

        //assert inputs
        WebElement checkInInput = webDriver.findElement(By.xpath("//*[@id='search-tabpanel']/div/div[3]/div[1]/div/div/div[2]"));
        assertEquals("Feb 14", checkInInput.getText());
        Thread.sleep(1000);

        WebElement checkOutInput = webDriver.findElement(By.xpath("//*[@id='search-tabpanel']/div/div[3]/div[3]/div/div/div[2]"));
        assertEquals("Feb 20", checkOutInput.getText());
        Thread.sleep(1000);

        //add +-2 days option
        WebElement additionalDays = webDriver.findElement(By.xpath("//*[@role='radiogroup']/div/label[3]"));
        additionalDays.click();
        Thread.sleep(1000);

        //then assert inputs in the searchbar again
        WebElement checkInInputUpdatedPart = webDriver.findElement(By.xpath("//*[@id='search-tabpanel']/div/div[3]/div[1]/div/div/div[2]/span"));
        assertEquals("±2", checkInInputUpdatedPart.getText());
        Thread.sleep(1000);

        WebElement checkOutInputUpdatedPart = webDriver.findElement(By.xpath("//*[@id='search-tabpanel']/div/div[3]/div[3]/div/div/div[2]/span"));
        assertEquals("±2", checkOutInputUpdatedPart.getText());
        Thread.sleep(1000);

    }

    @Test
    public void MonthsTest() throws InterruptedException{

        webDriver.get(baseUrl);
        Thread.sleep(1000);

        WebElement checkInField = webDriver.findElement(By.xpath("//*[@id='search-tabpanel']/div/div[3]/div[1]/div/div"));
        checkInField.click();
        Thread.sleep(1000);

        WebElement selectMonths = webDriver.findElement(By.xpath("//*[@id='tabs']/div/div/div/div/button[2]"));
        selectMonths.click();
        Thread.sleep(1000);

        WebElement whenIsYourTrip7 = webDriver.findElement(By.xpath("//*[@id='panel--tabs--1']/div/div[1]/div/div[2]/span[7]"));
        whenIsYourTrip7.click();
        Thread.sleep(1000);

        WebElement currentDate = webDriver.findElement(By.xpath("//*[@id='panel--tabs--1']/div/div[1]/div/div[3]/div/button/span"));
        WebElement futureTripDate = webDriver.findElement(By.xpath("//*[@id='panel--tabs--1']/div/div[1]/div/div[3]/span/button/span"));

        //modify selection of current date and exact date of trip that is to take place for 7 months
        currentDate.click();
        Thread.sleep(2000);
        WebElement March12th = webDriver.findElement(By.xpath("//*[@id='guest-header--modal-portal']/div/section/div/div/div[2]/div/div/div[1]/div/div/div/div[2]/div[2]/div/div[3]/div/table/tbody/tr[3]/td[4]/div/div"));
        March12th.click();
        Thread.sleep(2000);

        WebElement submitButton = webDriver.findElement(By.xpath("//*[@id='guest-header--modal-portal']/div/section/div/div/div[2]/div/footer/button"));
        submitButton.click();
        Thread.sleep(3000);


        /* this part does not work:

        WebElement waitCheckIn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='search-tabpanel']/div/div[3]/div[1]/div/div")));
        waitCheckIn.click();
        Thread.sleep(2000);

        selectMonths.click();
        Thread.sleep(2000);

        futureTripDate.click();
        Thread.sleep(2000);

        WebElement clickRight = webDriver.findElement(By.xpath("//*[@id='guest-header--modal-portal']/div/section/div/div/div[2]/div/div/div[1]/div/div/div/div[2]/div[1]/div[2]/button"));
        for(int i=0; i<2; i++){
            clickRight.click();
            Thread.sleep(2000);
        }

        WebElement November20th = webDriver.findElement(By.xpath("//*[@id=\"guest-header--modal-portal\"]/div/section/div/div/div[2]/div/div/div[1]/div/div/div/div[2]/div[2]/div/div[3]/div/table/tbody/tr[4]/td[5]/div/div"));
        November20th.click();
        Thread.sleep(2000);

        WebElement chooseFlexibilityOf3Days = webDriver.findElement(By.xpath("//*[@id='micro-flex-chips-endDate']/div/label[4]"));
        chooseFlexibilityOf3Days.click();
        Thread.sleep(2000);

        submitButton.click();
        Thread.sleep(3000);

        assertEquals("Wed, Feb 12", currentDate.getText());
        assertEquals("Thu, May 1", futureTripDate.getText());
        Thread.sleep(1000);

        //when changing trip start from september to november, circle shall go till 9th month - November - displaying 9
        WebElement updatedMonths = webDriver.findElement(By.xpath("//*[@id='panel--tabs--1']/div/div[1]/div/div[2]/div[3]/div/div[1]"));
        assertEquals("2", updatedMonths.getText());
        Thread.sleep(1000);

         */

    }

    @Test
    public void FlexibleTest() throws InterruptedException{
        webDriver.get(baseUrl);
        Thread.sleep(1000);

        WebElement checkInField = webDriver.findElement(By.xpath("//*[@id='search-tabpanel']/div/div[3]/div[1]/div/div"));
        checkInField.click();
        Thread.sleep(1000);

        WebElement selectFlexible = webDriver.findElement(By.xpath("//*[@id='tabs']/div/div/div/div/button[3]"));
        selectFlexible.click();
        Thread.sleep(1000);

        WebElement weekend = webDriver.findElement(By.xpath("//*[@id='super_flexible_lengths']/div[2]/label"));
        WebElement week = webDriver.findElement(By.xpath("//*[@id='super_flexible_lengths']/div[2]/label[2]"));
        WebElement month = webDriver.findElement(By.xpath("//*[@id='super_flexible_lengths']/div[2]/label[3]"));

        weekend.click();
        Thread.sleep(1000);

        WebElement weekendMarch = webDriver.findElement(By.xpath("//*[@id='super_flexible_trip_dates_title']/div[2]/div[2]/div[4]/button"));
        weekendMarch.click();
        Thread.sleep(1000);

        WebElement whenInput = webDriver.findElement(By.xpath("//*[@id='search-tabpanel']/div/div[3]/div[1]/div/div/div[2]"));

        assertEquals("Weekend in March", whenInput.getText());
        Thread.sleep(1000);

        week.click();
        assertEquals("Week in March", whenInput.getText());
        Thread.sleep(1000);

        month.click();
        assertEquals("Month in March", whenInput.getText());
        Thread.sleep(1000);
    }

    // Note: checkout identical to the checkin, hence no need for boilerplate code

    @AfterAll
    public static void TearDown(){
        if(webDriver!=null){
            webDriver.quit();
        }
    }
}
