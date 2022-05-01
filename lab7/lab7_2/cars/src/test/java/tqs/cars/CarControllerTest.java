package tqs.cars;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import tqs.cars.Controller.CarController;
import tqs.cars.Model.Car;
import tqs.cars.Service.CarManagerService;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

@WebMvcTest(CarController.class)
public class CarControllerTest {


    @Autowired
    private MockMvc mvc;

    @MockBean
    private CarManagerService carManagerService;

    @BeforeEach
    void start() {
        RestAssuredMockMvc.mockMvc(mvc);
    }


    @Test
    void createCar( ) throws Exception {
        Car ibiza_do_aço = new Car( "Seat", "Ibiza" );

        when( carManagerService.save( Mockito.any() )).thenReturn( ibiza_do_aço );

        RestAssuredMockMvc.given().header("Content-Type", "application/json")
                .body(JsonUtils.toJson(ibiza_do_aço))
                .post("/newcar")
                .then().assertThat().statusCode(201)
                .and().body("model", is(ibiza_do_aço.getModel()))
                .and().body("maker", is(ibiza_do_aço.getMaker()));

        verify(carManagerService, times(1)).save(Mockito.any());
    }


    @Test
    void getAllCarsTest() throws Exception {

        Car car_1 = new Car( "Seat", "Ibiza" );
        Car car_2 = new Car( "Dodge", "Challenger" );
        Car car_3 = new Car( "Volkswagen", "Golf" );

        List<Car> allCars = Arrays.asList(car_1, car_2, car_3);

        when( carManagerService.getAllCars() ).thenReturn( allCars );

        RestAssuredMockMvc.given()
                .get("/allcars")
                .then().assertThat().statusCode(200)
                .and().body("model[0]", is(car_1.getModel()))
                .and().body("maker[0]", is(car_1.getMaker()))
                .and().body("model[1]", is(car_2.getModel()))
                .and().body("maker[1]", is(car_2.getMaker()))
                .and().body("model[2]", is(car_3.getModel()))
                .and().body("maker[2]", is(car_3.getMaker()))
                .and().body("", hasSize(3));
            
        verify(carManagerService, times(1)).getAllCars();
    }


    @Test
    void getCarTest() throws Exception {

        Car ibiza_do_aço = new Car( "Seat", "Ibiza" );

        when( carManagerService.getCarDetails( Mockito.any() )).thenReturn( ibiza_do_aço );

        RestAssuredMockMvc.given()
                .get("/car/1")
                .then().assertThat().statusCode(200)
                .and().body("model", is(ibiza_do_aço.getModel()))
                .and().body("maker", is(ibiza_do_aço.getMaker()));

        verify(carManagerService, times(1)).getCarDetails( Long.valueOf(1) );

    }
    
}
