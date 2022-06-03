package com.examples.medicinetracker

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserInformation(val name:String, val score:Int,@PrimaryKey(autoGenerate = true)val id:Int=0)
{
}