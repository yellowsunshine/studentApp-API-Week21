package com.studentapp.studentinfo;

import com.studentapp.constants.EndPoints;
import com.studentapp.model.StudentPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;
import java.util.List;

public class StudentSteps {

    @Step("Creating student with firstName : {0}, lastName: {1}, email: {2}, programme: {3} and courses: {4}")
    public ValidatableResponse createStudent(String firstName, String lastName, String email, String programme, List<String> courses) {

        StudentPojo studentPojo = StudentPojo.getStudentPojo(firstName, lastName, email, programme, courses);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(studentPojo)
                .when()
                .post()
                .then().log().all();
    }

    @Step("Getting all students information")
    public ValidatableResponse getAllStudentInfo() {
        return SerenityRest.given().log().all()
                .when()
                .get(EndPoints.GET_ALL_STUDENT)
                .then().log().all();
    }

    @Step("Getting the student information with email: {0}")
    public HashMap<String, Object> getStudentInfoByEmail(String email) {
        return SerenityRest.given().log().all()
                .when()
                .get(EndPoints.GET_ALL_STUDENT)
                .then().log().all().statusCode(200)
                .extract()
                .path("findAll{it.email=='" + email + "'}.get(0)");
    }

    @Step("Updating student information with studentId: {0}, firstName: {1}, lastName: {2}, email: {3}, programme: {4} and courses: {5}")
    public ValidatableResponse updatingStudentRecord(int studentId, String firstName, String lastName, String email,
                                      String programme, List<String> courses){
        StudentPojo studentPojo = StudentPojo.getStudentPojo(firstName, lastName, email, programme, courses);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .pathParam("studentID", studentId)
                .body(studentPojo)
                .when()
                .put(EndPoints.UPDATE_STUDENT_BY_ID)
                .then().log().all();
    }

    @Step("Deleting student information with studentId: {0}")
    public ValidatableResponse deleteStudent(int studentId){
        return SerenityRest.given().log().all()
                .pathParam("studentID", studentId)
                .when()
                .delete(EndPoints.DELETE_STUDENT_BY_ID)
                .then().log().all();
    }

    @Step("Getting student information with studentId: {0}")
    public ValidatableResponse getStudentById(int studentId) {
        return SerenityRest
                .given().log().all()
                .pathParam("studentID", studentId)
                .when()
                .get(EndPoints.GET_SINGLE_STUDENT_BY_ID)
                .then().log().all();
    }
}
