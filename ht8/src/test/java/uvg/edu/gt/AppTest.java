package uvg.edu.gt;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 * Pruebas unitarias para VectorHeap y Paciente utilizando JUnit 4.
 */
public class AppTest {

    private VectorHeap<Paciente> heap;
    private Paciente p1, p2, p3, p4, p5;

    /**
     * Configura las pruebas inicializando las variables.
     */
    @Before
    public void setUp() {
        heap = new VectorHeap<>();

        // Crear pacientes con diferentes prioridades
        p1 = new Paciente("Juan", "Fractura", 'C');
        p2 = new Paciente("Maria", "Apendicitis", 'A');
        p3 = new Paciente("Pedro", "Gripe", 'E');
        p4 = new Paciente("Carmen", "Parto", 'B');
        p5 = new Paciente("Lorenzo", "Dolor", 'C');
    }

    /**
     * Prueba la inserción y extracción de elementos en VectorHeap.
     */
    @Test
    public void testVectorHeapInsertAndRemove() {
        // Verificar que inicia vacío
        assertTrue("El heap debería iniciar vacío", heap.isEmpty());
        assertEquals("El tamaño inicial del heap debería ser 0", 0, heap.size());

        // Insertar pacientes
        heap.add(p1);
        heap.add(p2);
        heap.add(p3);
        heap.add(p4);

        // Verificar tamaño
        assertFalse("El heap no debería estar vacío después de agregar elementos", heap.isEmpty());
        assertEquals("El tamaño del heap debería ser 4", 4, heap.size());

        // Verificar que el paciente con mayor prioridad es Maria (código A)
        assertEquals("El primer paciente debería ser Maria (A)", p2, heap.getFirst());

        // Extraer pacientes y verificar el orden (A, B, C, E)
        assertEquals("El primer paciente en salir debería ser Maria (A)", p2, heap.remove());
        assertEquals("El segundo paciente en salir debería ser Carmen (B)", p4, heap.remove());
        assertEquals("El tercer paciente en salir debería ser Juan (C)", p1, heap.remove());
        assertEquals("El cuarto paciente en salir debería ser Pedro (E)", p3, heap.remove());

        // Verificar que el heap está vacío después de extraer todos los elementos
        assertTrue("El heap debería estar vacío después de remover todos los elementos", heap.isEmpty());
    }

    /**
     * Prueba la comparación de pacientes.
     */
    @Test
    public void testPacienteCompareTo() {
        // A tiene mayor prioridad que C
        assertTrue("A debería tener mayor prioridad que C", p2.compareTo(p1) < 0);

        // C tiene mayor prioridad que E
        assertTrue("C debería tener mayor prioridad que E", p1.compareTo(p3) < 0);

        // B tiene mayor prioridad que C
        assertTrue("B debería tener mayor prioridad que C", p4.compareTo(p1) < 0);

        // C y C tienen la misma prioridad
        assertEquals("Dos pacientes con código C deberían tener igual prioridad", 0, p1.compareTo(p5));
    }

    /**
     * Prueba el método clear del VectorHeap.
     */
    @Test
    public void testVectorHeapClear() {
        // Crear y agregar pacientes
        heap.add(p1);
        heap.add(p2);

        // Verificar que no está vacío
        assertFalse("El heap no debería estar vacío después de agregar elementos", heap.isEmpty());
        assertEquals("El tamaño del heap debería ser 2", 2, heap.size());

        // Limpiar y verificar que está vacío
        heap.clear();
        assertTrue("El heap debería estar vacío después de clear()", heap.isEmpty());
        assertEquals("El tamaño del heap debería ser 0 después de clear()", 0, heap.size());
    }

    /**
     * Prueba el comportamiento cuando se intenta eliminar un elemento de un heap vacío.
     */
    @Test(expected = RuntimeException.class)
    public void testRemoveFromEmptyHeap() {
        heap.remove(); // Esto debería lanzar una RuntimeException
    }

    /**
     * Prueba el comportamiento cuando se intenta obtener el primer elemento de un heap vacío.
     */
    @Test(expected = RuntimeException.class)
    public void testGetFirstFromEmptyHeap() {
        heap.getFirst(); // Esto debería lanzar una RuntimeException
    }
}