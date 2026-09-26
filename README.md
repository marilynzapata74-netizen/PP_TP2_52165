# PP_TP1_&lt;TU_LEGAJO&gt;

Trabajo Práctico N°1 — Programación Orientada a Objetos en Java
Cátedra: Paradigmas de Programación — UTN Facultad Regional Mendoza
Unidad 1 — Fundamentos de la POO e implementación básica en Java

## Descripción del proyecto

Sistema simple para administrar **eventos universitarios** (charlas, talleres, jornadas, etc.),
sus **actividades**, la **sala** asignada, los **estudiantes** y sus **inscripciones**.

El proyecto fue desarrollado de forma incremental siguiendo los 4 ejercicios del TP1:

- **Ejercicio 1:** clase `EventoUniversitario` con encapsulamiento, constructor de copia y contador estático de eventos.
- **Ejercicio 2:** se incorporan las clases `Sala`, `Actividad`, `Estudiante` e `Inscripcion`, con relaciones de agregación (Sala) y composición (Actividades).
- **Ejercicio 3:** `Actividad` pasa a ser **abstracta**, y se agregan las subclases concretas `Charla` y `Taller` (herencia + polimorfismo). El método `mostrarIdentificacion()` se declara `final` en `Actividad`.
- **Ejercicio 4:** mapa de memoria de ejecución (imagen) que representa cómo quedan vinculados en memoria los objetos creados en `App.main()`.

> El código final del repositorio corresponde al modelo del **Ejercicio 3** (que ya incluye y supera lo pedido en los Ejercicios 1 y 2), y es la base sobre la que se construyó el mapa de memoria del Ejercicio 4.

## Estructura de clases

| Clase | Rol |
|---|---|
| `EventoUniversitario` | Evento universitario. Compone `Actividad`es y agrega una `Sala`. |
| `Sala` | Sala física, existe independientemente del evento (agregación). |
| `Actividad` *(abstracta)* | Actividad genérica de un evento. Define `inscribir()`, `mostrarInscripciones()` y el método `final mostrarIdentificacion()`. Declara abstractos `calcularCostoMateriales()` y `getTipo()`. |
| `Charla` | Actividad gratuita, con un `disertante`. |
| `Taller` | Actividad cuyo costo depende de `requiereNotebook` ($5000 con notebook / $2000 sin notebook). |
| `Estudiante` | Estudiante que puede inscribirse en actividades. |
| `Inscripcion` | Vincula un `Estudiante` con una `Actividad` en una fecha y estado determinados. |
| `App` | Clase con el `main()` que ejecuta el escenario completo de prueba. |

## Decisiones de diseño

- **`crearActividad`**: la consigna pide que el método reciba, además de `id`, `titulo` y `cupo`, un parámetro `String tipo` ("Charla" o "Taller"). Como cada subtipo necesita además un dato propio (`disertante` para Charla, `requiereNotebook` para Taller), se implementaron **dos métodos sobrecargados**, ambos con el parámetro `tipo` (que se valida contra el tipo de actividad que corresponde crear):
  - `crearActividad(int id, String titulo, int cupo, String tipo, String disertante)` → crea una `Charla`.
  - `crearActividad(int id, String titulo, int cupo, String tipo, boolean requiereNotebook)` → crea un `Taller`.
- **`CUPO_MINIMO`**: la consigna define la constante pero no especifica su valor ni su uso; se fijó en `5` como valor de referencia (no se aplica ninguna validación automática con ella, ya que el enunciado no lo pide explícitamente).
- **`Inscripcion`** guarda referencias a `Estudiante` y `Actividad` (además de `fecha` y `estado`) para poder trazar correctamente esos vínculos en el mapa de memoria del Ejercicio 4.

## Cómo ejecutar

1. Clonar el repositorio.
2. Abrir la carpeta como proyecto en **IntelliJ IDEA**.
3. Ejecutar la clase `App` (contiene el método `main`).

La salida por consola muestra: los estudiantes registrados, el evento con su sala y actividades,
las inscripciones por actividad, el costo estimado del evento, la copia del evento (constructor
de copia) y el total de eventos creados.

## Mapa de memoria (Ejercicio 4)

Ver `mapa_memoria_ejercicio4.png`. Representa el estado de la memoria luego de ejecutar `App.main()`
con el escenario: 3 estudiantes, 1 evento, 1 sala, 2 actividades (una Charla y un Taller) y
2 inscripciones por actividad.

- Flechas **azules**: referencias desde las variables locales de `main()` hacia los objetos del heap.
- Flechas **verdes**: asociación entre cada `Inscripcion` y el `Estudiante`/`Actividad` correspondiente.
- Flechas **rojas** (rombo sólido ♦): **composición** — `EventoUniversitario` con sus `Actividad`es, y cada `Actividad` con sus `Inscripcion`es (no existen fuera de su "todo").
- Flecha **naranja punteada** (rombo hueco ♦): **agregación** — `EventoUniversitario` con su `Sala` (la sala existe independientemente del evento).
- Las cajas de `Charla` y `Taller` muestran divididos la parte **heredada de `Actividad`** y la parte **específica** de cada subclase (herencia).

## Capturas

Ver `captura_consola_referencia.png` (o reemplazar por una captura propia de la ejecución en IntelliJ, como pide la consigna).
