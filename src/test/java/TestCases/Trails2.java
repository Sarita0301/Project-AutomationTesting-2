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

import PageFile.AboutPage;
import PageFile.AbstractMethods;
import PageFile.CartPage;
import PageFile.InventoryPage;
import PageFile.LoginPage;
import PageFile.ReUseableMethods;

public class Trails2 {

	WebDriver driver;
	LoginPage lp;
	InventoryPage In;
	AbstractMethods Am;
	ReUseableMethods Rm;
	AboutPage Ap;

	@FindBy(xpath = "//*[@id=\\\"react-burger-menu-btn\\\"]")WebElement ActualTitle;
	By massage = By.xpath("//*[contains(text(),'Products')]");
	@FindBy(xpath="//h4[text()='Accepted usernames are:']")WebElement AcceptedUserdetails;

	@FindBy(className="cart_list")
	WebElement cartNotification;
	@FindBy(className="shopping_cart_badge")
	WebElement cartIcon;

	By popUpOkButton=By.id("onetrust-accept-btn-handler");

	By popUpOkButton2=By.id("onetrust-accept-btn-handler");

	@FindBy(xpath="//*[@id=\"__next\"]/div[2]/div[1]/div/div[1]/div[1]/div/div[3]/p")
	WebElement AboutPg;

	@FindBy(xpath="//*[@id=\"__next\"]/div[2]/div[1]/div/div[1]/div[1]/div/div[4]/div[1]/a/button")
	WebElement tryItFreeBtn;

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

	@Test
	public void restAppStateFunction() throws IOException {
		//Enter the login details
		lp=new LoginPage(driver);
		lp.EnterValidCrendial();
		lp.clickonLoginBtn();

		//Add the products into cart
		In= new InventoryPage(driver);
		In.addFirstProductToCart();
		In.addSecondProductToCart();
		
		//view Cart
		In.CartBtn();
		
		//verify the cart notification matches with no. of added products
		In.verifyCartNotificationAfterAdding2Products();
		
		In.clickOnMenuButton();
		
		//
		In.clickOnResetAppState();
		
		driver.navigate().refresh();
		
		//
		Am=new AbstractMethods(driver);
		Am.implicitlywaitmethod();
		In.verifyCartNotificationAtResetMode();	
		System.out.println("Test Case -->> Remove 1st in Cart page verify the cart notification");

	}



}
