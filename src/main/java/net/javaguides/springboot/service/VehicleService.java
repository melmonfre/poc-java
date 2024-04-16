package net.javaguides.springboot.service;

import java.util.List;

import net.javaguides.springboot.model.Vehicle;
import org.springframework.data.domain.Page;

public interface VehicleService {
	List<Vehicle> getAllVehicles();
	void saveVehicle(Vehicle Vehicle);
	Vehicle getVehicleById(long id);
	void deleteVehicleById(long id);
	Page<Vehicle> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection, String keyword);
}
