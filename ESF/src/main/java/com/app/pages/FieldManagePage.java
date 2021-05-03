package com.app.pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

import com.app.factory.DriverFactory;
import com.app.util.CommonUtility;
import com.app.util.Log;

public class FieldManagePage extends BasePage {

	public FieldManagePage(WebDriver driver) {
		super(driver);

	}

	private CommonUtility commonUtil;
	private SoftAssert softassert = null;

	@FindBy(xpath = "//h2[text()='Manage Field']")
	public WebElement txtlabelHeaderManageField;

	@FindBy(xpath = "//input[@id='uniqueId']")
	private WebElement txtUniqueId;

	@FindBy(xpath = "//input[@id='fieldLabel']")
	private WebElement txtFieldLabel;

	@FindBy(xpath = "//select[@id='fieldType']")
	private WebElement drpdwnFieldType;

	@FindBy(xpath = "//input[@id='fieldseq']")
	private WebElement txtFieldseq;

	@FindBy(xpath = "//input[@id='placeholderText']")
	private WebElement txtPlaceholder;

	@FindBy(xpath = "//input[@id='tooltip']")
	private WebElement txtTooltip;

	@FindBy(xpath = "//input[@id='fieldCharacterLimit']")
	private WebElement txtFieldCharacterLimit;

	@FindBy(xpath = "//input[@id='apiKey']")
	private WebElement txtapiKey;

	@FindBy(xpath = "//input[@id='requestApiKey']")
	private WebElement txtRequestApiKey;

	@FindBy(xpath = "//input[@id='responseApiKey']")
	private WebElement txtResponseApiKey;

	@FindBy(xpath = "//input[@id='fieldClassName']")
	private WebElement txtCSSClassName;

	@FindBy(xpath = "//button[contains(text(),'Field Values')]")
	private WebElement expandFieldValues;

	@FindBy(xpath = "//input[@id='defaultValue']")
	private WebElement txtDefaultValue;

	@FindBy(xpath = "//input[@placeholder='Enter possible value']")
	private WebElement txtPossibleValue;

	@FindBy(xpath = "//button[text()='Field Style']")
	private WebElement expandFieldStyle;

	@FindBy(xpath = "//input[@id='bootstrap_class_name']")
	private WebElement txtBootstrapClassName;

	@FindBy(xpath = "//input[@id='custom_class_name']")
	private WebElement txtCustomClassName;

	@FindBy(xpath = "//button[text()='Field Conditions']")
	private WebElement expandFieldConditions;

	@FindBy(xpath = "//input[@id='displayConditionT']")
	private WebElement radioDisplayConditionTrue;

	@FindBy(xpath = "//input[@id='displayConditionF']")
	private WebElement radioDisplayConditionFalse;

	@FindBy(xpath = "//input[@id='displayConditionCond']")
	private WebElement radioDisplayConditionConditional;

	@FindBy(xpath = "//button[@data-id='displayCondition']")
	private WebElement drpdwnDisplayCondition;

	@FindBy(xpath = "//input[@aria-controls='bs-select-1']")
	private WebElement searchDisplayCondition;

	@FindBy(xpath = "//input[@id='editableConditionT']")
	private WebElement radioEditableConditionTrue;

	@FindBy(xpath = "//input[@id='editableConditionF']")
	private WebElement radioEditableConditionFalse;

	@FindBy(xpath = "//input[@id='editableConditionCond']")
	private WebElement radioEditableConditionConditional;

	@FindBy(xpath = "//button[@data-id='editableCondition']")
	private WebElement drpdwnEditableCondition;

	@FindBy(xpath = "//input[@aria-controls='bs-select-2']")
	private WebElement searchEditableCondition;

	@FindBy(xpath = "//input[@id='mandatoryConditionT']")
	private WebElement radioMandatoryConditionTrue;

	@FindBy(xpath = "//input[@id='mandatoryConditionF']")
	private WebElement radioMandatoryConditionFalse;

	@FindBy(xpath = "//input[@id='mandatoryConditionCond']")
	private WebElement radioMandatoryConditionConditional;

	@FindBy(xpath = "//button[@data-id='mandatoryCondition']")
	private WebElement drpdwnMandatoryCondition;

	@FindBy(xpath = "//input[@aria-controls='bs-select-3']")
	private WebElement searchMandatoryCondition;

	@FindBy(xpath = "//input[@id='displayConditionValueT']")
	private WebElement radioConditionalValueTrue;

	@FindBy(xpath = "//input[@id='displayConditionValueF']")
	private WebElement radioConditionalValueFalse;

	private String SearchResult = "//span[contains(text(),'%s')]";

	@FindBy(xpath = "//button[@data-id='eventList']")
	private WebElement drpdwnEvent;

	@FindBy(xpath = "//input[@aria-controls='bs-select-4']")
	private WebElement searchEvents;

	@FindBy(xpath = "//button[contains(text(),'Additional Parameter')]")
	private WebElement expandAdditionalParameter;

	@FindBy(xpath = "//button[text()='Validations']")
	private WebElement expandValidation;

	@FindBy(xpath = "//button[@data-id='validationMessages']")
	private WebElement drpdwnValidationMessage;

	@FindBy(xpath = "//input[@aria-controls='bs-select-5']")
	private WebElement searchValidationMessage;

	@FindBy(xpath = "//input[@id='step0']")
	private WebElement txtConditionStep;

	@FindBy(xpath = "//input[@id='field0']")
	private WebElement txtConditionField;

	@FindBy(xpath = "//select[@id='comparisionOperator0']")
	private WebElement drpdwnConditionComparisonOpeartor;

	@FindBy(xpath = "//input[@id='fieldValues0']")
	private WebElement txtConditionFieldValue;

	@FindBy(xpath = "//input[@id='apiKey0']")
	private WebElement txtConditionApiKey;

	@FindBy(xpath = "//input[@id='notApplicable_0']")
	private WebElement radioNotApplicable;

	@FindBy(xpath = "//input[@id='options_0']")
	private WebElement radioOptions;

	@FindBy(xpath = "//input[@id='others_0']")
	private WebElement radioOthers;

	@FindBy(xpath = "//input[@id='stepField_0']")
	private WebElement radioStepField;

	@FindBy(xpath = "//input[@id='valueOptionValue_0']")
	private WebElement txtValueOptionValue;

	@FindBy(xpath = "//input[@id='valueOtherValue_0']")
	private WebElement txtValueOtherValue;

	@FindBy(xpath = "//select[@id='valueStepId_0']")
	private WebElement drpdwnValueStepId;

	@FindBy(xpath = "//input[@id='valueStepField_0']")
	private WebElement txtValueStepField;

	@FindBy(xpath = "//input[@id='valueStepFieldValue_0']")
	private WebElement txtValueStepFieldfValue;

	private static String UniqueId = null;
	private static String FieldLabel = null;
	private static String FieldType = null;
	private static String FieldSequence = null;
	private static String PlaceholderText = null;
	private static String Tooltip = null;
	private static String FieldCharacterLimit = null;
	private static String APIKey = null;
	private static String RequestAPIKey = null;
	private static String ResponseAPIKey = null;
	private static String CSSClassName = null;
	private static String DefaultValue = null;
	private static String PossibleValues = null;
	private static String BootstrapClassName = null;
	private static String CustomClassName = null;
	private static String ParameterName = null;
	private static String ParameterValue = null;
	private static String modifiedUniqId = null;
	private static String IsDiplayConditional = null;
	private static String DisplayCondition = null;
	private static String IsEditableConditional = null;
	private static String EditableCondition = null;
	private static String IsMandatoryConditional = null;
	private static String MandatoryCondition = null;
	private static String EventName = null;
	private static String ValidationMessageName = null;
	private static String IsConditionalValue = null;
	private static String ConditionStep = null;
	private static String ConditionField = null;
	private static String ConditionComparisonOperator = null;
	private static String ConditionFieldValue = null;
	private static String ConditionApiKey = null;
	private static String ValuesToBePopulated = null;
	private static String ValueOptionValue = null;
	private static String ValueOtherValue = null;
	private static String ValueStepId = null;
	private static String ValueStepField = null;
	private static String ValuetepFieldValue = null;

	/**
	 * This method is used to create Field with test data from Excel Sheet
	 * 
	 * @param fielddetailssheetname
	 * @param rowno
	 */
	public void createField(String fielddetailssheetname, int rowno) {
		commonUtil = new CommonUtility(DriverFactory.getDriver());
		UniqueId = ec.getCellData("Field_Details", "Unique Id", rowno);
		FieldLabel = ec.getCellData("Field_Details", "Field Label", rowno);
		FieldType = ec.getCellData("Field_Details", "Field Type", rowno);
		FieldSequence = ec.getCellData("Field_Details", "Field Sequence", rowno);
		PlaceholderText = ec.getCellData("Field_Details", "Placeholder Text", rowno);
		Tooltip = ec.getCellData("Field_Details", "Tooltip", rowno);
		FieldCharacterLimit = ec.getCellData("Field_Details", "Field Character Limit", rowno);
		APIKey = ec.getCellData("Field_Details", "API Key", rowno);
		RequestAPIKey = ec.getCellData("Field_Details", "Request API Key", rowno);
		ResponseAPIKey = ec.getCellData("Field_Details", "Response API Key", rowno);
		CSSClassName = ec.getCellData("Field_Details", "CSS Class Name", rowno);
		DefaultValue = ec.getCellData("Field_Details", "Default Value", rowno);
		PossibleValues = ec.getCellData("Field_Details", "Possible Values", rowno);
		BootstrapClassName = ec.getCellData("Field_Details", "Bootstrap Class Name", rowno);
		CustomClassName = ec.getCellData("Field_Details", "Custom Class Name", rowno);
		IsDiplayConditional = ec.getCellData("Field_Details", "IsDisplayConditional", rowno);
		DisplayCondition = ec.getCellData("Field_Details", "Display Condition", rowno);
		IsEditableConditional = ec.getCellData("Field_Details", "IsEditableConditional", rowno);
		EditableCondition = ec.getCellData("Field_Details", "Editable Condition", rowno);
		IsMandatoryConditional = ec.getCellData("Field_Details", "IsMandatoryConditional", rowno);
		MandatoryCondition = ec.getCellData("Field_Details", "Mandatory Condition", rowno);
		IsConditionalValue = ec.getCellData("Field_Details", "Conditional Value", rowno);
		ConditionStep = ec.getCellData("Field_Details", "Condition Step", rowno);
		ConditionField = ec.getCellData("Field_Details", "Condition Field", rowno);
		ConditionComparisonOperator = ec.getCellData("Field_Details", "Condition Comparison Operator", rowno);
		ConditionFieldValue = ec.getCellData("Field_Details", "Condition Field Value", rowno);
		ConditionApiKey = ec.getCellData("Field_Details", "Condition Api Key", rowno);
		ValuesToBePopulated = ec.getCellData("Field_Details", "Values To Be Populated", rowno);
		ValueOptionValue = ec.getCellData("Field_Details", "Value Option Value", rowno);
		ValueOtherValue = ec.getCellData("Field_Details", "Value Other Value", rowno);
		ValueStepId = ec.getCellData("Field_Details", "Value Step ID", rowno);
		ValueStepField = ec.getCellData("Field_Details", "Value Step Field", rowno);
		ValuetepFieldValue = ec.getCellData("Field_Details", "Value Step Field Value", rowno);
		ParameterName = ec.getCellData("Field_Details", "Parameter Name", rowno);
		ParameterValue = ec.getCellData("Field_Details", "Parameter Value", rowno);
		EventName = ec.getCellData("Field_Details", "Event Name", rowno);
		ValidationMessageName = ec.getCellData("Field_Details", "Validation Message Name", rowno);
		int randomNum = commonUtil.generateRandomNumber();
		modifiedUniqId = UniqueId + "_" + randomNum;
		try {
			ec.writeCellData("Field_Details", "Modified Uniq Id", rowno, modifiedUniqId);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Log.info("Modified Uniq Id for Field is: " + modifiedUniqId);
//		txtFieldLabel.sendKeys(FieldLabel);
//		commonUtil.onClick(txtUniqueId);
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		commonUtil.acceptJavaScripAlert();
		String[] testData1 = { modifiedUniqId, FieldLabel, FieldType, FieldSequence, PlaceholderText, Tooltip,
				FieldCharacterLimit, APIKey, RequestAPIKey, ResponseAPIKey, CSSClassName };
		WebElement[] locator1 = { txtUniqueId, txtFieldLabel, drpdwnFieldType, txtFieldseq, txtPlaceholder, txtTooltip,
				txtFieldCharacterLimit, txtapiKey, txtRequestApiKey, txtResponseApiKey, txtCSSClassName };
		commonUtil.typeIn(locator1, testData1);
		commonUtil.onClick(expandFieldValues);
		String[] testData2 = { DefaultValue, PossibleValues };
		WebElement[] locator2 = { txtDefaultValue, txtPossibleValue };
		commonUtil.typeIn(locator2, testData2);
		commonUtil.onClick(expandFieldStyle);
		commonUtil.waitForElementToVisible(txtBootstrapClassName);
		String[] testData3 = { BootstrapClassName, CustomClassName };
		WebElement[] locator3 = { txtBootstrapClassName, txtCustomClassName };
		commonUtil.typeIn(locator3, testData3);
		commonUtil.onClick(expandFieldConditions);
		switch (IsDiplayConditional) {
		case "0":
			commonUtil.clickByJavaScriptExecutor(radioDisplayConditionFalse);
			break;
		case "1":
			commonUtil.clickByJavaScriptExecutor(radioDisplayConditionTrue);
			break;
		case "2":
			commonUtil.clickByJavaScriptExecutor(radioDisplayConditionConditional);
			commonUtil.onClick(drpdwnDisplayCondition);
			searchDisplayCondition.sendKeys(DisplayCondition);
			commonUtil.onClick(commonUtil.searchDropdown(SearchResult, DisplayCondition));
			break;
		default:
			commonUtil.clickByJavaScriptExecutor(radioDisplayConditionTrue);

		}
		switch (IsEditableConditional) {
		case "0":
			commonUtil.clickByJavaScriptExecutor(radioEditableConditionFalse);
			break;
		case "1":
			commonUtil.clickByJavaScriptExecutor(radioEditableConditionTrue);
			break;
		case "2":
			commonUtil.clickByJavaScriptExecutor(radioEditableConditionConditional);
			commonUtil.onClick(drpdwnEditableCondition);
			searchEditableCondition.sendKeys(EditableCondition);
			commonUtil.onClick(commonUtil.searchDropdown(SearchResult, EditableCondition));
			break;
		default:
			commonUtil.clickByJavaScriptExecutor(radioEditableConditionTrue);

		}
		switch (IsMandatoryConditional) {
		case "0":
			commonUtil.clickByJavaScriptExecutor(radioMandatoryConditionFalse);
			commonUtil.onClick(expandFieldConditions);
			break;
		case "1":
			commonUtil.clickByJavaScriptExecutor(radioMandatoryConditionTrue);
			commonUtil.onClick(expandFieldConditions);
			break;
		case "2":
			commonUtil.clickByJavaScriptExecutor(radioMandatoryConditionConditional);
			commonUtil.onClick(drpdwnMandatoryCondition);
			searchMandatoryCondition.sendKeys(MandatoryCondition);
			commonUtil.onClick(commonUtil.searchDropdown(SearchResult, MandatoryCondition));
			break;
		default:
			commonUtil.clickByJavaScriptExecutor(radioMandatoryConditionFalse);

		}
		if (IsConditionalValue.equals("0")) {
			commonUtil.clickByJavaScriptExecutor(radioConditionalValueFalse);
		} else if (IsConditionalValue.equals("1")) {
			commonUtil.clickByJavaScriptExecutor(radioConditionalValueTrue);
			commonUtil.waitForElementToVisible(txtConditionStep);
			String[] testData4 = { ConditionStep, ConditionField, ConditionComparisonOperator, ConditionFieldValue,
					ConditionApiKey };
			WebElement[] element4 = { txtConditionStep, txtConditionField, drpdwnConditionComparisonOpeartor,
					txtConditionFieldValue, txtConditionApiKey };
			commonUtil.typeIn(element4, testData4);
			switch (ValuesToBePopulated) {
			case "0":
				commonUtil.clickByJavaScriptExecutor(radioNotApplicable);
				break;
			case "1":
				commonUtil.clickByJavaScriptExecutor(radioOptions);
				txtValueOptionValue.sendKeys(ValueOptionValue);
				break;
			case "2":
				commonUtil.clickByJavaScriptExecutor(radioOthers);
				txtValueOtherValue.sendKeys(ValueOtherValue);
				break;
			case "3":
				commonUtil.clickByJavaScriptExecutor(radioStepField);
				String[] testData5 = { ValueStepId, ValueStepField, ValuetepFieldValue };
				WebElement[] element5 = { drpdwnValueStepId, txtValueStepField, txtValueStepFieldfValue };
				commonUtil.typeIn(element5, testData5);
				break;
			}
		} else {
			Log.error("No value is given to Condition Value in Excelsheet");
		}
		// commonUtil.scrollDownToVisibleElement(expandAdditionalParameter);
		// commonUtil.scrollDownToBottomPage();
		commonUtil.onClick(expandAdditionalParameter);
		commonUtil.waitForElementToVisible(txtParameterName);
		String[] testData4 = { ParameterName, ParameterValue };
		WebElement[] locator4 = { txtParameterName, txtParameterValue };
		commonUtil.typeIn(locator4, testData4);
		if (!EventName.isEmpty()) {
			commonUtil.onClick(drpdwnEvent);
			searchEvents.sendKeys(EventName);
			commonUtil.onClick(commonUtil.searchDropdown(SearchResult, EventName));
		}
		commonUtil.onClick(expandValidation);
		if (!ValidationMessageName.isEmpty()) {
			commonUtil.onClick(drpdwnValidationMessage);
			searchValidationMessage.sendKeys(ValidationMessageName);
			commonUtil.onClick(commonUtil.searchDropdown(SearchResult, ValidationMessageName));
		}
		commonUtil.scrollDownToBottomPage();
		commonUtil.waitForElementToVisible(btnSave);
		commonUtil.onClick(btnSave);
	}

	/**
	 * This method is used to verify the data of an field with Excel sheet data
	 * 
	 * @param fielddetailssheetname
	 * @param rowno
	 * @throws InterruptedException
	 */
	public void verifyField() {
		commonUtil = new CommonUtility(DriverFactory.getDriver());
		softassert = new SoftAssert();
		commonUtil.waitForElementToVisible(txtlabelHeaderManageField);
		String actualUniqueId = commonUtil.getText(txtUniqueId);
		String actualFieldLabel = commonUtil.getText(txtFieldLabel);
		Select s1 = new Select(drpdwnFieldType);
		WebElement fieldType = s1.getFirstSelectedOption();
		String actualFieldType = commonUtil.getText(fieldType);
		String actualFieldSequence = commonUtil.getText(txtFieldseq);
		String actualPlaceholderText = commonUtil.getText(txtPlaceholder);
		String actualTooltip = commonUtil.getText(txtTooltip);
		String actualFieldCharacterLimit = commonUtil.getText(txtFieldCharacterLimit);
		String actualAPIKey = commonUtil.getText(txtapiKey);
		String actualRequestAPIKey = commonUtil.getText(txtRequestApiKey);
		String actualResponseAPIKey = commonUtil.getText(txtResponseApiKey);
		String actualCSSClassName = commonUtil.getText(txtCSSClassName);
		commonUtil.onClick(expandFieldValues);
		String actualDefaultValue = commonUtil.getText(txtDefaultValue);
		String actualPossibleValues = commonUtil.getText(txtPossibleValue);
		commonUtil.onClick(expandFieldStyle);
		String actualBootstrapClassName = commonUtil.getText(txtBootstrapClassName);
		String actualCustomClassName = commonUtil.getText(txtCustomClassName);
		commonUtil.onClick(expandFieldConditions);
		String actualDisplayConditionName = "";
		if (IsDiplayConditional.equals("0")) {
			softassert.assertTrue(radioDisplayConditionFalse.isSelected(),
					"False radio button is not selected for Display condition");
		} else if (IsDiplayConditional.equals("2")) {
			softassert.assertTrue(radioDisplayConditionConditional.isSelected(),
					"Conditional radio button is not selected for Display condition");
			actualDisplayConditionName = commonUtil.getText(drpdwnDisplayCondition);
		} else {
			softassert.assertTrue(radioDisplayConditionTrue.isSelected(),
					"True radio button is not selected for Display Condition");
		}
		String actualEditableConditionName = "";
		if (IsEditableConditional.equals("0")) {
			softassert.assertTrue(radioEditableConditionFalse.isSelected(),
					"False radio button is not selected for Ediatble condition");
		} else if (IsEditableConditional.equals("2")) {
			softassert.assertTrue(radioEditableConditionConditional.isSelected(),
					"Conditional radio button is not selected for Ediatble condition");
			actualEditableConditionName = commonUtil.getText(drpdwnEditableCondition);
		} else {
			softassert.assertTrue(radioEditableConditionTrue.isSelected(),
					"True radio button is not selected for Ediatble Condition");
		}
		String actualMandatoryConditionName = "";
		if (IsMandatoryConditional.equals("1")) {
			softassert.assertTrue(radioMandatoryConditionTrue.isSelected(),
					"True radio button is not selected for Mandatory condition");
		} else if (IsMandatoryConditional.equals("2")) {
			softassert.assertTrue(radioMandatoryConditionConditional.isSelected(),
					"Conditional radio button is not selected for Mandatory condition");
			actualMandatoryConditionName = commonUtil.getText(drpdwnMandatoryCondition);
		} else {
			softassert.assertTrue(radioMandatoryConditionFalse.isSelected(),
					"False radio button is not selected for Mandatory Condition");
		}
		String actualConditionStep = "";
		String actualConditionField = "";
		String actualConditionComparionOperator = "";
		String actualFieldValue = "";
		String actualApiKey = "";
		String actualValueOptionValue = "";
		String actualValueOtherValue = "";
		String actualValueStepId = "";
		String actualValueStepField = "";
		String actualValueStepFieldValue = "";
		if (IsConditionalValue.equals("1")) {
			actualConditionStep = commonUtil.getText(txtConditionStep);
			actualConditionField = commonUtil.getText(txtConditionField);
			Select s2 = new Select(drpdwnConditionComparisonOpeartor);
			WebElement conditionComparionOperator = s2.getFirstSelectedOption();
			actualConditionComparionOperator = commonUtil.getText(conditionComparionOperator);
			actualFieldValue = commonUtil.getText(txtConditionFieldValue);
			actualApiKey = commonUtil.getText(txtConditionApiKey);
			switch (ValuesToBePopulated) {
			case "0":
				softassert.assertTrue(radioNotApplicable.isSelected(), "Not Applicable radio button is not selected");
				break;
			case "1":
				softassert.assertTrue(radioOptions.isSelected(), "Options radio button is not selected");
				actualValueOptionValue = commonUtil.getText(txtValueOptionValue);
				softassert.assertEquals(actualValueOptionValue, ValueOptionValue);
				break;
			case "2":
				softassert.assertTrue(radioOthers.isSelected(), "Other radio button is not selected");
				actualValueOtherValue = commonUtil.getText(txtValueOtherValue);
				softassert.assertEquals(actualValueOtherValue, ValueOtherValue);
				break;
			case "3":
				softassert.assertTrue(radioStepField.isSelected(), "Step Field radio button is not selected");
				Select s3 = new Select(drpdwnValueStepId);
				WebElement valueStepId = s3.getFirstSelectedOption();
				actualValueStepId = commonUtil.getText(valueStepId);
				actualValueStepField = commonUtil.getText(txtValueStepField);
				actualValueStepFieldValue = commonUtil.getText(txtValueStepFieldfValue);
				String[] actualData2 = { actualValueStepId, actualValueStepField, actualValueStepFieldValue };
				String[] expectedData2 = { ValueStepId, ValueStepField, ValuetepFieldValue };
				commonUtil.softAssert(actualData2, expectedData2, softassert);
				break;
			}
			String[] actualData1 = { actualConditionStep, actualConditionField, actualConditionComparionOperator,
					actualFieldValue, actualApiKey };
			String[] expectedData1 = { ConditionStep, ConditionField, ConditionComparisonOperator, ConditionFieldValue,
					ConditionApiKey };
			commonUtil.softAssert(actualData1, expectedData1, softassert);
		}
		// commonUtil.scrollDownToVisibleElement(expandAdditionalParameter);
		commonUtil.onClick(expandAdditionalParameter);
		commonUtil.waitForElementToVisible(txtBootstrapClassName);
		String actualParameterName = commonUtil.getText(txtParameterName);
		String actualParameterValue = commonUtil.getText(txtParameterValue);
		String actualEventName = "";
		if (!EventName.isEmpty()) {
			actualEventName = commonUtil.getText(drpdwnEvent);
		}
		String actualValidationMessageName = "";
		commonUtil.onClick(expandValidation);
		if (!ValidationMessageName.isEmpty()) {
			actualValidationMessageName = commonUtil.getText(drpdwnValidationMessage);
		}
		String[] actualData = { actualUniqueId, actualFieldLabel, actualFieldType, actualFieldSequence,
				actualPlaceholderText, actualTooltip, actualFieldCharacterLimit, actualAPIKey, actualRequestAPIKey,
				actualResponseAPIKey, actualCSSClassName, actualDefaultValue, actualPossibleValues,
				actualBootstrapClassName, actualCustomClassName, actualDisplayConditionName,
				actualEditableConditionName, actualMandatoryConditionName, actualParameterName, actualParameterValue,
				actualEventName, actualValidationMessageName };
		String[] expectedData = { modifiedUniqId, FieldLabel, FieldType, FieldSequence, PlaceholderText, Tooltip,
				FieldCharacterLimit, APIKey, RequestAPIKey, ResponseAPIKey, CSSClassName, DefaultValue, PossibleValues,
				BootstrapClassName, CustomClassName, DisplayCondition, EditableCondition, MandatoryCondition,
				ParameterName, ParameterValue, EventName, ValidationMessageName };
		commonUtil.softAssert(actualData, expectedData, softassert);
		softassert.assertAll();
	}

}
