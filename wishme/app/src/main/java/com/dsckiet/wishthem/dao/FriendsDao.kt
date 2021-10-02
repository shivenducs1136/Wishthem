package com.dsckiet.wishthem.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.dsckiet.wishthem.entity.FriendsEntity

@Dao
interface FriendsDao {

    @Insert (onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(friendsEntity: FriendsEntity)

    @Delete
    suspend fun delete(friendsEntity: FriendsEntity)

    @Query("SELECT * FROM friends_table")
    fun getfriendsdao(): LiveData<List<FriendsEntity>>

}