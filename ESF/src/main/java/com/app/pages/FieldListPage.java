package com.app.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.app.factory.DriverFactory;
import com.app.util.CommonUtility;

public class FieldListPage extends BasePage {

	public FieldListPage(WebDriver driver) {
		super(driver);
	}

	private CommonUtility commonUtil = new CommonUtility(DriverFactory.getDriver());
	private ManageFieldPage manageFieldPage = new ManageFieldPage(DriverFactory.getDriver());

	@FindBy(xpath = "//span[text()='Application Step Fields']")
	public WebElement txtLabelHeaderFieldListPage;

	@FindBy(xpath = "//a[@id='createButton']//span[text()='New Step Field']")
	public WebElement btnCreateNewField;

	public String labelHeaderFieldListPage() {
		return txtLabelHeaderFieldListPage.getText();
	}

	public void clickOnCreateNewFieldBtn() {
		commonUtil.onClick(btnCreateNewField);
		commonUtil.waitForElementToVisible(manageFieldPage.txtlabelHeaderManageFiled);
	}

}
