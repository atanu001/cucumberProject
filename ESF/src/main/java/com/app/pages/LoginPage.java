package com.app.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.app.factory.DriverFactory;
import com.app.util.CommonUtility;

public class LoginPage {

	private WebDriver driver;
	private CommonUtility commonUtil = new CommonUtility(DriverFactory.getDriver());
	private MyApplication myApplication = new MyApplication(DriverFactory.getDriver());

	@FindBy(id = "inputEmail")
	@CacheLookup
	WebElement emailId;

	@FindBy(id = "inputPassword")
	@CacheLookup
	WebElement password;

	@FindBy(xpath = "//button[text()='Login']")
	@CacheLookup
	WebElement btnLogin;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public void openUrl() {
		DriverFactory.getDriver().get("https://cee-dt-dev.inadev.net/webclient/v2/");
	}

	public String getLoginPageTitle() {
		return driver.getTitle();
	}

	public void doLogin(String email, String Password) {

		String[] testData = { email, Password };
		WebElement[] locator = { emailId, password };
		commonUtil.typeIn(locator, testData);
		commonUtil.onClick(btnLogin);
		commonUtil.waitForElementToVisible(myApplication.appListTable);

	}
}
