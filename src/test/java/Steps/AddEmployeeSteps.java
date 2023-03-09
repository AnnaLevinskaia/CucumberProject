package Steps;

import Utils.CommonMethods;
import Utils.Constants;
import Utils.DBUtility;
import Utils.ExcelReader;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AddEmployeeSteps extends CommonMethods {
    String id;
    String fName, lName;

    @When("user clicks on PIM option")
    public void user_clicks_on_pim_option() {
        //WebElement pimOption=driver.findElement(By.id("menu_pim_viewPimModule"));
        //pimOption.click();
        click(dashboard.pimOption); //we are calling our click method and it is asking for which e,ement
        // to be clicked so we are providing the pimElement which comes from the dashBoard page
    }

    @When("user clicks on Add Employee button")
    public void user_clicks_on_add_employee_button() {
        //WebElement addEmployeeOption=driver.findElement(By.id("menu_pim_addEmployee"));
        //addEmployeeOption.click();
        click(dashboard.addEmployeeOption);
    }
    @When("user enter firstname and lastname")
    public void user_enter_firstname_and_lastname() {

        //WebElement firstName=driver.findElement(By.id("firstName"));
        //firstName.sendKeys("Anna");
        sendText(addEmployee.firstNameFeild, "Anna");

        //WebElement lastName=driver.findElement(By.id("lastName"));
        //lastName.sendKeys("Levinskaia");
        sendText(addEmployee.lastNameFeild, "Levinskaia");
    }
    @When("user clicks on save button")
    public void user_clicks_on_save_button() {

        //WebElement saveButton=driver.findElement(By.id("btnSave"));
        //saveButton.click();
        click(addEmployee.saveButton);
    }
    @Then("employee added successfully")
    public void employee_added_successfully() {

        System.out.println("Employee added");
    }

    @When("user enter {string} and {string}")
    public void user_enter_and(String firstName, String lastName) {
        fName=firstName;
        lName=lastName;
        sendText(addEmployee.firstNameFeild, firstName);
        sendText(addEmployee.lastNameFeild, lastName);
    }

    @When("user enter {string} and {string} for adding multiple employees")
    public void user_enter_and_for_adding_multiple_employees(String firstNameValue, String lastNameValue) {
        sendText(addEmployee.firstNameFeild, firstNameValue);
        sendText(addEmployee.lastNameFeild, lastNameValue);
    }

    @When("user adds multiple employees and verify they are added successfully")
    public void user_adds_multiple_employees_and_verify_they_are_added_successfully(DataTable dataTable) throws InterruptedException { //delete io.cucumber and import
        List<Map<String, String>> employeeNames = dataTable.asMaps();

        //getting the map from list of maps
        for (Map<String, String> employee:employeeNames
             ) {
            //getting the keys and values from every map
            String firstNameValue=employee.get("firstName"); //should be the same as in feature file
            String middleNameValue=employee.get("middleName");
            String lastNameValue=employee.get("lastName");

            sendText(addEmployee.firstNameFeild, firstNameValue);
            sendText(addEmployee.lastNameFeild, lastNameValue);
            sendText(addEmployee.middleNameField, middleNameValue);

            click(addEmployee.saveButton);
            Thread.sleep(2000);
            //till this point one employee has been added
            //verifying that employee added

            click(dashboard.addEmployeeOption);
            Thread.sleep(2000);
        }
    }

    @When("user adds multiple employee from exel using {string} and verify it")
    public void user_adds_multiple_employee_from_exel_using_and_verify_it(String sheetName) throws InterruptedException {

        List<Map<String, String>> empFromExcel =
                ExcelReader.exelListIntoMap(Constants.TESTDATA_FILEPATH, sheetName);


        //it returns one map from list of maps
        Iterator<Map<String, String>> itr = empFromExcel.iterator();
        while (itr.hasNext()){
            //it returns the key and value for employee from excel
            Map<String, String> mapNewEmp = itr.next();

            sendText(addEmployee.firstNameFeild, mapNewEmp.get("firstName"));
            sendText(addEmployee.middleNameField, mapNewEmp.get("middleName"));
            sendText(addEmployee.lastNameFeild, mapNewEmp.get("lastName"));
            String empIdValue = addEmployee.empIdLocator.getAttribute("value");
            sendText(addEmployee.photograph, mapNewEmp.get("photograph"));
            if(!addEmployee.checkBox.isSelected()){
                click(addEmployee.checkBox);
            }
            sendText(addEmployee.createusernameField, mapNewEmp.get("username"));
            sendText(addEmployee.createpasswordField, mapNewEmp.get("password"));
            sendText(addEmployee.confirmpasswordField, mapNewEmp.get("confirmPassword"));
            click(addEmployee.saveButton);
            System.out.println("click taken on save button");
            //verification is in home-work
            Thread.sleep(3000);

            click(dashboard.empListOption);
            Thread.sleep(2000);
            System.out.println("click taken on emp list option");

            //to search the employee, we use emp id what we captured from attribute
            sendText(employeeList.empSearchIdField, empIdValue);
            click(employeeList.searchButton);

            //verifying the employee added from the excel file

            List<WebElement> rowData =
                    driver.findElements(By.xpath("//*[@id='resultTable']/tbody/tr"));


            for (int i =0; i<rowData.size(); i++){
                System.out.println("I am inside the loop and worried about josh");
                //getting the text of every element from here and storing it into string
                String rowText = rowData.get(i).getText();
                System.out.println(rowText);

                String expectedData = empIdValue + " " + mapNewEmp.get("firstName")
                        + " " + mapNewEmp.get("middleName") + " " + mapNewEmp.get("lastName");

                //verifying the exact details  of the employee
                Assert.assertEquals(expectedData, rowText);

            }
            click(dashboard.addEmployeeOption);
            Thread.sleep(2000);
        }
    }

    @When("user captures employee id")
    public void user_captures_employee_id() {
        id=addEmployee.empIdLocator.getAttribute("value");
    }

    @Then("added employee is displayed in database")
    public void added_employee_is_displayed_in_database() {
        List<Map<String, String>> dataFromDatabase= DBUtility.getListOfMapFromRset(DataBaseSteps.getFirstLastNameQuery()+id);

        //System.out.println(dataFromDatabase);

        String fNameFromDB=dataFromDatabase.get(0).get("emp_firstname");
        String lNameFromDB=dataFromDatabase.get(0).get("emp_lastname");

        Assert.assertEquals(fName, fNameFromDB);
        Assert.assertEquals(lName, lNameFromDB);

    }

    @When("added employee is available in my database")
    public void added_employee_is_available_in_my_database() {
        String query = "select * from hs_hr_employees where employee_id='"+id+"'";

        List<Map<String, String>> dataFromDatabase= DBUtility.getListOfMapFromRset(DataBaseSteps.getFirstLastNameQuery()+id);

        String fNameFromDB=dataFromDatabase.get(0).get("emp_firstname");
        String lNameFromDB=dataFromDatabase.get(0).get("emp_lastname");
        String empId=dataFromDatabase.get(0).get("employee_id");

        Assert.assertEquals(fName, fNameFromDB);
        Assert.assertEquals(lName, lNameFromDB);
        Assert.assertEquals(id, empId);
    }
}



