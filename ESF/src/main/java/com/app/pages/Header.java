package com.app.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Header {
	public WebDriver driver;

	@FindBy(xpath = "//input[@type='search']")
	@CacheLookup
	public WebElement txtSearch;

	@FindBy(id = "parameterType0")
	@CacheLookup
	public WebElement parameterName;

	@FindBy(xpath = "//input[@id='value0']")
	@CacheLookup
	public WebElement parameterValue;

	@FindBy(xpath = "//button[text()='Save']")
	@CacheLookup
	public WebElement btnSave;

	@FindBy(xpath = "//table//button[@id='dropdownMenuButton']")
	@CacheLookup
	public WebElement btnOption;

	@FindBy(xpath = "//div[@class='dropdown-menu show']/a[text()='Manage']")
	@CacheLookup
	public WebElement optionManage;

	@FindBy(xpath = "//div[@class='dropdown-menu show']/a[text()='Edit']")
	@CacheLookup
	public WebElement optionEdit;

	@FindBy(xpath = "//div[@class='dropdown-menu show']/a[text()='Remove']")
	@CacheLookup
	public WebElement optionRemove;

	@FindBy(xpath = "//button[contains(text(),'Configure Additional Parameters')]")
	@CacheLookup
	public WebElement btnConfigAddParam;

	@FindBy(xpath = "//label[text()='Bootstrap Class Name']//following-sibling::input[@id='bootstrapClassName']")
	@CacheLookup
	public WebElement bootstrapClassName;

	@FindBy(xpath = "//label[text()='Custom Class Name']//following-sibling::input[@id='customClassName']")
	@CacheLookup
	public WebElement customClassName;

	@FindBy(xpath = "//select[@name='deviceType']")
	@CacheLookup
	public WebElement deviceType;

	@FindBy(xpath = "//select[@name='templateId']")
	@CacheLookup
	public WebElement templateId;

	@FindBy(xpath = "//button[contains(text(),'Configure Templates')]")
	@CacheLookup
	public WebElement btnConfigureTemplate;

	@FindBy(xpath = "//div[@class='modal-content']//a[text()='Remove']")
	@CacheLookup
	public WebElement optionRemoveInPopup;

	@FindBy(xpath = "//table[@id='generic-table']//td[text()='No matching records found']")
	@CacheLookup
	public WebElement txtNoRecordFound;

	public Header(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
