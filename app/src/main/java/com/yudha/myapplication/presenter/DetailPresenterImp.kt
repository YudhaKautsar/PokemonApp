package com.yudha.myapplication.presenter

import android.annotation.SuppressLint
import android.content.Context
import com.yudha.myapplication.network.ConfigNetwork
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class DetailPresenterImp(private val detailPresenter: DetailPresenter, val context: Context){
    @SuppressLint("CheckResult")
    fun detailPresenter(namaPokemon: String){
        ConfigNetwork.getNetwork().detailPokemon(namaPokemon).observeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                 detailPresenter.detailPokemon(it.abilities, it.sprites)
            },{

            })
    }
}