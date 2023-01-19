package APIPackage;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;

//to change the order of execution in alphabetical order
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HardCodedExamples {
    //base URI - base URL
    //using when keyword we wll send the end point

    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";

    //we need to perform CRUD operation
    String token="Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NzQwOTY2ODYsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY3NDEzOTg4NiwidXNlcklkIjoiNDgzNyJ9.sexG5LRlBkRjGgS1RawCxNXW_3ue5RxZ8E0wHbEGdO0";

    static String employee_id;

    @Test
    public void bgetOneEmployee(){
        //prepare the request
        //to prepare the request we use request specification
        RequestSpecification request = given().header("Authorization", token)
                .header("Content-Type", "application/json")
                .queryParam("employee_id", employee_id);

        //to hit the point/to make the request which will return response
        Response response=request.when().get("/getOneEmployee.php");

        //System.out.println(response.asString());
        response.prettyPrint(); //the same as line 30
        //verifying the status code
        response.then().assertThat().statusCode(200);

        //using jsonpath method, we are extracting the value from the response body
        String firstName=response.jsonPath().getString("employee.emp_firstname");
        System.out.println(firstName);
        //first way of assertion
        Assert.assertEquals(firstName, "Anna");

        //second way of assertion to verify the value in response body using hamcrest matchers
        response.then().assertThat().body("employee.emp_firstname", equalTo("Anna"));
    }

    @Test
    public void acreateEmployee(){
        RequestSpecification request = given().header("Authorization", token)
                .header("Content-Type", "application/json").
                body("{\n" +
                        "  \"emp_firstname\": \"Anna\",\n" +
                        "  \"emp_lastname\": \"Deleted\",\n" +
                        "  \"emp_middle_name\": \"ms\",\n" +
                        "  \"emp_gender\": \"F\",\n" +
                        "  \"emp_birthday\": \"1990-09-10\",\n" +
                        "  \"emp_status\": \"confirmed\",\n" +
                        "  \"emp_job_title\": \"QA\"\n" +
                        "}");

        Response response=request.when().post("/createEmployee.php");
        response.prettyPrint();
        response.then().assertThat().statusCode(201);
        //getting the employee id from the response
        employee_id=response.jsonPath().getString("Employee.employee_id");
        System.out.println(employee_id);
        response.then().assertThat().body("Employee.emp_lastname",equalTo("Deleted"));
        //verify console header
        response.then().assertThat().header("Server", "Apache/2.4.39 (Win64) PHP/7.2.18");
    }

    @Test
    public void cupdateEmployee(){
        RequestSpecification request=given().header("Authorization", token)
                .header("Content-Type", "application/json").
                body("{\n" +
                        "  \"employee_id\": \""+ employee_id+"\",\n" +
                        "  \"emp_firstname\": \"Asma\",\n" +
                        "  \"emp_lastname\": \"Alfag\",\n" +
                        "  \"emp_middle_name\": \"ms\",\n" +
                        "  \"emp_gender\": \"F\",\n" +
                        "  \"emp_birthday\": \"2010-01-15\",\n" +
                        "  \"emp_status\": \"probation\",\n" +
                        "  \"emp_job_title\": \"manager\"\n" +
                        "}");

        Response response=request.when().put("/updateEmployee.php");

        response.prettyPrint();
        //verification
        response.then().assertThat().statusCode(200);
        response.then().assertThat().body("Message", equalTo("Employee record Updated"));


/*        @Test
        public void dgetUpdatedEmployee(){

        }*/

    }

}
