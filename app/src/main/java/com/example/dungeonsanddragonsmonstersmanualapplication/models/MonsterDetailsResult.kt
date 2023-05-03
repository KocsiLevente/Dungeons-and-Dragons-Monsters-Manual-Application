package com.example.dungeonsanddragonsmonstersmanualapplication.models

class MonsterDetailsResult(
    val index: String,
    val image: String,
    //Character properties.
    val name: String,
    val desc: String,
    val size: String,
    val type: String,
    val subtype: String,
    val alignment: String,
    //Base stats.
    val armor_class: List<ArmorClass>,
    val hit_points: Int,
    val hit_points_roll: String,
    val speed: Speed,
    val strength: Int,
    val dexterity: Int,
    val constitution: Int,
    val intelligence: Int,
    val wisdom: Int,
    val charisma: Int,
    val xp: Int,
    //Extra stats.
    val damage_vulnerabilities: List<String>,
    val damage_resistances: List<String>,
    val damage_immunities: List<String>,
    val languages: String,
    val actions: List<Action>) {

    class ArmorClass(
        val type: String,
        val value: Int,
        val armor: List<Armor>)

    class Armor(
        val index: String,
        val name: String,
        val url: String)

    class Speed(
        val walk: String,
        val fly: String,
        val swim: String) {

        override fun toString(): String {
            return "Walk: $walk Fly: $fly Swim: $swim"
        }
    }

    class Action(
        val name: String,
        val desc: String)
}