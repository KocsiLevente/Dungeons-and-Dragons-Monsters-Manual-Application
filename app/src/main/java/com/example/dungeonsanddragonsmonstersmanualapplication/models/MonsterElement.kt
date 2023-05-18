package com.example.dungeonsanddragonsmonstersmanualapplication.models

class MonsterElement(val index: String, val name: String, val url: String) {
    override fun toString(): String
    {
        return "$name: check out: $url"
    }
}