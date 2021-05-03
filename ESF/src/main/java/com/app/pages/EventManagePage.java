package com.app.pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

import com.app.factory.DriverFactory;
import com.app.util.CommonUtility;
import com.app.util.Log;

public class EventManagePage extends BasePage {

	public EventManagePage(WebDriver driver) {
		super(driver);
	}

	private CommonUtility commonUtil;
	private EventListPage eventListPage;
	private SoftAssert softassert = null;

	@FindBy(xpath = "//h2[text()='Manage Events']")
	public WebElement labelTxtHeaderEventManagePage;

	@FindBy(xpath = "//select[@id='eventName']")
	private WebElement drpdwnEventType;

	@FindBy(xpath = "//select[@id='displayType']")
	private WebElement drpdwnDisplayType;

	@FindBy(xpath = "//input[@id='eventDesc']")
	private WebElement txtEventName;

	@FindBy(xpath = "//button[@data-id='nextEventId']")
	private WebElement drpdwnNextEventId;

//	@FindBy(xpath = "//span[contains(text(),'%s')]")
//	private WebElement selectNextEventId;
	String searchDrpdwnSelect = "//span[contains(text(),'%s')]";

	@FindBy(xpath = "//input[@id='library_id']")
	private WebElement txtLibraryId;

	@FindBy(xpath = "//input[@id='isDefaultCheckbox']")
	private WebElement chkboxIsDefault;

	@FindBy(xpath = "//button[@data-id='framework_type']")
	private WebElement drpdwnFramework;
	// String selectFramework = "//button[@data-id='framework_type']";

	@FindBy(xpath = "//button[@data-id='displaystepforevent']")
	private WebElement drpdwnDisplayStep;
	// String selectDisplayStep = "//button[@data-id='displaystepforevent']";

	@FindBy(xpath = "//button[@data-id='displayworkflowforevent']")
	private WebElement drpdwnWorkflow;
	// String selectWorkflow = "//button[@data-id='displayworkflowforevent']";

	@FindBy(xpath = "//input[@id='eventHandler']")
	private WebElement txtEventHandler;

	@FindBy(xpath = "//button[contains(text(),'Event Conditions')]")
	private WebElement expandEventCondition;

	@FindBy(xpath = "//input[@name='eventCondId']")
	private WebElement txtConditionId;

	@FindBy(xpath = "//input[@name='step']")
	private WebElement txtStepName;

	@FindBy(xpath = "//input[@name='block']")
	private WebElement txtBlockName;

	@FindBy(xpath = "//input[@name='field']")
	private WebElement txtFieldUniqId;

	@FindBy(xpath = "//select[@name='comparisonOperator']")
	private WebElement drpdwnComparisonOperator;

	@FindBy(xpath = "//input[@name='fieldValue']")
	private WebElement txtFieldValue;

	@FindBy(xpath = "//input[@name='apiKey']")
	private WebElement txtApiKey;

	@FindBy(xpath = "//input[@name='query']")
	private WebElement txtQuery;

	@FindBy(xpath = "//button[text()='Additional Parameters']")
	private WebElement expandAdditionalParameter;

	@FindBy(xpath = "//button[contains(text(),'Event CallBack')]")
	private WebElement expandAddEventCallBack;

	@FindBy(xpath = "//select[@name='key']")
	private WebElement drpdwnHandlerName;

	@FindBy(xpath = "//input[@id='api_key0']")
	private WebElement txtClBckApiKey;

	@FindBy(xpath = "//select[@name='headerStatus']")
	private WebElement drpdwnHeaderStatusCode;

	@FindBy(xpath = "//select[@id='nextEventId0']")
	private WebElement drpdwnClBckNxtEventId;

	@FindBy(xpath = "//select[@id='messageCode0']")
	private WebElement drpdwnClBckMsgCode;

	@FindBy(xpath = "//input[@id='navigatetoNextPage0']")
	private WebElement chkboxNavigatetoNextPage;

	@FindBy(xpath = "//input[@name='responseKey']")
	private WebElement txtResponseKey;

	@FindBy(xpath = "//input[@name='responseValue']")
	private WebElement txtResponseValue;

	@FindBy(xpath = "//input[@aria-controls='bs-select-3']")
	private WebElement drpdwnStepSearch;

	@FindBy(xpath = "//input[@aria-controls='bs-select-2']")
	private WebElement drpdwnFrameworkSearch;

	private static String EventType = null;
	private static String DisplayType = null;
	private static String EventName = null;
	private static String ModifiedEventName = null;
	private static String NextEventId = null;
	private static String LibraryId = null;
	private static String Framework = null;
	private static String DisplayStep = null;
	private static String DisplayWorkflow = null;
	private static String EventHandler = null;
	private static String ConditionID = null;
	private static String StepName = null;
	private static String BlockName = null;
	private static String FieldUniqueId = null;
	private static String ComparisonOperator = null;
	private static String FieldValue = null;
	private static String ApiKey = null;
	private static String Query = null;
	private static String ParameterName = null;
	private static String ParameterValue = null;
	private static String HandlerName = null;
	private static String CallBckAPIkey = null;
	private static String HeaderStatusCode = null;
	private static String CallBckNextEventID = null;
	private static String MessageCode = null;
	private static String ResponseKey = null;
	private static String ResponseValue = null;

	/**
	 * This method is used to create Event
	 * 
	 * @param eventdetailssheetname
	 * @param rowno
	 * @return the Event List Page
	 */
	public EventListPage createEvents(String eventdetailssheetname, int rowno) {
		commonUtil = new CommonUtility(DriverFactory.getDriver());
		eventListPage = new EventListPage(DriverFactory.getDriver());
		EventType = ec.getCellData("Event_Details", "Event Type", rowno);
		DisplayType = ec.getCellData("Event_Details", "Display Type", rowno);
		EventName = ec.getCellData("Event_Details", "Event Name", rowno);
		NextEventId = ec.getCellData("Event_Details", "Next Event Id", rowno);
		LibraryId = ec.getCellData("Event_Details", "Library Id", rowno);
		Framework = ec.getCellData("Event_Details", "Framework", rowno);
		DisplayStep = ec.getCellData("Event_Details", "Display Step", rowno);
		DisplayWorkflow = ec.getCellData("Event_Details", "Display Workflow", rowno);
		EventHandler = ec.getCellData("Event_Details", "Event Handler", rowno);
		ConditionID = ec.getCellData("Event_Details", "Condition ID", rowno);
		StepName = ec.getCellData("Event_Details", "Step Name", rowno);
		BlockName = ec.getCellData("Event_Details", "Block Name", rowno);
		FieldUniqueId = ec.getCellData("Event_Details", "Field Unique Id", rowno);
		ComparisonOperator = ec.getCellData("Event_Details", "Comparison Operator", rowno);
		FieldValue = ec.getCellData("Event_Details", "Field Value", rowno);
		ApiKey = ec.getCellData("Event_Details", "Api Key", rowno);
		Query = ec.getCellData("Event_Details", "Query", rowno);
		ParameterName = ec.getCellData("Event_Details", "Parameter Name", rowno);
		ParameterValue = ec.getCellData("Event_Details", "Parameter Value", rowno);
		HandlerName = ec.getCellData("Event_Details", "Handler Name", rowno);
		CallBckAPIkey = ec.getCellData("Event_Details", "CallBck API key", rowno);
		HeaderStatusCode = ec.getCellData("Event_Details", "Header Status Code", rowno);
		CallBckNextEventID = ec.getCellData("Event_Details", "CallBck Next Event ID", rowno);
		MessageCode = ec.getCellData("Event_Details", "Message Code", rowno);
		ResponseKey = ec.getCellData("Event_Details", "Response Key", rowno);
		ResponseValue = ec.getCellData("Event_Details", "Response Value", rowno);
		int randNum = commonUtil.generateRandomNumber();
		ModifiedEventName = EventName + "_" + randNum;
		try {
			ec.writeCellData("Event_Details", "Modified Event Name", rowno, ModifiedEventName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] testData1 = { EventType, DisplayType, ModifiedEventName, LibraryId };
		WebElement[] element1 = { drpdwnEventType, drpdwnDisplayType, txtEventName, txtLibraryId };
		commonUtil.typeIn(element1, testData1);
		commonUtil.onClick(chkboxIsDefault);
		commonUtil.onClick(drpdwnNextEventId);
		if (txtSearch.isDisplayed()) {
			commonUtil.onClick(txtSearch);
			commonUtil.doSearch(NextEventId);
			commonUtil.onClick(commonUtil.searchDropdown(searchDrpdwnSelect, NextEventId));
		} else {
			Log.error("Search box is not displaying");
		}
		if (DisplayType.equals("Step")) {
			commonUtil.onClick(drpdwnDisplayStep);
			drpdwnStepSearch.sendKeys(DisplayStep);
			commonUtil.onClick(commonUtil.searchDropdown(searchDrpdwnSelect, DisplayStep));

		} else if (DisplayType.equals("Workflow")) {
			commonUtil.onClick(drpdwnWorkflow);
			drpdwnWorkflow.sendKeys(DisplayWorkflow);
			commonUtil.onClick(commonUtil.searchDropdown(searchDrpdwnSelect, DisplayWorkflow));
		} else {
			commonUtil.onClick(drpdwnFramework);
			drpdwnFrameworkSearch.sendKeys(Framework);
			commonUtil.onClick(commonUtil.searchDropdown(searchDrpdwnSelect, Framework));
			txtEventHandler.sendKeys(EventHandler);

		}
		commonUtil.onClick(expandEventCondition);
		commonUtil.waitForElementToVisible(txtConditionId);
		String[] testData2 = { ConditionID, StepName, BlockName, FieldUniqueId, ComparisonOperator, FieldValue, ApiKey,
				Query };
		WebElement[] element2 = { txtConditionId, txtStepName, txtBlockName, txtFieldUniqId, drpdwnComparisonOperator,
				txtFieldValue, txtApiKey, txtQuery };
		commonUtil.typeIn(element2, testData2);
		commonUtil.onClick(expandAdditionalParameter);
		commonUtil.waitForElementToVisible(txtParameterName);
		String[] testData3 = { ParameterName, ParameterValue };
		WebElement[] element3 = { txtParameterName, txtParameterValue };
		commonUtil.typeIn(element3, testData3);
		commonUtil.onClick(expandAddEventCallBack);
		commonUtil.waitForElementToVisible(drpdwnHandlerName);
		String[] testData4 = { HandlerName, CallBckAPIkey, HeaderStatusCode, CallBckNextEventID, MessageCode,
				ResponseKey, ResponseValue };
		WebElement[] element4 = { drpdwnHandlerName, txtClBckApiKey, drpdwnHeaderStatusCode, drpdwnClBckNxtEventId,
				drpdwnClBckMsgCode, txtResponseKey, txtResponseValue };
		commonUtil.typeIn(element4, testData4);
		commonUtil.onClick(chkboxNavigatetoNextPage);
		commonUtil.onClick(btnSave);
		commonUtil.waitForElementToVisible(eventListPage.labelHeaderEventListPage);
		return new EventListPage(driver);

	}

	/**
	 * This method is used to verify the events data with excel data
	 */
	public void verifyEvent() {
		commonUtil = new CommonUtility(DriverFactory.getDriver());
		softassert = new SoftAssert();
		// Getting Basic config Data
		Select s1 = new Select(drpdwnEventType);
		WebElement eventType = s1.getFirstSelectedOption();
		String actualEventType = commonUtil.getText(eventType);
		Select s2 = new Select(drpdwnDisplayType);
		WebElement displayType = s2.getFirstSelectedOption();
		String actualDisplayType = commonUtil.getText(displayType);
		String actualEventName = commonUtil.getText(txtEventName);
		String actualNextEventType = commonUtil.getText(drpdwnNextEventId);
		String actualLibraryId = commonUtil.getText(txtLibraryId);
		softassert.assertTrue(chkboxIsDefault.isSelected(), " Default Checkbox is not selected");
		if (actualDisplayType.equals("Step")) {
			String actualDisplayStep = commonUtil.getText(drpdwnDisplayStep);
			softassert.assertEquals(actualDisplayStep, DisplayStep);
		} else if (actualDisplayType.equals("Workflow")) {
			String actualDisplayWorkflow = commonUtil.getText(drpdwnWorkflow);
			softassert.assertEquals(actualDisplayWorkflow, DisplayWorkflow);
		} else {
			String actualDisplayFramework = commonUtil.getText(drpdwnFramework);
			softassert.assertEquals(actualDisplayFramework, Framework);
		}
		// Getting Event Condition Data
		commonUtil.onClick(expandEventCondition);
		String actualConditionId = commonUtil.getText(txtConditionId);
		String actualStepName = commonUtil.getText(txtStepName);
		String actualBlockName = commonUtil.getText(txtBlockName);
		String actualFieldUniqId = commonUtil.getText(txtFieldUniqId);
		Select s3 = new Select(drpdwnComparisonOperator);
		WebElement conditionalOperator = s3.getFirstSelectedOption();
		String actualConditionalOperator = commonUtil.getText(conditionalOperator);
		String actualFieldValue = commonUtil.getText(txtFieldValue);
		String actualApiKey = commonUtil.getText(txtApiKey);
		String actualQuery = commonUtil.getText(txtQuery);
		// Getting Additional Parameter data
		commonUtil.onClick(expandAdditionalParameter);
		String actualParameterName = commonUtil.getText(txtParameterName);
		String actualParameterValue = commonUtil.getText(txtParameterValue);
		// Getting Event Call Back Data
		commonUtil.onClick(expandAddEventCallBack);
		Select s4 = new Select(drpdwnHandlerName);
		WebElement handlerName = s4.getFirstSelectedOption();
		String actualHandlerName = commonUtil.getText(handlerName);
		String actualClBckApiKey = commonUtil.getText(txtClBckApiKey);
		Select s5 = new Select(drpdwnHeaderStatusCode);
		WebElement headerStatusCode = s5.getFirstSelectedOption();
		String actualHeaderStatusCode = commonUtil.getText(headerStatusCode);
		Select s6 = new Select(drpdwnClBckNxtEventId);
		WebElement clBckNxtEventId = s6.getFirstSelectedOption();
		String actualClBckNxtEventId = commonUtil.getText(clBckNxtEventId);
		Select s7 = new Select(drpdwnClBckMsgCode);
		WebElement messageCode = s7.getFirstSelectedOption();
		String actualMessageCode = commonUtil.getText(messageCode);
		softassert.assertTrue(chkboxNavigatetoNextPage.isSelected(), "Navigate to next page check box is not selected");
		String actualResponseKey = commonUtil.getText(txtResponseKey);
		String actualResponseValue = commonUtil.getText(txtResponseValue);
		String[] actualData = { actualEventType, actualDisplayType, actualEventName, actualNextEventType,
				actualLibraryId, actualConditionId, actualStepName, actualBlockName, actualFieldUniqId,
				actualConditionalOperator, actualFieldValue, actualApiKey, actualQuery, actualParameterName,
				actualParameterValue, actualHandlerName, actualClBckApiKey, actualHeaderStatusCode,
				actualClBckNxtEventId, actualMessageCode, actualResponseKey, actualResponseValue };
		String[] expectedData = { EventType, DisplayType, ModifiedEventName, NextEventId, LibraryId, ConditionID,
				StepName, BlockName, FieldUniqueId, ComparisonOperator, FieldValue, ApiKey, Query, ParameterName,
				ParameterValue, HandlerName, CallBckAPIkey, HeaderStatusCode, CallBckNextEventID, MessageCode,
				ResponseKey, ResponseValue };
		commonUtil.softAssert(actualData, expectedData, softassert);
		softassert.assertAll();

	}
}
