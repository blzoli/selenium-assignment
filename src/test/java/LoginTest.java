import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;


public class LoginTest extends BaseCsfdTest {

    @Test
    public void canLoginWithValidCredentials() {

        login("seleniumTest", "Selenium2026");
        assertLoggedIn();
    }

}