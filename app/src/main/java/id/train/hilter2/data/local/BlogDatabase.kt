package id.train.hilter2.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import id.train.hilter2.data.local.entity.BlogCacheEntity

@Database(entities = [BlogCacheEntity::class], version = 1)
abstract class BlogDatabase: RoomDatabase() {

    abstract fun getBlogDao(): BlogDao

    companion object {
        const val DATABASE_NAME: String = "blog_db"
    }
}