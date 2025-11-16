package Models;
import java.time.LocalDate;


public class Pet extends Base {
     // Atributos principales
    private String name;           // NOT NULL, máx. 60
    private String species;          // NOT NULL, máx. 30
    private String breed;             // máx. 60
    private LocalDate birthDate;
    private String owner;           // NOT NULL, máx. 120
    // Relación 1→1 con Microchip
    private Microchip microchip;
  
    /** Crea mascota sin ID. */
    public Pet() {
        super();
        }
    
     /** Constructor con datos de BD. */
    public Pet(int id, String name, String species,
                   String breed, LocalDate birthDate, String owner, Microchip microchip) {
        super(id, false);
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.birthDate = birthDate;
        this.owner = owner;
        this.microchip = microchip;
    }
    
    // -------------------------
    // Getters y Setters
    // -------------------------

    public String getName() {
        return name;
    }

    /** Define nombre. */
    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }
    /** Define especie. */
    public void setSpecies(String species) {
        this.species = species;
    }

    public String getBreed() {
        return breed;
    }

    /** Define raza. */
    public void setBreed(String breed) {
        this.breed = breed;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    /** Define fecha de nacimiento. */
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getOwner() {
        return owner;
    }

    /** Define dueño. */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Microchip getMicrochip() {
        return microchip;
    }
    
    /** Asocia microchip (null=sin microchip). */
    public void setMicrochip(Microchip microchip) {
        this.microchip = microchip;
    }
    
    @Override
    public String toString() {
        return "Pet {" +
               "id=" + getId() +
               ", deleted=" + isDeleted() +
               ", name='" + name + '\'' +
               ", species='" + species + '\'' +
               ", breed='" + breed + '\'' +
               ", birthDate=" + birthDate +
               ", owner='" + owner + '\'' +
               ", microchip=" + microchip +
               '}';
}
}

