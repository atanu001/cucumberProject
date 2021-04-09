package com.app.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.app.factory.DriverFactory;
import com.app.util.CommonUtility;

public class ApplicationDashboard extends BasePage {

	public ApplicationDashboard(WebDriver driver) {
		super(driver);
	}

	private CommonUtility commonUtil = new CommonUtility(DriverFactory.getDriver());
	private StepListPage stepListPage = new StepListPage(DriverFactory.getDriver());

	@FindBy(xpath = "//div[@class='row col-lg-12 p-3']//h6[text()='Steps']")
	public WebElement linkStep;

	/*
	 * This method is used to click on step link on the Dashboard
	 */
	public void clickOnStep() {
		try {
			commonUtil.onClick(linkStep);
			// Thread.sleep(2000);
			commonUtil.waitForElementToVisible(stepListPage.stepListPageHeading);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getApplicationDashboardTitle() {
		return driver.getTitle();
	}

}
