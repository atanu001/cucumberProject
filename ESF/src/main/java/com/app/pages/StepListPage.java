package com.app.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.app.factory.DriverFactory;
import com.app.util.CommonUtility;

public class StepListPage extends BasePage {

	public StepListPage(WebDriver driver) {
		super(driver);
	}

	private CommonUtility commonUtil = new CommonUtility(DriverFactory.getDriver());

	@FindBy(xpath = "//a[@id='createButton']//span[text()='New Step']")
	WebElement createStepBtn;

	@FindBy(xpath = "//div[@class='w-100 row p-2 mb-3']//span[text()='Application Steps']")
	public WebElement stepListPageHeading;

	public void clickOnCreateStepBtn() {
		commonUtil.onClick(createStepBtn);

	}

	public String getStepListPageTitle() {
		return driver.getTitle();
	}

}
