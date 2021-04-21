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
import com.app.pages.BlockFieldListPage;
import com.app.pages.BlockListPage;
import com.app.pages.ConditionListPage;
import com.app.pages.LoginPage;
import com.app.pages.ManageBlockPage;
import com.app.pages.ManageFieldPage;
import com.app.pages.ManageMessagePage;
import com.app.pages.ManageSectionPage;
import com.app.pages.ManageStepPage;
import com.app.pages.ManageWorkflowPage;
import com.app.pages.MessageListPage;
import com.app.pages.ModalListPage;
import com.app.pages.ModalManagePage;
import com.app.pages.MyApplication;
import com.app.pages.NewApplication;
import com.app.pages.SectionListPage;
import com.app.pages.StepFieldListPage;
import com.app.pages.StepListPage;
import com.app.pages.ValidationMessageListPage;
import com.app.pages.ValidationMessageManagePage;
import com.app.pages.WorkflowListPage;
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
	private StepFieldListPage stepFieldListPage;
	private ManageFieldPage manageFieldPage;
	private ConditionListPage conditionListPage;
	private AddConditionPage addConditionPage;
	private BlockListPage blockListPage;
	private ManageBlockPage manageBlockPage;
	private BlockFieldListPage blockFieldListPage;
	private MessageListPage messageListPage;
	private ManageMessagePage manageMessagePage;
	private WorkflowListPage workflowListPage;
	private ManageWorkflowPage manageWorkflowPage;
	private ModalListPage modalListPage;
	private ModalManagePage modalManagePage;
	private ValidationMessageListPage validationMessageListPage;
	private ValidationMessageManagePage validationMessageManagePage;

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
		stepListPage = applicationDashboard.clickOnStepButtonOnDashboard();
		String actualTitle = stepListPage.getStepListPageTitle();
		String expectedTitle = "Application Steps";
		Assert.assertTrue(actualTitle.equals(expectedTitle));
		manageStepPage = stepListPage.clickOnCreateStepBtn();
		stepListPage = manageStepPage.createStep(stepdetailssheetname, rowno);
	}

	@Then("^Verify the Step in the list using data in sheetWithRow (.*) and (.*)$")
	public void verify_the_step_in_the_list_using_data_in_sheetwithrow_and(String stepdetailssheetname, int rowno) {
		manageStepPage = stepListPage.editStep(stepdetailssheetname, rowno);
		manageStepPage.verifyStep();
	}

	@When("^User open an Step from the list using data in sheetWithRow (.*) and (.*)$")
	public void user_open_an_step_from_the_list_using_data_in_sheetwithrow_and(String stepdetailssheetname, int rowno) {
		stepListPage = applicationDashboard.clickOnStepButtonOnDashboard();
		sectionListPage = stepListPage.openStep(stepdetailssheetname, rowno);
		if (sectionListPage.getSectionListPageTitle().equals("Application Sections")) {
			Log.info("Section List page is displaying with title: " + sectionListPage.getSectionListPageTitle());
		} else {
			Log.error("Section List page is not displaying with title:");
		}
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
		manageSectionPage.verifySection();
	}

	@And("^User open an Section from the list using data in sheetWithRow (.*) and (.*)$")
	public void user_open_an_section_from_the_list_using_data_in_sheetwithrow_and(String sectiondetailssheetname,
			int rowno) {
		stepFieldListPage = sectionListPage.openSection(sectiondetailssheetname, rowno);
		if (stepFieldListPage.labelHeaderFieldListPage().equals("Application Step Fields")) {
			Log.info("User is on Step Field List page :");
		} else {
			Log.error("User is not on Step Field List page :");
		}

	}

	@And("^User create a Step Field using data in sheetWithRow (.*) and (.*)$")
	public void user_create_a_field_using_data_in_sheetwithrow_and(String fielddetailssheetname, int rowno) {
		manageFieldPage = stepFieldListPage.clickOnCreateNewFieldBtn();
		manageFieldPage.createField(fielddetailssheetname, rowno);

	}

	@Then("^Verify the Step Field in the list using data in sheetWithRow (.*) and (.*)$")
	public void verify_the_field_in_the_list_using_data_in_sheetwithrow_and(String fielddetailssheetname, int rowno) {
		manageFieldPage = new ManageFieldPage(DriverFactory.getDriver());
		manageFieldPage = stepFieldListPage.editField(fielddetailssheetname, rowno);
		manageFieldPage.verifyField();
	}

	@When("^User create a Block using data in sheetWithRow (.*) and (.*)$")
	public void user_create_a_block_using_data_in_sheetwithrow_and(String blockdetailssheetname, int rowno) {
		blockListPage = applicationDashboard.clickOnBlockButtonOnDashboard();
		manageBlockPage = blockListPage.clickOnAddNewBlockBtn();
		blockListPage = manageBlockPage.createBlock(blockdetailssheetname, rowno);

	}

	@Then("^Verify the Block in the list using data in sheetWithRow (.*) and (.*)$")
	public void verify_the_block_in_the_list_using_data_in_sheetwithrow_and(String blockdetailssheetname, int rowno) {
		manageBlockPage = blockListPage.clickOnEditBtnOfBlock(blockdetailssheetname, rowno);
		manageBlockPage.verifyBlock();

	}

	@When("^User open a Block from the list using data in sheetWithRow (.*) and (.*)$")
	public void user_open_a_block_from_the_list_using_data_in_sheetwithrow_and(String blockdetailssheetname,
			int rowno) {
		blockListPage = applicationDashboard.clickOnBlockButtonOnDashboard();
		blockFieldListPage = blockListPage.openBlock(blockdetailssheetname, rowno);

	}

	@And("^User create a Block Field using data in sheetWithRow (.*) and (.*)$")
	public void user_create_a_block_field_using_data_in_sheetwithrow_and(String fielddetailssheetname, int rowno) {
		manageFieldPage = blockFieldListPage.clickOnAddNewBlockFieldBtn();
		manageFieldPage.createField(fielddetailssheetname, rowno);
	}

	@Then("^Verify the Block Field in the list using data in sheetWithRow (.*) and (.*)$")
	public void verify_the_block_field_in_the_list_using_data_in_sheetwithrow_and(String fielddetailssheetname,
			int rowno) {
		blockFieldListPage = new BlockFieldListPage(DriverFactory.getDriver());
		manageFieldPage = blockFieldListPage.clickOnBlockFieldEdit(fielddetailssheetname, rowno);
		manageFieldPage.verifyField();

	}

	@When("^User create a Message using data in sheetWithRow (.*) and (.*)$")
	public void user_create_a_message_using_data_in_sheetwithrow_and(String messagedetailssheetname, int rowno) {
		messageListPage = applicationDashboard.clickOnMessageButtonOnDashboard();
		manageMessagePage = messageListPage.clickOnAddNewMessageBtn();
		messageListPage = manageMessagePage.createMessage(messagedetailssheetname, rowno);

	}

	@Then("^Verify the Message in the list using data in sheetWithRow (.*) and (.*)$")
	public void verify_the_message_in_the_list_using_data_in_sheetwithrow_and(String messagedetailssheetname,
			int rowno) {
		manageMessagePage = messageListPage.clickOnOptionEditMessage(messagedetailssheetname, rowno);
		manageMessagePage.verifyMessage();

	}

	@When("^User create a Workflow using data in sheetWithRow (.*) and (.*)$")
	public void user_create_a_workflow_using_data_in_sheetwithrow_and(String workflowdetailssheetname, int rowno) {
		workflowListPage = applicationDashboard.clickOnWorkflowButtonOnDashboard();
		manageWorkflowPage = workflowListPage.clickOnAddNewWorkflowBtn();
		workflowListPage = manageWorkflowPage.createWorkflow(workflowdetailssheetname, rowno);
	}

	@Then("^Verify the Workflow in the list using data in sheetWithRow (.*) and (.*)$")
	public void verify_the_workflow_in_the_list_using_data_in_sheetwithrow_and(String workflowdetailssheetname,
			int rowno) {
		manageWorkflowPage = workflowListPage.clickOnEditWorkflowOption(workflowdetailssheetname, rowno);
		manageWorkflowPage.verifyWorkflow();

	}

	@When("^User create a Modal using data in sheetWithRow (.*) and (.*)$")
	public void user_create_a_modal_using_data_in_sheetwithrow_and(String modaldetailssheetname, int rowno) {
		modalListPage = applicationDashboard.clickOnModalwButtonOnDashboard();
		modalManagePage = modalListPage.clickOnAddNewModalBtn();
		modalListPage = modalManagePage.createModal(modaldetailssheetname, rowno);
	}

	@Then("^Verify the Modal in the list using data in sheetWithRow (.*) and (.*)$")
	public void verify_the_modal_in_the_list_using_data_in_sheetwithrow_and(String modaldetailssheetname, int rowno) {
		modalManagePage = modalListPage.clickOnEditModalOption(modaldetailssheetname, rowno);
		modalManagePage.verifyModal();
	}

	@When("^User create (.*) Validation Message using data in sheetWithRow (.*) and (.*)$")
	public void user_create_validation_message_using_data_in_sheetwithrow_and(int noofvalidationmessage,
			String validationmessagedetailssheetname, int rowno) {
		validationMessageListPage = applicationDashboard.clickOnValidationMessageButtonOnDashboard();
		validationMessageManagePage = validationMessageListPage.clickOnAddNewValidationMsg();
		validationMessageListPage = validationMessageManagePage.createValidationMessage(noofvalidationmessage,
				validationmessagedetailssheetname, rowno);
	}

	@Then("^Verify the Validation Message in the list using data in sheetWithRow (.*) and (.*)$")
	public void verify_the_validation_message_in_the_list_using_data_in_sheetwithrow_and(
			String validationmessagedetailssheetname, int rowno) {
		validationMessageManagePage = validationMessageListPage
				.clickOnValidationMsgEditOption(validationmessagedetailssheetname, rowno);
		validationMessageManagePage.verifyValidationMessage();
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
			int rowno) {

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

	@Then("^verify application will remove from the list using data in sheetWithRow (.*) and (.*)$")
	public void verify_application_will_remove_from_the_list_using_data_in_sheetwithrow_and(
			String applicationremovalsheetname, String rowno) {
		// commonUtil.doSearch(removalAppName);
		// Assert.assertTrue(commonUtil.isDisplayed(header.txtNoRecordFound));

	}

}
