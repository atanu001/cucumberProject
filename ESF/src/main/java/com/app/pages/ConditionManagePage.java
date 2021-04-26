package com.app.pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

import com.app.factory.DriverFactory;
import com.app.util.CommonUtility;

public class ConditionManagePage extends BasePage {

	public ConditionManagePage(WebDriver driver) {
		super(driver);
	}

	private CommonUtility commonUtil;
	private ConditionListPage conditionListPage;
	// private ConditionManagePage conditionManagePage;
	private SoftAssert softassert = null;

	@FindBy(xpath = "//h1[text()='Add Condition']")
	public WebElement labelHeaderconditionManagePage;

	@FindBy(xpath = "//input[@id='condName']")
	private WebElement txtConditionName;

	@FindBy(xpath = "//input[@name='ID']")
	private WebElement txtConditionId;

	@FindBy(xpath = "//input[@name='Comparison_step']")
	private WebElement txtComparisonStepName;

	@FindBy(xpath = "//input[@name='block']")
	private WebElement txtComparisonBlockName;

	@FindBy(xpath = "//input[@name='Field']")
	private WebElement txtFieldUniqId;

	@FindBy(xpath = "//select[@id='comparisionOperator0']")
	private WebElement drpdwnComparisionOperator;

	@FindBy(xpath = "//input[@name='Field_value']")
	private WebElement txtFieldValue;

	@FindBy(xpath = "//input[@name='Api_Key']")
	private WebElement txtApiKey;

	@FindBy(xpath = "//input[@id='msgQuery']")
	private WebElement txtQuery;

	private static String ConditionName = null;
	private static String ConditionId = null;
	private static String ComparisonStepName = null;
	// private String ComparisonBlockName = null;
	private static String FieldUniqId = null;
	private static String ComparisonOperator = null;
	private static String FieldValues = null;
	private static String ApiKey = null;
	private static String Query = null;
	private static String ModifiedConditionName = null;

	/**
	 * This method is used to create Condition
	 * 
	 * @param conditiondetailssheetname
	 * @param rowno
	 * @return Condition List Page
	 */
	public ConditionListPage createCondition(int number, String conditiondetailssheetname, int rowno) {
		commonUtil = new CommonUtility(DriverFactory.getDriver());
		conditionListPage = new ConditionListPage(DriverFactory.getDriver());
		ConditionName = ec.getCellData("Condition_Details", "Condition Name", rowno);
		ConditionId = ec.getCellData("Condition_Details", "Condition ID", rowno);
		ComparisonStepName = ec.getCellData("Condition_Details", "Step Name", rowno);
		FieldUniqId = ec.getCellData("Condition_Details", "Field Unique Id", rowno);
		ComparisonOperator = ec.getCellData("Condition_Details", "Comparison Operator", rowno);
		FieldValues = ec.getCellData("Condition_Details", "Field Values", rowno);
		ApiKey = ec.getCellData("Condition_Details", "Api Key", rowno);
		Query = ec.getCellData("Condition_Details", "Query", rowno);
		int randNum = commonUtil.generateRandomNumber();
		ModifiedConditionName = ConditionName + "_" + randNum;
		try {
			ec.writeCellData("Condition_Details", "Modified Condition Name", rowno, ModifiedConditionName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] testData = { ModifiedConditionName, ConditionId, ComparisonStepName, FieldUniqId, ComparisonOperator,
				FieldValues, ApiKey, Query };
		WebElement[] locator = { txtConditionName, txtConditionId, txtComparisonStepName, txtFieldUniqId,
				drpdwnComparisionOperator, txtFieldValue, txtApiKey, txtQuery };
		commonUtil.typeIn(locator, testData);
		commonUtil.onClick(btnSave);
		commonUtil.waitForElementToVisible(conditionListPage.labelHeaderConditionListPage);
		return new ConditionListPage(driver);
	}

	/**
	 * This method is used to create conditions as per the number provided
	 * 
	 * @param number
	 * @param conditiondetailssheetname
	 * @param rowno
	 */
//	public void createConditions(int number, String conditiondetailssheetname, int rowno) {
//		for (int i = 0; i < number; i++) {
//			conditionListPage = new ConditionListPage(DriverFactory.getDriver());
//			conditionManagePage = new ConditionManagePage(DriverFactory.getDriver());
//			conditionListPage.clickOnAddNewConditionBtn();
//			 conditionManagePage.createCondition(conditiondetailssheetname, rowno);
//
//		}
//	}

	/**
	 * This method is used to verify the Condition data with excel data
	 */
	public void verifyCondition() {
		commonUtil = new CommonUtility(DriverFactory.getDriver());
		softassert = new SoftAssert();
		String actualConditionName = commonUtil.getText(txtConditionName);
		String actualConditionId = commonUtil.getText(txtConditionId);
		String actualComparisonStepName = commonUtil.getText(txtComparisonStepName);
		String actualFieldUniqId = commonUtil.getText(txtFieldUniqId);
		Select s = new Select(drpdwnComparisionOperator);
		WebElement compOperator = s.getFirstSelectedOption();
		String actualComparisonOperator = commonUtil.getText(compOperator);
		String actualFieldValues = commonUtil.getText(txtFieldValue);
		String actualApiKey = commonUtil.getText(txtApiKey);
		String actualQuery = commonUtil.getText(txtQuery);
		String[] actualData = { actualConditionName, actualConditionId, actualComparisonStepName, actualFieldUniqId,
				actualComparisonOperator, actualFieldValues, actualApiKey, actualQuery };
		String[] expectedData = { ModifiedConditionName, ConditionId, ComparisonStepName, FieldUniqId,
				ComparisonOperator, FieldValues, ApiKey, Query };
		commonUtil.softAssert(actualData, expectedData, softassert);
		softassert.assertAll();

	}
}
