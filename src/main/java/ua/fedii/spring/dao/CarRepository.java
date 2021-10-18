package ua.fedii.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.fedii.spring.model.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
}
