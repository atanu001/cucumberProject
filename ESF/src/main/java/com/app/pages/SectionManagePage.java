package com.app.pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;

import com.app.factory.DriverFactory;
import com.app.util.CommonUtility;

public class SectionManagePage extends BasePage {

	public SectionManagePage(WebDriver driver) {
		super(driver);
	}

	private CommonUtility commonUtil;
	private SoftAssert softassert = null;

	@FindBy(xpath = "//h2[text()='Manage Section']")
	public WebElement labelHeaderManageSectionPage;

	@FindBy(xpath = "//input[@id='sectionName']")
	private WebElement txtSectionName;

	@FindBy(xpath = "//input[@id='sequenceNumber']")
	private WebElement drpdwnSequenceNumber;

	@FindBy(xpath = "//input[@id='sectionClassName']")
	private WebElement txtSectionClassName;

	@FindBy(xpath = "//select[@id='sectionNumber']")
	private WebElement drpdwnSectionNum;

	@FindBy(xpath = "//input[@id='sectionBootstrapClassName']")
	private WebElement txtSectionBootstrapClassName;

	@FindBy(xpath = "//input[@id='sectionCustomClassName']")
	private WebElement txtSectionCustomClassName;

	private static String SectionNumber = null;
	private static String SectionName = null;
	private static String SequenceNumber = null;
	private static String SectionClassName = null;
	private static String BootstrapClassName = null;
	private static String CustomClassName = null;
	private static String ParameterName = null;
	private static String ParameterValue = null;
	private static String modifiedSectionName = null;

	/**
	 * This will return label Header of Manage Section page
	 * 
	 * @return String
	 */
	public String labelHeaderManageSection() {
		return labelHeaderManageSectionPage.getText();
	}

	/**
	 * This method will create section with data from excel sheet
	 * 
	 * @param sectiondetailssheetname
	 * @param rowno
	 * @return the driver to the Section List Page
	 */
	public SectionListPage createSection(String sectiondetailssheetname, int rowno) {
		commonUtil = new CommonUtility(DriverFactory.getDriver());
		SectionNumber = ec.getCellData("Section_Details", "Section Number", rowno);
		SectionName = ec.getCellData("Section_Details", "Section Name", rowno);
		SequenceNumber = ec.getCellData("Section_Details", "Sequence Number", rowno);
		SectionClassName = ec.getCellData("Section_Details", "Section Class Name", rowno);
		BootstrapClassName = ec.getCellData("Section_Details", "Bootstrap Class Name", rowno);
		CustomClassName = ec.getCellData("Section_Details", "Custom Class Name", rowno);
		ParameterName = ec.getCellData("Section_Details", "Parameter Name", rowno);
		ParameterValue = ec.getCellData("Section_Details", "Parameter Value", rowno);
		int randomNum = commonUtil.generateRandomNumber();
		modifiedSectionName = SectionName + "_" + randomNum;
		try {
			ec.writeCellData("Section_Details", "Modified Section Name", rowno, modifiedSectionName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] testData1 = { SectionNumber, modifiedSectionName, SequenceNumber, SectionClassName, BootstrapClassName,
				CustomClassName };
		WebElement[] locator1 = { drpdwnSectionNum, txtSectionName, drpdwnSequenceNumber, txtSectionClassName,
				txtSectionBootstrapClassName, txtSectionCustomClassName };
		commonUtil.typeIn(locator1, testData1);
		String[] testData2 = { ParameterName, ParameterValue };
		WebElement[] locator2 = { txtParameterName, txtParameterValue };
		commonUtil.onClick(btnConfigAddParam);
		commonUtil.waitForElementToVisible(txtParameterName);
		commonUtil.typeIn(locator2, testData2);
		commonUtil.scrollDownToVisibleElement(btnSave);
		commonUtil.onClick(btnSave);
		return new SectionListPage(driver);
	}

	/**
	 * This method will verify the data in a section with excel sheet data
	 * 
	 * @param sectiondetailssheetname
	 * @param rowno
	 */
	public void verifySection() {
		commonUtil = new CommonUtility(DriverFactory.getDriver());
		softassert = new SoftAssert();
		commonUtil.waitForElementToVisible(labelHeaderManageSectionPage);
		String actualSectionNumber = drpdwnSectionNum.getAttribute("value");
		String actualSectionName = txtSectionName.getAttribute("value");
		String actualSequenceNumber = drpdwnSequenceNumber.getAttribute("value");
		String actualSectionClassName = txtSectionClassName.getAttribute("value");
		String actualBootstrapClassName = txtSectionBootstrapClassName.getAttribute("value");
		String actualCustomClassName = txtSectionCustomClassName.getAttribute("value");
		String actualParameterName = txtParameterName.getAttribute("value");
		String actualParameterValue = txtParameterValue.getAttribute("value");
		String[] actualData = { actualSectionNumber, actualSectionName, actualSequenceNumber, actualSectionClassName,
				actualBootstrapClassName, actualCustomClassName, actualParameterName, actualParameterValue };
		String[] expectedData = { SectionNumber, modifiedSectionName, SequenceNumber, SectionClassName,
				BootstrapClassName, CustomClassName, ParameterName, ParameterValue };
		commonUtil.softAssert(actualData, expectedData, softassert);
		softassert.assertAll();

	}

}
