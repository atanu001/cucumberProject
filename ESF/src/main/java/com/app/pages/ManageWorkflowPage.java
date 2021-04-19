package com.app.pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.app.factory.DriverFactory;
import com.app.util.CommonUtility;
import com.app.util.Log;

public class ManageWorkflowPage extends BasePage {

	public ManageWorkflowPage(WebDriver driver) {
		super(driver);
	}

	private CommonUtility commonUtil;
	private WorkflowListPage workflowListPage;

	@FindBy(xpath = "//h2[text()='Manage Workflow']")
	public WebElement labelTxtHeaderManageWorkflowPage;

	@FindBy(xpath = "//input[@id='workflowTitle']")
	private WebElement txtNameOfWorkflow;

	@FindBy(xpath = "//button[contains(text(),'Select the steps associated to this workflow:')]")
	private WebElement expandToSelectStepsAssociated;

	@FindBy(xpath = "//select[@id='workFlowStepIds0']")
	private WebElement drpdwnStep;

	@FindBy(xpath = "//label[text()='Set as default workflow?']/preceding-sibling::input[@id='setAsDefault']")
	private WebElement chkboxSetAsDefaultWorkflow;

	@FindBy(xpath = "//label[text()=' Show step count?']/preceding-sibling::input[@id='isStepCountVisible']")
	private WebElement chkboxIsStepCountVisible;

	@FindBy(xpath = "//label[contains(text(),'Display completion percentage?')]/preceding-sibling::input[@id='isPercentageVisible']")
	private WebElement chkboxIsPercentageVisible;

	@FindBy(xpath = "//select[@id='exampleFormControlSelect1']")
	private WebElement drpdwnAssociateProgressBar;

	@FindBy(xpath = "//input[@id='stepCountColor']")
	private WebElement txtStepCountColor;

	@FindBy(xpath = "//input[@id='percentageTextColor']")
	private WebElement txtPercentageTextColor;

	@FindBy(xpath = "//input[@id='percenatgeProgressBarColor']")
	private WebElement txtProgressBarColor;

	private static String WorkflowTitle = null;
	private static String StepName = null;
	private static String StepCountColor = null;
	private static String PercentageTextColor = null;
	private static String ProgressBarColor = null;
	private static String ModifiedWorkflowTitle = null;

	/**
	 * This method will create Workflow with Excel sheet test data
	 * 
	 * @param workflowdetailssheetname
	 * @param rowno
	 * @return the Workflow list page
	 */
	public WorkflowListPage createWorkflow(String workflowdetailssheetname, int rowno) {
		commonUtil = new CommonUtility(DriverFactory.getDriver());
		workflowListPage = new WorkflowListPage(DriverFactory.getDriver());
		WorkflowTitle = ec.getCellData("Workflow_Details", "Name of the workflow", rowno);
		StepName = ec.getCellData("Workflow_Details", "Step Name", rowno);
		StepCountColor = ec.getCellData("Workflow_Details", "Step Count Color", rowno);
		PercentageTextColor = ec.getCellData("Workflow_Details", "Percentage Text Color", rowno);
		ProgressBarColor = ec.getCellData("Workflow_Details", "Progress Bar Color", rowno);
		int randNum = commonUtil.generateRandomNumber();
		ModifiedWorkflowTitle = WorkflowTitle + "_" + randNum;
		try {
			ec.writeCellData("Workflow_Details", "Modified Workflow Name", rowno, ModifiedWorkflowTitle);
		} catch (IOException e) {
			e.printStackTrace();
		}
		commonUtil.onClick(expandToSelectStepsAssociated);
		commonUtil.waitForElementToVisible(drpdwnStep);
		commonUtil.onClick(chkboxSetAsDefaultWorkflow);
		commonUtil.onClick(chkboxIsStepCountVisible);
		commonUtil.onClick(chkboxIsPercentageVisible);
		String[] testData = { ModifiedWorkflowTitle, StepName, StepCountColor, PercentageTextColor, ProgressBarColor };
		WebElement[] locator = { txtNameOfWorkflow, drpdwnStep, txtStepCountColor, txtPercentageTextColor,
				txtProgressBarColor };
		commonUtil.typeIn(locator, testData);
		commonUtil.onClick(btnSave);
		commonUtil.waitForElementToVisible(workflowListPage.labelTxtHeaderWorkflowListPage);
		return new WorkflowListPage(driver);

	}

	public void verifyWorkflow() {
		commonUtil = new CommonUtility(DriverFactory.getDriver());
		String actualWorkflowTitle = txtNameOfWorkflow.getAttribute("value");
		if (actualWorkflowTitle.equals(ModifiedWorkflowTitle)) {
			Log.info("Workflow Title is matched " + " Expected: " + ModifiedWorkflowTitle + " Found: "
					+ actualWorkflowTitle);
		} else {
			Log.error("Workflow Title does not matched " + " Expected: " + ModifiedWorkflowTitle + " Found: "
					+ actualWorkflowTitle);
		}
		commonUtil.onClick(expandToSelectStepsAssociated);
		commonUtil.waitForElementToVisible(drpdwnStep);
		Select selectName = new Select(drpdwnStep);
		WebElement eleStepName = selectName.getFirstSelectedOption();
		String actualStepSelected = eleStepName.getText();
		if (actualStepSelected.equals(StepName)) {
			Log.info("Step selection is mathced with Step Name " + "Expected: " + StepName + " Found: "
					+ actualStepSelected);
		} else {
			Log.error("Step selection does not mathced with Step Name " + "Expected: " + StepName + " Found: "
					+ actualStepSelected);
		}
		if (chkboxSetAsDefaultWorkflow.isSelected()) {
			Log.info("Checkbox for Set as default workflow? is selected ");
		} else {
			Log.error("Checkbox for Set as default workflow? is not selected ");
		}
		if (chkboxIsStepCountVisible.isSelected()) {
			Log.info("Checkbox for Show step count? is selected ");
		} else {
			Log.error("Checkbox for Show step count? is not selected ");
		}
		if (chkboxIsPercentageVisible.isSelected()) {
			Log.info("Checkbox for Display completion percentage? is selected ");
		} else {
			Log.error("Checkbox for Display completion percentage? is not selected ");
		}
		String actualStepCountColor = txtStepCountColor.getAttribute("value");
		if (actualStepCountColor.equals(StepCountColor)) {
			Log.info("Step Count Color is matched " + " Expected: " + StepCountColor + " Found: "
					+ actualStepCountColor);
		} else {
			Log.error("Step Count Color does not matched " + " Expected: " + StepCountColor + " Found: "
					+ actualStepCountColor);
		}
		String actualPercentageTextColor = txtPercentageTextColor.getAttribute("value");
		if (actualPercentageTextColor.equals(PercentageTextColor)) {
			Log.info("Percentage Text Color is matched " + " Expected: " + PercentageTextColor + " Found: "
					+ actualPercentageTextColor);
		} else {
			Log.error("Percentage Text Color does not matched " + " Expected: " + PercentageTextColor + " Found: "
					+ actualPercentageTextColor);
		}
		String actualProgressBarColor = txtProgressBarColor.getAttribute("value");
		if (actualProgressBarColor.equals(ProgressBarColor)) {
			Log.info("Progress Bar Color is matched " + " Expected: " + ProgressBarColor + " Found: "
					+ actualProgressBarColor);
		} else {
			Log.error("Progress Bar Color does not matched " + " Expected: " + ProgressBarColor + " Found: "
					+ actualProgressBarColor);
		}
	}

}
