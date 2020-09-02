package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mhw
 */
@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final PetService petService;
    private final SpecialtyService specialtyService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, PetService petService, SpecialtyService specialtyService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.petService = petService;
        this.specialtyService = specialtyService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {
        int count = petTypeService.findAll().size();
        if (count == 0) {
            loadData();
        }
    }

    private void loadData() {
        List<Owner> owners = new ArrayList<>();
        owners.add(createOwner("Michael", "Weston", "123 Main St.", "City1", "1234567890"));
        owners.add(createOwner("Karen", "CentralPark", "001 Privilege Dr.", "City2", "0001112222"));
        owners.add(createOwner("Joe", "Dirt", "address3", "city3", "3334445555"));
        System.out.println("loaded Owners...");

        List<Specialty> specialties = new ArrayList<>();
        specialties.add(createSpecialty("Radiology"));
        specialties.add(createSpecialty("Surgery"));
        specialties.add(createSpecialty("Dentistry"));
        System.out.println("loaded Specialties...");

        List<Vet> vets = new ArrayList<>();
        vets.add(createVet("Sand", "Witch", specialties.toArray(new Specialty[specialties.size()])[0]));
        vets.add(createVet("Silly", "Vet", specialties.toArray(new Specialty[specialties.size()])[1]));
        vets.add(createVet("Vetty", "McVetface", specialties.toArray(new Specialty[specialties.size()])[2]));
        System.out.println("loaded Vets...");

        List<PetType> petTypes = new ArrayList<>();
        petTypes.add(createPetType("Dog"));
        petTypes.add(createPetType("Cat"));
        petTypes.add(createPetType("Fish"));
        System.out.println("loaded Pet Types...");

        List<Pet> pets = new ArrayList<>();
        // add a dog to Michael
        pets.add(createPet("Fido",
                petTypes.get(0),
                LocalDate.now(),
                owners.get(0)));
        // add a cat to Karen
        pets.add(createPet("Shadow",
                petTypes.get(1),
                LocalDate.now(),
                owners.get(1)));
        System.out.println("added Pets to Owners...");

        List<Visit> visits = new ArrayList<>();
        visits.add(createVisit(pets.get(0),
                LocalDate.now(),
                "Ate a toy."));

        visits.add(createVisit(pets.get(1),
                LocalDate.now(),
                "Covid-Cat"));
        System.out.println("created Visits...");
    }

    private Specialty createSpecialty(String description) {
        Specialty specialty = new Specialty();
        specialty.setDescription(description);
        Specialty savedSpecialty = specialtyService.save(specialty);
        return savedSpecialty;
    }

    private PetType createPetType(String name) {
        PetType petType = new PetType();
        petType.setName(name);
        PetType savedPetType = petTypeService.save(petType);
        return savedPetType;
    }

    private Vet createVet(String firstName, String lastName, Specialty specialty) {
        Vet vet1 = new Vet();
        vet1.setFirstName(firstName);
        vet1.setLastName(lastName);
        vet1.getSpecialties().add(specialty);
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
        pet.setBirthDate(birthday);
        pet.setOwner(owner);
        owner.getPets().add(pet);

        Pet savedPet = petService.save(pet);
        ownerService.save(owner);

        return savedPet;
    }

    private Visit createVisit(Pet pet, LocalDate date, String description) {
        Visit visit = new Visit();
        visit.setDescription(description);
        visit.setDate(date);
        visit.setPet(pet);
        visitService.save(visit);
        return visit;
    }
}
