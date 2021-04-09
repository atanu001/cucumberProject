package com.app.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.app.factory.DriverFactory;
import com.app.util.CommonUtility;

public class MyApplication {
	private WebDriver driver;
	private CommonUtility commonUtil = new CommonUtility(DriverFactory.getDriver());
	private Header header = new Header(DriverFactory.getDriver());
	private ApplicationDashboard applicationDashboard = new ApplicationDashboard(DriverFactory.getDriver());

	@FindBy(xpath = "//a[@id='createButton']")
	@CacheLookup
	WebElement btnCreateApp;

	@FindBy(xpath = "//div[@class='col-sm-12']//table[@id='generic-table']")
	@CacheLookup
	WebElement appListTable;

	@FindBy(xpath = "//th[@aria-label='Name: activate to sort column ascending']//parent::tr//parent::thead/following-sibling::tbody//td[2]/span")
	@CacheLookup
	WebElement appNameFromList;

	public MyApplication(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String getMyApplicationPageTitle() {
		return driver.getTitle();
	}

	public void clickOnCreateApplicationBtn() {
		commonUtil.onClick(btnCreateApp);

	}

	public void openApplication() {

		commonUtil.onClick(header.btnOption);
		commonUtil.onClick(header.optionManage);
		// Thread.sleep(2000);
		commonUtil.waitForElementToVisible(applicationDashboard.linkStep);
	}

	public void removeApplication(List<String> removalAppName) throws InterruptedException {
		for (int i = 0; i < removalAppName.size(); i++) {
			commonUtil.doSearch(removalAppName.get(i));
			commonUtil.onClick(header.btnOption);
			commonUtil.onClick(header.optionRemove);
			Thread.sleep(2000);
			commonUtil.onClick(header.optionRemoveInPopup);

		}
	}

	public String getApplicationNameFromList() {
		String applicationNameFromList = appNameFromList.getText();
		return applicationNameFromList;
	}
}
