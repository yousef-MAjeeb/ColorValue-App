package com.example.colorvalue

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ColorDao {

    @Query("SELECT * FROM colors_table")
    fun getAll(): Array<Color>

    @Query("SELECT * FROM colors_table WHERE name = :name")
    suspend fun getColorByName(name: String): LiveData<Color>

    @Query("SELECT * FROM colors_table WHERE hex_color = :hex")
    suspend fun getColorByhex(hex: String): LiveData<Color>

    @Insert
    suspend fun insert(vararg color: Color)

    @Update
    suspend fun update(color: Color)

    @Delete
    suspend fun delete(color: Color)

}