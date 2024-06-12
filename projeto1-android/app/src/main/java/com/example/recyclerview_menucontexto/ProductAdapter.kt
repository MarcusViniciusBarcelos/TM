package com.example.recyclerview_menucontexto

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recyclerview_menucontexto.databinding.ItemProductBinding

class ProductAdapter(
    private val products: MutableList<Product>,
    private val onClick: (Product) -> Unit,
    private val onLongClick: (Product, Int) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product, position: Int) {
            with(binding) {
                productName.text = product.name
                productPrice.text = product.price
                Glide.with(root).load(product.urlImage).into(productImage)
                root.setOnClickListener {
                    onClick(product)
                }
                root.setOnLongClickListener {
                    showPopupMenu(it, product, position)
                    true
                }
            }
        }

        private fun showPopupMenu(view: View, product: Product, position: Int) {
            val popupMenu = PopupMenu(view.context, view)
            popupMenu.inflate(R.menu.context_menu)
            popupMenu.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.context_menu_item_remove -> {
                        onLongClick(product, position)
                        true
                    }
                    else -> false
                }
            }
            popupMenu.show()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position], position)
    }

    override fun getItemCount(): Int = products.size


    fun removeItem(position: Int) {
        products.removeAt(position)
        notifyItemRemoved(position)
    }
}

