package com.example.projectdraft

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.NumberFormat
import java.util.Locale

data class DisplayPriceItem(
    val productName: String,
    val storeName: String,
    val price: Double,
    val url: String
)

class ProductAdapter(
    private val prices: List<DisplayPriceItem>,
    private val clickListener: (DisplayPriceItem) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.product_name)
        val priceTextView: TextView = view.findViewById(R.id.product_price)
        val storeTextView: TextView = view.findViewById(R.id.product_store)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.srp_product_item_layout, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item = prices[position]

        // Format the price to currency (using US locale as a default)
        val formatter = NumberFormat.getCurrencyInstance(Locale.US)

        // 1. Product Name: Now shows the parent product name
        holder.nameTextView.text = item.productName

        // 2. Price: Shows the formatted price
        holder.priceTextView.text = formatter.format(item.price)

        // 3. Store: Shows the specific store name
        holder.storeTextView.text = "Sold by: ${item.storeName}"

        // Set up the click listener to open the external link
        holder.itemView.setOnClickListener {
            clickListener(item)
        }
    }

    override fun getItemCount() = prices.size
}