package com.bridgelabz.employeepayrollapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.employeepayrollapp.dto.EmployeePayrollDTO;
import com.bridgelabz.employeepayrollapp.dto.ResponseDTO;
import com.bridgelabz.employeepayrollapp.model.EmployeePayrollData;
import com.bridgelabz.employeepayrollapp.service.IEmployeePayrollService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("employeepayroll")
@Slf4j
public class EmployeePayrollRestController {
	
	@Autowired
	private IEmployeePayrollService iEmployeePayrollService;
	
	@RequestMapping(value = {"", "/"})
	public ResponseEntity<ResponseDTO> getEmployeePayrollData() {
	    List<EmployeePayrollData> employeePayrollDataList = null;
	    employeePayrollDataList = iEmployeePayrollService.getEmployeePayrollData();
	    ResponseDTO responseDTO = new ResponseDTO("Get Call Success!!", employeePayrollDataList);
	    return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
	}
	
	@GetMapping("/get/{employeeId}")
	public ResponseEntity<ResponseDTO> getEmployeePayrollDataById(@PathVariable("employeeId") int employeeId) {
	    EmployeePayrollData employeePayrollData = iEmployeePayrollService.getEmployeePayrollDataById(employeeId);
	    ResponseDTO responseDTO = new ResponseDTO("Get Call Success!!", employeePayrollData);
	    return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<ResponseDTO> addEmployeePayrollData(@Valid @RequestBody EmployeePayrollDTO employeePayrollDTO) {
		log.debug("Employee DTO : " + employeePayrollDTO.toString());
		EmployeePayrollData employeePayrollData = iEmployeePayrollService.createEmployeePayrollData(employeePayrollDTO);
	    ResponseDTO responseDTO = new ResponseDTO("Created Employee Payroll Data!!", employeePayrollData);
	    return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
	}
	
	@PutMapping("/update/{employeeId}")
	public ResponseEntity<ResponseDTO> updateEmployeePayrollData(@PathVariable("employeeId") int employeeId,
	                                                             @Valid @RequestBody EmployeePayrollDTO employeePayrollDTO) {
	    EmployeePayrollData employeePayrollData = iEmployeePayrollService.updateEmployeePayrollData(employeeId, employeePayrollDTO);
	    ResponseDTO responseDTO = new ResponseDTO("Updated Employee Payroll Data for ID", employeePayrollData);
	    return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{employeeId}")
	public ResponseEntity<ResponseDTO> deleteEmployeePayrollData(@PathVariable("employeeId") int employeeId) {
	    iEmployeePayrollService.deleteEmployeePayrollData(employeeId);
	    ResponseDTO responseDTO = new ResponseDTO("Deleted successfully", "Deleted ID : " + employeeId);
	    return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
	}
}
