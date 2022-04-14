package tqs.cars;

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

@WebMvcTest(CarController.class)
public class CarControllerTest {


    @Autowired
    private MockMvc mvc;

    @MockBean
    private CarManagerService carManagerService;


    @Test
    void createCar( ) throws Exception {
        Car ibiza_do_aço = new Car( "Seat", "Ibiza" );

        when( carManagerService.save( Mockito.any() )).thenReturn( ibiza_do_aço );

        mvc.perform(
                post("/newcar").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson( ibiza_do_aço )))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.maker", is("Seat")))
                .andExpect(jsonPath("$.model", is("Ibiza")));

        verify(carManagerService, times(1)).save(Mockito.any());
    }


    @Test
    void getAllCarsTest() throws Exception {

        Car car_1 = new Car( "Seat", "Ibiza" );
        Car car_2 = new Car( "Dodge", "Challenger" );
        Car car_3 = new Car( "Volkswagen", "Golf" );

        List<Car> allCars = Arrays.asList(car_1, car_2, car_3);

        when( carManagerService.getAllCars() ).thenReturn( allCars );

        mvc.perform(
            get("/allcars").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(3)))
            .andExpect(jsonPath("$[0].maker", is(car_1.getMaker())))
            .andExpect(jsonPath("$[0].model", is(car_1.getModel())))
            .andExpect(jsonPath("$[1].maker", is(car_2.getMaker())))
            .andExpect(jsonPath("$[1].model", is(car_2.getModel())))
            .andExpect(jsonPath("$[2].maker", is(car_3.getMaker())))
            .andExpect(jsonPath("$[2].model", is(car_3.getModel())));
            
        verify(carManagerService, times(1)).getAllCars();

    }


    @Test
    void getCarTest() throws Exception {

        Car ibiza_do_aço = new Car( "Seat", "Ibiza" );

        when( carManagerService.getCarDetails( Mockito.any() )).thenReturn( ibiza_do_aço );

        mvc.perform(
            get("/car/1").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.maker", is(ibiza_do_aço.getMaker())))
            .andExpect(jsonPath("$.model", is(ibiza_do_aço.getModel())));

        verify(carManagerService, times(1)).getCarDetails( Long.valueOf(1) );

    }
    
}
