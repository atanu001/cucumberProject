package com.app.pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

import com.app.factory.DriverFactory;
import com.app.util.CommonUtility;

public class ManageBlockPage extends BasePage {

	public ManageBlockPage(WebDriver driver) {
		super(driver);
	}

	private CommonUtility commonUtil;
	private SoftAssert softassert = null;

	@FindBy(xpath = "//h2[text()='Manage Block']")
	public WebElement labelHeaderManageBlockPage;

	@FindBy(xpath = "//input[@id='blockName']")
	public WebElement txtBlockName;

	@FindBy(xpath = "//select[@id='blockType']")
	public WebElement drpdwnBlockType;

	@FindBy(xpath = "//textarea[@id='blockDesc']")
	public WebElement txtareaBlockDesc;

	@FindBy(xpath = "//button[@data-id='eventIds']")
	public WebElement drpdwnEvents;

	@FindBy(xpath = "//input[@aria-controls='bs-select-1']")
	private WebElement searchEvents;

	@FindBy(xpath = "//button[@data-id='displayCondition']")
	public WebElement drpdwnDisplayCondition;

	@FindBy(xpath = "//input[@aria-controls='bs-select-2']")
	private WebElement searchDisplayCondition;

	@FindBy(xpath = "//button[@data-id='editableCondition']")
	public WebElement drpdwnEditableCondition;

	@FindBy(xpath = "//input[@aria-controls='bs-select-3']")
	private WebElement searchEditableCondition;

	@FindBy(xpath = "//button[@data-id='mandatoryCondition']")
	public WebElement drpdwnMandatoryCondition;

	@FindBy(xpath = "//input[@aria-controls='bs-select-4']")
	private WebElement searchMandatoryCondition;

	private String searchDrpdwnSelect = "//span[contains(text(),'%s')]";

	private static String BlockName = null;
	private static String BlockType = null;
	private static String BlockDescription = null;
	private static String BootsrapClassName = null;
	private static String CustomClassName = null;
	private static String ParameterName = null;
	private static String ParameterValue = null;
	private static String ModifiedBlockName = null;
	private static String EventName = null;
	private static String DisplayConditionName = null;
	private static String EditableConditionName = null;
	private static String MandatoryConditionName = null;

	/**
	 * This method will create Block using excel sheet test data
	 * 
	 * @param blockDetailsSheetname
	 * @param rowNo
	 * @return Block List Page
	 */
	public BlockListPage createBlock(String blockDetailsSheetname, int rowNo) {
		commonUtil = new CommonUtility(DriverFactory.getDriver());
		BlockName = ec.getCellData("Block_Details", "Application Block Name", rowNo);
		BlockType = ec.getCellData("Block_Details", "Block Type", rowNo);
		BlockDescription = ec.getCellData("Block_Details", "Description", rowNo);
		BootsrapClassName = ec.getCellData("Block_Details", "Bootstrap Class Name", rowNo);
		CustomClassName = ec.getCellData("Block_Details", "Custom Class Name", rowNo);
		EventName = ec.getCellData("Block_Details", "Event Name", rowNo);
		DisplayConditionName = ec.getCellData("Block_Details", "Display Condition Name", rowNo);
		EditableConditionName = ec.getCellData("Block_Details", "Editable Condition Name", rowNo);
		MandatoryConditionName = ec.getCellData("Block_Details", "Mandatory Condition Name", rowNo);
		ParameterName = ec.getCellData("Block_Details", "Parameter Name", rowNo);
		ParameterValue = ec.getCellData("Block_Details", "Parameter Value", rowNo);
		int randNUm = commonUtil.generateRandomNumber();
		ModifiedBlockName = BlockName + "_" + randNUm;
		try {
			ec.writeCellData("Block_Details", "Modified Block Name", rowNo, ModifiedBlockName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] testData1 = { ModifiedBlockName, BlockType, BlockDescription, BootsrapClassName, CustomClassName };
		WebElement[] locator1 = { txtBlockName, drpdwnBlockType, txtareaBlockDesc, txtBootstrapClassName,
				txtCustomClassName };
		commonUtil.typeIn(locator1, testData1);
		if (!EventName.isEmpty()) {
			commonUtil.onClick(drpdwnEvents);
			searchEvents.sendKeys(EventName);
			commonUtil.onClick(commonUtil.searchDropdown(searchDrpdwnSelect, EventName));
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
		commonUtil.onClick(btnConfigAddParam);
		String[] testData2 = { ParameterName, ParameterValue };
		WebElement[] locator2 = { txtParameterName, txtParameterValue };
		commonUtil.waitForElementToVisible(txtParameterName);
		commonUtil.typeIn(locator2, testData2);
		commonUtil.scrollDownToVisibleElement(btnSave);
		commonUtil.onClick(btnSave);
		return new BlockListPage(driver);

	}

	/**
	 * This method is used to verify Block Data with Excell data
	 */
	public void verifyBlock() {
		commonUtil = new CommonUtility(DriverFactory.getDriver());
		softassert = new SoftAssert();
		commonUtil.waitForElementToVisible(labelHeaderManageBlockPage);
		String actualBlockName = commonUtil.getText(txtBlockName);
		Select s1 = new Select(drpdwnBlockType);
		WebElement blockType = s1.getFirstSelectedOption();
		String actualBlockType = commonUtil.getText(blockType);
		String actualBlockDescrtiption = commonUtil.getText(txtareaBlockDesc);
		String actualBootstrapClassName = commonUtil.getText(txtBootstrapClassName);
		String actualCustomClassName = commonUtil.getText(txtCustomClassName);
		String actualEventsName = "";
		String actualDisplayConditionName = "";
		String actualEditableConditionName = "";
		String actualMandatoryConditionName = "";
		if (!EventName.isEmpty()) {
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
		String[] actualData = { actualBlockName, actualBlockType, actualBlockDescrtiption, actualBootstrapClassName,
				actualCustomClassName, actualEventsName, actualDisplayConditionName, actualEditableConditionName,
				actualMandatoryConditionName, actualParameterName, actualParameterValue };
		String[] expectedData = { ModifiedBlockName, BlockType, BlockDescription, BootsrapClassName, CustomClassName,
				EventName, DisplayConditionName, EditableConditionName, MandatoryConditionName, ParameterName,
				ParameterValue };
		commonUtil.softAssert(actualData, expectedData, softassert);
		softassert.assertAll();

	}

}
