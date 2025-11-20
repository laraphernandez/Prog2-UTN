package Main;

import java.util.Scanner;


// Menú principal de la aplicación

public class AppMenu {
    
    private final Scanner scanner = new Scanner(System.in);
    private final MenuDisplay display = new MenuDisplay();
    private final MenuHandler handler = new MenuHandler();
    
    
// Ejecuta el bucle principal del menú
  
    public void ejecutar() {
        boolean continuar = true;
        
        while (continuar) {
            display.mostrarMenuPrincipal();
            int opcion = leerOpcion();
            
            switch (opcion) {
                case 1:
                    menuMascotas();
                    break;
                case 2:
                    menuMicrochips();
                    break;
                case 3:
                    handler.asignarMicrochipAMascota();
                    break;
                case 0:
                    continuar = false;
                    break;
                default:
                    System.out.println("❌ Opción inválida");
            }
        }
    }
    
   
    //Submenú de operaciones con mascotas
   
    private void menuMascotas() {
        boolean volver = false;
        
        while (!volver) {
            display.mostrarMenuMascotas();
            int opcion = leerOpcion();
            
            switch (opcion) {
                case 1:
                    handler.crearMascota();
                    break;
                case 2:
                    handler.listarMascotas();
                    break;
                case 3:
                    handler.buscarMascotaPorId();
                    break;
                case 4:
                    handler.buscarMascotaPorDuenio();
                    break;
                case 5:
                    handler.actualizarMascota();
                    break;
                case 6:
                    handler.eliminarMascota();
                    break;
                case 0:
                    volver = true;
                    break;
                default:
                    System.out.println("❌ Opción inválida");
            }
        }
    }
    
  
   // Submenú de operaciones con microchips

    private void menuMicrochips() {
        boolean volver = false;
        
        while (!volver) {
            display.mostrarMenuMicrochips();
            int opcion = leerOpcion();
            
            switch (opcion) {
                case 1:
                    handler.crearMicrochip();
                    break;
                case 2:
                    handler.listarMicrochips();
                    break;
                case 3:
                    handler.buscarMicrochipPorId();
                    break;
                case 4:
                    handler.buscarMicrochipPorCodigo();
                    break;
                case 5:
                    handler.actualizarMicrochip();
                    break;
                case 6:
                    handler.eliminarMicrochip();
                    break;
                case 0:
                    volver = true;
                    break;
                default:
                    System.out.println("❌ Opción inválida");
            }
        }
    }
    

   // Lee una opción numérica del usuario con manejo de errores

    private int leerOpcion() {
        try {
            System.out.print("\n➤ Opción: ");
            return scanner.nextInt();
        } catch (Exception e) {
            scanner.nextLine(); // Limpiar buffer
            return -1;
        }
    }
}