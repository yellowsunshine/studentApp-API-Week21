package com.studentapp.cucumber.steps;

import com.studentapp.studentinfo.StudentSteps;
import com.studentapp.utils.TestUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.hasValue;


public class CrudOperation {

    static String email;
    static int studentId;

    public static ValidatableResponse response;

    @Steps
    StudentSteps studentSteps;

    @When("^User creates a new student providing \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void userCreatesANewStudentProviding(String firstName, String lastName, String email1, String programme, String course1, String course2)  {
        List<String> courseList = new ArrayList<>();
        courseList.add(course1);
        courseList.add(course2);

        email = TestUtils.getRandomValue() + email1;

        response = studentSteps.createStudent(firstName,lastName,email,programme, courseList);
        response.statusCode(201);

    }

    @Then("^verifies that the student with email\"([^\"]*)\" is created$")
    public void verifiesThatTheStudentWithEmailIsCreated(String arg0)  {

        HashMap<String, Object> studentInfo = studentSteps.getStudentInfoByEmail(email);
        Assert.assertThat(studentInfo, hasValue(email));

        studentId = (int) studentInfo.get("id");
        System.out.println(studentId);

    }

    @And("^updates the created record with new email using PUT request and details \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void updatesTheCreatedRecordWithNewEmailUsingPUTRequestAndDetails(String firstName, String lastName, String programme, String course1, String course2)  {
        List<String> courseList = new ArrayList<>();
        courseList.add(course1);
        courseList.add(course2);
        email = "updated"+email;
        response = studentSteps.updatingStudentRecord(studentId,firstName,lastName,email,programme,courseList).statusCode(200);

        HashMap<String, Object> studentInfo = studentSteps.getStudentInfoByEmail(email);
        Assert.assertThat(studentInfo, hasValue(email));
        System.out.println(email);
    }

    @And("^deletes the updated record using id$")
    public void deletesTheUpdatedRecordUsingId() {
        studentSteps.deleteStudent(studentId).statusCode(204);

    }

    @And("^verifies that the record is deleted$")
    public void verifiesThatTheRecordIsDeleted() {

        response = studentSteps.getStudentById(studentId);
        response.statusCode(404);
    }
}
