package net.javaguides.springboot.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "vehicles")
public class Vehicle {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "model", length = 100, nullable = false)
	private String model;
	
	@Column(name = "brand", length = 100)
	private String brand;
	
	@Column(name = "plate", length = 12, nullable = false)
	private String plate;

	@Column(name = "fabrication")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fabrication;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getPlate() {
		return plate;
	}
	public void setPlate(String plate) {
		this.plate = plate;
	}
	public Date getFabrication() {
		return fabrication;
	}
	public void setFabrication(Date fabrication) {
		this.fabrication = fabrication;
	}
}
