Feature: Test feature

Background: 
Given User has logged into the Portal
|Email                    |Password|Title       |
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


@StepField   
Scenario Outline: Create a Field in a Section of an Application

Given User open an application from the list using data in sheetWithRow <Application Details Sheet Name> and <Row No>
When User open an Step from the list using data in sheetWithRow <Step Details Sheet Name> and <Row No>
And User open an Section from the list using data in sheetWithRow <Section Details Sheet Name> and <Row No>
And User create a Step Field using data in sheetWithRow <Field Details Sheet Name> and <Row No>
Then Verify the Step Field in the list using data in sheetWithRow <Field Details Sheet Name> and <Row No>

Examples:
      |Application Details Sheet Name|Row No|Step Details Sheet Name|Section Details Sheet Name|Field Details Sheet Name|
      |Application_Details           |0     |Step_Details           |Section_Details           |Field_Details           |  
      
      
@Block      
Scenario Outline: Create a Block in the application

Given User open an application from the list using data in sheetWithRow <Application Details Sheet Name> and <Row No>
When User create a Block using data in sheetWithRow <Block Details Sheet Name> and <Row No>
Then Verify the Block in the list using data in sheetWithRow <Block Details Sheet Name> and <Row No>

Examples:
      |Application Sheet Name|Row No|Block Details Sheet Name|
      |Application_Details   |1     |Block_Details           |
    


@BlockField   
Scenario Outline: Create a Field in a Block of an Application

Given User open an application from the list using data in sheetWithRow <Application Details Sheet Name> and <Row No>
When User open a Block from the list using data in sheetWithRow <Block Details Sheet Name> and <Row No>
And User create a Block Field using data in sheetWithRow <Field Details Sheet Name> and <Row No>
Then Verify the Block Field in the list using data in sheetWithRow <Field Details Sheet Name> and <Row No>

Examples:
      |Application Details Sheet Name|Row No|Block Details Sheet Name|Field Details Sheet Name|
      |Application_Details           |0     |Block_Details           |Field_Details           |  
      

      
@Condition
Scenario Outline: Create a Condition in the application

Given User open an application from the list using data in sheetWithRow <Application Details Sheet Name> and <Row No>
When User create <No of Condition> Condition using data in sheetWithRow <Condition Details Sheet Name> and <Row No>
#Then Verify the Condition in the list using data in sheetWithRow <Condition Details Sheet Name> and <Row No>

Examples:
      |Application Sheet Name|Row No|Condition Details Sheet Name|No of Condition|
      |Application_Details   |0     |Condition_Details           |1            |
      |Application_Details   |1     |Condition_Details           |1            |
      
   

@ApplicationRemove
Scenario Outline: Remove an application from the list
When user click on remove option of an application from the list using data in sheetWithRow <Application Removal Sheet Name> and <Row No>
#Then verify application will remove from the list using data in sheetWithRow <Application Removal Sheet Name> and <Row No>

Examples:
      |Application Removal Sheet Name|Row No|
      |Application_Removal           |1     |
      