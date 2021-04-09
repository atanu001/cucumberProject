package com.app.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.app.factory.DriverFactory;
import com.app.util.CommonUtility;

public class NewApplication extends BasePage {

	public NewApplication(WebDriver driver) {
		super(driver);
	}

	private CommonUtility commonUtil = new CommonUtility(DriverFactory.getDriver());
	private BasePage header = new BasePage(DriverFactory.getDriver());

	@FindBy(id = "applicationName")
	public WebElement applicationName;

	@FindBy(xpath = "//select[@id='platformList']")
	public WebElement platformList;

	@FindBy(xpath = "//select[@id='langList']")
	public WebElement languageList;

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
