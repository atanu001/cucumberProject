package com.app.pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

import com.app.factory.DriverFactory;
import com.app.util.CommonUtility;

public class StepManagePage extends BasePage {

	public StepManagePage(WebDriver driver) {
		super(driver);
	}

	private CommonUtility commonUtil;
	private SoftAssert softassert;

	@FindBy(xpath = "//h2[text()='Manage Step']")
	public WebElement labelHeaderManageStepPage;

	@FindBy(id = "stepName")
	private WebElement txtStepName;

	@FindBy(id = "stepapiurl")
	private WebElement stepApiUrl;

	@FindBy(xpath = "//label[text()='Display this step as a Modal?']//preceding-sibling::input[@id='displayAsModal']")
	private WebElement chkboxDisplayAsModal;

	@FindBy(xpath = "//label[text()='Will the modal be dismissable?']//preceding-sibling::input[@id='dismissModal']")
	private WebElement chkboxDismissModal;

	@FindBy(xpath = "//label[text()='Is this step conditional?']//preceding-sibling::input[@id='conditional']")
	private WebElement chkboxConditional;

	@FindBy(xpath = "//button[@data-id='displayCondition']")
	private WebElement drpdwnStepCondition;

	@FindBy(xpath = "//input[@aria-controls='bs-select-1']")
	private WebElement SearchStepCondition;

	@FindBy(xpath = "//button[@data-id='eventList']")
	private WebElement drpdwnStepEvent;

	@FindBy(xpath = "//input[@aria-controls='bs-select-2']")
	private WebElement SearchStepEvent;

	String searchDrpdwnSelect = "//span[contains(text(),'%s')]";

	private static String stepName = null;
	private static String stepApiUrlName = null;
	private static String bootstrapClassName = null;
	private static String customClassName = null;
	private static String parameterName = null;
	private static String parameterValue = null;
	private static String device_Type = null;
	private static String templateId = null;
	private static String modifiedStepName = null;
	private static String StepCondition = null;
	private static String StepEvent = null;

	/**
	 * This method will create a step with the data from Excel Sheet
	 * 
	 * @param stepdetailssheetname
	 * @param rowno
	 */
	public StepListPage createStep(String stepdetailssheetname, int rowno) {
		commonUtil = new CommonUtility(DriverFactory.getDriver());
		stepName = ec.getCellData("Step_Details", "Application Step Name", rowno);
		stepApiUrlName = ec.getCellData("Step_Details", "Step API URL", rowno);
		StepCondition = ec.getCellData("Step_Details", "Step Condition", rowno);
		StepEvent = ec.getCellData("Step_Details", "Step Event", rowno);
		bootstrapClassName = ec.getCellData("Step_Details", "Bootstrap Class Name", rowno);
		customClassName = ec.getCellData("Step_Details", "Custom Class Name", rowno);
		parameterName = ec.getCellData("Step_Details", "Parameter Name", rowno);
		parameterValue = ec.getCellData("Step_Details", "Parameter Value", rowno);
		device_Type = ec.getCellData("Step_Details", "Device Type", rowno);
		templateId = ec.getCellData("Step_Details", "Template Id", rowno);
		int randomNum = commonUtil.generateRandomNumber();
		modifiedStepName = stepName + "_" + randomNum;
		try {
			ec.writeCellData("Step_Details", "Modified Step Name", rowno, modifiedStepName);
			ec.writeCellData("Workflow_Details", "Step Name", rowno, modifiedStepName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] testDate1 = { modifiedStepName, stepApiUrlName, bootstrapClassName, customClassName };
		WebElement[] locator1 = { txtStepName, stepApiUrl, txtBootstrapClassName, txtCustomClassName };
		commonUtil.typeIn(locator1, testDate1);
		commonUtil.onClick(chkboxDisplayAsModal);
		commonUtil.onClick(chkboxDismissModal);
		commonUtil.onClick(chkboxConditional);
		// select step condition
		commonUtil.onClick(drpdwnStepCondition);
		drpdwnStepCondition.sendKeys(StepCondition);
		commonUtil.onClick(commonUtil.searchDropdown(searchDrpdwnSelect, StepCondition));
		// select step event
		commonUtil.onClick(drpdwnStepEvent);
		drpdwnStepEvent.sendKeys(StepEvent);
		commonUtil.onClick(commonUtil.searchDropdown(searchDrpdwnSelect, StepEvent));
		commonUtil.onClick(btnConfigAddParam);
		commonUtil.waitForElementToVisible(txtParameterName);
		String[] testDate2 = { parameterName, parameterValue };
		WebElement[] locator2 = { txtParameterName, txtParameterValue };
		commonUtil.typeIn(locator2, testDate2);
		commonUtil.onClick(btnConfigureTemplate);
		commonUtil.waitForElementToVisible(drpdwnDeviceType);
		String[] testDate3 = { device_Type, templateId };
		WebElement[] locator3 = { drpdwnDeviceType, txtTemplateId };
		commonUtil.typeIn(locator3, testDate3);
		commonUtil.scrollDownToBottomPage();
		commonUtil.onClick(btnSave);
		return new StepListPage(driver);

	}

	/**
	 * This method will verify the data inside a Step with Excel Sheet data
	 * 
	 * @param stepdetailssheetname
	 * @param rowno
	 */
	public void verifyStep() {
		commonUtil = new CommonUtility(DriverFactory.getDriver());
		softassert = new SoftAssert();
		commonUtil.waitForElementToVisible(labelHeaderManageStepPage);
		String actualStepName = commonUtil.getText(txtStepName);
		String actualStepApiUrl = commonUtil.getText(stepApiUrl);
		softassert.assertTrue(chkboxDisplayAsModal.isSelected());
		softassert.assertTrue(chkboxDismissModal.isSelected());
		softassert.assertTrue(chkboxConditional.isSelected());
		String actualBootstrapClassName = commonUtil.getText(txtBootstrapClassName);
		String actualCustomClassName = commonUtil.getText(txtCustomClassName);
		String actualStepCondition = commonUtil.getText(drpdwnStepCondition);
		String actualStepEvent = commonUtil.getText(drpdwnStepEvent);
		String actualParameterName = commonUtil.getText(txtParameterName);
		String actualParameterValue = commonUtil.getText(txtParameterValue);
		Select selectDeviceType = new Select(drpdwnDeviceType);
		WebElement eleDeviceType = selectDeviceType.getFirstSelectedOption();
		String actualDeviceType = commonUtil.getText(eleDeviceType);
		Select selectTemplateId = new Select(txtTemplateId);
		WebElement eleTemplateId = selectTemplateId.getFirstSelectedOption();
		String actualTemplateId = commonUtil.getText(eleTemplateId);
		String[] actualData = { actualStepName, actualStepApiUrl, actualBootstrapClassName, actualCustomClassName,
				actualStepCondition, actualStepEvent, actualParameterName, actualParameterValue, actualDeviceType,
				actualTemplateId };
		String[] expectedData = { modifiedStepName, stepApiUrlName, bootstrapClassName, customClassName, StepCondition,
				StepEvent, parameterName, parameterValue, device_Type, templateId };
		commonUtil.softAssert(actualData, expectedData, softassert);
		softassert.assertAll();
	}

}
