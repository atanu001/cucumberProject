package stepDefinitions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.junit.Assert;

import com.app.factory.DriverFactory;
import com.app.pages.AddConditionPage;
import com.app.pages.ApplicationDashboard;
import com.app.pages.BasePage;
import com.app.pages.ConditionListPage;
import com.app.pages.FieldListPage;
import com.app.pages.LoginPage;
import com.app.pages.ManageFieldPage;
import com.app.pages.ManageSectionPage;
import com.app.pages.ManageStepPage;
import com.app.pages.MyApplication;
import com.app.pages.NewApplication;
import com.app.pages.SectionListPage;
import com.app.pages.StepListPage;
import com.app.util.ConfigReader;
import com.app.util.Excell;
import com.app.util.Log;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepDefinition {

	private LoginPage loginPage;
	private MyApplication myApplication;
	private NewApplication newApplication;
	private static Properties prop;
	private Excell ec;
	private static ConfigReader configReader;
	private ApplicationDashboard applicationDashboard;
	private StepListPage stepListPage;
	private ManageStepPage manageStepPage;
	private BasePage base;
	private SectionListPage sectionListPage;
	private ManageSectionPage manageSectionPage;
	private FieldListPage fieldListPage;
	private ManageFieldPage manageFieldPage;
	private ConditionListPage conditionListPage;
	private AddConditionPage addConditionPage;

	// private String removalAppName = null;

	static {
		configReader = new ConfigReader();
		prop = configReader.init_prop();

	}

	@Given("^User has logged into the Portal$")
	public void user_has_logged_into_the_Portal(DataTable dataTable) {
		base = new BasePage(DriverFactory.getDriver());
		loginPage = new LoginPage(DriverFactory.getDriver());
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
		myApplication = new MyApplication(DriverFactory.getDriver());
		String actualTitle = myApplication.getMyApplicationPageTitle();
		Assert.assertTrue(actualTitle.equals(expectedTitle));
		if (actualTitle.equals(expectedTitle)) {
			Log.info("Title matched: " + "Expected: " + expectedTitle + "And" + "Found: " + actualTitle);
		} else {
			Log.error("Title do not matched: " + "Expected: " + expectedTitle + "And" + "Found: " + actualTitle);
		}

		newApplication = myApplication.clickOnCreateApplicationBtn();
	}

	@And("^Create an Application using data in sheetWithRow (.*) and (.*)$")
	public void provide_appName_and_platform_and_lang(String sheetName, int rowNo) {
		newApplication.createApplication(sheetName, rowNo);
	}

	@Then("^Verify the Application in the list using data in sheetWithRow (.*) and (.*)$")
	public void application_will_display_in_the_list(String sheetName, int rowNo) {
		newApplication = new NewApplication(DriverFactory.getDriver());
		newApplication.verifyApplication(sheetName, rowNo);
	}

	@Given("^User open an application from the list using data in sheetWithRow (.*) and (.*)$")
	public void user_open_an_application_from_the_list_using_data_in_sheetwithrow_and(
			String applicationdetailssheetname, int rowno) {
		myApplication = new MyApplication(DriverFactory.getDriver());
		applicationDashboard = myApplication.openApplication(applicationdetailssheetname, rowno);
		String actualTitle = applicationDashboard.getApplicationDashboardTitle();
		String expectedTitle = "Application Dashboard";
		Assert.assertTrue(actualTitle.equals(expectedTitle));

	}

	@When("^User create a Step using data in sheetWithRow (.*) and (.*)$")
	public void user_create_a_step_using_data_in_sheetwithrow_and(String stepdetailssheetname, int rowno) {
		stepListPage = applicationDashboard.clickOnStep();
		String actualTitle = stepListPage.getStepListPageTitle();
		String expectedTitle = "Application Steps";
		Assert.assertTrue(actualTitle.equals(expectedTitle));
		manageStepPage = stepListPage.clickOnCreateStepBtn();
		stepListPage = manageStepPage.createStep(stepdetailssheetname, rowno);
	}

	@Then("^Verify the Step in the list using data in sheetWithRow (.*) and (.*)$")
	public void verify_the_step_in_the_list_using_data_in_sheetwithrow_and(String stepdetailssheetname, int rowno) {
		manageStepPage = stepListPage.editStep(stepdetailssheetname, rowno);
		manageStepPage.verifyStep(stepdetailssheetname, rowno);
	}

	@And("^User create a Section using data in sheetWithRow (.*) and (.*)$")
	public void user_create_a_section_using_data_in_sheetwithrow_and(String sectiondetailssheetname, int rowno) {
		manageSectionPage = sectionListPage.clickOnCreateSectionBtn();
		if (manageSectionPage.labelHeaderManageSection().equals("Manage Section")) {
			Log.info("User is on Manage Section Page: ");
		} else {
			Log.error("User is not on Manage Section Page: ");
		}
		sectionListPage = manageSectionPage.createSection(sectiondetailssheetname, rowno);
	}

	@Then("^Verify the Section in the list using data in sheetWithRow (.*) and (.*)$")
	public void verify_the_section_in_the_list_using_data_in_sheetwithrow_and(String sectiondetailssheetname,
			int rowno) {
		manageSectionPage = sectionListPage.editSection(sectiondetailssheetname, rowno);
		manageSectionPage.verifySection(sectiondetailssheetname, rowno);
	}

	@When("^User open an Step from the list using data in sheetWithRow (.*) and (.*)$")
	public void user_open_an_step_from_the_list_using_data_in_sheetwithrow_and(String stepdetailssheetname, int rowno) {
		sectionListPage = stepListPage.openStep(stepdetailssheetname, rowno);
		if (sectionListPage.getSectionListPageTitle().equals("Application Sections")) {
			Log.info("Section List page is displaying with title: " + sectionListPage.getSectionListPageTitle());
		} else {
			Log.error("Section List page is not displaying with title:");
		}
	}

	@And("^User open an Section from the list using data in sheetWithRow (.*) and (.*)$")
	public void user_open_an_section_from_the_list_using_data_in_sheetwithrow_and(String sectiondetailssheetname,
			int rowno) {
		fieldListPage = sectionListPage.openSection(sectiondetailssheetname, rowno);
		if (fieldListPage.labelHeaderFieldListPage().equals("Application Step Fields")) {
			Log.info("User is on Step Field List page :");
		} else {
			Log.error("User is not on Step Field List page :");
		}

	}

	@And("^User create a Field using data in sheetWithRow (.*) and (.*)$")
	public void user_create_a_field_using_data_in_sheetwithrow_and(String fielddetailssheetname, int rowno) {
		manageFieldPage = fieldListPage.clickOnCreateNewFieldBtn();
		fieldListPage = manageFieldPage.createField(fielddetailssheetname, rowno);

	}

	@Then("^Verify the Field in the list using data in sheetWithRow (.*) and (.*)$")
	public void verify_the_field_in_the_list_using_data_in_sheetwithrow_and(String fielddetailssheetname, int rowno) {
		manageFieldPage = fieldListPage.editField(fielddetailssheetname, rowno);
		manageFieldPage.verifyField(fielddetailssheetname, rowno);
	}

	@When("^user click on remove option of an application from the list using data in sheetWithRow (.*) and (.*)$")
	public void user_click_on_remove_option(String applicationremovalsheetname, int rowno) {
		try {
			myApplication = new MyApplication(DriverFactory.getDriver());
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

	@When("^User create a Block using data in sheetWithRow (.*) and (.*)$")
	public void user_create_a_block_using_data_in_sheetwithrow_and(String blockdetailssheetname, String rowno) {

	}

	@Then("^Verify the Block in the list using data in sheetWithRow (.*) and (.*)$")
	public void verify_the_block_in_the_list_using_data_in_sheetwithrow_and(String blockdetailssheetname,
			String rowno) {

	}

	@When("^User create (.*) Condition using data in sheetWithRow (.*) and (.*)$")
	public void user_create_a_condition_using_data_in_sheetwithrow_and(int number, String conditiondetailssheetname,
			int rowno) {
		addConditionPage = new AddConditionPage(DriverFactory.getDriver());
		conditionListPage = applicationDashboard.clickOnConditionsBtnOnDashboard();
		addConditionPage.createConditions(number, conditiondetailssheetname, rowno);

	}

	@Then("^Verify the Condition in the list using data in sheetWithRow (.*) and (.*)$")
	public void verify_the_condition_in_the_list_using_data_in_sheetwithrow_and(String conditiondetailssheetname,
			String rowno) {

	}

	@Then("^verify application will remove from the list using data in sheetWithRow (.*) and (.*)$")
	public void verify_application_will_remove_from_the_list_using_data_in_sheetwithrow_and(
			String applicationremovalsheetname, String rowno) {
		// commonUtil.doSearch(removalAppName);
		// Assert.assertTrue(commonUtil.isDisplayed(header.txtNoRecordFound));

	}

}
