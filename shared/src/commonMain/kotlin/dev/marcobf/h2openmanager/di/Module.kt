package dev.marcobf.h2openmanager.di

import androidx.room.RoomDatabase
import dev.marcobf.h2openmanager.data.local.AppDatabase
import dev.marcobf.h2openmanager.data.local.getAppDatabase
import dev.marcobf.h2openmanager.data.repository.AquariumRepositoryImpl
import dev.marcobf.h2openmanager.domain.repository.AquariumRepository
import dev.marcobf.h2openmanager.presentation.aquarium.AquariumListViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.bind
import org.koin.dsl.module

fun initKoin(config: KoinAppDeclaration? = null ) =
    startKoin {
        config?.invoke(this)
        modules(
            sharedModule,
            platformModule
        )
    }

expect val platformModule: Module

val sharedModule = module {
    singleOf(::AquariumRepositoryImpl).bind<AquariumRepository>()
    single{get<AppDatabase>().aquariumDao()}

    single {
        getAppDatabase(get<RoomDatabase.Builder<AppDatabase>>())
    }

    factory { AquariumListViewModel(get()) }
}