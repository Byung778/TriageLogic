package triagelogic.modelo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static triagelogic.modelo.Proposicion.de;

import java.util.EnumSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

class TriajeTest {

    @Test
    void sinSintomasNoEsUrgente() {
        assertFalse(Triaje.evaluar(EnumSet.noneOf(Sintoma.class)).urgente());
    }

    @Test
    void conjuncionExigeAmbosSintomas() {
        assertFalse(Triaje.evaluar(EnumSet.of(Sintoma.FIEBRE_ALTA)).urgente());
        assertTrue(Triaje.evaluar(EnumSet.of(Sintoma.FIEBRE_ALTA, Sintoma.DOLOR_INTENSO)).urgente());
    }

    @Test
    void disyuncionBastaUnSintoma() {
        assertTrue(Triaje.evaluar(EnumSet.of(Sintoma.CONVULSIONES)).urgente());
    }

    @Test
    void distributivaYTerceroExcluidoSimplificanLaRegla() {
        Proposicion p = de(Sintoma.DOLOR_TORACICO);
        Proposicion q = de(Sintoma.CONVULSIONES);
        Proposicion original = p.y(q).o(p.y(q.negar()));
        for (boolean conP : new boolean[] {true, false}) {
            for (boolean conQ : new boolean[] {true, false}) {
                Set<Sintoma> presentes = EnumSet.noneOf(Sintoma.class);
                if (conP) {
                    presentes.add(Sintoma.DOLOR_TORACICO);
                }
                if (conQ) {
                    presentes.add(Sintoma.CONVULSIONES);
                }
                assertEquals(p.evaluar(presentes), original.evaluar(presentes));
            }
        }
    }
}
