package com.example.schedules

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    // スケジュールの選択肢
    private val spinnerItems = arrayOf("有給日", "休日", "祝日", "デート")

    private var mSelectedSchedule = 0
    private var tempSchedule = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val text = findViewById<TextView>(R.id.mSelectedSchedule)

        // カレンダーのViewを準備
        val calendarView = findViewById<CalendarView>(R.id.calendar)

        // 日付選択を検知して予定を変更するダイアログを表示
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            // 選択した日付を文字列に
            val selectDate = "$year/${month + 1}/$dayOfMonth"

            // 日付を選択した時に表示するダイアログを準備
            var builder: AlertDialog.Builder = AlertDialog.Builder(this)
            builder.setTitle("$selectDate")
                .setSingleChoiceItems(
                    spinnerItems, -1, onDialogClickListener
                )
                .setPositiveButton(R.string.schedulePost) { dialog, which ->
                    // 「選択したスケジュールを登録する
                    mSelectedSchedule = tempSchedule
                    text.text = spinnerItems[mSelectedSchedule]
                    // 選択したスケジュールを登録する
                    Toast.makeText(this, "登録しました", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton(R.string.cancel) { dialog, which ->
                    // 「戻る」ボタンをタップで何も実行せずダイアログを閉じる
                    dialog.dismiss()
                }
                .create()

            // ダイアログを表示
            builder.show()
        }
    }

    private val onDialogClickListener =
        DialogInterface.OnClickListener { dialog, which -> // AlertDialogで選択された内容を保持
            tempSchedule = which
        }
}