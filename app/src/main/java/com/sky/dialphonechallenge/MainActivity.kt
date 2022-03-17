package com.sky.dialphonechallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sky.dialphonechallenge.presentation.MainFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState==null){
            supportFragmentManager.beginTransaction()
                .replace(com.google.android.material.R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}