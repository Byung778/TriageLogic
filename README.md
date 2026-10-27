# TriageLogic

Aplicación en Java que indica si un paciente es **urgente** o **no urgente** a partir de sus síntomas, aplicando lógica proposicional. Proyecto de Modelos Discretos.

## Objetivo

Desarrollar una aplicación en Java que determine si un paciente es urgente o no urgente a partir de sus síntomas, aplicando lógica proposicional.

## Temas aplicados

- **Proposiciones (1.1.2):** cada síntoma es una proposición, combinada con los conectivos Y, O y NO.
- **Implicación lógica (1.1.4):** cada regla tiene la forma "si condición, entonces urgente" y se aplica modus ponens.
- **Equivalencia lógica (1.1.3):** dos reglas de dolor torácico se simplificaron en una sola, comprobado con una prueba automática.

## Reglas de urgencia

| Regla | Condición |
|---|---|
| R1 | Dificultad respiratoria Y saturación de oxígeno baja |
| R2 | Dolor torácico |
| R3 | Inconsciente |
| R4 | Convulsiones O sangrado abundante |
| R5 | Fiebre alta Y dolor intenso |

Si se activa al menos una regla, el resultado es URGENTE. Si no se activa ninguna, es NO URGENTE.

## Cómo ejecutarlo

Requisitos: JDK 17 o superior y Maven (NetBeans ya incluye Maven).

1. En Apache NetBeans: File → Open Project y elegir la carpeta del proyecto.
2. Clic derecho sobre el proyecto → Run. Clase principal: `triagelogic.Main`.

Pruebas automáticas (JUnit 5): `mvn test`

## Estructura
