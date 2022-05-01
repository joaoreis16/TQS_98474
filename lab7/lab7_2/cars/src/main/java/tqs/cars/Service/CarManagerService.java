package tqs.cars.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tqs.cars.Model.Car;
import tqs.cars.Repository.CarRepository;

@Service
public class CarManagerService {

    @Autowired(required = true)
    private CarRepository carRepository;


    public Car save(Car car) {
        return carRepository.save(car);
    }
    
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car getCarDetails(Long carID) {
        return carRepository.findByCarId(carID);
    }
}
