package com.app.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.app.factory.DriverFactory;
import com.app.util.CommonUtility;

public class ManageFieldPage extends BasePage {

	public ManageFieldPage(WebDriver driver) {
		super(driver);

	}

	private CommonUtility commonUtil = new CommonUtility(DriverFactory.getDriver());

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

	public void createField(String UniqueId, String FieldLabel, String FieldType, String FieldSequence,
			String PlaceholderText, String Tooltip, String FieldCharacterLimit, String APIKey, String RequestAPIKey,
			String ResponseAPIKey, String CSSClassName, String DefaultValue, String PossibleValues,
			String BootstrapClassName, String CustomClassName, String ParameterName, String ParameterValue) {

		txtFieldLabel.sendKeys(FieldLabel);
		commonUtil.onClick(txtUniqueId);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		commonUtil.acceptJavaScripAlert();
		String[] testData1 = { UniqueId, FieldType, FieldSequence, PlaceholderText, Tooltip, FieldCharacterLimit,
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
		commonUtil.waitForElementToVisible(parameterName);
		String[] testData4 = { ParameterName, ParameterValue };
		WebElement[] locator4 = { parameterName, parameterValue };
		commonUtil.typeIn(locator4, testData4);
		commonUtil.scrollDownToBottomPage();
		commonUtil.waitForElementToVisible(btnSave);
		commonUtil.onClick(btnSave);
	}

}
