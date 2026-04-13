package com.example.core.database.dao

import androidx.room.Dao
import androidx.room.Ignore
import androidx.room.Transaction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


@Dao
abstract class TransactionRunnerDao {
    @Transaction
    protected open suspend fun runInTransaction(tx: suspend () -> Unit) = tx()

    @Ignore
    suspend operator fun invoke(tx: suspend () -> Unit) = withContext(Dispatchers.IO) {
        try {
            runInTransaction(tx)
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }
}