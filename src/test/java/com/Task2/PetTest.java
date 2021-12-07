package com.Task2;

import com.Utilities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PetTest {

    Pet pet=new Pet();
    int id=Integer.parseInt( ConfigurationReader.get("id"));

    @BeforeClass
    public static void setup() {
      RestAssured.baseURI= ConfigurationReader.get("Base_Url");

    }

    @Test

    public void CreatePet(){

        pet.setId(id);
        pet.setName("Choban");
        pet.setStatus("available");

        given().log().all()
                .accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(pet)
                .when().post("/api/v3/pet")
                .then().log().all()
                .statusCode(200)
                .and().contentType("application/json")
                .and().assertThat().body("name",equalTo("Choban"),"status",equalTo("available"));



    }


    @Test
    public void GetPet(){



        given().log().all()
                .accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .pathParam("id",id)
                .when().get("/api/v3/pet/{id}")
                .then().log().all()
                .statusCode(200)
                .contentType("application/json")
                .and().assertThat().body("name",equalTo("Choban"),"status",equalTo("available"));


    }


    @Test()
    public void UpdatePet() {

        pet.setId(id);
        pet.setName("Choban");
        pet.setStatus("sold");

        given().log().all()
                .accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(pet)
                .when().put("/api/v3/pet")
                .then().log().all()
                .statusCode(200)
                .contentType("application/json")
                .and().assertThat().body("name",equalTo("Choban"),"status",equalTo("sold"));

    }

    @Test()
    public void DeletePet() {

        given().log().all()
                .accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .pathParam("id",id)
                .when().delete("/api/v3/pet/{id}")
                .then().log().all()
                .statusCode(200)
                .contentType("application/json");
    }






}
