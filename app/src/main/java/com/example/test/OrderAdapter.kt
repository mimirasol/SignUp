package com.example.test

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class OrderAdapter(private val orders: MutableList<Market.Order>) :
    RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName: TextView = itemView.findViewById(R.id.itemName)
        val itemPack: TextView = itemView.findViewById(R.id.itemPack)
        var itemQty: TextView = itemView.findViewById(R.id.itemQty)
        val itemPrice: TextView = itemView.findViewById(R.id.itemPrice)
        val itemImg: ImageView = itemView.findViewById(R.id.itemImg)
        val addBtn: ImageButton = itemView.findViewById(R.id.addBtn)
        val minusBtn: ImageButton = itemView.findViewById(R.id.minusBtn)
        val deleteBtn: ImageButton = itemView.findViewById(R.id.deleteBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_item_order, parent, false)
        return OrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = orders[position]

        holder.itemName.text = order.name
        holder.itemPack.text = "${order.qty} Pieces"
        holder.itemQty.text = "${order.qty}"
        holder.itemPrice.text = "₱${order.price}"

        val resId = holder.itemView.context.resources.getIdentifier(
            order.img, "drawable", holder.itemView.context.packageName
        )
        holder.itemImg.setImageResource(resId)

        holder.addBtn.setOnClickListener {
            if (order.qty < 10) {
                order.qty += 1
                holder.itemQty.text = "${order.qty}"
                holder.itemPrice.text = "₱${order.price}"
                (holder.itemView.context as? Cart)?.updateTotals()
            } else {
                val message = "Max order quantity is 10!"
                Toast.makeText(holder.itemView.context, message, Toast.LENGTH_SHORT).show()
            }
        }

        // Minus button
        holder.minusBtn.setOnClickListener {
            if (order.qty > 1) {
                order.qty -= 1
                holder.itemQty.text = "${order.qty}"
                holder.itemPrice.text = "₱${order.price}"
                (holder.itemView.context as? Cart)?.updateTotals()
            }
        }

        // Delete button
        holder.deleteBtn.setOnClickListener {
            orders.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, orders.size)
            (holder.itemView.context as? Cart)?.updateTotals()
        }
    }

    override fun getItemCount(): Int = orders.size
}