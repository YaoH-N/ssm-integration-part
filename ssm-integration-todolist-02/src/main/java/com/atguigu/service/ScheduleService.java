package com.atguigu.service;

import com.atguigu.pojo.Schedule;
import com.atguigu.utils.R;

/**
 * Author: 牛耀辉
 * Date: 2024/11/21 21:57
 * Description:
 */
public interface ScheduleService {


    R page(int pageSize, int currentPage);

    R remove(Integer id);

    R save(Schedule schedule);

    R update(Schedule schedule);
}
