
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;
import java.net.MalformedURLException;
import org.openqa.selenium.support.ui.*;



import org.junit.*;

public class LoginTest {

    private WebDriver driver;
    private WebDriverWait wait;

    private WebElement waitVisibilityAndFindElement(By locator) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return this.driver.findElement(locator);
    }


    @Before
    public void setup() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1920,1080");
        this.driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        this.wait = new WebDriverWait(driver, 10);

    }

    @Test
    public void LoginTest() {

        // accept cookies

        this.driver.get("https://www.csfd.cz/prihlaseni/");

        // id="didomi-notice-agree-button"
        this.waitVisibilityAndFindElement(By.id("didomi-notice-agree-button")).click();



        this.waitVisibilityAndFindElement(By.id("frm-loginForm-nick")).sendKeys("seleniumTest"); 
        this.waitVisibilityAndFindElement(By.id("frm-loginForm-password")).sendKeys("Selenium2026");
        // click login button
        this.waitVisibilityAndFindElement(By.cssSelector("button[name='send']")).click();


        // check if login was successful

        // <div class="profile-img">
        WebElement profileImg = this.waitVisibilityAndFindElement(By.cssSelector("div.profile-img"));
        Assert.assertNotNull(profileImg);
										
    }

    @After
    public void close() {
        if (this.driver != null) {
            this.driver.quit();
        }
    }

};
