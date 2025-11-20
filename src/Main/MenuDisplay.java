package Main;


// Clase para mostrar los menús por consola

public class MenuDisplay {
    
   
  // Muestra el menú principal del sistema
     
    public void mostrarMenuPrincipal() {
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║       MENÚ PRINCIPAL               ║");
        System.out.println("╚════════════════════════════════════╝");
        System.out.println("  1. Gestión de Mascotas");
        System.out.println("  2. Gestión de Microchips");
        System.out.println("  3. Asignar Microchip a Mascota");
        System.out.println("  0. Salir");
        System.out.println("────────────────────────────────────");
    }
    
   
     // Muestra el submenú de operaciones con mascotas
    
    public void mostrarMenuMascotas() {
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║      GESTIÓN DE MASCOTAS           ║");
        System.out.println("╚════════════════════════════════════╝");
        System.out.println("  1. Crear Mascota");
        System.out.println("  2. Listar Todas");
        System.out.println("  3. Buscar por ID");
        System.out.println("  4. Buscar por Dueño");
        System.out.println("  5. Actualizar Mascota");
        System.out.println("  6. Eliminar Mascota");
        System.out.println("  0. Volver");
        System.out.println("────────────────────────────────────");
    }
    
  
     // Muestra el submenú de operaciones con microchips

    public void mostrarMenuMicrochips() {
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║     GESTIÓN DE MICROCHIPS          ║");
        System.out.println("╚════════════════════════════════════╝");
        System.out.println("  1. Crear Microchip");
        System.out.println("  2. Listar Todos");
        System.out.println("  3. Buscar por ID");
        System.out.println("  4. Buscar por Código");
        System.out.println("  5. Actualizar Microchip");
        System.out.println("  6. Eliminar Microchip");
        System.out.println("  0. Volver");
        System.out.println("────────────────────────────────────");
    }
}