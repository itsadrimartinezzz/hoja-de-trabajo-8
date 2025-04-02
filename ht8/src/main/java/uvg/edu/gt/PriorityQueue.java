package uvg.edu.gt;

/**
 * Interfaz para una cola con prioridad.
 * @param <E> El tipo de elemento en la cola, debe implementar Comparable.
 */
public interface PriorityQueue<E extends Comparable<E>> {

    /**
     * Inserta un elemento en la cola con prioridad.
     * @param value El elemento a insertar.
     */
    void add(E value);

    /**
     * Remueve y devuelve el elemento con mayor prioridad.
     * @return El elemento con mayor prioridad.
     */
    E remove();

    /**
     * Devuelve el elemento con mayor prioridad sin removerlo.
     * @return El elemento con mayor prioridad.
     */
    E getFirst();

    /**
     * Verifica si la cola está vacía.
     * @return true si la cola está vacía, false de lo contrario.
     */
    boolean isEmpty();

    /**
     * Devuelve el tamaño de la cola.
     * @return El número de elementos en la cola.
     */
    int size();

    /**
     * Elimina todos los elementos de la cola.
     */
    void clear();
}