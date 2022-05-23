package co.erti.runningtrackerapp.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.room.Room
import co.erti.runningtrackerapp.db.RunningDatabase
import co.erti.runningtrackerapp.other.Constants.KEY_FIRST_TIME_TOGGLE
import co.erti.runningtrackerapp.other.Constants.KEY_NAME
import co.erti.runningtrackerapp.other.Constants.KEY_WEIGHT
import co.erti.runningtrackerapp.other.Constants.RUNNING_DATABASE_NAME
import co.erti.runningtrackerapp.other.Constants.SHARED_PREFERENCES_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class) //install module (manual DI) to this application component, DI lasts in app timescope
object AppModule {

    @Singleton //nuk do te krijohen instanca te reja per cdo klase qe mund te kerkoje DB por do perdoret vetem 1
    @Provides  //funksioni sherben per te krijuar other dependencies dhe mund te inject n klasa t tjera
    fun provideRunningDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        RunningDatabase::class.java,
        RUNNING_DATABASE_NAME
    ).build()


    @Singleton
    @Provides
    fun provideRunDao(db: RunningDatabase) = db.getRunDao()

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext app: Context) =
        app.getSharedPreferences(SHARED_PREFERENCES_NAME,MODE_PRIVATE)

    @Singleton
    @Provides
    fun provideName(sharedPref : SharedPreferences) = sharedPref.getString(KEY_NAME, "") ?: ""

    @Singleton
    @Provides
    fun provideWeight(sharedPref : SharedPreferences) = sharedPref.getFloat(KEY_WEIGHT, 80f)

    @Singleton
    @Provides
    fun provideFirstTimeToggle(sharedPref : SharedPreferences) = sharedPref.getBoolean(
        KEY_FIRST_TIME_TOGGLE, true)
}