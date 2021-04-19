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
	private BlockListPage blockListPage;
	private ConditionListPage conditionListPage;
	private MessageListPage messageListPage;
	private WorkflowListPage workflowListPage;

	@FindBy(xpath = "//div[@class='row col-lg-12 p-3']//h6[text()='Steps']")
	public WebElement linkStep;

	@FindBy(xpath = "//div[@class='row col-lg-12 p-3']//h6[text()='Blocks']")
	public WebElement linkBlock;

	@FindBy(xpath = "//div[@class='row col-lg-12 p-3']//h6[text()='Messages']")
	public WebElement linkMessage;

	@FindBy(xpath = "//div[@class='row col-lg-12 p-3']//h6[text()='Workflows']")
	public WebElement linkWorkflow;

	@FindBy(xpath = "//div[@class='row col-lg-12 p-3']//h6[text()='Conditions']")
	public WebElement linkConditions;

	public String getApplicationDashboardTitle() {
		return driver.getTitle();
	}

	/**
	 * This method is used to click on step link on the Dashboard
	 * 
	 * @return the Step List Page
	 */
	public StepListPage clickOnStepButtonOnDashboard() {
		try {
			commonUtil = new CommonUtility(DriverFactory.getDriver());
			stepListPage = new StepListPage(DriverFactory.getDriver());
			commonUtil.onClick(linkStep);
			commonUtil.waitForElementToVisible(stepListPage.stepListPageHeading);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new StepListPage(driver);
	}

	/**
	 * This method is used to click on Block link on the Dashboard
	 * 
	 * @return the Block List Page
	 */
	public BlockListPage clickOnBlockButtonOnDashboard() {
		commonUtil = new CommonUtility(DriverFactory.getDriver());
		blockListPage = new BlockListPage(DriverFactory.getDriver());
		commonUtil.onClick(linkBlock);
		commonUtil.waitForElementToVisible(blockListPage.labelHeaderBlockListPage);
		return new BlockListPage(driver);
	}

	/**
	 * This method is used to click on Message link on the Dashboard
	 * 
	 * @return the Message List Page
	 */
	public MessageListPage clickOnMessageButtonOnDashboard() {
		commonUtil = new CommonUtility(DriverFactory.getDriver());
		messageListPage = new MessageListPage(DriverFactory.getDriver());
		commonUtil.onClick(linkMessage);
		commonUtil.waitForElementToVisible(messageListPage.labelTxtHeaderMessageListPage);
		return new MessageListPage(driver);
	}

	/**
	 * This method is used to click on Workflow link on the Dashboard
	 * 
	 * @return the Workflow List Page
	 */
	public WorkflowListPage clickOnWorkflowButtonOnDashboard() {
		commonUtil = new CommonUtility(DriverFactory.getDriver());
		workflowListPage = new WorkflowListPage(DriverFactory.getDriver());
		commonUtil.onClick(linkWorkflow);
		commonUtil.waitForElementToVisible(workflowListPage.labelTxtHeaderWorkflowListPage);
		return new WorkflowListPage(driver);
	}

	/**
	 * This method is used to click on Condition link on the Dashboard
	 * 
	 * @return the Condition List Page
	 */
	public ConditionListPage clickOnConditionsBtnOnDashboard() {
		commonUtil = new CommonUtility(DriverFactory.getDriver());
		conditionListPage = new ConditionListPage(DriverFactory.getDriver());
		commonUtil.onClick(linkConditions);
		commonUtil.waitForElementToVisible(conditionListPage.labelHeaderConditionListPage);
		return new ConditionListPage(driver);
	}

}
