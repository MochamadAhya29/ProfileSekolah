package com.satriaamrudito.profilesekolah.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.satriaamrudito.profilesekolah.model.ItemRv

@Dao
interface RoomDao {

    @Query("SELECT * FROM item WHERE type = :type")
    fun getDataByType(type : String): LiveData<List<ItemRv>>

//    @Query("SELECT * FROM item WHERE uid = :id")
//    suspend fun getAllData(id: Long): LiveData<ItemRv>

    @Query("DELETE FROM item")
    suspend fun resetDatabase()

    @Query("DELETE FROM item WHERE type = :type")
    suspend fun reserType(type: String)

    @Insert
    suspend fun insertData(data: List<ItemRv>)

//    @Delete
//    fun delete(data: ItemRv)

}