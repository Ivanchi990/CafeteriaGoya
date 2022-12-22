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
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager

class CarritoFragment : Fragment()
{
    var binding: FragmentCarritoBinding? = null
    val productViewModel: ProductViewModel by activityViewModels()
    lateinit var adapterCarrito: CarritoAdapter

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
            pagar()
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

        adapterCarrito.CarritoAdapter(productViewModel.getContext(),  productViewModel.carrito)

        recyclerView?.adapter = adapterCarrito
    }


    fun pagar()
    {
        var pro = ""

        for(p in productViewModel.carrito)
        {
            pro = pro + p.id + "-"
        }

        pro = pro.substring(pro.length-1)

        var base = productViewModel.getDatabase()

        if(productViewModel.carrito.size  > 0)
        {
            base.andirProducto(pro)

            Toast.makeText(productViewModel.getContext(), "El pago se ha procesado correctamente.", Toast.LENGTH_SHORT).show()
        }

        var intent = Intent(productViewModel.getContext(), MainActivity:: class.java)

        startActivity(intent)
    }

    fun dameID(pos: Producto)
    {
        productViewModel.carrito.remove(pos)

        productViewModel.setPrecio()

        iniciarRecyclerCarrito()
    }
}