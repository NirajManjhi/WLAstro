package com.wl.astro

import androidx.room.Dao
import androidx.room.Query
import io.reactivex.Single

/**
 * Created by NirajM on 23/06/21.
 * Version 1.0
 */
@Dao
interface AstroDao {
    companion object {
        const val tableName = AppDatabase.TABLE_ASTRO
    }

    @Query("SELECT * FROM $tableName LIMIT 1")
    fun getAstroFromLocal(): Single<List<AstroTable>>
}