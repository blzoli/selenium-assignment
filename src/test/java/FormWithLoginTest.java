import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;


public class FormWithLoginTest extends BaseCsfdTest {
    @Test
	public void canSearchMailBox() {

        login("seleniumTest", "Selenium2026");
        assertLoggedIn();

		this.driver.get("https://www.csfd.cz/posta/");
        // find text Pošta - Všechny zprávy 
        waitVisibility(By.xpath("//h1[contains(text(), 'Pošta - Všechny zprávy')]"));

        WebElement searchInput = waitVisibility(By.id("frm-fulltextSearchForm-q")).click();

        searchInput.sendKeys("csfd");
        searchInput.sendKeys(Keys.ENTER);

        // look for match <span class="match">ČSFD</span>

        waitVisibility(By.xpath("//span[contains(@class, 'match') and contains(text(), 'ČSFD')]"));

	}

}