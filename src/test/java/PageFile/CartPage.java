package PageFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class CartPage extends AbstractMethods{

	WebDriver driver;
	InventoryPage In;

	@FindBy(xpath="//button[@id='checkout']")
	WebElement checkOutBtn;

	@FindBy(xpath="//div[@class='header_secondary_container']")
	WebElement checkOutInformationText;

	@FindBy(name="firstName")
	WebElement firstName;

	@FindBy(name="lastName")
	WebElement lastName;

	@FindBy(name="postalCode")
	WebElement zipCode;

	@FindBy(xpath="//input[@id='continue']")
	WebElement continueBtn;

	@FindBy(xpath="//*[@id=\"checkout_summary_container\"]/div/div[2]")
	WebElement PaymentInformationDetails;

	@FindBy(id="finish")
	WebElement finishBtn;
	
	@FindBy(xpath="//h2[@class='complete-header']")
	WebElement successfullMsg;

	public CartPage(WebDriver driverhere) {
		super(driverhere);
		this.driver=driverhere;
		PageFactory.initElements(driverhere, this);
	}

	public void verifyCartPageCheckOutInformation() {
		//Click checkout Button
		checkOutBtn.click();

		//Verify navigated to checkout page and "CheckOut Information" Text is visible
		Assert.assertEquals(checkOutInformationText.getText(), "Checkout: Your Information");
		System.out.println(checkOutInformationText.getText());
	}

	public void enterCheckoutInformation() throws IOException {
		FileInputStream fis= new FileInputStream("E:\\eclipse-workspace_Selenium\\Project-AutomationTesting-2\\src\\test\\java\\DataFile\\Data.Properties");
		Properties p = new Properties();
		p.load(fis);
		String firstNameField=p.getProperty("firstName");
		String lastNameField=p.getProperty("lastName");
		String zipCodeField=p.getProperty("zipCode");
		firstName.sendKeys(firstNameField);
		lastName.sendKeys(lastNameField);
		zipCode.sendKeys(zipCodeField);
	}

	public void continueBtn() {
		continueBtn.click();
	}

	public List<String> getPaymentInformation(){
		// Create an empty list to store product names
		List<String> paymentInformation = new ArrayList<>();

		// Find all elements on the page with the class 'inventory_item_name'
		List<WebElement> paymentElements = driver.findElements(By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[2]"));

		// Iterate through each WebElement representing a product name
		for (WebElement paymentElement : paymentElements) {
			// Get the text of the product name element and add it to the list
			paymentInformation.add(paymentElement.getText());
		}

		// Return the list of product names
		return paymentInformation;
	}

	public void clickOnFinishBtn() {
		finishBtn.click();
	}

	public void verifyOrderSuccessfullMassage() {
		Assert.assertEquals(successfullMsg.getText(), "Thank you for your order!");
	}
}
