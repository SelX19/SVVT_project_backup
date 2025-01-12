import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WhereSearchBar {
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
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(10)); //used for explicit waits for certain events (waiting for web elements to load)
    }

    @Test
    public void WhereInputTest() throws InterruptedException{
        webDriver.get(baseUrl);
        Thread.sleep(2000);

        // checking if stays are selected
        WebElement stays = webDriver.findElement(By.id("search-block-tab-STAYS"));
        String ariaSelected = stays.getAttribute("aria-selected");
        if("false".equals(ariaSelected)){
            stays.click();
            ariaSelected = "true";
        }
        else{
            System.out.println("Stays already selected.");
        }
        assertEquals("true", ariaSelected);

        WebElement whereField = webDriver.findElement(By.xpath("//*[@id='search-tabpanel']/div/div[1]/div"));
        whereField.click();
        Thread.sleep(1000);

        WebElement whereInput = webDriver.findElement(By.xpath("//*[@id='search-tabpanel']/div/div[1]/div/label/div/input"));
        whereInput.sendKeys("Doha");
        Thread.sleep(1000);

        // we are making switch that would go to the appropriate displayed field, based on division number, and if no field was clicked on, enter would be pressed by default
        int numOfDivs = 5;
        boolean elementClicked = false; //initially

        //setting division at div[3] to choose third displayed field(Doha airport) using switch, otherwise, if this line was not defined, program would still operate default option - just press enter
        WebElement DohaAirport = webDriver.findElement(By.xpath("//*[@id='bigsearch-query-location-listbox']/div[3]"));

        // Loop through div elements using a switch statement
        for (int i = 1; i <= numOfDivs; i++) {
            String xpath = "//*[@id='bigsearch-query-location-listbox']/div[" + i + "]";
            WebElement element = webDriver.findElement(By.xpath(xpath));

            switch (i) {
                case 1:
                    if (element.isDisplayed() && element.isEnabled()) {
                        element.click();
                        elementClicked = true;
                        break;
                    }
                case 2:
                    if (element.isDisplayed() && element.isEnabled()) {
                        element.click();
                        elementClicked = true;
                        break;
                    }
                case 3:
                    if (element.isDisplayed() && element.isEnabled()) {
                        element.click();
                        elementClicked = true;
                        break;
                    }
                case 4:
                    if (element.isDisplayed() && element.isEnabled()) {
                        element.click();
                        elementClicked = true;
                        break;
                    }
                case 5: //5 displayed options when 'Doha' typed in
                    if (element.isDisplayed() && element.isEnabled()) {
                        element.click();
                        elementClicked = true;
                        break;
                    }
                default:
                    // Default action: press Enter if no element was clicked
                    whereField.sendKeys(Keys.ENTER);
                    break;
            }

            // Exit the loop if an element was clicked
            if (elementClicked) {
                break;
            }
        }

        //use WebDriverWait's instance to wait until the next field of the search bar(check in) becomes visible:
        WebElement checkInField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='search-tabpanel']/div/div[3]/div[1]/div")));

        assertTrue(checkInField.isDisplayed() && checkInField.isEnabled());

        //when transitioned to check in, the input in where field stays in the form: Input city, Input matching country
        assertEquals("Doha, Qatar", whereInput.getAttribute("value"));

    }

    @Test
    public void WhereRegionTest() throws InterruptedException{
        webDriver.get(baseUrl);

        //stays selected by default, no need to show again how they are clicked on, explained above

        WebElement whereField = webDriver.findElement(By.xpath("//*[@id='search-tabpanel']/div/div[1]/div"));
        whereField.click();
        Thread.sleep(1000);

        WebElement middleEastRegion = webDriver.findElement(By.xpath("//*[@id='locationInspirationsSectionID']/div[2]/div[2]/button/img"));
        middleEastRegion.click();
        Thread.sleep(1000);

        //use WebDriverWait's instance to wait until the next field of the search bar(check in) becomes visible:
        WebElement checkInField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='search-tabpanel']/div/div[3]/div[1]/div")));

        assertTrue(checkInField.isDisplayed() && checkInField.isEnabled());

        WebElement whereInput = webDriver.findElement(By.xpath("//*[@id='search-tabpanel']/div/div[1]/div/label/div/input"));
        assertEquals("Middle East", whereInput.getAttribute("value"));

    } /* it does not work sometimes, since when we click on where it shows the search by region options,
        and other times (when testing) the suggested locations are displayed. Hence, the test fails,
        since we cannot predict which one will be displayed when test is run, but in each case, the principe is the same, just different xpath would be used to locate suggested location field
        Also, no time to test the suggested options*/

    @AfterAll
    public static void TearDown(){
        if(webDriver!=null){
            webDriver.quit();
        }
    }
}
