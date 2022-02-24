package com.example.ecuchange.utils

class EnumArticulos {
    //private var electronica = "6212ef2448b036d3701843e7"
    enum class SelectionCategory (val id: String) {

        electronica("6212ef2448b036d3701843e7"), casa("62141afa79dda0b10ec53cc3");

        companion object {
            fun fromPosition(pos: Int): String = SelectionCategory.values().firstOrNull {
                it.ordinal == pos
            }!!.id.toString()

//            fun position(pos: Int): String {
//                for (category in SelectionCategory.values()) {
//                    if (category.ordinal == pos) {
//                        return category.id
//                    }
//                }
//                return ""
//            }
        }

    }
}