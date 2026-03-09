package com.example.colorvalue

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.colorvalue.databinding.ItemColorBinding

class ColorAdapter : ListAdapter<Color, ColorAdapter.ColorViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        val binding = ItemColorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ColorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ColorViewHolder(private val binding: ItemColorBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(color: Color) {
            binding.tvHex.text = color.hex
            binding.colorCard.setCardBackgroundColor(android.graphics.Color.rgb(color.red, color.green, color.blue))
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Color>() {
        override fun areItemsTheSame(oldItem: Color, newItem: Color): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Color, newItem: Color): Boolean {
            return oldItem == newItem
        }
    }
}