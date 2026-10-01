package com.filkom.lazylayout

import androidx.annotation.DrawableRes

data class Gadget(
    val id: Int,
    val name: String,
    val description: String,
    @DrawableRes val image: Int
)