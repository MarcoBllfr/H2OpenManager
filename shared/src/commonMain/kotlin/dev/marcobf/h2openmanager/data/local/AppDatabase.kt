package dev.marcobf.h2openmanager.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.marcobf.h2openmanager.data.local.dao.AquariumDao
import dev.marcobf.h2openmanager.data.local.dao.MaintenanceDao
import dev.marcobf.h2openmanager.data.local.entity.AquariumEntity
import dev.marcobf.h2openmanager.data.local.entity.MaintenanceEntity

@Database(
    entities = [AquariumEntity::class, MaintenanceEntity::class],
    version = 3

)
abstract class AppDatabase : RoomDatabase(){
    abstract fun aquariumDao(): AquariumDao
    abstract fun maintenanceDao(): MaintenanceDao
}