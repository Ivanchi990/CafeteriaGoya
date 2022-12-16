package aad.cafeteriagoya.fragments

import aad.cafeteriagoya.entidades.Producto
import aad.cafeteriagoya.sqlite.MiBDOpenHelper
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProductViewModel: ViewModel()
{
    private var totalPrecio: MutableLiveData<String> = MutableLiveData<String>()
    private var base: MiBDOpenHelper? = null
    var precioTotal = 0.0
    var horaPedido = ""
    lateinit var contexto: Context
    var carrito = ArrayList<Producto>()

    init
    {
        totalPrecio.setValue("Precio total: $precioTotal€")
    }

    fun setDatabase(database: MiBDOpenHelper)
    {
        base = database
    }

    fun getDatabase(): MiBDOpenHelper
    {
        return base!!
    }

    fun setPrecio()
    {
        precioTotal = 0.0

       for(p in carrito)
       {
           precioTotal += p.precio
       }

        totalPrecio.value = "Total: $precioTotal€"
    }

    fun getPrecio(): MutableLiveData<String>
    {
        return totalPrecio
    }

    fun setHora(hora: String)
    {
        horaPedido = hora
    }

    fun getHora(): String
    {
        return horaPedido
    }

    fun setContext(contexto: Context)
    {
        this.contexto = contexto
    }

    fun getContext(): Context
    {
        return contexto
    }
}