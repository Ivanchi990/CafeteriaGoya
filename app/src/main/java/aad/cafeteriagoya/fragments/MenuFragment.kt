package aad.cafeteriagoya.fragments

import aad.cafeteriagoya.provider.DataProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import aad.cafeteriagoya.adapter.MenuAdaptador
import aad.cafeteriagoya.databinding.FragmentMenuBinding
import aad.cafeteriagoya.entidades.Producto
import aad.cafeteriagoya.sqlite.MiBDOpenHelper
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager

class MenuFragment : Fragment()
{
    var binding: FragmentMenuBinding? = null
    var lista: ArrayList<Producto> = ArrayList(DataProvider.listaProductos)
    private lateinit var adapterProductos: MenuAdaptador
    val productViewModel: ProductViewModel by activityViewModels()
    lateinit var base: MiBDOpenHelper

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val frag = FragmentMenuBinding.inflate(inflater, container, false)
        binding = frag

        base = productViewModel.getDatabase()

        rellenarSpinner()
        iniciarRecicler()

        binding?.btFiltrar?.setOnClickListener{
            filtrar()
        }

        return frag.root
    }

    fun rellenarSpinner()
    {
        val categorias = arrayOf("Todas", "pincho", "cafe", "refresco", "bocadillo")

        var adaptador: ArrayAdapter<String> = ArrayAdapter(productViewModel.getContext(), android.R.layout.simple_spinner_item, categorias)
        binding!!.spinner.adapter = adaptador
    }

    fun iniciarRecicler()
    {
        val recyclerView = binding?.recyclerView
        recyclerView?.layoutManager = LinearLayoutManager(productViewModel.getContext())

        adapterProductos = MenuAdaptador(
            onClickListener = { pos -> dameID(pos) }
        )

        adapterProductos.MenuAdaptador(productViewModel.getContext(),  lista)

        recyclerView?.adapter = adapterProductos
    }

    fun filtrar()
    {
        val categoria = binding?.spinner?.selectedItem.toString()

        val lista = ArrayList<Producto>()

        if (categoria != "Todas")
        {
            for (p in DataProvider.listaProductos)
            {
                if (p.categoria == categoria)
                {
                    lista.add(p)
                }
            }
            adapterProductos.productos = ArrayList(lista)
        }
        else
        {
            adapterProductos.productos = ArrayList(DataProvider.listaProductos)
        }

        adapterProductos.notifyDataSetChanged()
    }


    fun dameID(pos: Int)
    {
        productViewModel.carrito.add(DataProvider.listaProductos.get(pos-1))
        productViewModel.setPrecio()
    }
}