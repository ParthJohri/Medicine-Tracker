package com.examples.medicinetracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.examples.medicinetracker.databinding.HealthQuizLayoutBinding


class HealthQuizActivity : AppCompatActivity() {
    lateinit var allViewModel: AllInformationViewModel

    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        allViewModel = AllInformationViewModel(application)

        //Splash Screen
        installSplashScreen().apply {
            viewModel.isLoading.value
        }

        val isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .getBoolean("isFirstRun", true)

        if (!isFirstRun) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        val binding = HealthQuizLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()


        val answerList = arrayOf("", "", "", "", "")
        var name: String
        var score = 0
        val background = binding.buttonQ1No.background

        binding.buttonQ1Yes.setOnClickListener {
            binding.buttonQ1Yes.setBackgroundResource(R.drawable.star)
            binding.buttonQ1No.background = background
            answerList[0] = "yes"
        }
        binding.buttonQ1No.setOnClickListener {
            binding.buttonQ1No.setBackgroundResource(R.drawable.star)
            binding.buttonQ1Yes.background = background
            answerList[0] = "no"
        }

        binding.buttonQ2Yes.setOnClickListener {
            binding.buttonQ2Yes.setBackgroundResource(R.drawable.star)
            binding.buttonQ2No.background = background
            answerList[1] = "yes"
        }

        binding.buttonQ2No.setOnClickListener {
            binding.buttonQ2No.setBackgroundResource(R.drawable.star)
            binding.buttonQ2Yes.background = background
            answerList[1] = "no"
        }

        binding.buttonQ3Yes.setOnClickListener {
            binding.buttonQ3Yes.setBackgroundResource(R.drawable.star)
            binding.buttonQ3No.background = background
            answerList[2] = "yes"
        }

        binding.buttonQ3No.setOnClickListener {
            binding.buttonQ3No.setBackgroundResource(R.drawable.star)
            binding.buttonQ3Yes.background = background
            answerList[2] = "no"
        }

        binding.buttonQ4Yes.setOnClickListener {
            binding.buttonQ4Yes.setBackgroundResource(R.drawable.star)
            binding.buttonQ4No.background = background
            answerList[3] = "yes"
        }

        binding.buttonQ4No.setOnClickListener {
            binding.buttonQ4No.setBackgroundResource(R.drawable.star)
            binding.buttonQ4Yes.background = background
            answerList[3] = "no"
        }

        binding.buttonQ5Yes.setOnClickListener {
            binding.buttonQ5Yes.setBackgroundResource(R.drawable.star)
            binding.buttonQ5No.background = background
            answerList[4] = "yes"
        }

        binding.buttonQ5No.setOnClickListener {
            binding.buttonQ5No.setBackgroundResource(R.drawable.star)
            binding.buttonQ5Yes.background = background
            answerList[4] = "no"
        }

        fun calculateScore() {
            for (answer in answerList) {
                if (answer == "yes")
                    score += 20
            }
        }

        binding.buttonSubmit.setOnClickListener {

            name = binding.nameUser.text.toString()

            if (name == "") {
                val toastString = "Please, enter your Name to continue!"
                Toast.makeText(this, toastString, Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            for (answer in answerList) {
                if (answer == "") {
                    val toastString = "Please, answer all questions to continue!"
                    Toast.makeText(this, toastString, Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }
            }
            calculateScore()

            val userInformation = UserInformation(name, score)
            allViewModel.insertName(userInformation)
            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                .putBoolean("isFirstRun", false).apply()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}
