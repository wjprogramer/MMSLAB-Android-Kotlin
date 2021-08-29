package com.wjprogrammer.lab8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

//設計新的類別定義聯絡人的資料結構
data class Contact (
    val name: String, //姓名
    val phone: String //電話
)