package dev.marcobf.h2openmanager.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.marcobf.h2openmanager.data.local.dao.AquariumDao
import dev.marcobf.h2openmanager.data.local.entity.AquariumEntity

@Database(
    entities = [AquariumEntity::class],
    version = 2,

)
abstract class AppDatabase : RoomDatabase(){
    abstract fun aquariumDao(): AquariumDao
}