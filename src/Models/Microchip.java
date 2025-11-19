package Models;

import java.time.LocalDate;

/**
 * Clase Microchip (Entidad B)
 * Representa un microchip de identificación para mascotas.
 * 
 * Explicación para el video:
 * - Esta es la clase "B" en la relación 1→1
 * - NO tiene referencia a Pet (relación unidireccional)
 * - Solo Pet conoce a Microchip, no al revés
 */
public class Microchip extends Base {
    
    private String code;                    // Código único del microchip
    private LocalDate implantationDate;     // Fecha de implantación
    private String veterinary;              // Veterinaria que lo implantó
    private String observations;            // Observaciones adicionales
    
    // Constructor vacío
    public Microchip() {
        super();
    }
    
    // Constructor completo
    public Microchip(Long id, Boolean deleted, String code, LocalDate implantationDate, 
                     String veterinary, String observations) {
        super(id, deleted);
        this.code = code;
        this.implantationDate = implantationDate;
        this.veterinary = veterinary;
        this.observations = observations;
    }
    
    // GETTERS Y SETTERS 
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public LocalDate getImplantationDate() {
        return implantationDate;
    }
    
    public void setImplantationDate(LocalDate implantationDate) {
        this.implantationDate = implantationDate;
    }
    
    public String getVeterinary() {
        return veterinary;
    }
    
    public void setVeterinary(String veterinary) {
        this.veterinary = veterinary;
    }
    
    public String getObservations() {
        return observations;
    }
    
    public void setObservations(String observations) {
        this.observations = observations;
    }
    
    // método toString()
    
    @Override
    public String toString() {
        return "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n" +
               "  MICROCHIP ID: " + getId() + "\n" +
               "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n" +
               "  Código: " + code + "\n" +
               "  Fecha implantación: " + (implantationDate != null ? implantationDate : "N/A") + "\n" +
               "  Veterinaria: " + (veterinary != null ? veterinary : "N/A") + "\n" +
               "  Observaciones: " + (observations != null ? observations : "N/A") + "\n" +
               "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━";
    }
}