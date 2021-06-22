package com.wl.astro

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by NirajM on 23/06/21.
 * Version 1.0
 */
@Entity(tableName = AppDatabase.TABLE_ASTRO)
class AstroTable {
    @PrimaryKey
    @NonNull
    var id: Int = 1
    var date: String? = null
    var title: String? = null
    var desc: String? = null
}