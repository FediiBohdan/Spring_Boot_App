package ua.fedii.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.fedii.spring.dao.CarRepository;
import ua.fedii.spring.model.Car;

import javax.validation.Valid;

@Controller
@RequestMapping("/cars")
public class CarControllerImpl implements CarController {
    @Autowired
    private CarRepository carRepository;

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("cars", carRepository.findAll());
        return ("index");
    }

    @GetMapping("/{idCar}")
    public String show(@PathVariable("idCar") int idCar, Model model) {
        model.addAttribute("car", carRepository.getById(idCar));
        return ("show");
    }

    @GetMapping("/new")
    public String newCar(Model model) {
        model.addAttribute("car", new Car());
        return "new";
    }

    @PostMapping()
    public String create(@ModelAttribute("car") @Valid Car car, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "new";
        }

        carRepository.save(car);
        return "redirect:/cars/index";
    }

    @GetMapping("/{idCar}/edit")
    public String edit(Model model, @PathVariable("idCar") int idCar) {
        model.addAttribute("car", carRepository.getById(idCar));
        return "edit";
    }

    @PostMapping("/{idCar}")
    public String update(@ModelAttribute("car") @Valid Car car, BindingResult bindingResult, @PathVariable("idCar") int idCar) {
        if (bindingResult.hasErrors()) {
            car.setId(idCar);
            return "edit";
        }

        carRepository.save(car);
        return "redirect:/cars/index";
    }

    @GetMapping("/{idCar}/delete")
    public String deleteUser(@PathVariable("idCar") int idCar) {
        Car car = carRepository.findById(idCar)
                .orElseThrow(() -> new IllegalArgumentException("Invalid car id:" + idCar));
        carRepository.delete(car);
        return "redirect:/cars/index";
    }
}
