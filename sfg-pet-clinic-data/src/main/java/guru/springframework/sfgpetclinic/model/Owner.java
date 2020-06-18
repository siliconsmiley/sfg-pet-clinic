package guru.springframework.sfgpetclinic.model;

import java.util.Objects;
import java.util.Set;

public class Owner extends Person {

    private Set<Pet> pets;

    public Set<Pet> getPets() {
        return pets;
    }

    public void setPets(Set<Pet> pets) {
        this.pets = pets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Owner owner = (Owner) o;
        return Objects.equals(pets, owner.pets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), pets);
    }

    @Override
    public String toString() {
        return "Owner{" +
                "pets=" + pets +
                '}';
    }
}
