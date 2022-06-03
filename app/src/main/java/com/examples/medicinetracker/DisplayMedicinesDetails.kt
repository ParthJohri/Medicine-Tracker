package com.examples.medicinetracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DisplayMedicinesDetails : AppCompatActivity() {
    private lateinit var allViewModel: AllInformationViewModel
    private var morningList: MutableList<MedicineInformation> = mutableListOf()
    private var afternoonList: MutableList<MedicineInformation> = mutableListOf()
    private var eveningList: MutableList<MedicineInformation> = mutableListOf()
    private lateinit var adp: DisplayMedicinesAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_medicines_details)
        this.title = when(intent.getStringExtra("time")){
            "morning" -> {
                "Morning"
            }
            "afternoon" -> {
                "Afternoon"
            }
            "evening" -> {
                "Evening"
            }
            else->{
                "Medicines"
            }
        }+" schedule"
        recyclerView = findViewById(R.id.rvDetails)
        allViewModel = AllInformationViewModel(application)
        val time = intent.getStringExtra("time")
        Log.d("TAG", "$time")

        when (time) {
            "morning" -> {
                Log.d("TAG", "inside morning")
                adp = DisplayMedicinesAdapter(morningList,application,time)
            }
            "afternoon" -> {
                Log.d("TAG", "inside afternoon")
                adp = DisplayMedicinesAdapter(afternoonList,application,time)
            }
            "evening" -> {
                Log.d("TAG", "inside evening")
                adp = DisplayMedicinesAdapter(eveningList,application,time)
            }
        }

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adp
        allViewModel.allMedicines.observe(this, {
            Log.d("LIST ", "$it")
            morningList.clear()
            afternoonList.clear()
            eveningList.clear()
            for (medObject in it) {
                if (medObject.DoseMorning)
                    morningList.add(medObject)
                if (medObject.DoseAfter)
                    afternoonList.add(medObject)
                if (medObject.DoseEvening)
                    eveningList.add(medObject)
            }
            Log.d("TAG", "$morningList")
            Log.d("TAG", "$afternoonList")
            Log.d("TAG", "$eveningList")
            adp.notifyDataSetChanged()
        })
        adp.notifyDataSetChanged()

    }
}