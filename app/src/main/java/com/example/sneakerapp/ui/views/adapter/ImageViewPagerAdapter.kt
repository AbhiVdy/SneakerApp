package com.example.sneakerapp.ui.views.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sneakerapp.databinding.LayoutImageBinding

class ImageViewPagerAdapter :
    ListAdapter<String, ImageViewPagerAdapter.ImageViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        companion object {
            fun create(parent: ViewGroup): ImageViewHolder {
                val binding = LayoutImageBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                return ImageViewHolder(binding.root)
            }
        }

        private val binding = LayoutImageBinding.bind(view)

        fun bind(thumbnail: String) {
            binding.run {
                Glide.with(this.root).load(thumbnail).into(ivThumbnail)
            }
        }
    }


}