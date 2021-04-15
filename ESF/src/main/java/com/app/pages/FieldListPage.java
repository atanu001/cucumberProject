package com.app.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.app.factory.DriverFactory;
import com.app.util.CommonUtility;

public class FieldListPage extends BasePage {

	public FieldListPage(WebDriver driver) {
		super(driver);
	}

	private CommonUtility commonUtil;
	private ManageFieldPage manageFieldPage;

	@FindBy(xpath = "//span[text()='Application Step Fields']")
	public WebElement txtLabelHeaderFieldListPage;

	@FindBy(xpath = "//a[@id='createButton']//span[text()='New Step Field']")
	private WebElement btnCreateNewField;

	private String UniqId = null;

	/**
	 * This method is used to get the label Header Text of the Filed List Page
	 * 
	 * @return String
	 */
	public String labelHeaderFieldListPage() {
		return txtLabelHeaderFieldListPage.getText();
	}

	/**
	 * This method is used to click on Create New Field button on Field List Page
	 * 
	 * @return the driver Manage Field Page
	 */
	public ManageFieldPage clickOnCreateNewFieldBtn() {
		commonUtil = new CommonUtility(DriverFactory.getDriver());
		manageFieldPage = new ManageFieldPage(DriverFactory.getDriver());
		commonUtil.onClick(btnCreateNewField);
		commonUtil.waitForElementToVisible(manageFieldPage.txtlabelHeaderManageFiled);
		return new ManageFieldPage(driver);
	}

	public ManageFieldPage editField(String fielddetailssheetname, int rowno) {
		commonUtil = new CommonUtility(DriverFactory.getDriver());
		UniqId = ec.getCellData("Field_Details", "Modified Uniq Id", rowno);
		commonUtil.waitForElementToVisible(txtLabelHeaderFieldListPage);
		commonUtil.doSearch(UniqId);
		commonUtil.onClick(btnOption);
		commonUtil.onClick(optionEdit);
		return new ManageFieldPage(driver);
	}

}
