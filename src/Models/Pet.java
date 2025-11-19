package Models;

import java.time.LocalDate;

/**
 * Clase Pet (Entidad A)
 * Representa una mascota en el sistema.
 */
public class Pet extends Base {
    
    private String name;                // Nombre de la mascota
    private String species;             // Especie (perro, gato, etc.)
    private String breed;               // Raza
    private LocalDate birthDate;        // Fecha de nacimiento
    private String owner;               // Dueño
    private Microchip microchip;        // Relación 1→1 con Microchip
    
    // Constructor vacío
    public Pet() {
        super();
    }
    
    // Constructor completo
    public Pet(Long id, Boolean deleted, String name, String species, String breed,
               LocalDate birthDate, String owner) {
        super(id, deleted);
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.birthDate = birthDate;
        this.owner = owner;
    }
    
    // GETTERS Y SETTERS
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getSpecies() {
        return species;
    }
    
    public void setSpecies(String species) {
        this.species = species;
    }
    
    public String getBreed() {
        return breed;
    }
    
    public void setBreed(String breed) {
        this.breed = breed;
    }
    
    public LocalDate getBirthDate() {
        return birthDate;
    }
    
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
    
    public String getOwner() {
        return owner;
    }
    
    public void setOwner(String owner) {
        this.owner = owner;
    }
    
    public Microchip getMicrochip() {
        return microchip;
    }
    
    public void setMicrochip(Microchip microchip) {
        this.microchip = microchip;
    }
    
    // método toString()
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        sb.append("  MASCOTA ID: ").append(getId()).append("\n");
        sb.append("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        sb.append("  Nombre: ").append(name).append("\n");
        sb.append("  Especie: ").append(species).append("\n");
        sb.append("  Raza: ").append(breed != null ? breed : "N/A").append("\n");
        sb.append("  Fecha nacimiento: ").append(birthDate != null ? birthDate : "N/A").append("\n");
        sb.append("  Dueño: ").append(owner).append("\n");
        
        // Mostrar info del microchip si existe
        if (microchip != null) {
            sb.append("  ┌─ Microchip asignado:\n");
            sb.append("  │  Código: ").append(microchip.getCode()).append("\n");
            sb.append("  │  Fecha impl.: ").append(microchip.getImplantationDate() != null ? 
                     microchip.getImplantationDate() : "N/A").append("\n");
            sb.append("  └─ Veterinaria: ").append(microchip.getVeterinary() != null ? 
                     microchip.getVeterinary() : "N/A").append("\n");
        } else {
            sb.append("  Sin microchip asignado\n");
        }
        
        sb.append("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        return sb.toString();
    }
}