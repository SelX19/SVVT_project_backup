import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SignUp {
    private static WebDriver webDriver;
    private static String baseUrl;

    @BeforeAll
    public static void SetUp(){
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--start-maximized");

        webDriver = new ChromeDriver(options);
        baseUrl = "https://www.airbnb.com";
    }


    @Test
    public void SignUpWithPhoneNumberTest() throws InterruptedException{
        webDriver.get(baseUrl);
        Thread.sleep(16000);

        WebElement profileButton = webDriver.findElement(By.xpath("//*[@id=\"react-application\"]/div/div/div[1]/div/div[3]/div[3]/div/div/div/header/div/div[3]/nav/div[2]/div/button"));
        profileButton.click();
        Thread.sleep(2000);

        //open sign up form
        WebElement signUpButton = webDriver.findElement(By.xpath("//*[@id=\"simple-header-profile-menu\"]/div/a[1]"));
        signUpButton.click();
        Thread.sleep(10000);

        WebElement phoneInput = webDriver.findElement(By.id("phoneInputphone-login"));
        phoneInput.click();
        Thread.sleep(1000);
        phoneInput.sendKeys("603187049");
        Thread.sleep(1000);

        WebElement continueButton = webDriver.findElement(By.xpath("/html/body/div[10]/div/section/div/div/div[2]/div/div[2]/div/form/div/div[4]/button"));
        continueButton.click();
        Thread.sleep(60000); //time to fill-in the verification code, and input fields

        WebElement firstName = webDriver.findElement(By.name("user[first_name]"));
        firstName.click();
        Thread.sleep(1000);
        firstName.sendKeys("Selma");
        Thread.sleep(1000);

        WebElement lastName = webDriver.findElement(By.name("user[last_name]"));
        lastName.click();
        Thread.sleep(1000);
        lastName.sendKeys("Djozic");
        Thread.sleep(1000);

        WebElement dateOfBirth = webDriver.findElement(By.xpath("//*[@id=\"email-signup-date\"]"));
        dateOfBirth.click();
        Thread.sleep(1000);
        dateOfBirth.sendKeys("19");
        Thread.sleep(1000);
        dateOfBirth.sendKeys("01");
        Thread.sleep(1000);
        dateOfBirth.sendKeys("2002");
        Thread.sleep(1000);

        WebElement email = webDriver.findElement(By.xpath("//*[@inputmode='email']"));
        email.click();
        Thread.sleep(1000);
        email.sendKeys("email.edu.ba");
        Thread.sleep(1000);

        WebElement password = webDriver.findElement(By.name("user[password]"));
        password.click();
        Thread.sleep(1000);
        password.sendKeys("2345IKLM*urs79");
        Thread.sleep(1000);

        WebElement checkBox = webDriver.findElement(By.xpath("//*[@id=\"email-signupuser_profile_info[receive_promotional_email]\"]"));
        checkBox.click();
        Thread.sleep(1000);

        WebElement agreeAndContinueButton = webDriver.findElement(By.xpath("/html/body/div[10]/div/section/div/div/div[2]/div/div[2]/div/section/div/form/div[7]/button"));
        agreeAndContinueButton.click();
        Thread.sleep(3000);

    }

    @Test
    public void SignUpWithEmailTest() throws InterruptedException{
        webDriver.get(baseUrl);
        Thread.sleep(16000);

        WebElement profileButton = webDriver.findElement(By.xpath("//*[@id=\"react-application\"]/div/div/div[1]/div/div[3]/div[3]/div/div/div/header/div/div[3]/nav/div[2]/div/button"));
        profileButton.click();
        Thread.sleep(2000);

        //open sign up form
        WebElement signUpButton = webDriver.findElement(By.xpath("//*[@id=\"simple-header-profile-menu\"]/div/a[1]"));
        signUpButton.click();
        Thread.sleep(5000);

        WebElement continueWithEmailButton = webDriver.findElement(By.xpath("//*[@data-testid='social-auth-button-email']"));
        continueWithEmailButton.click();
        Thread.sleep(5000);

        WebElement emailInput = webDriver.findElement(By.xpath("//*[@id=\"email-login-email\"]"));
        emailInput.click();
        emailInput.sendKeys("selyxs07@gmail.com");
        Thread.sleep(1000);
        emailInput.sendKeys(Keys.ENTER);
        Thread.sleep(10000);

        WebElement firstName = webDriver.findElement(By.name("user[first_name]"));
        firstName.click();
        Thread.sleep(1000);
        firstName.sendKeys("Selma");
        Thread.sleep(3000);

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
        password.sendKeys("2345IKLM*urs79");
        Thread.sleep(3000);

        WebElement checkBox = webDriver.findElement(By.xpath("//*[@id=\"email-signupuser_profile_info[receive_promotional_email]\"]"));
        checkBox.click();
        Thread.sleep(2000);

        WebElement agreeAndContinueButton = webDriver.findElement(By.xpath("/html/body/div[10]/div/section/div/div/div[2]/div/div[2]/div/section/div/form/div[7]/button"));
        agreeAndContinueButton.click();
        Thread.sleep(3000);
    }

    @AfterAll
    public static void TearDown(){
        if(webDriver!=null){
            webDriver.quit();
        }
    }


}





