package com.app.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.app.factory.DriverFactory;
import com.app.util.CommonUtility;

public class SectionListPage extends BasePage {

	public SectionListPage(WebDriver driver) {
		super(driver);
	}

	private CommonUtility commonUtil;
	private SectionManagePage sectionManagePage;
	private StepFieldListPage stepFieldListPage;

	@FindBy(xpath = "//span[text()='Application Sections']")
	public WebElement labelHeaderSectionListPage;

	@FindBy(xpath = "//a[@id='createButton']")
	private WebElement btnCreateSection;

	private String sectionName = null;

	public String getSectionListPageTitle() {
		return driver.getTitle();
	}

	/**
	 * This method will click on Create Section Button on Section List Page
	 * 
	 * @return the driver to the Manage Section Page
	 */
	public SectionManagePage clickOnCreateSectionBtn() {
		commonUtil = new CommonUtility(DriverFactory.getDriver());
		sectionManagePage = new SectionManagePage(DriverFactory.getDriver());
		commonUtil.onClick(btnCreateSection);
		commonUtil.waitForElementToVisible(sectionManagePage.labelHeaderManageSectionPage);
		return new SectionManagePage(driver);
	}

	/**
	 * This method is used to click on Manage option of a section from the List
	 * 
	 * @param sectiondetailssheetname
	 * @param rowno
	 * @return the Field List Page
	 */
	public StepFieldListPage clickOnManageSectionOption(String sectiondetailssheetname, int rowno) {
		commonUtil = new CommonUtility(DriverFactory.getDriver());
		stepFieldListPage = new StepFieldListPage(DriverFactory.getDriver());
		sectionName = ec.getCellData("Section_Details", "Modified Section Name", rowno);
		commonUtil.doSearch(sectionName);
		commonUtil.onClick(btnOption);
		commonUtil.onClick(optionManage);
		commonUtil.waitForElementToVisible(stepFieldListPage.txtLabelHeaderFieldListPage);
		return new StepFieldListPage(driver);

	}

	/**
	 * This method is used to click on edit option of a section from the List of a
	 * Step
	 * 
	 * @param sectiondetailssheetname
	 * @param rowno
	 * @return the Manage Section Page
	 */
	public SectionManagePage clickOnEditSectionoption(String sectiondetailssheetname, int rowno) {
		commonUtil = new CommonUtility(DriverFactory.getDriver());
		sectionName = ec.getCellData("Section_Details", "Modified Section Name", rowno);
		commonUtil.waitForElementToVisible(labelHeaderSectionListPage);
		commonUtil.doSearch(sectionName);
		commonUtil.onClick(btnOption);
		commonUtil.onClick(optionEdit);
		return new SectionManagePage(driver);
	}
}
