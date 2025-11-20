package Main;

import Models.Pet;
import Models.Microchip;
import Service.PetServiceImpl;
import Service.MicrochipServiceImpl;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;


 // Maneja las operaciones del menú (CRUD)
 // Y conecta la interfaz de usuario con los servicios

public class MenuHandler {
    
    private final Scanner scanner = new Scanner(System.in);
    private final PetServiceImpl petService = new PetServiceImpl();
    private final MicrochipServiceImpl microchipService = new MicrochipServiceImpl();
    private final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    // Mascotas 
    
    public void crearMascota() {
        try {
            System.out.println("\n--- CREAR MASCOTA ---");
            scanner.nextLine();
            
            System.out.print("Nombre: ");
            String name = scanner.nextLine().toUpperCase();
            
            System.out.print("Especie: ");
            String species = scanner.nextLine().toUpperCase();
            
            System.out.print("Raza (opcional): ");
            String breed = scanner.nextLine();
            
            System.out.print("Fecha nacimiento (YYYY-MM-DD, opcional): ");
            String dateStr = scanner.nextLine();
            LocalDate birthDate = dateStr.isEmpty() ? null : LocalDate.parse(dateStr, dateFormat);
            
            System.out.print("Dueño: ");
            String owner = scanner.nextLine().toUpperCase();
            
            Pet pet = new Pet();
            pet.setName(name);
            pet.setSpecies(species);
            pet.setBreed(breed.isEmpty() ? null : breed);
            pet.setBirthDate(birthDate);
            pet.setOwner(owner);
            pet.setDeleted(false);
            
            petService.insertar(pet);
            System.out.println("✓ Mascota creada con ID: " + pet.getId());
            
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }
    
    public void listarMascotas() {
        try {
            List<Pet> pets = petService.getAll();
            
            if (pets.isEmpty()) {
                System.out.println("\nNo hay mascotas registradas");
            } else {
                for (Pet p : pets) {
                    System.out.println("\n" + p);
                }
                System.out.println("\nTotal: " + pets.size());
            }
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }
    
    public void buscarMascotaPorId() {
        try {
            System.out.print("\nID de mascota: ");
            Long id = scanner.nextLong();
            
            Pet pet = petService.getById(id);
            System.out.println(pet != null ? "\n" + pet : "\nNo encontrada");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
            scanner.nextLine();
        }
    }
    
    public void buscarMascotaPorDuenio() {
        try {
            scanner.nextLine();
            System.out.print("\nDueño: ");
            String owner = scanner.nextLine().toUpperCase();
            
            List<Pet> pets = petService.buscarPorDuenio(owner);
            
            if (pets.isEmpty()) {
                System.out.println("\nNo se encontraron mascotas");
            } else {
                pets.forEach(p -> System.out.println("\n" + p));
                System.out.println("\nTotal: " + pets.size());
            }
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }
    
    public void actualizarMascota() {
        try {
            System.out.print("\nID de mascota: ");
            Long id = scanner.nextLong();
            scanner.nextLine();
            
            Pet pet = petService.getById(id);
            if (pet == null) {
                System.out.println("No encontrada");
                return;
            }
            
            System.out.println("\nDatos actuales:\n" + pet);
            System.out.println("\n(Enter para mantener)");
            
            System.out.print("Nombre: ");
            String name = scanner.nextLine().toUpperCase();
            if (!name.isEmpty()) pet.setName(name);
            
            System.out.print("Especie: ");
            String species = scanner.nextLine().toUpperCase();
            if (!species.isEmpty()) pet.setSpecies(species);
            
            System.out.print("Dueño: ");
            String owner = scanner.nextLine().toUpperCase();
            if (!owner.isEmpty()) pet.setOwner(owner);
            
            petService.actualizar(pet);
            System.out.println("✓ Actualizada");
            
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }
    
    public void eliminarMascota() {
        try {
            System.out.print("\nID de mascota: ");
            Long id = scanner.nextLong();
            
            Pet pet = petService.getById(id);
            if (pet == null) {
                System.out.println("No encontrada");
                return;
            }
            
            System.out.println("\n" + pet);
            System.out.print("\n¿Eliminar? (S/N): ");
            scanner.nextLine();
            
            if (scanner.nextLine().equalsIgnoreCase("S")) {
                petService.eliminar(id);
                System.out.println("✓ Eliminada");
            } else {
                System.out.println("Cancelado");
            }
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }
    
    // Microchips
    
    public void crearMicrochip() {
        try {
            System.out.println("\n--- CREAR MICROCHIP ---");
            scanner.nextLine();
            
            System.out.print("Código: ");
            String code = scanner.nextLine().toUpperCase();
            
            System.out.print("Fecha implantación (YYYY-MM-DD, opcional): ");
            String dateStr = scanner.nextLine();
            LocalDate implDate = dateStr.isEmpty() ? null : LocalDate.parse(dateStr, dateFormat);
            
            System.out.print("Veterinaria (opcional): ");
            String veterinary = scanner.nextLine();
            
            System.out.print("Observaciones (opcional): ");
            String obs = scanner.nextLine();
            
            Microchip micro = new Microchip();
            micro.setCode(code);
            micro.setImplantationDate(implDate);
            micro.setVeterinary(veterinary.isEmpty() ? null : veterinary);
            micro.setObservations(obs.isEmpty() ? null : obs);
            micro.setDeleted(false);
            
            microchipService.insertar(micro);
            System.out.println("✓ Microchip creado con ID: " + micro.getId());
            
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }
    
    public void listarMicrochips() {
        try {
            List<Microchip> microchips = microchipService.getAll();
            
            if (microchips.isEmpty()) {
                System.out.println("\nNo hay microchips registrados");
            } else {
                for (Microchip m : microchips) {
                    System.out.println("\n" + m);
                }
                System.out.println("\nTotal: " + microchips.size());
            }
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }
    
    public void buscarMicrochipPorId() {
        try {
            System.out.print("\nID de microchip: ");
            Long id = scanner.nextLong();
            
            Microchip micro = microchipService.getById(id);
            System.out.println(micro != null ? "\n" + micro : "\nNo encontrado");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
            scanner.nextLine();
        }
    }
    
    public void buscarMicrochipPorCodigo() {
        try {
            scanner.nextLine();
            System.out.print("\nCódigo: ");
            String code = scanner.nextLine().toUpperCase();
            
            Microchip micro = microchipService.buscarPorCodigo(code);
            System.out.println(micro != null ? "\n" + micro : "\nNo encontrado");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }
    
    public void actualizarMicrochip() {
        try {
            System.out.print("\nID de microchip: ");
            Long id = scanner.nextLong();
            scanner.nextLine();
            
            Microchip micro = microchipService.getById(id);
            if (micro == null) {
                System.out.println("No encontrado");
                return;
            }
            
            System.out.println("\nDatos actuales:\n" + micro);
            System.out.println("\n(Enter para mantener)");
            
            System.out.print("Código: ");
            String code = scanner.nextLine().toUpperCase();
            if (!code.isEmpty()) micro.setCode(code);
            
            System.out.print("Veterinaria: ");
            String vet = scanner.nextLine();
            if (!vet.isEmpty()) micro.setVeterinary(vet);
            
            microchipService.actualizar(micro);
            System.out.println("✓ Actualizado");
            
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }
    
    public void eliminarMicrochip() {
        try {
            System.out.print("\nID de microchip: ");
            Long id = scanner.nextLong();
            
            Microchip micro = microchipService.getById(id);
            if (micro == null) {
                System.out.println("No encontrado");
                return;
            }
            
            System.out.println("\n" + micro);
            System.out.print("\n¿Eliminar? (S/N): ");
            scanner.nextLine();
            
            if (scanner.nextLine().equalsIgnoreCase("S")) {
                microchipService.eliminar(id);
                System.out.println("✓ Eliminado");
            } else {
                System.out.println("Cancelado");
            }
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }
    
    // Asingacion
    
    
    // Asigna un microchip a una mascota (demuestra relación 1 a 1)
    
    public void asignarMicrochipAMascota() {
        try {
            System.out.println("\n--- ASIGNAR MICROCHIP ---");
            
            System.out.print("ID de mascota: ");
            Long petId = scanner.nextLong();
            
            System.out.print("ID de microchip: ");
            Long microchipId = scanner.nextLong();
            
            petService.asignarMicrochip(petId, microchipId);
            System.out.println("✓ Asignado exitosamente");
            
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
            scanner.nextLine();
        }
    }
}