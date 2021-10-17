package ua.fedii.spring.model;

import javax.persistence.*;

@Entity
@Table(name="car")
public class Car {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="brand")
    private String brand;

    @Column(name="model")
    private String model;

    @Column(name="production_year")
    private int productionYear;

    @Column(name="weight")
    private float weight;

    @Column(name="horse_powers")
    private int horsePowers;

    public Car() {}

    public Car(String brand, String model, int productionYear, float weight, int horsePowers) {
        this.brand = brand;
        this.model = model;
        this.productionYear = productionYear;
        this.weight = weight;
        this.horsePowers = horsePowers;
    }

    public Car(int id, String brand, String model, int productionYear, float weight, int horsePowers) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.productionYear = productionYear;
        this.weight = weight;
        this.horsePowers = horsePowers;
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
