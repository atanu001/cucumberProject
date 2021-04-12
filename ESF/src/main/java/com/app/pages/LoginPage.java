package com.app.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.app.factory.DriverFactory;
import com.app.util.CommonUtility;
import com.app.util.Log;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);

	}

	private CommonUtility commonUtil = new CommonUtility(DriverFactory.getDriver());
	private MyApplication myApplication = new MyApplication(DriverFactory.getDriver());

	@FindBy(id = "inputEmail")
	WebElement emailId;

	@FindBy(id = "inputPassword")
	WebElement password;

	@FindBy(xpath = "//button[text()='Login']")
	WebElement btnLogin;

	public String getLoginPageTitle() {
		return driver.getTitle();
	}

	public void doLogin(String email, String Password) {

		String[] testData = { email, Password };
		WebElement[] locator = { emailId, password };
		commonUtil.typeIn(locator, testData);
		commonUtil.onClick(btnLogin);
		Log.info("click on login button");
		commonUtil.waitForElementToVisible(myApplication.appListTable);

	}
}
