package aad.cafeteriagoya.adapter

import aad.cafeteriagoya.R
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PedidosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
{
    var textViewProducto = itemView.findViewById<TextView>(R.id.numeroPedido)
    var textViewPrecio = itemView.findViewById<TextView>(R.id.costePedido)
    var button = itemView.findViewById<Button>(R.id.btnMostrar)

    fun render(id: Int, pedido: String, onClickListener: (Int) -> Unit)
    {
        textViewProducto.text = id.toString()
        textViewPrecio.text = damePrecio(pedido) + "€"

        button.setOnClickListener {
            onClickListener(id)
        }
    }


    fun damePrecio(pedido: String): String
    {
        return ""
    }
}