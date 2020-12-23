package com.example.auth.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.auth.data.model.ProfileModel

const val DATABASE_NAME = "auth_app"

@Database(entities = [ProfileModel::class], version = 2)
abstract class AppDataBase : RoomDatabase() {
    abstract fun profileDao(): ProfileDao

    companion object {
        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE ProfileModel ADD COLUMN avatar2 TEXT NOT NULL DEFAULT 's'")
            }
        }
    }
}