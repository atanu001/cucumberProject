package com.app.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.app.factory.DriverFactory;
import com.app.util.CommonUtility;

public class WorkflowListPage extends BasePage {

	public WorkflowListPage(WebDriver driver) {
		super(driver);
	}

	private CommonUtility commonUtil;
	private ManageWorkflowPage manageWorkflowPage;

	@FindBy(xpath = "//span[text()='Application Workflows']")
	public WebElement labelTxtHeaderWorkflowListPage;

	@FindBy(xpath = "//span[text()='New Workflow']")
	private WebElement btnAddNewWorkflow;

	private String WorkflowName = null;

	/**
	 * This method will click on Add New Workflow button on Workflow list page
	 * 
	 * @return the Manage Workflow Page
	 */
	public ManageWorkflowPage clickOnAddNewWorkflowBtn() {
		commonUtil = new CommonUtility(DriverFactory.getDriver());
		manageWorkflowPage = new ManageWorkflowPage(DriverFactory.getDriver());
		commonUtil.onClick(btnAddNewWorkflow);
		commonUtil.waitForElementToVisible(manageWorkflowPage.labelTxtHeaderManageWorkflowPage);
		return new ManageWorkflowPage(driver);
	}

	/**
	 * This method will click on Edit option of a Workflow list page
	 * 
	 * @param rowno
	 * @param workflowdetailssheetname
	 * 
	 * @return the Manage Workflow Page
	 */
	public ManageWorkflowPage clickOnEditWorkflowOption(String workflowdetailssheetname, int rowno) {
		commonUtil = new CommonUtility(DriverFactory.getDriver());
		manageWorkflowPage = new ManageWorkflowPage(DriverFactory.getDriver());
		WorkflowName = ec.getCellData("Workflow_Details", "Modified Workflow Name", rowno);
		commonUtil.doSearch(WorkflowName);
		commonUtil.onClick(btnOption);
		commonUtil.onClick(optionEdit);
		commonUtil.waitForElementToVisible(manageWorkflowPage.labelTxtHeaderManageWorkflowPage);
		return new ManageWorkflowPage(driver);
	}

}
