package com.app.pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.app.factory.DriverFactory;
import com.app.util.CommonUtility;
import com.app.util.Log;

public class NewApplication extends BasePage {

	public NewApplication(WebDriver driver) {
		super(driver);
	}

	private CommonUtility commonUtil;
	private MyApplication myApplication;

	@FindBy(xpath = "//h2[text()='New Application']")
	private WebElement labelHeaderNewApplication;

	@FindBy(id = "applicationName")
	private WebElement txtApplicationName;

	@FindBy(xpath = "//select[@id='platformList']")
	private WebElement drpdwnPlatformList;

	@FindBy(xpath = "//select[@id='langList']")
	private WebElement drpdwnLanguageList;

	private static String appName = null;
	private static String platformName = null;
	private static String languageName = null;
	private static String paramName = null;
	private static String paramValue = null;
	private static String modifiedAppName = null;

	/**
	 * This method is used to create an Application with Data from Excel Sheet
	 * 
	 * @param sheetName
	 * @param rowNo
	 */
	public void createApplication(String sheetName, String rowNo) {
		commonUtil = new CommonUtility(DriverFactory.getDriver());
		appName = ec.getCellData("Application_Details", "Application Name", 0);
		platformName = ec.getCellData("Application_Details", "Platform", 0);
		languageName = ec.getCellData("Application_Details", "Languages", 0);
		paramName = ec.getCellData("Application_Details", "Parameter Name", 0);
		paramValue = ec.getCellData("Application_Details", "Parameter Value", 0);
		int randomNum = commonUtil.generateRandomNumber();
		modifiedAppName = appName + "_" + randomNum;
		String[] testData1 = { modifiedAppName, platformName, languageName };
		WebElement[] locator1 = { txtApplicationName, drpdwnPlatformList, drpdwnLanguageList };
		commonUtil.typeIn(locator1, testData1);
		commonUtil.onClick(btnConfigAddParam);
		commonUtil.scrollDownToBottomPage();
		commonUtil.waitForElementToVisible(txtParameterName);
		String[] testData2 = { paramName, paramValue };
		WebElement[] locator2 = { txtParameterName, txtParameterValue };
		commonUtil.typeIn(locator2, testData2);
		commonUtil.onClick(btnSave);

		try {
			ec.writeCellData("Application_Details", "Modified Application Name", 1, modifiedAppName);
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	/**
	 * This method is used to verify the data of inside an Application with Excel
	 * Data
	 * 
	 * @param sheetName
	 * @param rowNo
	 */
	public void verifyApplication(String sheetName, String rowNo) {
		commonUtil = new CommonUtility(DriverFactory.getDriver());
		myApplication = new MyApplication(DriverFactory.getDriver());
		commonUtil.waitForElementToVisible(myApplication.appListTable);
		commonUtil.doSearch(modifiedAppName);
		commonUtil.onClick(btnOption);
		commonUtil.onClick(optionEdit);
		commonUtil.waitForElementToVisible(labelHeaderNewApplication);
		String actualAppName = txtApplicationName.getAttribute("value");
		if (actualAppName.equals(modifiedAppName)) {
			Log.info("Application Name is matched: " + "Expected: " + modifiedAppName + " Found: " + actualAppName);
		} else {
			Log.error(
					"Application Name is not matched: " + "Expected: " + modifiedAppName + " Found: " + actualAppName);
		}
		commonUtil.onClick(btnConfigAddParam);
		commonUtil.scrollDownToBottomPage();
		commonUtil.waitForElementToVisible(txtParameterName);
		String actualParameterName = txtParameterName.getAttribute("value");
		if (actualParameterName.equals(paramName)) {
			Log.info("Parameter Name is matched for the Application: " + modifiedAppName + " Expected: " + paramName
					+ " Found: " + actualParameterName);
		} else {
			Log.error("Parameter Name is not matched for the Application: " + modifiedAppName + " Expected: "
					+ paramName + " Found: " + actualParameterName);
		}
		String actualParameterValue = txtParameterValue.getAttribute("value");
		if (actualParameterValue.equals(paramValue)) {
			Log.info("Parameter Value is matched for the Application: " + modifiedAppName + " Expected: " + paramValue
					+ " Found: " + actualParameterValue);
		} else {
			Log.error("Parameter Value is not matched for the Application: " + modifiedAppName + " Expected: "
					+ paramValue + " Found: " + actualParameterValue);
		}
	}
}
