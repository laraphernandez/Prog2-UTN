package Models;

import java.time.LocalDate;

public class Microchip extends Base {
    private String code;           // NOT NULL, UNIQUE, máx. 25
    private LocalDate implantationDate;
    private String veterinaryClinic;      // máx. 120
    private String observations;    // máx. 255

    /**
     * Constructor por defecto para crear un microchip nuevo.
     * El ID será asignado por la BD al insertar.
     * El flag eliminado se inicializa en false por Base.
     */
    public Microchip() {
        super();
    }
    /**
     * Constructor con todos los parámetros incluyendo ID.
     * Se utiliza al recuperar registros existentes de la base de datos.
     */
    public Microchip(int id, String code, LocalDate implantationDate,
                     String veterinaryClinic, String observations) {
        super(id, false); // Llama al constructor de Base con eliminado=false
        this.code = code;
        this.implantationDate = implantationDate;
        this.veterinaryClinic = veterinaryClinic;
        this.observations = observations;
    }


    public String getCode() {
        return code;
    }
    /**
     * Asigna el código identificador único del microchip.
     * @param code Código alfanumérico de hasta 25 caracteres
     */
    public void setCode(String code) {
        this.code = code;
    }

    public LocalDate getImplantationDate() {
        return implantationDate;
    }
    /**
     * Registra la fecha en la que el microchip fue implantado.
     * @param implantationDate Fecha de implantación del dispositivo
     */
    public void setImplantationDate(LocalDate implantationDate) {
        this.implantationDate = implantationDate;
    }

    public String getVeterinaryClinic() {
        return veterinaryClinic;
    }
    /**
     * Define el nombre de la clínica veterinaria donde se realizó la implantación.
     * @param veterinaryClinic Nombre de la veterinaria, máximo 120 caracteres
     */
    public void setVeterinaryClinic(String veterinaryClinic) {
        this.veterinaryClinic = veterinaryClinic;
    }

    public String getObservations() {
        return observations;
    }
    /**
     * Guarda notas o comentarios adicionales sobre el microchip.
     * @param observations Texto libre con un límite de 255 caracteres
     */
    public void setObservations(String observations) {
        this.observations = observations;
    }

    @Override
    public String toString() {
        return "Microchip{" +
                "id=" + getId() +
                ", deleted=" + isDeleted() +
                ", code='" + code + '\'' +
                ", implantationDate=" + implantationDate +
                ", veterinaryClinic='" + veterinaryClinic + '\'' +
                ", observations='" + observations + '\'' +
                '}';
    }
}
