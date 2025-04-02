package uvg.edu.gt;

import java.util.Vector;

/**
 * Implementación de una cola con prioridad usando un heap basado en un vector.
 * @param <E> El tipo de elemento en la cola, debe implementar Comparable.
 */
public class VectorHeap<E extends Comparable<E>> implements PriorityQueue<E> {

    protected Vector<E> data; // El vector que contiene los elementos

    /**
     * Constructor que crea un heap vacío.
     */
    public VectorHeap() {
        data = new Vector<E>();
    }

    /**
     * Constructor que crea un heap a partir de un vector de datos.
     * @param v El vector con los datos iniciales.
     */
    public VectorHeap(Vector<E> v) {
        data = new Vector<E>(v.size()); // Crea un vector con la misma capacidad
        for (E element : v) {
            add(element); // Agrega cada elemento
        }
    }

    /**
     * Devuelve el índice del padre de un nodo.
     * @param i Índice del nodo.
     * @return Índice del padre.
     */
    protected int parent(int i) {
        return (i - 1) / 2;
    }

    /**
     * Devuelve el índice del hijo izquierdo de un nodo.
     * @param i Índice del nodo.
     * @return Índice del hijo izquierdo.
     */
    protected int left(int i) {
        return 2 * i + 1;
    }

    /**
     * Devuelve el índice del hijo derecho de un nodo.
     * @param i Índice del nodo.
     * @return Índice del hijo derecho.
     */
    protected int right(int i) {
        return 2 * i + 2;
    }

    /**
     * Mueve un elemento hacia arriba hasta encontrar su posición correcta.
     * @param leaf Índice del elemento a mover.
     */
    protected void percolateUp(int leaf) {
        int parent = parent(leaf);
        E value = data.get(leaf);

        // Mientras no lleguemos a la raíz y el valor sea de mayor prioridad que el padre
        while (leaf > 0 && value.compareTo(data.get(parent)) < 0) {
            data.set(leaf, data.get(parent)); // Mueve el padre hacia abajo
            leaf = parent;
            parent = parent(leaf);
        }

        data.set(leaf, value); // Coloca el valor en su posición final
    }

    /**
     * Inserta un elemento en el heap.
     * @param value El elemento a insertar.
     */
    @Override
    public void add(E value) {
        data.add(value); // Agrega al final
        percolateUp(data.size() - 1); // Reordena hacia arriba
    }

    /**
     * Mueve un elemento hacia abajo hasta encontrar su posición correcta.
     * @param root Índice del elemento a mover.
     */
    protected void pushDownRoot(int root) {
        int heapSize = data.size();
        E value = data.get(root);

        while (root < heapSize) {
            int childpos = left(root);

            // Si no tiene hijo izquierdo, termina
            if (childpos >= heapSize) {
                break;
            }

            // Si tiene hijo derecho y éste tiene mayor prioridad que el izquierdo
            if (right(root) < heapSize &&
                    data.get(childpos + 1).compareTo(data.get(childpos)) < 0) {
                childpos++; // Selecciona el hijo derecho
            }

            // Si el valor actual tiene mayor o igual prioridad que el hijo de mayor prioridad
            if (value.compareTo(data.get(childpos)) <= 0) {
                break; // Terminamos
            }

            // Movemos el hijo hacia arriba
            data.set(root, data.get(childpos));
            root = childpos; // Continuamos con el hijo
        }

        data.set(root, value); // Colocamos el valor en su posición final
    }

    /**
     * Remueve y devuelve el elemento con mayor prioridad.
     * @return El elemento con mayor prioridad.
     * @throws RuntimeException si el heap está vacío.
     */
    @Override
    public E remove() {
        if (isEmpty()) {
            throw new RuntimeException("La cola de prioridad está vacía");
        }

        E minVal = data.get(0); // Elemento de mayor prioridad
        data.set(0, data.get(data.size() - 1)); // Mueve el último elemento a la raíz
        data.setSize(data.size() - 1); // Reduce el tamaño

        if (data.size() > 0) {
            pushDownRoot(0); // Reordena la raíz hacia abajo
        }

        return minVal;
    }

    /**
     * Devuelve el elemento con mayor prioridad sin removerlo.
     * @return El elemento con mayor prioridad.
     * @throws RuntimeException si el heap está vacío.
     */
    @Override
    public E getFirst() {
        if (isEmpty()) {
            throw new RuntimeException("La cola de prioridad está vacía");
        }
        return data.get(0);
    }

    /**
     * Verifica si el heap está vacío.
     * @return true si el heap está vacío, false de lo contrario.
     */
    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    /**
     * Devuelve el tamaño del heap.
     * @return El número de elementos en el heap.
     */
    @Override
    public int size() {
        return data.size();
    }

    /**
     * Elimina todos los elementos del heap.
     */
    @Override
    public void clear() {
        data.clear();
    }
}