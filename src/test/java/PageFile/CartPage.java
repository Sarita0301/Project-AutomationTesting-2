package PageFile;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class CartPage extends AbstractMethods{

	WebDriver driver;
	InventoryPage In;


	@FindBy(className="shopping_cart_badge")
	WebElement cartIcon;

	@FindBy(className="cart_list")
	WebElement cartNotification;

	@FindBy(xpath="//*[@id=\"continue-shopping\"]")
	WebElement continueShoppingBtn;

	public CartPage(WebDriver driverhere) {
		super(driverhere);
		this.driver=driverhere;
		PageFactory.initElements(driverhere, this);
	}

	public void CartBtn() {
		cartIcon.click();
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
		int numberOfProductsInCart = cartNotification.findElements(By.className("cart_item")).size();
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
	public void clickOnContinueShoppingButton() {
		continueShoppingBtn.click();
	}

}
