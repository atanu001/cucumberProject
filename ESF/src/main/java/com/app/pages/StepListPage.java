package com.app.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.app.factory.DriverFactory;
import com.app.util.CommonUtility;

public class StepListPage {

	private WebDriver driver;
	private CommonUtility commonUtil = new CommonUtility(DriverFactory.getDriver());

	@FindBy(xpath = "//a[@id='createButton']//span[text()='New Step']")
	@CacheLookup
	WebElement createStepBtn;

	@FindBy(xpath = "//div[@class='w-100 row p-2 mb-3']//span[text()='Application Steps']")
	@CacheLookup
	public WebElement stepListPageHeading;

	public StepListPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickOnCreateStepBtn() {
		commonUtil.onClick(createStepBtn);

	}

	public String getStepListPageTitle() {
		return driver.getTitle();
	}

}
