package com.irisaco.retrofit_kotlin

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        textView = findViewById(R.id.txt_view)
        val retrofitService = RetrofitInstance
            .getRetrofitInstance()
            .create(PostService::class.java)


        val responseLiveData: LiveData<Response<Posts>> =
            liveData {
                val response = retrofitService.getPosts()
                emit(response)
            }

        responseLiveData.observe(this, Observer {
            val postsList = it.body()?.listIterator()
            if (postsList != null) {
                while (postsList.hasNext()){
                    val post = postsList.next()
                    Log.i("TAGY", post.title)
                    val result = "Post Title : ${post.title} \n "
                    textView.append(result)
                }
            }
        })
    }
}