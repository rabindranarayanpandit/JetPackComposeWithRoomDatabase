package com.techskill.jetpackcomposecleanarchitechture.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
@Dao
interface QuoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuotesList(quotes:List<QuoteEntity>)

    @Query("DELETE  FROM QuoteEntity")
    suspend fun deleteQuoteList()

    @Query("SELECT * FROM QuoteEntity")
    suspend fun getStoredQuotesList():List<QuoteEntity>
}