package com.example.sneakerapp.ui.views.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sneakerapp.databinding.FragmentCartBinding
import com.example.sneakerapp.model.Sneaker
import com.example.sneakerapp.ui.view_model.SneakerViewModel
import com.example.sneakerapp.ui.views.adapter.SneakerAdapter

class CartFragment : Fragment() {

    private lateinit var binding: FragmentCartBinding
    private val sneakerViewModel: SneakerViewModel by activityViewModels()
    private lateinit var sneakerAdapter: SneakerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sneakerAdapter = SneakerAdapter(object :
            SneakerAdapter.OnItemClickListener {
            override fun onClick(sneaker: Sneaker) {

            }
        })
        showEmptyList()
        sneakerViewModel.sneakersAddedInCart.observe(viewLifecycleOwner, Observer { sneakerList ->
            binding.run {
                sneakerList?.let {
                    if (it.isNotEmpty()) {
                        showSneakerList()
                    } else {
                        showEmptyList()
                    }
                    sneakerAdapter.submitList(sneakerList)
                    rvSneaker.layoutManager = LinearLayoutManager(requireContext())
                    rvSneaker.adapter = sneakerAdapter

                    tvSubtotal.text = sneakerViewModel.getSubtotal()
                    tvTotal.text = sneakerViewModel.getTotal()
                } ?: run {
                    showEmptyList()
                }
            }
        })
    }

    private fun showSneakerList() {
        binding.run {
            tvEmptyCart.visibility = View.GONE
            rvSneaker.visibility = View.VISIBLE
            clOrder.visibility = View.VISIBLE
        }
    }

    private fun showEmptyList() {
        binding.run {
            tvEmptyCart.visibility = View.VISIBLE
            rvSneaker.visibility = View.GONE
            clOrder.visibility = View.GONE
        }
    }
}