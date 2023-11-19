package com.yudha.myapplication.activity

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.yudha.myapplication.activity.model.Ability
import com.yudha.myapplication.activity.model.Sprites
import com.yudha.myapplication.adapter.AbilitiesAdapter
import com.yudha.myapplication.adapter.HomeAdapter
import com.yudha.myapplication.databinding.ActivityDetailPokemonBinding
import com.yudha.myapplication.presenter.DetailPresenter
import com.yudha.myapplication.presenter.DetailPresenterImp


class DetailPokemonActivityPresenter : AppCompatActivity(), DetailPresenter {

    private lateinit var binding: ActivityDetailPokemonBinding
    private lateinit var presenter: DetailPresenterImp
    private lateinit var namaPokemon: String
    private lateinit var detailAdapter: AbilitiesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPokemonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showRecycler()

        val accNamaPokemon = intent.getStringExtra(HomeAdapter.EXTRA_DETAIL)
        namaPokemon = accNamaPokemon.toString()
        presenter = DetailPresenterImp(this, this)
        presenter.detailPresenter(accNamaPokemon.toString())



    }

    private fun showRecycler(){
        detailAdapter = AbilitiesAdapter()
        binding.rvAbilities.layoutManager = LinearLayoutManager(this)
        binding.rvAbilities.adapter = detailAdapter
    }

    private fun setViews(profilePokemon: String){
        binding.tvNamaDetail.text = namaPokemon
        Glide.with(this)
            .load(profilePokemon)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    binding.pgBar2.visibility = View.GONE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: com.bumptech.glide.load.DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    binding.pgBar2.visibility = View.GONE
                    return false
                }

            })
            .into(binding.imgDetail)
    }



    override fun detailPokemon(abilities: ArrayList<Ability>, sprites: Sprites) {
        setViews(sprites.front_default)
        detailAdapter.setAbilities(abilities)
        Log.d("logAbility", abilities.toString()+"\n"+sprites)
    }
}