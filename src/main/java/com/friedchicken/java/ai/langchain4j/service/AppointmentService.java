package com.friedchicken.java.ai.langchain4j.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.friedchicken.java.ai.langchain4j.entity.Appointment;

public interface AppointmentService extends IService<Appointment> {
  Appointment getOne(Appointment appointment);
}