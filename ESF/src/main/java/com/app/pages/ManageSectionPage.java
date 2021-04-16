package com.app.pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.app.factory.DriverFactory;
import com.app.util.CommonUtility;
import com.app.util.Log;

public class ManageSectionPage extends BasePage {

	public ManageSectionPage(WebDriver driver) {
		super(driver);
	}

	private CommonUtility commonUtil;

	@FindBy(xpath = "//h2[text()='Manage Section']")
	private WebElement labelHeaderManageSectionPage;

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
		commonUtil.waitForElementToVisible(labelHeaderManageSectionPage);
		String actualSectionNumber = drpdwnSectionNum.getAttribute("value");
		if (actualSectionNumber.equals(SectionNumber)) {
			Log.info("Section Number matched for the Section Name: " + modifiedSectionName + " Expected: "
					+ SectionNumber + " Found: " + actualSectionNumber);
		} else {
			Log.error("Section Number does not matched for the Section Name: " + modifiedSectionName + " Expected: "
					+ SectionNumber + " Found: " + actualSectionNumber);
		}
		String actualSectionName = txtSectionName.getAttribute("value");
		if (actualSectionName.equals(modifiedSectionName)) {
			Log.info("Section Name matched for the Section Name: " + modifiedSectionName + " Expected: "
					+ modifiedSectionName + " Found: " + actualSectionName);
		} else {
			Log.error("Section Name does not matched for the Section Name: " + modifiedSectionName + " Expected: "
					+ modifiedSectionName + " Found: " + actualSectionName);
		}
		String actualSequenceNumber = drpdwnSequenceNumber.getAttribute("value");
		if (actualSequenceNumber.equals(SequenceNumber)) {
			Log.info("Sequence Number matched for the Section Name: " + modifiedSectionName + " Expected: "
					+ SequenceNumber + " Found: " + actualSequenceNumber);
		} else {
			Log.error("Sequence Number does not matched for the Section Name: " + modifiedSectionName + " Expected: "
					+ SequenceNumber + " Found: " + actualSequenceNumber);
		}
		String actualSectionClassName = txtSectionClassName.getAttribute("value");
		if (actualSectionClassName.equals(SectionClassName)) {
			Log.info("Section Class Name matched for the Section Name: " + modifiedSectionName + " Expected: "
					+ SectionClassName + " Found: " + actualSectionClassName);
		} else {
			Log.error("Section Class Name does not matched for the Section Name: " + modifiedSectionName + " Expected: "
					+ SectionClassName + " Found: " + actualSectionClassName);
		}
		String actualBootstrapClassName = txtSectionBootstrapClassName.getAttribute("value");
		if (actualBootstrapClassName.equals(BootstrapClassName)) {
			Log.info("Bootstrap Class Name matched for the Section Name: " + modifiedSectionName + " Expected: "
					+ BootstrapClassName + " Found: " + actualBootstrapClassName);
		} else {
			Log.error("Bootstrap Class Name does not matched for the Section Name: " + modifiedSectionName
					+ " Expected: " + BootstrapClassName + " Found: " + actualBootstrapClassName);
		}
		String actualCustomClassName = txtSectionCustomClassName.getAttribute("value");
		if (actualCustomClassName.equals(CustomClassName)) {
			Log.info("Custom Class Name matched for the Section Name: " + modifiedSectionName + " Expected: "
					+ CustomClassName + " Found: " + actualCustomClassName);
		} else {
			Log.error("Custom Class Name does not matched for the Section Name: " + modifiedSectionName + " Expected: "
					+ CustomClassName + " Found: " + actualCustomClassName);
		}
		String actualParameterName = txtParameterName.getAttribute("value");
		if (actualParameterName.equals(ParameterName)) {
			Log.info("Parameter Name matched for the Section Name: " + modifiedSectionName + " Expected: "
					+ ParameterName + " Found: " + actualParameterName);
		} else {
			Log.error("Parameter Name does not matched for the Section Name: " + modifiedSectionName + " Expected: "
					+ ParameterName + " Found: " + actualParameterName);
		}
		String actualParameterValue = txtParameterValue.getAttribute("value");
		if (actualParameterValue.equals(ParameterValue)) {
			Log.info("Parameter Value matched for the Section Name: " + modifiedSectionName + " Expected: "
					+ ParameterValue + " Found: " + actualParameterValue);
		} else {
			Log.error("Parameter Value does not matched for the Section Name: " + modifiedSectionName + " Expected: "
					+ ParameterValue + " Found: " + actualParameterValue);
		}
	}

}
