package com.example.recyclerview_menucontexto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.recyclerview_menucontexto.databinding.FragmentHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var productViewModel: ProductViewModel
    private lateinit var adapter: ProductAdapter
    private var products = mutableListOf<Product>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).configureToolbar("Home", false)
        activity?.findViewById<BottomNavigationView>(R.id.bottomMenu)?.visibility = View.VISIBLE
        productViewModel = ViewModelProvider(this)[ProductViewModel::class.java]

        // Recuperar o estado salvo
        savedInstanceState?.let {
            products = it.getSerializable("products") as MutableList<Product>
        } ?: run {
            products = productViewModel.getProducts()
        }

        adapter = ProductAdapter(products, onClick = { product ->
            val bundle = Bundle().apply {
                putSerializable("product", product)
            }
            findNavController().navigate(R.id.action_homeFragment_to_detailProductFragment, bundle)
        }, onLongClick = { product, position ->
            adapter.removeItem(position)
        })

        with(binding.recyclerView) {
            layoutManager = GridLayoutManager(context, 2)
            adapter = this@HomeFragment.adapter
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable("products", ArrayList(products))
    }
}