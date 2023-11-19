package com.yudha.myapplication.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.R.*
import com.yudha.myapplication.R
import com.yudha.myapplication.activity.DetailPokemonActivityPresenter
import com.yudha.myapplication.databinding.ItemHomeBinding
import com.yudha.myapplication.activity.model.modelPokemon

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    private var listPokemon = ArrayList<modelPokemon>()

    @SuppressLint("NotifyDataSetChanged")
    fun setPokemon(listPokemon: ArrayList<modelPokemon>) {
        this.listPokemon.clear()
        this.listPokemon.addAll(listPokemon)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemHomeBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("PrivateResource")
        fun bindViews(data: modelPokemon, position: Int) {
            binding.txtNamaPokemon.text =
                itemView.resources.getString(R.string.namaPokemon, data.nama)
            if (position % 2 == 0) {
                binding.txtNamaPokemon.setBackgroundColor(itemView.resources.getColor(color.material_blue_grey_800))
            } else {
                binding.txtNamaPokemon.setBackgroundColor(itemView.resources.getColor(color.m3_ref_palette_secondary40))
            }


            binding.txtNamaPokemon.setOnClickListener {
                val send = Intent(itemView.context, DetailPokemonActivityPresenter::class.java)
                send.putExtra(EXTRA_DETAIL, data.nama)
                itemView.context.startActivity(send)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bindViews(listPokemon[position], position + 1)

    override fun getItemCount(): Int = listPokemon.size

    companion object{
        const val EXTRA_DETAIL = "KEYS_DETAIL"
    }

}