package com.example.mvvm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mvvm.ui.main.MainFragment
import com.example.mvvm.ui.main.MainViewModel

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()

    }
}