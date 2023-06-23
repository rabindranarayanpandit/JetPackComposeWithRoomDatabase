package com.techskill.jetpackcomposecleanarchitechture.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class QuoteEntity(
    val quote:String,
    val author:String,
    @PrimaryKey val id:Int? = null
)
