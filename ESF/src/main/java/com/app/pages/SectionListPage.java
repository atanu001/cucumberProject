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

	@FindBy(xpath = "//a[@id='createButton']")
	public WebElement btnCreateSection;

	private CommonUtility commonUtil = new CommonUtility(DriverFactory.getDriver());

	public String getSectionListPageTitle() {
		return driver.getTitle();
	}

	public void clickOnCreateSectionBtn() {
		commonUtil.onClick(btnCreateSection);
	}

	// This method is used to click on manage of a section from the List
	public void openSection() {
		commonUtil.onClick(btnOption);
		commonUtil.onClick(optionManage);

	}
}
