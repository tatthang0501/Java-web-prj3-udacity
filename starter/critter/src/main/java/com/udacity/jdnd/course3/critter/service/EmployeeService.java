package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.critterenum.EmployeeSkill;
import com.udacity.jdnd.course3.critter.dto.EmployeeDTO;
import com.udacity.jdnd.course3.critter.dto.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
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

    public void setAvailabilityDayOfEmployee(Set<DayOfWeek> daysAvailable, long employeeId){
        Employee employeeFound = employeeRepository.findById(employeeId).orElse(null);
        if(employeeFound != null){
            employeeFound.setDaysAvailable(daysAvailable);
            employeeRepository.save(employeeFound);
        }
        else throw new UnsupportedOperationException("Cannot find employee");
    }

    public List<EmployeeDTO> findEmployeesForService(EmployeeRequestDTO employeeRequestDTO){
        DayOfWeek queryingDay = employeeRequestDTO.getDate().getDayOfWeek();
        Set<EmployeeSkill> queryingSkills = employeeRequestDTO.getSkills();

        Set<String> skillsString = new HashSet<>();
        queryingSkills.forEach(employeeSkill -> {
            skillsString.add(employeeSkill.toString());
        });

        Set<Employee> listEmployeeFound = employeeRepository.findEmployeesForService(queryingDay, skillsString);
        listEmployeeFound.forEach(employee -> {
            System.out.println(employee.toString());
        });
        List<EmployeeDTO> listEmployeeDtoFound = new ArrayList<>();
        if(listEmployeeFound != null && listEmployeeFound.size() != 0){
            listEmployeeFound.forEach(employee -> {
                if(employee.getSkills().containsAll(queryingSkills)){
                    listEmployeeDtoFound.add(copyEmployeeEntityToDto(employee));
                }
            });
            return listEmployeeDtoFound;
        }
        throw new UnsupportedOperationException("Cannot find any employee with this request");

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
