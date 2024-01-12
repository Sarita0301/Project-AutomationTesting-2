package PageFile;

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

	@FindBy(xpath="//a[@id='logout_sidebar_link']")
	WebElement logOut;


	@FindBy(xpath="//button[@id='add-to-cart-sauce-labs-backpack']")
	WebElement Add2Cart1;

	@FindBy(xpath="//button[@id='add-to-cart-sauce-labs-onesie']")
	WebElement Add2Cart2;

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

	public InventoryPage(WebDriver driverhere) {
		//driver=d;
		super(driverhere);
		this.driver=driverhere;
		PageFactory.initElements(driverhere, this);
	}
	public void changeFilter() {

		Select sel=new Select(select);
		sel.selectByIndex(2);
	}
	public void logout() {
		implicitlywaitmethod();
		menuBtn.click();
		logOut.click();
	}
	public void addFirstProductToCart() {
		implicitlywaitmethod();
		Add2Cart1.click();

	}
	public void addSecondProductToCart() {
		implicitlywaitmethod();
		Add2Cart2.click();

	}
	public void CartBtn() {
		cartIcon.click();;
	}

	public boolean CartIconDisplayed() {
		return cartIcon.isDisplayed();
	}
	public void verifyCartNotification() {
		int numberOfProductsInCart = cartNotification.findElements(By.className("cart_item")).size();
		Assert.assertEquals(numberOfProductsInCart, 2, "Unexpected number of products in the cart");

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
}


