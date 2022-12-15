package aad.cafeteriagoya.provider

import aad.cafeteriagoya.entidades.Producto

class DataProvider {

companion object {
    val listaProductos = listOf<Producto>(
        Producto(1,"pincho de tortilla", 1.50, "pincho"),
        Producto(2,"bizcocho", 1.50, "pincho"),
        Producto(3,"cafe con leche", 1.10, "cafe"),
        Producto(4,"cafe cortado", 1.10, "cafe"),
        Producto(5,"cocacola", 1.00, "refresco"),
        Producto(6,"cocacola zero", 1.00, "refresco"),
        Producto(7,"bocadillo de lomo", 1.50, "bocadillo"),
        Producto(8,"medio bocadillo de lomo", 2.90, "bocadillo"),
        Producto(9,"bocadillo de jamón", 1.50, "bocadillo"),
        Producto(10,"medio bocadillo de jamón", 2.90, "bocadillo"),
        Producto(11,"bocadillo de atún y tomate", 1.50, "bocadillo"),
        Producto(12,"medio bocadillo de atún y tomate", 2.90, "bocadillo")
    )
}
}