package com.examples.medicinetracker

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

class DatabaseRepository(private val userInformationDao:UserInformationDao, private val medicineInformationDao: MedicineInformationDao) {
    val allNames: LiveData<List<UserInformation>> = userInformationDao.getAllNames()
    val getallMedicines: LiveData<List<MedicineInformation>> = medicineInformationDao.getAllMedicines()
    @Suppress("RedundantSuppressModifier")
    suspend fun insertuser(name:UserInformation)
    {
        userInformationDao.insert(name)
    }
    suspend fun deleteuser(name:UserInformation)
    {
        userInformationDao.delete(name)
    }
    suspend fun insertmeds(meds:MedicineInformation)
    {
        medicineInformationDao.insert(meds)
    }
    suspend fun deletemeds(meds:MedicineInformation)
    {
        medicineInformationDao.delete(meds)
    }
}