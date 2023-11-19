package com.yudha.myapplication.presenter

import com.yudha.myapplication.activity.model.Ability
import com.yudha.myapplication.activity.model.Sprites

interface DetailPresenter {
    fun detailPokemon(abilities: ArrayList<Ability>, sprites: Sprites)
}