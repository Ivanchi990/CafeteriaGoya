package aad.cafeteriagoya.adapter
import aad.cafeteriagoya.entidades.Producto
import android.content.Context
import aad.cafeteriagoya.R
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CarritoAdapter(private val onClickListener: (Producto) -> Unit): RecyclerView.Adapter<CarritoViewHolder>()
{
    private lateinit var context: Context
    lateinit var carrito: ArrayList<Producto>

    fun CarritoAdapter(context: Context, carrito: ArrayList<Producto>)
    {
        this.context = context
        this.carrito = carrito
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarritoViewHolder
    {
        val layoutInflater = LayoutInflater.from(parent.context)

        return CarritoViewHolder(layoutInflater.inflate(R.layout.item_carrito, parent, false))
    }


    override fun onBindViewHolder(holder: CarritoViewHolder, position: Int)
    {
        holder.render(carrito[position], onClickListener)
    }


    override fun getItemCount(): Int
    {
        return carrito.size
    }
}