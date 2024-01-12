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
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import PageFile.AbstractMethods;
import PageFile.ExcelUtility;
import PageFile.InventoryPage;
import PageFile.LoginPage;
import PageFile.ReUseableMethods;

public class SauceDemoAutomationTestCases {

	WebDriver driver;
	LoginPage lp;
	InventoryPage In;
	AbstractMethods Am;
	ReUseableMethods Rm;

	@FindBy(xpath = "//*[@id=\\\"react-burger-menu-btn\\\"]")WebElement ActualTitle;
	By massage = By.xpath("//*[contains(text(),'Products')]");
	@FindBy(xpath="//h4[text()='Accepted usernames are:']")WebElement AcceptedUserdetails;

	@BeforeMethod
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

	@AfterMethod
	public void tearDown() {
		driver.quit();

	}

	@Test(priority= 1)
	public void VerifyLoginWithValidData() throws Throwable {
		//Login the page with valid Inputs from property file
		lp= new LoginPage(driver);
		lp.EnterValidCrendial();
		lp.clickonLoginBtn();
		System.out.println(driver.getCurrentUrl());
		System.out.println(driver.getTitle());

		//waiting page to load
		Am= new AbstractMethods(driver);

		//Verify landed to Inventory Page
		Rm= new ReUseableMethods(driver);
		Rm.urlEquals("https://www.saucedemo.com/inventory.html");

		Am.implicitlywaitmethod();

		System.out.println("TestCase 1-->> Verify the login with Valid inputs");

	}

	@Test(priority= 2)
	public void VerifyLoginWithInValidData() throws Throwable {
		lp= new LoginPage(driver);
		lp.EnterInvalidCrendial();
		lp.clickonLoginBtn();

		//Verify didn't landed to Inventory Page still on LoginPage
		Rm= new ReUseableMethods(driver);
		Rm.urlEquals("https://www.saucedemo.com/");
		//waiting page to load
		Am= new AbstractMethods(driver);

		Am.implicitlywaitmethod();

		// Alternatively, you can assert the absence of error message
		Assert.assertTrue(lp.isErrorDisplayed(), "Error message displayed when not expected");
		System.out.println(lp.getErrorMessage());

		System.out.println("TestCase 2-->> Verify the login with Valid inputs");

	}
	@Test(priority=-1)
	public void test() {
		lp= new LoginPage(driver);
		// Step 4: Verify that "Accepted username are " is visible
		WebElement acceptedUsername = driver.findElement(By.xpath("//h4[text()='Accepted usernames are:']"));
		Assert.assertTrue(acceptedUsername.isDisplayed(), "Accepted username message not visible");
		//  (Need to add this setep into login tescase page)
	}
	@Test(priority=3)
	public void LogoutUser() throws IOException, InterruptedException {
		lp= new LoginPage(driver);
		// enter the user& password and click login buttton
		lp.EnterValidCrendial();
		lp.clickonLoginBtn();
		//verify landed to Inventory page
		Rm= new ReUseableMethods(driver);
		Rm.urlEquals("https://www.saucedemo.com/inventory.html");
		Am= new AbstractMethods(driver);
		Am.implicitlywaitmethod();

		//Click 'Logout' button
		In= new InventoryPage(driver);
		In.logout();
		Am.implicitlywaitmethod();
		
		//Verify that user is navigated to login page
		Rm= new ReUseableMethods(driver);
		Rm.urlEquals("https://www.saucedemo.com/");
		Am.implicitlywaitmethod();
		
		System.out.println("Test Case 3-->> Logout User");
		
	}
	@Test
	public void Add_To_Cart() throws IOException  {
		lp= new LoginPage(driver);
		// enter the user& password and click login buttton
		lp.EnterValidCrendial();
		lp.clickonLoginBtn();
		//verify landed to Inventory page
		Rm= new ReUseableMethods(driver);
		Rm.urlEquals("https://www.saucedemo.com/inventory.html");
		Am= new AbstractMethods(driver);
		Am.implicitlywaitmethod();

		//Hover over first product and click 'Add to cart'	
		In=new InventoryPage(driver);
		In.addFirstProductToCart();

		//Hover over Second product and click 'Add to cart'	
		In=new InventoryPage(driver);
		In.addSecondProductToCart();

		// Verify the number of products in the cart notification
		Assert.assertTrue(In.CartIconDisplayed(), "Cart icon not visible");

		// Click on the cart icon to open the cart

		In.CartBtn();

		// Verify the number of products in the cart notification on the cart page
		In.verifyCartNotification();
		
		System.out.println("Test Case 4-->> Verify add products & notification in cart");
	}
	
}




