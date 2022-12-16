package aad.cafeteriagoya.adapter

import android.content.Context
import aad.cafeteriagoya.R
import android.database.Cursor
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class PedidosAdapter(private val onClickListener: (Int) -> Unit): RecyclerView.Adapter<PedidosViewHolder>()
{
    private lateinit var context: Context
    lateinit var pedidos: Cursor

    fun PedidosAdapter(context: Context, cursor: Cursor)
    {
        this.context = context
        this.pedidos = cursor
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PedidosViewHolder
    {
        val layoutInflater = LayoutInflater.from(parent.context)

        return PedidosViewHolder(layoutInflater.inflate(R.layout.item_pedido, parent, false))
    }


    override fun onBindViewHolder(holder: PedidosViewHolder, position: Int)
    {
        pedidos.move(position)

        holder.render(pedidos.getInt(0), pedidos.getString(1), onClickListener)
    }


    override fun getItemCount(): Int
    {
        return pedidos.count
    }
}