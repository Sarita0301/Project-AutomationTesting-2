package PageFile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class InventoryPage extends AbstractMethods{
	WebDriver driver;
	AbstractMethods AbstrctM;


	@FindBy(xpath="//select[@class='product_sort_container']")
	WebElement select;

	@FindBy(xpath="//button[@id='react-burger-menu-btn']")
	WebElement menuBtn;

	@FindBy(xpath="//a[@id='logout_sidebar_link']")
	WebElement logOut;

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
		menuBtn.click();
		AbstrctM.implicitlywaitmethod();
		logOut.click();
	}
}


