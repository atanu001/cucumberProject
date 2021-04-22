package com.app.pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;

import com.app.factory.DriverFactory;
import com.app.util.CommonUtility;

public class ApplicationManagePage extends BasePage {

	public ApplicationManagePage(WebDriver driver) {
		super(driver);
	}

	private CommonUtility commonUtil;
	private ApplicationListPage applicationListPage;
	private SoftAssert softassert = null;

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
	public void createApplication(String sheetName, int rowNo) {
		commonUtil = new CommonUtility(DriverFactory.getDriver());
		appName = ec.getCellData("Application_Details", "Application Name", rowNo);
		platformName = ec.getCellData("Application_Details", "Platform", rowNo);
		languageName = ec.getCellData("Application_Details", "Languages", rowNo);
		paramName = ec.getCellData("Application_Details", "Parameter Name", rowNo);
		paramValue = ec.getCellData("Application_Details", "Parameter Value", rowNo);
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
			ec.writeCellData("Application_Details", "Modified Application Name", rowNo, modifiedAppName);
			ec.writeCellData("Application_Removal", "Application Name", rowNo, modifiedAppName);
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
	public void verifyApplication(String sheetName, int rowNo) {
		commonUtil = new CommonUtility(DriverFactory.getDriver());
		softassert = new SoftAssert();
		applicationListPage = new ApplicationListPage(DriverFactory.getDriver());
		commonUtil.waitForElementToVisible(applicationListPage.appListTable);
		commonUtil.doSearch(modifiedAppName);
		commonUtil.onClick(btnOption);
		commonUtil.onClick(optionEdit);
		commonUtil.waitForElementToVisible(labelHeaderNewApplication);
		String actualAppName = commonUtil.getText(txtApplicationName);
		commonUtil.onClick(btnConfigAddParam);
		commonUtil.scrollDownToBottomPage();
		commonUtil.waitForElementToVisible(txtParameterName);
		String actualParameterName = commonUtil.getText(txtParameterName);
		String actualParameterValue = commonUtil.getText(txtParameterValue);
		String[] actualData = { actualAppName, actualParameterName, actualParameterValue };
		String[] expectedData = { modifiedAppName, paramName, paramValue };
		commonUtil.softAssert(actualData, expectedData, softassert);
		softassert.assertAll();
	}
}
