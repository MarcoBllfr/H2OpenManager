package dev.marcobf.h2openmanager.data.local

import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

expect object AppDatabaseCreator : RoomDatabaseConstructor<AppDatabase>

fun getAppDatabase(
    builder: RoomDatabase.Builder<AppDatabase>
): AppDatabase {
    return builder
        //.addMigrations(MIGRATIONS)
        //.fallbackToDestructiveMigrationOnDowngrade()
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}