package com.app.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.app.factory.DriverFactory;
import com.app.util.CommonUtility;

public class ManageStepPage extends BasePage {

	public ManageStepPage(WebDriver driver) {
		super(driver);
	}

	private CommonUtility commonUtil = new CommonUtility(DriverFactory.getDriver());
	private BasePage header = new BasePage(DriverFactory.getDriver());

	@FindBy(id = "stepName")
	WebElement txtStepName;

	@FindBy(id = "stepapiurl")
	WebElement stepApiUrl;

	@FindBy(xpath = "//label[text()='Display this step as a Modal?']//preceding-sibling::input[@id='displayAsModal']")
	WebElement chkboxDisplayAsModal;

	@FindBy(xpath = "//label[text()='Will the modal be dismissable?']//preceding-sibling::input[@id='dismissModal']")
	WebElement chkboxDismissModal;

	@FindBy(xpath = "//label[text()='Is this step conditional?']//preceding-sibling::input[@id='conditional']")
	WebElement chkboxConditional;

	public void createStep(String stepName, String stepApiUrlName, String bootstrapClassName, String customClassName,
			String parameterName, String parameterValue, String device_Type, String templateId) {

		String[] testDate1 = { stepName, stepApiUrlName, bootstrapClassName, customClassName };
		WebElement[] locator1 = { txtStepName, stepApiUrl, header.bootstrapClassName, header.customClassName };
		commonUtil.typeIn(locator1, testDate1);
		commonUtil.onClick(chkboxDisplayAsModal);
		commonUtil.onClick(chkboxDismissModal);
		commonUtil.onClick(header.btnConfigAddParam);
		commonUtil.waitForElementToVisible(header.parameterName);
		// Thread.sleep(2000);
		String[] testDate2 = { parameterName, parameterValue };
		WebElement[] locator2 = { header.parameterName, header.parameterValue };
		commonUtil.typeIn(locator2, testDate2);
		commonUtil.onClick(header.btnConfigureTemplate);
		String[] testDate3 = { device_Type, templateId };
		WebElement[] locator3 = { header.deviceType, header.templateId };
		commonUtil.typeIn(locator3, testDate3);
		commonUtil.scrollDownToBottomPage();
		commonUtil.onClick(header.btnSave);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
