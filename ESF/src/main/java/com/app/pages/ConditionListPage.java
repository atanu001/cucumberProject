package com.app.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.app.factory.DriverFactory;
import com.app.util.CommonUtility;

public class ConditionListPage extends BasePage {

	public ConditionListPage(WebDriver driver) {
		super(driver);
	}

	private CommonUtility commonUtil;
	private ConditionManagePage conditionManagePage;

	@FindBy(xpath = "//span[text()='Application Conditions']")
	public WebElement labelHeaderConditionListPage;

	@FindBy(xpath = "//span[text()='New Condition']")
	private WebElement btnAddCondition;

	/**
	 * This method will click on New Condition Button on Condition List Page
	 * 
	 * @return Add Condition Page
	 */
	public ConditionManagePage clickOnAddNewConditionBtn() {
		commonUtil = new CommonUtility(DriverFactory.getDriver());
		conditionManagePage = new ConditionManagePage(DriverFactory.getDriver());
		commonUtil.waitForElementToVisible(labelHeaderConditionListPage);
		commonUtil.onClick(btnAddCondition);
		commonUtil.waitForElementToVisible(conditionManagePage.labelHeaderconditionManagePage);
		return new ConditionManagePage(driver);
	}

	/**
	 * This method will click on edit option of a Condition
	 * 
	 * @param rowno
	 * @param conditiondetailssheetname
	 * 
	 * @return Manage Condition Page
	 */
	public ConditionManagePage clickOnEditConditionOption(String conditiondetailssheetname, int rowno) {
		commonUtil = new CommonUtility(DriverFactory.getDriver());
		conditionManagePage = new ConditionManagePage(DriverFactory.getDriver());
		commonUtil.waitForElementToVisible(labelHeaderConditionListPage);
		String ConditionName = ec.getCellData("Condition_Details", "Modified Condition Name", rowno);
		commonUtil.doSearch(ConditionName);
		commonUtil.onClick(btnOption);
		commonUtil.onClick(optionEdit);
		commonUtil.waitForElementToVisible(conditionManagePage.labelHeaderconditionManagePage);
		return new ConditionManagePage(driver);
	}
}
