package ua.fedii.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.fedii.spring.model.Car;
import ua.fedii.spring.service.CarService;

import javax.validation.Valid;

@Controller
@RequestMapping("/cars")
public class CarControllerImpl {
    @Autowired
    private CarService carService;

    @GetMapping("/show_all")
    public String index(Model model) {
        model.addAttribute("cars", carService.findAll());
        return ("show_all");
    }

    @GetMapping("/{idCar}")
    public String show(@PathVariable("idCar") int idCar, Model model) {
        model.addAttribute("car", carService.findById(idCar));
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

        carService.save(car);
        return "redirect:/cars/show_all";
    }

    @GetMapping("/edit/{idCar}")
    public String edit(Model model, @PathVariable("idCar") int idCar) {
        model.addAttribute("car", carService.findById(idCar));
        return "edit";
    }

    @PostMapping("/{idCar}")
    public String update(@ModelAttribute("car") @Valid Car car, BindingResult bindingResult, @PathVariable("idCar") int idCar) {
        if (bindingResult.hasErrors()) {
            return "edit";
        }

        car.setId(idCar);

        carService.save(car);
        return "redirect:/cars/show_all";
    }

    @GetMapping("/delete/{idCar}")
    public String deleteCar(@PathVariable("idCar") int idCar) {
        Car car = carService.findById(idCar);

        if (car == null) {
            throw new RuntimeException("Did not find car with id: " + idCar);
        }

        carService.deleteById(idCar);
        return "redirect:/cars/show_all";
    }
}
