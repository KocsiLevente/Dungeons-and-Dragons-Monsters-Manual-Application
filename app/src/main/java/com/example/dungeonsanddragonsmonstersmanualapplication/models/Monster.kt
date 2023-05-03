package com.example.dungeonsanddragonsmonstersmanualapplication.models

class Monster(
    val id: Int,
    val index: String,
    val image: String?,
    //Character properties.
    val name: String,
    val desc: String,
    val size: String,
    val type: String,
    val subtype: String,
    val alignment: String,
    //Base stats.
    val armorClass: Int,
    val armorType: String,
    val hitPoints: Int,
    val hitPointsRoll: String,
    val speed: String,
    val strength: Int,
    val dexterity: Int,
    val constitution: Int,
    val intelligence: Int,
    val wisdom: Int,
    val charisma: Int,
    val xp: Int,
    //Extra stats.
    val damageVulnerabilities: List<String>,
    val damageResistances: List<String>,
    val damageImmunities: List<String>,
    val languages: String,
    val actions: List<String>) {

    override fun toString(): String
    {
        return "Name: $name Description:$desc"
    }
}