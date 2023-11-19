package com.yudha.myapplication.presenter

import android.annotation.SuppressLint
import android.content.Context
import com.yudha.myapplication.activity.model.Result
import com.yudha.myapplication.network.ConfigNetwork
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class HomePresenterImp(private val homePresenter: HomePresenter, val context: Context){

    @SuppressLint("CheckResult")
    fun loadPresenter(){
        ConfigNetwork.getNetwork().loadHomePokemon().observeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.count != 0){
                    homePresenter.loadPokemon(it.results)
                }
            },{
                val data = ArrayList<Result>()
                homePresenter.loadPokemon(data)
            })
    }


}