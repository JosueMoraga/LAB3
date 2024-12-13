import kotlin.system.measureTimeMillis

fun main() {
    while (true) {
        println("\nSeleccione una opción:")
        println("1. Ordenar una lista usando Bubble Sort")
        println("2. Ordenar una lista usando Quick Sort")
        println("3. Calcular el factorial de un número")
        println("4. Resolver las Torres de Hanoi")
        println("5. Salir")

        when (readLine()?.toIntOrNull()) {
            1 -> ejecutarBubbleSort()
            2 -> ejecutarQuickSort()
            3 -> ejecutarCalculoFactorial()
            4 -> ejecutarTorresDeHanoi()
            5 -> {
                println("Saliendo del programa...")
                break
            }
            else -> println("Opción no válida. Por favor, intente de nuevo.")
        }
    }
}

// 1. Ordenar usando Bubble Sort
fun ejecutarBubbleSort() {
    println("Ingrese una lista de números separados por comas (ejemplo: 8,3,5,1,9):")
    val listaNumeros = readLine()?.split(",")?.mapNotNull { it.trim().toIntOrNull() } ?: return println("Entrada no válida.")
    val tiempoDeEjecucion = measureTimeMillis {
        val listaOrdenada = bubbleSort(listaNumeros.toMutableList())
        println("Lista ordenada usando Bubble Sort: $listaOrdenada")
    }
    println("Tiempo de ejecución: ${tiempoDeEjecucion}ms")
}

fun bubbleSort(numeros: MutableList<Int>): List<Int> {
    // Recorremos la lista varias veces, comparando y ordenando elementos adyacentes
    for (indiceExterno in 0 until numeros.size - 1) {
        for (indiceInterno in 0 until numeros.size - 1 - indiceExterno) {
            if (numeros[indiceInterno] > numeros[indiceInterno + 1]) {
                val temporal = numeros[indiceInterno]
                numeros[indiceInterno] = numeros[indiceInterno + 1]
                numeros[indiceInterno + 1] = temporal
            }
        }
    }
    return numeros
}

// 2. Ordenar usando Quick Sort
fun ejecutarQuickSort() {
    println("Ingrese una lista de números separados por comas (ejemplo: 8,3,5,1,9):")
    val listaNumeros = readLine()?.split(",")?.mapNotNull { it.trim().toIntOrNull() } ?: return println("Entrada no válida.")
    val tiempoDeEjecucion = measureTimeMillis {
        val listaOrdenada = quickSort(listaNumeros)
        println("Lista ordenada usando Quick Sort: $listaOrdenada")
    }
    println("Tiempo de ejecución: ${tiempoDeEjecucion}ms")
}

fun quickSort(numeros: List<Int>): List<Int> {
    // Si la lista tiene 1 o 0 elementos, ya está ordenada
    if (numeros.size <= 1) return numeros

    val pivote = numeros[numeros.size / 2]
    val menores = numeros.filter { it < pivote }
    val iguales = numeros.filter { it == pivote }
    val mayores = numeros.filter { it > pivote }

    // Ordenamos recursivamente las sublistas y las combinamos
    return quickSort(menores) + iguales + quickSort(mayores)
}

// 3. Calcular el factorial de un número
fun ejecutarCalculoFactorial() {
    println("Ingrese un número entero positivo:")
    val numeroIngresado = readLine()?.toIntOrNull()
    if (numeroIngresado == null || numeroIngresado < 0) {
        println("Por favor, ingrese un número válido.")
        return
    }
    val resultadoFactorial = calcularFactorial(numeroIngresado)
    println("El factorial de $numeroIngresado es: $resultadoFactorial")
}

fun calcularFactorial(numero: Int): Long {
    // Caso base: factorial de 0 es 1; caso recursivo
    return if (numero == 0) 1 else numero * calcularFactorial(numero - 1)
}

// 4. Resolver las Torres de Hanoi
fun ejecutarTorresDeHanoi() {
    println("Ingrese el número de discos:")
    val cantidadDiscos = readLine()?.toIntOrNull()
    if (cantidadDiscos == null || cantidadDiscos <= 0) {
        println("Por favor, ingrese un número válido.")
        return
    }
    println("Resolviendo Torres de Hanoi con $cantidadDiscos discos:")
    resolverHanoi(cantidadDiscos, "Torre A", "Torre C", "Torre B")
}

fun resolverHanoi(discos: Int, torreOrigen: String, torreDestino: String, torreAuxiliar: String) {
    // Caso base: mover un disco
    if (discos == 1) {
        println("Mover disco 1 de $torreOrigen a $torreDestino")
    } else {
        // Paso recursivo: resolver para discos - 1 y mover el disco más grande
        resolverHanoi(discos - 1, torreOrigen, torreAuxiliar, torreDestino)
        println("Mover disco $discos de $torreOrigen a $torreDestino")
        resolverHanoi(discos - 1, torreAuxiliar, torreDestino, torreOrigen)
    }
}
