package com.example.usingretrofit

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginFragment : Fragment() {
    private lateinit var userViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userViewModel = UserViewModel()
        view.findViewById<Button>(R.id.loginButton).setOnClickListener {
            val userEmail = view.findViewById<EditText>(R.id.userEmail).text.toString()
            val userPassword = view.findViewById<EditText>(R.id.userPassword).text.toString()
            if(userPassword.isEmpty() || userEmail.isEmpty()){
                Log.i("My_Tag","All Fields are required")
            }else{
                userViewModel.userLogin(email = userEmail, password = userPassword)
            }
        }
    }
}