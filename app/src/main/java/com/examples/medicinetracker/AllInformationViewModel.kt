package com.examples.medicinetracker

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AllInformationViewModel(application: Application) : AndroidViewModel(application) {
    lateinit var repository: DatabaseRepository
    val allNames: LiveData<List<UserInformation>>
    val allMedicines: LiveData<List<MedicineInformation>>

    init {
        val daouser = AppInformationDatabase.getDatabase(application).UserInformationDao()
        val daomeds = AppInformationDatabase.getDatabase(application).MedicineInformationDao()
        repository = DatabaseRepository(daouser, daomeds)
        allNames = repository.allNames
        allMedicines = repository.getallMedicines
    }
    fun deleteName(name:UserInformation)=viewModelScope.launch(Dispatchers.IO)
    {
        repository.deleteuser(name);
    }
    fun deleteMeds(meds:MedicineInformation)=viewModelScope.launch(Dispatchers.IO)
    {
        repository.deletemeds(meds)
    }
    fun insertName(name:UserInformation)=viewModelScope.launch(Dispatchers.IO)
    {
        repository.insertuser(name)
    }
    fun insertMeds(meds:MedicineInformation)=viewModelScope.launch(Dispatchers.IO)
    {
        repository.insertmeds(meds)
    }
}