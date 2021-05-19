package com.example.schedules

import android.os.Bundle
import android.widget.CalendarView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var selectedDate: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        selectedDate = findViewById(R.id.selectedDate)

        val format = SimpleDateFormat("yyyy/MM/dd", Locale.US)

        val calendarView = findViewById<CalendarView>(R.id.calendar)

        // カレンダーの初期選択日を今日にする
        val defaultDate = calendarView.date

        // 日付選択を検知
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            selectedDate.text = "$year/${month + 1}/$dayOfMonth"
        }
    }
}