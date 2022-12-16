package aad.cafeteriagoya

import aad.cafeteriagoya.databinding.ActivityFragmentBinding
import aad.cafeteriagoya.fragments.CarritoFragment
import aad.cafeteriagoya.fragments.MenuFragment
import aad.cafeteriagoya.fragments.ProductViewModel
import aad.cafeteriagoya.sqlite.MiBDOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer

class FragmentActivity : AppCompatActivity()
{
    private var binding: ActivityFragmentBinding? = null
    private val productViewModel: ProductViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityFragmentBinding.inflate(layoutInflater)

        var hora = intent.getStringExtra("hora").toString()

        productViewModel.setHora(hora)
        productViewModel.setContext(this)

        val base = MiBDOpenHelper(this, null)

        productViewModel.setDatabase(base)

        val nameObserver = Observer<String>{ valor ->
            binding!!.mostrarTotal?.setText(valor.toString())
        }

        productViewModel.getPrecio().observe(this, nameObserver)

        productViewModel.setPrecio()

        binding?.btnCarrito?.setOnClickListener{
            mostrarCarrito()
        }

        binding?.btnMenu?.isVisible = false

        binding?.btnMenu?.setOnClickListener{
            mostrarMenu()
        }

        setContentView(binding!!.root)
    }


    fun mostrarCarrito()
    {
        val transaction = supportFragmentManager.beginTransaction()
        val fragmento = CarritoFragment()

        transaction.replace(R.id.fragmentContainerView, fragmento)
        transaction.addToBackStack(null)

        binding?.btnCarrito?.isVisible = false
        binding?.btnMenu?.isVisible = true

        transaction.commit()
    }


    fun mostrarMenu()
    {
        val transaction = supportFragmentManager.beginTransaction()
        val fragmento = MenuFragment()

        transaction.replace(R.id.fragmentContainerView, fragmento)
        transaction.addToBackStack(null)

        binding?.btnCarrito?.isVisible = true
        binding?.btnMenu?.isVisible = false

        transaction.commit()
    }
}