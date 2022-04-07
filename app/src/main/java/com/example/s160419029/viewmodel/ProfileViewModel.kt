package com.example.s160419029.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.s160419029.model.Profile
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ProfileViewModel(application: Application):AndroidViewModel(application) {
    val profileLiveData = MutableLiveData<Profile>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun fetch(){
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://192.168.50.191/utsANMP/users.php?username=kenneth"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<Profile>() {}.type
                val result = Gson().fromJson<Profile>(it, sType)
                profileLiveData.value = result
                Log.d("showvolley", it)
            },
            {
                Log.d("errorvolley", it.toString())
            }
        ).apply {
            tag = "TAG"
        }
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()

        queue?.cancelAll(TAG)
    }
}