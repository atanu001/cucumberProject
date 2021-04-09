package com.app.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.app.factory.DriverFactory;
import com.app.util.CommonUtility;

public class MyApplication extends BasePage {

	public MyApplication(WebDriver driver) {
		super(driver);
	}

	private CommonUtility commonUtil = new CommonUtility(DriverFactory.getDriver());
	private BasePage header = new BasePage(DriverFactory.getDriver());
	private ApplicationDashboard applicationDashboard = new ApplicationDashboard(DriverFactory.getDriver());

	@FindBy(xpath = "//a[@id='createButton']")
	public WebElement btnCreateApp;

	@FindBy(xpath = "//div[@class='col-sm-12']//table[@id='generic-table']")
	public WebElement appListTable;

	@FindBy(xpath = "//th[@aria-label='Name: activate to sort column ascending']//parent::tr//parent::thead/following-sibling::tbody//td[2]/span")
	public WebElement appNameFromList;

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
