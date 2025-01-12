import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HTTPSCompliance {
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
    public void HTTPSComplianceTest() throws InterruptedException{
        webDriver.get(baseUrl);
        Thread.sleep(1000);

        String currentURL = webDriver.getCurrentUrl();
        System.out.println("The current url: " + currentURL);

        boolean isHTTPS = currentURL.startsWith("https://");

        if (isHTTPS) {
            System.out.println("The webpage is HTTPS compliant.");
        } else {
            System.out.println("The webpage is NOT HTTPS compliant.");
        }

        assertTrue(isHTTPS, "The web page is not HTTPS compliant.");

    }

    @Test
    public void HTTPSIfNotDisplayedTest() throws InterruptedException{
        webDriver.get(baseUrl);
        Thread.sleep(1000);

        webDriver.navigate().to("https://www.airbnb.com/rooms/593366110540588381?adults=1&category_tag=Tag%3A8536&children=0&enable_m3_private_room=true&infants=0&pets=0&photo_id=1397672318&search_mode=flex_destinations_search&check_in=2025-04-05&check_out=2025-04-12&source_impression_id=p3_1736559557_P3jOrQhraH436Hj2&previous_page_section_name=1000&federated_search_id=92150bac-53a9-4009-bb4d-3a26e7a8d7eb");
        Thread.sleep(1000);

        String currentURL = webDriver.getCurrentUrl();
        System.out.println("The current url: " + currentURL);

        JavascriptExecutor jsExecutor = (JavascriptExecutor)webDriver;
        String protocolUsed = (String) jsExecutor.executeScript("return window.location.protocol;"); //JS's window.location object contains info about current URL, and protocol property retrieves protocol used for the current webpage.
        System.out.println("Protocol used: " + protocolUsed);

        assertEquals("https:", protocolUsed, "Web page is not using HTTPS protocol.");

        System.out.println("Web page is using HTTPS.");
    }

    @AfterAll
    public static void TearDown(){
        if(webDriver!=null){
            webDriver.quit();
        }
    }
}
