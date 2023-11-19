package com.yudha.myapplication.activity.cache

import android.content.Context

class cachePokemon(context: Context){

    companion object{
        private const val PREFER_SORT = "SORTING"
        private const val KEY_ASC_OR_DESC = "Ascending_DESC"
    }

    private val preferenceSort = context.getSharedPreferences(PREFER_SORT, Context.MODE_PRIVATE)

    fun jenisSorting(sort: String){
        val editor = preferenceSort.edit()
        editor.putString(KEY_ASC_OR_DESC, sort)
        editor.apply()
    }

    fun getSorting() : String{
        return preferenceSort.getString(KEY_ASC_OR_DESC, "Ascending").toString()
    }

}