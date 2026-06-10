package dev.marcobf.h2openmanager.di

import androidx.room.RoomDatabase
import dev.marcobf.h2openmanager.data.local.AppDatabase
import dev.marcobf.h2openmanager.data.local.getAppDatabaseBuilder
import org.koin.dsl.module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind


actual val platformModule = module {
    singleOf(::getAppDatabaseBuilder).bind<RoomDatabase.Builder<AppDatabase>>()
}
