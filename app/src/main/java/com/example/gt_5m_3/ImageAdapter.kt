package com.example.gt_5m_3

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.gt_5m_3.databinding.ItemBinding

class ImageAdapter(var list: ArrayList<ImageModel>) : Adapter<ImageAdapter.ImageViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            ItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addImage(imageModel: ArrayList<ImageModel>){
        list.addAll(imageModel)
        notifyDataSetChanged()

    }
    fun cleanList(){
        list.clear()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    class ImageViewHolder(var binding: ItemBinding) : ViewHolder(binding.root) {
        fun onBind(imageModel: ImageModel) {
            binding.ivImage.load(imageModel.largeImageURL)
        }
    }
}