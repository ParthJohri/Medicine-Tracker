package com.examples.medicinetracker
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageButton
import android.widget.ProgressBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import kotlinx.coroutines.delay
import java.util.*
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.*
import com.examples.medicinetracker.affirmations.AffirmationsActivity



class MainActivity : AppCompatActivity() {
    companion object{
        const val TAG="MainActivity";
    }
    lateinit var allViewModel:AllInformationViewModel
    private lateinit var viewPager: ViewPager2
    lateinit var morning: Button
    lateinit var afternoon: Button
    lateinit var evening: Button
    lateinit var userName: TextView
    lateinit var smileScore: TextView
    lateinit var feedback: TextView
    var counter=0
    val list=ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
//      Removing Action Bar
        supportActionBar?.hide()

        allViewModel = AllInformationViewModel(application)

        setContentView(R.layout.activity_main)
        //      Removing Action Bar
        supportActionBar?.hide()

        morning =findViewById(R.id.morning)
        afternoon =findViewById(R.id.afternoon)
        evening =findViewById(R.id.evening)
        //      Database Score Name, Medicines
        userName= findViewById(R.id.tvUserName)
        smileScore= findViewById(R.id.tvSmileScore)
        feedback= findViewById(R.id.tvFeedbackText)

        //Check condition for creating the activity or not
//        Log.d(TAG, "onCreate:  ${userViewModel.allNames}")

        allViewModel.allNames.observe(this, androidx.lifecycle.Observer {
            Log.d(TAG, "onCreate:  ${it}")
            for (medObject in it) {
                userName.setText(medObject.name.toString())
                smileScore.setText(medObject.score.toString())
                prog(medObject.score)
            }
        })
        allViewModel.allMedicines.observe(this, androidx.lifecycle.Observer {
            Log.d(TAG,"${it}")

            var morningList=it.filter{ medit->medit.DoseMorning}
            var afternoonList=it.filter{ medit->medit.DoseAfter}
            var eveningList=it.filter{ medit->medit.DoseEvening}


            Log.d(TAG,"${morningList}")

            for(meds in it)
            {
            }
        })
        morning.setOnClickListener { var i:Intent =Intent(this,DisplayMedicinesDetails::class.java)
            i.putExtra("time","morning")
            startActivity(i)
        }
        afternoon.setOnClickListener { var i:Intent =Intent(this,DisplayMedicinesDetails::class.java)
            i.putExtra("time","afternoon")
            startActivity(i)  }

        evening.setOnClickListener { var i:Intent =Intent(this,DisplayMedicinesDetails::class.java)
            i.putExtra("time","evening")
            startActivity(i) }


        // Netmeds link
        val netmedsimg: ImageView = findViewById<View>(R.id.netmeds) as ImageView
        netmedsimg.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent()
                intent.action = Intent.ACTION_VIEW
                intent.addCategory(Intent.CATEGORY_BROWSABLE)
                intent.data = Uri.parse("https://www.netmeds.com")
                startActivity(intent)
            }
        })
        
        // PharmEasy link
        val pharmeasyimg: ImageView = findViewById<View>(R.id.pharmeasy) as ImageView
        pharmeasyimg.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent()
                intent.action = Intent.ACTION_VIEW
                intent.addCategory(Intent.CATEGORY_BROWSABLE)
                intent.data = Uri.parse("https://pharmeasy.in")
                startActivity(intent)
            }
        })
        //Medication Button
        val addMedicationButton: FloatingActionButton = findViewById(R.id.addMedication)
        addMedicationButton.setOnClickListener {
            val intent = Intent(this , AddMedication::class.java)
            startActivity(intent)
        }

        val affirmationsButton: ImageButton = findViewById(R.id.quotes)
        affirmationsButton.setOnClickListener {
            val intent = Intent(this , AffirmationsActivity::class.java)
            startActivity(intent)
        }

//      Image Slider
        viewPager = findViewById(R.id.viewPager2)
        val imageList = listOf(
            R.drawable.quote1,
            R.drawable.quote2,
            R.drawable.quote3,
            R.drawable.quote4,
            R.drawable.quote5
        )
        val adapter = ViewPagerAdapter(imageList)
        viewPager.adapter = adapter
        viewPager.autoScroll(lifecycleScope, 2500)



        list.add("ALL IS WELL");
        list.add("YO THATS SICK");
        list.add("YOU BETTER NOT GIVE UP");
        list.add("COOOOOL");
        list.add("AMAZING");

        // Color Change for the ProgressBar

        val pb: ProgressBar = findViewById(R.id.pb)
        pb.getProgressDrawable()
            .setColorFilter(Color.parseColor("#EB9900"), android.graphics.PorterDuff.Mode.SRC_IN);

    }

//Image Slider

    fun ViewPager2.autoScroll(lifecyclerScope: LifecycleCoroutineScope, interval: Long) {
        lifecyclerScope.launchWhenResumed {
            scrollIndefinitely(interval)
        }
    }

    private suspend fun ViewPager2.scrollIndefinitely(interval: Long) {
        delay(interval)
        val numberOfItems = adapter?.itemCount ?: 0
        val lastIndex = if (numberOfItems > 0) numberOfItems - 1 else 0
        val nextItem = if (currentItem == lastIndex) 0 else currentItem + 1
        setCurrentItem(nextItem, true)
        scrollIndefinitely(interval)

    }


    //Progress Bar
    fun prog(value:Int) {
        val pb: ProgressBar = findViewById(R.id.pb)
        val t = Timer()
        counter=0
        val tt: TimerTask = object : TimerTask() {
            override fun run() {
                counter++
                pb.progress = counter
                if (counter == value) t.cancel()
            }
        }
        t.schedule(tt, 0, 25)

        when(value)
        {
           in 0..20->{feedback.setText(list[0])}
           in 20..40->{feedback.setText(list[1])}
           in 40..60->{feedback.setText(list[2])}
           in 60..80->{feedback.setText(list[3])}
           in 80..100->{feedback.setText(list[4])}
        }
    }

}