package com.yudha.myapplication.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.yudha.myapplication.R
import com.yudha.myapplication.activity.cache.cachePokemon
import com.yudha.myapplication.adapter.HomeAdapter
import com.yudha.myapplication.controller.Pokemon_DBHandler
import com.yudha.myapplication.databinding.ActivityHomePageBinding
import com.yudha.myapplication.activity.model.Result
import com.yudha.myapplication.activity.model.modelPokemon
import com.yudha.myapplication.presenter.HomePresenter
import com.yudha.myapplication.presenter.HomePresenterImp

class HomePageActivityPresenter : AppCompatActivity(), HomePresenter {

    private lateinit var binding: ActivityHomePageBinding
    private lateinit var presenter: HomePresenterImp
    private lateinit var homeAdapter: HomeAdapter
    private lateinit var list: ArrayList<modelPokemon>
    var newText: String = ""
    private lateinit var cache: cachePokemon

    companion object {
        lateinit var dbHandler: Pokemon_DBHandler
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cache = cachePokemon(this)

        dbHandler =
            Pokemon_DBHandler(this, null)

        binding.pgBar.visibility = View.VISIBLE


        binding.edtSearch.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //
            }

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val newTextt = s.toString()
                newText = newTextt
                homeAdapter.setPokemon(getDataSqliteAscendingDescending(newText, cache.getSorting()))
                binding.pgBar.visibility = View.GONE
            }

            override fun afterTextChanged(p0: Editable?) {
                //
            }
        })


        setupAdapter()
        initPresenter()
        getDataPokemon()
    }



    fun getDataSqliteAscendingDescending(query: String, jenisSort: String): ArrayList<modelPokemon> {
        var result = ArrayList<modelPokemon>()
        if(jenisSort == Pokemon_DBHandler.EXTRA_ASCENDING){
            result =  dbHandler.getOfflinePokemonAscendingDescending(this, query, Pokemon_DBHandler.EXTRA_ASCENDING)
        }else if(jenisSort == Pokemon_DBHandler.EXTRA_DESCENDING){
            result =  dbHandler.getOfflinePokemonAscendingDescending(this, query, Pokemon_DBHandler.EXTRA_DESCENDING)
        }
        return result
    }

    private fun setupAdapter() {
        homeAdapter = HomeAdapter()
        binding.rvPokemon.layoutManager = LinearLayoutManager(this)
        binding.rvPokemon.adapter = homeAdapter
    }

    private fun getDataPokemon() {
        presenter.loadPresenter()
    }

    private fun initPresenter() {
        presenter = HomePresenterImp(this, this)
    }

    override fun loadPokemon(results: ArrayList<Result>) {
        binding.pgBar.visibility = View.GONE
        val sqlitePokemon = dbHandler.getPokemonSQLite(this)
        if (sqlitePokemon.isEmpty()) {
            val listPokemon = ArrayList<modelPokemon>()
            results.forEach {
                val infoPokemon = modelPokemon(null, it.name)
                listPokemon.add(infoPokemon)
                dbHandler.addPokemonName(this, it.name)
            }
            this.list = listPokemon
            homeAdapter.setPokemon(listPokemon)
        } else {
            homeAdapter.setPokemon(sqlitePokemon)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.actionAsc->{
                cache.jenisSorting(Pokemon_DBHandler.EXTRA_ASCENDING)
                homeAdapter.setPokemon(
                    getDataSqliteAscendingDescending(
                        newText,
                        cache.getSorting()
                    )
                )
            }

            R.id.actionDesc->{
                cache.jenisSorting(Pokemon_DBHandler.EXTRA_DESCENDING)
                homeAdapter.setPokemon(getDataSqliteAscendingDescending(newText, cache.getSorting()))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }


}