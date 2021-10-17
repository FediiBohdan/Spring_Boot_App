package ua.fedii.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.fedii.spring.model.Car;

public interface CarRepository extends JpaRepository<Car, Integer> {
}
