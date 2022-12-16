package aad.cafeteriagoya.adapter

import aad.cafeteriagoya.R
import aad.cafeteriagoya.entidades.Producto
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import java.io.File

class CarritoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
{
    var textViewProducto = itemView.findViewById<TextView>(R.id.nombreProducto)
    var textViewPrecio = itemView.findViewById<TextView>(R.id.costeProducto)
    var button = itemView.findViewById<Button>(R.id.buttonEliminar)

    fun render(producto: Producto, onClickListener: (Producto) -> Unit)
    {
        textViewProducto.text = producto.nombre
        textViewPrecio.text = producto.precio.toString() + "€"

        button.setOnClickListener {
            onClickListener(producto)
        }
    }
}