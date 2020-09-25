package com.satriaamrudito.profilesekolah.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.satriaamrudito.profilesekolah.model.ItemRv

@Database(entities = arrayOf(ItemRv::class), version = 1)
abstract class RoomDB : RoomDatabase() {
    abstract fun roomDao(): RoomDao

    companion object {
        @Volatile
        private var instance: RoomDB? = null

        fun getInstance(context: Context): RoomDB {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(context, RoomDB::class.java, "item.db")
                    .build()
            }
        }

    }
}
