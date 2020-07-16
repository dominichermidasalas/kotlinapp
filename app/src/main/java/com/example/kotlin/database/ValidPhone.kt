package com.example.kotlin.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "phone_validation_tries_table")
data class ValidPhone(
    @PrimaryKey(autoGenerate = true)
    var userId: Long = 0L,

    @ColumnInfo(name = "phone_number")
    val phoneNumber: String = "+1 (123) 456-7890",

    @ColumnInfo(name = "generated_code")
    val generatedCode: String = "024535",

    @ColumnInfo(name = "provided_code")
    val providedCode: String = "123456",

    @ColumnInfo(name = "is_valid")
    val valid_code: Boolean = false
)