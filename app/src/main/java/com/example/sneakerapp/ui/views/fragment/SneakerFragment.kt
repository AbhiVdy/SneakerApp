package com.example.sneakerapp.ui.views.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.sneakerapp.databinding.FragmentSneakerBinding
import com.example.sneakerapp.ui.view_model.SneakerViewModel
import com.example.sneakerapp.ui.views.adapter.ImageViewPagerAdapter


class SneakerFragment : Fragment() {

    private lateinit var binding: FragmentSneakerBinding
    private val sneakerViewModel: SneakerViewModel by activityViewModels()
    private lateinit var imageViewAdapter: ImageViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSneakerBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageViewAdapter = ImageViewPagerAdapter()

        sneakerViewModel.selectedSneaker.observe(viewLifecycleOwner, Observer { sneakerEntity ->
            binding.run {

                Glide.with(this.root).load(sneakerEntity.media.imageUrl).into(ivThumbnail)
                tvTitle.text = sneakerEntity.title
                tvPrice.text = "$${sneakerEntity.retailPrice}"

                btnAddToCart.setOnClickListener {
                    sneakerViewModel.addSneakerToCart(sneakerEntity)
                }
            }
        })
    }
}