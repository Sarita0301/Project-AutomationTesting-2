package PageFile;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class AboutPage extends AbstractMethods {
	WebDriver driver;
	AbstractMethods Am;

	@FindBy(xpath="//div[@class='your-class-name']/p")
	WebElement AboutPg;

	By tryItFreeBtn =By.xpath("//div[@class='your-class-name']/div[1]/a/button");
//	
//	WebElement tryItFreeBtn;

	@FindBy(xpath="//h1[text()='Start testing in minutes']")
	WebElement startTestingText;

	@FindBy(xpath="//div[@class='your-class-name']/div[2]/a/button[1]")
	WebElement RequestDemoBtn;

	@FindBy(xpath="//div[@id='form_3124']/div/div/div/div[1]/div/div[1]/span/h1")
	WebElement requestDemoText;

	public AboutPage(WebDriver driverhere) {
		//driver=d;
		super(driverhere);
		this.driver=driverhere;
		PageFactory.initElements(driverhere, this);
	}

	public void verifyNavigatedToAboutPg() {
		Assert.assertTrue(AboutPg.isDisplayed(), "Didnt Landed to About page");
		boolean istextVisible=AboutPg.isDisplayed();
		// Optionally, you can print the current URL for verification
		System.out.println("Navigated to About Page" + istextVisible);
		System.out.println("Text of About Page:"+AboutPg.getText());
	}

	public void verifyAboutPgTryItFunctions() {
		Am= new AbstractMethods(driver);
		Am.Wait_Till_Link_Is_Clickable(tryItFreeBtn);
		

		Assert.assertTrue(startTestingText.isDisplayed(),"Start testing in minutes text is not visible");
		boolean startTestingTextVisiblity=startTestingText.isDisplayed();
		System.out.println("Navigated to TRY IT FOR FREE Page"+startTestingTextVisiblity);

	}

	public void verifyAboutPgRequestDemo() {
		Am= new AbstractMethods(driver);
		RequestDemoBtn.click();

		Assert.assertTrue(requestDemoText.isDisplayed(),"Start testing in minutes text is not visible");
		boolean requestDemoTextVisiblity=startTestingText.isDisplayed();
		System.out.println("Is text visible on the singup page of Request Demo is visible: "+requestDemoTextVisiblity);

	}
}
