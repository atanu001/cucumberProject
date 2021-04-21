package com.app.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.app.factory.DriverFactory;
import com.app.util.CommonUtility;

public class ValidationMessageListPage extends BasePage {

	public ValidationMessageListPage(WebDriver driver) {
		super(driver);
	}

	private CommonUtility commonUtil;
	private ValidationMessageManagePage validationMessageManagePage;

	@FindBy(xpath = "//span[text()='Application Validation Messages']")
	public WebElement labelTxtHeaderValidationMsgListPage;

	@FindBy(xpath = "//span[text()='New Validation Message']")
	private WebElement btnAddNewValidationMsg;

	private String ValidationName = null;

	/**
	 * This method is used to click on Add New Validation Message button on
	 * Validation Message List Page
	 * 
	 * @return the Validation Message Manage Page
	 */
	public ValidationMessageManagePage clickOnAddNewValidationMsg() {
		commonUtil = new CommonUtility(DriverFactory.getDriver());
		validationMessageManagePage = new ValidationMessageManagePage(DriverFactory.getDriver());
		commonUtil.onClick(btnAddNewValidationMsg);
		commonUtil.waitForElementToVisible(validationMessageManagePage.labelTxtHeaderValidationMsgManagePage);
		return new ValidationMessageManagePage(driver);
	}

	/**
	 * This method is used to click on Edit option of a Validation Message
	 * Validation Message List Page
	 * 
	 * @return the Validation Message Manage Page
	 */
	public ValidationMessageManagePage clickOnValidationMsgEditOption(String validationMessageSheetName, int rowno) {
		commonUtil = new CommonUtility(DriverFactory.getDriver());
		validationMessageManagePage = new ValidationMessageManagePage(DriverFactory.getDriver());
		ValidationName = ec.getCellData("Validation_Message_Details", "Modified Name", rowno);
		commonUtil.doSearch(ValidationName);
		commonUtil.onClick(btnOption);
		commonUtil.onClick(optionEdit);
		commonUtil.waitForElementToVisible(validationMessageManagePage.labelTxtHeaderValidationMsgManagePage);
		return new ValidationMessageManagePage(driver);
	}

}
