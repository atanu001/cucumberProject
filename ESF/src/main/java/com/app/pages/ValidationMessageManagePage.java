package com.app.pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

import com.app.factory.DriverFactory;
import com.app.util.CommonUtility;

public class ValidationMessageManagePage extends BasePage {

	public ValidationMessageManagePage(WebDriver driver) {
		super(driver);
	}

	private CommonUtility commonUtil;
	private ValidationMessageListPage validationMessageListPage;
	private SoftAssert softassert = null;

	@FindBy(xpath = "//h1[text()='Add Validation Message']")
	public WebElement labelTxtHeaderValidationMsgManagePage;

	@FindBy(xpath = "//input[@id='valmsgName']")
	private WebElement txtValMsgName;

	@FindBy(xpath = "//select[@id='valmsgtype']")
	private WebElement drpdwnValmsgType;

	@FindBy(xpath = "//select[@id='displayType']")
	private WebElement drpdwnDisplayType;

	@FindBy(xpath = "//select[@id='messageCode']")
	private WebElement drpdwnAssociateMsgCode;

	@FindBy(xpath = "//input[@name='ID']")
	private WebElement txtConditionId;

	@FindBy(xpath = "//input[@name='Comparison_step']")
	private WebElement txtComparisonStepName;

	@FindBy(xpath = "//input[@name='Field']")
	private WebElement txtFieldUniqId;

	@FindBy(xpath = "//select[@id='Comparison0']")
	private WebElement drpdwnComparisionOperator;

	@FindBy(xpath = "//input[@name='Field_value']")
	private WebElement txtFieldValue;

	@FindBy(xpath = "//input[@name='Api_key']")
	private WebElement txtApiKey;

	@FindBy(xpath = "//select[@name='compare_by']")
	private WebElement drpdwnCompareBy;

	@FindBy(xpath = "//input[@id='msgQuery']")
	private WebElement txtConditionQuery;

	private static String ValMsgName = null;
	private static String ValmsgType = null;
	private static String DisplayType = null;
	private static String AssociateMsgCode = null;
	private static String ModifiedValMsgName = null;
	private static String ConditionId = null;
	private static String ComparisonStepName = null;
	private static String FieldUniqId = null;
	private static String ComparisionOperator = null;
	private static String FieldValue = null;
	private static String ApiKey = null;
	private static String CompareBy = null;
	private static String ConditionQuery = null;

	/**
	 * This method is used to create a Validation Message
	 * 
	 * @param noofvalidationmessage
	 * @param validationMessageSheetName
	 * @param rowno
	 * @return the Validation Message List Page
	 */
	public ValidationMessageListPage createValidationMessage(int noofvalidationmessage,
			String validationMessageSheetName, int rowno) {
		commonUtil = new CommonUtility(DriverFactory.getDriver());
		validationMessageListPage = new ValidationMessageListPage(DriverFactory.getDriver());
		ValMsgName = ec.getCellData("Validation_Message_Details", "Name", rowno);
		ValmsgType = ec.getCellData("Validation_Message_Details", "Validation Message Type", rowno);
		DisplayType = ec.getCellData("Validation_Message_Details", "Display Type", rowno);
		AssociateMsgCode = ec.getCellData("Validation_Message_Details", "Associate Message Code", rowno);
		int randNum = commonUtil.generateRandomNumber();
		ModifiedValMsgName = ValMsgName + "_" + randNum;
		try {
			ec.writeCellData("Validation_Message_Details", "Modified Name", rowno, ModifiedValMsgName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] testData1 = { ModifiedValMsgName, ValmsgType, DisplayType, AssociateMsgCode };
		WebElement[] element1 = { txtValMsgName, drpdwnValmsgType, drpdwnDisplayType, drpdwnAssociateMsgCode };
		commonUtil.typeIn(element1, testData1);
		Select drpdwn = new Select(drpdwnValmsgType);
		List<WebElement> element = drpdwn.getOptions();
		String[] expectedValMsgType = ec
				.getCellData("Validation_Message_Details", "Expected Validation Message Type", rowno).split("\\|");
		for (int i = 0; i < element.size(); i++) {
			if (element.get(i).getText().equalsIgnoreCase(expectedValMsgType[i + 1])) {
				System.out.println(element.get(i).getText());
				System.out.println(expectedValMsgType[i + 1]);
				System.out.println("pass");
			} else {
				System.out.println("Fail");
				System.out.println(element.get(i).getText());
				System.out.println(expectedValMsgType[i + 1]);
			}

		}

		if (ValmsgType.equals("Conditional")) {
			ConditionId = ec.getCellData("Validation_Message_Details", "Conditional ID", rowno);
			ComparisonStepName = ec.getCellData("Validation_Message_Details", "Step Name", rowno);
			FieldUniqId = ec.getCellData("Validation_Message_Details", "Field Unique Id", rowno);
			ComparisionOperator = ec.getCellData("Validation_Message_Details", "Comparison Operator", rowno);
			FieldValue = ec.getCellData("Validation_Message_Details", "Field Values", rowno);
			ApiKey = ec.getCellData("Validation_Message_Details", "Api Key", rowno);
			CompareBy = ec.getCellData("Validation_Message_Details", "CompareBy", rowno);
			ConditionQuery = ec.getCellData("Validation_Message_Details", "Condition Query", rowno);
			String[] testData2 = { ConditionId, ComparisonStepName, FieldUniqId, ComparisionOperator, FieldValue,
					ApiKey, CompareBy, ConditionQuery };
			WebElement[] element2 = { txtConditionId, txtComparisonStepName, txtFieldUniqId, drpdwnComparisionOperator,
					txtFieldValue, txtApiKey, drpdwnCompareBy, txtConditionQuery };
			commonUtil.typeIn(element2, testData2);
		}
		commonUtil.onClick(btnSave);
		commonUtil.waitForElementToVisible(validationMessageListPage.labelTxtHeaderValidationMsgListPage);
		return new ValidationMessageListPage(driver);
	}

	public void verifyValidationMessage() {
		commonUtil = new CommonUtility(DriverFactory.getDriver());
		softassert = new SoftAssert();
		String actualValMsgName = txtValMsgName.getAttribute("value");
		String actualValMsgType = drpdwnValmsgType.getAttribute("value");
		Select s1 = new Select(drpdwnDisplayType);
		WebElement ele1 = s1.getFirstSelectedOption();
		String actualDisplayType = ele1.getText();
		String actualAssociateMsgCode = drpdwnAssociateMsgCode.getAttribute("value");
		String[] actualData = { actualValMsgName, actualValMsgType, actualDisplayType, actualAssociateMsgCode };
		String[] expectedData = { ModifiedValMsgName, ValmsgType, DisplayType, AssociateMsgCode };
		commonUtil.softAssert(actualData, expectedData, softassert);
		if (ValmsgType.equals("Conditional")) {
			String actualConditionalId = txtConditionId.getAttribute("value").trim();
			String actualComparisonStepName = txtComparisonStepName.getAttribute("value");
			String actualFieldUniqId = txtFieldUniqId.getAttribute("value").trim();
			Select s2 = new Select(drpdwnComparisionOperator);
			WebElement ele2 = s2.getFirstSelectedOption();
			String actualComparisionOperator = ele2.getText();
			String actualFieldValue = txtFieldValue.getAttribute("value").trim();
			String actualApiKey = txtApiKey.getAttribute("value").trim();
			String actualCompareBy = drpdwnCompareBy.getAttribute("value");
			String actualConditionQuery = txtConditionQuery.getAttribute("value");
			String[] actualData2 = { actualConditionalId, actualComparisonStepName, actualFieldUniqId,
					actualComparisionOperator, actualFieldValue, actualApiKey, actualCompareBy, actualConditionQuery };
			String[] expectedData2 = { ConditionId, ComparisonStepName, FieldUniqId, ComparisionOperator, FieldValue,
					ApiKey, CompareBy, ConditionQuery };
			commonUtil.softAssert(actualData2, expectedData2, softassert);

		}
		softassert.assertAll();
	}

}
