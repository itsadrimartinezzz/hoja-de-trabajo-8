# Sistema de Atención de Emergencias Hospitalarias

Este proyecto implementa un sistema para la gestión de pacientes en la sección de Emergencias de un hospital. El sistema organiza a los pacientes según su código de prioridad y permite atenderlos en el orden correcto.

## Descripción

El sistema gestiona pacientes que llegan a la sección de Emergencias, donde cada paciente tiene:

- Nombre del paciente
- Descripción del síntoma
- Código de emergencia (A-E, donde A es la máxima prioridad y E la mínima)

El sistema utiliza una cola de prioridad (Priority Queue) para asegurar que los pacientes sean atendidos según la urgencia de su condición, independientemente del orden de llegada.

## Estructura del proyecto

```
HT8
├── src
│   ├── main
│   │   └── java
│   │       └── uvg
│   │           └── edu
│   │               └── gt
│   │                   ├── App.java             # Aplicación principal con VectorHeap
│   │                   ├── AppJCF.java          # Versión alternativa con java.util.PriorityQueue
│   │                   ├── Paciente.java        # Clase que representa a un paciente
│   │                   ├── PriorityQueue.java   # Interfaz para la cola de prioridad
│   │                   └── VectorHeap.java      # Implementación de cola prioridad con Vector
│   └── test
│       └── java
│           └── uvg
│               └── edu
│                   └── gt
│                       └── AppTest.java         # Pruebas unitarias
├── pacientes.txt                               # Archivo de ingreso de pacientes
└── pom.xml                                     # Configuración de Maven
```

## Funcionalidades

1. **Carga de pacientes**: Lee automáticamente los pacientes desde `pacientes.txt`.
2. **Atención por prioridad**: Extrae pacientes según su código de emergencia (A, B, C, D, E).
3. **Consulta del siguiente paciente**: Permite ver quién es el siguiente sin atenderlo.
4. **Conteo de pacientes**: Muestra cuántos pacientes quedan en espera.

## Funcionamiento y flujo de trabajo

### 1. Inicio del sistema

Al ejecutar la aplicación:
1. Se crea una cola de prioridad vacía
2. Se cargan los pacientes desde `pacientes.txt`
3. Se muestra un menú de opciones

### 2. Formato del archivo de entrada

El archivo `pacientes.txt` debe tener el siguiente formato:
```
Nombre Apellido, descripción del síntoma, X
```
Donde `X` es un código de emergencia entre A y E.

Ejemplo:
```
Juan Perez, fractura de pierna, C
Maria Ramirez, apendicitis, A
Lorenzo Toledo, chikunguya, E
Carmen Sarmientos, dolores de parto, B
```

### 3. Atención de pacientes

Los pacientes se atienden en estricto orden de prioridad:
- Primero todos los pacientes con código A
- Luego todos los pacientes con código B
- Y así sucesivamente

Para el ejemplo anterior, el orden de atención sería:
1. Maria Ramirez (A)
2. Carmen Sarmientos (B)
3. Juan Perez (C)
4. Lorenzo Toledo (E)

### 4. Implementaciones disponibles

El proyecto ofrece dos implementaciones:

1. **Implementación propia (VectorHeap)**: Utiliza una implementación personalizada de heap basada en vectores.
    - Para ejecutar: `mvn exec:java`

2. **Implementación con Java Collections Framework**: Utiliza `java.util.PriorityQueue`.
    - Para ejecutar: `mvn exec:java -Dexec.mainClass="uvg.edu.gt.AppJCF"`

## Requisitos

- Java 21
- Maven 3.6 o superior

## Compilación y ejecución

```bash
# Compilar el proyecto
mvn compile

# Ejecutar pruebas
mvn test

# Ejecutar la aplicación (implementación propia)
mvn exec:java

# Ejecutar la aplicación (implementación JCF)
mvn exec:java -Dexec.mainClass="uvg.edu.gt.AppJCF"
```

## Detalles

### Clase Paciente

Representa a un paciente en el sistema. Implementa `Comparable<Paciente>` para permitir la comparación de prioridades.

### VectorHeap

Implementación personalizada de un heap utilizando un vector como estructura subyacente. Esta implementación mantiene la propiedad de heap donde:
- El elemento con mayor prioridad está siempre en la raíz
- Cada padre tiene mayor prioridad que sus hijos

### Operaciones principales

- **add**: Añade un paciente a la cola (O(log n))
- **remove**: Atiende al paciente con mayor prioridad (O(log n))
- **getFirst**: Consulta el siguiente paciente sin atenderlo (O(1))