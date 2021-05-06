package com.vahitkeskin.kotlininstagramstatusbarclone.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.vahitkeskin.kotlininstagramstatusbarclone.adapter.StatusBarAdapter
import com.vahitkeskin.kotlininstagramstatusbarclone.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var nameList = ArrayList<String>()
    private var imageList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

        for(i in 1..12) {
            nameList.add("@user$i")
            imageList.add("http://www.vahitkeskin.com/appUsers/userimage$i.png")
            val statusBarAdapter = StatusBarAdapter(nameList,imageList,this)
            binding.recyclerView.adapter = statusBarAdapter
            statusBarAdapter.notifyDataSetChanged()
        }
        
    }
}