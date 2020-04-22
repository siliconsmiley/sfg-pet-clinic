package guru.springframework.sfgpetclinicdata.model;

import java.time.LocalDate;
import java.util.Objects;

public class Pet {
    private PetType petType;
    private Owner owner;
    private LocalDate birthdate;

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return Objects.equals(petType, pet.petType) &&
                Objects.equals(owner, pet.owner) &&
                Objects.equals(birthdate, pet.birthdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(petType, owner, birthdate);
    }

    @Override
    public String toString() {
        return "Pet{" +
                "petType=" + petType +
                ", owner=" + owner +
                ", birthdate=" + birthdate +
                '}';
    }
}
