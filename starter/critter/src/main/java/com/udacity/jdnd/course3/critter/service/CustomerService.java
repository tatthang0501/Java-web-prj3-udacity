package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.dto.PetDTO;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.dto.CustomerDTO;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    PetRepository petRepository;
    @Autowired
    CustomerRepository customerRepository;

    public CustomerDTO save(CustomerDTO customerDTO){
        Customer savingCustomer = new Customer();
        savingCustomer.setPhoneNumber(customerDTO.getPhoneNumber());
        savingCustomer.setNotes(customerDTO.getNotes());
        savingCustomer.setName(customerDTO.getName());
        savingCustomer.setPetList(null);

        if(customerRepository.save(savingCustomer) != null){
            return copyCustomerEntityToDto(savingCustomer);

        }
        throw new UnsupportedOperationException("Cannot save customer");
    }

    public CustomerDTO getCustomerByPetId(long petId){
        Customer petOwnerFound = customerRepository.findCustomerByPetId(petId);
        petOwnerFound.setPetList(petRepository.getPetsByOwnerId(petOwnerFound.getId()));
        if(petOwnerFound != null){
            return copyCustomerEntityToDto(petOwnerFound);
        }
        throw new UnsupportedOperationException("Cannot find customer");
    }

    public List<CustomerDTO> getAllCustomer(){
        List<Customer> listAllCustomer = customerRepository.findAll();

        listAllCustomer.forEach(customer -> {
            customer.setPetList(petRepository.getPetsByOwnerId(customer.getId()));
        });

        List<CustomerDTO> listAllCustomerDto = new ArrayList<>();
        listAllCustomer.forEach(customer -> {

            CustomerDTO customerDTO = copyCustomerEntityToDto(customer);
            listAllCustomerDto.add(customerDTO);
        });

        return listAllCustomerDto;
    }

    public Customer getCustomerById(long customerId){
        Customer customerFound = customerRepository.findById(customerId).get();
        if(customerFound == null){
            throw new UnsupportedOperationException("Cannot find customer with this customerId");
        }
        return customerFound;
    }
    //Common method to copy a customer entity to dto
    private CustomerDTO copyCustomerEntityToDto(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setName(customer.getName());
        customerDTO.setNotes(customer.getNotes());
        customerDTO.setPhoneNumber(customer.getPhoneNumber());

        if(customer.getPetList() != null){
            List<Long> petIds = new ArrayList<>();
            customer.getPetList().forEach(p -> {
                petIds.add(p.getId());
            });
            customerDTO.setPetIds(petIds);
        }

        return customerDTO;
    }
}
