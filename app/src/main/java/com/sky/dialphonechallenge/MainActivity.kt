package com.sky.dialphonechallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import com.sky.dialphonechallenge.presentation.MainFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)
        if (savedInstanceState==null){
            supportFragmentManager.beginTransaction()
                .replace(com.google.android.material.R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}