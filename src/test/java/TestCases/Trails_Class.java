package TestCases;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import PageFile.AbstractMethods;
import PageFile.InventoryPage;
import PageFile.LoginPage;
import PageFile.ReUseableMethods;

public class Trails_Class {

	WebDriver driver;
	LoginPage lp;
	InventoryPage In;
	AbstractMethods Am;
	ReUseableMethods Rm;

	@FindBy(xpath = "//*[@id=\\\"react-burger-menu-btn\\\"]")WebElement ActualTitle;
	By massage = By.xpath("//*[contains(text(),'Products')]");
	@FindBy(xpath="//h4[text()='Accepted usernames are:']")WebElement AcceptedUserdetails;

	@FindBy(className="cart_list")
	WebElement cartNotification;
	@FindBy(className="shopping_cart_badge")
	WebElement cartIcon;

	@BeforeTest
	public void setUpofBrowser() throws IOException {
		FileInputStream fs = new FileInputStream("E:\\eclipse-workspace_Selenium\\Project-AutomationTesting-2\\src\\test\\java\\DataFile\\Data.Properties");
		Properties prop = new Properties();
		prop.load(fs);

		//initializing the browser's
		String browser = prop.getProperty("browser");
		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver(); 
		}

		//opening the url
		String url = prop.getProperty("url");
		driver.get(url);
		driver.manage().window().maximize();
		}

	@AfterTest
	public void tearDown() {
		driver.quit();

	}

	@Test
	public void VerifyProductPgFooter() throws IOException, InterruptedException {
		lp= new LoginPage(driver);
		// enter the user& password and click login buttton
		lp.EnterValidCrendial();
		lp.clickonLoginBtn();
		//verify landed to Inventory page
		Rm= new ReUseableMethods(driver);
		Rm.urlEquals("https://www.saucedemo.com/inventory.html");
		Am= new AbstractMethods(driver);
		Am.implicitlywaitmethod();

		Am.scroll_Down();

		In.VerifyAtTwitterIconEnableAndNavigatedToTwitterPg();

		//Verify landed to Twitter pg
		Am.implicitlywaitmethod();

//		// Switch to the new window or tab (assuming it's the last one)
//		for (String windowHandle : driver.getWindowHandles()) {
//			driver.switchTo().window(windowHandle);
//		}
//		// Verify that the current URL is the Twitter page
//		String currentUrl = driver.getCurrentUrl();
//		Assert.assertTrue(currentUrl.contains("twitter.com"), "Not navigated to Twitter page");
//
//		// Optionally, you can print the current URL for verification
//		System.out.println("Current URL: " + currentUrl);


	}
	
}
