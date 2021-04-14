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

	private CommonUtility commonUtil;
	private StepListPage stepListPage;

	@FindBy(xpath = "//div[@class='row col-lg-12 p-3']//h6[text()='Steps']")
	public WebElement linkStep;

	/**
	 * This method is used to click on step link on the Dashboard
	 * 
	 * @return the driver to the StepList Page
	 */
	public StepListPage clickOnStep() {
		try {
			commonUtil = new CommonUtility(DriverFactory.getDriver());
			stepListPage = new StepListPage(DriverFactory.getDriver());
			commonUtil.onClick(linkStep);
			// Thread.sleep(2000);
			commonUtil.waitForElementToVisible(stepListPage.stepListPageHeading);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new StepListPage(driver);
	}

	public String getApplicationDashboardTitle() {
		return driver.getTitle();
	}

}
