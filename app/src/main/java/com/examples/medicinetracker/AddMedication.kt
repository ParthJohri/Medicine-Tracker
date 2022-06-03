package com.examples.medicinetracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import kotlinx.android.synthetic.main.activity_add_medication.*

class AddMedication : AppCompatActivity() {

    private var selectedButton: ImageButton? = null
    lateinit var proceed:Button
    lateinit var medicine:EditText
    lateinit var cbMorning:CheckBox
    lateinit var cbAfternoon:CheckBox
    lateinit var cbEvening:CheckBox
    lateinit var medicineTypes:RadioGroup
    lateinit var medicinetimetypes:RadioGroup
    lateinit var allViewModel:AllInformationViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        allViewModel = AllInformationViewModel(application)
        setContentView(R.layout.activity_add_medication)
        supportActionBar?.hide()
        proceed=findViewById(R.id.add_medicine)
        medicine=findViewById(R.id.med_name)
        cbMorning=findViewById(R.id.cbMorning)
        cbAfternoon=findViewById(R.id.cbAfternoon)
        cbEvening=findViewById(R.id.cbEvening)
        medicineTypes=findViewById(R.id.medicine_types)
        medicinetimetypes=findViewById(R.id.medicinegroup)

        val tablet: RadioButton = findViewById(R.id.tablet)
        handleMedicineType(tablet, "Tablet")
        val pill: RadioButton = findViewById(R.id.pill)
        handleMedicineType(pill, "Pill")
        val syrup: RadioButton = findViewById(R.id.syrup)
        handleMedicineType(syrup, "Syrup")
        val inhaler: RadioButton = findViewById(R.id.inhaler)
        handleMedicineType(inhaler, "Inhaler")

        val medicineTypes : RadioGroup = findViewById(R.id.medicine_types)
        medicineTypes.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId) {
                R.id.tablet -> {
                    tablet.setBackgroundResource(R.drawable.tick)
                    pill.setBackgroundResource(R.drawable.pill)
                    syrup.setBackgroundResource(R.drawable.syrup)
                    inhaler.setBackgroundResource(R.drawable.inhaler)
                }
                R.id.pill -> {
                    tablet.setBackgroundResource(R.drawable.tablet)
                    pill.setBackgroundResource(R.drawable.tick)
                    syrup.setBackgroundResource(R.drawable.syrup)
                    inhaler.setBackgroundResource(R.drawable.inhaler)
                }
                R.id.syrup -> {
                    tablet.setBackgroundResource(R.drawable.tablet)
                    pill.setBackgroundResource(R.drawable.pill)
                    syrup.setBackgroundResource(R.drawable.tick)
                    inhaler.setBackgroundResource(R.drawable.inhaler)
                }
                R.id.inhaler -> {
                    tablet.setBackgroundResource(R.drawable.tablet)
                    pill.setBackgroundResource(R.drawable.pill)
                    syrup.setBackgroundResource(R.drawable.syrup)
                    inhaler.setBackgroundResource(R.drawable.tick)
                }
            }
        }

        proceed.setOnClickListener {
            if(medicine.text.toString().isEmpty())
            {
                Toast.makeText(this,"Please Enter the Medicine Name",Toast.LENGTH_SHORT).show()
            }
            else if(cbMorning.isChecked==false&&cbAfternoon.isChecked==false&&cbEvening.isChecked==false)
            {
                Toast.makeText(this,"Please select atleast one (Morning/Afternoon/Evening)",Toast.LENGTH_SHORT).show()
            }

            else{
                val title=medicine.text.toString()
                Log.d("TAG","${medicineTypes.checkedRadioButtonId}")
                var description=
                when(medicineTypes.checkedRadioButtonId)
                {
                    R.id.pill->{"Pill"}
                    R.id.tablet->{"Tablet"}
                    R.id.inhaler->{"Inhaler"}
                    R.id.syrup->{"Syrup"}
                    else->""
                }+when(medicinetimetypes.checkedRadioButtonId)
                {
                    R.id.beforemeal->{"\nBefore Meal"}
                    R.id.withfood->{"\nWith Food"}
                    R.id.aftermeal->{"\nAfter Meal"}
                    else->""
                }
                val medicineInformation=MedicineInformation(title,description,0,cbMorning.isChecked,cbAfternoon.isChecked,cbEvening.isChecked)
                allViewModel.insertMeds(medicineInformation)
                finish()
            }

        }
    }

    private fun handleMedicineType(button: RadioButton, toast: String){
        button.setOnLongClickListener {
            Toast.makeText(this, toast, Toast.LENGTH_SHORT).show()
            return@setOnLongClickListener true
        }

    }

    private fun selectMedicineType(button: RadioButton) {
        button.setBackgroundResource(R.drawable.tick)
    }
}
