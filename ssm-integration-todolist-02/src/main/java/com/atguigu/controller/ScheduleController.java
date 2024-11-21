package com.atguigu.controller;

import com.atguigu.pojo.Schedule;
import com.atguigu.service.ScheduleService;
import com.atguigu.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Author: 牛耀辉
 * Date: 2024/11/21 21:56
 * Description:
 */
@RestController
@RequestMapping("schedule")
@Slf4j
@CrossOrigin //允许其他源访问我们的controller  加上该注解浏览器就不拦截了！ 可以加载类或者单独的某个请求的方法上
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    //控制层：接收参数，响应结果
    @GetMapping("/{pageSize}/{currentPage}")
    public R page(@PathVariable int pageSize, @PathVariable int currentPage) {
        R r = scheduleService.page(pageSize, currentPage);
        // sl4j
        log.info("查询的数据为: {}", r);
        return r;
    }

    @DeleteMapping("/{id}")
    public R remove(@PathVariable Integer id) {
        R r = scheduleService.remove(id);
        return r;
    }

    @PostMapping
    public R save(@Validated @RequestBody Schedule schedule, BindingResult result) {
        if (result.hasErrors()) {
            return R.fail("参数为null, 不能保存！");
        }
        R r = scheduleService.save(schedule);
        return r;
    }

    @PutMapping
    public R update(@Validated @RequestBody Schedule schedule, BindingResult result) {
        if (result.hasErrors()) {
            return R.fail("参数为null, 不能修改！");
        }
        R r = scheduleService.update(schedule);
        return r;
    }
}
