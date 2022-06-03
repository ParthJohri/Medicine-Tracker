package com.examples.medicinetracker

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MedicineInformationDao {
    @Query("SELECT * FROM MedicineInformation ORDER BY id ASC")
    fun getAllMedicines(): LiveData<List<MedicineInformation>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(name:MedicineInformation)

    @Delete
    fun delete(name:MedicineInformation)
}