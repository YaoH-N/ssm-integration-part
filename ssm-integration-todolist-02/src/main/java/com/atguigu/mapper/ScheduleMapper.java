package com.atguigu.mapper;

import com.atguigu.pojo.Schedule;

import java.util.List;

/**
 * Author: 牛耀辉
 * Date: 2024/11/21 21:58
 * Description:
 */
public interface ScheduleMapper {
    List<Schedule> queryList();

    int deleteById(Integer id);

    int insert(Schedule schedule);

    int update(Schedule schedule);
}
