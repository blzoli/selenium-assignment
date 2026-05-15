import org.junit.Test;
import org.junit.Assert;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MultiplePageTest extends BaseCsfdTest {

	private void assertTitleContains(String expected) {
        waitForDocumentReady();
		String title = driver.getTitle();
		Assert.assertTrue(
				"Expected title to contain '" + expected + "' but was: " + title,
				title.contains(expected)
		);
	}

	@Test
	public void canNavigateMultiplePagesAndHistoryPersists() {
		String[] urls = {
				"https://www.csfd.cz/novinky/",
				"https://www.csfd.cz/vod/",
				"https://www.csfd.cz/televize/",
				"https://www.csfd.cz/kino/1-praha/",
				"https://www.csfd.cz/zanry/1-akcni/"
		};

		String[] expectedTitleParts = {
				"Novinky",
				"VOD",
				"Televize",
				"Kino",
				"Žánry"
		};

		for (int i = 0; i < urls.length; i++) {
			driver.get(urls[i]);
			acceptCookiesIfPresent();
			assertTitleContains(expectedTitleParts[i]);
		}

		// After navigating to the last page, go back twice and verify titles
		driver.navigate().back();
		assertTitleContains("Kino");

		driver.navigate().back();
		assertTitleContains("Televize");

		// Then go forward and verify again
		driver.navigate().forward();
		assertTitleContains("Kino");
	}
}

