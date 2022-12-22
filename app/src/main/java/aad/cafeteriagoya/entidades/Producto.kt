package aad.cafeteriagoya.entidades

data class Producto (var id :Int,var nombre:String, var precio:Double, var categoria: String)
{
    constructor() :this(0,"",0.0,"")
}