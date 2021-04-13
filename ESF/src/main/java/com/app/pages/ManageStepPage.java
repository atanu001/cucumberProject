package com.app.pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.app.factory.DriverFactory;
import com.app.util.CommonUtility;
import com.app.util.Log;

public class ManageStepPage extends BasePage {

	public ManageStepPage(WebDriver driver) {
		super(driver);
	}

	private CommonUtility commonUtil = new CommonUtility(DriverFactory.getDriver());
	private StepListPage stepListPage = new StepListPage(DriverFactory.getDriver());

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

	private String stepName = null;
	private String stepApiUrlName = null;
	private String bootstrapClassName = null;
	private String customClassName = null;
	private String parameterName = null;
	private String parameterValue = null;
	private String device_Type = null;
	private String templateId = null;
	private String modifiedStepName = null;

	/**
	 * This method will create a step with the data from Excel Sheet
	 * 
	 * @param stepdetailssheetname
	 * @param rowno
	 */
	public void createStep(String stepdetailssheetname, String rowno) {
		stepName = ec.getCellData("Step_Details", "Application Step Name", 0);
		stepApiUrlName = ec.getCellData("Step_Details", "Step API URL", 0);
		bootstrapClassName = ec.getCellData("Step_Details", "Bootstrap Class Name", 0);
		customClassName = ec.getCellData("Step_Details", "Custom Class Name", 0);
		parameterName = ec.getCellData("Step_Details", "Parameter Name", 0);
		parameterValue = ec.getCellData("Step_Details", "Parameter Value", 0);
		device_Type = ec.getCellData("Step_Details", "Device Type", 0);
		templateId = ec.getCellData("Step_Details", "Template Id", 0);
		int randomNum = commonUtil.generateRandomNumber();
		modifiedStepName = stepName + "_" + randomNum;
		try {
			ec.writeCellData("Step_Details", "Modified Step Name", 1, modifiedStepName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] testDate1 = { modifiedStepName, stepApiUrlName, bootstrapClassName, customClassName };
		WebElement[] locator1 = { txtStepName, stepApiUrl, txtBootstrapClassName, txtCustomClassName };
		commonUtil.typeIn(locator1, testDate1);
		commonUtil.onClick(chkboxDisplayAsModal);
		commonUtil.onClick(chkboxDismissModal);
		commonUtil.onClick(btnConfigAddParam);
		commonUtil.waitForElementToVisible(txtParameterName);
		// Thread.sleep(2000);
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
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	/**
	 * This method will verify the data inside a Step with Excel Sheet data
	 * 
	 * @param stepdetailssheetname
	 * @param rowno
	 */
	public void verifyStep(String stepdetailssheetname, String rowno) {
		commonUtil.waitForElementToVisible(stepListPage.stepListPageHeading);
		commonUtil.doSearch(modifiedStepName);
		commonUtil.onClick(btnOption);
		commonUtil.onClick(optionEdit);
		commonUtil.waitForElementToVisible(labelHeaderManageStepPage);
		String actualStepName = txtStepName.getAttribute("value");
		if (actualStepName.equals(modifiedStepName)) {
			Log.info("Step Name matched: " + "Expected: " + modifiedStepName + " Found: " + actualStepName);
		} else {
			Log.error("Step Name do not matched: " + "Expected: " + modifiedStepName + " Found: " + actualStepName);
		}
		String actualStepApiUrl = stepApiUrl.getAttribute("value");
		if (actualStepApiUrl.equals(stepApiUrlName)) {
			Log.info("Step Api Url matched: " + "Expected: " + stepApiUrlName + " Found: " + actualStepApiUrl);
		} else {
			Log.error("Step Api Url do not matched: " + "Expected: " + stepApiUrlName + " Found: " + actualStepApiUrl);
		}
		if (chkboxDisplayAsModal.isSelected()) {
			Log.info("Display this step as a Modal? Checkbox is selected for the Step: " + modifiedStepName);
		} else {
			Log.error("Display this step as a Modal? Checkbox is not selected for the Step: " + modifiedStepName);
		}
		if (chkboxDismissModal.isSelected()) {
			Log.info("Will the modal be dismissable? Checkbox is selected for the Step: " + modifiedStepName);
		} else {
			Log.error("Will the modal be dismissable? Checkbox is not selected for the Step: " + modifiedStepName);
		}
		if (!chkboxConditional.isSelected()) {
			Log.info("Is this step conditional? Checkbox is not selected for the Step: " + modifiedStepName);
		} else {
			Log.error("Will the modal be dismissable? Checkbox is selected for the Step: " + modifiedStepName);
		}
		String actualBootstrapClassName = txtBootstrapClassName.getAttribute("value");
		if (actualBootstrapClassName.equals(bootstrapClassName)) {
			Log.info("Bootstrap Class Name is matched for the Step: " + modifiedStepName + "Expected: "
					+ bootstrapClassName + " Found: " + actualBootstrapClassName);
		} else {
			Log.error("Bootstrap Class Name is not matched for the Step: " + modifiedStepName + "Expected: "
					+ bootstrapClassName + " Found: " + actualBootstrapClassName);
		}
		String actualCustomClassName = txtCustomClassName.getAttribute("value");
		if (actualCustomClassName.equals(customClassName)) {
			Log.info("Custom Class Name is matched for the Step: " + modifiedStepName + "Expected: " + customClassName
					+ " Found: " + actualCustomClassName);
		} else {
			Log.error("Custom Class Name is not matched for the Step: " + modifiedStepName + "Expected: "
					+ customClassName + " Found: " + actualCustomClassName);
		}
		String actualParameterName = txtParameterName.getAttribute("value");
		if (actualParameterName.equals(parameterName)) {
			Log.info("Parameter Name is matched for the Step: " + modifiedStepName + "Expected: " + parameterName
					+ " Found: " + actualParameterName);
		} else {
			Log.error("Parameter Name is not matched for the Step: " + modifiedStepName + "Expected: " + parameterName
					+ " Found: " + actualParameterName);
		}
		String actualParameterValue = txtParameterValue.getAttribute("value");
		if (actualParameterValue.equals(parameterValue)) {
			Log.info("Parameter Value is matched for the Step: " + modifiedStepName + "Expected: " + parameterValue
					+ " Found: " + actualParameterValue);
		} else {
			Log.error("Parameter Value is not matched for the Step: " + modifiedStepName + "Expected: " + parameterValue
					+ " Found: " + actualParameterValue);
		}
		String actualDeviceType = drpdwnDeviceType.getAttribute("value");
		if (actualDeviceType.equals(device_Type)) {
			Log.info("Device type is matched for the Step: " + modifiedStepName + "Expected: " + device_Type
					+ " Found: " + actualDeviceType);
		} else {
			Log.error("Device type is not matched for the Step: " + modifiedStepName + "Expected: " + device_Type
					+ " Found: " + actualDeviceType);
		}
		String actualTemplateId = txtTemplateId.getAttribute("value");
		if (actualTemplateId.equals(templateId)) {
			Log.info("TemplateId is matched for the Step: " + modifiedStepName + "Expected: " + templateId + " Found: "
					+ actualTemplateId);
		} else {
			Log.error("TemplateId is not matched for the Step: " + modifiedStepName + "Expected: " + templateId
					+ " Found: " + actualTemplateId);
		}
	}

}
