package com.app.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.app.factory.DriverFactory;
import com.app.util.CommonUtility;

public class BlockListPage extends BasePage {

	public BlockListPage(WebDriver driver) {
		super(driver);
	}

	private CommonUtility commonUtil;
	private ManageBlockPage manageBlockPage;
	// private ApplicationDashboard applicationDashboard;

	@FindBy(xpath = "//span[text()='Application Blocks']")
	public WebElement labelHeaderBlockListPage;

	@FindBy(xpath = "//span[text()='New Block']")
	public WebElement btnAddNewBlock;

	private String BlockName = null;

	/**
	 * This method is used to Click on Add New Button on Block List Page
	 * 
	 * @return Manage Block Page
	 */
	public ManageBlockPage clickOnAddNewBlockBtn() {
		commonUtil = new CommonUtility(DriverFactory.getDriver());
		manageBlockPage = new ManageBlockPage(DriverFactory.getDriver());
		try {
			commonUtil.onClick(btnAddNewBlock);
			commonUtil.waitForElementToVisible(manageBlockPage.labelHeaderManageBlockPage);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ManageBlockPage(driver);
	}

	/**
	 * This method is used to Click on Edit option of a Block on Block List Page
	 * 
	 * @return Manage Block Page
	 */
	public ManageBlockPage clickOnEditBtnOfBlock(String blockDetailsSheetname, int rowNo) {
		commonUtil = new CommonUtility(DriverFactory.getDriver());
		BlockName = ec.getCellData("Block_Details", "Modified Block Name", rowNo);
		commonUtil.waitForElementToVisible(labelHeaderBlockListPage);
		commonUtil.doSearch(BlockName);
		commonUtil.onClick(btnOption);
		commonUtil.onClick(optionEdit);
		return new ManageBlockPage(driver);
	}

	/**
	 * This method is used to Click on Manage option of a Block on Block List Page
	 * 
	 * @return Manage Block Page
	 */
	public BlockFieldListPage openBlock(String blockDetailsSheet, int rowNo) {
		commonUtil = new CommonUtility(DriverFactory.getDriver());
		commonUtil.waitForElementToVisible(labelHeaderBlockListPage);
		BlockName = ec.getCellData("Block_Details", "Modified Block Name", rowNo);
		commonUtil.doSearch(BlockName);
		commonUtil.onClick(btnOption);
		commonUtil.onClick(optionManage);
		return new BlockFieldListPage(driver);

	}

}
