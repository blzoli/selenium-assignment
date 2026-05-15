import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CookiePopupTest extends BaseCsfdTest {

    private void clearCookieConsentState() {
        driver.manage().deleteAllCookies();

        ((JavascriptExecutor) driver).executeScript(
                "window.localStorage.clear(); window.sessionStorage.clear();"
        );
    }

    @Test
    // delete accepted cookies and assert that cookie popup is visible again
    public void cookiePopupReappearsAfterClearingCookies() {
        clearCookieConsentState();

        // reload homepage so the consent script runs again
        driver.get("https://www.csfd.cz/");

        // assert cookie popup is visible again
        waitVisibility(By.id("didomi-notice-agree-button"));
    }

    @Test

    // accept cookies and assert that cookie popup is not visible anymore

    public void canAcceptCookies() {

        clearCookieConsentState();
        driver.get("https://www.csfd.cz/");

        // ensure the popup is present, then accept
        new WebDriverWait(this.driver, 10)
            .until(ExpectedConditions.elementToBeClickable(By.id("didomi-notice-agree-button")))
            .click();

        // assert cookie popup is not visible anymore
        new WebDriverWait(this.driver, 10)
            .until(ExpectedConditions.invisibilityOfElementLocated(By.id("didomi-notice-agree-button")));
    }
    

}