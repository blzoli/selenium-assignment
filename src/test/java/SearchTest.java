import org.junit.Test;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.Keys;

import java.util.List;


public class SearchTest extends BaseCsfdTest {

	@Test
	public void detailedSearchFindsForrestGump() {
		driver.get("https://www.csfd.cz/podrobne-vyhledavani/");
		waitForDocumentReady();

		// In the filters, unselect every checkbox under "Typ" besides "film"
		waitVisibility(By.id("frm-filmsForm-type-1"));
		List<WebElement> typeCheckboxes = driver.findElements(
				By.xpath("//h3[normalize-space(.)='Typ:']/parent::*//input[@type='checkbox' and @name='type[]']")
		);
		for (WebElement checkbox : typeCheckboxes) {
			String id = checkbox.getAttribute("id");
			boolean isFilm = "frm-filmsForm-type-1".equals(id);

			if (isFilm && !checkbox.isSelected()) {
				checkbox.click();
			}
			if (!isFilm && checkbox.isSelected()) {
				checkbox.click();
			}
		}

		// In "Žánr obsahující", select "alespoň jeden ze zvolených" and next to it "Komedie"
		WebElement genreMode = waitVisibility(By.id("frm-filmsForm-genre"));
		new Select(genreMode).selectByVisibleText("alespoň jeden ze zvolených:");
		WebElement genreInclude = waitVisibility(By.id("complex-selects-genre-include"));
		new Select(genreInclude).selectByVisibleText("Komedie");

		// In "Herci", enter "Tom Hanks"
		WebElement actorInput = waitVisibility(By.id("frm-filmsForm-actor-selectized"));
		actorInput.click();
		actorInput.clear();
		actorInput.sendKeys("Tom Hanks");
        actorInput.sendKeys(Keys.ENTER); 

		// Check if Forrest Gump is in the results
		By forrestGumpLocator = By.xpath("//h3[contains(normalize-space(.), 'Výsledky vyhledávání')]/following::*[contains(normalize-space(.), 'Forrest Gump')]");
		new WebDriverWait(this.driver, 10)
				.until(ExpectedConditions.presenceOfElementLocated(forrestGumpLocator));
		Assert.assertFalse(
				"expected 'Forrest Gump' to be present in search results",
				driver.findElements(forrestGumpLocator).isEmpty()
		);
	}
}


