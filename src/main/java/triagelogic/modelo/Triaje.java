package triagelogic.modelo;

import static triagelogic.modelo.Proposicion.de;
import static triagelogic.modelo.Sintoma.CONVULSIONES;
import static triagelogic.modelo.Sintoma.DIFICULTAD_RESPIRATORIA;
import static triagelogic.modelo.Sintoma.DOLOR_INTENSO;
import static triagelogic.modelo.Sintoma.DOLOR_TORACICO;
import static triagelogic.modelo.Sintoma.FIEBRE_ALTA;
import static triagelogic.modelo.Sintoma.INCONSCIENTE;
import static triagelogic.modelo.Sintoma.SANGRADO_ABUNDANTE;
import static triagelogic.modelo.Sintoma.SATURACION_BAJA;

import java.util.List;
import java.util.Set;

public final class Triaje {

    private static final List<Regla> REGLAS = List.of(
            new Regla("R1", de(DIFICULTAD_RESPIRATORIA).y(de(SATURACION_BAJA))),
            new Regla("R2", de(DOLOR_TORACICO)),
            new Regla("R3", de(INCONSCIENTE)),
            new Regla("R4", de(CONVULSIONES).o(de(SANGRADO_ABUNDANTE))),
            new Regla("R5", de(FIEBRE_ALTA).y(de(DOLOR_INTENSO))));

    private Triaje() {
    }

    public record Resultado(List<Regla> activas) {
        public boolean urgente() {
            return !activas.isEmpty();
        }
    }

    public static Resultado evaluar(Set<Sintoma> presentes) {
        return new Resultado(REGLAS.stream().filter(regla -> regla.seActiva(presentes)).toList());
    }
}
