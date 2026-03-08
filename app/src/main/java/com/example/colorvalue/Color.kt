package com.example.colorvalue

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "colors")
data class Color(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "hex_color") val hex: String,
    val name: String
)
