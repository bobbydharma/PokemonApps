package com.example.core.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core.db.entity.RemoteKeysEntity

@Dao
interface RemoteKeysDao {
    @Query("SELECT * FROM remote_keys WHERE pokemonId = :id")
    suspend fun getRemoteKey(id: Int): RemoteKeysEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<RemoteKeysEntity>)

    @Query("DELETE FROM remote_keys")
    suspend fun clearRemoteKeys()
}