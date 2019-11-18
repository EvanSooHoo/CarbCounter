package com.first.evansoohoo.carbcounter

import androidx.room.ColumnInfo

import androidx.room.Entity

import androidx.room.PrimaryKey

@Entity(tableName = "carb_table")
class CarbEntry(@PrimaryKey @ColumnInfo(name = "carbentry") val content: String)