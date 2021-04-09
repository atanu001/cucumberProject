package com.app.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.app.factory.DriverFactory;
import com.app.util.CommonUtility;

public class NewApplication {
	private WebDriver driver;
	private CommonUtility commonUtil = new CommonUtility(DriverFactory.getDriver());
	private Header header = new Header(DriverFactory.getDriver());

	@FindBy(id = "applicationName")
	@CacheLookup
	WebElement applicationName;

	@FindBy(xpath = "//select[@id='platformList']")
	@CacheLookup
	WebElement platformList;

	@FindBy(xpath = "//select[@id='langList']")
	@CacheLookup
	WebElement languageList;

	public NewApplication(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void createApplication(String appName, String platfromName, String languageName, String paramName,
			String paramValue) {

		String[] testDate = { appName, platfromName, languageName };
		WebElement[] locator = { applicationName, platformList, languageList };
		commonUtil.typeIn(locator, testDate);
		commonUtil.onClick(header.btnConfigAddParam);
		commonUtil.scrollDownToBottomPage();
		String[] testDate1 = { paramName, paramValue };
		WebElement[] locator1 = { header.parameterName, header.parameterValue };
		commonUtil.typeIn(locator1, testDate1);
		commonUtil.onClick(header.btnSave);

	}

	public void verifyApplication() {
		commonUtil = new CommonUtility(DriverFactory.getDriver());
		String appName = applicationName.getAttribute("value");

	}
}
