package PageFile;

import static org.testng.Assert.assertEquals;


import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ReUseableMethods {
	WebDriver driver;


	public  ReUseableMethods(WebDriver driverhere) {
		this.driver = driverhere;
		PageFactory.initElements(driverhere, this);
	}
	// Method to verify the title is equal to expected title
	public void titleEquals(String expectedTitle) {
		String actualTitle = driver.getTitle();
		assertEquals(actualTitle, expectedTitle,"Actual Title is equal to the expected Title(" + expectedTitle + ")");
	}

	// Method to verify the url is equal to expected url
	public void urlEquals(String expectedURL) {
		String actualURL = driver.getCurrentUrl();
		assertEquals(actualURL, expectedURL,"Actual url is equal to the expected url(" + expectedURL + ")");

	}
	// Method to verify the innerHtmlText of the single webelement is equal to the expected text
	public void innerTextTrue(WebElement element, String expectedText) {
		String actualText = element.getText();
		assertEquals(actualText, expectedText);
		//System.out.println(actualText);
	}
	// Method to verify the innerHtmlText of the multiple webelement is equal to the expected text
	public void multipleInnerTextEquals(List<WebElement> element, String expectedText) {
		for(WebElement data: element) {
			String actualText = data.getText();
			assertEquals(actualText, expectedText);	
		}

	}

}



