package ca.sheridancollege.edwaroni.beans;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment implements Serializable {

	private static final long serialVersionUID = 1L;
      
	
	  private Long id;
	  private String firstName;
	  private String email;
	  
	  @DateTimeFormat(pattern = "yyyy-MM-dd")
      private LocalDate appointmentDate;
	  @DateTimeFormat(pattern = "HH:mm")
	  private LocalTime appointmentTime;
}
