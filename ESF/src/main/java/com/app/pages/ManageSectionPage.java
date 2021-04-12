package com.app.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.app.factory.DriverFactory;
import com.app.util.CommonUtility;

public class ManageSectionPage extends BasePage {

	public ManageSectionPage(WebDriver driver) {
		super(driver);
	}

	CommonUtility commonUtil = new CommonUtility(DriverFactory.getDriver());

	@FindBy(xpath = "//h2[text()='Manage Section']")
	public WebElement headerTitleManageSectionPage;

	@FindBy(xpath = "//input[@id='sectionName']")
	public WebElement txtSectionName;

	@FindBy(xpath = "//input[@id='sequenceNumber']")
	public WebElement drpdwnSequenceNumber;

	@FindBy(xpath = "//input[@id='sectionClassName']")
	public WebElement txtSectionClassName;

	@FindBy(xpath = "//select[@id='sectionNumber']")
	public WebElement drpdwnSectionNum;

	@FindBy(xpath = "//input[@id='sectionBootstrapClassName']")
	public WebElement txtSectionBootstrapClassName;

	@FindBy(xpath = "//input[@id='sectionCustomClassName']")
	public WebElement txtSectionCustomClassName;

	public String headerTitleManageSection() {
		return headerTitleManageSectionPage.getText();
	}

	public void createSection(String SectionNumber, String SectionName, String SequenceNumber, String SectionClassName,
			String BootstrapClassName, String CustomClassName, String ParameterName, String ParameterValue) {

		String[] testData1 = { SectionNumber, SectionName, SequenceNumber, SectionClassName, BootstrapClassName,
				CustomClassName };
		WebElement[] locator1 = { drpdwnSectionNum, txtSectionName, drpdwnSequenceNumber, txtSectionClassName,
				txtSectionBootstrapClassName, txtSectionCustomClassName };
		commonUtil.typeIn(locator1, testData1);
		// commonUtil.scrollDownToVisibleElement(btnConfigAddParam);
		// commonUtil.waitForElementToVisible(btnConfigAddParam);
		commonUtil.onClick(btnConfigAddParam);
		// commonUtil.scrollDownToBottomPage();
		String[] testData2 = { ParameterName, ParameterValue };
		WebElement[] locator2 = { parameterName, parameterValue };
		commonUtil.typeIn(locator2, testData2);
		commonUtil.scrollDownToVisibleElement(btnSave);
		commonUtil.onClick(btnSave);

	}

}
