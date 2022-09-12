package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.dto.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public CustomerDTO save(CustomerDTO customerDTO){
        Customer savingCustomer = new Customer();
        savingCustomer.setPhoneNumber(customerDTO.getPhoneNumber());
        savingCustomer.setNotes(customerDTO.getNotes());
        savingCustomer.setName(customerDTO.getName());
        savingCustomer.setPetList(null);

        if(customerRepository.save(savingCustomer) != null){
            customerDTO.setId(savingCustomer.getId());
            return customerDTO;
        }
        throw new UnsupportedOperationException("Cannot save customer");
    }

    public CustomerDTO getCustomerByPetId(long petId){
        Customer petOwnerFound = customerRepository.findCustomerByPetId(petId);
        if(petOwnerFound != null){
            return copyCustomerEntityToDto(petOwnerFound);
        }
        throw new UnsupportedOperationException("Cannot save customer");
    }

    public List<CustomerDTO> getAllCustomer(){
        List<Customer> listAllCustomer = customerRepository.findAll();
        List<CustomerDTO> listAllCustomerDto = new ArrayList<>();
        listAllCustomer.forEach(customer -> {
            listAllCustomerDto.add(copyCustomerEntityToDto(customer));
        });

        return listAllCustomerDto;
    }

    //Common method to copy a customer entity to dto
    private CustomerDTO copyCustomerEntityToDto(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setName(customer.getName());
        customerDTO.setNotes(customer.getNotes());
        customerDTO.setPhoneNumber(customer.getPhoneNumber());
        List<Long> petIds = new ArrayList<>();

        if(customer.getPetList() != null){
            customer.getPetList().forEach(p -> {
                petIds.add(p.getId());
            });
        }

        customerDTO.setPetIds(petIds);

        return customerDTO;
    }
}
