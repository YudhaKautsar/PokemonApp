package com.yudha.myapplication.controller

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.yudha.myapplication.activity.model.modelPokemon

class Pokemon_DBHandler(
    context: Context,
    factory: SQLiteDatabase.CursorFactory?
) : SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "pokemonYudhaWoW.db"
        private const val DATABASE_VERSION = 1

        const val TABLE_NAME = "tblPokemon"
        private const val colId = "Id"
        private const val colNamaPokemon = "namaPokemon"

        const val EXTRA_ASCENDING = "Ascending"
        const val EXTRA_DESCENDING = "Descending"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE: String = (
                "CREATE TABLE $TABLE_NAME (" +
                        "$colId INTEGER PRIMARY KEY AUTOINCREMENT,"+
                        "$colNamaPokemon TEXT)"
                )
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
       //
    }

    fun addPokemonName(context: Context, namaPokemon: String){
        val dataValues = ContentValues()
        dataValues.put(colNamaPokemon, namaPokemon)
        val db = this.writableDatabase
        try {
            db.insert(TABLE_NAME, null, dataValues)
        }catch (e:Exception){
            showMessage(context, e.message.toString())
        }
        db.close()
    }

    fun showMessage(context: Context, message: String){
        //Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("Range")
    fun getPokemonSQLite(context: Context): ArrayList<modelPokemon>{
        val query = "Select * from $TABLE_NAME ORDER BY $colNamaPokemon ASC"
        val db = this.readableDatabase
        val dataPokemon = db.rawQuery(query, null)
        val recPokemon = ArrayList<modelPokemon>()
        if(dataPokemon.count == 0) showMessage(context, "Data tidak ada")
        else{
            dataPokemon.moveToFirst()
            while (!dataPokemon.isAfterLast){
                val pokemon = modelPokemon()
                pokemon.id = dataPokemon.getInt(dataPokemon.getColumnIndex(colId))
                pokemon.nama  = dataPokemon.getString(dataPokemon.getColumnIndex(colNamaPokemon))
                recPokemon.add(pokemon)
                dataPokemon.moveToNext()
            }
        }
        dataPokemon.close()
        db.close()
        return recPokemon
    }

    @SuppressLint("Range")
    fun getOfflinePokemonAscendingDescending(context: Context, inputanNama: String, jenisSort: String): ArrayList<modelPokemon>{
        var query = ""
        if (inputanNama == "" && jenisSort == EXTRA_ASCENDING){
            query = "Select * from $TABLE_NAME ORDER BY $colNamaPokemon ASC"
        }
        if(inputanNama.isNotEmpty() && jenisSort == EXTRA_ASCENDING){
            query = "SELECT * FROM $TABLE_NAME WHERE $colNamaPokemon LIKE '%$inputanNama%' ORDER BY $colNamaPokemon ASC"
        }
        if (inputanNama == "" && jenisSort == EXTRA_DESCENDING){
            query = "Select * from $TABLE_NAME ORDER BY $colNamaPokemon DESC"
        }
        if(inputanNama.isNotEmpty() && jenisSort == EXTRA_DESCENDING){
            query = "SELECT * FROM $TABLE_NAME WHERE $colNamaPokemon LIKE '%$inputanNama%' ORDER BY $colNamaPokemon DESC"
        }

        val db = this.readableDatabase
        val dataPokemon = db.rawQuery(query, null)
        val recPokemon = ArrayList<modelPokemon>()
        if(dataPokemon.count == 0) showMessage(context, "Data tidak ada")
        else{
            dataPokemon.moveToFirst()
            while (!dataPokemon.isAfterLast){
                val pokemon = modelPokemon()
                pokemon.id = dataPokemon.getInt(dataPokemon.getColumnIndex(colId))
                pokemon.nama  = dataPokemon.getString(dataPokemon.getColumnIndex(colNamaPokemon))
                recPokemon.add(pokemon)
                dataPokemon.moveToNext()
            }
        }
        dataPokemon.close()
        db.close()
        return recPokemon
    }



}