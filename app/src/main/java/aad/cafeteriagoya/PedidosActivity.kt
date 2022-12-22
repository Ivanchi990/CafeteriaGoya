package aad.cafeteriagoya

import aad.cafeteriagoya.adapter.MenuAdaptador
import aad.cafeteriagoya.adapter.PedidosAdapter
import aad.cafeteriagoya.databinding.ActivityPedidosBinding
import aad.cafeteriagoya.sqlite.MiBDOpenHelper
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager

class PedidosActivity : AppCompatActivity()
{
    var binding: ActivityPedidosBinding? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityPedidosBinding.inflate(layoutInflater)

        iniciarRecicler()

        binding?.btCasa?.setOnClickListener{
            casita()
        }

        setContentView(binding!!.root)
    }


    fun iniciarRecicler()
    {
        val recyclerView = binding?.recyclerViewp
        recyclerView?.layoutManager = LinearLayoutManager(this)

        val base = MiBDOpenHelper(this, null)

        var lista = base.obtenerCarrito()

        var adapterPedidos = PedidosAdapter(
            onClickListener = { pos -> dameID(pos) }
        )

        adapterPedidos.PedidosAdapter(this,  lista)

        recyclerView?.adapter = adapterPedidos
    }


    fun dameID(pos: Int)
    {
        intent = Intent(this, VerPedido::class.java).apply {
            putExtra("id", pos)
        }

        startActivity(intent)
    }


    fun casita()
    {
        intent = Intent(this, MainActivity::class.java)

        startActivity(intent)
    }
}