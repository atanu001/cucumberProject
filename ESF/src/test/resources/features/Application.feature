Feature: Test feature

Background: 
Given User has logged into the Portal
|Email                    |Password|Title|
|portaluser0002@inadev.net|123456  |CEEBIT Login|


@Application
Scenario Outline: Verify Application creation 

Given User is in My Application page with the title "My Applications"
When Create an Application using data in sheetWithRow <Application Details Sheet Name> and <Row No>
Then Verify the Application in the list using data in sheetWithRow <Application Details Sheet Name> and <Row No>

Examples:
      |Application Sheet Name|Row No|
      |Application_Details   |0     |
      
@Step      
Scenario Outline: Create a Step in the application

Given User open an application from the list using data in sheetWithRow <Application Details Sheet Name> and <Row No>
When User create a Step using data in sheetWithRow <Step Details Sheet Name> and <Row No>
Then Verify the Step in the list using data in sheetWithRow <Step Details Sheet Name> and <Row No>

Examples:
      |Application Sheet Name|Row No|Step Details Sheet Name|
      |Application_Details   |0     |Step_Details           |

@Section    
Scenario Outline: Create a Section in a Step of an Application

Given User open an application from the list using data in sheetWithRow <Application Details Sheet Name> and <Row No>
When User open an Step from the list using data in sheetWithRow <Step Details Sheet Name> and <Row No>
And User create a Section using data in sheetWithRow <Section Details Sheet Name> and <Row No>
Then Verify the Section in the list using data in sheetWithRow <Section Details Sheet Name> and <Row No>

Examples:
      |Application Details Sheet Name|Row No|Step Details Sheet Name|Section Details Sheet Name|
      |Application_Details           |0     |Step_Details           |Section_Details           |


@Field   
Scenario Outline: Create a Field in a Section of an Application

Given User open an application from the list using data in sheetWithRow <Application Details Sheet Name> and <Row No>
When User open an Step from the list using data in sheetWithRow <Step Details Sheet Name> and <Row No>
And User open an Section from the list using data in sheetWithRow <Section Details Sheet Name> and <Row No>
And User create a Field using data in sheetWithRow <Field Details Sheet Name> and <Row No>
Then Verify the Field in the list using data in sheetWithRow <Field Details Sheet Name> and <Row No>

Examples:
      |Application Details Sheet Name|Row No|Step Details Sheet Name|Section Details Sheet Name|Field Details Sheet Name|
      |Application_Details           |0     |Step_Details           |Section_Details           |Field_Details           |     

@ApplicationRemove
Scenario Outline: Remove an application from the list
When user click on remove option of an application from the list using data in sheetWithRow <Application Removal Sheet Name> and <Row No>
#Then verify application will remove from the list using data in sheetWithRow <Application Removal Sheet Name> and <Row No>

Examples:
      |Application Removal Sheet Name|Row No|
      |Application_Removal           |1     |
      