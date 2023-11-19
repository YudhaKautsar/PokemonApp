package com.yudha.myapplication.activity.model

data class HomePageResult(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: ArrayList<Result>
)