# 🐾 PetCare Tracker

**PetCare Tracker** es una aplicación Android construida con Jetpack Compose que permite registrar y gestionar información de mascotas de forma local.

---

## 📌 Objetivo de la Aplicación

El objetivo de PetCare Tracker es permitir a los usuarios:

- Registrar mascotas con sus datos básicos.
- Ver un listado general de todas las mascotas registradas.
- Editar la información de cada mascota.
- Eliminar mascotas si ya no están activas.
- Consultar el estado de vacunación y especie.
- Funcionar sin internet y sin backend, usando almacenamiento local (Room).

---

## 🧩 Funcionalidades más relevantes

- Pantalla de inicio/login simulada.
- Listado de mascotas.
- Detalle de cada mascota.
- Formulario de alta de mascota.
- Edición y eliminación de mascotas.
- Persistencia con Room.
- Validaciones de campos (nombre obligatorio, edad positiva, etc.).
- Navegación entre pantallas usando Jetpack Navigation Compose.
- Manejo de errores y estados de carga/vacío.

---

## 🎮 Manual mínimo de uso

### Ingreso
- Se accede directamente a la pantalla principal sin autenticación real (login simulado).

### Agregar mascota
1. Presiona el botón "+" en la esquina inferior.
2. Completa el formulario:
   - Nombre (mín. 3 caracteres)
   - Especie (ej: Perro, Gato)
   - Edad (número mayor a 0)
   - Estado de vacunación (checkbox)

3. Pulsa "Guardar".

### Ver mascotas
- Desde la pantalla principal puedes ver todas las mascotas registradas.
- Haz clic sobre una mascota para ver detalles.

### Editar o eliminar
- Desde el detalle de una mascota puedes editar sus datos o eliminarla.

---

## 📚 Arquitectura utilizada

```plaintext
app/
├── data/
│   ├── Pet.kt
│   ├── PetDao.kt
│   ├── PetDatabase.kt
│   └── PetRepository.kt
│
├── ui/
│   ├── screens/
│   │   ├── PetListScreen.kt
│   │   ├── PetDetailScreen.kt
│   │   ├── AddPetScreen.kt
│   │   └── EditPetScreen.kt
│   ├── viewmodels/
│   │   └── PetViewModel.kt
│   └── navigation/
│       └── NavGraph.kt
│
├── MainActivity.kt
└── App.kt
