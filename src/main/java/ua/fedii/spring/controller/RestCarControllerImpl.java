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

    @GetMapping("/cars")
    public List<Car> findAll() {
        return carService.findAll();
    }

    @GetMapping("/cars/{carId}")
    public Car getCar(@PathVariable int carId) {
        Car car = carService.findById(carId);

        if (car == null) {
            throw new RuntimeException("Car id not found - " + carId);
        }

        return car;
    }

    @PostMapping("/cars")
    public Car addCar(@RequestBody Car car) {
        car.setId(0);

        carService.save(car);

        return car;
    }

    @PutMapping("/cars")
    public Car updateCar(@RequestBody Car car) {
        carService.save(car);

        return car;
    }

    @DeleteMapping("/cars/{idCar}")
    public String deleteCar(@PathVariable int idCar) {
        Car car = carService.findById(idCar);

        if (car == null) {
            throw new RuntimeException("Car id not found - " + idCar);
        }

        carService.deleteById(idCar);

        return "Deleted car id - " + idCar;
    }
}
