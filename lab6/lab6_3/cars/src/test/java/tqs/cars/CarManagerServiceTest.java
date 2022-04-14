package tqs.cars;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import tqs.cars.Model.Car;
import tqs.cars.Repository.CarRepository;
import tqs.cars.Service.CarManagerService;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class CarManagerServiceTest {


    @Mock(lenient = true)
    private CarRepository carRepository;

    @InjectMocks
    private CarManagerService carManagerService;

    @BeforeEach
    public void setUp() {

        Car car_1 = new Car( "Seat", "Ibiza" );
        Car car_2 = new Car( "Dodge", "Challenger" );
        car_1.setCarId(1L);
        car_2.setCarId(2L);

        List<Car> allCars = Arrays.asList(car_1, car_2);

        Mockito.when( carRepository.findByCarId( car_1.getCarId() ) ).thenReturn( car_1 );
        Mockito.when( carRepository.findByCarId( -1L) ).thenReturn( null );

        Mockito.when( carRepository.findAll() ).thenReturn( allCars );

    }


    @Test
    void getAllCarsTest() {

        Car car_1 = new Car( "Seat", "Ibiza" );
        Car car_2 = new Car( "Dodge", "Challenger" );

        List<Car> cars = carManagerService.getAllCars();

        assertThat(cars).hasSize(2).extracting(Car::getMaker).contains( car_1.getMaker(), car_2.getMaker() );
       
    }


    @Test
    void getCarDetailsTest() {

        Car car_1 = carManagerService.getCarDetails( 1L );
        assertThat( car_1.getMaker() ).isEqualTo( "Seat" );

        Car car_not_found = carManagerService.getCarDetails(-1L);
        assertThat( car_not_found ).isEqualTo( null );
    }
    
    
}
