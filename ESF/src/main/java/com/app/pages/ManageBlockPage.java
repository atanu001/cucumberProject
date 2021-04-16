package com.app.pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.app.factory.DriverFactory;
import com.app.util.CommonUtility;
import com.app.util.Log;

public class ManageBlockPage extends BasePage {

	public ManageBlockPage(WebDriver driver) {
		super(driver);
	}

	private CommonUtility commonUtil;

	@FindBy(xpath = "//h2[text()='Manage Block']")
	public WebElement labelHeaderManageBlockPage;

	@FindBy(xpath = "//input[@id='blockName']")
	public WebElement txtBlockName;

	@FindBy(xpath = "//select[@id='blockType']")
	public WebElement drpdwnBlockType;

	@FindBy(xpath = "//textarea[@id='blockDesc']")
	public WebElement txtareaBlockDesc;

	private static String BlockName = null;
	private static String BlockType = null;
	private static String BlockDescription = null;
	private static String BootsrapClassName = null;
	private static String CustomClassName = null;
	private static String ParameterName = null;
	private static String ParameterValue = null;
	private static String ModifiedBlockName = null;

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
		commonUtil.onClick(btnConfigAddParam);
		String[] testData2 = { ParameterName, ParameterValue };
		WebElement[] locator2 = { txtParameterName, txtParameterValue };
		commonUtil.waitForElementToVisible(txtParameterName);
		commonUtil.typeIn(locator2, testData2);
		commonUtil.scrollDownToVisibleElement(btnSave);
		commonUtil.onClick(btnSave);
		return new BlockListPage(driver);

	}

	public void verifyBlock() {
		commonUtil = new CommonUtility(DriverFactory.getDriver());
		commonUtil.waitForElementToVisible(labelHeaderManageBlockPage);
		String actualBlockName = txtBlockName.getAttribute("value");
		if (actualBlockName.equals(ModifiedBlockName)) {
			Log.info("Block Name matched" + " Expectd: " + ModifiedBlockName + " Found: " + actualBlockName);
		} else {
			Log.error("Block Name does not matched" + " Expectd: " + ModifiedBlockName + " Found: " + actualBlockName);
		}
		String actualBlockType = drpdwnBlockType.getAttribute("value");
		if (actualBlockType.equals(BlockType)) {
			Log.info("Block Type matched" + " Expectd: " + BlockType + " Found: " + actualBlockType);
		} else {
			Log.error("Block Type does not matched" + " Expectd: " + BlockType + " Found: " + actualBlockType);
		}
		String actualBlockDescrtiption = txtareaBlockDesc.getAttribute("value");
		if (actualBlockDescrtiption.equals(BlockDescription)) {
			Log.info("Block Description matched" + " Expectd: " + BlockDescription + " Found: "
					+ actualBlockDescrtiption);
		} else {
			Log.error("Block Description does not matched" + " Expectd: " + BlockDescription + " Found: "
					+ actualBlockDescrtiption);
		}
		String actualParameterName = txtParameterName.getAttribute("value");
		if (actualParameterName.equals(ParameterName)) {
			Log.info("Parameter Name matched" + " Expectd: " + ParameterName + " Found: " + actualParameterName);
		} else {
			Log.error("Parameter Name does not matched" + " Expectd: " + ParameterName + " Found: "
					+ actualParameterName);
		}
		String actualParameterValue = txtParameterValue.getAttribute("value");
		if (actualParameterValue.equals(ParameterValue)) {
			Log.info("Parameter Value matched" + " Expectd: " + ParameterValue + " Found: " + actualParameterValue);
		} else {
			Log.error("Parameter Value does not matched" + " Expectd: " + ParameterValue + " Found: "
					+ actualParameterValue);
		}
	}

}
