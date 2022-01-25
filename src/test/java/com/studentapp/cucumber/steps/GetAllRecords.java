package com.studentapp.cucumber.steps;

import com.studentapp.studentinfo.StudentSteps;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;

public class GetAllRecords {

     public static ValidatableResponse response;

    @Steps
    StudentSteps studentSteps;

    @When("^User sends a GET request to get all the students details$")
    public void userSendsAGETRequestToGetAllTheStudentsDetails() {
        response = studentSteps.getAllStudentInfo();
    }

    @Then("^User gets a valid response with status code (\\d+)$")
    public void userGetsAValidResponseWithStatusCode(int arg0) {
        response.statusCode(200);
    }
    }

