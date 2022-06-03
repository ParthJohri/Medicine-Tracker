package com.examples.medicinetracker

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserInformationDao {
    @Query("SELECT * FROM UserInformation ORDER BY name ASC")
    fun getAllNames(): LiveData<List<UserInformation>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insert(name:UserInformation)

    @Delete
     fun delete(name:UserInformation)
}
