package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {
        createOwner("Michael", "Weston");
        createOwner("Karen", "CentralPark");
        createOwner("Joe", "Dirt");
        System.out.println("loaded Owners...");

        createVet("Sand", "Witch");
        createVet("Silly", "Vet");
        System.out.println("loaded Vets...");

        createPetType("Dog");
        createPetType("Cat");
        createPetType("Fish");
        System.out.println("loaded Pet Types...");
    }

    private void createPetType(String name) {
        PetType petType = new PetType();
        petType.setName(name);
        PetType savedPetType = petTypeService.save(petType);

    }

    private void createVet(String firstName, String lastName) {
        Vet vet1 = new Vet();
        vet1.setFirstName(firstName);
        vet1.setLastName(lastName);
        Vet savedVet = vetService.save(vet1);
    }

    private void createOwner(String firstName, String lastName) {
        Owner owner1 = new Owner();
        owner1.setFirstName(firstName);
        owner1.setLastName(lastName);
        Owner savedOwner = ownerService.save(owner1);
    }
}
