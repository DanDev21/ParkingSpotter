package com.dan.parkingspotter.di

import android.app.Application
import androidx.room.Room
import com.dan.parkingspotter.data.repository.ParkingSpotRepository
import com.dan.parkingspotter.data.source.local.LocalDatabase
import com.dan.parkingspotter.domain.repository.IParkingSpotRepository
import com.dan.parkingspotter.util.LocalDatabaseProperties
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides
    @Singleton
    fun providesLocalDatabase(app: Application) =
        Room.databaseBuilder(
            app,
            LocalDatabase::class.java,
            LocalDatabaseProperties.NAME
        ).build()

    @Provides
    @Singleton
    fun providesParkingSpotRepository(database: LocalDatabase): IParkingSpotRepository =
        ParkingSpotRepository(database.dao)

}