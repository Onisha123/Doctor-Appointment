package ca.sheridancollege.edwaroni;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import ca.sheridancollege.edwaroni.beans.Appointment;
import ca.sheridancollege.edwaroni.database.DatabaseAccess;

;

@SpringBootTest
@AutoConfigureMockMvc
public class TestController {
	
	@Autowired
	private MockMvc mockMvc;
	@Autowired
    private DatabaseAccess  databaseAccess;
  
	@Test
	public void testLoadingIndexPage() throws Exception {
		this.mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("index"));
	}
	
	
	@Test
	public void testEditWithPathVariables() throws Exception {
	this.mockMvc
	.perform(get("/edit/{id}", 1))
	.andExpect(status().isOk())
	.andExpect(view().name("edit_form"));
	}
	
	
	@Test
	public void testAddAppointment() throws Exception {
	this.mockMvc
	.perform(post("/save")
	.flashAttr("appointment", new Appointment()))
	.andExpect(status().isOk())
	.andExpect(view().name("index"));
	

}
	
	 @Test
	 public void testBadRequestSaveAppointment() throws Exception {
	 this.mockMvc.perform(post("/save"))
	 .andExpect(status().isOk());
	 }
}