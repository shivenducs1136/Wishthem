package com.dsckiet.wishthem.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "friends_table")
class FriendsEntity(@ColumnInfo(name = "name") val name: String,
                    @ColumnInfo(name = "tagline") val tagline: String,
                    @ColumnInfo(name = "dob") val dob: String,
                    @ColumnInfo(name ="image") val image:String){
        @PrimaryKey(autoGenerate = true)
        var id = 0

}
//                    @ColumnInfo(name = "image") val image: String,
