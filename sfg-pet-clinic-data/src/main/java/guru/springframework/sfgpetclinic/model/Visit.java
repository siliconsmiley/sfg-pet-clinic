package guru.springframework.sfgpetclinic.model;

import java.time.LocalDate;
import java.util.Objects;

public class Visit extends BaseEntity {

    private LocalDate date;

    private String description;

    private Pet pet;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Visit visit = (Visit) o;
        return Objects.equals(date, visit.date) &&
                Objects.equals(description, visit.description) &&
                Objects.equals(pet, visit.pet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, description, pet);
    }

    @Override
    public String toString() {
        return "Visit{" +
                "date=" + date +
                ", description='" + description + '\'' +
                ", pet=" + pet +
                '}';
    }
}
