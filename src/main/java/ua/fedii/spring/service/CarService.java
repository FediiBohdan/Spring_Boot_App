package ua.fedii.spring.service;

import ua.fedii.spring.model.Car;

import java.util.List;

public interface CarService {
	List<Car> findAll();
	
	Car findById(int id);
	
	void save(Car car);

	void deleteById(int id);
}
