package PageFile;


import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class InventoryPage extends AbstractMethods{
	WebDriver driver;



	@FindBy(xpath="//select[@class='product_sort_container']")
	WebElement select;

	@FindBy(xpath="//button[@id='react-burger-menu-btn']")
	WebElement menuBtn;

	@FindBy(id="about_sidebar_link")
	WebElement aboutBtn;

	@FindBy(xpath="//a[@id='logout_sidebar_link']")
	WebElement logOut;


	@FindBy(xpath="//button[@id='add-to-cart-sauce-labs-backpack']")
	WebElement Add2Cart1;

	@FindBy(xpath="//button[@id='add-to-cart-sauce-labs-onesie']")
	WebElement Add2Cart2;
	
	@FindBy(xpath="//button[@name='remove-sauce-labs-backpack']")
	WebElement removeBtn1;

	@FindBy(className="shopping_cart_badge")
	WebElement cartIcon;

	@FindBy(className="cart_list")
	WebElement cartNotification;

	@FindBy(linkText="Twitter")
	WebElement twitterIcon;

	@FindBy(linkText="Facebook")
	WebElement facebookIcon;

	@FindBy(linkText="LinkedIn")
	WebElement linkdinIcon;
	
	@FindBy(xpath="//*[@id=\"item_4_img_link\"]/img")
	WebElement click1stProduct;
	
	@FindBy(xpath="//*[@id=\"back-to-products\"]")
	WebElement backToProductsBtn;
	
	@FindBy(id="reset_sidebar_link")
	WebElement resetAppStateBtn;

	public InventoryPage(WebDriver driverhere) {
		//driver=d;
		super(driverhere);
		this.driver=driverhere;
		PageFactory.initElements(driverhere, this);
	}
	public void clickOnMenuButton() {
		menuBtn.click();
	}
	public void clickOnAboutButton() {
		aboutBtn.click();
	}
	public void clickOnResetAppState() {
		resetAppStateBtn.click();
	}
	
	public void changeFilterName_AtoZ() {

		Select sel=new Select(select);
		//sel.selectByIndex(0);

		System.out.println("===");
		// Capture the initial state
		List<WebElement> initialElements = driver.findElements(By.xpath("//*[@id=\"inventory_container\"]/div"));

		// Change the sorting order
		sel.selectByIndex(1);
		System.out.println("9999"+initialElements.get(0));

		// Capture the state after changing the sorting order
		List<WebElement> elementsAfterSorting = driver.findElements(By.xpath("/html/body/div/div/div/div[2]/div/div"));
		System.out.println("000"+elementsAfterSorting);
		// Compare the states
		for (int i = 0; i < initialElements.size(); i++) {
			String initialText = initialElements.get(i).getText();
			String afterSortingText = elementsAfterSorting.get(i).getText();

			// Assert that the order has changed as expected
			Assert.assertNotEquals(initialText, afterSortingText, "Order is not changed as expected");
		}
	}
	public void changeFilterName_ZtoA() {

		Select sel=new Select(select);
		sel.selectByIndex(1);
	}
	public void changeFilterPrice_LowtoHigh() {

		Select sel=new Select(select);
		sel.selectByIndex(2);
	}
	public void changeFilterPrice_HightoLow() {

		Select sel=new Select(select);
		sel.selectByIndex(3);
	}
	public void logout() {
		implicitlywaitmethod();
		menuBtn.click();
		logOut.click();
	}
	public void viewFirstProduct() {
		click1stProduct.click();
	}
	public void addFirstProductToCart() {
		implicitlywaitmethod();
		Add2Cart1.click();

	}
	public void addSecondProductToCart() {
		implicitlywaitmethod();
		Add2Cart2.click();

	}
	
	public void clickOnRemoveBtn() {
		implicitlywaitmethod();
		removeBtn1.click();
		
	}
	
	public void clickOnBackToProductsBtn() {
		backToProductsBtn.click();
	}
	public void CartBtn() {
		cartIcon.click();;
	}

	public boolean CartIconDisplayed() {
		return cartIcon.isDisplayed();
	}
	public void verifyCartNotificationAfterAdding2Products() {
		int numberOfProductsInCart = cartNotification.findElements(By.className("cart_item")).size();
		Assert.assertEquals(numberOfProductsInCart, 2, "Unexpected number of products in the cart");

		// Optionally, you can print the number of products for verification
		System.out.println("Number of products in the cart: " + numberOfProductsInCart);

	}
	public void verifyCartNotificationAtResetMode() {
		int numberOfProductsInCart = cartNotification.findElements(By.className("cart_list")).size();
		Assert.assertEquals(numberOfProductsInCart, 0, "Unexpected number of products in the cart");

		// Optionally, you can print the number of products for verification
		System.out.println("Number of products in the cart: " + numberOfProductsInCart);

	}
	public void verifyCartNotificationAfterRemoving1stProduct() {
		int numberOfProductsInCart = cartNotification.findElements(By.className("cart_item")).size();
		Assert.assertEquals(numberOfProductsInCart, 1, "Unexpected number of products in the cart");

		// Optionally, you can print the number of products for verification
		System.out.println("Number of products in the cart: " + numberOfProductsInCart);

	}

	public void VerifyAtTwitterIconEnableAndNavigatedToTwitterPg() {
		twitterIcon.click();
		Actions actions = new Actions(driver);
		actions.moveToElement(twitterIcon).click().perform();
	}

	public void VerifyAtFacebookIconEnableAndNavigatedToFacebookPg() {
		facebookIcon.click();
		Actions actions = new Actions(driver);
		actions.moveToElement(facebookIcon).click().perform();
	}
	public void VerifyAtLinkdinIconEnableAndNavigatedToLinkedinPg() {
		linkdinIcon.click();
		Actions actions = new Actions(driver);
		actions.moveToElement(linkdinIcon).click().perform();
	}
	
	public List<String> getProductInformation(){
		// Create an empty list to store product names
		List<String> productInformation = new ArrayList<>();

		// Find all elements on the page with the class 'inventory_item_name'
		List<WebElement> productElements = driver.findElements(By.xpath("//*[@id=\"inventory_item_container\"]/div/div/div[2]"));

		// Iterate through each WebElement representing a product name
		for (WebElement productElement : productElements) {
			// Get the text of the product name element and add it to the list
			productInformation.add(productElement.getText());
		}

		// Return the list of product names
		return productInformation;
	}
	
	
}


