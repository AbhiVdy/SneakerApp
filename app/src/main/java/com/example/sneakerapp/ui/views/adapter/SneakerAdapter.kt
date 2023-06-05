package com.example.sneakerapp.ui.views.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sneakerapp.databinding.AdapterSneakerBinding
import com.example.sneakerapp.model.Sneaker

class SneakerAdapter(private val itemClickListener: OnItemClickListener) :
    ListAdapter<Sneaker, SneakerAdapter.SneakerViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Sneaker>() {
            override fun areItemsTheSame(oldItem: Sneaker, newItem: Sneaker): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Sneaker, newItem: Sneaker): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SneakerViewHolder {
        return SneakerViewHolder.create(parent, itemClickListener)
    }

    override fun onBindViewHolder(holder: SneakerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class SneakerViewHolder(view: View, private val itemClickListener: OnItemClickListener) :
        RecyclerView.ViewHolder(view) {

        companion object {
            fun create(
                parent: ViewGroup,
                itemClickListener: OnItemClickListener
            ): SneakerViewHolder {
                val binding = AdapterSneakerBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                return SneakerViewHolder(binding.root, itemClickListener)
            }
        }

        private val binding = AdapterSneakerBinding.bind(view)

        fun bind(sneaker: Sneaker) {
            binding.run {
                Glide.with(this.root).load(sneaker.media.smallImageUrl).centerCrop()
                    .into(ivThumbnail)
                tvTitle.text = sneaker.title
                ivThumbnail.setOnClickListener {
                    itemClickListener.onClick(sneaker)
                }
            }
        }
    }

    interface OnItemClickListener {
        fun onClick(sneaker: Sneaker)
    }
}