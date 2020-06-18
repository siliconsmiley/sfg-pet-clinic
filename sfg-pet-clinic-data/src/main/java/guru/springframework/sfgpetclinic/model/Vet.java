package guru.springframework.sfgpetclinic.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Vet extends Person {

    private Set<Specialty> specialties = new HashSet<>();

    public Set<Specialty> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(Set<Specialty> specialties) {
        this.specialties = specialties;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Vet vet = (Vet) o;
        return Objects.equals(specialties, vet.specialties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), specialties);
    }

    @Override
    public String toString() {
        return "Vet{" +
                "specialties=" + specialties +
                '}';
    }
}
