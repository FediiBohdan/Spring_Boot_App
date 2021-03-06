package ua.fedii.spring.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="car")
@SQLDelete(sql = "UPDATE car SET is_deleted = true WHERE id = ?")
@Where(clause = "is_deleted = false")
public class Car extends RepresentationModel<Car> {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="brand")
    @NotEmpty(message = "Brand is required!")
    @Size(min = 2, max = 15, message = "Brand should be between 2 and 15 characters!")
    private String brand;

    @Column(name="model")
    @NotEmpty(message = "Model is required!")
    @Size(min = 2, max = 25, message = "Model should be between 2 and 25 characters!")
    private String model;

    @Column(name="production_year")
    @Min(value = 1880, message = "Production year should be grater than 1880!")
    private int productionYear;

    @Column(name="weight")
    @Min(value = 500, message = "Car weight should be grater than 500!")
    private float weight;

    @Column(name="horse_powers")
    @Min(value = 2, message = "Car should have more than 2 horse powers!")
    private int horsePowers;

    @Column(name="is_deleted")
    private boolean isDeleted;

    public Car() {}

    @JsonCreator
    public Car(String brand, String model, int productionYear, float weight, int horsePowers, boolean isDeleted) {
        this.brand = brand;
        this.model = model;
        this.productionYear = productionYear;
        this.weight = weight;
        this.horsePowers = horsePowers;
        this.isDeleted = isDeleted;
    }

    @JsonCreator
    public Car(int id, String brand, String model, int productionYear, float weight, int horsePowers, boolean isDeleted) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.productionYear = productionYear;
        this.weight = weight;
        this.horsePowers = horsePowers;
        this.isDeleted = isDeleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getHorsePowers() {
        return horsePowers;
    }

    public void setHorsePowers(int horsePowers) {
        this.horsePowers = horsePowers;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", productionYear=" + productionYear +
                ", weight=" + weight +
                ", horsePowers=" + horsePowers +
                '}';
    }
}
