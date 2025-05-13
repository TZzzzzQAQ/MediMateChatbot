package com.friedchicken.java.ai.langchain4j.tools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.friedchicken.java.ai.langchain4j.entity.Appointment;
import com.friedchicken.java.ai.langchain4j.service.AppointmentService;

import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;

@Component
public class AppointmentTools {
  @Autowired
  private AppointmentService appointmentService;

  @Tool(name = "Book Appointment", value = "Based on the parameters, first execute the tool method queryDepartment to check if an appointment can be made, directly inform the user whether it is possible, and let the user confirm all appointment details. After the user confirms, proceed with the booking.")
  public String bookAppointment(Appointment appointment) {
    Appointment appointmentDB = appointmentService.getOne(appointment);
    if (appointmentDB == null) {
      appointment.setId(null);
      if (appointmentService.save(appointment)) {
        return "Appointment successful, returning appointment details.";
      } else {
        return "Appointment failed.";
      }
    }
    return "You already have an appointment for the same department and time.";
  }

  @Tool(name = "Cancel Appointment", value = "Based on the parameters, check if the appointment exists. If it exists, delete the appointment record and return a success message; otherwise, return a failure message.")
  public String cancelAppointment(Appointment appointment) {
    Appointment appointmentDB = appointmentService.getOne(appointment);
    if (appointmentDB != null) {
      if (appointmentService.removeById(appointmentDB.getId())) {
        return "Appointment cancellation successful.";
      } else {
        return "Appointment cancellation failed.";
      }
    }
    return "You have no appointment records. Please verify the department and time.";
  }

  @Tool(name = "Check Availability", value = "Check if there are available slots based on the department name, date, time, and doctor, and return the result to the user.")
  public boolean queryDepartment(
      @P(value = "Department Name") String name,
      @P(value = "Date") String date,
      @P(value = "Time (options: Morning, Afternoon)") String time,
      @P(value = "Doctor Name", required = false) String doctorName) {
    System.out.println("Checking availability...");
    System.out.println("Department Name: " + name);
    System.out.println("Date: " + date);
    System.out.println("Time: " + time);
    System.out.println("Doctor Name: " + doctorName);
    // TODO: Maintain doctor scheduling information:
    // If no doctor name is specified, check if there are any available doctors
    // based on other conditions (return true if available, false otherwise);
    // If a doctor name is specified, check if the doctor has a schedule (return
    // false if no schedule exists);
    // If a schedule exists, check if the doctor's schedule is fully booked (return
    // false if fully booked, true if there are available slots).
    return true;
  }
}
