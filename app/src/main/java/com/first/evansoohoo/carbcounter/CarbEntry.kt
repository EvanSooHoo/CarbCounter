package com.first.evansoohoo.carbcounter

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class User(
        @PrimaryKey var uid: Int,
        @ColumnInfo(name = "carb_count") var carbCount: Int?,
        @ColumnInfo(name = "last_name") var lastName: String?
)