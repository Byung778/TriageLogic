package triagelogic.modelo;

import java.util.Set;

public record Regla(String nombre, Proposicion condicion) {

    public boolean seActiva(Set<Sintoma> presentes) {
        return condicion.evaluar(presentes);
    }

    @Override
    public String toString() {
        return nombre + ": si " + condicion + " entonces urgente";
    }
}
