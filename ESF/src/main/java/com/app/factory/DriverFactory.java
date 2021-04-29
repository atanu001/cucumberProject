package com.app.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	public WebDriver driver;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

	/**
	 * This method is used to initialize the thread local driver on the basis of
	 * given driver
	 * 
	 * @param browser
	 * @return this will return the tlDriver
	 */

	public WebDriver initializeDriver(String browser) {
		if (browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			tlDriver.set(new ChromeDriver());
		} else if (browser.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			tlDriver.set(new FirefoxDriver());
		} else if (browser.equals("safari")) {
			tlDriver.set(new SafariDriver());
		} else {
			System.out.println("Please provide correct browser :" + browser);
		}

		getDriver().manage().deleteAllCookies();
//		getDriver().get("chrome://settings/clearBrowserData");
//		getDriver().findElement(By.xpath("//settings-ui")).sendKeys(Keys.ENTER);
		getDriver().manage().window().maximize();
		return getDriver();

	}

	/**
	 * This is used to get the driver with ThreadLocal
	 * 
	 * @return
	 */

	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
}
