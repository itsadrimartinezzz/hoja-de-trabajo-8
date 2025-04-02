package uvg.edu.gt;

/**
 * Clase que representa a un paciente en la sala de emergencias.
 * Implementa Comparable para determinar la prioridad basada en el código de emergencia.
 */
public class Paciente implements Comparable<Paciente> {
    private String nombre;
    private String sintoma;
    private char codigoEmergencia;

    /**
     * Constructor para un nuevo paciente.
     * @param nombre Nombre del paciente.
     * @param sintoma Descripción del síntoma.
     * @param codigoEmergencia Código de emergencia (A-E).
     */
    public Paciente(String nombre, String sintoma, char codigoEmergencia) {
        this.nombre = nombre;
        this.sintoma = sintoma;
        this.codigoEmergencia = Character.toUpperCase(codigoEmergencia);
    }

    /**
     * Obtiene el nombre del paciente.
     * @return El nombre del paciente.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene la descripción del síntoma.
     * @return La descripción del síntoma.
     */
    public String getSintoma() {
        return sintoma;
    }

    /**
     * Obtiene el código de emergencia.
     * @return El código de emergencia.
     */
    public char getCodigoEmergencia() {
        return codigoEmergencia;
    }

    /**
     * Compara este paciente con otro basado en el código de emergencia.
     * La prioridad A es la más alta y E la más baja.
     * @param otro El otro paciente a comparar.
     * @return Un valor negativo si este paciente tiene mayor prioridad,
     *         un valor positivo si tiene menor prioridad,
     *         o cero si tienen la misma prioridad.
     */
    @Override
    public int compareTo(Paciente otro) {
        // Código A tiene más prioridad que B, B más que C, etc.
        return this.codigoEmergencia - otro.codigoEmergencia;
    }

    /**
     * Devuelve una representación en cadena del paciente.
     * @return Una cadena con el nombre, síntoma y código de emergencia.
     */
    @Override
    public String toString() {
        return nombre + ", " + sintoma + ", " + codigoEmergencia;
    }
}