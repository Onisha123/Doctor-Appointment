package ca.sheridancollege.edwaroni.database;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.edwaroni.beans.Appointment;



@Repository 
public class DatabaseAccess {
 
	@Autowired
	protected NamedParameterJdbcTemplate jdbc;
	
public void insertAppontment(Appointment appointment) {
		
		MapSqlParameterSource namedParameters =
		new MapSqlParameterSource();
		namedParameters.addValue("firstName", appointment.getFirstName());
		namedParameters.addValue("email", appointment.getEmail());
		namedParameters.addValue("appointmentDate", appointment.getAppointmentDate());
		namedParameters.addValue("appointmentTime", appointment.getAppointmentTime());
		
		String query="INSERT INTO appointment (firstName,email,appointmentDate,appointmentTime) VALUES (:name,:email,:date,:time)";
		
		jdbc.update(query, namedParameters);
		}

public void updateAppointment(Appointment appointment) {
	String sql = "update appointment set firstName=:name, email=:email, appointmentDate=:date, appointmentTime=:time where id=:id";
	
	Map <String,Object> params =new HashMap<String, Object>();
	
	params.put("name", appointment.getFirstName());
	params.put("email", appointment.getEmail());
	params.put("date", appointment.getAppointmentDate());
	params.put("time", appointment.getAppointmentTime());
	params.put("id", appointment.getId());
	
	jdbc.update(sql,params);
}
public void deleteAppointmentById(int id) {
	String query="delete from appointment where id=:id";
	MapSqlParameterSource namedParameters = new MapSqlParameterSource();
	namedParameters.addValue("id", id);
	
	jdbc.update(query, namedParameters);
	}

public Appointment getAppointmentById(int id) {
	//assumption : id provided is the correct id
	String query = "SELECT id, firstName, email,appointmentDate,appointmentTime FROM appointment where id =:id";
	ArrayList<Appointment> list = new ArrayList<Appointment>();
	Map<String, Object> map = new HashMap <String, Object>();
	map.put("id", id);
	
	List<Map<String, Object>> rows = jdbc.queryForList(query,map);
	
	System.out.println(rows);
	for (Map<String, Object> row : rows) {
	list.add(new Appointment((Long) row.get("id"), (String) row.get("firrstName"),
	(String) row.get("email"), (((java.sql.Date) row.get("appointmentDate")).toLocalDate()), (((java.sql.Time) row.get("appointmentTime")).toLocalTime())));
	}
	
	return list.get(0);
	}

public ArrayList<Appointment> getAllAppointment() {
	String q = "Select id, firstName, email, appointmentDate, appointmentTime FROM  appointment";
	ArrayList<Appointment> appointment =
	(ArrayList<Appointment>)jdbc.query(q,
	new BeanPropertyRowMapper<Appointment>(Appointment.class));
	return appointment;
}
public void addAppointmnet(Appointment appointment) {
	
	MapSqlParameterSource namedParameters =
	new MapSqlParameterSource();
	namedParameters.addValue("name", appointment.getFirstName());
	namedParameters.addValue("email", appointment.getEmail());
	namedParameters.addValue("date", appointment.getAppointmentDate());
	namedParameters.addValue("time", appointment.getAppointmentTime());
	
	String query="INSERT INTO appointment (firstName,email,appointmentDate,appointmentTime) VALUES (:name,:email,:date,:time)";
	 
	jdbc.update(query, namedParameters);
	}
 
}

