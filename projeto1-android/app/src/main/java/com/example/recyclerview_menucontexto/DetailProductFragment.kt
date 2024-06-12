package com.example.recyclerview_menucontexto

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.recyclerview_menucontexto.databinding.FragmentDetailProductBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class DetailProductFragment : Fragment() {

    private lateinit var binding: FragmentDetailProductBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_product, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val product = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getSerializable("product", Product::class.java)
        } else {
            arguments?.getSerializable("product") as? Product
        }

        (requireActivity() as AppCompatActivity).configureToolbar("Detalhe", true)
        activity?.findViewById<BottomNavigationView>(R.id.bottomMenu)?.visibility = View.GONE

        Log.d("DetailProductFragment", "Product: $product")

        product?.let {
            Log.d("DetailProductFragment", "Product Name: ${it.name}")
            Log.d("DetailProductFragment", "Product Price: ${it.price}")
            binding.product = it
            Glide.with(this).load(it.urlImage).into(binding.productImage)
        }
    }
}
