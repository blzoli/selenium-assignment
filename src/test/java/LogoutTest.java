import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebElement;



public class LogoutTest extends BaseCsfdTest {


    @Test
    public void canLogout() { 

        login("seleniumTest", "Selenium2026");
        assertLoggedIn();

        // find dropdown <a class="profile initialized"

        WebElement dropdown = waitVisibility(By.cssSelector("a.profile.initialized"));

        // hover
        
        new Actions(driver).moveToElement(dropdown).perform();


        // find button "Odhlásit"

        waitVisibility(By.xpath("//a[contains(text(), 'Odhlásit')]")).click();

        // assert logged out text <span>Můj účet</span>

        waitVisibility(By.xpath("//span[contains(text(), 'Můj účet')]"));

    }

}