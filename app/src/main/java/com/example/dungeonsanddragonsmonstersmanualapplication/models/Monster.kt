package com.example.dungeonsanddragonsmonstersmanualapplication.models

data class Monster(val id: Int, val name: String) {
    //TODO further data

    override fun toString(): String
    {
        return name
    }
}