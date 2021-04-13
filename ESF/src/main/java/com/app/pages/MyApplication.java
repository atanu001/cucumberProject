package com.app.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.app.factory.DriverFactory;
import com.app.util.CommonUtility;
import com.app.util.Log;

public class MyApplication extends BasePage {

	public MyApplication(WebDriver driver) {
		super(driver);
	}

	private CommonUtility commonUtil = new CommonUtility(DriverFactory.getDriver());
	private ApplicationDashboard applicationDashboard = new ApplicationDashboard(DriverFactory.getDriver());

	@FindBy(xpath = "//a[@id='createButton']")
	private WebElement btnCreateApp;

	@FindBy(xpath = "//div[@class='col-sm-12']//table[@id='generic-table']")
	public WebElement appListTable;

	@FindBy(xpath = "//th[@aria-label='Name: activate to sort column ascending']//parent::tr//parent::thead/following-sibling::tbody//td[2]/span")
	private WebElement appNameFromList;

	public String getMyApplicationPageTitle() {
		return driver.getTitle();
	}

	/**
	 * This method is used to click on Create Application button on My Application
	 * Page
	 */
	public void clickOnCreateApplicationBtn() {
		commonUtil.onClick(btnCreateApp);
		Log.info("User clicked on Create Application Button");
	}

	/**
	 * This method is used to open an application from the table
	 * 
	 * @param applicationdetailssheetname
	 * @param rowno
	 */
	public void openApplication(String applicationdetailssheetname, String rowno) {
		String appName = ec.getCellData("Application_Details", "Modified Application Name", 0);
		commonUtil.doSearch(appName);
		commonUtil.onClick(btnOption);
		commonUtil.onClick(optionManage);
		// Thread.sleep(2000);
		commonUtil.waitForElementToVisible(applicationDashboard.linkStep);
	}

	/**
	 * This method is used to Delete multiple Application from the Table
	 * 
	 * @param removalAppName
	 * @throws InterruptedException
	 */
	public void removeApplication(List<String> removalAppName) throws InterruptedException {
		for (int i = 0; i < removalAppName.size(); i++) {
			commonUtil.doSearch(removalAppName.get(i));
			commonUtil.onClick(btnOption);
			commonUtil.onClick(optionRemove);
			Thread.sleep(2000);
			commonUtil.onClick(optionRemoveInPopup);

		}
	}

	/**
	 * This method is used to get the application name from the Table
	 * 
	 * @return Application Name
	 */
	public String getApplicationNameFromList() {
		String applicationNameFromList = appNameFromList.getText();
		return applicationNameFromList;
	}
}
