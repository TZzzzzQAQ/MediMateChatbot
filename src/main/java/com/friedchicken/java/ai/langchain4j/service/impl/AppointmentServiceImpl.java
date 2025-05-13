package com.friedchicken.java.ai.langchain4j.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.friedchicken.java.ai.langchain4j.entity.Appointment;
import com.friedchicken.java.ai.langchain4j.mapper.AppointmentMapper;
import com.friedchicken.java.ai.langchain4j.service.AppointmentService;

@Service
public class AppointmentServiceImpl extends ServiceImpl<AppointmentMapper, Appointment>
    implements AppointmentService {

  @Override
  public Appointment getOne(Appointment appointment) {
    LambdaQueryWrapper<Appointment> queryWrapper = new LambdaQueryWrapper<>();
    queryWrapper.eq(Appointment::getUsername, appointment.getUsername());
    queryWrapper.eq(Appointment::getIdCard, appointment.getIdCard());
    queryWrapper.eq(Appointment::getDepartment, appointment.getDepartment());
    queryWrapper.eq(Appointment::getDate, appointment.getDate());
    queryWrapper.eq(Appointment::getTime, appointment.getTime());
    Appointment appointmentDB = baseMapper.selectOne(queryWrapper);
    return appointmentDB;
  }
}