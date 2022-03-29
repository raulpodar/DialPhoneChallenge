package com.sky.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "phoneNumbers")
data class PhoneNumber (
    @PrimaryKey(autoGenerate = true) val id:Int,
    @ColumnInfo (name="phone_number")val phoneNumber:String
)