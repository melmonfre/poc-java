package net.javaguides.springboot.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.javaguides.springboot.model.Vehicle;
import net.javaguides.springboot.service.VehicleService;

@Controller
public class VehicleController {

	@Autowired
	private VehicleService VehicleService;
	
	// display list of Vehicles
	@GetMapping("/")
	public String viewHomePage(Model model, String keyword) {
		return findPaginated(1, "model", "asc", model, keyword);
	}
	
	@GetMapping("/showNewVehicleForm")
	public String showNewVehicleForm(Model model) {
		// create model attribute to bind form data
		Vehicle Vehicle = new Vehicle();
		model.addAttribute("Vehicle", Vehicle);
		return "new_vehicle";
	}
	
	@PostMapping("/saveVehicle")
	public String saveVehicle(@ModelAttribute("Vehicle") Vehicle Vehicle) {
		// save Vehicle to database
		VehicleService.saveVehicle(Vehicle);
		return "redirect:/";
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {
		
		// get Vehicle from the service
		Vehicle Vehicle = VehicleService.getVehicleById(id);
		
		// set Vehicle as a model attribute to pre-populate the form
		model.addAttribute("Vehicle", Vehicle);
		return "update_vehicle";
	}
	
	@GetMapping("/deleteVehicle/{id}")
	public String deleteVehicle(@PathVariable (value = "id") long id) {
		
		// call delete Vehicle method 
		this.VehicleService.deleteVehicleById(id);
		return "redirect:/";
	}
	
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model,
			String keyword) {
		int pageSize = 5;

		if (Objects.equals(keyword, null)) { keyword = "%"; }

		Page<Vehicle> page = VehicleService.findPaginated(pageNo, pageSize, sortField, sortDir, keyword);
		List<Vehicle> listVehicles = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listVehicles", listVehicles);
		return "index";
	}
}
