package ua.fedii.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.fedii.spring.model.Car;
import ua.fedii.spring.service.CarService;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api")
public class RestCarController {
    @Autowired
    private CarService carService;

    @GetMapping("/cars")
    public List<Car> findAll() {
        return carService.findAll();
    }

    @GetMapping("/cars/{idCar}")
    public HttpEntity<Car> getCar(@PathVariable int idCar) {
        Car car = carService.findById(idCar);

        if (car == null) {
            throw new RuntimeException("Car id not found: " + idCar);
        }

        car.add(linkTo(methodOn(RestCarController.class).getCar(idCar)).withSelfRel());
        car.add(linkTo(methodOn(RestCarController.class).findAll()).withRel("all"));

        return new ResponseEntity<>(car, HttpStatus.OK);
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
            throw new RuntimeException("Did not find car with id: " + idCar);
        }

        carService.deleteById(idCar);

        return "Deleted car id: " + idCar;
    }
}
