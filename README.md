# Sistema de Gestión de Mascotas y Microchips

Trabajo Práctico Integrador - Programación 2  
UTN - Universidad Tecnológica Nacional

## Integrantes
- Franco Petrozzelli
- Lara Hernández

## Descripción
Sistema de gestión que permite administrar mascotas y sus microchips asociados. 
Cada mascota puede tener un microchip asignado de forma opcional.

## Tecnologías utilizadas
- Java 
- MySQL
- JDBC para conexión a base de datos
- NetBeans 

## Configuración de la Base de Datos

1. Crear la base de datos en MySQL:
```sql
CREATE DATABASE mascotas_microchips;
```

2. Ejecutar el script SQL incluido en el proyecto para crear las tablas necesarias.

3. Configurar la conexión en el archivo de configuración con tus credenciales de MySQL.

## Cómo ejecutar el proyecto

1. Abrir el proyecto en NetBeans
2. Verificar que la conexión a MySQL esté configurada correctamente
3. Ejecutar la clase principal con el menú
4. Seguir las opciones del menú en consola

## Estructura del proyecto
```
src/
├── models/       # Clases de entidades (Pet, Microchip)
├── dao/          # Acceso a datos
├── services/     # Lógica de negocio
└── menu/         # Interfaz de usuario por consola
```

## Funcionalidades

- Registrar nuevas mascotas
- Asignar microchips a mascotas
- Consultar información de mascotas y microchips
- Actualizar datos
- Eliminar registros (soft delete)
- Listar mascotas y microchips registrados

## Notas
- El sistema usa "soft delete" para no borrar registros permanentemente
- Una mascota puede existir sin microchip
- Los microchips son únicos y no se pueden reasignar