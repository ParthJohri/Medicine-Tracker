package com.examples.medicinetracker

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MedicineInformation(val Title:String, val Description:String, val EndDay:Long, val DoseMorning:Boolean, val DoseAfter:Boolean, val DoseEvening:Boolean, @PrimaryKey(autoGenerate=true)val id:Int=0) {

}