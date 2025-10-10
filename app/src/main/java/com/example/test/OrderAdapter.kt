package com.example.test

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class OrderAdapter(
    private val context: Context,
    private val orders: List<Market.Order>
) : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    class OrderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemImg: ImageView = view.findViewById(R.id.itemImg)
        val itemName: TextView = view.findViewById(R.id.itemName)
        val itemPack: TextView = view.findViewById(R.id.itemPack)
        val itemPrice: TextView = view.findViewById(R.id.itemPrice)
        val itemQty: TextView = view.findViewById(R.id.itemQty)
        val addBtn: ImageButton = view.findViewById(R.id.addBtn)
        val minusBtn: ImageButton = view.findViewById(R.id.minusBtn)
        val deleteBtn: ImageButton = view.findViewById(R.id.deleteBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_item_order, parent, false)
        return OrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = orders[position]

        holder.itemName.text = order.name
        holder.itemPack.text = "${order.pack} pcs"
        holder.itemPrice.text = "₱${order.price}"
        holder.itemQty.text = order.qty.toString()

        // Dynamically set image from drawable using its name
        val resId = context.resources.getIdentifier(order.img, "drawable", context.packageName)
        if (resId != 0) {
            holder.itemImg.setImageResource(resId)
        } else {
            holder.itemImg.setImageResource(R.drawable.ic_launcher_background) // fallback
        }

        // Optional: set up button clicks here
        holder.addBtn.setOnClickListener {
            // logic for add
        }
        holder.minusBtn.setOnClickListener {
            // logic for minus
        }
        holder.deleteBtn.setOnClickListener {
            // logic for delete
        }
    }

    override fun getItemCount(): Int = orders.size
}
