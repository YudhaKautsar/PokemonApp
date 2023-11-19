package com.yudha.myapplication.presenter

import com.yudha.myapplication.activity.model.Result

interface HomePresenter {
    fun loadPokemon(results: ArrayList<Result>)
}