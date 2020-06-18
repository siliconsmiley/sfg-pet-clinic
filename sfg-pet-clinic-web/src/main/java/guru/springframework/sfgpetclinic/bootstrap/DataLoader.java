package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * @author mhw
 */
@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final PetService petService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, PetService petService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.petService = petService;
    }

    @Override
    public void run(String... args) throws Exception {
        Set<Owner> owners = new HashSet<>();
        owners.add(createOwner("Michael", "Weston", "123 Main St.", "City1", "1234567890"));
        owners.add(createOwner("Karen", "CentralPark", "001 Privilege Dr.", "City2", "0001112222"));
        owners.add(createOwner("Joe", "Dirt", "address3", "city3", "3334445555"));
        System.out.println("loaded Owners...");

        Set<Vet> vets = new HashSet<>();
        vets.add(createVet("Sand", "Witch"));
        vets.add(createVet("Silly", "Vet"));
        System.out.println("loaded Vets...");

        Set<PetType> petTypes = new HashSet<>();
        petTypes.add(createPetType("Dog"));
        petTypes.add(createPetType("Cat"));
        petTypes.add(createPetType("Fish"));
        System.out.println("loaded Pet Types...");

        Set<Pet> pets = new HashSet<>();
        // add a dog to Michael
        pets.add(createPet("Fido",
                petTypes.toArray(new PetType[petTypes.size()])[0],
                LocalDate.now(),
                owners.toArray(new Owner[owners.size()])[0]));
        // add a cat to Karen
        pets.add(createPet("Shadow",
                petTypes.toArray(new PetType[petTypes.size()])[1],
                LocalDate.now(),
                owners.toArray(new Owner[owners.size()])[0]));
    }

    private PetType createPetType(String name) {
        PetType petType = new PetType();
        petType.setName(name);
        PetType savedPetType = petTypeService.save(petType);
        return savedPetType;
    }

    private Vet createVet(String firstName, String lastName) {
        Vet vet1 = new Vet();
        vet1.setFirstName(firstName);
        vet1.setLastName(lastName);
        Vet savedVet = vetService.save(vet1);
        return savedVet;
    }

    private Owner createOwner(String firstName, String lastName, String address, String city, String telephone) {
        Owner owner1 = new Owner();
        owner1.setFirstName(firstName);
        owner1.setLastName(lastName);
        owner1.setAddress(address);
        owner1.setCity(city);
        owner1.setTelephone(telephone);
        Owner savedOwner = ownerService.save(owner1);
        return savedOwner;
    }

    private Pet createPet(String name, PetType petType, LocalDate birthday, Owner owner) {
        Pet pet = new Pet();
        pet.setName(name);
        pet.setPetType(petType);
        pet.setBirthdate(birthday);
        pet.setOwner(owner);
        owner.getPets().add(pet);
        ownerService.save(owner);

        Pet savedPet = petService.save(pet);
        return savedPet;
    }
}
