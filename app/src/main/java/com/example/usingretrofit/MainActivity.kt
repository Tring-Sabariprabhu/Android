package com.example.usingretrofit

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(){
    lateinit var viewModel: UserViewModel
    lateinit var fragmentManager: FragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        viewModel = UserViewModel()
        fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().add(R.id.container, LoginFragment()).commit()

    }
}