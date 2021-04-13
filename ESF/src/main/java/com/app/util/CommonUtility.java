package com.app.util;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.app.factory.DriverFactory;
import com.app.pages.BasePage;

/**
 * This class will contain all the common method
 * 
 * @author Atanu Samanta
 */
public class CommonUtility {

	private WebDriver driver;
	private BasePage header = new BasePage(DriverFactory.getDriver());

	public CommonUtility(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void doSearch(String text) {
		try {
			header.txtSearch.sendKeys(text);
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void refresh(WebElement element) {
		element.sendKeys(Keys.F5);
	}

	public void waitForStaleElement(WebElement Element) {
		try {
			WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), 20);
			wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(Element)));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public int generateRandomNumber() {
		Random random = new Random();
		int randNum = random.nextInt(1000);
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

	public void onClick(WebElement loc) {
		loc.click();
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

	/**
	 * This method is used to wait the driver for the Element to be Clickable driver
	 * will wait 10 s
	 * 
	 * @param Element
	 */

	public void waitForElementToBeClickable(WebElement Element) {
		try {
			WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), 10);
			wait.until(ExpectedConditions.elementToBeClickable(Element));
		} catch (Exception e) {
			try {
				Thread.sleep(2000);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	/*
	 * public void waitForElementToInvisible(By locator) { try { WebDriverWait wait
	 * = new WebDriverWait(DriverFactory.getDriver(), Configuration.defaultTimeOut);
	 * wait.until(ExpectedConditions.refreshed(ExpectedConditions.
	 * invisibilityOfElementLocated(locator))); } catch (Exception e) { try {
	 * Thread.sleep(2000); } catch (Exception ex) { ex.printStackTrace(); } }
	 * 
	 * }
	 */

	/**
	 * This method is used to wait the driver for the Element to be visible will
	 * wait 15 s
	 * 
	 * @param Element
	 */

	public void waitForElementToVisible(WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), 15);
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			try {
				Thread.sleep(2000);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

	}

	public void waitForElementToEnabled(By locator) {
		try {
			WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), 5);
			wait.until(ExpectedConditions.elementToBeClickable(locator));
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
