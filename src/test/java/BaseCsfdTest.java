import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

public abstract class BaseCsfdTest {

	protected WebDriver driver;
	protected WebDriverWait wait;

	protected WebElement waitVisibility(By locator) {
		return this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	protected void acceptCookiesIfPresent() {
		try {
			new WebDriverWait(this.driver, 3)
					.until(ExpectedConditions.elementToBeClickable(By.id("didomi-notice-agree-button")))
					.click();
		} catch (TimeoutException ignored) {
			// Cookie dialog not present
		}
	}

	protected void login(String username, String password) {
		this.driver.get("https://www.csfd.cz/prihlaseni/");
		acceptCookiesIfPresent();

		waitVisibility(By.id("frm-loginForm-nick")).sendKeys(username);
		waitVisibility(By.id("frm-loginForm-password")).sendKeys(password);
		waitVisibility(By.cssSelector("button[name='send']")).click();
	}

	protected void assertLoggedIn() {
		waitVisibility(By.cssSelector("div.profile-img"));
	}

	@Before
	public void setup() throws MalformedURLException {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--window-size=1920,1080");

		this.driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
		this.wait = new WebDriverWait(driver, 10);

		// Pre-load homepage to handle cookie consent once per test
		this.driver.get("https://www.csfd.cz/");
		acceptCookiesIfPresent();
	}

	@After
	public void close() {
		if (this.driver != null) {
			this.driver.quit();
		}
	}
}
