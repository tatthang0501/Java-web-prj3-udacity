package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.dto.PetDTO;
import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    public PetDTO save(PetDTO petDTO){
        Pet savingPet = new Pet();
        savingPet.setName(petDTO.getName());
        savingPet.setNotes(petDTO.getNotes());
        savingPet.setPetType(petDTO.getType());
        savingPet.setBirthDate(petDTO.getBirthDate());
        Customer customer = new Customer();
        customer.setId(petDTO.getOwnerId());

        savingPet.setOwner(customer);

        if(petRepository.save(savingPet) != null){
            return copyPetEntityToDto(savingPet);
        }
        throw new UnsupportedOperationException("Cannot save pet");
    }

    public PetDTO getPetById(long petId){
        Pet pet = petRepository.findById(petId).orElse(null);
        if(pet != null){
            return copyPetEntityToDto(pet);
        }
        throw new UnsupportedOperationException("Cannot find pet with this petId");
    }

    public List<PetDTO> getAllPet() {
        List<Pet> listAllPet = petRepository.findAll();
        List<PetDTO> listAllPetDto = new ArrayList<>();
        if(listAllPet != null || listAllPet.size() != 0){
            listAllPet.forEach(pet -> {
                listAllPetDto.add(copyPetEntityToDto(pet));
            });
        }
        return listAllPetDto;
    }

    public List<PetDTO> getPetsByOwner(long ownerId){
        List<Pet> listAllPetFound = petRepository.getPetsByOwnerId(ownerId);
        List<PetDTO> listAllPetDto = new ArrayList<>();
        if(listAllPetFound != null || listAllPetFound.size() != 0){
            listAllPetFound.forEach(pet -> {
                listAllPetDto.add(copyPetEntityToDto(pet));
            });
        }
        return listAllPetDto;
    }

    private PetDTO copyPetEntityToDto(Pet pet){
        PetDTO petDTO = null;
        if(pet != null){
            petDTO = new PetDTO();
            petDTO.setId(pet.getId());
            petDTO.setType(pet.getPetType());
            petDTO.setName(pet.getName());
            petDTO.setOwnerId(pet.getOwner().getId());
            petDTO.setBirthDate(pet.getBirthDate());
            petDTO.setNotes(pet.getNotes());
        }
        return petDTO;
    }
}
