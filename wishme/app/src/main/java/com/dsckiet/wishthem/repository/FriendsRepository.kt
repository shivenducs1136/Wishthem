package com.dsckiet.wishthem.repository

import androidx.lifecycle.LiveData
import com.dsckiet.wishthem.dao.FriendsDao
import com.dsckiet.wishthem.entity.FriendsEntity

class FriendsRepository(private  val friendsDao: FriendsDao) {

    val allNotes:LiveData<List<FriendsEntity>> = friendsDao.getfriendsdao()

    suspend fun insert(friendsEntity: FriendsEntity){
        friendsDao.insert(friendsEntity)
    }
    suspend fun delete(friendsEntity: FriendsEntity){
        friendsDao.delete(friendsEntity)
    }

}