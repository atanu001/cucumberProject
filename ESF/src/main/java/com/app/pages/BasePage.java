package com.app.pages;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.app.factory.DriverFactory;
import com.app.util.ConfigReader;
import com.app.util.Excell;

public class BasePage {
	public WebDriver driver;
	public static Properties prop;
	public static ConfigReader configReader = new ConfigReader();
	public static Excell ec;

	static {

		prop = configReader.init_prop();
		try {
			ec = new Excell(prop.getProperty("TestDataPath"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@type='search']")
	public WebElement txtSearch;

	@FindBy(id = "parameterType0")
	public WebElement txtParameterName;

	@FindBy(xpath = "//input[@id='value0']")
	public WebElement txtParameterValue;

	@FindBy(xpath = "//button[text()='Save']")
	public WebElement btnSave;

	@FindBy(xpath = "//table//button[@id='dropdownMenuButton']")
	public WebElement btnOption;

	@FindBy(xpath = "//div[@class='dropdown-menu show']/a[text()='Manage']")
	public WebElement optionManage;

	@FindBy(xpath = "//div[@class='dropdown-menu show']/a[text()='Edit']")
	public WebElement optionEdit;

	@FindBy(xpath = "//div[@class='dropdown-menu show']/a[text()='Remove']")
	public WebElement optionRemove;

	@FindBy(xpath = "//button[contains(text(),'Configure Additional Parameters')]")
	public WebElement btnConfigAddParam;

	@FindBy(xpath = "//label[text()='Bootstrap Class Name']//following-sibling::input[@id='bootstrapClassName']")
	public WebElement txtBootstrapClassName;

	@FindBy(xpath = "//label[text()='Custom Class Name']//following-sibling::input[@id='customClassName']")
	public WebElement txtCustomClassName;

	@FindBy(xpath = "//select[@name='deviceType']")
	public WebElement drpdwnDeviceType;

	@FindBy(xpath = "//select[@name='templateId']")
	public WebElement txtTemplateId;

	@FindBy(xpath = "//button[contains(text(),'Configure Templates')]")
	public WebElement btnConfigureTemplate;

	@FindBy(xpath = "//div[@class='modal-content']//a[text()='Remove']")
	public WebElement optionRemoveInPopup;

	@FindBy(xpath = "//table[@id='generic-table']//td[text()='No matching records found']")
	public WebElement txtNoRecordFound;

	public void openUrl(String Url) {

		DriverFactory.getDriver().get(Url);
	}

}
