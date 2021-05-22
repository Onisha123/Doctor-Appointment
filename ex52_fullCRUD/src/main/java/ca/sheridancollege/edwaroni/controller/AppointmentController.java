package ca.sheridancollege.edwaroni.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ca.sheridancollege.edwaroni.beans.Appointment;

import ca.sheridancollege.edwaroni.database.DatabaseAccess;

@Controller
public class AppointmentController {
	
	@Autowired 
	DatabaseAccess ap;

	@GetMapping("/")
	public String getindex (Model model, Appointment appointment) {
		
		ArrayList<Appointment> list=ap.getAllAppointment();
		model.addAttribute("list",list);
		return "index";
	}
	
	@PostMapping
	public String insertAppointment(Model model, @ModelAttribute Appointment appointment) {
		
		ArrayList<Appointment> list=ap.getAllAppointment();
		ap.addAppointmnet(appointment);
		model.addAttribute("list",list);
		return "index";
	}
	

	//update appointment with EDIT Link
	@GetMapping("/edit/{id}")
		public String editAppointment ( Model model,@PathVariable int id) {
		
		Appointment a = ap.getAppointmentById(id);
		model.addAttribute("appointment", a);
		return "edit_form";
	}
	
	//Display new update will ll appointments
	@PostMapping("/save")
	public String displayAppoinment(Model model, @ModelAttribute Appointment appointment ) {
		
		ap.updateAppointment(appointment);
		ArrayList<Appointment> list= ap.getAllAppointment();
		model.addAttribute("list",list);
		return "index";
	}
	
	@GetMapping("/delete/{id}")
	public String removeAppoinments(Model model, @PathVariable int id, Appointment appointment) {
	   ap.deleteAppointmentById(id);
	   ArrayList<Appointment> list= ap.getAllAppointment();
		model.addAttribute("list",list);
	   
	      return "index";
	}
	
}
