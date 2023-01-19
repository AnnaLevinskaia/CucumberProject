package APIPackage;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.given;

public class HardCodedExamples {
    //base URI - base URL
    //using when keyword we wll send the end point

    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";

    //we need to perform CRUD operation
    String token="Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NzQwNzU5MTYsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY3NDExOTExNiwidXNlcklkIjoiNDgzNyJ9.fCF5LpOJ5fGCN_6v1KRWwlSG8gnry3RB5LOswNM95to";

    @Test
    public void getOneEmployee(){
        //prepare the request
        //to prepare the request we use request specification
        RequestSpecification request = given().header("Authorization", token)
                .header("Content-Type", "application/json")
                .queryParam("employee_id", "47306A");

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

    public void createEmployee(){
        //RequestSpecification request = given()
    }

}
