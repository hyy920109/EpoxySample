package com.hyy.epoxysample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hyy.epoxysample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val rootView = ActivityMainBinding.inflate(layoutInflater)
        setContentView(rootView.root)
        rootView.btnLinear.setOnClickListener {
            startActivity(Intent(this, LinearLayoutManagerActivity::class.java))
        }

        rootView.btnGrid.setOnClickListener {
            startActivity(Intent(this, GridLayoutManagerActivity::class.java))
        }

        rootView.btnPaging.setOnClickListener {
            startActivity(Intent(this, LoadMoreActivity::class.java))
        }

        rootView.btnHeader.setOnClickListener {
            startActivity(Intent(this, HeaderFooterActivity::class.java))

        }
    }
}