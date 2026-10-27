package triagelogic.modelo;

import java.util.Set;

public sealed interface Proposicion {

    boolean evaluar(Set<Sintoma> presentes);

    static Proposicion de(Sintoma sintoma) {
        return new Atomo(sintoma);
    }

    default Proposicion y(Proposicion otra) {
        return new Conjuncion(this, otra);
    }

    default Proposicion o(Proposicion otra) {
        return new Disyuncion(this, otra);
    }

    default Proposicion negar() {
        return new Negacion(this);
    }

    record Atomo(Sintoma sintoma) implements Proposicion {
        @Override
        public boolean evaluar(Set<Sintoma> presentes) {
            return presentes.contains(sintoma);
        }

        @Override
        public String toString() {
            return sintoma.etiqueta();
        }
    }

    record Negacion(Proposicion a) implements Proposicion {
        @Override
        public boolean evaluar(Set<Sintoma> presentes) {
            return !a.evaluar(presentes);
        }

        @Override
        public String toString() {
            return "NO (" + a + ")";
        }
    }

    record Conjuncion(Proposicion a, Proposicion b) implements Proposicion {
        @Override
        public boolean evaluar(Set<Sintoma> presentes) {
            return a.evaluar(presentes) && b.evaluar(presentes);
        }

        @Override
        public String toString() {
            return a + " Y " + b;
        }
    }

    record Disyuncion(Proposicion a, Proposicion b) implements Proposicion {
        @Override
        public boolean evaluar(Set<Sintoma> presentes) {
            return a.evaluar(presentes) || b.evaluar(presentes);
        }

        @Override
        public String toString() {
            return a + " O " + b;
        }
    }
}
