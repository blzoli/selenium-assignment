import org.junit.Test;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By;

import java.util.List;
import org.openqa.selenium.WebElement;

public class SeriesPageTest extends BaseCsfdTest {


    @Test
    public void seriesPageLoadsCorrectly() {
        this.driver.get("https://www.csfd.cz/film/1417211-soso-no-frieren/prehled/"); // frieren is a banger

        // head and body load correctly
        Assert.assertNotNull(driver.findElement(By.tagName("head")));
        Assert.assertNotNull(driver.findElement(By.tagName("body")));

        // get page title

        Assert.assertEquals("Sósó no Frieren (2023) | ČSFD.cz", driver.getTitle());

        // body has an h1 with the title
        List<WebElement> h1Elements = driver.findElements(By.cssSelector("body h1"));
        Assert.assertFalse("Expected a <h1> in <body>", h1Elements.isEmpty());

        String h1Text = h1Elements.get(0).getText().trim();
        Assert.assertEquals("Sósó no Frieren", h1Text);

        // body contains creators div
        Assert.assertFalse(
                "Expected a creators div with id='creators'",
                driver.findElements(By.id("creators")).isEmpty()
        );

        // creators include Režie, Předloha and Scénář
        Assert.assertFalse(
                "Expected 'Režie' section inside #creators",
                driver.findElements(By.xpath("//div[@id='creators']//div[contains(normalize-space(.), 'Režie')]"))
                        .isEmpty()
        );
        Assert.assertFalse(
                "Expected 'Předloha' section inside #creators",
                driver.findElements(By.xpath("//div[@id='creators']//div[contains(normalize-space(.), 'Předloha')]"))
                        .isEmpty()
        );
        Assert.assertFalse(
                "Expected 'Scénář' section inside #creators",
                driver.findElements(By.xpath("//div[@id='creators']//div[contains(normalize-space(.), 'Scénář')]"))
                        .isEmpty()
        );

    }
    

}