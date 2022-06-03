package com.examples.medicinetracker

import android.app.Application
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DisplayMedicinesAdapter(val list: List<MedicineInformation>,val application:Application,val time:String) :
    RecyclerView.Adapter<DisplayMedicinesAdapter.MedicineViewHolder>() {
    private var allViewModel: AllInformationViewModel = AllInformationViewModel(application)

    class MedicineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.title)
        var description: TextView = itemView.findViewById(R.id.description)
        var btnDelete: ImageView = itemView.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicineViewHolder {
        return MedicineViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.medicine_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MedicineViewHolder, position: Int) {
        holder.title.text = list[position].Title
        holder.description.text = list[position].Description
        holder.btnDelete.setOnClickListener {
            var doseMorning=list[position].DoseMorning
            var doseAfternoon=list[position].DoseAfter
            var doseEvening=list[position].DoseEvening
            when(time){
                "morning"->{doseMorning=false}
                "afternoon"->{doseAfternoon=false}
                "evening"->{doseEvening=false}
            }
            allViewModel.insertMeds(MedicineInformation(list[position].Title,list[position].Description,list[position].EndDay,doseMorning,doseAfternoon,doseEvening,list[position].id))
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}