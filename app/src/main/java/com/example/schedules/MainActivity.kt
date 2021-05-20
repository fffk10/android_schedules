package com.example.schedules

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    // スケジュールの選択肢
    private val spinnerItems = arrayOf("有給日", "休日", "祝日", "デート")

    lateinit var alertDialog: AlertDialog
    lateinit var adapter: ArrayAdapter<String?>
    var selectedIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // カレンダーのViewを準備
        val calendarView = findViewById<CalendarView>(R.id.calendar)

        // カレンダーの初期選択日を今日にする
        val defaultDate = calendarView.date

        // 日付選択を検知して予定を変更するダイアログを表示
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            // 選択した日付を文字列に
            val selectDate = "$year/${month + 1}/$dayOfMonth"

            // スケジュール変更のスピナーを準備
            val selectScheduleSpinner = findViewById<Spinner>(R.id.selectScheduleSpinner)

            // Adapterの生成
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerItems)

            // 選択肢の各項目のレイアウト
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            // AdapterをSpinnerのAdapterとして設定
            selectScheduleSpinner.adapter = adapter

            // 日付を選択した時に表示するダイアログを準備
            var builder: AlertDialog.Builder = AlertDialog.Builder(this)
            builder.setTitle("$selectDate")
                .setSingleChoiceItems(
                    adapter, selectedIndex,
                    onDialogClickListener
                )
                .setPositiveButton(R.string.schedulePost) { dialog, which ->
                    // Positiveボタンがタップされたときに実行される処理
                    Toast.makeText(this, "登録しました", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton(R.string.cancel) { dialog, which ->
                    // Negativeボタンがタップされたときには何も実行しない
                }
                .create()

            // ダイアログを表示
            builder.show()
        }
    }

    private val onDialogClickListener =
        DialogInterface.OnClickListener { dialog, which -> // AlertDialogで選択された内容を保持
            selectedIndex = which
            alertDialog.dismiss()
        }
}