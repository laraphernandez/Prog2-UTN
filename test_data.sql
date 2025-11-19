-- ============================================
-- DATOS DE PRUEBA
-- Franco Petrozzelli & Lara Hernández
-- ============================================

USE pets_microchips;

-- Limpiar datos previos (opcional)
-- DELETE FROM pets;
-- DELETE FROM microchips;

-- MICROCHIPS (creados primero)

-- Microchips sin asignar (disponibles)
INSERT INTO microchips (deleted, code, implantation_date, veterinary, observations)
VALUES 
  (FALSE, 'CHIP-0001-XYZ', '2024-01-15', 'Vet Central', 'Microchip estándar'),
  (FALSE, 'CHIP-0004-GHI', '2024-02-05', 'Vet Este', 'Disponible para asignar');

-- Microchips para asignar a mascotas específicas
INSERT INTO microchips (deleted, code, implantation_date, veterinary, observations)
VALUES 
  (FALSE, 'CHIP-0002-ABC', '2024-01-10', 'Vet Norte', 'Para Milo'),
  (FALSE, 'CHIP-0003-DEF', '2023-12-20', 'Vet Sur', 'Para Max');

-- MASCOTAS

-- Mascotas SIN microchip
INSERT INTO pets (deleted, name, species, breed, birth_date, owner, microchip_id)
VALUES 
  (FALSE, 'LUNA', 'PERRO', 'Mestizo', '2018-05-10', 'ANA PÉREZ', NULL),
  (FALSE, 'ROCKY', 'PERRO', 'Golden Retriever', '2019-03-15', 'JUAN LÓPEZ', NULL),
  (FALSE, 'GARFIELD', 'GATO', 'Persa', '2020-07-22', 'MARÍA RODRÍGUEZ', NULL);

-- Mascotas CON microchip asignado
INSERT INTO pets (deleted, name, species, breed, birth_date, owner, microchip_id)
VALUES 
  (FALSE, 'MILO', 'GATO', 'Siamés', '2020-03-01', 'CARLOS GÓMEZ',
   (SELECT id FROM microchips WHERE code = 'CHIP-0002-ABC')),
  (FALSE, 'MAX', 'PERRO', 'Labrador', '2017-11-08', 'LAURA FERNÁNDEZ',
   (SELECT id FROM microchips WHERE code = 'CHIP-0003-DEF'));

-- VERIFICACIÓN

-- Ver todas las mascotas con sus microchips
SELECT 
  p.id AS pet_id,
  p.name,
  p.species,
  p.owner,
  m.code AS microchip_code,
  m.veterinary
FROM pets p
LEFT JOIN microchips m ON p.microchip_id = m.id
WHERE p.deleted = FALSE;