package PageFile;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

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

	@FindBy(linkText="Twitter")
	WebElement twitterIcon;

	@FindBy(linkText="Facebook")
	WebElement facebookIcon;

	@FindBy(linkText="LinkedIn")
	WebElement linkdinIcon;



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
		sel.selectByIndex(0);

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


