package com.app.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.app.factory.DriverFactory;
import com.app.util.CommonUtility;

public class AddConditionPage extends BasePage {

	public AddConditionPage(WebDriver driver) {
		super(driver);
	}

	private CommonUtility commonUtil;
	private ConditionListPage conditionListPage;
	private AddConditionPage addConditionPage;

	@FindBy(xpath = "//h1[text()='Add Condition']")
	public WebElement labelHeaderAddConditionPage;

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

	@FindBy(xpath = "//select[@name='comparisionOperator']")
	private WebElement drpdwnComparisionOperator;

	@FindBy(xpath = "//input[@name='Field_value']")
	private WebElement txtFieldValue;

	@FindBy(xpath = "//input[@name='Api_Key']")
	private WebElement txtApiKey;

	@FindBy(xpath = "//input[@id='msgQuery']")
	private WebElement txtQuery;

	private String ConditionName = null;
	private String ConditionId = null;
	private String ComparisonStepName = null;
	// private String ComparisonBlockName = null;
	private String FieldUniqId = null;
	private String ComparisonOperator = null;
	private String FieldValues = null;
	private String ApiKey = null;
	private String Query = null;
	private String ModifiedConditionName = null;

	public ConditionListPage createCondition(String conditiondetailssheetname, int rowno) {
		commonUtil = new CommonUtility(DriverFactory.getDriver());
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
		String[] testData = { ModifiedConditionName, ConditionId, ComparisonStepName, FieldUniqId, ComparisonOperator,
				FieldValues, ApiKey, Query };
		WebElement[] locator = { txtConditionName, txtConditionId, txtComparisonStepName, txtFieldUniqId,
				drpdwnComparisionOperator, txtFieldValue, txtApiKey, txtQuery };
		commonUtil.typeIn(locator, testData);
		commonUtil.onClick(btnSave);
		return new ConditionListPage(driver);
	}

	public void createConditions(int number, String conditiondetailssheetname, int rowno) {
		for (int i = 0; i < number; i++) {
			conditionListPage = new ConditionListPage(DriverFactory.getDriver());
			addConditionPage = new AddConditionPage(DriverFactory.getDriver());
			conditionListPage.clickOnAddNewConditionBtn();
			addConditionPage.createCondition(conditiondetailssheetname, rowno);

		}
	}
}
