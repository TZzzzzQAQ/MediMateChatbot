package com.friedchicken.java.ai.langchain4j.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.friedchicken.java.ai.langchain4j.entity.Appointment;

@Mapper
public interface AppointmentMapper extends BaseMapper<Appointment> {
}