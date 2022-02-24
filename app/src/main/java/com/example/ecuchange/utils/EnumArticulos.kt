package com.example.ecuchange.utils

class EnumArticulos {

    enum class SelectionCategory {
        electronica, casa;

        companion object {
            fun fromPosition(pos: Int) = SelectionCategory.values().firstOrNull {
                it.ordinal == pos
            }.toString()
        }

    }
}