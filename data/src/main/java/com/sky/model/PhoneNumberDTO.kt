package com.sky.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "phoneNumbers")
data class PhoneNumberDTO (
    @PrimaryKey(autoGenerate = true) val id:Int=0,
    @ColumnInfo(name="phone_number")val phoneNumber:String
)