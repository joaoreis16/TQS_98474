package tqs.cars.Service;

import java.util.List;

import tqs.cars.Model.Car;
import tqs.cars.Repository.CarRepository;

public class CarManagerService {

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
