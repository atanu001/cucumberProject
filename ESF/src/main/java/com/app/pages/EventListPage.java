package com.app.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.app.factory.DriverFactory;
import com.app.util.CommonUtility;

public class EventListPage extends BasePage {

	public EventListPage(WebDriver driver) {
		super(driver);
	}

	private EventManagePage eventManagePage;
	private CommonUtility commonUtil;

	@FindBy(xpath = "//span[text()='Application Events']")
	public WebElement labelHeaderEventListPage;

	@FindBy(xpath = "//span[text()='New Event']")
	public WebElement btnAddNewEvent;

	private String EventName = null;

	/**
	 * This method is used to click on Add New Event button
	 * 
	 * @return the Event Manage Page
	 */
	public EventManagePage clickOnAddNewEventBtn() {
		commonUtil = new CommonUtility(DriverFactory.getDriver());
		eventManagePage = new EventManagePage(DriverFactory.getDriver());
		commonUtil.onClick(btnAddNewEvent);
		commonUtil.waitForElementToVisible(eventManagePage.labelTxtHeaderEventManagePage);
		return new EventManagePage(driver);
	}

	/**
	 * This method is used to click on edit Event option
	 * 
	 * @param rowno
	 * @param eventdetailssheetname
	 * 
	 * @return the Event Manage Page
	 */
	public EventManagePage clickOnEditEventOption(String eventdetailssheetname, int rowno) {
		commonUtil = new CommonUtility(DriverFactory.getDriver());
		eventManagePage = new EventManagePage(DriverFactory.getDriver());
		EventName = ec.getCellData("Event_Details", "Modified Event Name", rowno);
		commonUtil.doSearch(EventName);
		commonUtil.onClick(btnOption);
		commonUtil.onClick(optionEdit);
		commonUtil.waitForElementToVisible(eventManagePage.labelTxtHeaderEventManagePage);
		return new EventManagePage(driver);
	}
}
