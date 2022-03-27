package tqs.cars.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tqs.cars.Model.Car;

public interface CarRepository extends JpaRepository<Car, Integer>{
    
    public Car findByCarId(Long carID);
    public List<Car> findAll();
    
}
