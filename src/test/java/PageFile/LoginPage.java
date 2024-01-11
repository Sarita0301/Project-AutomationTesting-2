package PageFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;
	By UserName=By.id("user-name");
	By Password=By.id("password");
	By loginBtn=By.id("login-button");

	public LoginPage(WebDriver driverhere) {
		//driver=d;
		super();
		this.driver=driverhere;
		PageFactory.initElements(driverhere, this);
	}

	public void EnterValidCrendial() throws IOException {
		FileInputStream fis= new FileInputStream("E:\\eclipse-workspace_Selenium\\Project-AutomationTesting-2\\src\\test\\java\\DataFile\\Data.Properties");
		Properties p = new Properties();
		p.load(fis);
		String ValidUserName=p.getProperty("UserNameField");
		String ValidPass=p.getProperty("PasswordField");
		driver.findElement(UserName).sendKeys(ValidUserName);	
		driver.findElement(Password).sendKeys(ValidPass);	

	}
	public void EnterInvalidCrendial() throws IOException {
		FileInputStream fis= new FileInputStream("E:\\eclipse-workspace_Selenium\\Project-AutomationTesting-2\\src\\test\\java\\DataFile\\Data.Properties");
		Properties p = new Properties();
		p.load(fis);
		String InvalidUserName=p.getProperty("InValidUserNameField");
		String InvalidPass=p.getProperty("InValidPasswordField");
		driver.findElement(UserName).sendKeys(InvalidUserName);	
		driver.findElement(Password).sendKeys(InvalidPass);	

	}
	public void clickonLoginBtn() {
		driver.findElement(loginBtn).click();
	}


}
