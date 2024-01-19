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
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import PageFile.AboutPage;
import PageFile.AbstractMethods;
import PageFile.CartPage;
import PageFile.InventoryPage;
import PageFile.LoginPage;
import PageFile.ProductDetailsPage;
import PageFile.ReUseableMethods;

public class Trails2 {

	WebDriver driver;
	LoginPage lp;
	InventoryPage In;
	AbstractMethods Am;
	ReUseableMethods Rm;
	AboutPage Abp;
	CartPage Crtp;
	ProductDetailsPage Pdp;


	@FindBy(xpath="//select[@class='product_sort_container']")
	WebElement select;


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

	@FindBy(id="react-burger-menu-btn")
	WebElement MenuBtn;



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
		//	driver.quit();

	}

	//
	//	public void VerifyTheFilterFuncation_A_to_Z() throws IOException {
	//		//Enter the login details
	//		lp=new LoginPage(driver);
	//		lp.EnterValidCrendial();
	//		lp.clickonLoginBtn();
	//
	//		//Add the products into cart
	//		In= new InventoryPage(driver);
	//		In.changeFilterName_ZtoA();
	//
	//
	//		System.out.println("Test Case 86-->> Changed Filter A to Z");
	//
	//	}
	//	//	
	//	//	// Create an empty list to store product names
	//	//	List<String> productInformation = new ArrayList<>();
	//	//
	//	//	// Find all elements on the page with the class 'inventory_item_name'
	//	//	List<WebElement> productElements = driver.findElements(By.xpath("//*[@id=\"inventory_item_container\"]/div/div/div[2]"));
	//	//
	//	//	// Iterate through each WebElement representing a product name
	//	//	for (WebElement productElement : productElements) {
	//	//		// Get the text of the product name element and add it to the list
	//	//		productInformation.add(productElement.getText());
	//	//	}
	//	//
	//	////	// Return the list of product names
	//	//	return productInformation;

	//	@Test
	//	public void verifyLoginPageAcceptedUserDetails() throws IOException {
	//	
	//			lp= new LoginPage(driver);
	//			
	//			
	//			// Find the "Accepted username" element by class name
	//	        WebElement acceptedUsersElement = driver.findElement(By.className("login_credentials"));
	//
	//	        System.out.println(acceptedUsersElement.getText());
	////	        // Verify that "Accepted usernames are:" is visible
	////	        Assert.assertTrue(acceptedUsersElement.isDisplayed(), "Accepted username message not visible");
	////
	////	        // Get the list of accepted users
	////	        List<WebElement> acceptedUserElements = acceptedUsersElement.findElements(By.tagName("br"));
	////	        List<String> acceptedUserList = new ArrayList<>();
	////
	////	        for (WebElement userElement : acceptedUserElements) {
	////	            acceptedUserList.add(userElement.getText());
	////	        }
	////
	////	        // Print the accepted user list
	////	        System.out.println("Accepted usernames are:");
	////	        for (String acceptedUser : acceptedUserList) {
	////	            System.out.println(acceptedUser);
	////	        }
	//			
	////			
	////			List<WebElement> acceptedUsersElements= driver.findElements(By.className("login_credentials"));
	////			
	////			// Step 4: Verify that "Accepted username are " is visible
	////			List<String> acceptedUserList= new ArrayList<>();
	////			
	////			for(WebElement acceptedUsersElement : acceptedUsersElements) {
	////				String user=acceptedUsersElement.findElement(By.id("login_credentials")).getText();
	////				acceptedUserList.add(user);
	////				
	////			}
	////			System.out.println("===");
	////			for(String acceptedUsers:acceptedUserList) {
	////				System.out.println(acceptedUsers);
	////			}
	//			
	//			//WebElement acceptedUsers= driver.findElement(By.xpath("//h4[text()='Accepted usernames are:']"));
	//		//	Assert.assertTrue(acceptedUsername.isDisplayed(), "Accepted username message not visible");
	//			//  (Need to add this setep into login tescase page)
	//		
	//	}
	@Test(priority=1)

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
		
		// Verify Navigated to About Page & "The world relies on your code" text is visible
		Abp=new AboutPage(driver);
		Abp.verifyNavigatedToAboutPg();
		Am.implicitlywaitmethod();
		
		// Verify Navigated "TRY IT FREE" Page & "start testing in minutes" text is visible
		Abp.verifyAboutPgTryItFunctions();
		
	
		System.out.println("Test Case 8-->> Verify About pg");
	}



}
