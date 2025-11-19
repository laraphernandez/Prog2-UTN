package Models;

// Clase base abstracta para todas las entidades del sistema
public abstract class Base {
    
    private Long id;
    private Boolean deleted;
    
    // Constructor vacío
    public Base() {
        this.deleted = false;  // Por defecto, no está eliminado
    }
    
    // Constructor completo
    public Base(Long id, Boolean deleted) {
        this.id = id;
        this.deleted = deleted;
    }
    
    // GETTERS Y SETTERS 
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Boolean getDeleted() {
        return deleted;
    }
    
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}