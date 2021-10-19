package ua.fedii.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.fedii.spring.dao.CarRepository;
import ua.fedii.spring.model.Car;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {
	@Autowired
	private CarRepository carRepository;

	@Override
	public List<Car> findAll() {
		return carRepository.findAll();
	}

	@Override
	public Car findById(int id) {
		Optional<Car> result = carRepository.findById(id);
		
		Car car;
		
		if (result.isPresent()) {
			car = result.get();
		}
		else {
			throw new RuntimeException("Did not find car id - " + id);
		}
		
		return car;
	}

	@Override
	public void save(Car car) {
		carRepository.save(car);
	}

	@Override
	public void deleteById(int id) {
		carRepository.deleteById(id);
	}
}






