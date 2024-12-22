package com.example.coc_char

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Char(
    val name: String,
    val description: String,
    val photo: String,
    val dmg: Int,
    val hp: Int,
    val attackType: String,
    val heal:String = "Healing per Second"
): Parcelable
