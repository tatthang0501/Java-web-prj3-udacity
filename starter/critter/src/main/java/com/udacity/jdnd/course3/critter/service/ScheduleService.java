package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.dto.ScheduleDTO;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public ScheduleDTO save(ScheduleDTO scheduleDTO){
        Schedule schedule = new Schedule();
        schedule.setDate(scheduleDTO.getDate());
        schedule.setActivities(scheduleDTO.getActivities());

        List<Employee> listEmployeeRequest = new ArrayList<>();
        scheduleDTO.getEmployeeIds().forEach(id -> {
            Employee emp = new Employee();
            emp.setId(id);
            listEmployeeRequest.add(emp);
        });
        schedule.setListEmployee(listEmployeeRequest);

        List<Pet> listPetRequest = new ArrayList<>();
        scheduleDTO.getPetIds().forEach(id -> {
            Pet pet = new Pet();
            pet.setId(id);
            listPetRequest.add(pet);
        });
        schedule.setListPet(listPetRequest);

        if(scheduleRepository.save(schedule) != null){
            return copyScheduleEntityToDto(schedule);
        }
        throw new UnsupportedOperationException("Cannot save schedule");
    }

    public List<ScheduleDTO> getAllSchedule(){
        List<Schedule> listAllSchedule = scheduleRepository.findAll();
        List<ScheduleDTO> listAllScheduleDto = new ArrayList<>();
        listAllSchedule.forEach(schedule -> {
            listAllScheduleDto.add(copyScheduleEntityToDto(schedule));
        });

        return listAllScheduleDto;
    }

    public List<ScheduleDTO> getSchedulesForPet(long petId){
        List<Schedule> listScheduleFound = scheduleRepository.getSchedulesByPetId(petId);
        if(listScheduleFound != null){
            return copyListScheduleEntityToDto(listScheduleFound);
        }
        throw new UnsupportedOperationException("No schedule found for this petId");
    }

    public List<ScheduleDTO> getSchedulesForEmployee(long employeeId){
        List<Schedule> listScheduleFound = scheduleRepository.getSchedulesByEmployeeId(employeeId);
        if(listScheduleFound != null){
            return copyListScheduleEntityToDto(listScheduleFound);
        }
        throw new UnsupportedOperationException("No schedule found for this employeeId");
    }

    public List<ScheduleDTO> getSchedulesForCustomer(long customerId){
        List<Schedule> listScheduleFound = scheduleRepository.getSchedulesByCustomerId(customerId);
        if(listScheduleFound != null){
            return copyListScheduleEntityToDto(listScheduleFound);
        }
        throw new UnsupportedOperationException("No schedule found for this employeeId");
    }

    public ScheduleDTO copyScheduleEntityToDto(Schedule schedule){
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setId(schedule.getId());
        scheduleDTO.setActivities(schedule.getActivities());
        scheduleDTO.setDate(schedule.getDate());

        List<Long> listEmpIds = new ArrayList<>();
        schedule.getListEmployee().forEach(employee -> {
            listEmpIds.add(employee.getId());
        });
        scheduleDTO.setEmployeeIds(listEmpIds);

        List<Long> listPetIds = new ArrayList<>();
        schedule.getListPet().forEach(pet -> {
            listPetIds.add(pet.getId());
        });
        scheduleDTO.setPetIds(listPetIds);

        return scheduleDTO;
    }

    private List<ScheduleDTO> copyListScheduleEntityToDto(List<Schedule> listSchedule){
        List<ScheduleDTO> listAllScheduleDto = new ArrayList<>();
        listSchedule.forEach(schedule -> {
            listAllScheduleDto.add(copyScheduleEntityToDto(schedule));
        });
        return listAllScheduleDto;
    }
}
