package com.example.test

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class OrderAdapter(private val orders: MutableList<Market.Order>) :
    RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName: TextView = itemView.findViewById(R.id.itemName)
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
        holder.itemQty.text = "${order.qty}"
        holder.itemPrice.text = "₱${order.price}"

        val resId = holder.itemView.context.resources.getIdentifier(
            order.img, "drawable", holder.itemView.context.packageName
        )
        holder.itemImg.setImageResource(resId)

        holder.addBtn.setOnClickListener {
            order.qty += 1
            holder.itemQty.text = "${order.qty}"
        }

        // Minus button
        holder.minusBtn.setOnClickListener {
            if (order.qty > 1) {
                order.qty -= 1
                holder.itemQty.text = "${order.qty}"
            }
        }

        // Delete button
        holder.deleteBtn.setOnClickListener {
            orders.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, orders.size)
        }
    }

    override fun getItemCount(): Int = orders.size
}