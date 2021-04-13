package com.app.pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.app.factory.DriverFactory;
import com.app.util.CommonUtility;
import com.app.util.Log;

public class ManageFieldPage extends BasePage {

	public ManageFieldPage(WebDriver driver) {
		super(driver);

	}

	private CommonUtility commonUtil = new CommonUtility(DriverFactory.getDriver());;
	private FieldListPage fieldListPage = new FieldListPage(DriverFactory.getDriver());;

	@FindBy(xpath = "//h2[text()='Manage Field']")
	public WebElement txtlabelHeaderManageFiled;

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

	@FindBy(xpath = "//button[contains(text(),'Additional Parameter')]")
	private WebElement expandAdditionalParameter;

//	public void createField(String UniqueId, String FieldLabel, String FieldType, String FieldSequence,
//			String PlaceholderText, String Tooltip, String FieldCharacterLimit, String APIKey, String RequestAPIKey,
//			String ResponseAPIKey, String CSSClassName, String DefaultValue, String PossibleValues,
//			String BootstrapClassName, String CustomClassName, String ParameterName, String ParameterValue) {
//
//		txtFieldLabel.sendKeys(FieldLabel);
//		commonUtil.onClick(txtUniqueId);
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		commonUtil.acceptJavaScripAlert();
//		String[] testData1 = { UniqueId, FieldType, FieldSequence, PlaceholderText, Tooltip, FieldCharacterLimit,
//				APIKey, RequestAPIKey, ResponseAPIKey, CSSClassName };
//		WebElement[] locator1 = { txtUniqueId, drpdwnFieldType, txtFieldseq, txtPlaceholder, txtTooltip,
//				txtFieldCharacterLimit, txtapiKey, txtRequestApiKey, txtResponseApiKey, txtCSSClassName };
//		commonUtil.typeIn(locator1, testData1);
//		commonUtil.onClick(expandFieldValues);
//		String[] testData2 = { DefaultValue, PossibleValues };
//		WebElement[] locator2 = { txtDefaultValue, txtPossibleValue };
//		commonUtil.typeIn(locator2, testData2);
//		commonUtil.onClick(expandFieldStyle);
//		commonUtil.waitForElementToVisible(txtBootstrapClassName);
//		String[] testData3 = { BootstrapClassName, CustomClassName };
//		WebElement[] locator3 = { txtBootstrapClassName, txtCustomClassName };
//		commonUtil.typeIn(locator3, testData3);
//		commonUtil.onClick(expandAdditionalParameter);
//		commonUtil.waitForElementToVisible(txtParameterName);
//		String[] testData4 = { ParameterName, ParameterValue };
//		WebElement[] locator4 = { txtParameterName, txtParameterValue };
//		commonUtil.typeIn(locator4, testData4);
//		commonUtil.scrollDownToBottomPage();
//		commonUtil.waitForElementToVisible(btnSave);
//		commonUtil.onClick(btnSave);
//	}

	private String UniqueId = null;
	private String FieldLabel = null;
	private String FieldType = null;
	private String FieldSequence = null;
	private String PlaceholderText = null;
	private String Tooltip = null;
	private String FieldCharacterLimit = null;
	private String APIKey = null;
	private String RequestAPIKey = null;
	private String ResponseAPIKey = null;
	private String CSSClassName = null;
	private String DefaultValue = null;
	private String PossibleValues = null;
	private String BootstrapClassName = null;
	private String CustomClassName = null;
	private String ParameterName = null;
	private String ParameterValue = null;
	private String modifiedUniqId = null;

	/**
	 * This method is used to create Field with test data from Excel Sheet
	 * 
	 * @param fielddetailssheetname
	 * @param rowno
	 */
	public void createField(String fielddetailssheetname, String rowno) {
		UniqueId = ec.getCellData("Field_Details", "Unique Id", 0);
		FieldLabel = ec.getCellData("Field_Details", "Field Label", 0);
		FieldType = ec.getCellData("Field_Details", "Field Type", 0);
		FieldSequence = ec.getCellData("Field_Details", "Field Sequence", 0);
		PlaceholderText = ec.getCellData("Field_Details", "Placeholder Text", 0);
		Tooltip = ec.getCellData("Field_Details", "Tooltip", 0);
		FieldCharacterLimit = ec.getCellData("Field_Details", "Field Character Limit", 0);
		APIKey = ec.getCellData("Field_Details", "API Key", 0);
		RequestAPIKey = ec.getCellData("Field_Details", "Request API Key", 0);
		ResponseAPIKey = ec.getCellData("Field_Details", "Response API Key", 0);
		CSSClassName = ec.getCellData("Field_Details", "CSS Class Name", 0);
		DefaultValue = ec.getCellData("Field_Details", "Default Value", 0);
		PossibleValues = ec.getCellData("Field_Details", "Possible Values", 0);
		BootstrapClassName = ec.getCellData("Field_Details", "Bootstrap Class Name", 0);
		CustomClassName = ec.getCellData("Field_Details", "Custom Class Name", 0);
		ParameterName = ec.getCellData("Field_Details", "Parameter Name", 0);
		ParameterValue = ec.getCellData("Field_Details", "Parameter Value", 0);
		int randomNum = commonUtil.generateRandomNumber();
		modifiedUniqId = UniqueId + "_" + randomNum;
		try {
			ec.writeCellData("Step_Details", "Modified Step Name", 1, modifiedUniqId);
		} catch (IOException e) {
			e.printStackTrace();
		}
		txtFieldLabel.sendKeys(FieldLabel);
		commonUtil.onClick(txtUniqueId);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		commonUtil.acceptJavaScripAlert();
		String[] testData1 = { modifiedUniqId, FieldType, FieldSequence, PlaceholderText, Tooltip, FieldCharacterLimit,
				APIKey, RequestAPIKey, ResponseAPIKey, CSSClassName };
		WebElement[] locator1 = { txtUniqueId, drpdwnFieldType, txtFieldseq, txtPlaceholder, txtTooltip,
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
		commonUtil.onClick(expandAdditionalParameter);
		commonUtil.waitForElementToVisible(txtParameterName);
		String[] testData4 = { ParameterName, ParameterValue };
		WebElement[] locator4 = { txtParameterName, txtParameterValue };
		commonUtil.typeIn(locator4, testData4);
		commonUtil.scrollDownToBottomPage();
		commonUtil.waitForElementToVisible(btnSave);
		commonUtil.onClick(btnSave);
	}

	public void verifyField(String fielddetailssheetname, String rowno) {
		commonUtil.waitForElementToVisible(fieldListPage.txtLabelHeaderFieldListPage);
		commonUtil.doSearch(modifiedUniqId);
		commonUtil.onClick(btnOption);
		commonUtil.onClick(optionEdit);
		commonUtil.waitForElementToVisible(txtlabelHeaderManageFiled);
		String actualUniqueId = txtUniqueId.getAttribute("value");
		if (actualUniqueId.equals(modifiedUniqId)) {
			Log.info("Uniq Id mathched " + "Expected" + modifiedUniqId + " Found: " + actualUniqueId);
		} else {
			Log.error("Uniq Id does not mathched " + "Expected" + modifiedUniqId + " Found: " + actualUniqueId);
		}
		String actualFieldLabel = txtFieldLabel.getAttribute("value");
		if (actualFieldLabel.equals(FieldLabel)) {
			Log.info("Field Label mathched " + "Expected" + FieldLabel + " Found: " + actualFieldLabel);
		} else {
			Log.error("Field Label does not mathched " + "Expected" + FieldLabel + " Found: " + actualFieldLabel);
		}
		String actualFieldType = drpdwnFieldType.getAttribute("value");
		if (actualFieldType.equals(FieldType)) {
			Log.info("Field Type mathched " + "Expected" + FieldType + " Found: " + actualFieldType);
		} else {
			Log.error("Field Type does not mathched " + "Expected" + FieldType + " Found: " + actualFieldType);
		}
		String actualFieldSequence = txtFieldseq.getAttribute("value");
		if (actualFieldSequence.equals(FieldSequence)) {
			Log.info("Field Sequence mathched " + "Expected" + FieldSequence + " Found: " + actualFieldSequence);
		} else {
			Log.error("Field Sequence does not mathched " + "Expected" + FieldSequence + " Found: "
					+ actualFieldSequence);
		}
		String actualPlaceholderText = txtPlaceholder.getAttribute("value");
		if (actualPlaceholderText.equals(PlaceholderText)) {
			Log.info("Placeholder Text mathched " + "Expected" + PlaceholderText + " Found: " + actualPlaceholderText);
		} else {
			Log.error("Placeholder Text does not mathched " + "Expected" + PlaceholderText + " Found: "
					+ actualPlaceholderText);
		}
		String actualTooltip = txtTooltip.getAttribute("value");
		if (actualTooltip.equals(Tooltip)) {
			Log.info("Tooltip mathched " + "Expected" + Tooltip + " Found: " + actualTooltip);
		} else {
			Log.error("Tooltip does not mathched " + "Expected" + Tooltip + " Found: " + actualTooltip);
		}
		String actualFieldCharacterLimit = txtFieldCharacterLimit.getAttribute("value");
		if (actualFieldCharacterLimit.equals(FieldCharacterLimit)) {
			Log.info("Field Character Limit mathched " + "Expected" + FieldCharacterLimit + " Found: "
					+ actualFieldCharacterLimit);
		} else {
			Log.error("Field Character Limit does not mathched " + "Expected" + FieldCharacterLimit + " Found: "
					+ actualFieldCharacterLimit);
		}
		String actualAPIKey = txtapiKey.getAttribute("value");
		if (actualAPIKey.equals(APIKey)) {
			Log.info("API Key mathched " + "Expected" + APIKey + " Found: " + actualAPIKey);
		} else {
			Log.error("API Key does not mathched " + "Expected" + APIKey + " Found: " + actualAPIKey);
		}
		String actualRequestAPIKey = txtRequestApiKey.getAttribute("value");
		if (actualRequestAPIKey.equals(RequestAPIKey)) {
			Log.info("Request API Key mathched " + "Expected" + RequestAPIKey + " Found: " + actualRequestAPIKey);
		} else {
			Log.error("Request API Key does not mathched " + "Expected" + RequestAPIKey + " Found: "
					+ actualRequestAPIKey);
		}
		String actualResponseAPIKey = txtResponseApiKey.getAttribute("value");
		if (actualResponseAPIKey.equals(ResponseAPIKey)) {
			Log.info("Response API Key mathched " + "Expected" + ResponseAPIKey + " Found: " + actualResponseAPIKey);
		} else {
			Log.error("Response API Key does not mathched " + "Expected" + ResponseAPIKey + " Found: "
					+ actualResponseAPIKey);
		}
		String actualCSSClassName = txtCSSClassName.getAttribute("value");
		if (actualCSSClassName.equals(CSSClassName)) {
			Log.info("CSS Class Name mathched " + "Expected" + CSSClassName + " Found: " + actualCSSClassName);
		} else {
			Log.error(
					"CSS Class Name does not mathched " + "Expected" + CSSClassName + " Found: " + actualCSSClassName);
		}
		commonUtil.onClick(expandFieldValues);
		String actualDefaultValue = txtDefaultValue.getAttribute("value");
		if (actualDefaultValue.equals(DefaultValue)) {
			Log.info("Default Value mathched " + "Expected" + DefaultValue + " Found: " + actualDefaultValue);
		} else {
			Log.error("Default Value does not mathched " + "Expected" + DefaultValue + " Found: " + actualDefaultValue);
		}
		String actualPossibleValues = txtPossibleValue.getAttribute("value");
		if (actualPossibleValues.equals(PossibleValues)) {
			Log.info("Possible Values mathched " + "Expected" + PossibleValues + " Found: " + actualPossibleValues);
		} else {
			Log.error("Possible Values does not mathched " + "Expected" + PossibleValues + " Found: "
					+ actualPossibleValues);
		}
		commonUtil.onClick(expandFieldStyle);
		String actualBootstrapClassName = txtBootstrapClassName.getAttribute("value");
		if (actualBootstrapClassName.equals(BootstrapClassName)) {
			Log.info("Bootstrap Class Name mathched " + "Expected" + BootstrapClassName + " Found: "
					+ actualBootstrapClassName);
		} else {
			Log.error("Bootstrap Class Name does not mathched " + "Expected" + BootstrapClassName + " Found: "
					+ actualBootstrapClassName);
		}
		String actualCustomClassName = txtCustomClassName.getAttribute("value");
		if (actualCustomClassName.equals(CustomClassName)) {
			Log.info("Custom Class Name mathched " + "Expected" + CustomClassName + " Found: " + actualCustomClassName);
		} else {
			Log.error("Custom Class Name does not mathched " + "Expected" + CustomClassName + " Found: "
					+ actualCustomClassName);
		}
		commonUtil.onClick(expandAdditionalParameter);
		commonUtil.waitForElementToVisible(txtBootstrapClassName);
		String actualParameterName = txtParameterName.getAttribute("value");
		if (actualParameterName.equals(ParameterName)) {
			Log.info("Parameter Name mathched " + "Expected" + ParameterName + " Found: " + actualParameterName);
		} else {
			Log.error("Parameter Name does not mathched " + "Expected" + ParameterName + " Found: "
					+ actualParameterName);
		}
		String actualParameterValue = txtCustomClassName.getAttribute("value");
		if (actualParameterValue.equals(ParameterValue)) {
			Log.info("Parameter Value mathched " + "Expected" + ParameterValue + " Found: " + actualParameterValue);
		} else {
			Log.error("Parameter Value does not mathched " + "Expected" + ParameterValue + " Found: "
					+ actualParameterValue);
		}
	}

}
