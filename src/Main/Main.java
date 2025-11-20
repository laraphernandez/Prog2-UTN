package Main;


// Punto de entrada de la aplicación.
public class Main {
    
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║   SISTEMA DE GESTIÓN DE MASCOTAS          ║");
        System.out.println("║   Mascotas → Microchips (Relación 1→1)    ║");
        System.out.println("╚════════════════════════════════════════════╝\n");
        
        AppMenu menu = new AppMenu();
        menu.ejecutar();
        
        System.out.println("\n¡Hasta luego!");
    }
}