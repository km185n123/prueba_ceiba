package com.example.contactos.data.users.entities

import androidx.room.*
@Entity
data class UserEntity(
    @ColumnInfo(name = "id") var id: String,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "username") var username: String,
    @ColumnInfo(name = "email") var email: String,
    @ColumnInfo(name = "street") var street: String,
    @ColumnInfo(name = "suite") var suite: String,
    @ColumnInfo(name = "city") var city: String,
    @ColumnInfo(name = "zipcode") var zipcode: String,
    @ColumnInfo(name = "lat") val lat: String,
    @ColumnInfo(name = "lng") val lng: String,
    @ColumnInfo(name = "phone") var phone: String,
    @ColumnInfo(name = "website") var website: String,
    @ColumnInfo(name = "nameCompany") val nameCompany: String,
    @ColumnInfo(name = "catchPhrase") val catchPhrase: String,
    @ColumnInfo(name = "bs") val bs: String
){
    @PrimaryKey(autoGenerate = true)
    var primary: Int = 0
}