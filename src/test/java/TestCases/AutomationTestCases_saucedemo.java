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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import PageFile.AbstractMethods;
import PageFile.ExcelUtility;
import PageFile.InventoryPage;
import PageFile.LoginPage;
import PageFile.ReUseableMethods;

public class AutomationTestCases_saucedemo {

	WebDriver driver;
	LoginPage lp;
	InventoryPage Inpg;
	ExcelUtility xldata;
	AbstractMethods Am;
	ReUseableMethods Rm;

	@FindBy(xpath = "//*[@id=\\\"react-burger-menu-btn\\\"]")WebElement ActualTitle;

	By massage = By.xpath("//*[contains(text(),'Products')]");



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
		System.out.println(url);
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
		Am.implicitlywaitmethod();

		//Verify landed to Inventory Page
		Rm= new ReUseableMethods(driver);
		Rm.urlEquals("https://www.saucedemo.com/inventory.html");

		Am.implicitlywaitmethod();

	}

	@Test(priority= 2)
	public void VerifyLoginWithInValidData() throws Throwable {
		lp= new LoginPage(driver);
		lp.EnterInvalidCrendial();
		lp.clickonLoginBtn();
		//waiting page to load
		Am= new AbstractMethods(driver);
		Am.implicitlywaitmethod();

		//Verify landed to Inventory Page
		Rm= new ReUseableMethods(driver);
		Rm.urlEquals("https://www.saucedemo.com/");


	}



}
