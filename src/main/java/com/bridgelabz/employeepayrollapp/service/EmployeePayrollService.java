package com.bridgelabz.employeepayrollapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import lombok.extern.slf4j.*;

import com.bridgelabz.employeepayrollapp.dto.EmployeePayrollDTO;
import com.bridgelabz.employeepayrollapp.exceptions.EmployeePayrollCustomException;
import com.bridgelabz.employeepayrollapp.model.EmployeePayrollData;
import com.bridgelabz.employeepayrollapp.repository.EmployeePayrollRepository;

@Service
public class EmployeePayrollService implements IEmployeePayrollService{

	@Autowired
    private EmployeePayrollRepository employeePayrollRepository;

    private List<EmployeePayrollData> employeePayrollDataList = new ArrayList<>();

    @Override
    public List<EmployeePayrollData> getEmployeePayrollData() {
        return employeePayrollDataList;
    }

    @Override
    public EmployeePayrollData getEmployeePayrollDataById(int employeeId) {
        return employeePayrollDataList.stream()
                                      .filter(employeePayrollData -> employeePayrollData.getEmployeeId() == employeeId)
                                      .findFirst()
                                      .orElseThrow(() -> new EmployeePayrollCustomException("Employee not found"));
    }

    @Override
    public EmployeePayrollData createEmployeePayrollData(EmployeePayrollDTO employeePayrollDTO) {
        EmployeePayrollData employeePayrollData = null;
        employeePayrollData = new EmployeePayrollData(employeePayrollDTO);
        employeePayrollDataList.add(employeePayrollData);
//        log.debug("Employee data : " + employeePayrollData.toString());
        return employeePayrollRepository.save(employeePayrollData);
    }

    @Override
    public EmployeePayrollData updateEmployeePayrollData(int employeeId, EmployeePayrollDTO employeePayrollDTO) {
        EmployeePayrollData employeePayrollData = this.getEmployeePayrollDataById(employeeId);
        employeePayrollData.setName(employeePayrollDTO.name);
        employeePayrollData.setSalary(employeePayrollDTO.salary);
        employeePayrollDataList.set(employeeId - 1, employeePayrollData);
        return employeePayrollData;
    }

    @Override
    public void deleteEmployeePayrollData(int employeeId) {
        employeePayrollDataList.remove(employeeId - 1);
    }
}
