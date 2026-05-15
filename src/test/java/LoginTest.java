import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import java.util.Random;

public class LoginTest extends BaseCsfdTest {

    @Test
    public void canLoginWithValidCredentials() {

        login("seleniumTest", "Selenium2026");
        assertLoggedIn();
    }

    // generate random string for username and password, attempt login and assert error message is visible

    @Test
    public void cannotLoginWithInvalidCredentials() {

        Random random = new Random(System.currentTimeMillis());

        String username = "user" + random.nextInt(100000);
        String password = "pass" + random.nextInt(100000);

        login(username, password);
        waitVisibility(By.xpath("//div[contains(text(), 'Přihlášení se nezdařilo. Přezdívka nebo heslo jsou nesprávné. Zadáváš správně velká a malá písmena v heslu?')]"));
    }

}