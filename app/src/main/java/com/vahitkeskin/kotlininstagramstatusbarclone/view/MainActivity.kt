package com.vahitkeskin.kotlininstagramstatusbarclone.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.vahitkeskin.kotlininstagramstatusbarclone.adapter.StatusBarAdapter
import com.vahitkeskin.kotlininstagramstatusbarclone.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var nameList = ArrayList<String>()
    private var profileList = ArrayList<String>()
    private var postList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

        for(i in 1..12) {
            nameList.add("@user$i")
            profileList.add("http://www.vahitkeskin.com/appUsers/userimage$i.png")
            postList.add("http://www.vahitkeskin.com/appUsers/post$i.jpg")
            val statusBarAdapter = StatusBarAdapter(nameList,profileList,postList,this)
            binding.recyclerView.adapter = statusBarAdapter
            statusBarAdapter.notifyDataSetChanged()
        }
        
    }
}