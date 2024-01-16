package TestCases;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import PageFile.AboutPage;
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
	public void verifyAboutPage() throws IOException, InterruptedException {
		lp= new LoginPage(driver);
		// enter the user& password and click login buttton
		lp.EnterValidCrendial();
		lp.clickonLoginBtn();


		Am= new AbstractMethods(driver);
		Am.implicitlywaitmethod();

		//Click to menu Button and then click to About button
		In= new InventoryPage(driver);
		In.clickOnMenuButton();
		In.clickOnAboutButton();

		Am.implicitlywaitmethod();
		Am.Wait_Till_Link_Is_Clickable(popUpOkButton);

		// Verify navigated to About Page and "The world relies on your code" text is visibile
		Ap= new AboutPage(driver);
		Ap.verifyNavigatedToAboutPg();

		// Verify Try It Free Function of About Page and "Start Testing text" is visible"

		Ap.verifyAboutPgTryItFunctions();
		System.out.println("Test Case >> Try It Functions of About Page ");

	}
	@Test(priority=1) 
	public void verifyRequestDemoPg() throws IOException {
		lp= new LoginPage(driver);
		// enter the user& password and click login buttton
		lp.EnterValidCrendial();
		lp.clickonLoginBtn();


		Am= new AbstractMethods(driver);
		Am.implicitlywaitmethod();

		//Click to menu Button and then click to About button
		In= new InventoryPage(driver);
		In.clickOnMenuButton();
		In.clickOnAboutButton();

		Am.implicitlywaitmethod();
		Am.Wait_Till_Link_Is_Clickable(popUpOkButton);

		// Verify navigated to About Page and "The world relies on your code" text is visibile
		Ap= new AboutPage(driver);
		Ap.verifyNavigatedToAboutPg();

//		// Verify Request Demo  Funtion of About Page and "Request Demo Text" is visible"
//
//		Ap.verifyAboutPgRequestDemo();;
		System.out.println("Test Case >> requestof About Page ");


	}
	
}
