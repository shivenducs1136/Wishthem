package com.dsckiet.wishthem.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.dsckiet.wishthem.database.FriendsDatabase
import com.dsckiet.wishthem.entity.FriendsEntity
import com.dsckiet.wishthem.repository.FriendsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


class FriendViewModel(application: Application):AndroidViewModel(application) {

    val allFriends: LiveData<List<FriendsEntity>>
    private val repository: FriendsRepository
    init{
        val dao = FriendsDatabase.getDatabase(application).friendsdao()
        repository = FriendsRepository(dao)
        allFriends = repository.allNotes
    }

    fun deleteFriend(friendsEntity: FriendsEntity) = viewModelScope.launch(    Dispatchers.IO){

        repository.delete(friendsEntity)
    }

    fun insertFriend(friendsEntity: FriendsEntity) = viewModelScope.launch (Dispatchers.IO){
        repository.insert(friendsEntity)
    }

}