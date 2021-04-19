package com.app.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.app.factory.DriverFactory;
import com.app.util.CommonUtility;

public class MessageListPage extends BasePage {

	public MessageListPage(WebDriver driver) {
		super(driver);
	}

	private CommonUtility commonUtil;
	private ManageMessagePage manageMessagePage;

	@FindBy(xpath = "//span[text()='Application Messages']")
	public WebElement labelTxtHeaderMessageListPage;

	@FindBy(xpath = "//span[text()='New Message']")
	private WebElement btnAddNewMessage;

	private String MessageCode = null;

	/**
	 * This method will click on Add New Message button on Message List Page
	 * 
	 * @return the Manage Message Page
	 */
	public ManageMessagePage clickOnAddNewMessageBtn() {
		commonUtil = new CommonUtility(DriverFactory.getDriver());
		manageMessagePage = new ManageMessagePage(DriverFactory.getDriver());
		commonUtil.onClick(btnAddNewMessage);
		commonUtil.waitForElementToVisible(manageMessagePage.labelTxtHeaderManageMessagePage);
		return new ManageMessagePage(driver);
	}

	/**
	 * This method will click on edit option of a Message from Message List Page
	 * 
	 * @param messagedetailssheetname
	 * @param rowno
	 * @return the Manage Message Page
	 */
	public ManageMessagePage clickOnOptionEditMessage(String messagedetailssheetname, int rowno) {
		commonUtil = new CommonUtility(DriverFactory.getDriver());
		manageMessagePage = new ManageMessagePage(DriverFactory.getDriver());
		MessageCode = ec.getCellData("Message_Details", "Modified Message Code", rowno);
		commonUtil.doSearch(MessageCode);
		commonUtil.onClick(btnOption);
		commonUtil.onClick(optionEdit);
		commonUtil.waitForElementToVisible(manageMessagePage.labelTxtHeaderManageMessagePage);
		return new ManageMessagePage(driver);
	}
}
