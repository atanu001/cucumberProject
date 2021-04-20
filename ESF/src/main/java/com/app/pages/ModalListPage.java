package com.app.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.app.factory.DriverFactory;
import com.app.util.CommonUtility;

public class ModalListPage extends BasePage {

	public ModalListPage(WebDriver driver) {
		super(driver);
	}

	private CommonUtility commonUtil;
	private ModalManagePage modalManagePage;

	@FindBy(xpath = "//span[text()='Application Modals']")
	public WebElement labelTxtHeaderModalListPage;

	@FindBy(xpath = "//span[text()='New Modal']")
	public WebElement btnAddNewModal;

	private String ModalId = null;

	/**
	 * This method will used to click on Add New Modal button on Modal List Page
	 * 
	 * @return the Modal Manage Page
	 */
	public ModalManagePage clickOnAddNewModalBtn() {
		commonUtil = new CommonUtility(DriverFactory.getDriver());
		modalManagePage = new ModalManagePage(DriverFactory.getDriver());
		commonUtil.onClick(btnAddNewModal);
		commonUtil.waitForElementToVisible(modalManagePage.labelTxtHeaderModalManagePage);
		return new ModalManagePage(driver);
	}

	/**
	 * This method will used to click on Edit option of an Modal on Modal List Page
	 * 
	 * @param rowno
	 * @param modaldetailssheetname
	 * 
	 * @return the Modal Manage Page
	 */
	public ModalManagePage clickOnEditModalOption(String modaldetailssheetname, int rowno) {
		commonUtil = new CommonUtility(DriverFactory.getDriver());
		modalManagePage = new ModalManagePage(DriverFactory.getDriver());
		ModalId = ec.getCellData("Modal_Details", "Modified Modal Id", rowno);
		commonUtil.doSearch(ModalId);
		commonUtil.onClick(btnOption);
		commonUtil.onClick(optionEdit);
		commonUtil.waitForElementToVisible(modalManagePage.labelTxtHeaderModalManagePage);
		return new ModalManagePage(driver);
	}
}
