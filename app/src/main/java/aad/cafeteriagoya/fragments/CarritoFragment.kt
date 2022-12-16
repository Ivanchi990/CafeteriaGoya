package aad.cafeteriagoya.fragments

import aad.cafeteriagoya.MainActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import aad.cafeteriagoya.R
import aad.cafeteriagoya.adapter.CarritoAdapter
import aad.cafeteriagoya.adapter.MenuAdaptador
import aad.cafeteriagoya.databinding.FragmentCarritoBinding
import aad.cafeteriagoya.databinding.FragmentMenuBinding
import aad.cafeteriagoya.entidades.Producto
import android.content.Intent
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager

class CarritoFragment : Fragment()
{
    var binding: FragmentCarritoBinding? = null
    val productViewModel: ProductViewModel by activityViewModels()
    lateinit var adapterCarrito: CarritoAdapter
    var lista = ArrayList<Producto>()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val frag = FragmentCarritoBinding.inflate(inflater, container, false)
        binding = frag

        iniciarRecyclerCarrito()

        binding?.fabHome?.setOnClickListener()
        {
            volverCasa()
        }

        return frag.root
    }


    fun iniciarRecyclerCarrito()
    {
        val recyclerView = binding?.recylerCarrito
        recyclerView?.layoutManager = LinearLayoutManager(productViewModel.getContext())

        adapterCarrito = CarritoAdapter(
            onClickListener = { pos -> dameID(pos) }
        )

        lista = dameCarrito()

        adapterCarrito.CarritoAdapter(productViewModel.getContext(),  lista)

        recyclerView?.adapter = adapterCarrito
    }

    fun dameCarrito(): ArrayList<Producto>
    {
        var lista = ArrayList<Producto>()

        val base = productViewModel?.getDatabase()

        val carro = base?.obtenerCarrito()

        while(carro!!.moveToNext())
        {
            lista.add(Producto(carro.getInt(1), carro.getString(2), carro.getDouble(3), carro.getString(4)))
        }

        return lista
    }


    fun volverCasa()
    {
        var intent = Intent(productViewModel.getContext(), MainActivity:: class.java)

        startActivity(intent)
    }

    fun dameID(pos: Int)
    {
        var base = productViewModel.getDatabase()

        base.eliminarProducto(pos)

        lista = dameCarrito()

        adapterCarrito.carrito = lista

        adapterCarrito.notifyDataSetChanged()
    }
}