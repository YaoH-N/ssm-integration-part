package com.atguigu.service.impl;

import com.atguigu.mapper.EmployeeMapper;
import com.atguigu.pojo.Employee;
import com.atguigu.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: 牛耀辉
 * Date: 2024/11/20 21:32
 * Description:
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapperImpl;

    @Override
    public List<Employee> findAll() {
        return employeeMapperImpl.queryList();
    }
}
