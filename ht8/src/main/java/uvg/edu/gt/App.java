package uvg.edu.gt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Aplicación principal para el sistema de atención de pacientes.
 */
public class App {

    /**
     * Método principal que ejecuta el programa.
     * @param args Argumentos de línea de comando.
     */
    public static void main(String[] args) {
        System.out.println("Sistema de Atención de Pacientes de Emergencia");
        System.out.println("---------------------------------------------");

        // Crear la cola de prioridad con nuestra implementación
        VectorHeap<Paciente> colaPacientes = new VectorHeap<>();

        // Cargar pacientes desde el archivo
        cargarPacientes(colaPacientes);

        // Interfaz de usuario simple
        Scanner scanner = new Scanner(System.in);
        boolean ejecutando = true;

        while (ejecutando) {
            System.out.println("\nOpciones:");
            System.out.println("1. Atender al siguiente paciente");
            System.out.println("2. Ver siguiente paciente (sin atender)");
            System.out.println("3. Ver cantidad de pacientes en espera");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    atenderPaciente(colaPacientes);
                    break;
                case "2":
                    verSiguientePaciente(colaPacientes);
                    break;
                case "3":
                    System.out.println("Hay " + colaPacientes.size() + " pacientes en espera.");
                    break;
                case "4":
                    ejecutando = false;
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }

        scanner.close();
    }

    /**
     * Carga los pacientes desde un archivo de texto.
     * @param cola La cola de prioridad donde se cargarán los pacientes.
     */
    private static void cargarPacientes(PriorityQueue<Paciente> cola) {
        try (BufferedReader br = new BufferedReader(new FileReader("pacientes.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Dividir la línea por comas
                String[] partes = linea.split(",", 3);

                if (partes.length == 3) {
                    String nombre = partes[0].trim();
                    String sintoma = partes[1].trim();
                    char codigoEmergencia = partes[2].trim().charAt(0);

                    // Crear y agregar el paciente a la cola
                    Paciente paciente = new Paciente(nombre, sintoma, codigoEmergencia);
                    cola.add(paciente);
                    System.out.println("Paciente agregado: " + paciente);
                }
            }
            System.out.println("Pacientes cargados exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo 'pacientes.txt': " + e.getMessage());
            System.out.println("Asegúrese de que el archivo existe en el directorio de ejecución.");
        }
    }

    /**
     * Atiende al siguiente paciente con mayor prioridad.
     * @param cola La cola de prioridad de pacientes.
     */
    private static void atenderPaciente(PriorityQueue<Paciente> cola) {
        if (cola.isEmpty()) {
            System.out.println("No hay pacientes en espera.");
            return;
        }

        Paciente paciente = cola.remove();
        System.out.println("Atendiendo al paciente: " + paciente);
    }

    /**
     * Muestra el siguiente paciente sin atenderlo.
     * @param cola La cola de prioridad de pacientes.
     */
    private static void verSiguientePaciente(PriorityQueue<Paciente> cola) {
        if (cola.isEmpty()) {
            System.out.println("No hay pacientes en espera.");
            return;
        }

        Paciente paciente = cola.getFirst();
        System.out.println("Siguiente paciente: " + paciente);
    }
}