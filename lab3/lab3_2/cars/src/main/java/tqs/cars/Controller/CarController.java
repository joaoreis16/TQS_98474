package tqs.cars.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tqs.cars.Model.Car;
import tqs.cars.Service.CarManagerService;

@RestController
@RequestMapping("/")
public class CarController {

    @Autowired
    private CarManagerService carManagerService;

    @PostMapping("/newcar")
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        return new ResponseEntity<Car>( carManagerService.save(car), HttpStatus.CREATED );
    }

    @GetMapping("/allcars")
    public List<Car> getAllCars() {
        return carManagerService.getAllCars();
    }

    @GetMapping("/car/{carID}")
    public ResponseEntity<Car> getCarById(@PathVariable Long carID) {
        Car car = carManagerService.getCarDetails(carID);
        return new ResponseEntity<Car>( car, HttpStatus.OK );
    }
    
}
