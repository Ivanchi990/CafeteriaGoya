package aad.cafeteriagoya

import aad.cafeteriagoya.databinding.ActivityVerPedidoBinding
import aad.cafeteriagoya.provider.DataProvider
import aad.cafeteriagoya.sqlite.MiBDOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class VerPedido : AppCompatActivity()
{
    var binding: ActivityVerPedidoBinding? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityVerPedidoBinding.inflate(layoutInflater)

        var id = intent.getIntExtra("id", -1)

        mostrarPedido(id)

        setContentView(binding!!.root)
    }


    fun mostrarPedido(id:Int)
    {
        var base = MiBDOpenHelper(this, null)

        var cursor = base.obtenerPedido(id)

        cursor.moveToFirst()

        var fin = cursor.getInt(0)
        var con = cursor.getString(1)

        var content = con.split("-")

        var p = ""

        for(c in content)
        {
            p = p + DataProvider.listaProductos.get(c.toInt())
        }

        binding!!.idPedido.text = id.toString()
        binding!!.contenidoPedido.text = p
    }
}