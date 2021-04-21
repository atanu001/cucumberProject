package com.app.pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.app.factory.DriverFactory;
import com.app.util.CommonUtility;
import com.app.util.Log;

public class ValidationMessageManagePage extends BasePage {

	public ValidationMessageManagePage(WebDriver driver) {
		super(driver);
	}

	private CommonUtility commonUtil;
	private ValidationMessageListPage validationMessageListPage;

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

		String actualValMsgName = txtValMsgName.getAttribute("value");
		if (actualValMsgName.equals(ModifiedValMsgName)) {
			Log.info("Validation Message is matched " + " Expected: " + ModifiedValMsgName + " Found: "
					+ actualValMsgName);
		} else {
			Log.error("Validation Message does not matched " + " Expected: " + ModifiedValMsgName + " Found: "
					+ actualValMsgName);
		}
		String actualValMsgType = drpdwnValmsgType.getAttribute("value");
		if (actualValMsgType.equals(ValmsgType)) {
			Log.info(
					"Validation Message Type is matched " + " Expected: " + ValmsgType + " Found: " + actualValMsgType);
		} else {
			Log.error("Validation Message Type does not matched " + " Expected: " + ValmsgType + " Found: "
					+ actualValMsgType);
		}
		Select s1 = new Select(drpdwnDisplayType);
		WebElement ele1 = s1.getFirstSelectedOption();
		String actualDisplayType = ele1.getText();
		if (actualDisplayType.equals(DisplayType)) {
			Log.info("Display Type is matched " + " Expected: " + DisplayType + " Found: " + actualDisplayType);
		} else {
			Log.error("Display Type does not matched " + " Expected: " + DisplayType + " Found: " + actualDisplayType);
		}
		String actualAssociateMsgCode = drpdwnAssociateMsgCode.getAttribute("value");
		if (actualAssociateMsgCode.equals(AssociateMsgCode)) {
			Log.info("Associate Message Code is matched " + " Expected: " + AssociateMsgCode + " Found: "
					+ actualAssociateMsgCode);
		} else {
			Log.error("Associate Message Code does not matched " + " Expected: " + AssociateMsgCode + " Found: "
					+ actualAssociateMsgCode);
		}
		if (ValmsgType.equals("Conditional")) {
			String actualConditionalId = txtConditionId.getAttribute("value").trim();
			// String abc = actualConditionalId.trim();
			if (actualConditionalId.equals(ConditionId)) {
				Log.info("Condition Id is matched " + " Expected: " + ConditionId + " Found: " + actualConditionalId);
			} else {
				Log.error("Condition Id does not matched " + " Expected: " + ConditionId + " Found: "
						+ actualConditionalId);
			}
			String actualComparisonStepName = txtComparisonStepName.getAttribute("value");
			if (actualComparisonStepName.equals(ComparisonStepName)) {
				Log.info("Comparison Step Name is matched " + " Expected: " + ComparisonStepName + " Found: "
						+ actualComparisonStepName);
			} else {
				Log.error("Comparison Step Name does not matched " + " Expected: " + ComparisonStepName + " Found: "
						+ actualComparisonStepName);
			}
			String actualFieldUniqId = txtFieldUniqId.getAttribute("value").trim();
			if (actualFieldUniqId.equals(FieldUniqId)) {
				Log.info("Field Uniq Id is matched " + " Expected: " + FieldUniqId + " Found: " + actualFieldUniqId);
			} else {
				Log.error("Field Uniq Id does not matched " + " Expected: " + FieldUniqId + " Found: "
						+ actualFieldUniqId);
			}
			Select s2 = new Select(drpdwnComparisionOperator);
			WebElement ele2 = s2.getFirstSelectedOption();
			String actualComparisionOperator = ele2.getText();
			if (actualComparisionOperator.equals(ComparisionOperator)) {
				Log.info("Comparision Operator is matched " + " Expected: " + ComparisionOperator + " Found: "
						+ actualComparisionOperator);
			} else {
				Log.error("Comparision Operator does not matched " + " Expected: " + ComparisionOperator + " Found: "
						+ actualComparisionOperator);
			}
			String actualFieldValue = txtFieldValue.getAttribute("value").trim();
			if (actualFieldValue.equals(FieldValue)) {
				Log.info("Field Value is matched " + " Expected: " + FieldValue + " Found: " + actualFieldValue);
			} else {
				Log.error("Field Value does not matched " + " Expected: " + FieldValue + " Found: " + actualFieldValue);
			}
			String actualApiKey = txtApiKey.getAttribute("value").trim();
			if (actualApiKey.equals(ApiKey)) {
				Log.info("Api Key is matched " + " Expected: " + ApiKey + " Found: " + actualApiKey);
			} else {
				Log.error("Api Key does not matched " + " Expected: " + ApiKey + " Found: " + actualApiKey);
			}
			String actualCompareBy = drpdwnCompareBy.getAttribute("value");
			if (actualCompareBy.equals(CompareBy)) {
				Log.info("Compare By is matched " + " Expected: " + CompareBy + " Found: " + actualCompareBy);
			} else {
				Log.error("Compare By does not matched " + " Expected: " + CompareBy + " Found: " + actualCompareBy);
			}
			String actualConditionQuery = txtConditionQuery.getAttribute("value");
			if (actualConditionQuery.equals(ConditionQuery)) {
				Log.info("Condition Query is matched " + " Expected: " + ConditionQuery + " Found: "
						+ actualConditionQuery);
			} else {
				Log.error("Condition Query does not matched " + " Expected: " + ConditionQuery + " Found: "
						+ actualConditionQuery);
			}
		}
	}

}
