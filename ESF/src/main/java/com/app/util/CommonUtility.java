package com.app.util;

import java.time.Duration;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.app.factory.DriverFactory;
import com.app.pages.BasePage;

/**
 * This class will contain all the common method
 * 
 * @author Atanu Samanta
 */
public class CommonUtility {

	private WebDriver driver;
	private BasePage basePage;
	// private SoftAssert softassert;

	public CommonUtility(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void doSearch(String text) {
		basePage = new BasePage(DriverFactory.getDriver());
		waitForElementToVisible(basePage.txtSearch);
		try {
			basePage.txtSearch.sendKeys(text);
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void softAssert(String[] actualData, String[] expectedData, SoftAssert softassert) {
		for (int i = 0; i < expectedData.length; i++) {
			softassert.assertEquals(actualData[i], expectedData[i]);
		}
	}

	public String getText(WebElement element) {
		String s1 = null;
		waitForElementToVisible(element);
		if (StringUtils.isEmpty(element.getText().trim())) {
			s1 = element.getAttribute("value").trim();
		} else {
			s1 = element.getText().trim();
		}
		return s1;
	}

	public WebElement searchDropdown(String locator, String text) {
		return driver.findElement(By.xpath(String.format(locator, text)));
	}

	public void refresh(WebElement element) {
		element.sendKeys(Keys.F5);
	}

	public void waitForStaleElement(WebElement Element) {
		try {
			WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), 20);
			wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(Element)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int generateRandomNumber() {
		Random random = new Random();
		int randNum = random.nextInt(10000);
		return randNum;
	}

	public void typeIn(WebElement[] loc, String[] str) {
		for (int i = 0; i < str.length; i++) {
			loc[i].sendKeys(str[i]);
		}
	}

	public void typeIn(By[] loc, String[] str) {
		for (int i = 0; i < str.length; i++) {
			driver.findElement(loc[i]).sendKeys(str[i]);
		}
	}

	public void onClick(WebElement element) {
		waitForElementToBeClickable(element);
		try {
			element.click();
		} catch (ElementNotInteractableException e) {
			clickByJavaScriptExecutor(element);
		}

	}

	public boolean isDisplayed(WebElement Element) {
		return Element.isDisplayed();
	}

	public void scrollDownToVisibleElement(WebElement Element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].scrollIntoView;", Element);
	}

	public void scrollDownToVisibleElement(By loc) {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].scrollIntoView;", loc);
	}

	public void scrollDownToBottomPage() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public void clickByJavaScriptExecutor(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}

	/**
	 * This method is used to wait the driver for the Element to be Clickable driver
	 * will check in every 2s for 60 s
	 * 
	 * @param Element
	 */

	public void waitForElementToBeClickable(WebElement Element) {
		try {
			FluentWait<WebDriver> wait = new WebDriverWait(DriverFactory.getDriver(), 60)
					.pollingEvery(Duration.ofSeconds(2));
			wait.until(ExpectedConditions.elementToBeClickable(Element));
		} catch (Exception e) {
			try {
				Thread.sleep(2000);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * This method is used to wait the driver for the Element to be visible will
	 * check in every 2s for 60s
	 * 
	 * @param Element
	 */

	public void waitForElementToVisible(WebElement element) {
		try {
			FluentWait<WebDriver> wait = new WebDriverWait(DriverFactory.getDriver(), 60)
					.pollingEvery(Duration.ofSeconds(2));
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			try {
				Thread.sleep(2000);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

	}

	public void acceptJavaScripAlert() {
		driver.switchTo().alert().accept();
	}

}
