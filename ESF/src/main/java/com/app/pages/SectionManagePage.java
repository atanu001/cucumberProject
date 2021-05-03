package com.app.pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
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

	@FindBy(xpath = "//button[@data-id='events']")
	private WebElement drpdwnEvents;

	@FindBy(xpath = "//input[@aria-controls='bs-select-1']")
	private WebElement searchEvents;

	@FindBy(xpath = "//button[@data-id='sectionDisplayCondition']")
	private WebElement drpdwnDisplayCondition;

	@FindBy(xpath = "//input[@aria-controls='bs-select-2']")
	private WebElement searchDisplayCondition;

	@FindBy(xpath = "//button[@data-id='sectioEditableCondition']")
	private WebElement drpdwnEditableCondition;

	@FindBy(xpath = "//input[@aria-controls='bs-select-3']")
	private WebElement searchEditableCondition;

	@FindBy(xpath = "//button[@data-id='sectioMandatoryCondition']")
	private WebElement drpdwnMandatoryCondition;

	@FindBy(xpath = "//input[@aria-controls='bs-select-4']")
	private WebElement searchMandatoryCondition;

	private String searchDrpdwnSelect = "//span[contains(text(),'%s')]";

	private static String SectionNumber = null;
	private static String SectionName = null;
	private static String SequenceNumber = null;
	private static String SectionClassName = null;
	private static String BootstrapClassName = null;
	private static String CustomClassName = null;
	private static String EventsName = null;
	private static String DisplayConditionName = null;
	private static String EditableConditionName = null;
	private static String MandatoryConditionName = null;
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
		EventsName = ec.getCellData("Section_Details", "Section Event", rowno);
		DisplayConditionName = ec.getCellData("Section_Details", "Display Condition", rowno);
		EditableConditionName = ec.getCellData("Section_Details", "Editable Condition", rowno);
		MandatoryConditionName = ec.getCellData("Section_Details", "Mandatory Condition", rowno);
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
		if (!EventsName.isEmpty()) {
			commonUtil.onClick(drpdwnEvents);
			searchEvents.sendKeys(EventsName);
			commonUtil.onClick(commonUtil.searchDropdown(searchDrpdwnSelect, EventsName));
		}
		if (!DisplayConditionName.isEmpty()) {
			commonUtil.onClick(drpdwnDisplayCondition);
			searchDisplayCondition.sendKeys(DisplayConditionName);
			commonUtil.onClick(commonUtil.searchDropdown(searchDrpdwnSelect, DisplayConditionName));
		}
		if (!EditableConditionName.isEmpty()) {
			commonUtil.onClick(drpdwnEditableCondition);
			searchEditableCondition.sendKeys(EditableConditionName);
			commonUtil.onClick(commonUtil.searchDropdown(searchDrpdwnSelect, EditableConditionName));
		}
		if (!MandatoryConditionName.isEmpty()) {
			commonUtil.onClick(drpdwnMandatoryCondition);
			searchMandatoryCondition.sendKeys(MandatoryConditionName);
			commonUtil.onClick(commonUtil.searchDropdown(searchDrpdwnSelect, MandatoryConditionName));
		}
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
		Select s1 = new Select(drpdwnSectionNum);
		WebElement sectionNumber = s1.getFirstSelectedOption();
		String actualSectionNumber = commonUtil.getText(sectionNumber);
		String actualSectionName = commonUtil.getText(txtSectionName);
		String actualSequenceNumber = commonUtil.getText(drpdwnSequenceNumber);
		String actualSectionClassName = commonUtil.getText(txtSectionClassName);
		String actualBootstrapClassName = commonUtil.getText(txtSectionBootstrapClassName);
		String actualCustomClassName = commonUtil.getText(txtSectionCustomClassName);
		String actualEventsName = "";
		String actualDisplayConditionName = "";
		String actualEditableConditionName = "";
		String actualMandatoryConditionName = "";
		if (!EventsName.isEmpty()) {
			actualEventsName = commonUtil.getText(drpdwnEvents);
		}
		if (!DisplayConditionName.isEmpty()) {
			actualDisplayConditionName = commonUtil.getText(drpdwnDisplayCondition);
		}
		if (!EditableConditionName.isEmpty()) {
			actualEditableConditionName = commonUtil.getText(drpdwnEditableCondition);
		}
		if (!MandatoryConditionName.isEmpty()) {
			actualMandatoryConditionName = commonUtil.getText(drpdwnMandatoryCondition);
		}
		String actualParameterName = commonUtil.getText(txtParameterName);
		String actualParameterValue = commonUtil.getText(txtParameterValue);
		String[] actualData = { actualSectionNumber, actualSectionName, actualSequenceNumber, actualSectionClassName,
				actualBootstrapClassName, actualCustomClassName, actualEventsName, actualDisplayConditionName,
				actualEditableConditionName, actualMandatoryConditionName, actualParameterName, actualParameterValue };
		String[] expectedData = { SectionNumber, modifiedSectionName, SequenceNumber, SectionClassName,
				BootstrapClassName, CustomClassName, EventsName, DisplayConditionName, EditableConditionName,
				MandatoryConditionName, ParameterName, ParameterValue };
		commonUtil.softAssert(actualData, expectedData, softassert);
		softassert.assertAll();

	}

}
