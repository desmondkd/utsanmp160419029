package com.example.s160419029.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.s160419029.model.Publisher
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PublisherViewModel(application: Application): AndroidViewModel(application) {
    val publisherLiveData = MutableLiveData<ArrayList<Publisher>>()
    val publisherLoadErrorLiveData = MutableLiveData<Boolean>()
    val loadingLiveData = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun refresh(){
        publisherLoadErrorLiveData.value = false
        loadingLiveData.value = true
        queue = Volley.newRequestQueue(getApplication())
        val url = " http://192.168.50.191/utsANMP/publishers.php"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<ArrayList<Publisher>>() {}.type
                val result = Gson().fromJson<ArrayList<Publisher>>(it, sType)
                publisherLiveData.value = result
                loadingLiveData.value = false
                Log.d("showvolleypublisher", it)
            },
            {
                loadingLiveData.value = false
                publisherLoadErrorLiveData.value = true
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