package com.app.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.app.factory.DriverFactory;
import com.app.util.CommonUtility;

public class StepListPage extends BasePage {

	public StepListPage(WebDriver driver) {
		super(driver);
	}

	private CommonUtility commonUtil;
	private StepManagePage stepManagePage;

	@FindBy(xpath = "//a[@id='createButton']//span[text()='New Step']")
	public WebElement createStepBtn;

	@FindBy(xpath = "//div[@class='w-100 row p-2 mb-3']//span[text()='Application Steps']")
	public WebElement stepListPageHeading;

	@FindBy(xpath = "//div[@aria-labelledby='dropdownMenuButton']//a[text()='Manage Sections']")
	public WebElement btnManageSection;

	private String stepName = null;

	/**
	 * This method is used to click on Create Step button on Step List Page
	 * 
	 * @return the Manage Step Page
	 */
	public StepManagePage clickOnAddStepBtn() {
		commonUtil = new CommonUtility(DriverFactory.getDriver());
		stepManagePage = new StepManagePage(DriverFactory.getDriver());
		commonUtil.onClick(createStepBtn);
		commonUtil.waitForElementToVisible(stepManagePage.labelHeaderManageStepPage);
		return new StepManagePage(driver);

	}

	public String getStepListPageTitle() {
		return driver.getTitle();
	}

	/**
	 * This method will click on Manage Section option of a Step
	 * 
	 * @param stepdetailssheetname
	 * @param rowno
	 * @return the Section List Page
	 */
	public SectionListPage clickOnManageSectionOption(String stepdetailssheetname, int rowno) {
		commonUtil = new CommonUtility(DriverFactory.getDriver());
		stepName = ec.getCellData("Step_Details", "Modified Step Name", rowno);
		commonUtil.doSearch(stepName);
		commonUtil.onClick(btnOption);
		commonUtil.onClick(btnManageSection);
		return new SectionListPage(driver);
	}

	/**
	 * This method will click on Edit option of a Step
	 * 
	 * @param stepdetailssheetname
	 * @param rowno
	 * @return the driver to the Manage Step Page
	 */
	public StepManagePage editStep(String stepdetailssheetname, int rowno) {
		commonUtil = new CommonUtility(DriverFactory.getDriver());
		stepName = ec.getCellData("Step_Details", "Modified Step Name", rowno);
		commonUtil.waitForElementToVisible(stepListPageHeading);
		commonUtil.doSearch(stepName);
		commonUtil.onClick(btnOption);
		commonUtil.onClick(optionEdit);
		return new StepManagePage(driver);
	}

}
