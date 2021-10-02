package com.dsckiet.wishthem.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.dsckiet.wishthem.dao.FriendsDao
import com.dsckiet.wishthem.entity.FriendsEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database (entities = [FriendsEntity::class],version = 1)
abstract class FriendsDatabase:RoomDatabase() {

    abstract fun friendsdao(): FriendsDao
    companion object{

        @Volatile
        private var INSTANCE: FriendsDatabase? = null
        fun getDatabase(context: Context): FriendsDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FriendsDatabase::class.java,
                    "friends_database"
                ).build()
                INSTANCE = instance

                instance
            }
        }

    }
}