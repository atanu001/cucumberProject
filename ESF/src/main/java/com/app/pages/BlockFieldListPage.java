package com.app.pages;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.app.factory.DriverFactory;
import com.app.util.CommonUtility;
import com.app.util.ConfigReader;
import com.app.util.Excell;

public class BlockFieldListPage extends BasePage {

	public BlockFieldListPage(WebDriver driver) {
		super(driver);
	}

	private CommonUtility commonUtil;
	private FieldManagePage fieldManagePage;
	private Excell ec;
	private Properties prop;
	private ConfigReader configReader = new ConfigReader();

	@FindBy(xpath = "//span[text()='Application Block Fields']")
	public WebElement labelHeaderBlockFieldListPage;

	@FindBy(xpath = "//span[text()='New Block Field']")
	private WebElement btnAddNewBlockField;

	private String FieldUniqId = null;

	/**
	 * This method is used to click on Add New Block Field Button on Field List Page
	 * of a Block
	 * 
	 * @return the Manage Field Page
	 */
	public FieldManagePage clickOnAddNewBlockFieldBtn() {
		commonUtil = new CommonUtility(DriverFactory.getDriver());
		fieldManagePage = new FieldManagePage(DriverFactory.getDriver());
		commonUtil.onClick(btnAddNewBlockField);
		commonUtil.waitForElementToVisible(fieldManagePage.txtlabelHeaderManageField);
		return new FieldManagePage(driver);
	}

	public FieldManagePage clickOnBlockFieldEdit(String fielddetailssheetname, int rowno) {
		commonUtil = new CommonUtility(DriverFactory.getDriver());
		prop = configReader.init_prop();
		try {
			ec = new Excell(prop.getProperty("TestDataPath"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		commonUtil.waitForElementToVisible(labelHeaderBlockFieldListPage);
		FieldUniqId = ec.getCellData("Field_Details", "Modified Uniq Id", rowno);
		commonUtil.doSearch(FieldUniqId);
		commonUtil.onClick(btnOption);
		commonUtil.onClick(optionEdit);
		return new FieldManagePage(driver);
	}

}
