package PageFile;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogOutPage extends AbstractMethods {
	WebDriver driver;
	By UserName=By.id("user-name");
	By Password=By.id("password");
	By loginBtn=By.id("login-button");
	@FindBy(xpath = "/html/body/div/div/div[2]/div[1]/div/div/form/div[3]/h3")
    private WebElement errorLabel;

	public LogOutPage(WebDriver driverhere) {
		//driver=d;
		super(driverhere);
		this.driver=driverhere;
		PageFactory.initElements(driverhere, this);
	}
}
