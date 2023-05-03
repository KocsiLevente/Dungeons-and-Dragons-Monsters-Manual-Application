package com.example.dungeonsanddragonsmonstersmanualapplication.remote

import com.example.dungeonsanddragonsmonstersmanualapplication.models.MonsterDetailsResult
import com.example.dungeonsanddragonsmonstersmanualapplication.models.MonsterElement
import com.example.dungeonsanddragonsmonstersmanualapplication.models.MonstersResult
import retrofit2.Call
import retrofit2.http.*

interface DnDApi {

    @GET("monsters")
    fun getMonsters(): Call<MonstersResult>

    @GET("monsters/{index}")
    fun getMonsterDetails(@Path("index") index: String): Call<MonsterDetailsResult>

    @POST("monsters")
    fun addMonster(@Body monster: MonsterElement)

    @PUT("monsters/{index}")
    fun updateMonster(@Path("index") index: String, @Body monster: MonsterElement)

    @DELETE("monsters/{index}")
    fun deleteMonster(@Path("index") index: String)
}