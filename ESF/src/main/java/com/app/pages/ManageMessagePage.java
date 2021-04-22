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

public class ManageMessagePage extends BasePage {

	public ManageMessagePage(WebDriver driver) {
		super(driver);
	}

	private CommonUtility commonUtil;
	private MessageListPage messageListPage;

	@FindBy(xpath = "//h2[text()='Manage Messages']")
	public WebElement labelTxtHeaderManageMessagePage;

	@FindBy(xpath = "//input[@id='messageCode']")
	private WebElement txtEnterMessageCode;

	@FindBy(xpath = "//select[@id='msgtype']")
	private WebElement drpdwnMessageType;

	@FindBy(xpath = "//textarea[@id='msgtext']")
	private WebElement txtareaMessage;

	private static String MessageCode = null;
	private static String MessageType = null;
	private static String Message = null;
	private static String ModifiedMessageCode = null;

	/**
	 * This method will create a Message with test data from Excel
	 * 
	 * @param messagedetailssheetname
	 * @param rowno
	 * @return the Message List Page
	 */
	public MessageListPage createMessage(String messagedetailssheetname, int rowno) {
		commonUtil = new CommonUtility(DriverFactory.getDriver());
		messageListPage = new MessageListPage(DriverFactory.getDriver());
		MessageCode = ec.getCellData("Message_Details", "Enter a message code", rowno);
		MessageType = ec.getCellData("Message_Details", "Select a message type", rowno);
		Message = ec.getCellData("Message_Details", "Enter the message to be displayed", rowno);
		int randNum = commonUtil.generateRandomNumber();
		ModifiedMessageCode = MessageCode + "_" + randNum;
		try {
			ec.writeCellData("Message_Details", "Modified Message Code", rowno, ModifiedMessageCode);
			ec.writeCellData("Validation_Message_Details", "Associate Message Code", rowno, ModifiedMessageCode);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] testData = { ModifiedMessageCode, MessageType, Message };
		WebElement[] locator = { txtEnterMessageCode, drpdwnMessageType, txtareaMessage };
		commonUtil.typeIn(locator, testData);
		commonUtil.onClick(btnSave);
		commonUtil.waitForElementToVisible(messageListPage.labelTxtHeaderMessageListPage);
		return new MessageListPage(driver);
	}

	/**
	 * This method will verify the input data with Excel test data
	 */
	public void verifyMessage() {
		String actualMessageCode = txtEnterMessageCode.getAttribute("value");
		SoftAssert softassert = new SoftAssert();
		softassert.assertEquals(actualMessageCode, ModifiedMessageCode);
		if (actualMessageCode.equals(ModifiedMessageCode)) {
			Log.info("Message Code is matched " + " Expected: " + ModifiedMessageCode + " Found: " + actualMessageCode);
		} else {
			Log.error("Message Code does not matched " + " Expected: " + ModifiedMessageCode + " Found: "
					+ actualMessageCode);
		}
		Select selectMessageType = new Select(drpdwnMessageType);
		WebElement eleMessageType = selectMessageType.getFirstSelectedOption();
		String actualMessageType = eleMessageType.getText();
		if (actualMessageType.contains(MessageType)) {
			Log.info("Message Type is matched " + " Expected: " + MessageType + " Found: " + actualMessageType);
		} else {
			Log.error("Message Type does not matched " + " Expected: " + MessageType + " Found: " + actualMessageType);
		}
		String actualMessage = txtareaMessage.getAttribute("value");
		if (actualMessage.equals(Message)) {
			Log.info("Message is matched " + " Expected: " + Message + " Found: " + actualMessage);
		} else {
			Log.error("Message does not matched " + " Expected: " + Message + " Found: " + actualMessage);
		}
		softassert.assertAll();
	}

}
