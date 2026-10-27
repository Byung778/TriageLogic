# TriageLogic

Aplicación en Java que indica si un paciente es **urgente** o **no urgente** a partir de sus síntomas, aplicando lógica proposicional.

Proyecto de la materia **Modelos Discretos** (Ingeniería).

## Autoras

- Josselyn Ormaza
- Nohelia Machay

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

## Cómo funciona

1. El usuario marca los síntomas del paciente en la interfaz.
2. Cada síntoma marcado vale verdadero y cada síntoma sin marcar vale falso.
3. El sistema evalúa las cinco reglas con esos valores.
4. Si alguna condición es verdadera, por modus ponens el paciente es urgente y se muestran las reglas activadas.

## Cómo ejecutarlo

Requisitos: JDK 17 o superior y Maven (NetBeans ya incluye Maven).

1. En Apache NetBeans: File → Open Project y elegir la carpeta del proyecto.
2. Clic derecho sobre el proyecto → Run. Clase principal: `triagelogic.Main`.

Pruebas automáticas (JUnit 5): `mvn test`

## Estructura

```
src/main/java/triagelogic/
├── Main.java
├── modelo/
│   ├── Sintoma.java       síntomas del sistema
│   ├── Proposicion.java   proposiciones y conectivos
│   ├── Regla.java         regla: condición y conclusión
│   └── Triaje.java        reglas y evaluación
└── ui/
    └── Ventana.java       interfaz gráfica
src/test/java/triagelogic/modelo/
└── TriajeTest.java        pruebas automáticas
```

## Limitaciones

- Las reglas son simplificadas y de carácter educativo. El sistema no diagnostica ni sustituye el criterio del personal de salud.
- Un síntoma sin marcar se toma como falso.
