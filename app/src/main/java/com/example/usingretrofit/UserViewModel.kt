package com.example.usingretrofit
import Retrofit
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usingretrofit.API.ApiParams.LoginParams
import com.example.usingretrofit.API.Response.ErrorResponse
import com.example.usingretrofit.Data.User
import com.google.gson.Gson
import kotlinx.coroutines.launch

class UserViewModel : ViewModel(){
    val users = MutableLiveData<List<User>>()
    private val userService = Retrofit().retrofit.create(UserService::class.java)
    fun getUsers() {
        viewModelScope.launch {
            try {
                val result = userService.getUsers()
                if(result.isSuccessful) {
                    users.postValue(result.body())
                    Log.i("My_Tag", result.body().toString())
                }
            }catch (e: Exception){
                Log.e("My_Tag", "Exception caught" + e.localizedMessage)
            }
        }
    }
    fun userLogin(email: String, password: String){
        viewModelScope.launch {
            try {
                val result = userService.login(
                    LoginParams(userEmail = email, userPassword = password)
                )
                if (result.isSuccessful) {
                    Log.i("My_Tag", result.body()?.message!!)
                }else {
                    val gson = Gson()
                    Log.i("My_Tag", gson.fromJson(result.errorBody()?.string(), ErrorResponse::class.java).message)
                }
            } catch (e: Exception) {
                Log.e("My_Tag", "Exception caught" + e.localizedMessage)
            }
        }
    }
}


