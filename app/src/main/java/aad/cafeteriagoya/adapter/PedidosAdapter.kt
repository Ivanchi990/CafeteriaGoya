package aad.cafeteriagoya.adapter

import android.content.Context
import aad.cafeteriagoya.R
import android.database.Cursor
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class PedidosAdapter(private val onClickListener: (Int) -> Unit) : RecyclerView.Adapter<PedidosViewHolder>()
{
    private lateinit var context: Context
    lateinit var cursor: Cursor

    fun PedidosAdapter(context: Context, cursor: Cursor)
    {
        this.context = context
        this.cursor = cursor
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PedidosViewHolder
    {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PedidosViewHolder(layoutInflater.inflate(R.layout.item_pedido, parent, false))
    }


    override fun onBindViewHolder(holder: PedidosViewHolder, position: Int)
    {
        cursor.moveToPosition(position)

        holder.render(cursor.getInt(0), cursor.getString(1), onClickListener)
    }

    override fun getItemCount(): Int = cursor.count
}