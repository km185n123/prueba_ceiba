package com.example.contactos.data.posts.entities

import androidx.room.*
@Entity
data class PostEntity(
    @ColumnInfo(name = "userId") var userId: String,
    @ColumnInfo(name = "id") var id: String,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "body") var body: String
){
    @PrimaryKey(autoGenerate = true)
    var primary: Int = 0
}