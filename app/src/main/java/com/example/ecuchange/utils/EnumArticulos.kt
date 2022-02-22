package com.example.ecuchange.utils

class EnumArticulos {

    enum class SelectionCategory {
        business, entertainment, general, health, science, sports, technology;

        companion object {
            fun fromPosition(pos: Int) = SelectionCategory.values().firstOrNull {
                it.ordinal == pos
            }.toString()
        }

    }
}