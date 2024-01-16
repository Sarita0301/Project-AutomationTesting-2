package TestCases;

import java.io.FileInputStream;
import java.io.IOException;
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
import PageFile.CheckOutInformationPage;
import PageFile.InventoryPage;
import PageFile.LoginPage;
import PageFile.ProductDetailsPage;
import PageFile.ReUseableMethods;

public class SauceDemoAutomationTestCases {

	WebDriver driver;
	LoginPage lp;
	InventoryPage In;
	AbstractMethods Am;
	ReUseableMethods Rm;
	AboutPage Ap;
	CheckOutInformationPage cp;
	CartPage Crtp;
	ProductDetailsPage Pdp;
	AboutPage Abp;

	@FindBy(xpath = "//*[@id=\\\"react-burger-menu-btn\\\"]")WebElement ActualTitle;

	By massage = By.xpath("//*[contains(text(),'Products')]");

	@FindBy(xpath="//h4[text()='Accepted usernames are:']")WebElement AcceptedUserdetails;
	By popUpOkButton=By.id("onetrust-accept-btn-handler");

	@FindBy(xpath="//h1[text()='Start testing in minutes']")
	WebElement AboutPg;


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
	@Test(priority=3)
	public void verifyLoginPageAcceptedUserDetails() {
		lp= new LoginPage(driver);

		// Find the "Accepted username" element by class name
		WebElement acceptedUsersElement = driver.findElement(By.className("login_credentials"));

		System.out.println(acceptedUsersElement.getText());

		// Step 4: Verify that "Accepted username are " is visible
		Assert.assertTrue(acceptedUsersElement.isDisplayed(), "Accepted Users not visible");

		System.out.println("TestCase 3-->> Accepted Users visibility");


	}
	@Test(priority=4)
	public void verifyLoginPagePasswordVisiblity() {
		lp= new LoginPage(driver);

		// Find the "Password for all users:" element by class name
		WebElement passwordUsersElement = driver.findElement(By.className("login_password"));

		System.out.println(passwordUsersElement.getText());

		// Step 4: Verify that "Password for all users:" is visible
		Assert.assertTrue(passwordUsersElement.isDisplayed(), "Password for all Users are not visible");

		System.out.println("TestCase 4-->> Password for all Users visibility");


	}
	@Test(priority=5)
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

		System.out.println("Test Case 5-->> Logout User");

	}
	@Test(priority=6)
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
		In.addSecondProductToCart();


		Crtp=new CartPage(driver);
		// Verify the number of products in the cart notification
		Assert.assertTrue(Crtp.CartIconDisplayed(), "Cart icon not visible");

		// Click on the cart icon to open the cart

		Crtp.CartBtn();

		// Verify the number of products in the cart notification on the cart page
		Crtp.verifyCartNotificationAfterAdding2Products();

		System.out.println("Test Case 6-->> Verify add products & notification in cart");
	}
	@Test(priority=7)
	public void removeProduct_InCartPage() throws IOException {
		//Enter the login details
		lp=new LoginPage(driver);
		lp.EnterValidCrendial();
		lp.clickonLoginBtn();

		//Add the products into cart
		In= new InventoryPage(driver);
		In.addFirstProductToCart();
		In.addSecondProductToCart();


		Crtp=new CartPage(driver);
		// Verify the number of products in the cart notification
		Assert.assertTrue(Crtp.CartIconDisplayed(), "Cart icon not visible");

		// Click on the cart icon to open the cart

		Crtp.CartBtn();

		// Verify the number of products in the cart notification on the cart page
		Crtp.verifyCartNotificationAfterAdding2Products();;

		//Click on remove button on 1st Product
		In.clickOnRemoveBtn();

		// Verify the number of products in the cart notification After removing the 1st product on the cart page
		Crtp.verifyCartNotificationAfterRemoving1stProduct();
		System.out.println("Test Case 7-->> Remove 1st in Cart page verify the cart notification");

	}

	@Test(priority=8)
	public void VerifyProductPgFooter_NavigatedTotwitter() throws IOException, InterruptedException {
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
		In= new InventoryPage(driver);
		In.VerifyAtTwitterIconEnableAndNavigatedToTwitterPg();

		//Verify landed to Twitter pg
		Am.implicitlywaitmethod();

		// Switch to the new window or tab (assuming it's the last one)
		for (String windowHandle : driver.getWindowHandles()) {
			driver.switchTo().window(windowHandle);
		}
		// Verify that the current URL is the Twitter page
		Rm.urlEquals("https://twitter.com/saucelabs");

		// Optionally, you can print the current URL for verification
		System.out.println("Current URL: " + driver.getCurrentUrl());
		System.out.println("Test Case 8-->> Verify footer of ProductPg and navigated to Twitter pg");
	}
	@Test(priority=9)
	public void VerifyProductPgFooter_NavigatedToFacebook() throws IOException, InterruptedException {
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
		In= new InventoryPage(driver);
		In.VerifyAtFacebookIconEnableAndNavigatedToFacebookPg();

		//Verify landed to facebook pg
		Am.implicitlywaitmethod();

		// Switch to the new window or tab (assuming it's the last one)
		for (String windowHandle : driver.getWindowHandles()) {
			driver.switchTo().window(windowHandle);
		}
		// Verify that the current URL is the Twitter page
		Rm.urlEquals("https://www.facebook.com/saucelabs");

		// Optionally, you can print the current URL for verification
		System.out.println("Current URL: " + driver.getCurrentUrl());
		System.out.println("Test Case 9-->> Verify footer of ProductPg and navigated to Facebook pg");
	}
	@Test(priority=10)
	public void VerifyProductPgFooter_NavigatedToLinkedin() throws IOException, InterruptedException {
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
		In= new InventoryPage(driver);
		In.VerifyAtLinkdinIconEnableAndNavigatedToLinkedinPg();

		//Verify landed to Linkdin pg
		Am.implicitlywaitmethod();

		// Switch to the new window or tab (assuming it's the last one)
		for (String windowHandle : driver.getWindowHandles()) {
			driver.switchTo().window(windowHandle);
		}
		// Verify that the current URL is the Twitter page
		Assert.assertTrue(driver.getCurrentUrl().contains("www.linkedin.com"), "Not Navigated to Linkedin Page");
		//Rm.urlEquals("https://www.linkedin.com/company/sauce-labs/");

		// Optionally, you can print the current URL for verification
		System.out.println("Current URL: " + driver.getCurrentUrl());
		System.out.println("Test Case 10-->> Verify footer of ProductPg and navigated to Linkdin pg");
	}

	@Test(priority=11)

	public void CheckOutInformationPage() throws IOException {
		//Enter the login details
		lp=new LoginPage(driver);
		lp.EnterValidCrendial();
		lp.clickonLoginBtn();

		//Add the products into cart
		In= new InventoryPage(driver);
		In.addFirstProductToCart();
		In.addSecondProductToCart();

		//Click on cart btn
		Crtp=new CartPage(driver);
		Crtp.CartBtn();

		//Click on checkout Button and verify successfully landed to checkOutInformation Page
		cp= new CheckOutInformationPage(driver);
		cp.verifyCartPageCheckOutInformation();

		//enter the checkout Information details
		cp.enterFirstNameCheckoutInformation();
		cp.enterLastNameCheckoutInformation();
		cp.enterZipCodeCheckoutInformation();


		//click on continue button
		cp.continueBtn();


		//verify the payment details are visible
		System.out.println(cp.getPaymentInformation());

		//click on finish button
		cp.clickOnFinishBtn();

		//verify order Placed successfully massage 
		cp.verifyOrderSuccessfullMassage();
		System.out.println("test Case 11-->> Place Order:with all checkout information");

	}
	@Test(priority=12)
	public void placeOrder_withoutCheckoutInformation() throws IOException {
		//Enter the login details
		lp=new LoginPage(driver);
		lp.EnterValidCrendial();
		lp.clickonLoginBtn();

		//Add the products into cart
		In= new InventoryPage(driver);
		In.addFirstProductToCart();
		In.addSecondProductToCart();

		//Click on cart btn
		Crtp=new CartPage(driver);
		Crtp.CartBtn();

		//Click on checkout Button and verify successfully landed to checkOutInformation Page
		cp= new CheckOutInformationPage(driver);
		cp.verifyCartPageCheckOutInformation();


		//click on continue button
		cp.continueBtn();


		// verify the error massage " FirstName is required" at checkoutInformation
		cp.verifyCheckOutErrorMassage();

		System.out.println("Test Case 12-->> CheckOut Information Error Massage With no details");

	}

	@Test(priority=13)
	public void placeOrder_withFirstNameCheckoutInformation() throws IOException {
		//Enter the login details
		lp=new LoginPage(driver);
		lp.EnterValidCrendial();
		lp.clickonLoginBtn();

		//Add the products into cart
		In= new InventoryPage(driver);
		In.addFirstProductToCart();

		//Click on cart btn
		Crtp=new CartPage(driver);
		Crtp.CartBtn();
		//Click on checkout Button and verify successfully landed to checkOutInformation Page
		cp= new CheckOutInformationPage(driver);
		cp.verifyCartPageCheckOutInformation();

		//enter the checkout Information details
		cp.enterFirstNameCheckoutInformation();



		//click on continue button
		cp.continueBtn();

		// verify the error massage " FirstName is required" at checkoutInformation
		cp.verifyCheckOutErrorMassage();

		System.out.println("Test Case 13-->> CheckOut Information Error Massage With FirstName details");

	}
	@Test(priority=14)
	public void placeOrder_withOutZipCodeCheckoutInformation() throws IOException {
		//Enter the login details
		lp=new LoginPage(driver);
		lp.EnterValidCrendial();
		lp.clickonLoginBtn();

		//Add the products into cart
		In= new InventoryPage(driver);
		In.addFirstProductToCart();

		//Click on cart btn
		Crtp=new CartPage(driver);
		Crtp.CartBtn();
		//Click on checkout Button and verify successfully landed to checkOutInformation Page
		cp= new CheckOutInformationPage(driver);
		cp.verifyCartPageCheckOutInformation();

		//enter the checkout Information details
		cp.enterFirstNameCheckoutInformation();
		cp.enterLastNameCheckoutInformation();



		//click on continue button
		cp.continueBtn();

		// verify the error massage " FirstName is required" at checkoutInformation
		cp.verifyCheckOutErrorMassage();

		System.out.println("Test Case 14-->> CheckOut Information Error Massage WithOut PostalCode");

	}

	@Test(priority=15)
	public void placeOrder_withOutLastNameCheckoutInformation() throws IOException {
		//Enter the login details
		lp=new LoginPage(driver);
		lp.EnterValidCrendial();
		lp.clickonLoginBtn();

		//Add the products into cart
		In= new InventoryPage(driver);
		In.addFirstProductToCart();

		//Click on cart btn
		Crtp=new CartPage(driver);
		Crtp.CartBtn();
		//Click on checkout Button and verify successfully landed to checkOutInformation Page
		cp= new CheckOutInformationPage(driver);
		cp.verifyCartPageCheckOutInformation();

		//enter the checkout Information details
		cp.enterFirstNameCheckoutInformation();

		cp.enterZipCodeCheckoutInformation();



		//click on continue button
		cp.continueBtn();

		// verify the error massage " FirstName is required" at checkoutInformation
		cp.verifyCheckOutErrorMassage();

		System.out.println("Test Case 15-->> CheckOut Information Error Massage WithOut Last Name");

	}

	@Test(priority=16)
	public void productDetailsPage() throws IOException {
		//Enter the login details
		lp=new LoginPage(driver);
		lp.EnterValidCrendial();
		lp.clickonLoginBtn();

		//Add the products into cart
		Pdp=new ProductDetailsPage (driver);

		//View 1st product


		Pdp.viewFirstProduct();

		//product details are visible
		Pdp.getProductInformation();

		//print the product details
		System.out.println(Pdp.getProductInformation());

		//Click on add to cart 
		In= new InventoryPage(driver);
		In.addFirstProductToCart();


		//click on Back to Products page
		Pdp.clickOnBackToProductsBtn();

		//view cart 
		Crtp=new CartPage(driver);
		Crtp.CartBtn();

		//verify the cart notification
		Crtp.verifyCartNotificationAfterRemoving1stProduct();;
		System.out.println("Test Case 16 -->> view product details");

	}

	@Test(priority=17)
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
		Crtp= new CartPage(driver);
		Crtp.CartBtn();

		//verify the cart notification matches with no. of added products
		//Crtp.verifyCartNotificationAfterAdding2Products();

		In.clickOnMenuButton();

		//
		In.clickOnResetAppState();

		driver.navigate().refresh();

		//want to verify the all products are removerd from cart 
		//page is getting stuck at this place
		//Crtp.verifyCartNotificationAtResetMode();


		Crtp.clickOnContinueShoppingButton();

		System.out.println("Test Case 17-->> Reset App State Function");

	}
	@Test(priority=18)
	public void changeFilterName_AtoZ() throws IOException {

		//Enter the login details
		lp=new LoginPage(driver);
		lp.EnterValidCrendial();
		lp.clickonLoginBtn();


		Pdp= new ProductDetailsPage(driver);
		List<String> productListBeforeReset=Pdp.getProductlist();
		System.out.println("======Before Filter====");
		for(String product:productListBeforeReset) {
			System.out.println(product);
		}

		In=new InventoryPage(driver);
		In.changeFilterName_AtoZ();
		List<String> productListAfterReset=Pdp.getProductlist();
		System.out.println("======After Filter====");
		for(String product2:productListAfterReset) {
			System.out.println(product2);
		}
		Assert.assertEquals(productListBeforeReset, productListAfterReset);
		System.out.println("Test Case 18-->> Change Filter By Name A To Z.");

	}
	@Test(priority=19)
	public void changeFilterName_ZtoA() throws IOException {

		//Enter the login details
		lp=new LoginPage(driver);
		lp.EnterValidCrendial();
		lp.clickonLoginBtn();


		Pdp= new ProductDetailsPage(driver);
		List<String> productListBeforeReset=Pdp.getProductlist();
		System.out.println("======Before Filter====");
		for(String product:productListBeforeReset) {
			System.out.println(product);
		}

		In=new InventoryPage(driver);
		In.changeFilterName_ZtoA();
		List<String> productListAfterReset=Pdp.getProductlist();
		System.out.println("======After Filter====");
		for(String product2:productListAfterReset) {
			System.out.println(product2);
		}
		Assert.assertNotEquals(productListBeforeReset, productListAfterReset);
		System.out.println("Test Case 19-->> Change Filter By Name Z To A.");
	}
	@Test(priority=20)
	public void changeFilterPrice_LowtoHigh() throws IOException {

		//Enter the login details
		lp=new LoginPage(driver);
		lp.EnterValidCrendial();
		lp.clickonLoginBtn();


		Pdp= new ProductDetailsPage(driver);
		List<String> productListBeforeReset=Pdp.getProductlist();
		System.out.println("======Before Filter====");
		for(String product:productListBeforeReset) {
			System.out.println(product);
		}

		In=new InventoryPage(driver);
		In.changeFilterPrice_LowtoHigh();
		List<String> productListAfterReset=Pdp.getProductlist();
		System.out.println("======After Filter====");
		for(String product2:productListAfterReset) {
			System.out.println(product2);
		}
		Assert.assertNotEquals(productListBeforeReset, productListAfterReset);
		System.out.println("Test Case 20-->> Change Filter By price low To high.");
	}
	@Test(priority=21)
	public void changeFilterPrice_HightoLow() throws IOException {

		//Enter the login details
		lp=new LoginPage(driver);
		lp.EnterValidCrendial();
		lp.clickonLoginBtn();


		Pdp= new ProductDetailsPage(driver);
		List<String> productListBeforeReset=Pdp.getProductlist();
		System.out.println("======Before Filter====");
		for(String product:productListBeforeReset) {
			System.out.println(product);
		}

		In=new InventoryPage(driver);
		In.changeFilterPrice_HightoLow();
		List<String> productListAfterReset=Pdp.getProductlist();
		System.out.println("======After Filter====");
		for(String product2:productListAfterReset) {
			System.out.println(product2);
		}
		Assert.assertNotEquals(productListBeforeReset, productListAfterReset);
		System.out.println("Test Case 21-->> Change Filter By price high To low.");
	}

	@Test(priority=22)

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

		System.out.println("Test Case 22-->> Verify About pg");
	}
}




