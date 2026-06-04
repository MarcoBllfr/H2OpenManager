package dev.marcobf.h2openmanager.data.local

expect class DatabaseFactory{
    fun create(): AppDatabase
}