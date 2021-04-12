package stepDefinitions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.junit.Assert;

import com.app.factory.DriverFactory;
import com.app.pages.ApplicationDashboard;
import com.app.pages.BasePage;
import com.app.pages.FieldListPage;
import com.app.pages.LoginPage;
import com.app.pages.ManageFieldPage;
import com.app.pages.ManageSectionPage;
import com.app.pages.ManageStepPage;
import com.app.pages.MyApplication;
import com.app.pages.NewApplication;
import com.app.pages.SectionListPage;
import com.app.pages.StepListPage;
import com.app.util.CommonUtility;
import com.app.util.ConfigReader;
import com.app.util.Excell;
import com.app.util.Log;

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
	private BasePage base = new BasePage(DriverFactory.getDriver());
	private SectionListPage sectionListPage = new SectionListPage(DriverFactory.getDriver());
	private ManageSectionPage manageSectionPage = new ManageSectionPage(DriverFactory.getDriver());
	private FieldListPage fieldListPage = new FieldListPage(DriverFactory.getDriver());
	private ManageFieldPage manageFieldPage = new ManageFieldPage(DriverFactory.getDriver());

	private String modifiedAppName = null;
	private String modifiedStepName = null;
	private String modifiedSectionName = null;

	// private String removalAppName = null;

	static {

		prop = configReader.init_prop();

	}

	@Given("^User has logged into the Portal$")
	public void user_has_logged_into_the_Portal(DataTable dataTable) {
		base.openUrl(prop.getProperty("URL"));
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
		// Assert.assertTrue(actualTitle.equals(expectedTitle));
		if (actualTitle.equals(expectedTitle)) {
			Log.info("Title matched: " + "Expected: " + expectedTitle + "And" + "Found: " + actualTitle);
		} else {
			Log.error("Title do not matched: " + "Expected: " + expectedTitle + "And" + "Found: " + actualTitle);
		}

		myApplication.clickOnCreateApplicationBtn();
	}

	@And("^Create an Application using data in sheetWithRow (.*) and (.*)$")
	public void provide_appName_and_platform_and_lang(String sheetName, String rowNo) {
		try {
			// prop = configReader.init_prop();
			// testDataPath = prop.getProperty("TestDataPath");
			ec = new Excell(prop.getProperty("TestDataPath"));
			String appName = ec.getCellData("Application_Details", "Application Name", 0);
			String platformName = ec.getCellData("Application_Details", "Platform", 0);
			String languageName = ec.getCellData("Application_Details", "Languages", 0);
			String paramName = ec.getCellData("Application_Details", "Parameter Name", 0);
			String paramValue = ec.getCellData("Application_Details", "Parameter Value", 0);
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
		commonUtil.waitForElementToVisible(myApplication.appListTable);
		commonUtil.doSearch(modifiedAppName);
		String actualApplicationName = myApplication.getApplicationNameFromList();
		Assert.assertTrue(actualApplicationName.equals(modifiedAppName));
	}

	@Given("^User open an application from the list using data in sheetWithRow (.*) and (.*)$")
	public void user_open_an_application_from_the_list_using_data_in_sheetwithrow_and(
			String applicationdetailssheetname, String rowno) {
		try {
			ec = new Excell(prop.getProperty("TestDataPath"));
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
			ec = new Excell(prop.getProperty("TestDataPath"));
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
		commonUtil.doSearch(modifiedStepName);
	}

	@And("^User create a Section using data in sheetWithRow (.*) and (.*)$")
	public void user_create_a_section_using_data_in_sheetwithrow_and(String sectiondetailssheetname, String rowno) {
		sectionListPage.clickOnCreateSectionBtn();
		if (manageSectionPage.headerTitleManageSection().equals("Manage Section")) {
			Log.info("User is on Manage Section Page: ");
		} else {
			Log.error("User is not on Manage Section Page: ");
		}
		try {
			ec = new Excell(prop.getProperty("TestDataPath"));
			String SectionNumber = ec.getCellData("Section_Details", "Section Number", 0);
			String SectionName = ec.getCellData("Section_Details", "Section Name", 0);
			String SequenceNumber = ec.getCellData("Section_Details", "Sequence Number", 0);
			String SectionClassName = ec.getCellData("Section_Details", "Section Class Name", 0);
			String BootstrapClassName = ec.getCellData("Section_Details", "Bootstrap Class Name", 0);
			String CustomClassName = ec.getCellData("Section_Details", "Custom Class Name", 0);
			String ParameterName = ec.getCellData("Section_Details", "Parameter Name", 0);
			String ParameterValue = ec.getCellData("Section_Details", "Parameter Value", 0);
			int randomNum = commonUtil.generateRandomNumber();
			modifiedSectionName = SectionName + "_" + randomNum;
			manageSectionPage.createSection(SectionNumber, modifiedSectionName, SequenceNumber, SectionClassName,
					BootstrapClassName, CustomClassName, ParameterName, ParameterValue);
			ec.writeCellData("Section_Details", "Modified Section Name", 1, modifiedSectionName);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Then("^Verify the Section in the list using data in sheetWithRow (.*) and (.*)$")
	public void verify_the_section_in_the_list_using_data_in_sheetwithrow_and(String sectiondetailssheetname,
			String rowno) {
		commonUtil.doSearch(modifiedSectionName);
	}

	@When("^User open an Step from the list using data in sheetWithRow (.*) and (.*)$")
	public void user_open_an_step_from_the_list_using_data_in_sheetwithrow_and(String stepdetailssheetname,
			String rowno) {
		try {
			ec = new Excell(prop.getProperty("TestDataPath"));
			String stepName = ec.getCellData("Step_Details", "Modified Step Name", 0);
			commonUtil.doSearch(stepName);
			stepListPage.openStep();
			if (sectionListPage.getSectionListPageTitle().equals("Application Section")) {
				Log.info("Section List page is displaying with title: " + sectionListPage.getSectionListPageTitle());
			} else {
				Log.error("Section List page is not displaying with title:");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@And("^User open an Section from the list using data in sheetWithRow (.*) and (.*)$")
	public void user_open_an_section_from_the_list_using_data_in_sheetwithrow_and(String sectiondetailssheetname,
			String rowno) {
		try {
			ec = new Excell(prop.getProperty("TestDataPath"));
			String sectionName = ec.getCellData("Section_Details", "Modified Section Name", 0);
			commonUtil.doSearch(sectionName);
			sectionListPage.openSection();
			if (fieldListPage.labelHeaderFieldListPage().equals("Application Step Fields")) {
				Log.info("User is on Step Field List page :");
			} else {
				Log.error("User is not on Step Field List page :");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@And("^User create a Field using data in sheetWithRow (.*) and (.*)$")
	public void user_create_a_field_using_data_in_sheetwithrow_and(String fielddetailssheetname, String rowno) {
		fieldListPage.clickOnCreateNewFieldBtn();
		try {
			ec = new Excell(prop.getProperty("TestDataPath"));
			String UniqueId = ec.getCellData("Field_Details", "Unique Id", 0);
			String FieldLabel = ec.getCellData("Field_Details", "Field Label", 0);
			String FieldType = ec.getCellData("Field_Details", "Field Type", 0);
			String FieldSequence = ec.getCellData("Field_Details", "Field Sequence", 0);
			String PlaceholderText = ec.getCellData("Field_Details", "Placeholder Text", 0);
			String Tooltip = ec.getCellData("Field_Details", "Tooltip", 0);
			String FieldCharacterLimit = ec.getCellData("Field_Details", "Field Character Limit", 0);
			String APIKey = ec.getCellData("Field_Details", "API Key", 0);
			String RequestAPIKey = ec.getCellData("Field_Details", "Request API Key", 0);
			String ResponseAPIKey = ec.getCellData("Field_Details", "Response API Key", 0);
			String CSSClassName = ec.getCellData("Field_Details", "CSS Class Name", 0);
			String DefaultValue = ec.getCellData("Field_Details", "Default Value", 0);
			String PossibleValues = ec.getCellData("Field_Details", "Possible Values", 0);
			String BootstrapClassName = ec.getCellData("Field_Details", "Bootstrap Class Name", 0);
			String CustomClassName = ec.getCellData("Field_Details", "Custom Class Name", 0);
			String ParameterName = ec.getCellData("Field_Details", "Parameter Name", 0);
			String ParameterValue = ec.getCellData("Field_Details", "Parameter Value", 0);
			manageFieldPage.createField(UniqueId, FieldLabel, FieldType, FieldSequence, PlaceholderText, Tooltip,
					FieldCharacterLimit, APIKey, RequestAPIKey, ResponseAPIKey, CSSClassName, DefaultValue,
					PossibleValues, BootstrapClassName, CustomClassName, ParameterName, ParameterValue);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Then("^Verify the Field in the list using data in sheetWithRow (.*) and (.*)$")
	public void verify_the_field_in_the_list_using_data_in_sheetwithrow_and(String fielddetailssheetname,
			String rowno) {

	}

	@When("^user click on remove option of an application from the list using data in sheetWithRow (.*) and (.*)$")
	public void user_click_on_remove_option(String applicationremovalsheetname, String rowno) {
		try {
			ec = new Excell(prop.getProperty("TestDataPath"));
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
		// Assert.assertTrue(commonUtil.isDisplayed(header.txtNoRecordFound));

	}

	public static void main(String[] args) {

	}

}
