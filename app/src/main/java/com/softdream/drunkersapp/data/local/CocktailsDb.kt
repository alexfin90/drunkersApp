package com.softdream.drunkersapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [LocalCocktail::class],
    version = 3,
    exportSchema = false
)
abstract class CocktailsDb : RoomDatabase() {
    abstract val dao: CocktailDao

}