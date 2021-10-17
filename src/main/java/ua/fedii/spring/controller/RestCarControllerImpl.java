package ua.fedii.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.fedii.spring.model.Car;
import ua.fedii.spring.service.CarService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestCarControllerImpl {
    @Autowired
    private CarService carService;

    // expose "/employees" and return list of employees
    @GetMapping("/cars")
    public List<Car> findAll() {
        return carService.findAll();
    }

    // add mapping for GET /employees/{employeeId}

    @GetMapping("/cars/{carId}")
    public Car getCar(@PathVariable int carId) {

        Car car = carService.findById(carId);

        if (car == null) {
            throw new RuntimeException("Car id not found - " + carId);
        }

        return car;
    }

    // add mapping for POST /employees - add new employee

    @PostMapping("/cars")
    public Car addCar(@RequestBody Car car) {

        // also just in case they pass an id in JSON ... set id to 0
        // this is to force a save of new item ... instead of update

        car.setId(0);

        carService.save(car);

        return car;
    }

    // add mapping for PUT /employees - update existing employee

    @PutMapping("/cars")
    public Car updateCar(@RequestBody Car car) {

        carService.save(car);

        return car;
    }

    // add mapping for DELETE /employees/{employeeId} - delete employee

    @DeleteMapping("/cars/{carId}")
    public String deleteCar(@PathVariable int carId) {

        Car tempCar = carService.findById(carId);

        // throw exception if null

        if (tempCar == null) {
            throw new RuntimeException("Car id not found - " + carId);
        }

        carService.deleteById(carId);

        return "Deleted car id - " + carId;
    }
}
