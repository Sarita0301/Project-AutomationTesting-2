package PageFile;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductDetailsPage {
	WebDriver driver;
	@FindBy(xpath="//a[@id=\"item_4_img_link\"]/img")
	WebElement click1stProduct;

	@FindBy(xpath="//button[@id=\"back-to-products\"]")
	WebElement backToProductsBtn;


	public ProductDetailsPage(WebDriver driverhere) {
		//driver=d;
		//super(driverhere);
		this.driver=driverhere;
		PageFactory.initElements(driverhere, this);
	}

	public void viewFirstProduct() {
		click1stProduct.click();
	}

	public void clickOnBackToProductsBtn() {
		backToProductsBtn.click();
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


	public List<String> getProductlist() {
		// Find product elements using XPath or other suitable locators
		List<WebElement> productElements = driver.findElements(By.className("inventory_item"));

		// Create an ArrayList to store product information
		List<String> productList = new ArrayList<>();

		// Iterate through each product element and extract information
		for (WebElement productElement : productElements) {
			// Assuming each product has a name and price element
			String productName = productElement.findElement(By.className("inventory_item_name")).getText();
			String productPrice = productElement.findElement(By.className("inventory_item_price")).getText();

			// Format the information as needed and add it to the ArrayList
			String productInfo = productName + " - " + productPrice;
			productList.add(productInfo);
		}
		return productList;

		//
		//        // Print the product list
		//        System.out.println("Product List:");
		//        for (String product : productList) {
		//            System.out.println(product);
		//        }
	}

}
