package com.ecodisonante.cookinpal.model

/**
 * Clase modelo de Menu
 */
data class Menu(
    val nombre: String,
    val ingredientes: List<Ingrediente>,
    val pasos: List<Paso>
)

/**
 * Clase modelo de Ingredientes
 */
data class Ingrediente(
    val nombre: String
)

/**
 * Clase modelo de Pasos de Preparacion
 */
data class Paso(
    val orden: Int,
    val proceso: String
)


class MenuDataProvider {
    companion object {

        val menuList = mutableListOf(

            Menu(
                "Cervecita",
                listOf(
                    Ingrediente("Cerveza"),
                ),
                listOf(
                    Paso(1, "Abrir una cerveza"),
                    Paso(2, "Echarla en un vaso (opcional)"),
                    Paso(3, "Disfrutar la vida"),
                    Paso(4, "Repetir cuantas veces sea necesario"),
                )
            ),

            Menu(
                "Piscola",
                listOf(
                    Ingrediente("Hielo"),
                    Ingrediente("Coca Cola Zero"),
                    Ingrediente("Pisco"),
                ),
                listOf(
                    Paso(1, "Llenar el vaso con hielo"),
                    Paso(2, "Rellenar con Pisco hasta la mitad del vaso"),
                    Paso(3, "Rellenar el resto con bebida"),
                    Paso(4, "Apagar el teléfono"),
                )
            ),

            Menu(
                "Terremoto",
                listOf(
                    Ingrediente("Pipeño"),
                    Ingrediente("Helado de Piña"),
                    Ingrediente("Granadina"),
                    Ingrediente("Pisco"),
                ),
                listOf(
                    Paso(1, "Mezclar los ingredientes en un jarro"),
                    Paso(2, "No importan las cantidades de nada"),
                    Paso(3, "Servir en un vaso grande"),
                    Paso(4, "No tener nada que hacer al otro día"),
                )
            ),

            Menu(
                "Navegao",
                listOf(
                    Ingrediente("Vino tinto en caja"),
                    Ingrediente("Naranja"),
                    Ingrediente("Canela"),
                    Ingrediente("Clavo de Olor"),
                ),
                listOf(
                    Paso(1, "Echar el vino en una olla"),
                    Paso(2, "Agregar la naranja en rodajas y los otros condimentos"),
                    Paso(3, "Calentar hasta antes de hervir"),
                    Paso(4, "Servir en un tazón"),
                    Paso(4, "Acompañar con sopaipillas con pebre"),
                )
            ),

            Menu(
                "Colemono",
                listOf(
                    Ingrediente("Aguardiente"),
                    Ingrediente("Cafe"),
                    Ingrediente("Leche"),
                    Ingrediente("Canela"),
                    Ingrediente("Clavo de Olor"),
                ),
                listOf(
                    Paso(1, "Hervir la leche con el cafe y las especies"),
                    Paso(2, "Dejar enfriar y agregar aguardiente"),
                    Paso(3, "Servir bien helado y con hielo"),
                    Paso(4, "Asegurarse de tener un baño cerca"),
                )
            ),

        )
    }
}