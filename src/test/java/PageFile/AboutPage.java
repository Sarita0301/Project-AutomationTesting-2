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
		
	@FindBy(xpath="//*[@id=\"__next\"]/div[2]/div[1]/div/div[1]/div[1]/div/div[3]/p")
	WebElement AboutPg;
	
	@FindBy(xpath="//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedAccentGreen MuiButton-sizeMedium MuiButton-containedSizeMedium MuiButton-disableElevation MuiButton-fullWidth MuiButton-root MuiButton-contained MuiButton-containedAccentGreen MuiButton-sizeMedium MuiButton-containedSizeMedium MuiButton-disableElevation MuiButton-fullWidth css-as8hpy']")
	WebElement tryItFreeBtn;
	
	@FindBy(xpath="//h1[text()='Start testing in minutes']")
	WebElement startTestingText;
	
	@FindBy(xpath="//*[@id=\"__next\"]/div[2]/div[1]/div/div[1]/div[1]/div/div[4]/div[2]/a/button[1]")
	WebElement RequestDemoBtn;
	
	@FindBy(xpath="//h1[@class='MuiTypography-root MuiTypography-label css-iffhsr']")
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
		System.out.println("Is text visible on the About page? " + istextVisible);
	}
	
	public void verifyAboutPgTryItFunctions() {
		Am= new AbstractMethods(driver);
		tryItFreeBtn.click();
		
		Assert.assertTrue(startTestingText.isDisplayed(),"Start testing in minutes text is not visible");
		boolean startTestingTextVisiblity=startTestingText.isDisplayed();
		System.out.println("Is text visible on the singup page of try it for free is visible: "+startTestingTextVisiblity);
		
	}
	
	public void verifyAboutPgRequestDemo() {
		Am= new AbstractMethods(driver);
		RequestDemoBtn.click();
		
		Assert.assertTrue(requestDemoText.isDisplayed(),"Start testing in minutes text is not visible");
		boolean requestDemoTextVisiblity=startTestingText.isDisplayed();
		System.out.println("Is text visible on the singup page of Request Demo is visible: "+requestDemoTextVisiblity);
		
	}
}
