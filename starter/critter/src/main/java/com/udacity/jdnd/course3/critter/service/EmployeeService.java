package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.dto.CustomerDTO;
import com.udacity.jdnd.course3.critter.dto.EmployeeDTO;
import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeDTO save(EmployeeDTO employeeDTO){
        Employee savingEmployee = new Employee();
        savingEmployee.setName(employeeDTO.getName());
        savingEmployee.setSkills(employeeDTO.getSkills());
        savingEmployee.setDaysAvailable(employeeDTO.getDaysAvailable());
        savingEmployee.setListSchedule(null);

        if(employeeRepository.save(savingEmployee) != null){
            employeeDTO.setId(savingEmployee.getId());
            return employeeDTO;
        }
        throw new UnsupportedOperationException("Cannot save customer");
    }

    public EmployeeDTO getEmployeeById(long empId){
        Employee employeeFound = employeeRepository.findById(empId).orElse(null);
        if(employeeFound != null){
            return copyEmployeeEntityToDto((employeeFound));
        }
        throw new UnsupportedOperationException("Cannot find employee");
    }

    private EmployeeDTO copyEmployeeEntityToDto(Employee employee){
        EmployeeDTO employeeDto = new EmployeeDTO();
        employeeDto.setName(employee.getName());
        employeeDto.setId(employee.getId());
        employeeDto.setSkills(employee.getSkills());
        employeeDto.setDaysAvailable(employee.getDaysAvailable());

        return employeeDto;
    }
}
