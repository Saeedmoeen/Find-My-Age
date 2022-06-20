package com.example.findmyage

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.findmyage.databinding.ActivityMainBinding
import java.util.*

const val TAG = ""

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.btnSelectedDate.setOnClickListener {
            findMyAge(binding.textShowYourAge, binding)
        }

    }

    private fun findMyAge(view: View, binding: ActivityMainBinding) {

        var myCalendar = Calendar.getInstance()
        var year = myCalendar.get(Calendar.YEAR)
        var month = myCalendar.get(Calendar.MONTH)
        var day = myCalendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, day ->

            val selectedDate = "$day/${month + 1}/$year"

            binding.textViewShowDob.text = selectedDate


            var dob = Calendar.getInstance()
            dob.set(year, month, day)

            var age = myCalendar.get(Calendar.YEAR) - dob.get(Calendar.YEAR)
            if (myCalendar.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
                age--
            }

            var ageMonth = (myCalendar.get(Calendar.MONTH) + 1) - (dob.get(Calendar.MONTH) + 1)
            if (ageMonth < 0) {
                ageMonth += 12
                if (myCalendar.get(Calendar.DAY_OF_MONTH) - dob.get(Calendar.DAY_OF_MONTH) < 0)
                    ageMonth--
            }

            var ageDay = myCalendar.get(Calendar.DAY_OF_MONTH) - dob.get(Calendar.DAY_OF_MONTH)
            if (ageDay < 0)
                ageDay += if (myCalendar.get(Calendar.DAY_OF_MONTH) >= 6) 31 else 30

            var ageMonths = (age * 12) + ageDay
            var dayToWeek = (ageDay / 4.34524)
            var ageWeeks = (ageMonths * 4.34524) + dayToWeek
            var ageDays = (ageWeeks * 7) + dayToWeek
            var ageHours = ageDays * 24
            var ageMinutes = ageHours * 60
            var ageSecond = ageMinutes * 60


            binding.textShowYourAge.text = "You have $age/$ageMonth/$ageDay year old"
            binding.monthHbd.text = "Or $ageMonths months $ageDay days"
            binding.weeksHbd.text = "Or ${ageWeeks.toInt()} weeks ${dayToWeek.toInt()} days"
            binding.daysHbd.text = "Or ${ageDays.toInt()} days"
            binding.hoursHbd.text = "Or ${ageHours.toInt()} hours"
            binding.minutesHbd.text= "Or ${ageMinutes.toInt()} minutes"
            binding.secondsHbd.text = "Or ${ageSecond.toInt()} second"

        }, year, month, day).show()
    }

}




