package AppHooks;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.app.factory.DriverFactory;
import com.app.util.ConfigReader;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class ApplicationHooks {

	private ConfigReader configReader;
	private DriverFactory driverFactory;
	private WebDriver driver;
	private Properties prop;

	@Before(order = 0)
	public void getProperty() {
		configReader = new ConfigReader();
		prop = configReader.init_prop();
		PropertyConfigurator.configure("log4j.properties");
	}

	@Before(order = 1)
	public void launchBrowser() {
		String browserName = prop.getProperty("browser");

		driverFactory = new DriverFactory();
		driver = driverFactory.initializeDriver(browserName);
	}

	@After(order = 0)
	public void quitBrowser() {
		try {
			Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
			driver.quit();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@After(order = 1)
	public void takeScreenshot(Scenario scenario) {
		if (scenario.isFailed()) {
			// take screenshot
			String screenshotName = scenario.getName().replaceAll(" ", "_");
			byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(sourcePath, "image/png", screenshotName);
		}

	}

}
