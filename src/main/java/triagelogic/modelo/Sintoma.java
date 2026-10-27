package triagelogic.modelo;

public enum Sintoma {
    DIFICULTAD_RESPIRATORIA("Dificultad respiratoria"),
    SATURACION_BAJA("Saturacion de oxigeno baja"),
    DOLOR_TORACICO("Dolor toracico"),
    INCONSCIENTE("Inconsciente"),
    CONVULSIONES("Convulsiones"),
    SANGRADO_ABUNDANTE("Sangrado abundante"),
    FIEBRE_ALTA("Fiebre alta"),
    DOLOR_INTENSO("Dolor intenso");

    private final String etiqueta;

    Sintoma(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public String etiqueta() {
        return etiqueta;
    }
}
