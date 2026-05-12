import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;


public class FormWithLoginTest extends BaseCsfdTest {
    @Test
	public void canAccessMailBox() {

        login("seleniumTest", "Selenium2026");
        assertLoggedIn();

		this.driver.get("https://www.csfd.cz/posta/");
        // find text Pošta - Všechny zprávy 
        waitVisibility(By.xpath("//h1[contains(text(), 'Pošta - Všechny zprávy')]"));
	}

}