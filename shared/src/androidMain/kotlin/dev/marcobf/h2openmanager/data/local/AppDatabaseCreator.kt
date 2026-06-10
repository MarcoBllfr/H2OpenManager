package dev.marcobf.h2openmanager.data.local

import androidx.room.RoomDatabaseConstructor

actual object AppDatabaseCreator : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase = AppDatabase::class.java.newInstance()
}
