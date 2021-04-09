package stepDefinitions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.junit.Assert;

import com.app.factory.DriverFactory;
import com.app.pages.ApplicationDashboard;
import com.app.pages.Header;
import com.app.pages.LoginPage;
import com.app.pages.ManageStepPage;
import com.app.pages.MyApplication;
import com.app.pages.NewApplication;
import com.app.pages.StepListPage;
import com.app.util.CommonUtility;
import com.app.util.ConfigReader;
import com.app.util.Excell;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepDefinition {

	private LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
	private MyApplication myApplication = new MyApplication(DriverFactory.getDriver());
	private static Excell ec;
	private NewApplication newApplication = new NewApplication(DriverFactory.getDriver());
	private CommonUtility commonUtil = new CommonUtility(DriverFactory.getDriver());
	private static Properties prop;
	private static ConfigReader configReader = new ConfigReader();
	private ApplicationDashboard applicationDashboard = new ApplicationDashboard(DriverFactory.getDriver());
	private StepListPage stepListPage = new StepListPage(DriverFactory.getDriver());
	private ManageStepPage manageStepPage = new ManageStepPage(DriverFactory.getDriver());
	private Header header = new Header(DriverFactory.getDriver());

	private String modifiedAppName = null;
	private String modifiedStepName = null;
	private String testDataPath = null;
	// private String removalAppName = null;

	@Given("^User has logged into the Portal$")
	public void user_has_logged_into_the_Portal(DataTable dataTable) {
		loginPage.openUrl();
		String actualTitle = loginPage.getLoginPageTitle();
		List<Map<String, String>> credentialList = dataTable.asMaps();
		String email = credentialList.get(0).get("Email");
		String password = credentialList.get(0).get("Password");
		String expectedTitle = credentialList.get(0).get("Title");
		Assert.assertTrue(actualTitle.equals(expectedTitle));
		loginPage.doLogin(email, password);
	}

	@Given("^User is in My Application page with the title \"([^\"]*)\"$")
	public void user_in_the_MyApplication_page(String expectedTitle) {
		String actualTitle = myApplication.getMyApplicationPageTitle();
		Assert.assertTrue(actualTitle.equals(expectedTitle));
		myApplication.clickOnCreateApplicationBtn();
	}

	@And("^Create an Application using data in sheetWithRow (.*) and (.*)$")
	public void provide_appName_and_platform_and_lang(String sheetName, String rowNo) {
		try {
			prop = configReader.init_prop();
			testDataPath = prop.getProperty("TestDataPath");
			ec = new Excell(testDataPath);
			String appName = ec.getCellData("Application_Details", "Application Name", 0);
			String platformName = ec.getCellData("Application_Details", "Platform", 0);
			String languageName = ec.getCellData("Application_Details", "Languages", 0);
			String paramName = ec.getCellData("Application_Details", "Parameter Name", 0);
			String paramValue = ec.getCellData("Application_Details", "Parameter Value", 0);
			// Random random = new Random();
			// int randNum = random.nextInt(1000);
			int randomNum = commonUtil.generateRandomNumber();
			modifiedAppName = appName + "_" + randomNum;
			System.out.println(modifiedAppName);
			newApplication.createApplication(modifiedAppName, platformName, languageName, paramName, paramValue);
			ec.writeCellData("Application_Details", "Modified Application Name", 1, modifiedAppName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Then("^Verify the Application in the list using data in sheetWithRow (.*) and (.*)$")
	public void application_will_display_in_the_list(String sheetName, String rowNo) {
		commonUtil.doSearch(modifiedAppName);
		String actualApplicationName = myApplication.getApplicationNameFromList();
		Assert.assertTrue(actualApplicationName.equals(modifiedAppName));
	}

	@Given("^User open an application from the list using data in sheetWithRow (.*) and (.*)$")
	public void user_open_an_application_from_the_list_using_data_in_sheetwithrow_and(
			String applicationdetailssheetname, String rowno) {
		configReader = new ConfigReader();
		prop = configReader.init_prop();
		String testDataPath = prop.getProperty("TestDataPath");
		try {
			ec = new Excell(testDataPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String appName = ec.getCellData("Application_Details", "Modified Application Name", 0);
		commonUtil.doSearch(appName);
		// String actualApplicationName = myApplication.getApplicationNameFromList();
		// Assert.assertTrue(actualApplicationName.equals(modifiedAppName));
		myApplication.openApplication();
		String actualTitle = applicationDashboard.getApplicationDashboardTitle();
		String expectedTitle = "Application Dashboard";
		Assert.assertTrue(actualTitle.equals(expectedTitle));
		applicationDashboard.clickOnStep();
	}

	@When("^User create a Step using data in sheetWithRow (.*) and (.*)$")
	public void user_create_a_step_using_data_in_sheetwithrow_and(String stepdetailssheetname, String rowno) {

		String actualTitle = stepListPage.getStepListPageTitle();
		String expectedTitle = "Application Steps";
		Assert.assertTrue(actualTitle.equals(expectedTitle));
		stepListPage.clickOnCreateStepBtn();
		try {
			configReader = new ConfigReader();
			prop = configReader.init_prop();
			String testDataPath = prop.getProperty("TestDataPath");
			ec = new Excell(testDataPath);
			String stepName = ec.getCellData("Step_Details", "Application Step Name", 0);
			String stepApiUrlName = ec.getCellData("Step_Details", "Step API URL", 0);
			String bootstrapClassName = ec.getCellData("Step_Details", "Bootstrap Class Name", 0);
			String customClassName = ec.getCellData("Step_Details", "Custom Class Name", 0);
			String parameterName = ec.getCellData("Step_Details", "Parameter Name", 0);
			String parameterValue = ec.getCellData("Step_Details", "Parameter Value", 0);
			String device_Type = ec.getCellData("Step_Details", "Device Type", 0);
			String templateId = ec.getCellData("Step_Details", "Template Id", 0);
			int randomNum = commonUtil.generateRandomNumber();
			modifiedStepName = stepName + "_" + randomNum;
			manageStepPage.createStep(modifiedStepName, stepApiUrlName, bootstrapClassName, customClassName,
					parameterName, parameterValue, device_Type, templateId);
			ec.writeCellData("Step_Details", "Modified Step Name", 1, modifiedStepName);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Then("^Verify the Step in the list using data in sheetWithRow (.*) and (.*)$")
	public void verify_the_step_in_the_list_using_data_in_sheetwithrow_and(String stepdetailssheetname, String rowno) {
		System.out.println(modifiedStepName);
		commonUtil.waitForStaleElement(header.txtSearch);
		commonUtil.doSearch(modifiedStepName);
	}

	@When("^user click on remove option of an application from the list using data in sheetWithRow (.*) and (.*)$")
	public void user_click_on_remove_option(String applicationremovalsheetname, String rowno) {
		try {
			configReader = new ConfigReader();
			prop = configReader.init_prop();
			String testDataPath = prop.getProperty("TestDataPath");
			ec = new Excell(testDataPath);
			ArrayList<String> removalAppName = ec.getCellDataAsList("Application_Removal", "Application Name");
			try {
				myApplication.removeApplication(removalAppName);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Then("^verify application will remove from the list using data in sheetWithRow (.*) and (.*)$")
	public void verify_application_will_remove_from_the_list_using_data_in_sheetwithrow_and(
			String applicationremovalsheetname, String rowno) {
		// commonUtil.doSearch(removalAppName);
		Assert.assertTrue(commonUtil.isDisplayed(header.txtNoRecordFound));

	}

	public static void main(String[] args) {

	}

}
