package net.javaguides.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.model.Vehicle;
import net.javaguides.springboot.repository.VehicleRepository;

@Service
public class VehicleServiceImpl implements VehicleService {

	@Autowired
	private VehicleRepository VehicleRepository;

	@Override
	public List<Vehicle> getAllVehicles() {
		return VehicleRepository.findAll();
	}

	@Override
	public void saveVehicle(Vehicle Vehicle) {
		this.VehicleRepository.save(Vehicle);
	}

	@Override
	public Vehicle getVehicleById(long id) {
		Optional<Vehicle> optional = VehicleRepository.findById(id);
		Vehicle Vehicle = null;
		if (optional.isPresent()) {
			Vehicle = optional.get();
		} else {
			throw new RuntimeException(" Vehicle not found for id :: " + id);
		}
		return Vehicle;
	}

	@Override
	public void deleteVehicleById(long id) {
		this.VehicleRepository.deleteById(id);
	}

	@Override
	public Page<Vehicle> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection, String keyword) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.VehicleRepository.findByKeywordWithPageable(pageable, keyword);
	}
}
