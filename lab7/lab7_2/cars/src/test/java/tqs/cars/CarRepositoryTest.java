package tqs.cars;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import tqs.cars.Model.Car;
import tqs.cars.Repository.CarRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
public class CarRepositoryTest {
    
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository carRepository;

    @Test
    void whenFindCarByExistingId_thenReturnCar() {
        Car car_1 = new Car( "Seat", "Ibiza" );

        entityManager.persistAndFlush(car_1);

        Car fromDb = carRepository.findByCarId( car_1.getCarId() );
        assertThat(fromDb).isNotNull();
        assertThat(fromDb.getCarId()).isEqualTo( car_1.getCarId() );
    }

    @Test
    void whenInvalidId_thenReturnNull() {
        Car fromDb = carRepository.findByCarId(-111L);
        assertThat(fromDb).isNull();
    }

    @Test
    void givenSetOfEmployees_whenFindAll_thenReturnAllEmployees() {
        Car car_1 = new Car( "Seat", "Ibiza" );
        Car car_2 = new Car( "Dodge", "Challenger" );
        Car car_3 = new Car( "Volkswagen", "Golf" );

        entityManager.persist(car_1);
        entityManager.persist(car_2);
        entityManager.persist(car_3);
        entityManager.flush();

        List<Car> allCars = carRepository.findAll();

        assertThat(allCars).hasSize(3).extracting(Car::getModel).containsOnly(car_1.getModel(), car_2.getModel(), car_3.getModel());
    }
}