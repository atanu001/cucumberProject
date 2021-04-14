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
	 */
	public void clickOnCreateSectionBtn() {
		commonUtil = new CommonUtility(DriverFactory.getDriver());
		commonUtil.onClick(btnCreateSection);
	}

	/**
	 * This method is used to open a section from the List of a Step
	 * 
	 * @param sectiondetailssheetname
	 * @param rowno
	 */
	public void openSection(String sectiondetailssheetname, String rowno) {
		commonUtil = new CommonUtility(DriverFactory.getDriver());
		sectionName = ec.getCellData("Section_Details", "Modified Section Name", 0);
		commonUtil.doSearch(sectionName);
		commonUtil.onClick(btnOption);
		commonUtil.onClick(optionManage);

	}
}
