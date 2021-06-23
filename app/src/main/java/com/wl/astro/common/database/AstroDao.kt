package com.wl.astro.common.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: AstroTable): Completable

    @Query("SELECT * FROM $tableName")
    fun getAstroFromLocal(): Single<List<AstroTable>?>
}