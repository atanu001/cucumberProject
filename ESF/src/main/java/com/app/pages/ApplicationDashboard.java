package com.app.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.app.factory.DriverFactory;
import com.app.util.CommonUtility;

public class ApplicationDashboard {

	private WebDriver driver;
	private CommonUtility commonUtil = new CommonUtility(DriverFactory.getDriver());
	private StepListPage stepListPage = new StepListPage(DriverFactory.getDriver());

	@FindBy(xpath = "//div[@class='row col-lg-12 p-3']//h6[text()='Steps']")
	@CacheLookup
	public WebElement linkStep;

	public ApplicationDashboard(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

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
