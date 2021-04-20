package com.app.pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.app.factory.DriverFactory;
import com.app.util.CommonUtility;
import com.app.util.Log;

public class ModalManagePage extends BasePage {

	public ModalManagePage(WebDriver driver) {
		super(driver);
	}

	private CommonUtility commonUtil;
	private ModalListPage modalListPage;

	@FindBy(xpath = "//h2[text()='Manage Modal']")
	public WebElement labelTxtHeaderModalManagePage;

	@FindBy(xpath = "//input[@id='modalId']")
	public WebElement txtModalId;

	@FindBy(xpath = "//select[@id='type']")
	public WebElement drpdwnModalType;

	@FindBy(xpath = "//input[@id='modalTitle']")
	public WebElement txtModalTitle;

	@FindBy(xpath = "//input[@id='closeButtonImgUrl']")
	public WebElement txtIconForModalCloseBtn;

	@FindBy(xpath = "//textarea[@id='modalMessage']")
	public WebElement txtareaModalMessage;

	@FindBy(xpath = "//input[@id='inputFormFields']")
	public WebElement txtInputFormFields;

	@FindBy(xpath = "//button[contains(text(),'Configure Modal Buttons')]")
	public WebElement expandConfigModalButtons;

	@FindBy(xpath = "//input[@name='buttonLabel']")
	public WebElement txtButtonLabel;

	@FindBy(xpath = "//select[@name='buttonType']")
	public WebElement drpdwnButtonType;

	@FindBy(xpath = "//select[@name='linkEvent']")
	public WebElement drpdwnLinkEvent;

	private static String ModalId = null;
	private static String ModalType = null;
	private static String ModalTitle = null;
	private static String IconForModalCloseBtn = null;
	private static String ModalMessage = null;
	private static String InputFormFields = null;
	private static String ButtonLabel = null;
	private static String ButtonType = null;
	private static String EventId = null;
	private static String ModifiedModalId = null;

	/**
	 * This method is used to create Modal using test data from Excel
	 * 
	 * @param modaldetailssheetname
	 * @param rowno
	 * @return the Modal List Page
	 */
	public ModalListPage createModal(String modaldetailssheetname, int rowno) {
		commonUtil = new CommonUtility(DriverFactory.getDriver());
		modalListPage = new ModalListPage(DriverFactory.getDriver());
		ModalId = ec.getCellData("Modal_Details", "Modal Id", rowno);
		ModalType = ec.getCellData("Modal_Details", "Modal Type", rowno);
		ModalTitle = ec.getCellData("Modal_Details", "Modal Title", rowno);
		IconForModalCloseBtn = ec.getCellData("Modal_Details", "Icon For Modal Close Button", rowno);
		ModalMessage = ec.getCellData("Modal_Details", "Modal Message Text", rowno);
		InputFormFields = ec.getCellData("Modal_Details", "Enter value for input form fields", rowno);
		ButtonLabel = ec.getCellData("Modal_Details", "Button Label", rowno);
		ButtonType = ec.getCellData("Modal_Details", "Button Type", rowno);
		int randNum = commonUtil.generateRandomNumber();
		ModifiedModalId = ModalId + "_" + randNum;
		try {
			ec.writeCellData("Modal_Details", "Modified Modal Id", rowno, ModifiedModalId);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] testData1 = { ModifiedModalId, ModalType, ModalTitle, IconForModalCloseBtn, ModalMessage,
				InputFormFields };
		WebElement[] element1 = { txtModalId, drpdwnModalType, txtModalTitle, txtIconForModalCloseBtn,
				txtareaModalMessage, txtInputFormFields };
		commonUtil.typeIn(element1, testData1);
		commonUtil.onClick(expandConfigModalButtons);
		commonUtil.waitForElementToVisible(txtButtonLabel);
		String[] testData2 = { ButtonLabel, ButtonType };
		WebElement[] element2 = { txtButtonLabel, drpdwnButtonType };
		commonUtil.typeIn(element2, testData2);
		commonUtil.onClick(btnSave);
		commonUtil.waitForElementToVisible(modalListPage.labelTxtHeaderModalListPage);
		return new ModalListPage(driver);
	}

	/**
	 * This method is used to Verify Modal using test data from Excel
	 * 
	 */
	public void verifyModal() {
		// commonUtil = new CommonUtility(DriverFactory.getDriver());
		String actualModalId = txtModalId.getAttribute("value");
		if (actualModalId.equals(ModifiedModalId)) {
			Log.info("Modal Id matched " + " Expected: " + ModifiedModalId + " Found: " + actualModalId);
		} else {
			Log.error("Modal Id does not matched " + " Expected: " + ModifiedModalId + " Found: " + actualModalId);
		}
		String actualModalType = drpdwnModalType.getAttribute("value");
		if (actualModalType.equalsIgnoreCase(ModalType)) {
			Log.info("Modal Type matched " + " Expected: " + ModalType + " Found: " + actualModalType);
		} else {
			Log.error("Modal Type does not matched " + " Expected: " + ModalType + " Found: " + actualModalType);
		}
		String actualModalTitle = txtModalTitle.getAttribute("value");
		if (actualModalTitle.equalsIgnoreCase(ModalTitle)) {
			Log.info("Modal Title matched " + " Expected: " + ModalTitle + " Found: " + actualModalTitle);
		} else {
			Log.error("Modal Title does not matched " + " Expected: " + ModalTitle + " Found: " + actualModalTitle);
		}
		String actualIconForModalCloseBtn = txtIconForModalCloseBtn.getAttribute("value");
		if (actualIconForModalCloseBtn.equalsIgnoreCase(IconForModalCloseBtn)) {
			Log.info("Icon For Modal Close Button matched " + " Expected: " + IconForModalCloseBtn + " Found: "
					+ actualIconForModalCloseBtn);
		} else {
			Log.error("Icon For Modal Close Button does not matched " + " Expected: " + IconForModalCloseBtn
					+ " Found: " + actualIconForModalCloseBtn);
		}
		String actualModalMessage = txtareaModalMessage.getAttribute("value");
		if (actualModalMessage.equalsIgnoreCase(ModalMessage)) {
			Log.info("Icon For Modal Close Button matched " + " Expected: " + ModalMessage + " Found: "
					+ actualModalMessage);
		} else {
			Log.error("Icon For Modal Close Button does not matched " + " Expected: " + ModalMessage + " Found: "
					+ actualModalMessage);
		}
		String actualValueforInputFormFields = txtInputFormFields.getAttribute("value");
		if (actualValueforInputFormFields.equalsIgnoreCase(InputFormFields)) {
			Log.info("Input Form Fields matched " + " Expected: " + InputFormFields + " Found: "
					+ actualValueforInputFormFields);
		} else {
			Log.error("Input Form Fields does not matched " + " Expected: " + InputFormFields + " Found: "
					+ actualValueforInputFormFields);
		}
		// commonUtil.onClick(loc);
		String actualButtonLabel = txtButtonLabel.getAttribute("value");
		if (actualButtonLabel.equalsIgnoreCase(ButtonLabel)) {
			Log.info("Button Label matched " + " Expected: " + ButtonLabel + " Found: " + actualButtonLabel);
		} else {
			Log.error("Button Label does not matched " + " Expected: " + ButtonLabel + " Found: " + actualButtonLabel);
		}
		String actualButtonType = drpdwnButtonType.getAttribute("value");
		if (actualButtonType.equalsIgnoreCase(ButtonType)) {
			Log.info("Button Type matched " + " Expected: " + ButtonType + " Found: " + actualButtonType);
		} else {
			Log.error("Button Type does not matched " + " Expected: " + ButtonType + " Found: " + actualButtonType);
		}
	}

}
