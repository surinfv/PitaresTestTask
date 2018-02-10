package com.fed.pitarestesttask.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.fed.pitarestesttask.R

/**
 * created by Fedor SURIN on 10.02.2018.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager = fragmentManager
        val fragment = FragmentList()
        fragmentManager.beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()
    }
}