package com.atguigu.mapper;

import com.atguigu.pojo.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author: 牛耀辉
 * Date: 2024/11/20 21:28
 * Description:
 */
public interface EmployeeMapper {

    // 查询全部员工信息
    List<Employee> queryList();
}
