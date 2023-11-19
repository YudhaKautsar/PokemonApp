package com.yudha.myapplication.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yudha.myapplication.activity.model.Ability
import com.yudha.myapplication.databinding.ItemAbilitiesBinding

class AbilitiesAdapter : RecyclerView.Adapter<AbilitiesAdapter.ViewHolder>() {

    private val abilities = ArrayList<Ability>()

    @SuppressLint("NotifyDataSetChanged")
    fun setAbilities(abilities: ArrayList<Ability>) {
        this.abilities.clear()
        this.abilities.addAll(abilities)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemAbilitiesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindViews(ability: Ability){
            binding.txtAbilities.text = ability.ability.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbilitiesAdapter.ViewHolder {
        val view = ItemAbilitiesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AbilitiesAdapter.ViewHolder, position: Int) {
        holder.bindViews(this.abilities[position])
    }

    override fun getItemCount(): Int {
        return this.abilities.size
    }
}