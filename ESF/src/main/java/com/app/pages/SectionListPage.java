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
	public ManageSectionPage clickOnCreateSectionBtn() {
		commonUtil = new CommonUtility(DriverFactory.getDriver());
		commonUtil.onClick(btnCreateSection);
		return new ManageSectionPage(driver);
	}

	/**
	 * This method is used to click on Manage option of a section from the List
	 * 
	 * @param sectiondetailssheetname
	 * @param rowno
	 * @return the driver to Field List Page
	 */
	public FieldListPage openSection(String sectiondetailssheetname, String rowno) {
		commonUtil = new CommonUtility(DriverFactory.getDriver());
		sectionName = ec.getCellData("Section_Details", "Modified Section Name", 0);
		commonUtil.doSearch(sectionName);
		commonUtil.onClick(btnOption);
		commonUtil.onClick(optionManage);
		return new FieldListPage(driver);

	}

	/**
	 * This method is used to edit a section from the List of a Step
	 * 
	 * @param sectiondetailssheetname
	 * @param rowno
	 * @return the driver to the Manage Section Page
	 */
	public ManageSectionPage editSection(String sectiondetailssheetname, String rowno) {
		commonUtil = new CommonUtility(DriverFactory.getDriver());
		sectionName = ec.getCellData("Section_Details", "Modified Section Name", 0);
		commonUtil.waitForElementToVisible(labelHeaderSectionListPage);
		commonUtil.doSearch(sectionName);
		commonUtil.onClick(btnOption);
		commonUtil.onClick(optionEdit);
		return new ManageSectionPage(driver);
	}
}
