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
import static org.junit.jupiter.api.Assertions.assertFalse;

public class InputValidation {
    private static WebDriver webDriver;
    private static String baseUrl;

    @BeforeAll
    public static void SetUp(){
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--start-maximized");

        webDriver = new ChromeDriver(options);
        baseUrl = "https://www.airbnb.com/";
    }

    @Test
    public void noPhoneNumberEnteredTest() throws InterruptedException {
        webDriver.get(baseUrl);
        Thread.sleep(30000);

        WebElement profileMenu = webDriver.findElement(By.xpath("//*[@id=\"react-application\"]/div/div/div[1]/div/div[3]/div[3]/div/div/div/header/div/div[3]/nav/div[2]/div/button"));
        profileMenu.click();
        Thread.sleep(2000);

        WebElement signUpButton = webDriver.findElement(By.xpath("//*[@id=\"simple-header-profile-menu\"]/div/a[2]"));
        signUpButton.click();
        Thread.sleep(2000);

        WebElement phoneInput = webDriver.findElement(By.xpath("//*[@id=\"phoneInputphone-login\"]"));
        phoneInput.click();
        Thread.sleep(1000);

        phoneInput.sendKeys(Keys.ENTER);
        Thread.sleep(1000);

        WebElement messageDisplayed = webDriver.findElement(By.xpath("//*[@id=\"phone-number-error-phone-login\"]"));
        assertEquals("Phone number is required.", messageDisplayed.getText());
        Thread.sleep(1000);
    }

    @Test
    public void invalidPhoneNumberEnteredTest() throws InterruptedException{
        webDriver.get(baseUrl);
        Thread.sleep(30000);

        WebElement profileMenu = webDriver.findElement(By.xpath("//*[@id=\"react-application\"]/div/div/div[1]/div/div[3]/div[3]/div/div/div/header/div/div[3]/nav/div[2]/div/button"));
        profileMenu.click();
        Thread.sleep(2000);

        WebElement signUpButton = webDriver.findElement(By.xpath("//*[@id=\"simple-header-profile-menu\"]/div/a[2]"));
        signUpButton.click();
        Thread.sleep(2000);

        WebElement phoneInput = webDriver.findElement(By.xpath("//*[@id=\"phoneInputphone-login\"]"));
        phoneInput.click();
        Thread.sleep(1000);

        phoneInput.sendKeys("61");
        Thread.sleep(1000);

        phoneInput.sendKeys(Keys.ENTER);
        Thread.sleep(1000);

        WebElement messageDisplayed = webDriver.findElement(By.xpath("//*[@id=\"phone-number-error-phone-login\"]"));
        assertEquals("Phone number is too short or contains invalid characters.", messageDisplayed.getText());
        Thread.sleep(1000);
    }

    @Test
    public void SignUpFormValidationTest() throws InterruptedException{
        webDriver.get(baseUrl);
        Thread.sleep(16000);

        WebElement profileButton = webDriver.findElement(By.xpath("//*[@id=\"react-application\"]/div/div/div[1]/div/div[3]/div[3]/div/div/div/header/div/div[3]/nav/div[2]/div/button"));
        profileButton.click();
        Thread.sleep(2000);

        //open sign up form
        WebElement signUpButton = webDriver.findElement(By.xpath("//*[@id=\"simple-header-profile-menu\"]/div/a[1]"));
        signUpButton.click();
        Thread.sleep(10000);

        WebElement continueWithEmailButton = webDriver.findElement(By.xpath("//*[@data-testid='social-auth-button-email']"));
        continueWithEmailButton.click();
        Thread.sleep(10000);

        WebElement emailInput = webDriver.findElement(By.xpath("//*[@id=\"email-login-email\"]"));
        emailInput.click();
        emailInput.sendKeys("selyxs07@gmail.com");
        Thread.sleep(1000);
        emailInput.sendKeys(Keys.ENTER);
        Thread.sleep(10000);

        WebElement lastName = webDriver.findElement(By.name("user[last_name]"));
        lastName.click();
        Thread.sleep(1000);
        lastName.sendKeys("Djozic");
        Thread.sleep(3000);

        WebElement dateOfBirth = webDriver.findElement(By.xpath("//*[@id=\"email-signup-date\"]"));
        dateOfBirth.click();
        Thread.sleep(1000);
        dateOfBirth.sendKeys("19");
        Thread.sleep(3000);
        dateOfBirth.sendKeys("01");
        Thread.sleep(3000);
        dateOfBirth.sendKeys("2002");
        Thread.sleep(3000);

        WebElement password = webDriver.findElement(By.name("user[password]"));
        password.click();
        Thread.sleep(2000);
        password.sendKeys("abc");
        Thread.sleep(3000);

        assertFalse(password.getText().length()>8);

        WebElement checkBox = webDriver.findElement(By.xpath("//*[@id=\"email-signupuser_profile_info[receive_promotional_email]\"]"));
        checkBox.click();
        Thread.sleep(2000);

        WebElement agreeAndContinueButton = webDriver.findElement(By.xpath("/html/body/div[10]/div/section/div/div/div[2]/div/div[2]/div/section/div/form/div[7]/button"));
        agreeAndContinueButton.click();
        Thread.sleep(10000);

        WebElement firstNameIsRequired = webDriver.findElement(By.xpath("//*[@id=\"email-signup-email__error\"]"));
        assertEquals("First name is required.", firstNameIsRequired.getText());

    }

    @AfterAll
    public static void TearDown(){
        if(webDriver!=null){
            webDriver.quit();
        }
    }
}
